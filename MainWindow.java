/**
 * Created by Iason Koulas (ik5g15)
 */
import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer windowHeight;
	private Integer windowWidth;

	public MainWindow(int height, int width, String name) {

		super(name);

		this.windowHeight = height;
		this.windowWidth = width;

		this.setSize(windowWidth, windowHeight);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		/**
		 * Main Panel // BorderLayout
		 */
		JPanel mwindow = new JPanel();
		mwindow.setLayout(new BorderLayout());
		mwindow.setBackground(Color.GRAY);
		mwindow.setVisible(true);
		
		//Area that the doily will be drawn
		DoilyArea doilyArea = new DoilyArea();
		doilyArea.setPreferredSize(new Dimension(this.windowWidth / 2, this.windowHeight));

		// Options Panel
		JPanel options = new JPanel();
		options.setPreferredSize(new Dimension(this.windowWidth / 3, this.windowHeight));
	//	options.setLayout(new GridLayout(2, 1));
		options.setLayout(new FlowLayout());
		options.setBackground(Color.GRAY);

		// Gallery
		Gallery gallery = new Gallery();
		gallery.setPreferredSize(new Dimension(this.windowWidth / 3, this.windowHeight / 2));
		gallery.setVisible(true);
		
		// Buttons
		ControlPanel buttons = new ControlPanel(this, doilyArea, gallery);
		buttons.setPreferredSize(new Dimension(this.windowWidth / 3, this.windowHeight / 2));
		
		JLabel galTitle = new JLabel("Gallery:");
		JLabel optTitle = new JLabel("Options:");
		
		options.add(galTitle);
		options.add(gallery);
		options.add(optTitle);
		options.add(buttons);

		mwindow.add(options, BorderLayout.WEST);
		mwindow.add(doilyArea, BorderLayout.CENTER);

		this.add(mwindow);

		this.pack();
		

	}
}
