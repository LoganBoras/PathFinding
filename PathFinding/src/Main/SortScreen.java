package Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

import Audio.MidiSoundPlayer;

public class SortScreen extends JPanel{
	private MainApp theApp;
	private int[][] maze;

	public int[] indexArray;
//	private int screenWidth = 1920;
//	private int screenHeight = 1080;
//	private int size;
	private int totalBars;
	public long delay = 1;
	private int decide;
	private int choice;
	Random rd;
	private String theName;
	private boolean green;
	private int visualizationChoice;
	public int mod = 1;
	public int mod2;
	private int cols;
	private int rows;
	int startX = 1;
	int startY = 1;
	private int endX;
	private int endY;
	BufferedImage backGround;
	BufferedImage foreGround;
	private MidiSoundPlayer player;
	private int backtrack;
	
	public int[][] getMaze() {
		return maze;
	}


	public void setArray(int[][] maze) {
		this.maze = maze;
	}

	
	public void setTheName(String theName) {
		this.theName = theName;
	}


	public void setChoice(int choice) {
		this.choice = choice;
	}
	
	
	public SortScreen(MainApp theApp, int[][] theMaze) {
		//this.setSize(screenWidth, screenHeight);
		//Graphics2D panelGraphics = (Graphics2D) g.create();
		indexArray = new int[theMaze.length*theMaze[0].length];
		setBackground(Color.BLACK);
		this.theApp = theApp;
		this.maze = theMaze;
		this.totalBars = theMaze.length*theMaze[0].length;
		this.rows = theMaze.length;
		this.cols = theMaze[0].length;
		mod2 = (int) Math.pow(mod, 2);
		repaint();
		endX=theMaze.length-2;
		endY=theMaze[0].length-2;
		backtrack = 0;
		JButton startButton = new JButton("Start");
		player = new MidiSoundPlayer(200);
		startButton.addActionListener(ae -> {
			startButton.setVisible(false);
			theApp.started = true;
		});
		this.add(startButton);
	}
	
	public void reset(int[][] theMaze) {
		this.maze = theMaze;
		this.totalBars = theMaze.length*theMaze[0].length;
		this.rows = theMaze.length;
		this.cols = theMaze[0].length;
		backtrack = 0;
		mod2 = (int) Math.pow(mod, 2);
		repaint();
		endX=theMaze.length-2;
		endY=theMaze[0].length-2;
		this.backGround = null;
	}
	
	@Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        Graphics2D panelGraphics = (Graphics2D) g.create();

