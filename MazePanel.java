import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.Key;
import java.util.concurrent.TimeUnit;

public class MazePanel extends JPanel implements ActionListener, MouseListener, KeyListener {
    private static final int HEIGHT = 360;
    private static final int WIDTH = 720;
    Image nodeImgWall;
    Image nodeImgFree;
    Image nodeImgStart;
    Image nodeImgFinish;
    Image bfsImg;
    Image spImg;
    int finishx = 1;
    int finishy = 1;
    boolean finishPainted = false;
    boolean startPainted = false;
    boolean repaintMaze = true;
    Maze maze = new Maze();
    boolean info=false;

    int mouseXfinish = -1;
    int mouseXstart = -1;
    int mouseYfinish = -1;
    int mouseYstart = -1;

    MazePanel() {
        setFocusable(true);
        addMouseListener(this);
        addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(120, 120, 120));
        nodeImgWall = new ImageIcon(getClass().getResource("./BlackNode.png")).getImage();
        nodeImgFree = new ImageIcon(getClass().getResource("./WhiteNode.png")).getImage();
        nodeImgStart = new ImageIcon(getClass().getResource("./imgNodeStart.png")).getImage();
        nodeImgFinish = new ImageIcon(getClass().getResource("./NodeFinish.png")).getImage();
        bfsImg = new ImageIcon(getClass().getResource("./BfsNode.png")).getImage();
        spImg = new ImageIcon(getClass().getResource("./SpImg.png")).getImage();
        maze.NodesToArr();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        g.drawString("Press I for info.",630,20);
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 18; j++) {
                if (maze.getNode(i, j).getColor() == 1) {
                    g.drawImage(nodeImgWall, 30 * (j + 1), 30 * (i + 1), 30, 30, null);
                }
                if (maze.getNode(i, j).getColor() == 0) {
                    g.drawImage(nodeImgFree, 30 * (j + 1), 30 * (i + 1), 30, 30, null);
                }
                if (maze.getNode(i, j).getColor() == 4) {
                    g.drawImage(bfsImg, 30 * (j + 1), 30 * (i + 1), 30, 30, null);
                }
                if (maze.getNode(i, j).getColor() == 5) {
                    g.drawImage(spImg, 30 * (j + 1), 30 * (i + 1), 30, 30, null);
                }
            }
        if (finishPainted && mouseXfinish < 18 && mouseYfinish < 10 && mouseXfinish >= 0 && mouseYfinish >= 0) {
//            maze.setFinishArr(mouseYfinish,mouseXfinish);
            g.drawImage(nodeImgFinish, 30 * (mouseXfinish + 1), 30 * (mouseYfinish + 1), 30, 30, null);
        }
        if (startPainted && mouseXstart < 18 && mouseYstart < 10 && mouseYstart >= 0 && mouseXstart >= 0) {
//            maze.setStartArr(mouseYstart,mouseXstart);
            g.drawImage(nodeImgStart, 30 * (mouseXstart + 1), 30 * (mouseYstart + 1), 30, 30, null);
        }
        if(info){
            g.drawString("B-BFS",580,120);
            g.drawString("S-Shortest Path",580,140);
            g.drawString("R-Restart",580,160);
            g.drawString("G-Generateb a new maze",580,180);
        }
        if(repaintMaze && !startPainted && !finishPainted){
            repaintMaze=false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (mouseYfinish == -1 || mouseXfinish == -1 || mouseXstart == -1 || mouseYstart == -1) {
            if (e.getButton() == MouseEvent.BUTTON3 && !finishPainted) {
                mouseXfinish = (e.getX()) / 30 - 1;
                mouseYfinish = (e.getY()) / 30 - 1;
                if (mouseXfinish < 18 && mouseYfinish < 10 && mouseXfinish >= 0 && mouseYfinish >= 0 && maze.getArr()[mouseYfinish][mouseXfinish] != 1)
                    finishPainted = true;
                else {
                    mouseXfinish = -1;
                    mouseYfinish = -1;
                }
            }
            if (e.getButton() == MouseEvent.BUTTON1 && !startPainted) {
                mouseXstart = (e.getX()) / 30 - 1;
                mouseYstart = (e.getY()) / 30 - 1;
                if (mouseXstart < 18 && mouseYstart < 10 && mouseYstart >= 0 && mouseXstart >= 0 && maze.getArr()[mouseYstart][mouseXstart] != 1)
                    startPainted = true;
                else {
                    mouseYstart = -1;
                    mouseXstart = -1;
                }
            }
            if (startPainted || finishPainted)
                repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_B && startPainted && finishPainted) {
            for (int i = 0; i < 10; i++)
                for (int j = 0; j < 18; j++)
                    maze.getNode(i, j).getNeighb(maze.getMaze(), maze.getArr());
            Alghoritms.bfs(maze, maze.getNode(mouseYstart, mouseXstart), true);
        }
        if (e.getKeyCode() == KeyEvent.VK_S && startPainted && finishPainted) {
            maze.NodesToArr();
            for (int i = 0; i < 10; i++)
                for (int j = 0; j < 18; j++)
                    maze.getNode(i, j).getNeighb(maze.getMaze(), maze.getArr());
            Alghoritms.shortestPath(maze, maze.getNode(mouseYstart, mouseXstart), maze.getNode(mouseYfinish, mouseXfinish));
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 18; j++) {
                    maze.getNode(i, j).setVisited(false);
                    maze.getNode(i, j).setParent(null);
                    maze.getNode(i, j).color = maze.getArr()[i][j];
                }
            }
            mouseXstart = -1;
            mouseXfinish = -1;
            mouseYstart = -1;
            mouseYfinish = -1;
            finishPainted = false;
            startPainted = false;
        }
        repaint();
        if(e.getKeyCode() == KeyEvent.VK_I){
            info=!info;
        }
        if(e.getKeyCode() == KeyEvent.VK_G){
            maze=new Maze();
            maze.NodesToArr();
            startPainted=false;
            finishPainted=false;
            mouseXstart=-1;
            mouseYstart=-1;
            mouseYfinish=-1;
            mouseXfinish=-1;
            repaintMaze=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
