package pathFinders;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Collections;
import Main.*;

// sourced and modified from https://rosettacode.org/wiki/A*_search_algorithm#Java

public class AStar {
    private CopyOnWriteArrayList <Node> open;
    private CopyOnWriteArrayList <Node> closed;
	private CopyOnWriteArrayList <Node> path;
    private int[][] maze;
    private Node now;
    private int xstart;
    private int ystart;
    private int xend, yend;
    private boolean diag;
    private SortScreen theScreen;
	private long delay;
	private CopyOnWriteArrayList <Node> closedPaint;
	private CopyOnWriteArrayList<Node> openPaint;
	private long endDelay;
	private int heuristicChoice;
 

 
    public AStar(int[][] maze, int xstart, int ystart, boolean diag, CopyOnWriteArrayList <Node> open, CopyOnWriteArrayList <Node> closed, CopyOnWriteArrayList <Node> path, CopyOnWriteArrayList<Node> closedPaint2, CopyOnWriteArrayList<Node> openPaint, SortScreen theScreen) {
        this.open = new CopyOnWriteArrayList <>();
        this.openPaint = new CopyOnWriteArrayList <>();
        this.closed = new CopyOnWriteArrayList <>();
        this.closedPaint = new CopyOnWriteArrayList <>();
        this.path = new CopyOnWriteArrayList <>();
        this.maze = maze;
        this.now = new Node(null, xstart, ystart, 0, 0);
        this.xstart = xstart;
        this.ystart = ystart;
        this.diag = diag;
        this.open = open;
        this.closed = closed;
        this.path = path;
        this.theScreen = theScreen;
    }
    /*
    ** Finds path to xend/yend or returns null
    **
    ** @param (int) xend coordinates of the target position
    ** @param (int) yend
    ** @return (List<Node> | null) the path
    */
    public CopyOnWriteArrayList <Node> findPathTo(int xend, int yend) {
        this.xend = xend;
        this.yend = yend;
        this.closed.add(this.now);
        if(maze[this.now.y][this.now.x]!=100) {
        	this.closedPaint.add(this.now);
	        theScreen.repaint();
			 try {
				Thread.sleep(delay);
			 } catch (InterruptedException e) {
				e.printStackTrace();
			 } 
        }
        addNeigborsToOpenList();
        while (this.now.x != this.xend || this.now.y != this.yend) {
            if (this.open.isEmpty()) { // Nothing to examine
                return null;
            }
            this.now = this.open.get(0); // get first node (lowest f score)
            this.open.remove(0); // remove it
            this.closed.add(this.now); // and add to the closed
            if(maze[this.now.y][this.now.x]!=100) {
            	this.closedPaint.add(this.now);
//            	System.out.print(this.now.x + " " + this.now.y + " ");
//            	if(closedPaint.size()>=3)
//            	System.out.println(closedPaint.get(closedPaint.size()-1).x + " " + closedPaint.get(closedPaint.size()-1).y + " " + closedPaint.get(closedPaint.size()-2).x + " " + closedPaint.get(closedPaint.size()-2).y + " " + closedPaint.get(closedPaint.size()-3).x + " " + closedPaint.get(closedPaint.size()-3).y);
    	        theScreen.repaint();
    			 try {
    				Thread.sleep(delay); 
    			 } catch (InterruptedException e) {
    				e.printStackTrace();
    			 } 
            } 
            addNeigborsToOpenList();
            
        }
        this.path.add(0, this.now);
        while (this.now.x != this.xstart || this.now.y != this.ystart) {
            this.now = this.now.parent;
            this.path.add(0, this.now);
            
            theScreen.repaint();
				 try {
					Thread.sleep(endDelay);
				 } catch (InterruptedException e) {
					e.printStackTrace();
				 } 
				 
        }
        return this.path;
    }
    public CopyOnWriteArrayList<Node> getOpen() {
		return open;
	}
	public void setOpen(CopyOnWriteArrayList<Node> open) {
		this.open = open;
	}
	public CopyOnWriteArrayList<Node> getClosed() {
		return closed;
	}
	public void setClosed(CopyOnWriteArrayList<Node> closed) {
		this.closed = closed;
	}
	public CopyOnWriteArrayList<Node> getPath() {
		return path;
	}
	public void setPath(CopyOnWriteArrayList<Node> path) {
		this.path = path;
	}
	public Node getNow() {
		return now;
	}
	public void setNow(Node now) {
		this.now = now;
	}
	public int getXend() {
		return xend;
	}
	public void setXend(int xend) {
		this.xend = xend;
	}
	public int getYend() {
		return yend;
	}
	public void setYend(int yend) {
		this.yend = yend;
	}
	public SortScreen getTheScreen() {
		return theScreen;
	}
	public void setTheScreen(SortScreen theScreen) {
		this.theScreen = theScreen;
	}
	public int[][] getMaze() {
		return maze;
	}
	public int getXstart() {
		return xstart;
	}
	public int getYstart() {
		return ystart;
	}
	public boolean isDiag() {
		return diag;
	}
	/*
    ** Looks in a given List<> for a node
    **
    ** @return (bool) NeightborInListFound
    */
    private static boolean findNeighborInList(CopyOnWriteArrayList<Node> open2, Node node) {
        return open2.stream().anyMatch((n) -> (n.x == node.x && n.y == node.y));
    }
    /*
    ** Calulate distance between this.now and xend/yend
    **
    ** @return (int) distance
    */
    private double distance(int dx, int dy) {
        if (this.diag) { // if diagonal movement is alloweed
            return Math.hypot(this.now.x + dx - this.xend, this.now.y + dy - this.yend); // return hypothenuse
        } else {
            return Math.abs(this.now.x + dx - this.xend) + Math.abs(this.now.y + dy - this.yend); // else return "Manhattan distance"
        }
    }
    private void addNeigborsToOpenList() {
        Node node;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (!this.diag && x != 0 && y != 0) {
                    continue; // skip if diagonal movement is not allowed
                }
                node = new Node(this.now, this.now.x + x, this.now.y + y, this.now.g, this.distance(x, y));
                if ((x != 0 || y != 0) // not this.now
                    && this.now.x + x >= 0 && this.now.x + x < this.maze[0].length // check maze boundaries
                    && this.now.y + y >= 0 && this.now.y + y < this.maze.length
                    && this.maze[this.now.y + y][this.now.x + x] != -1 // check if square is walkable
                    && !findNeighborInList(this.open, node) && !findNeighborInList(this.closed, node)) { // if not already done
                        node.g = node.parent.g + 1.; // Horizontal/vertical cost = 1.0
                        node.g += maze[this.now.y + y][this.now.x + x]; // add movement cost for this square
 
                        // diagonal cost = sqrt(hor_cost² + vert_cost²)
                        // in this example the cost would be 12.2 instead of 11
                        /*
                        if (diag && x != 0 && y != 0) {
                            node.g += .4;	// Diagonal movement cost = 1.4
                        }
                        */
                        this.open.add(node);
                        if(maze[this.now.y][this.now.x]!=100) {
//                        	this.openPaint.add(node);
//                        	theScreen.repaint();
//		       				try {
//		       					Thread.sleep(theScreen.delay);
//		       				} catch (InterruptedException e) {
//		       					e.printStackTrace();
//		       				} 
                        }
                }
            }
        }
        Collections.sort(this.open);
    }
 

	public void begin(CopyOnWriteArrayList <Node> thePath, CopyOnWriteArrayList <Node> theOpen, CopyOnWriteArrayList <Node> theClosed, CopyOnWriteArrayList<Node> theClosedPaint, CopyOnWriteArrayList<Node> theOpenPaint) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.path = thePath;
		this.open = theOpen;
		this.closed = theClosed;
		this.closedPaint = theClosedPaint;
		this.openPaint = theOpenPaint;
		theScreen.delay = (long)250/maze.length;
		delay = (long)250/maze.length;
		endDelay = (long)1000/maze.length;
		System.out.println("DELAY: "+delay);
		System.out.println("END DELAY: "+endDelay);
		path = findPathTo(theScreen.getEndY(),theScreen.getEndX());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public long getDelay() {
		return delay;
	}
	public void setDelay(long delay) {
		this.delay = delay;
	}
	public CopyOnWriteArrayList<Node> getClosedPaint() {
		return closedPaint;
	}
	public void setClosedPaint(CopyOnWriteArrayList<Node> closedPaint) {
		this.closedPaint = closedPaint;
	}
    public void setMaze(int[][] maze) {
		this.maze = maze;
	}
	public void setXstart(int xstart) {
		this.xstart = xstart;
	}
	public void setYstart(int ystart) {
		this.ystart = ystart;
	}
	public void setDiag(boolean diag) {
		this.diag = diag;
	}
}
