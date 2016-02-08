import java.util.*;
class Process
	{
		public static final int MAXIMUM_BURST_TIME = 10000;
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
		Process (Process P)
			{
			Executed= P.Executed;
			ArrivalTime = P.ArrivalTime;
			StartTime = P.StartTime;
			BurstTime = P.BurstTime;
			WaitingTime = P.WaitingTime;
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

		int getBurstTime()
			{
				return BurstTime;
			}
	}
