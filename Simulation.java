/*
   Shajid Muntaser 
   #1264863
   CSCI 330 M04
 */
package csci_330;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Simulation 
{
    public Simulation(ArrayList<Process> p, int quantum)
    {
        processArrayList = p;
        timeQuantum = quantum;
        cpu=new CPU(timeQuantum);
        readyQueue = new LinkedList<Process>();
        clock = new Clock();
        finishedProcesses = new ArrayList<Process>();
        totalNumOfProcessesInSystem = 5;
        for(int i =0;i<p.size();i++)
        {
            p.get(i).setInitialArrivalTime();
            p.get(i).setInitialBurstTime();
        }
    }

    private ArrayList<Process> processArrayList;
    private int timeQuantum;
    private CPU cpu;
    private Queue<Process> readyQueue;
    private Clock clock;
    private ArrayList<Process> finishedProcesses;
    private static int totalNumOfProcessesInSystem;

    public void runRoundRobinSimulation()
    {
        ProcessCreator(null);
        RoundRobin();
    }

    public void ProcessCreator(Process currentProcess)
    {
        for(int i=0;i<processArrayList.size();i++)
        {
            Process p =processArrayList.get(i);
            if(p.checkIfReadytoExecute()==true && p.checkIfProcessFinished()==0 && (p != currentProcess) && !readyQueue.contains(p))
            {
                readyQueue.add(p);
                System.out.println("Process : " + p.getProcessName() + ": added to ready queue");
            }
        }
    }

    public void RoundRobin()
    {
        boolean done = false;

        while(!done)
        {
            Process currentProcess = readyQueue.peek();//get process at head of queue 
            if(currentProcess != null){
                int runTimeOfPrcoessOnCPU=cpu.run(currentProcess);
                clock.updateTimeTimeSinceStartOfSimulator(runTimeOfPrcoessOnCPU);
                clock.updateArrivalTimeOfProcessesWaitingToGetReadyQueue(processArrayList,runTimeOfPrcoessOnCPU);
                clock.updateWaitTimeOfProcessesInReadyQueue(readyQueue,runTimeOfPrcoessOnCPU, currentProcess);
                ProcessCreator(currentProcess);
                if(currentProcess.checkIfProcessFinished()==1)
                {
                    System.out.println("Process : " + currentProcess.getProcessName() + ": completed execution");

                    processArrayList.remove(currentProcess);
                    System.out.println("Process : " + currentProcess.getProcessName() + ": removed from ready queue");
                    finishedProcesses.add(readyQueue.remove());

                    if(finishedProcesses.size()==totalNumOfProcessesInSystem)
                    {
                        this.EndingSimulation();
                        done=true;
                    }
                }
                else
                {
                    currentProcess.updateContextSwitch();
                    currentProcess.getCurrentProcessInfo();
                    readyQueue.remove();
                    readyQueue.add(currentProcess);
                    ProcessCreator(currentProcess);
                }
            }
            else
            {

                clock.updateTimeTimeSinceStartOfSimulator(timeQuantum);
                clock.updateArrivalTimeOfProcessesWaitingToGetReadyQueue(processArrayList,timeQuantum);
                ProcessCreator(null);
            }
        }
    }

    public void EndingSimulation()
    {
        int totalWaitTimeOfAllProcesses=0;
        int totalTurnAroundTimeOfAllProcesses = 0;
        int totalContextSwitchesOfAllProcesses = 0;
        int wait = 0;
        for(int i =0;i<finishedProcesses.size();i++){
            finishedProcesses.get(i).setTurnAroundTime();}

        int p1TurnAround= finishedProcesses.get(0).getTurnAroundTime();
        int p5TurnAround= finishedProcesses.get(1).getTurnAroundTime();
        int p3TurnAround= finishedProcesses.get(2).getTurnAroundTime();
        int p4TurnAround= finishedProcesses.get(3).getTurnAroundTime();
        int p2TurnAround= finishedProcesses.get(4).getTurnAroundTime();
        int maxTurnAround = Math.max( p1TurnAround, Math.max( p2TurnAround, Math.max( p3TurnAround, Math.max(p4TurnAround, p5TurnAround))));
        totalTurnAroundTimeOfAllProcesses = p1TurnAround+p2TurnAround+p3TurnAround+p4TurnAround+p5TurnAround;

        for(int i =0;i<finishedProcesses.size();i++){
            wait = wait + finishedProcesses.get(i).getTotalWaitTime();}

        for(int i =0;i<finishedProcesses.size();i++)
        {
            totalWaitTimeOfAllProcesses = wait;

            totalContextSwitchesOfAllProcesses = totalContextSwitchesOfAllProcesses + finishedProcesses.get(i).getContextSwitch();
        }
        
        double averageWaitTime = totalWaitTimeOfAllProcesses/totalNumOfProcessesInSystem;
        double averageTurnAroundTime = totalTurnAroundTimeOfAllProcesses/totalNumOfProcessesInSystem;

        double throughput= ((double)cpu.getNumberOfCompletedProcesses()/clock.getTimeSinceStartOfSimulator()) *100.0;
        double runningTimeMinusContextSwitch=clock.getTimeSinceStartOfSimulator()-totalContextSwitchesOfAllProcesses;
        double utilization= (runningTimeMinusContextSwitch/clock.getTimeSinceStartOfSimulator())*100.0;

        System.out.println();
        System.out.println();
        System.out.println("Results: ");
        System.out.println("CPU utilization: " + utilization + "%");
        System.out.println("Throughput: " + throughput + "%");
        System.out.println("Average Waiting Time: " + averageWaitTime);
        System.out.println("Average Turnaround Time: " + averageTurnAroundTime);
    
}
    
}
