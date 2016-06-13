package shortestpath;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Application {
	public static final int numworkers = 1;
	public static final int n = 500;//400;
	private static int weight[][] = new int[n][n];
	//private static int weight[][] ;
//	= {
//	{inf, 4, 8, inf, inf},
//	{inf, inf, 3, 1, inf}, {inf, inf, inf, inf, 5},
//	{inf, inf, 2, inf, 10}, {inf, inf, inf, inf, inf}};
	
	//public static final int infinity = 32000;
	private static WorkPool workpool = new WorkPool();
	private static int mindist[] = new int[n];
	private static int inflag[] = new int[n];
	private static Lock L[] = new ReentrantLock[n];
	static int inf = 32000;

	public static void main(String[] args) throws InterruptedException {
//		int weight[][] = {
//			{inf, 4, 8, inf, inf},
//			{inf, inf, 3, 1, inf}, {inf, inf, inf, inf, 5},
//			{inf, inf, 2, inf, 10}, {inf, inf, inf, inf, inf}};
//		
		int point[][] = new int[n][2];
		int i,j, temp=0, dist=0;
		Random rand = new Random(500);
		
		for (i = 0; i < n; i++) {
			temp = rand.nextInt(10);
			point[i][0] = temp;
			temp = rand.nextInt(10);
			point[i][1] = temp;
		}
		
		for( i=0; i<n; i++){
			for ( j = 0; j <= i; j++ ){
				if ( i == j) weight[i][j] = 0;
				else {
					temp = point[i][0]-point[j][0];
					dist = temp*temp;
					temp = point[i][1]-point[j][1];
					dist = dist + temp*temp;
					weight[i][j] = dist;
					weight[j][i] = dist;
				}
			}
		}
		
		// initiaize mindist arr to infinity
		for(i=0; i<mindist.length; i++){
			mindist[i] = inf;
		}
		
		Date t = new Date();
		// insert your code here to create two Worker Threads
		Thread[] threads = new Thread[numworkers];
		
		for( i=0;i<numworkers; i++){
			threads[i] = new Thread(new Worker(workpool, weight, mindist, inflag, L, i));
		}
		
		mindist[0]=0;
		workpool.putwork(0);
		
		// start all threads
		for( i=0;i<numworkers; i++){
			threads[i].start();
		}
		
		// wait for the completion of all threads
		for( i=0;i<numworkers; i++){
			threads[i].join();
		}
		
		Date s = new Date();
		
		System.out.print("Parallel Elapsed Time: ");
		System.out.println(s.getTime() - t.getTime());
	}
}
