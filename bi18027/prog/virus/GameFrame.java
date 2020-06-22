package bi18027.prog.virus;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame extends Panel implements Runnable{
    private final Thread updateThread;
    private Scene scene;

    private Image depthBuffer;

    public GameFrame() {
        //setSize(Scene.WIDTH, Scene.HEIGHT);
        Dimension d = new Dimension(Scene.WIDTH, Scene.HEIGHT);
        setMaximumSize(d);
        setPreferredSize(d);
        setMinimumSize(d);

        setBackground(Color.WHITE);

        changeScene(new SceneTitle());

        updateThread = new Thread(this);
        updateThread.setDaemon(true);
        updateThread.start();


        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                scene.setMouseX(e.getX());
                scene.setMouseY(e.getY());
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //scene.setMouseX(e.getX());
                //scene.setMouseY(e.getY());
                scene.mouseClicked(e.getButton());
            }
        });

    }

    public void changeScene(Scene nextScene) {
        nextScene.setParent(this);
        scene = nextScene;
    }

    public void gameUpdate() {
        scene.update();
        repaint(0,0, Scene.WIDTH, Scene.HEIGHT);
    }

    @Override
    public void paint(Graphics g){
        super.paintComponents(g);
        if(depthBuffer == null) {
            depthBuffer = createImage(Scene.WIDTH, Scene.HEIGHT);
        }
        Graphics gi = depthBuffer.getGraphics();
        gi.clearRect(0, 0, Scene.WIDTH, Scene.HEIGHT);
        scene.draw(gi);

        g.drawImage(depthBuffer, 0, 0, null);
    }

    @Override
    public void run() {
        while(true) {
            gameUpdate();
            try {
                Thread.sleep(60 );
            } catch (Exception e) {}
        }
    }
}
