package shortestpath;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WorkPool {
	
	BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
	Integer count = 0;
	Lock lock = new ReentrantLock();
	
	public Integer getwork() throws InterruptedException {
		/* use Getwork code from Fig 11.4 of course text */
		int workcount=0;
		lock.lock();
			workcount = count -1;
			count = workcount;
		lock.unlock();
		if(workcount <= -Application.numworkers){
			for(int i=0; i<Application.numworkers;i++){
				queue.put(-1);
			}
		}
		Integer work = queue.take();
		
		return work;
	}

	public void putwork(int item) throws InterruptedException {
		/* use Putwork code from Fig 11.4 of course text */
		lock.lock();
			count ++;
		lock.unlock();
		
		queue.put(item);
	}
}
