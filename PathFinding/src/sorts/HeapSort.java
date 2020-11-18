package sorts;

import Main.SortScreen;

public class HeapSort implements Algorithm{
	private SortScreen theScreen;
	private int[] arr;
	
	public HeapSort(SortScreen screen, int[] arr) {
		this.theScreen = screen;
		this.arr = arr;
	}
	
	public void begin() {
	    theScreen.setTheName("Heapsort");
        theScreen.repaint();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
	    theScreen.delay = 10;
	     sort(arr); 
	     
			for(int i=0;i<arr.length;i++)
				theScreen.indexArray[i] = 0;
	        theScreen.repaint();
	}
	
	public void sort(int arr[]) 
    { 
        int n = arr.length; 
  
        // Build heap (rearrange array) 
        for (int i = n / 2 - 1; i >= 0; i--) 
            heapify(arr, n, i); 
  
        // One by one extract an element from heap 
        for (int i=n-1; i>0; i--) 
        { 
            // Move current root to end 
            int temp = arr[0]; 
            arr[0] = arr[i]; 
            arr[i] = temp; 
            theScreen.indexArray[i]=240;
            if(i%theScreen.mod==0) {
				 try {
					Thread.sleep(theScreen.delay);
				 } catch (InterruptedException e) {
					e.printStackTrace();
				 } 
           }
  
            // call max heapify on the reduced heap 
            heapify(arr, i, 0); 
        } 
    }
	
  
    // To heapify a subtree rooted with node i which is 
    // an index in arr[]. n is size of heap 
    void heapify(int arr[], int n, int i) 
    { 
        int largest = i; // Initialize largest as root 
        int l = 2*i + 1; // left = 2*i + 1 
        int r = 2*i + 2; // right = 2*i + 2 
  
        // If left child is larger than root 
        if (l < n && arr[l] > arr[largest]) 
            largest = l; 
  
        // If right child is larger than largest so far 
        if (r < n && arr[r] > arr[largest]) 
            largest = r; 
  
        // If largest is not root 
        if (largest != i) 
        { 
            int swap = arr[i]; 
            arr[i] = arr[largest]; 
            arr[largest] = swap; 
            theScreen.indexArray[i]=240;
            theScreen.indexArray[largest]=240;
            theScreen.repaint();
            if(i%theScreen.mod==0) {
				 try {
					Thread.sleep(theScreen.delay);
				 } catch (InterruptedException e) {
					e.printStackTrace();
				 } 
           }
  
            // Recursively heapify the affected sub-tree 
            heapify(arr, n, largest); 
        } 
    }
    
    public void setTheArr(int[] array) {
		this.arr = array;
	}  

}
