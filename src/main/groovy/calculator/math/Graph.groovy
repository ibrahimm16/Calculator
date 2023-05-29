package calculator.math

import calculator.Handler
import calculator.gui.Displayable
import calculator.gui.InputHandler
import org.mariuszgromada.math.mxparser.Expression

import java.awt.*
import java.awt.font.TextAttribute
import java.math.RoundingMode

class Graph implements Displayable {

    String expression = '5 * sin(x)'
    Map<Integer, Point> points = [:]

    int width = 690, height = 390

    Double xHover = 850
    Double yValue = 190

    Color background = new Color(160, 160, 160)
    Color gridColor = Color.black
    Color resultColor = Color.red

    InputHandler inputHandler

    boolean shouldEval = false

    Graph() {
        inputHandler = Handler.handler.inputHandler
        evaluate()
    }

    void update() {
        if (shouldEval) {
            evaluate()
            shouldEval = false
        }
    }

    void render(Graphics2D g) {
        g.setColor(background)
        g.fillRect(505, 5, 690, 390)

        g.setColor(gridColor)
        g.drawLine(505, 195, 1195, 195)
        g.drawLine(850, 5, 850, 395)
        for (int i = 0; i < 46; i++) {
            int x = 505 + i * 15
            g.drawLine(x, 190, x, 200)
        }

        for (int i = 0; i < 26; i++) {
            int y = 15 + i * 15
            g.drawLine(845, y, 855, y)
        }

        g.setColor(resultColor)
        Collection pointsList = points.values()
        g.drawRect(pointsList[0].x as int, pointsList[0].y as int, 1, 1)
        for (int i = 1; i < pointsList.size(); i++) {
            Point p1 = pointsList[i - 1]
            Point p2 = pointsList[i]
            g.drawLine(p1.x as int, p1.y as int, p2.x as int, p2.y as int)
        }

        Rectangle boundingBox = new Rectangle(505, 5, width, height)
        if (boundingBox.contains(inputHandler.x, inputHandler.y)) {
            xHover = inputHandler.x
            yValue = points[xHover as int]?.y ?: 195
        }

        Map fontAttributes = [:]
        fontAttributes[TextAttribute.FAMILY] = 'Times New Roman'
        fontAttributes[TextAttribute.SIZE] = 16
        fontAttributes[TextAttribute.WEIGHT, TextAttribute.WEIGHT_REGULAR]
        g.setFont(new Font(fontAttributes))
        g.setColor(Color.black)

        double xCoord = ((xHover - 850) / 15.0)
        double yCoord = ((yValue - 195) / -15.0)
        g.drawString("x: ${xCoord}", 510, 20)
        g.drawString("y: ${yCoord}", 510, 40)

    }

    void evaluate() {
        points.clear()
        for (int i = -23; i < 23; i++) {
            for (double x = i; x < i + 1; x += (1.0 / 15.0)) {
                Expression e = new Expression(expression.replaceAll('x', x.toString()))
                double xResult = (850.0 + (x * 15.0))
                double yResult = (-1.0 * (e.calculate() * 15.0) + 195.0)
                points[xResult as int] = new Point(xResult as int, yResult as int)
            }
        }
    }
}
