# Java Color Picker.
The Java Color Picker was a project created for Comptuer Languages and Implemention course. The project demonstrates use of Java, Java File I/O, and the Swing User Interface library to create a Color Sampler application. The application uses the Model-View-Controller (MVC) Design Pattern to encapsulate the application's business logic and user interface componenets for maintainability of the application. Figure 1 represents a comparison between tasked assignment and developed application. 

<p float="left" align="center">
<img src="./Images/Image1.png" alt="Initial start up of the Color Picker application." style="width:300px;"/>
<img src="./Images/Image2.png" alt="Color Picker application from Assigned Homework." style="width:300px;"/>
 </p>
 <p align="center">
 Fig. 1. Side-by-side comparison of described homework problem and developed application. 
 </p>

## Application Organization.

The business logic of the application is encapsulated within the `application_model` class and user interface components are encapsulated in the `application_view` class. For now on within the documentation the business logic will be referred as **Model** and the user interface components will be referred as **View**. The `application_controller` class is used by the View and Model to communicate events between the two classes. Figure 2 represents the relationship between the three classes. 

<p align="center">
<img src="./Images/Image3.png" alt="UML Drawing of Color Picker application." style="width:800px;"/>
</p>
<p align="center">
Fig. 2. UML of the application. 
 </p>

## Application Operation.
Components the View wishes to open to the Model for processing of events such as mouse-clicks must be exposed through *getter* methods. Once the View component is exposed, the component must be *registered* with the application's `application_controller` class in which the event is defined with the event behaviour. The event behaviour is defined by a called method within the application Model. Every time a event occurs such as a mouse-click, the appropriate function from Model is called. Model data changed must be reflected within the View by updating relevant View component(s) within the defined event behaviour. 

### A Example with Mouse-click of "+" Button.
When a mouse-click for increasing the blue value via the "+" button occurs the `application_controller` calls the appropriate registered event behaviour withing the class being `make_blue_lighter()` function. Within the function `make_blue_lighter()`, the appropriate function from the Model to change RGB data and increase the blue value is `model.increment_blue()`. 



The View must reflect changes from the Model data. The registered event behaviour uses the *getter* methods to acquire the necssary exposed components of the View which in this includes: the main window, the color viewer, and the blue textfield value. The Controller calls upon `view.get_main_window()`,`view.get_color_viewer()`, and `view.get_bluePalette_field()` to do the conduct the necessary visual changes to the user interface. 


Figure 3 shows the process of reacting to a user initiating a mouse-click on a button to increase the blue value in the Color Sampler. 

```
void make_blue_lighter() {

  // Update the blue value within the Model data.
  model.increment_blue();
  
  // Update window title bar to show new text.
  view.get_main_window().setTitle(model.update_window_title());
  // Update color viewer to display new color.
  view.get_color_viewer().setBackground(new Color(model.get_preview_color().get_red(), model.get_preview_color().get_green(), model.get_preview_color().get_blue()));
  // Update text field to show new blue value.
  view.get_bluePalette_field().setText(String.valueOf(model.get_preview_color().get_blue()));

}
```
<p align="center">
Fig. 3. The defined registered event behaviour wtihin the Controller class. 
</p>
