package calculator.math

import calculator.gui.Displayable
import org.mariuszgromada.math.mxparser.Expression

import java.awt.Color
import java.awt.Graphics2D

class Calculator implements Displayable {

    String expression
    String result
    Graph graph
    boolean shouldEval = false

    Calculator() {
        expression = ''
        result = 0
        graph = new Graph()

        displayables.add(graph)
    }

    void keyTyped(char c) {
        expression += c
    }

    void keyDeleted() {
        expression = expression.isEmpty() ? '' : expression.substring(0, expression.length() - 1)
    }

    void update() {
        if (shouldEval) {
            evaluate()
            shouldEval = false
        }
        graph.update()
    }

    void evaluate() {
        if (expression.contains('x')) {
            graph.expression = expression
            graph.shouldEval = true
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

        displayables.each { it.render(g) }
    }
}
