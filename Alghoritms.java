import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Alghoritms{
    public static void bfs(Maze maze, Node start, boolean colorNodes){
        List<Node> queue= new ArrayList<>();
        start.setVisited(true);
        queue.add(start);
        while(!queue.isEmpty()){
            Node u=queue.getFirst();
            queue.removeFirst();
            for(int i=0;i<u.getAdjList().size();i++) {
                Node n=u.getAdjList().get(i);
                if (!n.isVisited()) {
                    n.setParent(u);
                    n.setVisited(true);
                    if(colorNodes) {
                        n.color=4;
                    }
                    queue.add(n);
                }
            }
        }
    }

    public static void shortestPath(Maze maze, Node start, Node finish){
        bfs(maze,start,false);
        Node curr=finish;
        if(finish.isVisited()) {
            while (curr.getParent() != null) {
                curr.getParent().color = 5;
                curr = curr.getParent();
            }
        }
    }
}
