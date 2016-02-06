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
			t.IncreaseTime(243);
			Process p = new Process(0,56);
			p.Execute(t);
			/*Process []process_list;
			for (i=0;i<100;i++)
				{
					process_list[i] = new Process(0,i);
				}
			for (i=10;i<50;i++)
				process_list[i].Execute(t);
			*/
			System.out.println(t.getNow() + "\n" +p.getWaitingTime(t));
		}
}
