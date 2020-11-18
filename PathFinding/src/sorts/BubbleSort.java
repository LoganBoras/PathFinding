package sorts;
import Main.SortScreen;

public class BubbleSort implements Algorithm{
	SortScreen theScreen;
	int[] array;
	
	public BubbleSort(SortScreen screen, int[] array) {
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
	    
		bubbleSort(array);
		
		for(int i=0;i<array.length;i++)
			theScreen.indexArray[i] = 0;
        theScreen.repaint();
	}
	
	public void bubbleSort(int[] array) {
		theScreen.setTheName("Bubblesort");
        int n = array.length;  
        int temp = 0;  
        for(int i=0; i < n; i++){  
                for(int j=1; j < (n-i); j++){  
                         if(array[j-1] > array[j]){  
                       	  
	                    		  //transition2.setNode(this.rectArr[j-1]);
	                     		  
	                             temp = array[j-1];  
	                             array[j-1] = array[j];  
	                             array[j] = temp;
                             }  
	                         theScreen.indexArray[j]=240;
	                         theScreen.repaint();
	                         if(j%theScreen.mod2==0) {
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
