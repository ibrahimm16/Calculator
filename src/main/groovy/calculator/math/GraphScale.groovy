package calculator.math

import java.awt.*
import java.awt.image.BufferedImage
import java.util.List
import java.util.concurrent.CopyOnWriteArrayList

class GraphScale {

    BigDecimal xScale, yScale
    BufferedImage image
    List<GraphPoint> points

    GraphScale(BigDecimal xScale, BigDecimal yScale, Collection<GraphPoint> graphPoints) {
        this.xScale = xScale
        this.yScale = yScale
        setPoints(graphPoints)
        setImage()
    }

    void setPoints(Collection<GraphPoint> graphPoints) {
        BigDecimal min = xScale * Graph.ticksMinX
        BigDecimal max = xScale * Graph.ticksMaxX
        points = graphPoints.findAll {
            boolean inRange = it.x >= min && it.x <= max
            boolean inScale = it.x.remainder(xScale / 15.0) < .001
            inRange && inScale
        }.unique{
            it.x
        } .sort { it.x }

        println(points.size())
    }

    void setImage() {
        image = new BufferedImage(Graph.width, Graph.height, BufferedImage.TYPE_INT_ARGB)
        Graphics2D g = image.getGraphics() as Graphics2D
        g.setColor(Color.blue)

        GraphPoint last = points[0]
        (1..points.size() - 1).each { i ->
            GraphPoint next = points[i]
            int p1x = last.pixelX(xScale)
            int p1y = last.pixelY(yScale)
            int p2x = next.pixelX(xScale)
            int p2y = next.pixelY(yScale)
            last = next

            g.drawLine(p1x, p1y, p2x, p2y)
        }

        g.dispose()
    }
}
