package rammicroservice;

import Managers.AnswerManager;
import SQLClass.GlobalVariables;
import Managers.LogManager;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RamMicroservice {

    public static void main(String[] args) {  
        GlobalVariables global_variables = new GlobalVariables(args[0], args[1], args[2], Integer.valueOf(args[3]));
        LogManager log_manager = new LogManager(global_variables, "RamMicroservice");
        Connection connection = null;
        ConnectionFactory factory = null;
        
        String name_class = "RamMicroservice";
        
        try {  
            factory = new ConnectionFactory();
            factory.setHost(global_variables.getHost());
            factory.setPort(global_variables.getPort());
            connection = factory.newConnection();
            
            final Channel channel = connection.createChannel();
            channel.queueDeclare(global_variables.getQueue_name(), false, false, false, null);
            channel.basicQos(1);
            
            System.out.println(" [x] Host: " + global_variables.getHost() + ", Queue: " + global_variables.getQueue_name());
            
            Consumer consumer = new AnswerManager(channel, global_variables, log_manager);

            channel.basicConsume(global_variables.getQueue_name(), false, consumer);
            
            log_manager.CallEvent("Start", name_class);
            
            while (true) {
                synchronized(consumer) {
                    try {
      			consumer.wait();
                    } catch (InterruptedException e) {
                        log_manager.CallError(e.toString(), "main. Main wait", name_class);	    	
                    }
                }
            }
        } 
        catch (IOException | TimeoutException e) {
            log_manager.CallError(e.toString(), "main. Main program", name_class);
        }
        finally {
            if (connection != null)
                try {
                    connection.close();
                } 
                catch (IOException _ignore) {
                    log_manager.CallError(_ignore.toString(), "main. Close connection", name_class);
                }
        }
    }
    
}
