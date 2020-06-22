package bi18027.prog.virus;

import java.awt.*;

public class SceneTitle extends Scene {
    private final Entity titleScreen;

    public SceneTitle() {
        titleScreen = new Entity().setImage("image/title.png");
    }

    @Override
    void update() {

    }

    @Override
    void draw(Graphics g) {
        titleScreen.draw(g);
    }

    @Override
    public void mouseClicked(int button) {
        getParent().changeScene(new SceneMainGame());
    }
}
