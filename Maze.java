import java.awt.*;

public class Maze {
    private Node[][] maze;
    private int[][] arr;
    public Maze(){
        this.maze=new Node[10][18];
        for(int i=0;i<10;i++){
            for(int j=0;j<18;j++){
                this.maze[i][j]=new Node(i,j,false);
            }
        }
        for(int i=0;i<10;i++){
            for(int j=0;j<18;j++) {
                float colorDec=(float)Math.random();
                if(colorDec<=0.3)
                    this.maze[i][j].color=1;
                else
                    this.maze[i][j].color=0;
            }
        }
        this.arr=new int[10][18];
    }
    public Node getNode(int i,int j){
        return this.maze[i][j];
    }
    public void NodesToArr(){
        for(int i=0;i<10;i++){
            for(int j=0;j<18;j++){
                this.arr[i][j]=this.maze[i][j].color;
            }
        }
    }

    public int[][] getArr(){
        return this.arr;
    }

    public Node[][] getMaze() {
        return maze;
    }
}