		try
		{
			Map<RenderingHints.Key, Object> renderingHints = new HashMap<>();
			renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			panelGraphics.addRenderingHints(renderingHints);
			
//			panelGraphics.drawString("     Array Changes: " + "unfinished", 10, 80);

			drawBars(panelGraphics);
			
			panelGraphics.setColor(Color.WHITE);
			panelGraphics.setFont(new Font("Impact", Font.BOLD, 50));
			panelGraphics.drawString("MAZE WIDTH: " + maze[0].length, 130, 75);
			panelGraphics.drawString("DELAY: " + delay + "ms", 950, 75);
			panelGraphics.drawString("CLOSED NODES: " + theApp.getClosed().size(), 1310, 75);
		} finally {
        	panelGraphics.dispose();
        }
    }
	
	public int getMaxValue() {
    	return Arrays.stream(maze[0]).max().orElse(Integer.MIN_VALUE);
		//return null;
    }
	
	private Color getColor(int index, int y) {
		if(maze[index][y]==0) {
			return Color.WHITE;
		}else if(maze[index][y]==100) {
			return Color.BLACK;
		}else return Color.GREEN;
			
	}
	
	public void setMaze(int[][] maze) {
		this.maze = maze;
	}


	public void setDecide(int decide) {
		this.decide = decide;
	}

	private synchronized void drawBars(Graphics2D panelGraphics)
	{
		
		int barWidth = 1720 / cols;
		int bufferedImageWidth = barWidth * cols;
		int bufferedImageHeight = 1080;
		int height = (int) (880) / rows;
		int offset = 100;
		int xOffset = (1920-(cols*barWidth))/2;
		bufferedImageWidth = getWidth();
		bufferedImageHeight = getHeight();
        
		if(bufferedImageHeight > 0 && bufferedImageWidth > 0) {
			if(bufferedImageWidth < 256) {
				bufferedImageWidth = 256;
			}
			
			double maxValue = getMaxValue();
			
			foreGround = new BufferedImage(bufferedImageWidth, bufferedImageHeight, BufferedImage.TYPE_INT_ARGB);
			if(backGround==null)
				backGround = new BufferedImage(bufferedImageWidth, bufferedImageHeight, BufferedImage.TYPE_INT_ARGB);
			//makeBufferedImageTransparent(bufferedImage);
			Graphics2D bufferedGraphics = null;
			
			
			try
			{
				bufferedGraphics = backGround.createGraphics();
				//foregroundGraphics = foreGround.createGraphics();
				//if(backGround==null) {
				for (int x = 0; x < rows; x++) {
						for(int y = 0; y < cols; y++) {
							
							//double currentValue = maze[0][x];
							//double percentOfMax = currentValue / maxValue;
		//					double heightPercentOfPanel = percentOfMax * 0.5;
							
							int xBegin = barWidth * y + xOffset;
							int yBegin = height * x + offset;
							
							int val = indexArray[x];
		
							bufferedGraphics.setColor(new Color(val,val,val));
		
							
							if(indexArray[x]>0) {
								bufferedGraphics.fillRect(xBegin, 200, 6, 6);
								indexArray[x]-=80;
							}
							
							bufferedGraphics.setColor(getColor(x,y));
							
							visualizationChoice = 1;
							switch(visualizationChoice) {
							case 1:
								bufferedGraphics.fillRect(xBegin, yBegin, barWidth, height);
								break;
							case 2:
								bufferedGraphics.fillRect(xBegin, 840-height, barWidth, barWidth);
								break;
							case 3:
								bufferedGraphics.fillRect(xBegin, 840-height, barWidth, barWidth);
								bufferedGraphics.fillRect(xBegin, 300+height, barWidth, barWidth);
								break;
							case 4:
								bufferedGraphics.drawOval(xBegin, yBegin-height/2, height, height);
								break;
							case 5:
								//System.out.println(Math.sin(Math.PI*(double)2.00*(double)height/maxValue));
								bufferedGraphics.fillRect(xBegin, (int) ((double)640.00-(double)300.00*Math.sin(Math.PI*(double)2.00*(double)height/maxValue)), barWidth, barWidth);
								bufferedGraphics.fillRect(xBegin, (int) ((double)640-(double)200*Math.cos(Math.PI*(double)2*(double)height/maxValue)), barWidth, barWidth);
								bufferedGraphics.fillRect(xBegin, (int) ((double)640-(double)300*Math.sin(Math.PI*(double)2*(double)height/maxValue-Math.PI)), barWidth, barWidth);
								bufferedGraphics.fillRect(xBegin, (int) ((double)640-(double)200*Math.cos(Math.PI*(double)2*(double)height/maxValue-Math.PI)), barWidth, barWidth);
								break;
							case 6:
								bufferedGraphics.fillRect(xBegin, 0, barWidth, 250);
								bufferedGraphics.fillRect(xBegin, 1080-250, barWidth, 250);
		//						panelGraphics.setFont(new Font("Helvetica", Font.BOLD, 70)); 
		//						panelGraphics.drawString("THANKS FOR WATCHING", 500, 500);
		//						panelGraphics.setFont(new Font("Helvetica", Font.ITALIC, 70)); 
		//						panelGraphics.drawString("SUBSCRIBE FOR MORE", 500, 600);
								break;
							}
							
						}
						
						
						
					}
				//}
				
				bufferedGraphics.setColor(new Color(10,10,255));
				for(Node theNode: theApp.getClosedPaint()) {
						bufferedGraphics.fillRect(theNode.x*barWidth + xOffset, theNode.y*height+ offset, barWidth, height);
				}
				

				for(int x = 0; x<=maze.length*2-backtrack*2 ; x++) {
					bufferedGraphics.setColor(new Color(255-((x+backtrack*2)*(255/(maze.length*2))),0,255));
					if(theApp.getClosedPaint().size()>x)
						bufferedGraphics.fillRect(theApp.getClosedPaint().get(theApp.getClosedPaint().size()-1-x).x*barWidth + xOffset, theApp.getClosedPaint().get(theApp.getClosedPaint().size()-1-x).y*height+ offset, barWidth, height);
				}
				if(theApp.getPath().size()>0) {
					backtrack++;
					if(theApp.getPath().size()>1) {
						player.makeSound((int)(190*(((double)(theApp.getPath().get(0).x)/maze[0].length)+(double)(theApp.getPath().get(0).y)/maze.length)/2));
					}
				}else if(theApp.getClosedPaint().size()>1) player.makeSound((int)(190*(((double)theApp.getClosedPaint().get(theApp.getClosedPaint().size()-1).x/maze[0].length)+((double)theApp.getClosedPaint().get(theApp.getClosedPaint().size()-1).y/maze.length))/2));

				bufferedGraphics.setColor(new Color(0,220,0));
				for(Node theNode: theApp.getPath()) {
					bufferedGraphics.fillRect(theNode.x*barWidth + xOffset, theNode.y*height+ offset, barWidth, height);
				}
				
				bufferedGraphics.setColor(new Color(150,0,0));
				for(Node theNode: theApp.getOpenPaint()) {
					bufferedGraphics.fillRect(theNode.x*barWidth + xOffset, theNode.y*height+ offset, barWidth, height);
				}
				
				bufferedGraphics.fillRect((cols-2)*barWidth + xOffset, (rows-2)*height+ offset, barWidth, height);
				
				bufferedGraphics.setColor(new Color(200,130,0));
				bufferedGraphics.fillRect((1)*barWidth + xOffset, (1)*height+ offset, barWidth, height);
				
				bufferedGraphics.setColor(Color.BLACK);
				for(int m = 0; m<rows; m++)
					bufferedGraphics.drawLine(0 + xOffset, m*height+ offset, 1920 + xOffset, m*height+ offset);
				for(int n = 0; n<cols; n++)
					bufferedGraphics.drawLine(n*barWidth + xOffset, 100, n*barWidth + xOffset, 1080);
			}
			finally
			{
				if(bufferedGraphics != null)
				{
					bufferedGraphics.dispose();
				}
			}
			
			panelGraphics.drawImage(backGround, 0, 0, getWidth(), getHeight(), 0, 0, backGround.getWidth(), backGround.getHeight(), null);
		}
	}


	public MainApp getTheApp() {
		return theApp;
	}


	public void setTheApp(MainApp theApp) {
		this.theApp = theApp;
	}


	public void setGreen(boolean b) {
		green = b;
	}


	public int getEndX() {
		return endX;
	}


	public void setEndX(int endX) {
		this.endX = endX;
	}


	public int getEndY() {
		return endY;
	}


	public void setEndY(int endY) {
		this.endY = endY;
	}

}

//21,31,45,67,99,147,219,350,500
//
