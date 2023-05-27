# CPU-Scheduling
A Java program built to replicate a round robin CPU scheduling

CPU scheduling algorithms are a time-saving method of running a process. Round Robin is an 
example of a CPU scheduling method. Round Robin is a starvation-free scheduling algorithm in which all
processes are given equal turns on the CPU with a set execution period. This prevents events from 
interrupting the execution of low-priority processes. The quantum time covers part, if not all, of the 
burst time after a process is replaced by the CPU. If a process has a burst time remaining from the 
previous path, it is routinely pushed to the end of the queue for other processes and waits for the next 
pass until it completes. 
The repo contains a program that replicates a Round Robin CPU scheduling built in Java. 
Testing may be done with processes that have burst times and a quantum. Using the given burst 
time and quantum, the program determines the waiting time and turnaround time for the 
corresponding operations. Furthermore, the simulation provides details such as CPU utilization, 
throughput, average waiting time, average turnaround time, time quantum, and the number of contexts 
switches.
-----------------------------------------------------------------------------------------------------------------------------------------
To run the program-
1. Run “RoundRobinSimulation.java” class
2. Input PID in String form in FIFO order in the processes array (.CSV/.txt)
3. Input the burst times of the processes in relation to the order in the burst time array (.CSV/.txt)
4. Enter the time quantum you want to test
5. Enter config file path for .CSV/.txt file
5. Output printed in console
-----------------------------------------------------------------------------------------------------------------------------------------
