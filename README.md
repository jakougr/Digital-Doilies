# Digital Doilies

Programming II First Coursework UoS

** Assignment Instructions: Digital Doilies **

For this assignment I would like you to build a Java application that creates a Graphical User Interface for drawing Digital Doilies. A digital doily is a repeated pattern of mouse drawn graphics intended to resemble a lace doily. To achieve this, the drawing area is divided in to sectors radiating out from a central point. Any point drawn in any sector is also drawn at the same relative position in every other sector. In addition to this the points are reflected across each sector.

Here is an example Digital Doily drawn using 24 sectors

Here are the steps to building the application which you must follow:
Part One - The GUI Layout and Components

Create a GUI application using a JFrame that holds a large display panel for drawing the doily, a control panel for the various settings and panel to display a gallery of previously drawn doilies. In the contol panel, add appropriate components to form an interface for the following functionality:

    Ability to clear the doily display
    Ability to change the colour and size of the pen for the doily to be drawn in
    Ability to change the number of sectors that the display is divided into
    Ability to toggle between showing the sector lines, and whether or not to reflect the drawn points.
    Ability to undo the previously drawn point
    Ability to save the current doily image in a gallery of images

In the gallery panel, provide a layout for displaying up to 12 images and a button to remove a selected image from the gallery.

Part Two - Draw the Sector Lines

In the large display panel, draw lines from the centre point out to the edge of the display (you may draw lines all of the same length) to divide the display area in to sectors. For example, the image below shows a display with 12 sectors. Bear in mind that the number of sectors to draw will change during operation of the application.

Part Three - Drawing the Doily

Attach a listener to the main panel to allow the user to draw by dragging the mouse. Draw wherever the mouse is dragged but in addition, draw the same graphics repeated in every sector. That is, if the user draws some points in one sector, the same points should appear (suitably rotated) in every sector. (Hint: look at java.awt.Graphics2D to help, in particular the method rotate(double theta, double x,double y).)

Now, add a boolean flag to record whether the points of the doily should be reflected or not. Whenever this flag is true, ensure that every point drawn is also drawn reflected across the sectors (and repeated in every sector).

(Hint : think carefully about how to reflect the drawn point - look for symmetry in the whole doily rather than focussing on symmetry in each sector. )

For example, the following image shows a single point drawn in Red rotated in to each sector but not reflected, and a single point drawn in Blue that is both reflected and rotated in each sector.

Part Four - Clear and Undo

Attach a listener to the "Clear" component in your control panel to allow the user to clear the doily display so that all that remains displayed are the lines representing the sectors as drawn in Part Two.

Attach a listener to the "Undo" component in your control panel to allow the user to repeatedly remove some previously drawn points from the doily. You may want to consider removing more than a single point at a time to improve usability.
Part Five - Pen Colour, Size and Toggles

Attach listeners to the appropriate components in your control panel that allow the user to change the pen colour and size of point to be drawn at the next mouse drag.

Attach listeners to the appropriate components in your control panel to allow the user to toggle between showing the sector lines or not, and whether to reflect the next drawn points or not.
Part Six - Number of Sectors

Attach a listener to the appropriate component in your control panel to allow the user to change the number of sectors. Your image should redraw the existing doily points rotated through the new number of sectors. For example, if the application defaults to 12 sectors at start up and the user draws a single point. This point will be rendered 12 times - once in each sector (in fact, it will be rendered 24 times if reflections are active also). If the user now increases the number of sectors to 36, say, then this same single point should now be rendered 36 times (or 72 times with reflections).
Part Seven - The Gallery

Add a listener to the "Save" component in your control panel to allow the user to add the currently displayed doily to a gallery of images. The images should be displayed in the gallery panel. You should allow up to 12 images to be displayed in the gallery.

Implement the "remove" feature to allow the user to remove an image from the gallery. Think about how the user will select which image to remove and how this selected will be indicated visually.
Good Practice

Make sure that your code is well organised, concise and well commented. I will award a mark for well presented code that compiles as submitted using Java 1.8 To compile I will use the command line javac command javac *.java in your submission directory. If you have used package declarations etc then make sure that your submission directory accounts for these and make sure that your main class is not within a named package. 
