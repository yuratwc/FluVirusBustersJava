package bi18027.prog.virus;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class SceneMainGame extends Scene {
    private final ArrayList<Entity> entities;
    private final Virus[] virus;
    private final Random rand;
    private final Mask mask;
    private final Entity humanOver;
    private final Font font;

    private boolean isGameOver;
    private int countVirus;
    private int countReflect;

    public SceneMainGame() {
        entities = new ArrayList<>();
        virus = new Virus[80];
        rand = new Random();

        Entity human = new Entity().setImage("image/human.png");
        human.setX(Scene.WIDTH - 259);
        human.setY(Scene.HEIGHT - 383);
        humanOver = new Entity().setImage("image/human-over.png");
        humanOver.setX(Scene.WIDTH - 259);
        humanOver.setY(Scene.HEIGHT - 383);

        entities.add(human);

        mask = new Mask();
        entities.add(mask);

        font = new Font(Font.SANS_SERIF, Font.PLAIN, 28);

        try {
            URL url = new File("musics/WitchcraftTime.wav").toURI().toURL();
            AudioClip ac = Applet.newAudioClip(url);
            ac.play();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update() {

        if(countVirus >= 20) {
            isGameOver = true;
        }

        if(isGameOver)
            return;

        mask.setY(getMouseY());
        for(Entity e : entities) {
            e.update();
        }
        for(int i = 0; i < virus.length; i++) {
            if(virus[i] == null)
                continue;
            if(virus[i].isDead()) {
                if(virus[i].isAbsorbed()) {
                    countVirus++;
                }
                virus[i] = null;
                continue;
            }

            if(mask.intersects(virus[i].getX(), virus[i].getY())) {
                boolean r = virus[i].isReflected();
                virus[i].reflect();
                if(r != virus[i].isReflected()) {
                    countReflect++;
                }
            }

            virus[i].update();
        }


        if(rand.nextInt(15) == 0) {
            Virus v = new Virus(rand.nextInt(500) + 50);
            float vx = (float)rand.nextDouble() * 3 + 1f;
                float vy = (float)rand.nextDouble() - 0.5f;
                v.setVx(vx);
                v.setVy(vy);
                v.setSpeed(rand.nextInt(9) + 1 );
                addVirus(v);
        }


    }

    @Override
    public void mouseClicked(int button) {
        if(isGameOver) {
            getParent().changeScene(new SceneTitle());
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setFont(font);
        //g.drawString("Mask", 450, getMouseY());

        for(Entity e : entities) {
            e.draw(g);
        }

        for(int i = 0; i < virus.length; i++) {
            if(virus[i] == null)
                continue;
            virus[i].draw(g);
        }
        humanOver.draw(g);

        drawMeter(g);

        if(isGameOver) {
            g.setColor(Color.black);
            String strGmo = "Game Over";
            Rectangle2D r2d = g.getFontMetrics(font).getStringBounds(strGmo, g);
            g.drawString(strGmo, (int)(WIDTH-r2d.getWidth()) / 2, HEIGHT / 2);

            strGmo = "Score:" + (countReflect * 100);
            r2d = g.getFontMetrics(font).getStringBounds(strGmo, g);
            g.drawString(strGmo, (int)(WIDTH-r2d.getWidth()) / 2, HEIGHT / 2 + 50);
        }

    }

    private void drawMeter(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(20, 5, WIDTH - 40, 30);
        g.setColor(Color.red);
        g.fillRect(25, 8, (int)((WIDTH - 50) * (1f - countVirus / 20f)), 24);
        g.setColor(Color.white);
        final String meneki = "免疫力";
        Rectangle2D r2d = g.getFontMetrics(font).getStringBounds(meneki, g);
        g.drawString(meneki, (int)(WIDTH - r2d.getWidth()) / 2, 30);
    }

    private void addVirus(Virus v) {
        for(int i = 0; i < virus.length; i++) {
            if(virus[i] != null)
                continue;
            virus[i] = v;
            break;
        }
    }
}
