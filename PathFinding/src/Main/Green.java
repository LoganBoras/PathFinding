package Main;

public class Green {
	int[] arr;
	SortScreen theScreen;
	
	public Green(int[] arr, SortScreen theScreen) {
		this.arr = arr;
		this.theScreen = theScreen;		
	}
	
	public void makeGreen() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		theScreen.setGreen(true);
		theScreen.repaint();
	}

}
