package calculator.math

import calculator.ui.Element
import calculator.ui.Images
import calculator.ui.InputHandler
import org.mariuszgromada.math.mxparser.Argument
import org.mariuszgromada.math.mxparser.Expression

import java.awt.*
import java.awt.font.TextAttribute
import java.awt.image.BufferedImage
import java.util.concurrent.ConcurrentHashMap

class Graph extends Element {

    static Integer imageX = 505, imageY = 5
    static Integer width = 690, height = 390

    static BigDecimal xScale = 1.0, yScale = 1.0, minScale = .10

    static final GraphPoint defaultPoint = new GraphPoint('x', 0.0)

    static final ticksMinX = -23, ticksMaxX = 23

    String scaleKey = "${xScale}-${yScale}"

    InputHandler inputHandler
    String expression
    BufferedImage graphBackground
    GraphPoint hovered
    Integer xMin, xMax

    Map<BigDecimal, GraphPoint> points
    Map<String, GraphScale> scales

    Map<BigDecimal, Map> scalePoints

    Graph() {
        inputHandler = handler.inputHandler
        expression = '2.5 * sin(x)'
        graphBackground = Images.get('graph')
        hovered = defaultPoint
        xMin = -23
        xMax = 23

        points = new ConcurrentHashMap<>()
        scales = new ConcurrentHashMap<>()
        scalePoints = new ConcurrentHashMap<>()

        handler.executorService.submit {
            evaluateGraph()
        }
    }

    void keyTyped(char key) {
        boolean scaleChanged = (key as String) in ['w', 'a', 's', 'd']

        switch (key) {
            case 'w':
                yScale++
                break
            case 'a':
                xScale = (xScale - 1).max(1.0)
                break
            case 's':
                yScale = (yScale - 1).max(1.0)
                break
            case 'd':
                xScale++
        }

        scaleKey = "${xScale}-${yScale}"
        if (scaleChanged) scales[scaleKey] = newGScale()
    }

    @Override
    void update() {
        super.update()
    }

    @Override
    void render(Graphics2D g) {
        g.drawImage(graphBackground, imageX, imageY, null)
        GraphScale scale = scales.get(scaleKey)
        if (scale?.image) {
            g.drawImage(scale.image, imageX, imageY, null)
        }


        Rectangle boundingBox = new Rectangle(505, 5, width, height)
        if (boundingBox.contains(inputHandler.x, inputHandler.y)) {
            hovered = points[inputHandler.x as Integer] ?: defaultPoint
        }

        Map fontAttributes = [:]
        fontAttributes[TextAttribute.FAMILY] = 'Times New Roman'
        fontAttributes[TextAttribute.SIZE] = 16
        fontAttributes[TextAttribute.WEIGHT, TextAttribute.WEIGHT_REGULAR]
        g.setFont(new Font(fontAttributes))

        g.setColor(Color.black)
        g.drawString("X: ${hovered.x.trunc(3)}", 510, 20)
        g.drawString("Y: ${hovered.y.trunc(3)}", 510, 40)
        g.drawString("X scale: ${xScale}", 510, 370)
        g.drawString("Y scale: ${yScale}", 510, 390)

        g.drawString("points: ${points.size()}", 510, 350)


        g.setColor(Color.blue)
//        g.fillOval(hovered.pixelX() - 2, hovered.pixelY() - 2, 4, 4)
    }

    void evaluateGraph() {
        points.clear()
        handler.executorService.submit {
            for (BigDecimal xGraph = 0; xGraph < 125; xGraph += .1 / 15.0) {
                GraphPoint point = new GraphPoint(expression, xGraph)
                synchronized (points) {
                    points[xGraph] = point
                }
            }
        }

        handler.executorService.submit {
            for (BigDecimal xGraph = 0; xGraph > -125; xGraph -= .1 / 15.0) {
                GraphPoint point = new GraphPoint(expression, xGraph)
                synchronized (points) {
                    points[xGraph] = point
                }
            }
        }

        sleep(1500)
        xScale = 1.0
        yScale = 1.0
        scales[scaleKey] = newGScale()
    }

    GraphScale newGScale() {
        new GraphScale(xScale, yScale, points.values())
    }
}
