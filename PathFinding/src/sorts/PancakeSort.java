package sorts;

//Java program to 
//sort array using 
//pancake sort 

import Main.SortScreen; 

public class PancakeSort implements Algorithm{ 
	private SortScreen theScreen;
	private int[] arr;
	
	public PancakeSort(SortScreen screen, int[] arr) {
		this.theScreen = screen;
		this.arr = arr;
	}
	
	public void begin() {
	    theScreen.setTheName("Pancakesort");
	    theScreen.repaint();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
	    theScreen.delay = 2;
	     int n = arr.length; 
	     pancakeSort(arr, n); 
			for(int i=0;i<arr.length;i++)
				theScreen.indexArray[i] = 0;
	        theScreen.repaint();
	}

	 /* Reverses arr[0..i] */
	 private void flip(int arr[], int i) 
	 { 
	     int temp, start = 0; 
	     while (start < i) 
	     { 
	         temp = arr[start]; 
	         arr[start] = arr[i]; 
	         arr[i] = temp; 
	         theScreen.indexArray[i]=240;
	         theScreen.indexArray[start]=240;
	            theScreen.repaint();
	            if(i%theScreen.mod2==0) {
     				 try {
     					Thread.sleep(theScreen.delay);
     				 } catch (InterruptedException e) {
     					e.printStackTrace();
     				 } 
              	} 
	         start++; 
	         i--; 
	        
	     } 
	 } 
	
	 // Returns index of the  
	 // maximum element in  
	 // arr[0..n-1]  
	 static int findMax(int arr[], int n) 
	 { 
	     int mi, i; 
	     for (mi = 0, i = 0; i < n; ++i) 
	         if (arr[i] > arr[mi]) 
	             mi = i; 
	     return mi; 
	 } 
	
	 // The main function that 
	 // sorts given array using  
	 // flip operations 
	 private int pancakeSort(int arr[], int n) 
	 { 
	     // Start from the complete 
	     // array and one by one 
	     // reduce current size by one 
	     for (int curr_size = n; curr_size > 1; --curr_size) 
	     { 
	         // Find index of the 
	         // maximum element in 
	         // arr[0..curr_size-1] 
	         int mi = findMax(arr, curr_size); 
	
	         // Move the maximum element 
	         // to end of current array 
	         // if it's not already at  
	         // the end 
	         if (mi != curr_size-1) 
	         { 
	             // To move at the end, 
	             // first move maximum 
	             // number to beginning 
	             flip(arr, mi); 
	
	             // Now move the maximum  
	             // number to end by 
	             // reversing current array 
	             flip(arr, curr_size-1); 
	         } 
	     } 
	     return 0; 
	 } 
	 
	 public void setTheArr(int[] array) {
			this.arr = array;
		}  
}