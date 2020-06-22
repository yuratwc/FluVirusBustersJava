package bi18027.prog.virus;

import java.awt.*;

public class Virus extends Entity {
    private float vx;
    private float vy;
    private float speed;
    private boolean reflected;

    private boolean isCalced;
    private boolean absorbed;
    public Virus() {
        setImage("image/virus.png");
        setX(-17);
        speed = 8f;
        vx = 1f;
    }

    public Virus(int y) {
        this();
        setY(y);
    }

    public boolean isAbsorbed() {
        return absorbed;
    }

    @Override
    public void update() {
        if(isDead())
            return;

        setX((int)(getX() + vx * speed));
        setY((int)(getY() + vy * speed));

        if(!isCalced && getX() >= 450 && !reflected) { //reflect
            //vx = -vx;
            int x = getX();
            int y = getY();
            double rad = Math.atan2(340 - y, 660 - x);
            float speed2 = vx * vx + vy * vy;

            vy = (float)Math.sin(rad) * speed2;
            vx = (float)Math.cos(rad) * speed2;
            if(speed < 2) {
                speed = 2.1f;
            }
            isCalced = true;
        }

        if(getX() <= -40 || getX() >= 840 || getY() <= -30 || getY() >= 630) {
            absorbed = getX() >= 840;
            kill();
        }
    }

    public void reflect() {
        if(reflected)
            return;
        reflected = true;
        vx = -vx;
    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), getX(), getY(), 32, 32, null);
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public boolean isReflected() {
        return reflected;
    }
}
