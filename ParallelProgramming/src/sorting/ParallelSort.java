package sorting;

public class ParallelSort implements Runnable{
	int a;
	Integer x[];
	Integer y[];
	Integer z[];
	
	public ParallelSort(Integer[]x, Integer[]y, Integer[]z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		int size = 1000000/10;
		
		Integer x[] = new Integer[size];
		Integer y[] = new Integer[size];
		Integer z[] = new Integer[x.length + y.length];
		
		//initialization
		for(int i=0; i<size; i++){
			x[i]=i;
			y[i] = size + i;
		}
		final long startTime = System.currentTimeMillis();
		
		Runnable a = new ParallelSort(x, y, z);
		Runnable b = new ParallelSort(y, x, z);
		
		Thread xSort = new Thread(a);
		Thread ySort = new Thread(b);
		
		xSort.start();
		ySort.start();
		
		//xSort.run();
		//ySort.run();
		
		//Thread.sleep(1000);
		xSort.join();
		ySort.join();
		
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime) );
		
		//printZ(z);
		
		
	}


	private static void printZ(Integer[] z) {
		for(int i=0; i<z.length; i++){
			System.out.print(z[i]+", ");
			if(i%100==0){
				System.out.println();
			}
		}
	}
	
	private void sort(Integer[] x, Integer[] y, Integer[] z) {
		for(int i=0; i<x.length; i++){
			z[i+rank(y, x[i])] = x[i];
		}
	}
	
	public Integer rank(Integer[] arr, int val){
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

	@Override
	public void run() {
		sort(x, y, z);
	}
}

