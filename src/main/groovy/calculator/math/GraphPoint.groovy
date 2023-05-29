package calculator.math

import org.mariuszgromada.math.mxparser.Argument
import org.mariuszgromada.math.mxparser.Expression

class GraphPoint {

    BigDecimal x, y

    Integer pixelX() {
        Argument xArg = new Argument('x')
        xArg.setArgumentValue(x)

        Expression pixelXExp = new Expression("${Graph.MIDDLE_X} + (x * 15.0 / ${Graph.xScale})", xArg)

        return pixelXExp.calculate() as Integer
    }

    Integer pixelY() {
        Argument yArg = new Argument('y')
        yArg.setArgumentValue(y)

        Expression pixelYExp = new Expression("-1.0 * y * 15.0 / ${Graph.yScale} + ${Graph.MIDDLE_Y}", yArg)

        return pixelYExp.calculate() as Integer
    }
}
