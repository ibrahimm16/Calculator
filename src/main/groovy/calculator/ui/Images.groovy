package calculator.ui

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

abstract class Images {

    private static final Map<String, BufferedImage> images = [:]

    static {
        try {
            BufferedImage graph = ImageIO.read(new File('src/main/resources/images/graph.png'))
            images['graph'] = graph
        } catch (Exception ignored) {
        }
    }

    static BufferedImage get(String key) {
        return images[key]
    }
}
