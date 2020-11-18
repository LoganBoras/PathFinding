package sorts;

//Java Program to implement Gnome Sort 


import Main.SortScreen; 

public class GnomeSort implements Algorithm{ 
	private SortScreen theScreen;
	private int[] arr;
	
	public GnomeSort(SortScreen screen, int[] arr) {
		this.theScreen = screen;
		this.arr = arr;
	}
	
	public void begin() {
	    theScreen.setTheName("Gnomesort");
        theScreen.repaint();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
	    theScreen.delay = 1;
	     int n = arr.length; 
	     gnomeSort(arr, n); 
	     
			for(int i=0;i<arr.length;i++)
				theScreen.indexArray[i] = 0;
	        theScreen.repaint();
	}
	
	 private void gnomeSort(int arr[], int n) 
	 { 
	     int index = 0; 
	
	     while (index < n) { 
	         if (index == 0) 
	             index++; 
	         if (arr[index] >= arr[index - 1]) 
	             index++; 
	         else { 
	             int temp = 0; 
	             temp = arr[index]; 
	             arr[index] = arr[index - 1]; 
	             arr[index - 1] = temp; 
	             index--;  
	         }
	         theScreen.indexArray[index]=240;
            theScreen.repaint();
            if(index%theScreen.mod==0) {
  				 try {
  					Thread.sleep(theScreen.delay);
  				 } catch (InterruptedException e) {
  					e.printStackTrace();
  				 } 
           	} 
	     } 
	     return; 
	 }

	 @Override
		public void setTheArr(int[] array) {
			this.arr = array;
		}  
}
