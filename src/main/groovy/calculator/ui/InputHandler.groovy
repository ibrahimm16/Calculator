package calculator.ui

import calculator.math.Calculator
import calculator.math.Graph

import java.awt.event.*

class InputHandler implements KeyListener, MouseListener, MouseMotionListener {

    Calculator calculator
    Graph graph
    int x = 0, y = 0

    void keyTyped(KeyEvent e) {
        Character key = Character.toLowerCase(e.getKeyChar())

        // use '?' to close it
        if (e.getKeyChar() == '?' as char) {
            System.exit(0)
        }

        calculator.keyTyped(key)
        graph.keyTyped(key)
    }

    void mouseMoved(MouseEvent e) {
        x = e.x
        y = e.y
    }

    void keyPressed(KeyEvent e) {}

    void keyReleased(KeyEvent e) {}

    void mouseClicked(MouseEvent e) {}

    void mousePressed(MouseEvent e) {}

    void mouseReleased(MouseEvent e) {}

    void mouseDragged(MouseEvent e) {}

    void mouseEntered(MouseEvent e) {}

    void mouseExited(MouseEvent e) {}
}
