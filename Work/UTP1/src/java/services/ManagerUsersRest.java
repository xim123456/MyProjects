package services;

import Managers.LogManager;
import Managers.MessageManager;
import MyEnum.RestUserEnum;
import MyInterface.RestInterface;
import RestObject.RestObject;
import RestObject.RestObjectConverter;
import SQLClass.GlobalVariables;
import SQLClass.ResData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("ManagerUsers")
public class ManagerUsersRest implements RestInterface {
    ResData answer;
    String name_class = "ManagerUsersRest";
    GlobalVariables global_variables;
    MessageManager message_manager;
    LogManager log_manager;
    Gson gson;
     
    public ManagerUsersRest() {
        global_variables = new GlobalVariables();
        log_manager = new LogManager(global_variables, "ManagerUsersRest");
        
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(RestObject.class, new RestObjectConverter());
            gson = builder.create();
            
            message_manager = new MessageManager(global_variables);
        }
        catch(Exception ex) {
            answer = log_manager.CallError(ex.toString(), "Error on init", name_class);
        } 
    }
    
    @POST
    @Path("login")
    @Consumes("application/x-www-form-urlencoded")
    public Response getUser(@FormParam("json") String json) {
        answer = CreateMessage(RestUserEnum.User_select_one.ordinal(), json, "getUser(String). Send message", "user_session_queue");
        return InitMessage(answer);
    }
    
    @POST
    @Path("register")
    @Consumes("application/x-www-form-urlencoded")
    public Response addUser(@FormParam("json") String json) {
        answer = CreateMessage(RestUserEnum.User_add.ordinal(), json, "addUser(String). Send message", "user_session_queue");
        return InitMessage(answer);
    }
    
    @POST
    @Path("pre_register")
    @Consumes("application/x-www-form-urlencoded")
    public Response addUserAuth(@FormParam("json") String json) {
        answer = CreateMessage(RestUserEnum.User_pre_reg.ordinal(), json, "addUserAuth(String). Send message", "user_session_queue");
        return InitMessage(answer);
    }
    
    @POST
    @Path("cancel_register")
    @Consumes("application/x-www-form-urlencoded")
    public Response UserCancel(@FormParam("json") String json) {
        answer = CreateMessage(RestUserEnum.User_cancel.ordinal(), json, "UserCancel(String). Send message", "user_session_queue");
        return InitMessage(answer);
    }
    
    @POST
    @Path("checkParamUser")
    @Consumes("application/x-www-form-urlencoded")
    public Response checkParamUser(@FormParam("json") String json) {
        answer = CreateMessage(RestUserEnum.User_check_param.ordinal(), json, "checkParamUser(String). Send message", "user_session_queue");
        return InitMessage(answer);
    }
    
    @POST
    @Path("deleteUser")
    @Consumes("application/x-www-form-urlencoded")
    public Response deleteUser(@FormParam("json") String json) {
        answer = CreateMessage(RestUserEnum.User_delete.ordinal(), json, "deleteUser(String). Send message", "user_session_queue");
        return InitMessage(answer);
    }
    
    @POST
    @Path("updateUser")
    @Consumes("application/x-www-form-urlencoded")
    public Response updateUser(@FormParam("json") String json) {
        answer = CreateMessage(RestUserEnum.User_update.ordinal(), json, "updateUser(String). Send message", "user_session_queue");
        return InitMessage(answer);
    }
    
    @POST
    @Path("getUserEmails")
    @Consumes("application/x-www-form-urlencoded")
    public Response getUserEmails(@FormParam("json") String json) {
        answer = CreateMessage(RestUserEnum.User_select_emails.ordinal(), json, "getUserEmails(String). Send message", "user_session_queue");
        return InitMessage(answer);
    }
        
    @POST
    @Path("requestRecoveryPassword")
    @Consumes("application/x-www-form-urlencoded")
    public Response beginChangePassword(@FormParam("json") String json) {
        answer = CreateMessage(RestUserEnum.User_begin_change_password.ordinal(), json, "beginChangePassword(String). Send message", "user_session_queue");
        return InitMessage(answer);
    }
    
    @POST
    @Path("updatePassword")
    @Consumes("application/x-www-form-urlencoded")
    public Response endChangePassword(@FormParam("json") String json) {
        answer = CreateMessage(RestUserEnum.User_end_change_password.ordinal(), json, "endChangePassword(String). Send message", "user_session_queue");
        return InitMessage(answer);
    }
    
    @POST
    @Path("updateAccountPassword")
    @Consumes("application/x-www-form-urlencoded")
    public Response updateAccountPassword(@FormParam("json") String json) {
        answer = CreateMessage(RestUserEnum.User_update_account_password.ordinal(), json, "endChangePassword(String). Send message", "user_session_queue");
        return InitMessage(answer);
    }
    
    @Override
    public ResData CreateMessage(int code, String json, String ErrorMessage, String name_microservice) {
        try {        
            JsonParser parser = new JsonParser();
            JsonObject json_obj = parser.parse(json).getAsJsonObject();
            String user_id = "";
            try {
                user_id = checkFildString(json_obj, "user_id");
            }
            catch(NullPointerException e){
                user_id = "";
            }
            
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(RestObject.class, new RestObjectConverter());
            return message_manager.call(builder.create().toJson(new RestObject(json_obj, user_id, code)), name_microservice);
        }
        catch (Exception ex) {
            return log_manager.CallError(ex.toString(), ErrorMessage, name_class);
        }
    } 
}