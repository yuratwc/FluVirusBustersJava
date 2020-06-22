package bi18027.prog.virus;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameMain extends Frame {
    private final GameFrame frame;
    public GameMain() {
        setLayout(new FlowLayout());
        setTitle("Virus Basters");
        setMinimumSize(new Dimension(800 + 100, 600 + 80));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        } );
        frame = new GameFrame();
        //frame.setBounds(0, 0, 800+200, 600+50);
        add(frame);
        setBackground(Color.pink);
    }

    public static void main(String[] args) {
        GameMain gm = new GameMain();
        gm.setVisible(true);
    }
}
