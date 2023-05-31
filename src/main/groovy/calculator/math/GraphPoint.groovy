package calculator.math

import groovy.transform.Canonical
import org.mariuszgromada.math.mxparser.Argument
import org.mariuszgromada.math.mxparser.Expression

@Canonical
class GraphPoint {

    String formula
    BigDecimal x, y

    GraphPoint(String formula, BigDecimal x) {
        this.formula = formula
        this.x = x
        y = calculateY()
    }

    BigDecimal calculateY() {
        Argument xArg = new Argument('x')
        Expression yExp = new Expression(formula, xArg)
        xArg.setArgumentValue(x)

        return yExp.calculate()
    }

    Integer pixelX(BigDecimal scale) {
        Argument xArg = new Argument('x')
        xArg.setArgumentValue(x)

        String expressionStr = "x * 15.0 / ${scale}"
        Expression pixelXExp = new Expression(expressionStr, xArg)

        return pixelXExp.calculate().round()
    }

    Integer pixelY(BigDecimal scale) {
        Argument yArg = new Argument('y')
        yArg.setArgumentValue(y)

        String expressionStr = "-1.0 * y * 15.0 / ${scale}"
        Expression pixelYExp = new Expression(expressionStr, yArg)

        return pixelYExp.calculate().round()
    }
}
