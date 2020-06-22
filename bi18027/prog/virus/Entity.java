package bi18027.prog.virus;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Entity {
    private int x;
    private int y;

    private boolean isDead;

    private BufferedImage image;

    public Entity() {

    }

    public void update() {

    }

    public void draw(Graphics g) {
        if(image == null)
            return;
        g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
    }

    public Entity setImage(String path) {
        try{
            File f = new File(path);
            if(!f.exists()) {
                System.out.println("FileNotFound:" + f.getAbsolutePath());
                return this;
            }
            image = ImageIO.read(f);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public void kill() {
        isDead = true;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    protected BufferedImage getImage() {
        return image;
    }

    public boolean isDead() {
        return isDead;
    }

}
