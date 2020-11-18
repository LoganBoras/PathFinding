package Main;

//import pathFinders.AStar.Node;

// Node class for convienience
 public class Node implements Comparable {
    public Node parent;
    public int x, y;
    public double g;
    public double h;
    
    public Node(Node parent, int xpos, int ypos, double g, double h) {
        this.parent = parent;
        this.x = xpos;
        this.y = ypos;
        this.g = g;
        this.h = h;
   }
    
   // Compare by f value (g + h)
   @Override
   public int compareTo(Object o) {
       Node that = (Node) o;
       return (int)((this.g + this.h) - (that.g + that.h));
   }
}
