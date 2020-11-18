package sorts;

import Main.SortScreen;

//Java implementation of Counting Sort (maybe dont use for now)
public class CountingSort 
{ 
	SortScreen theScreen;
	int[] array;
	
	public CountingSort(SortScreen screen, int[] array) {
		this.theScreen = screen;
		this.array = array;
	}
	
	public void begin() {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
	    theScreen.delay = 1;
	    
		sort();
		
		for(int i=0;i<array.length;i++)
			theScreen.indexArray[i] = 0;
        theScreen.repaint();
	}
	void sort() 
	 { 
	     int n = array.length; 
	
	     // The output character array that will have sorted arr 
	     int output[] = new int[n]; 
	
	     // Create a count array to store count of inidividul 
	     // characters and initialize count array as 0 
	     int count[] = new int[256]; 
	     for (int i=0; i<256; ++i) 
	         count[i] = 0; 
	
	     // store count of each character 
	     for (int i=0; i<n; ++i) 
	         ++count[array[i]]; 
	
	     // Change count[i] so that count[i] now contains actual 
	     // position of this character in output array 
	     for (int i=1; i<=255; ++i) 
	         count[i] += count[i-1]; 
	
	     // Build the output character array 
	     // To make it stable we are operating in reverse order. 
	     for (int i = n-1; i>=0; i--) 
	     { 
	         output[count[array[i]]-1] = array[i]; 
	         --count[array[i]]; 
	     } 
	
	     // Copy the output array to arr, so that arr now 
	     // contains sorted characters 
	     for (int i = 0; i<n; ++i) 
	         array[i] = output[i]; 
	 } 

} 
/*This code is contributed by Rajat Mishra */
