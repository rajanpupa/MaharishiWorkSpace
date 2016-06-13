package sorting;

public class MergeSort {

	public static void main(String[] args) {
		
		int size = 1000000/10;
		
		Integer x[] = new Integer[size];
		Integer y[] = new Integer[size];
		
		//initialization
		for(int i=0; i<size; i++){
			x[i]=i;
			y[i] = size + i;
		}
		
		//2,000,000
		
		Integer z[] = new Integer[x.length + y.length];
		
		final long startTime = System.currentTimeMillis();
		sort(x, y, z);
		
		sort(y, x, z);
		
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime) );
		
	}

	private static void sort(Integer[] x, Integer[] y, Integer[] z) {
		for(int i=0; i<x.length; i++){
			z[i+rank(y, x[i])] = x[i];
		}
	}
	
	public static Integer rank(Integer[] arr, int val){
		int rank = 0;
		
		for(int i=0; i< arr.length; i++){
			if(val > arr[i]){
				rank ++;
			}else{
				break;
			}
		}
		return rank;
	}
}
