package sorts;

import Main.SortScreen;

public class StoogeSort implements Algorithm{
	
	
	private SortScreen theScreen;
	private int[] arr;
	
	public StoogeSort(SortScreen screen, int[] arr) {
		this.theScreen = screen;
		this.arr = arr;
	}
	
	public void begin() {
	    theScreen.setTheName("Stoogesort");
        theScreen.repaint();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
	    theScreen.delay = 2;
	     stoogesort(arr, 0, arr.length-1); 
	     
			for(int i=0;i<arr.length;i++)
				theScreen.indexArray[i] = 0;
	        theScreen.repaint();
	}
	
	private void stoogesort(int arr[], int l, int h) 
    { 
        if (l >= h) 
            return; 
  
        // If first element is smaller 
        // than last, swap them 
        if (arr[l] > arr[h]) { 
            int t = arr[l]; 
            arr[l] = arr[h]; 
            arr[h] = t; 
            theScreen.indexArray[h]=240;
            theScreen.indexArray[l]=240;
            theScreen.repaint();
            if(h%theScreen.mod==0) {
 				 try {
 					Thread.sleep(theScreen.delay);
 				 } catch (InterruptedException e) {
 					e.printStackTrace();
 				 } 
          	} 
        } 


  
        // If there are more than 2 elements in 
        // the array 
        if (h - l + 1 > 2) { 
            int t = (h - l + 1) / 3; 
  
            // Recursively sort first 2/3 elements 
            stoogesort(arr, l, h - t); 
  
            // Recursively sort last 2/3 elements 
            stoogesort(arr, l + t, h); 
  
            // Recursively sort first 2/3 elements 
            // again to confirm 
            stoogesort(arr, l, h - t); 
        } 
    } 
	
	public void setTheArr(int[] array) {
		this.arr = array;
	}  

}
