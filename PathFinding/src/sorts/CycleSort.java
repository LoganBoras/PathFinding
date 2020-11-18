package sorts;

//Java program to implement cycle sort 


import Main.SortScreen;


public class CycleSort implements Algorithm{ 
	private SortScreen theScreen;
	private int[] array;
	
	public CycleSort(SortScreen screen, int[] array) {
		this.theScreen = screen;
		this.array = array;
	}
	
	public void begin() {
	    theScreen.setTheName("Cyclesort");
        theScreen.repaint();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
	    theScreen.delay = 1;
	     int n = array.length; 
	     cycleSort(array, n); 
	     
			for(int i=0;i<array.length;i++)
				theScreen.indexArray[i] = 0;
	        theScreen.repaint();
	}
	
	 public void cycleSort(int array[], int n) 
	 { 
	     // count number of memory writes 
	     int writes = 0; 
	
	     // traverse array elements and put it to on 
	     // the right place 
	     for (int cycle_start = 0; cycle_start <= n - 2; cycle_start++) { 
	         // initialize item as starting point 
	         int item = array[cycle_start]; 
	
	         // Find position where we put the item. We basically 
	         // count all smaller elements on right side of item. 
	         int pos = cycle_start; 
	         for (int i = cycle_start + 1; i < n; i++) { 
	             if (array[i] < item) 
	                 pos++; 
	             theScreen.indexArray[i]=240;
	             if(i%3==0) {
		             theScreen.repaint();
		 			 try {
		 				Thread.sleep(theScreen.delay);
		 			 } catch (InterruptedException e) {
		 				e.printStackTrace();
		 			 } 
	             }
	         }
	
	         // If item is already in correct position 
	         if (pos == cycle_start) 
	             continue; 
	
	         // ignore all duplicate elements 
	         while (item == array[pos]) 
	             pos += 1; 
	
	         // put the item to it's right position 
	         if (pos != cycle_start) { 
	             int temp = item; 
	             item = array[pos]; 
	             array[pos] = temp; 
	             writes++; 
	             theScreen.repaint();
     			 try {
     				Thread.sleep(theScreen.delay);
     			 } catch (InterruptedException e) {
     				e.printStackTrace();
     			 }  
	         } 
	
	         // Rotate rest of the cycle 
	         while (pos != cycle_start) { 
	             pos = cycle_start; 
	
	             // Find position where we put the element 
	             for (int i = cycle_start + 1; i < n; i++) { 
	                 if (array[i] < item) 
	                     pos += 1; 
	                 theScreen.indexArray[i]=240;
	                 if(i%(3*theScreen.mod)==0) {
	    				 try {
	    					Thread.sleep(theScreen.delay);
	    				 } catch (InterruptedException e) {
	    					e.printStackTrace();
	    				 } 
	               }
	             }
	
	             // ignore all duplicate elements 
	             while (item == array[pos]) 
	                 pos += 1; 
	
	             // put the item to it's right position 
	             if (item != array[pos]) { 
	                 int temp = item; 
	                 item = array[pos]; 
	                 array[pos] = temp; 
	                 writes++; 
	                 theScreen.repaint();
         			 try {
         				Thread.sleep(theScreen.delay);
         			 } catch (InterruptedException e) {
         				e.printStackTrace();
         			 }  
	             } 
	         } 
	     } 
	 }
	 
	 @Override
		public void setTheArr(int[] array) {
			this.array = array;
		}  
}
//Code Contributed by Mohit Gupta_OMG <(0_o)> 
