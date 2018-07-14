#Minimization of Variance of Waiting Time of Processes

Simulation of the different heuristic methods which exist to minimize the variance in waiting time for processes, written in Java.

Based on http://web.utk.edu/~xli27/rawDocs/XLI/x_Job%20scheduling%20methods%20for%20reducing%20waiting%20time%20variance.pdf, also added to the repository.

### Assumptions
1. Non-preemptive scheduling is used
2. These methods are used only to schedule user processes not system processes.

### Models implemented

1. Unsorted (No model used)
2. Shortest Job First
3. V Model
4. Double-V Model
5. Spiral Model


## Instructions

Make sure you have javac and java installed. If not, install them on the command line.

To compile and run:

javac MinimizeTime.java Process.java Models.java Time.java
java Models

Or, use the commands
1. make : To clean, compile and run
2. make clean : To clean
