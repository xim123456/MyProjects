using System.Windows;
using System.Reflection;
using System.Windows.Markup;

[assembly: ThemeInfo(
    ResourceDictionaryLocation.None, //where theme specific resource dictionaries are located
    //(used if a resource is not found in the page, 
    // or application resource dictionaries)
    ResourceDictionaryLocation.SourceAssembly //where the generic resource dictionary is located
    //(used if a resource is not found in the page, 
    // app, or any theme specific resource dictionaries)
)]
//</SnippetThemeInfo>
[assembly: AssemblyTitleAttribute("Controls for WPF")]
[assembly: AssemblyCompanyAttribute("T-Alex Software")]
[assembly: AssemblyProductAttribute("TAlex.WPF.Controls")]
[assembly: AssemblyCopyrightAttribute("Copyright � 2015 T-Alex Software")]
[assembly: AssemblyVersionAttribute("2.0.1.0")]
[assembly: AssemblyFileVersionAttribute("2.0.1.0")]

[assembly: XmlnsDefinition("http://schemas.talex-soft.com/2010/xaml/presentation", "TAlex.WPF.Controls")]
[assembly: XmlnsDefinition("http://schemas.talex-soft.com/2010/xaml/presentation", "TAlex.WPF.Converters")]
[assembly: XmlnsDefinition("http://schemas.talex-soft.com/2010/xaml/presentation", "TAlex.WPF.Services.DragAndDrop")]
[assembly: XmlnsDefinition("http://schemas.talex-soft.com/2010/xaml/presentation", "TAlex.WPF.Services.PushBinding")]
[assembly: XmlnsPrefix("http://schemas.talex-soft.com/2010/xaml/presentation", "talex")]
[assembly: AssemblyDescriptionAttribute("Collection of controls for WPF")]
