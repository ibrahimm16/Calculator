package calculator.gui

import calculator.Handler
import calculator.gui.math.Calculator

import java.awt.event.*

class InputHandler implements KeyListener, MouseListener, MouseMotionListener {

    Calculator calculator = Handler.handler.calculator

    void keyTyped(KeyEvent e) {
        char key = Character.toLowerCase(e.getKeyChar())

        // use 'C' to close it
        if (key == KeyEvent.VK_C) {
            System.exit(0)
        }

        if (key == KeyEvent.VK_BACK_SPACE) {
            calculator.keyDeleted()
        } else if (key == KeyEvent.VK_ENTER) {
            calculator.evaluate()
        } else {
            calculator.keyTyped(key)
        }
    }

    void keyPressed(KeyEvent e) {}

    void keyReleased(KeyEvent e) {}

    void mouseClicked(MouseEvent e) {}

    void mouseMoved(MouseEvent e) {}

    void mousePressed(MouseEvent e) {}

    void mouseReleased(MouseEvent e) {}

    void mouseDragged(MouseEvent e) {}

    void mouseEntered(MouseEvent e) {}

    void mouseExited(MouseEvent e) {}
}
