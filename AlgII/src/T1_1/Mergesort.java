package T1_1;


public class Mergesort {
    private Mina[] minas;
    private Mina[] helper;
    private int command;

    private int number;

    public Mina[] sort(Mina[] values, int command) {
        this.minas = values;
        number = values.length;
        this.helper = new Mina[number];
        this.command = command;
        mergesort(0, number - 1);
        return minas;
        
    }

    private void mergesort(int low, int high) {
        // Check if low is smaller then high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = (low + high) / 2;
            // Sort the left side of the array
            mergesort(low, middle);
            // Sort the right side of the array
            mergesort(middle + 1, high);
            // Combine them both
            merge(low, middle, high);
        }
    }

    private void merge(int low, int middle, int high) {

        // Copy both parts into the helper array
        for (int i = low; i <= high; i++) {
            helper[i] = minas[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
        	if(command == 0){
        		if (helper[i].getY() <= helper[j].getY()) {
        			if(helper[i].getY() == helper[j].getY() && helper[i].getX() > helper[j].getX()) {
        				minas[k] = helper[j];
    	                j++;
	                } else {
		                minas[k] = helper[i];
		                i++;
	                }
	            } else {
            		minas[k] = helper[j];
 	                j++;
	            }
	            k++;
        	} else if(command == 1){
        		if (helper[i].getY() >= helper[j].getY()) {
        			if(helper[i].getY() == helper[j].getY() && helper[i].getX() > helper[j].getX()) {
        				minas[k] = helper[j];
    	                j++;
	                } else {
		                minas[k] = helper[i];
		                i++;
	                }
	            } else {
            		minas[k] = helper[j];
 	                j++;
	            }
	            k++;
        	} else if(command == 2) {
	            if (helper[i].getX() <= helper[j].getX()) {
	            	if(helper[i].getX() == helper[j].getX() && helper[i].getY() > helper[j].getY()) {
        				minas[k] = helper[j];
    	                j++;
	                } else {
		                minas[k] = helper[i];
		                i++;
	                }
	            } else {
	                minas[k] = helper[j];
	                j++;
	            }
	            k++;
        	} else if(command == 3) {
	            if (helper[i].getX() >= helper[j].getX()) {
	            	if(helper[i].getX() == helper[j].getX() && helper[i].getY() > helper[j].getY()) {
        				minas[k] = helper[j];
    	                j++;
	                } else {
		                minas[k] = helper[i];
		                i++;
	                }
	            } else {
	                minas[k] = helper[j];
	                j++;
	            }
	            k++;
        	}
        	
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            minas[k] = helper[i];
            k++;
            i++;
        }
    }

//    public static void main(String args[]) {
//        Random r = new Random();
//        int[] data = new int[100000];
//        for(int i=0; i<data.length; i++)
//            data[i] = r.nextInt(data.length*10);
//
//        long start = System.nanoTime();
//        Mergesort ms = new Mergesort();
//        ms.sort(data);
//        long end = System.nanoTime();
//        //for(int i=0; i<data.length; i++)
//        //    System.out.print(data[i]+" ");
//        //System.out.println();
//        System.out.println(end-start);
//    }
    
//    public static void main(String args[]) {
//    	Mina[] minas = new Mina[7];
//    	minas[0] = new Mina(5,1);
//    	minas[1] = new Mina(3,4);
//    	minas[2] = new Mina(2,1);
//    	minas[3] = new Mina(1,3);
//    	minas[4] = new Mina(5,4);
//    	minas[5] = new Mina(2,5);
//    	minas[6] = new Mina(1,4);
//    	Mergesort ms = new Mergesort();
//    	Mina[] auxMina = minas.clone();
//    	Mina[] printadora = ms.sort(auxMina,2);
//    	for(int i=0; i<7; i++) {
//    		System.out.println(printadora[i].toString() + "\n");
//    	}
//    }
}

