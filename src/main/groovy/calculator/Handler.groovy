package calculator

import calculator.gui.Display
import calculator.gui.Displayable
import calculator.gui.InputHandler
import calculator.math.Calculator

import java.awt.*
import java.util.List

class Handler {

    static Handler handler

    boolean running = true
    Calculator calculator
    InputHandler inputHandler
    Display display

    List<Displayable> displayable = []

    Handler() {
        handler = this
        inputHandler = new InputHandler()
        calculator = new Calculator()
        display = new Display()
        displayable = [calculator]

        inputHandler.calculator = calculator
    }

    void start() {
        while (running) {
            update()
            render()
            sleep(30)
        }
    }

    void update() {
        calculator.update()
    }

    void render() {
        Graphics2D g = display.render()

        displayable.each { it.render(g) }

        display.show()
    }
}
