package sorts;

import Main.SortScreen;

public class InsertionSort implements Algorithm{
	
	SortScreen theScreen;
	int[] array;
	
	public InsertionSort(SortScreen theScreen, int[] array) {
	       this.array = array;
	       this.theScreen = theScreen;	   
	}
	
	public void begin() {
		
	    try {
				Thread.sleep(1000);
	    } catch (InterruptedException e) {
				e.printStackTrace();
		}  
	    
	    theScreen.delay = 1;
		
	    theScreen.setTheName("Insertionsort");
	    
		sort(array); 

		for(int i=0;i<array.length;i++)
			theScreen.indexArray[i] = 0;
        theScreen.repaint();
	} 
	
	 /*Function to sort array using insertion sort*/
    void sort(int arr[]) 
    { 
        int n = arr.length; 
        for (int i = 1; i < n; ++i) { 
            int key = arr[i]; 
            int j = i - 1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j >= 0 && arr[j] > key) { 
                arr[j + 1] = arr[j]; 
                theScreen.indexArray[j]=240;
                theScreen.repaint();
            	if(j%(4*theScreen.mod)==0) {
       				 try {
       					Thread.sleep(theScreen.delay);
       				 } catch (InterruptedException e) {
       					e.printStackTrace();
       				 } 
            	}
                j = j - 1; 
            } 
            theScreen.indexArray[i]=240;
            theScreen.repaint();
			 try {
				Thread.sleep(theScreen.delay);
			 } catch (InterruptedException e) {
				e.printStackTrace();
			 }  
            arr[j + 1] = key; 
        } 
    } 
    
    @Override
	public void setTheArr(int[] array) {
		this.array = array;
	}  

}
