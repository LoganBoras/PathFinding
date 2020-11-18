package sorts;
import Main.SortScreen;

// Java program for implementation of QuickSort 
public class QuickSort implements Algorithm 
{ 
	SortScreen theScreen;
	int[] array;
	
	public QuickSort(SortScreen theScreen, int[] array) {
	       this.array = array;
	       this.theScreen = theScreen;	   
	}
	
	public void begin() {
		
	    try {
				Thread.sleep(1000);
	    } catch (InterruptedException e) {
				e.printStackTrace();
		}  
	    
	    theScreen.delay = 7;
		
	    int n = array.length; 
	    
	    theScreen.setTheName("Quicksort");
	    
		sort(array, 0, n-1); 

		for(int i=0;i<array.length;i++)
			theScreen.indexArray[i] = 0;
        theScreen.repaint();
	} 
	
    /* This function takes last element as pivot, 
       places the pivot element at its correct 
       position in sorted array, and places all 
       smaller (smaller than pivot) to left of 
       pivot and all greater elements to right 
       of pivot */
    int partition(int array[], int low, int high) 
    { 
        int pivot = array[high];  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than the pivot 
            if (array[j] < pivot) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
                int temp = array[i]; 
                array[i] = array[j]; 
                array[j] = temp; 
                theScreen.indexArray[i]=240;
                  
            } 
            theScreen.indexArray[j]=240;
            theScreen.repaint();
            if(j%theScreen.mod==0) {
				 try {
					Thread.sleep(theScreen.delay);
				 } catch (InterruptedException e) {
					e.printStackTrace();
				 } 
            }
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = array[i+1]; 
        array[i+1] = array[high]; 
        array[high] = temp; 
        
        theScreen.indexArray[i+1]=240;
        theScreen.indexArray[high]=240;
        theScreen.repaint();
		 try {
			Thread.sleep(theScreen.delay);
		 } catch (InterruptedException e) {
			e.printStackTrace();
		 }    
        
        return i+1; 
    } 
  
  
    /* The main function that implements QuickSort() 
      arr[] --> Array to be sorted, 
      low  --> Starting index, 
      high  --> Ending index */
    void sort(int arr[], int low, int high) 
    { 
        if (low < high) 
        { 
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partition(arr, low, high); 
  
            // Recursively sort elements before 
            // partition and after partition 
            sort(arr, low, pi-1); 
            sort(arr, pi+1, high); 
        } 
    } 
  
    /* A utility function to print array of size n */
    static void printArray(int arr[]) 
    { 
        int n = arr.length; 
        for (int i=0; i<n; ++i) 
            System.out.print(arr[i]+" "); 
        System.out.println(); 
    }
    
    @Override
	public void setTheArr(int[] array) {
		this.array = array;
	}  


} 
/*This code is contributed by Rajat Mishra */