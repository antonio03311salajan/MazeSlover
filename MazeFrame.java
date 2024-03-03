import javax.swing.*;
import java.awt.*;

public class MazeFrame{
    private static final int WIDTH=720;
    private static final int HEIGHT=360;
    private static final String title="Labirint";
    public MazeFrame(){
        JFrame frame=new JFrame();
        frame.setSize(WIDTH,HEIGHT);
        frame.setTitle(title);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MazePanel mazePanel=new MazePanel();
        frame.add(mazePanel);
        frame.pack();
        frame.getContentPane();
        frame.setBackground(Color.GRAY);
        mazePanel.requestFocus();
        frame.setVisible(true);
    }

}
