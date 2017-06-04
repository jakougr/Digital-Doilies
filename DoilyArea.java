
/**
 * Created by Iason Koulas (ik5g15)
 */
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class DoilyArea extends JPanel {

	/**
	 * Get and Set methods
	 */
	private static final long serialVersionUID = 1L;

	public Integer getPenSize() {
		return penSize;
	}

	public void setPenSize(Integer penSize) {
		this.penSize = penSize;
	}

	public Color getInkColor() {
		return inkColor;
	}

	public void setInkColor(Color inkColor) {
		this.inkColor = inkColor;
	}

	public Integer getNumberOfSectors() {
		return numberOfSectors;
	}

	public void setNumberOfSectors(Integer numberOfSectors) {
		this.numberOfSectors = numberOfSectors;
	}

	public double getAngleSectors() {
		return angleSectors;
	}

	public void setAngleSectors(double angleSectors) {
		this.angleSectors = angleSectors;
	}

	public boolean isToggleSectorLines() {
		return toggleSectorLines;
	}

	public void setToggleSectorLines(boolean toggleSectorLines) {
		this.toggleSectorLines = toggleSectorLines;
	}

	public boolean isReflectDrawing() {
		return reflectDrawing;
	}

	public void setReflectDrawing(boolean reflectDrawing) {
		this.reflectDrawing = reflectDrawing;
	}

	public BufferedImage getPaint() {
		return paint;
	}

	public void setPaint(BufferedImage paint) {
		this.paint = paint;
	}

	Stroke getPointList() {
		return pointList;
	}

	public void setPointList(Stroke pointList) {
		this.pointList = pointList;
	}

	public void setColorPointList(Color color) {
		this.pointList.setColor(color);
	}

	public void setPenSizePointList(int size) {
		this.pointList.setPenSize(size);
	}

	public void setRefPointList(boolean reflection) {
		this.pointList.setReflected(reflection);
	}

	public ArrayList<Stroke> getStrokes() {
		return strokes;
	}

	public void setStrokes(ArrayList<Stroke> strokes) {
		this.strokes = strokes;
	}

	public void removeStrokes() {
		this.strokes.clear();
	}

	public void removeStrokes(int index) {
		this.strokes.remove(index);
	}

	// Adds the new point to the PointList
	public void addPoint(MouseEvent e) {
		if (numberOfSectors % 2 == 0) {
			int tempY = e.getY() - getHeight() / 2;
			int tempX = e.getX() - getWidth() / 2;
			pointList.addPoint(new Point(-tempX, -tempY));
		} else {
			int tempY = e.getY() - getHeight() / 2;
			int tempX = e.getX() - getWidth() / 2;
			pointList.addPoint(new Point(tempX, tempY));
		}
		repaint();
	}

	private Integer penSize = 2;
	private Color inkColor = Color.WHITE;
	private Integer numberOfSectors = 6;
	private double angleSectors = 360 / numberOfSectors;
	private boolean toggleSectorLines = true;
	private boolean reflectDrawing = false;
	public BufferedImage paint;

	public Stroke pointList = new Stroke(this);
	public ArrayList<Stroke> strokes = new ArrayList<>();

	public DoilyArea() {
		super();

		this.setBackground(Color.BLACK);
		paint = new BufferedImage(600, 600, BufferedImage.TYPE_4BYTE_ABGR);
		inkColor = Color.WHITE;
		numberOfSectors = 6;
		penSize = 2;
		angleSectors = 360 / numberOfSectors;

		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addPoint(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			/**
			 * It creates a new list with for the next set of points (stroke)
			 * and saves the old one
			 */
			public void mouseReleased(MouseEvent e) {
				strokes.add(pointList);
				pointList = new Stroke(DoilyArea.this);
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				addPoint(e);
			}

			@Override
			public void mouseMoved(MouseEvent e) {

			}
		});

	}

	public void paint(Graphics g) {
		super.paint(g);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.BLACK);
		g2d.translate(this.getWidth() / 2, this.getHeight() / 2);

		// Draws the lines that divide the sectors
		if (toggleSectorLines)
			for (int i = 0; i < numberOfSectors; i++) {
				g2d.setStroke(new BasicStroke(1));
				g2d.setColor(Color.GREEN);
				g2d.drawLine(0, 0, 0, -360);
				g2d.rotate(Math.toRadians(angleSectors));
			}

		g2d.setStroke(new BasicStroke(penSize));
		g2d.setColor(Color.BLACK);

		for (int i = 0; i < strokes.size(); i++) {
			strokes.get(i).drawStroke(g2d);
		}
		pointList.drawStroke(g2d);

	}

	public BufferedImage getScreenShot(JPanel panel) {
		BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
		panel.paint(image.getGraphics());
		return image;
	}

}
