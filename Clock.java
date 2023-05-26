/*
   Shajid Muntaser 
   #1264863
   CSCI 330 M04
 */
package csci_330;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

public class Clock {
	
	public Clock(){
		time = 0;
	}
	
	public void updateTimeTimeSinceStartOfSimulator(int cpuRunTime) {
		time = time + cpuRunTime;
	}
	
	public int getTimeSinceStartOfSimulator() {
		return time;
	}
	
	public void updateArrivalTimeOfProcessesWaitingToGetReadyQueue(ArrayList<Process> processList, int cpuRunTime) {
		for(int i=0; i<processList.size();i++) {
			Process p = processList.get(i);
			p.updateArrivalTime(cpuRunTime);
		}
	}
	
	public void updateWaitTimeOfProcessesInReadyQueue(Queue<Process> q, int cpuRunTime, Process currentP) {
		Iterator<Process> itr = q.iterator();
        while(itr.hasNext())
        {
        	 Process p = itr.next();
             if(p != currentP)
             {
            	 p.setCurrentWaitTime(cpuRunTime);
             }
        }
	}

	private int time;
}
