package shortestpath;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

public class Worker implements Runnable{
	static Logger log = Logger.getLogger(Worker.class.getName());
	
	int threadnum;
	private WorkPool workpool;
	private int weight[][];
	private int mindist[];
	private int inflag[];
	private Lock L[];
	
	public Worker(WorkPool p, int[][] weight,int[] mindist, int[] inflag, Lock[] L, int threadnum) {
		this.threadnum = threadnum;
		
		this.workpool = p;
		this.weight = weight;
		this.mindist = mindist;
		this.inflag = inflag;
		this.L = L;
	}
	
	@Override
	public void run() {
		Integer work;
		try {
			work = workpool.getwork();
			
			while(work != -1){
				if(work != null && work != -1){
					doWork(work);
				}
				work = workpool.getwork();
			}
		} catch (InterruptedException e) {
			//log.info("Thread " + threadnum + " err occured. " + e);
			return;
		}
		
	}
	
	public void doWork(int me) throws InterruptedException{
		int  newdist;
		
		inflag[me] = 0;
		for(int w=0; w<Application.n; w++){
			if(weight[me][w] < Application.inf){
				newdist = mindist[me] + weight[me][w];
				//log.info(threadnum + " locking the lock " + w);
				lock(w);
				if(newdist < mindist[w]){
					mindist[w] = newdist;
					
					//log.info(threadnum + " unlocking the lock " + w);
					unlock(w);
					
					if(! (inflag[w]==1)){
						inflag[w] =1;
						workpool.putwork(w);
					}
				}else{
					//log.info(threadnum + " - unlocking the lock " + w);
					unlock(w);
					
				}
			}
		}
	}

	private void unlock(int w) {
		log.info(threadnum + " unlocking the lock " + w);
		if(((ReentrantLock)L[w]).isHeldByCurrentThread()){
			L[w].unlock();
		}
	}

	private void lock(int w) {
		log.info(threadnum + " locking the lock " + w);
		if(L[w]==null){
			L[w] = new ReentrantLock();
		}
		L[w].lock();
	}
}
