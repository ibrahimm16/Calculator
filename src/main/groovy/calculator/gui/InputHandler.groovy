package calculator.gui


import calculator.math.Calculator

import java.awt.event.*

class InputHandler implements KeyListener, MouseListener, MouseMotionListener {

    Calculator calculator

    int x = 0, y = 0

    void keyTyped(KeyEvent e) {
        Character key = Character.toLowerCase(e.getKeyChar())

        // use 'C' to close it
        if (key == 'c' as char) {
            System.exit(0)
        }

        if (key.charValue() == KeyEvent.VK_BACK_SPACE) {
            calculator.keyDeleted()
        } else if (key.charValue() == KeyEvent.VK_ENTER) {
            calculator.evaluate()
        } else if (key != null) {
            calculator.keyTyped(key)
        }
    }

    void keyPressed(KeyEvent e) {}

    void keyReleased(KeyEvent e) {}

    void mouseClicked(MouseEvent e) {}

    void mouseMoved(MouseEvent e) {
        x = e.x
        y = e.y
    }

    void mousePressed(MouseEvent e) {}

    void mouseReleased(MouseEvent e) {}

    void mouseDragged(MouseEvent e) {}

    void mouseEntered(MouseEvent e) {}

    void mouseExited(MouseEvent e) {}
}
