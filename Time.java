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

class BurstTimeComparator implements Comparator<Process>
	{
		@Override
		public int compare(Process a, Process b)
			{
				return ( a.getBurstTime() > b.getBurstTime()?1:-1);
			}
	}

class BurstTimeComparator2 implements Comparator<Process>
	{
		@Override
		public int compare(Process a, Process b)
			{
				return ( a.getBurstTime() < b.getBurstTime()?1:-1);
			}
	}

