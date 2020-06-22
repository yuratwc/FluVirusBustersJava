package bi18027.prog.virus;

import java.awt.*;

public abstract class Scene {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private GameFrame parent;

    private int mouseX;
    private int mouseY;

    abstract void update();
    abstract void draw(Graphics g);

    public void mouseClicked(int button) {}

    public int getMouseX() {
        return mouseX;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public GameFrame getParent() {
        return parent;
    }

    public void setParent(GameFrame parent) {
        this.parent = parent;
    }
}
