package calculator.math

import calculator.gui.Displayable
import org.mariuszgromada.math.mxparser.Expression

import java.awt.Color
import java.awt.Graphics2D

class Calculator implements Displayable {

    String expression = ''
    String result = 0

    void keyTyped(Character c) {
        expression += c
    }

    void keyDeleted() {
        expression = expression.isEmpty() ? '' : expression.substring(0, expression.length() - 1)
    }

    void evaluate() {
        try {
            result = new Expression(expression).calculate()
            if (result.equalsIgnoreCase('NaN')) {
                result = 'bad expression'
            }
        } catch (Exception e) {
            result = "bad expression, ${e.getMessage()}"
        }
    }

    void render(Graphics2D g) {
        g.setColor(Color.white)
        g.drawString(expression, 15, 30)
        g.drawString(result, 15, 50)
    }
}
