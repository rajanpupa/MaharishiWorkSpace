package shortestpath.sequential;

import java.util.LinkedList;
import java.util.List;

/**
 * Minimum Spanning tree, 
 * shortest path finder from node A to node E.
 * 
 * When the program ends executing, the array mindist, will have the min traversing cost from A(0) to different nodes.
 * @author Rajan
 *
 */
public class ShortestPathFinderSequential {

	public static void main(String[] args) {
		int n = 5;
		int inf = 32000;
		int weight[][] = {
			{inf, 4, 8, inf, inf},//A0 - A4
			{inf, inf, 3, 1, inf}, 
			{inf, inf, inf, inf, 5},
			{inf, inf, 2, inf, 10}, // D0-D4
			{inf, inf, inf, inf, inf}};
		int mindist[] = new int[n];
		
		// initiaize mindist arr to infinity
		for(int i=0; i<mindist.length; i++){
			mindist[i] = inf;
		}
		
		List<Integer> queue = new LinkedList<Integer>();
		//initialize queue to contain source vertex 1
		queue.add(0);
		
		mindist[0]=0;
		int newDist;
		
		// head of queue
		int x =-1;
		while(!queue.isEmpty()){
			//head of queue
			x = queue.remove(queue.size()-1);
			for(int w=0; w<n; w++){
				newDist = mindist[x] +weight[x][w];
				if(newDist < mindist[w]){
					mindist[w] = newDist;
					// if w is not in queue, add w to queue
					addToQueue(queue, w);
				}
			}
		}
	}

	private static void addToQueue(List<Integer> queue, int w) {
		for(int a: queue){
			if(a==w){// w is already in queue
				return;
			}
		}
		queue.add(w);
		
	}
}
