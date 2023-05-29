package calculator.ui

import calculator.app.Handler

import java.awt.Graphics2D

abstract class Element {

    Handler handler = Handler.get()
    List<Element> elements = []

    void update() {
        elements.each { it.update() }
    }

    void render(Graphics2D g) {
        elements.each { it.render(g) }
    }
}
