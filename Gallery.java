/**
 * Created by Iason Koulas (ik5g15)
 */
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class Gallery extends JPanel {

    /**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	ArrayList<JLabel> gallery;

    public ArrayList<JLabel> getGallery() {
		return gallery;
	}
    
    public void delGallery(int index) {
		gallery.remove(index);
		remove(index);
	}
    
    public void addGallery(JLabel image){
    	gallery.add(image);
    	add(image);
    }
    
    

	public Gallery()
    {
        super();
        gallery = new ArrayList<JLabel>();
        setLayout(new GridLayout(4, 3));
        setVisible(true);
        
        
        
    }
	  


}
