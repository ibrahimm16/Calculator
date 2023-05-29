package calculator.gui

import java.awt.Graphics2D

interface Displayable {

    List<Displayable> displayables = []

    void render(Graphics2D g)
}
