package sorts;

import Main.SortScreen;

/* Java program for Merge Sort */
public class MergeSort implements Algorithm
{ 
	private SortScreen theScreen;
	private int[] arr;
	
	public MergeSort(SortScreen screen, int[] arr) {
		this.theScreen = screen;
		this.arr = arr;
	}
	
	public void begin() {
	    theScreen.setTheName("Mergesort");
        theScreen.repaint();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	    theScreen.delay = 10;
	     int n = arr.length; 
	     sort(arr,0, n-1); 
	     
			for(int i=0;i<arr.length;i++)
				theScreen.indexArray[i] = 0;
	        theScreen.repaint();
	}
    // Merges two subarrays of arr[]. 
    // First subarray is arr[l..m] 
    // Second subarray is arr[m+1..r] 
    void merge(int arr[], int l, int m, int r) 
    { 
        // Find sizes of two subarrays to be merged 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        /* Create temp arrays */
        int L[] = new int [n1]; 
        int R[] = new int [n2]; 
  
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i) 
            L[i] = arr[l + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = arr[m + 1+ j]; 
  
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = l; 
        while (i < n1 && j < n2) 
        { 
            if (L[i] <= R[j]) 
            { 
                arr[k] = L[i]; 
                i++; 
                theScreen.indexArray[k]=240;
                theScreen.repaint();
                theScreen.repaint();
                if(k%theScreen.mod==0) {
      				 try {
      					Thread.sleep(theScreen.delay);
      				 } catch (InterruptedException e) {
      					e.printStackTrace();
      				 } 
               	} 
            } 
            else
            { 
                arr[k] = R[j]; 
                j++; 
                theScreen.indexArray[k]=240;
                theScreen.repaint();
                if(k%theScreen.mod==0) {
     				 try {
     					Thread.sleep(theScreen.delay);
     				 } catch (InterruptedException e) {
     					e.printStackTrace();
     				 } 
              	} 
            } 
            k++; 
        } 
  
        /* Copy remaining elements of L[] if any */
        while (i < n1) 
        { 
            arr[k] = L[i]; 
        	   theScreen.indexArray[k]=240;
               theScreen.repaint();
               if(k%theScreen.mod==0) {
    				 try {
    					Thread.sleep(theScreen.delay);
    				 } catch (InterruptedException e) {
    					e.printStackTrace();
    				 } 
             	} 
            i++; 
            k++; 
         
        } 
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) 
        { 
            arr[k] = R[j]; 
            theScreen.indexArray[k]=240;
            theScreen.repaint();
            if(k%theScreen.mod==0) {
 				 try {
 					Thread.sleep(theScreen.delay);
 				 } catch (InterruptedException e) {
 					e.printStackTrace();
 				 } 
          	} 
            j++; 
            k++; 
        } 
    } 
  
    // Main function that sorts arr[l..r] using 
    // merge() 
    void sort(int arr[], int l, int r) 
    { 
        if (l < r) 
        { 
            // Find the middle point 
            int m = (l+r)/2; 
  
            // Sort first and second halves 
            sort(arr, l, m); 
            sort(arr , m+1, r); 
  
            // Merge the sorted halves 
            merge(arr, l, m, r); 
        } 
    } 
    
    public void setTheArr(int[] array) {
		this.arr = array;
	}  
}
