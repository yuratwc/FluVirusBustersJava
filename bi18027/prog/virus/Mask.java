package bi18027.prog.virus;

import java.awt.*;

public class Mask extends Entity{

    private final int height;

    public Mask() {
        height = 110;
        setImage("image/mask.png");
        setX(400);
    }

    @Override
    public void update() {

    }

    public boolean intersects(int x, int y) {
        if(getX() <= x && getX() + 64 >= x && getY() - height / 2 <= y && getY() + height / 2 >= y) {
            return true;
        }
        return false;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), getX(), getY() - height * 3/4, getImage().getWidth(), getImage().getHeight(), null);
    }
}
