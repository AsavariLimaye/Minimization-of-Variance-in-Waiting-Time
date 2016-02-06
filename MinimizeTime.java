import java.util.*;
class Time
	{
		private int Now;
		Time()
			{
				Now=0;
			}
		void IncreaseTime(int amt)
			{
				Now+=amt;
			}
		int getNow()
			{
				return Now;
			}
	}

class Process
	{
		public static final int AVERAGE_BURST_TIME = 20;
		private boolean Executed;
		private int ArrivalTime;
		private int StartTime;
		private int BurstTime;
		private int WaitingTime;

		Process()
			{
				ArrivalTime=0;
				Executed=false;
				BurstTime =AVERAGE_BURST_TIME;

			}

		Process (int ArrivalTime_T,int BurstTime_T)
			{
				ArrivalTime = ArrivalTime_T;
				BurstTime =BurstTime_T;
			}

		void Execute(Time CurrentTime)
			{
				Executed = true;
				WaitingTime = CurrentTime.getNow() - ArrivalTime;
				CurrentTime.IncreaseTime(BurstTime);
			}

		int getWaitingTime(Time CurrentTime)
			{
				if (Executed == true)
					return WaitingTime;
				else
					return CurrentTime.getNow() - ArrivalTime;
			}
	}



class MinimizeTime
{
	public static void main(String args[])
		{
			Time t = new Time();
			int i;
			ArrayList<Process> process_list = new ArrayList<Process>(100);
			Process p;
			for (i=0;i<100;i++)
				{
					p = new Process(0,i);
					process_list.add(p);
				}
			for (i=10;i<50;i++)
				{
					System.out.println("Process "+i+" started executing at "+ t.getNow());
					p =(Process) process_list.get(i);
					p.Execute(t);
					System.out.println("Process "+i+" finished executing at "+ t.getNow());
				}
			for (i=0;i<100;i++)
			{	
				p = (Process) process_list.get(i);
				System.out.println(i+":"+ p.getWaitingTime(t));
			}
		}
}
