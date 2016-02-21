import java.util.*;
import java.lang.*;

class MinimizeTime
{
	public static final int MAX_QUEUE_SIZE=20;

	public static void copy_process_list(ArrayList<Process> to , ArrayList<Process> from)
			{
			int i;
			to.clear();

			for (i=0;i<from.size();i++)
				to.add(new Process(from.get(i)));
			}


		
		public static double findVariance(ArrayList<Process> process_list,Time t)
		{
			int i;
			double mean=0,std_dev=0,var=0,d=0;
			for (i=0;i<process_list.size();i++)
				{
					mean+=process_list.get(i).getWaitingTime(t);
				}

			mean/=process_list.size();
                        
			for (i=0;i<process_list.size();i++)
			{
					d=mean-process_list.get(i).getWaitingTime(t);
                                        System.out.println("d="+d+" and i = "+i+" WT= "+process_list.get(i).getWaitingTime(t));
                                        d=Math.pow(d,2);
                 
					std_dev+=d;
			}
			std_dev=std_dev/process_list.size();
                        var=Math.sqrt(std_dev);
			return var;
		}

	public static void  execute_processes(ArrayList<Process> process_list, Time t)
		{
			int i,j;
			ArrayList<Process> new_process_list = new ArrayList<Process>(process_list.size());
			ArrayList<ArrayList<Process>> queues = new ArrayList<ArrayList<Process>>(7);
			for (i=0;i<7;i++)
			{
				queues.add(new ArrayList<Process>());	
			}	
			
			int count=0;
			while (process_list.size()!=count)
				{
					for (i=0;i<7;i++)
					{
						for (j=0;j<MAX_QUEUE_SIZE && process_list.size()!=count;j++)
						{
							queues.get(i).add((Process)process_list.get(count));
							count++ ;
						}
					}
					for (i=0;i<7;i++)
					{
						while (queues.get(i).size()!=0)
						{
							Process p = queues.get(i).remove(0);
							p.Execute(t);
						}
					}
				}
		}

	public static void main(String args[])
		{
			Random rand = new Random();
			Time t = new Time();  //stores time for process sorting according to some rule
			Time t_clone = new Time();  //stores the time for unsorted processes
			int i;

			
			//Make the processes.
			ArrayList<Process> process_list = new ArrayList<Process>(5);
			Process p;
			for (i=0;i<5;i++)
				{
					p = new Process(0,rand.nextInt(Process.MAXIMUM_BURST_TIME)+1);
					process_list.add(p);
				}

			ArrayList<Process> process_list_clone = new ArrayList<Process>();
			copy_process_list(process_list_clone,process_list);


			Collections.sort(process_list,new BurstTimeComparator());
			
			MinimizeTime.execute_processes(process_list,t);
			MinimizeTime.execute_processes(process_list_clone,t_clone);

			System.out.println(MinimizeTime.findVariance(process_list,t)+"\n"+ MinimizeTime.findVariance(process_list_clone,t_clone));
			
		}

}
