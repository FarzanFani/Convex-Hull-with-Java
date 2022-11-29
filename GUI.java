import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GUI extends JPanel {
    private final ArrayList<Point> points = new ArrayList<>();
    private final Checkbox showX_Y = new Checkbox("Show X-Y");
    private final Button clearPage = new Button("Clear");
    private final JRadioButton blind = new JRadioButton("Blind");
    private final JRadioButton bruteForce = new JRadioButton("Brute Force");
    private final JRadioButton grahamScan = new JRadioButton("Graham Scan");
    private final ButtonGroup radioButtons = new ButtonGroup();

    public GUI() {
        add(blind);
        add(grahamScan);
        add(bruteForce);
        add(showX_Y);
        add(clearPage);

        radioButtons.add(blind);
        radioButtons.add(bruteForce);
        radioButtons.add(grahamScan);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                points.add(e.getPoint());
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.showX_Y.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                repaint();
            }
        });

        this.clearPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                points.clear();
                showX_Y.setState(false);
                repaint();
            }
        });

        this.blind.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                repaint();
            }
        });

        this.bruteForce.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // add brute force algorithm in phase 3
            }
        });

        this.grahamScan.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // add graham scan algorithm in phase 3
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Point p : points) {
            g.setColor(Color.BLACK);
            g.fillOval(p.x, p.y, 10, 10);
            String a = "(" + p.x + "," + p.y + ")";
            if (showX_Y.getState()) {
                g.setColor(Color.RED);
                g.drawString(a, p.x + 10, p.y + 10);
            }
            if (blind.isSelected()) {
                Blind blind = new Blind(points);
                ArrayList<Point> points = blind.sortByAngle();
                for (int i = 0; i < points.size() - 1; i++) {
                    g.setColor(Color.GREEN);
                    Point p1 = points.get(i);
                    Point p2 = points.get(i + 1);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
                g.drawLine(points.get(0).x, points.get(0).y, points.get(points.size() - 1).x, points.get(points.size() - 1).y);
            }
        }
    }
}
