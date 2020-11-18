package sorts;

import Main.SortScreen;

public class SelectionSort implements Algorithm{
	
	private SortScreen theScreen;
	private int[] arr;
	
	public SelectionSort(SortScreen screen, int[] arr) {
		this.theScreen = screen;
		this.arr = arr;
	}
	
	public void begin() {
	    theScreen.setTheName("Selectionsort");
        theScreen.repaint();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
	    theScreen.delay = 1;
	     sort(arr); 
		for(int i=0;i<arr.length;i++)
			theScreen.indexArray[i] = 0;
        theScreen.repaint();
	}
	
	void sort(int arr[]) 
    { 
        int n = arr.length; 
  
        // One by one move boundary of unsorted subarray 
        for (int i = 0; i < n-1; i++) 
        { 
            // Find the minimum element in unsorted array 
            int min_idx = i; 
            for (int j = i+1; j < n; j++) { 
                if (arr[j] < arr[min_idx]) 
                    min_idx = j; 
                theScreen.indexArray[j]=240;
                theScreen.repaint();
                if(j%(4*theScreen.mod)==0) {
     				 try {
     					Thread.sleep(theScreen.delay);
     				 } catch (InterruptedException e) {
     					e.printStackTrace();
     				 } 
              	} 
            }
  
            // Swap the found minimum element with the first 
            // element 
            int temp = arr[min_idx]; 
            arr[min_idx] = arr[i]; 
            arr[i] = temp; 
            theScreen.indexArray[i]=240;
            theScreen.repaint();
			 try {
				Thread.sleep(theScreen.delay);
			 } catch (InterruptedException e) {
				e.printStackTrace();
			 }  
            
        } 
    } 
	
	public void setTheArr(int[] array) {
		this.arr = array;
	}  

}
