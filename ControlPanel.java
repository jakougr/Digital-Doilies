
/**
 * Created by Iason Koulas (ik5g15)
 */
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class ControlPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DoilyArea doilyArea;

	public ControlPanel(JFrame window, DoilyArea doilyArea, Gallery gallery) {
		super();

		this.doilyArea = doilyArea;
		setLayout(new FlowLayout());
		setBackground(Color.WHITE);

		/**
		 * Clear Button - clears the JPanel
		 */
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doilyArea.removeStrokes();
				doilyArea.repaint();
			}
		});

		/**
		 * Change Number of Sectors Button - changes the quantity of sectors
		 */
		SpinnerModel numOfSectors = new SpinnerNumberModel((int) doilyArea.getNumberOfSectors(), 1, 100, 1);
		JSpinner sectorSpinner = new JSpinner(numOfSectors);
		sectorSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner test = (JSpinner) e.getSource();
				doilyArea.setNumberOfSectors((int) test.getValue());

				doilyArea.setAngleSectors(360 / (double) doilyArea.getNumberOfSectors());
				doilyArea.repaint();

			}
		});

		/**
		 * Pen Size Button
		 */
		SpinnerModel penSize = new SpinnerNumberModel((int) doilyArea.getPenSize(), 1, 20, 1);

		JSpinner penSizeSpinner = new JSpinner(penSize);
		penSizeSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner temp = (JSpinner) e.getSource();
				doilyArea.setPenSize((int) temp.getValue());
				doilyArea.setPenSizePointList((int) temp.getValue());
				doilyArea.repaint();
			}
		});

		/**
		 * Ink Color Button
		 */
		JButton changeColor = new JButton("Change Color");

		changeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color newColor = JColorChooser.showDialog(window, "Change Ink Color", window.getBackground());
				if (newColor != null) {
					doilyArea.setColorPointList(newColor);
					doilyArea.setInkColor(newColor);
				}
				doilyArea.repaint();
			}
		});

		/**
		 * Save doily to gallery button
		 */
		JButton saveDoily = new JButton("Save Doily");
		saveDoily.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (gallery.gallery.size() < 12) {
					BufferedImage temp = doilyArea.getScreenShot(doilyArea);
					BufferedImage image = resize(temp, 100, 100);

					// gallery.add(new JLabel(new ImageIcon(image)));
					gallery.addGallery(new JLabel(new ImageIcon(image)));
					gallery.revalidate();
				} else {
					JOptionPane.showMessageDialog(null, "Yo can only save up to 12 doily.");
				}
			}
		});
		/**
		 * Deletes a doily
		 */
		JButton delDoily = new JButton("Delete Doily");
		delDoily.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String doilyIndex = JOptionPane.showInputDialog(null,
						"Enter the number of the doily that you want to delete : ", "Delete saved Doily", 1);
				System.out.println(gallery.gallery.size());
				if (Integer.parseInt(doilyIndex) < gallery.gallery.size() && Integer.parseInt(doilyIndex) > 0 ) {
					gallery.delGallery(Integer.parseInt(doilyIndex) - 1);
					gallery.revalidate();
				} else {
					JOptionPane.showMessageDialog(null, "There is no such doily.");
				}

			}
		});

		/**
		 * Toggle Reflections Button
		 */
		JButton toggleReflection = new JButton("Toggle Refection");
		toggleReflection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doilyArea.setReflectDrawing(!doilyArea.isReflectDrawing());
				doilyArea.setRefPointList(doilyArea.isReflectDrawing());
				doilyArea.repaint();
			}
		});

		/**
		 * Toggle sector lines button
		 */
		JButton toggleSectors = new JButton("Toggle Sector Lines");
		toggleSectors.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doilyArea.setToggleSectorLines(!doilyArea.isToggleSectorLines());
				doilyArea.repaint();
			}
		});

		/**
		 * Undo the last stroke button
		 */
		JButton undo = new JButton("Undo");

		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (!doilyArea.getStrokes().isEmpty()) {
					doilyArea.removeStrokes(doilyArea.getStrokes().size() - 1);
					doilyArea.repaint();
				}
			}
		});
		
		JLabel penSizeTitle = new JLabel("PenSize: ");
		JLabel sectorsTitle = new JLabel("Sectors: ");


		add(toggleSectors);
		add(undo);
		add(clear);
		add(changeColor);
		add(toggleReflection);
		add(saveDoily);
		add(sectorsTitle);
		add(sectorSpinner);
		add(penSizeTitle);
		add(penSizeSpinner);
		add(delDoily);
	}

	public static BufferedImage resize(BufferedImage image, int width, int height) {
		BufferedImage saved = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) saved.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(image, 0, 0, width, height, null);
		g2d.dispose();
		return saved;
	}

}
