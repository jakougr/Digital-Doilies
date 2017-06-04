/**
 * Created by Iason Koulas (ik5g15)
 */
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Stroke {
	/**
	 * Variables
	 */
	private ArrayList<Point> pointList;
	private Color color;
	private boolean reflected;
	private int penSize;
	DoilyArea cArea;

	public void setPenSize(int penSize) {
		this.penSize = penSize;
	}
	public ArrayList<Point> getPointList() {
		return pointList;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void addPoint(Point point) {
		this.pointList.add(point);
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setReflected(boolean reflected) {
		this.reflected = reflected;
	}

	public Stroke(DoilyArea area) {
		pointList = new ArrayList<>();
		this.color = area.getInkColor();
		this.reflected = area.isReflectDrawing();
		this.penSize = area.getPenSize();
		cArea = area;

	}
	/**
	 * Draws each individual "mouseDragged and mouseClicked session"
	 */
	public void drawStroke(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(color);
		
		//For each sector
		for (int i = 0; i < cArea.getNumberOfSectors(); i++) {
			g2d.setStroke(new BasicStroke(penSize));
			Iterator<Point> iter = pointList.iterator();
			Point firstPoint = null; 
			Point secondPoint = null;
			//For the first point/stroke
			if (pointList.size() == 1) {
				firstPoint = pointList.get(0);
				g2d.drawRect((int) firstPoint.getX(), (int) firstPoint.getY(), cArea.getPenSize(), cArea.getPenSize());
				if (reflected) {  // in case the line must be reflected
					g2d.drawRect(-(int) firstPoint.getX(), (int) firstPoint.getY(), cArea.getPenSize(), cArea.getPenSize());
				}
				g2d.rotate(Math.toRadians(cArea.getAngleSectors()));
			} else if (pointList.size() > 1) { // After the first stroke
				firstPoint = iter.next();

				while (iter.hasNext()) {
					secondPoint = iter.next();

					g2d.drawLine((int) firstPoint.getX(), (int) firstPoint.getY(), (int) secondPoint.getX(), (int) secondPoint.getY());

					if (reflected) {  // in case the line must be reflected
						g2d.drawLine(-(int) firstPoint.getX(), (int) firstPoint.getY(), -(int) secondPoint.getX(),
								(int) secondPoint.getY());
					}

					firstPoint = secondPoint;
				}
				g2d.rotate(Math.toRadians(cArea.getAngleSectors()));
			}
		}

	}


}
