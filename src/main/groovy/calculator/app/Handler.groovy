package calculator.app

import calculator.math.Calculator
import calculator.ui.Display
import calculator.ui.Element
import calculator.ui.InputHandler

import java.awt.*
import java.util.List
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Handler {

    private static Handler handler
    static final ExecutorService singleThread = Executors.newSingleThreadExecutor()
    static final ExecutorService executorService = Executors.newFixedThreadPool(2)

    boolean running = true
    Calculator calculator
    InputHandler inputHandler
    Display display
    List<Element> elements

    static {
        handler = new Handler()
    }

    static Handler get() {
        return handler
    }

    private Handler() {
        handler = this
        inputHandler = new InputHandler()
        calculator = new Calculator()
        display = new Display()
        elements = [calculator]

        inputHandler.calculator = calculator
        inputHandler.graph = calculator.graph
    }

    void start() {
        while (running) {
            update()
            render()
            sleep(5)
        }
    }

    void update() {
        elements.each { it.update() }
    }

    void render() {
        Graphics2D g = display.render()
        elements.each { it.render(g) }
        display.show()
    }
}
