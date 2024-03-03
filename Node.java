import java.awt.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
public class Node {
    private boolean visited;
    private Node parent;
    private int x;
    private int y;
    protected int color;
    //0 free space
    //1 wall
    //2 start
    //3 red
    //4 purple
    //5 yellow
    private List<Node> adjList;
    public Node(int i,int j,boolean visited){
        this.parent=null;
        this.visited=visited;
        this.x=j;
        this.y=i;
        this.color=0;
        this.adjList=new ArrayList<>();
    }

    public int getColor() {
        return this.color;
    }

    public void addNeighb(Node node){
        this.adjList.addFirst(node);
    }

    public void getNeighb(Node[][] nodes,int[][] arr){
        int i= this.y;
        int j= this.x;
        if(arr[i][j]!=1){
            if (i - 1 >= 0 && arr[i - 1][j] != 1) {
                this.addNeighb(nodes[i - 1][j]);
            }
            if (i + 1 < 10 && arr[i + 1][j] != 1) {
                this.addNeighb(nodes[i + 1][j]);
            }
            if (j - 1 >= 0 && arr[i][j - 1] != 1) {
                this.addNeighb(nodes[i][j - 1]);
            }
            if (j + 1 < 18 && arr[i][j + 1] != 1) {
                this.addNeighb(nodes[i][j + 1]);
            }
        }
        else this.adjList.clear();
    }

    public List<Node> getAdjList(){
        return this.adjList;
    }

    public Node getParent(){
        return parent;
    }

    public void setParent(Node node){
        this.parent=node;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public int getX(){
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
