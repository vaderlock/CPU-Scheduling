/*
   Shajid Muntaser 
   #1264863
   CSCI 330 M04
 */
package csci_330;

public class CPU {
	public CPU(int quantum) {
		timeQuantum = quantum;
		numberOfCompletedProcesses = 0;
	}
	
	public int run(Process p) {
		if(p.getRemainingBurstTime()>timeQuantum) {
			p.updateRemainingBurstTime();
			return timeQuantum;
			
		}
		else if(p.getRemainingBurstTime()<timeQuantum) {
			 int run = p.getRemainingBurstTime();
	            p.updateRemainingBurstTime();
	            numberOfCompletedProcesses++;
	            return run;
		}
		else {
			 p.updateRemainingBurstTime();
	            numberOfCompletedProcesses++;
	            return timeQuantum;
		}
	}

	public int getNumberOfCompletedProcesses(){
		return numberOfCompletedProcesses;
	}
	private int timeQuantum;
	private int numberOfCompletedProcesses;
}
