package calculator.gui

import calculator.Handler

import javax.swing.JFrame
import java.awt.Canvas
import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.font.TextAttribute
import java.awt.image.BufferStrategy

class Display {

    static final int WIDTH = 800, HEIGHT = 600

    JFrame frame
    Canvas canvas
    Renderer renderer

    Display() {
        Handler handler = Handler.handler

        Dimension dimension = new Dimension(WIDTH, HEIGHT)
        canvas = new Canvas()
        canvas.setSize(dimension)
        canvas.setFocusable(false)
        canvas.addMouseListener(handler.inputMap)
        canvas.addMouseMotionListener(handler.inputMap)

        frame = new JFrame()
        frame.addKeyListener(handler.inputMap)
        frame.setSize(dimension)
        frame.add(canvas)
        frame.setResizable(false)
        frame.setUndecorated(true)
        frame.setLocation(200, 100)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        frame.pack()
        frame.setVisible(true)
        canvas.createBufferStrategy(3)

        renderer = new Renderer()
    }

    Graphics2D render() {
        return renderer.render()
    }

    void show() {
        renderer.show()
    }

    private class Renderer {

        Graphics2D g
        BufferStrategy bufferStrategy

        private Renderer() {}

        Graphics2D render() {
            bufferStrategy = canvas.getBufferStrategy()
            g = (Graphics2D) bufferStrategy.getDrawGraphics()

            g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight())
            g.setColor(Color.BLACK)
            g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight())

            Map fontAttributes = [:]
            fontAttributes[TextAttribute.FAMILY] = 'Times New Roman'
            fontAttributes[TextAttribute.SIZE] = 24
            fontAttributes[TextAttribute.WEIGHT, TextAttribute.WEIGHT_REGULAR]

            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP)
            g.setFont(new Font(fontAttributes))
            return g
        }

        void show() {
            g.setColor(Color.white)
            g.drawRect(0, 0, canvas.getWidth()-1, canvas.getHeight()-1)
            bufferStrategy.show()
            g.dispose()
        }
    }
}
