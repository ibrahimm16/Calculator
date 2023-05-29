package calculator.math

import calculator.ui.Element
import calculator.ui.Images
import calculator.ui.InputHandler
import org.mariuszgromada.math.mxparser.Argument
import org.mariuszgromada.math.mxparser.Expression

import java.awt.*
import java.awt.font.TextAttribute
import java.awt.image.BufferedImage

class Graph extends Element {

    static Integer imageX = 505, imageY = 5
    static final Integer WIDTH = 690, HEIGHT = 390
    static final Integer MIDDLE_X = 850, MIDDLE_Y = 195
    static BigDecimal xScale = 1.0, yScale = 1.0

    String expression = '5 * sin(x)'
    Map<Integer, GraphPoint> points = [:]
    BufferedImage graphImg
    GraphPoint hovered = new GraphPoint(x: 0, y: 0)

    Double xHover = 850
    Double yValue = 190

    InputHandler inputHandler
    Boolean shouldEval = false


    //graph ticks and scale
    Integer xMin = -23, xMax = 23


    Graph() {
        inputHandler = handler.inputHandler
        evaluate()
    }

    @Override
    void update() {
        if (shouldEval) {
            evaluate()
            shouldEval = false
        }
    }

    @Override
    void render(Graphics2D g) {
        BufferedImage graphBackgound = Images.get('graph')
        g.drawImage(graphBackgound, imageX, imageY, null)
        g.drawImage(graphImg, imageX, imageY, null)

        Rectangle boundingBox = new Rectangle(505, 5, WIDTH, HEIGHT)
        if (boundingBox.contains(inputHandler.x, inputHandler.y)) {
            hovered = points[inputHandler.x as Integer] ?: new GraphPoint(x: 0, y: 0)
        }

        Map fontAttributes = [:]
        fontAttributes[TextAttribute.FAMILY] = 'Times New Roman'
        fontAttributes[TextAttribute.SIZE] = 16
        fontAttributes[TextAttribute.WEIGHT, TextAttribute.WEIGHT_REGULAR]
        g.setFont(new Font(fontAttributes))

        g.setColor(Color.black)
        g.drawString("x: ${hovered.x.trunc(3)}", 510, 20)
        g.drawString("y: ${hovered.y.trunc(3)}", 510, 40)
        g.drawString("xScale: ${xScale}, yScale: ${yScale}", 510, 390)


        g.setColor(Color.red)
        g.fillOval(hovered.pixelX() - 2, hovered.pixelY() - 2, 4, 4)
    }

    private void evaluate() {
        points.clear()
        Argument xArg = new Argument('x')
        Expression formula = new Expression(expression, xArg)
        BigDecimal xInc = (xScale / 15.0 as BigDecimal) // scale divided by pixels between tick marks

        for (BigDecimal xGraph = (xMin * xScale); xGraph < (xMax * xScale); xGraph += xScale) {
            BigDecimal xNext = xGraph + xScale
            for (BigDecimal xVal = xGraph; xVal < xNext; xVal += xInc) {
                xArg.setArgumentValue(xVal)
                BigDecimal yVal = formula.calculate()
                GraphPoint point = new GraphPoint(x: xVal, y: yVal)

                if (point.x && point.y) {
                    points[point.pixelX()] = point
                }
            }
        }

        setGraphImage()
    }

    void setGraphImage() {
        BufferedImage img = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB)
        Graphics2D g = img.createGraphics()
        g.setColor(Color.blue)

        Collection graphPoints = points.values()
        GraphPoint last = graphPoints[0]

        (1..graphPoints.size() - 1).each { i ->
            GraphPoint next = graphPoints[i]
            Integer p1x = last.pixelX(), p1y = last.pixelY()
            Integer p2x = next.pixelX(), p2y = next.pixelY()
            g.drawLine(p1x, p1y, p2x, p2y)
            last = next
        }

        graphImg = img.getSubimage(imageX, imageY, WIDTH, HEIGHT)
        g.dispose()
    }
}
