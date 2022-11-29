import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Blind {
    ArrayList<Point> points;

    public Blind(ArrayList<Point> points) {
        this.points = points;
    }

    private ArrayList<Point> getExtremePoints() {
        ArrayList<Point> extremePoint = new ArrayList<>();
        for (Point point : points) {
            boolean finishOtherLoop = false;
            for (int i = 0; i < points.size() && !finishOtherLoop; i++) {
                for (int j = 0; j < points.size() && !finishOtherLoop; j++) {
                    for (int k = 0; k < points.size(); k++) {
                        Point p1 = points.get(i);
                        Point p2 = points.get(j);
                        Point p3 = points.get(k);
                        if ((calcDet(p1, p2, point) < 0) && (calcDet(p2, p3, point) < 0) && (calcDet(p3, p1, point) < 0)) {
                            finishOtherLoop = true;
                            break;
                        }
                    }
                }
            }
            if (!finishOtherLoop) {
                extremePoint.add(point);
            }
        }
        return extremePoint;
    }

    private double calcDet(Point p1, Point p2, Point p3) {
        return ((p2.x - p1.x) * (p3.y - p1.y)) - ((p2.y - p1.y) * (p3.x - p1.x));
    }

    private ArrayList<Point> getMinPoint(ArrayList<Point> extremes) {
        extremes.sort(Comparator.comparing(Point::getY).reversed().thenComparing(Point::getX));
        return extremes;
    }

    // this method has gotten from lecture codes
    public ArrayList<Point> sortByAngle() {
        ArrayList<Point> extremeSortByX_Y = getMinPoint(getExtremePoints());
        Point p = extremeSortByX_Y.get(0);
        extremeSortByX_Y.remove(0);
        extremeSortByX_Y.sort(Comparator.comparing(x -> Math.atan2((x.getY() - p.getY()), (x.getX() - p.getX()))));
        extremeSortByX_Y.add(0, p);
        return extremeSortByX_Y;
    }
}
