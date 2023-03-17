import gpdraw.*;
import java.awt.geom.Point2D;
import java.util.ArrayDeque;

public class Tree {
    LSystem system = new LSystem();
    ArrayDeque<Point2D> stack = new ArrayDeque<>();
    ArrayDeque<Double> stackDeg = new ArrayDeque<>();

    private final int iteration;

    private SketchPad canvas;
    private DrawingTool pen;

    public Tree(int iter) {
        canvas = new SketchPad(800, 800);
        pen = new DrawingTool(canvas);

        pen.up();
        pen.move(0, -200);
        pen.setDirection(90);
        pen.down();
        pen.setWidth(3);

        iteration = iter;
    }

    public void drawTree() {
        for (int i = 0; i < iteration; i++) {
            System.out.println("Iteration: " + system.getAxiom());
            system.nextStep();
        }
        drawLine();
    }

    private void setupPen(double x, double y, double dir) {
        pen.up();
        pen.move(x, y);
        pen.setDirection(dir);
        pen.down();
    }

    private void drawLine() {
        for (int i = 0; i < system.getAxiom().length(); i++) {
            char c = system.getAxiom().charAt(i);
            if (c == 'F') {
                pen.forward(3);
            }
            else if(c == '+') {
                pen.turnLeft(25);
            }
            else if(c == '-') {
                pen.turnRight(25);
            }
            else if (c == '[') {
                stack.push(new Point2D.Double(pen.getXPos(), pen.getYPos()));
                stackDeg.push(pen.getDirection());
            }
            else if (c == ']') {
                Point2D point = stack.pop();
                double dir = stackDeg.pop();
                setupPen(point.getX(), point.getY(), dir);
            }
        }
    }
}
