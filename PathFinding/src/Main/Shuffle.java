package Main;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Shuffle {
	private int[][] array;
	private SortScreen theScreen;
	private Random rd;
	private int randY;
	private int randX;
	
	public Shuffle(SortScreen theScreen, int[][] maze) {
		this.theScreen = theScreen;
		this.array = maze;
		rd = new Random();
		//shuffle();
	}
	
	public void shuffle() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	    theScreen.delay = 3;
		
		int temp;
		int rand;
		theScreen.setTheName("Shuffle");
		
		theScreen.setDecide(rd.nextInt(12)+1);
		theScreen.setChoice(rd.nextInt(26));
		
		theScreen.getTheApp().setPath(new CopyOnWriteArrayList <Node>());
		theScreen.getTheApp().setOpen(new CopyOnWriteArrayList <Node>());
		theScreen.getTheApp().setClosed(new CopyOnWriteArrayList <Node>());
		
		for(int i=0;i<array.length;i++) {
			for(int j = 0; j<array[0].length; j++) {
				randY = rd.nextInt(array.length-4)+2;
				randX = rd.nextInt(array[0].length-4)+2;
				if(i%20==0) {
					theScreen.setDecide(rd.nextInt(12)+1);
					theScreen.setChoice(rd.nextInt(26));
				}
				temp =array[i][j];
				array[i][j] = array[randY][randX];
				array[randY][randX] = temp;
	            theScreen.indexArray[i]=240;
				theScreen.repaint();
				if(i%theScreen.mod==0) {
	 				 try {
	 					Thread.sleep(theScreen.delay);
	 				 } catch (InterruptedException e) {
	 					e.printStackTrace();
	 				 } 
	          	} 
		}
		}
		for(int i=0;i<array.length;i++)
			theScreen.indexArray[i] = 0;
        theScreen.repaint();
	}
}
