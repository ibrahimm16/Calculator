package calculator.math


import calculator.ui.Element
import org.mariuszgromada.math.mxparser.Expression

import java.awt.*
import java.awt.event.KeyEvent

class Calculator extends Element {

    String expression
    String result
    Graph graph

    Calculator() {
        expression = ''
        result = 0
        graph = new Graph()

        elements.add(graph)
    }

    void keyTyped(char c) {
        if (c == KeyEvent.VK_BACK_SPACE) {
            expression = expression.isEmpty() ? '' : expression.substring(0, expression.length() - 1)
        } else if (c == KeyEvent.VK_ENTER) {
            evaluate()
        } else {
            expression += c
        }
    }

    void update() {
        super.update()
    }

    void evaluate() {
        if (expression.contains('x')) {
            graph.expression = expression
        } else {
            try {
                result = new Expression(expression).calculate()
                if (result.equalsIgnoreCase('NaN')) {
                    result = 'bad expression'
                }
            } catch (Exception e) {
                result = "bad expression, ${e.getMessage()}"
            }
        }
    }

    void render(Graphics2D g) {
        g.setColor(Color.white)
        g.drawString(expression, 15, 30)
        g.drawString(result, 15, 50)

        elements.each { it.render(g) }
    }
}
