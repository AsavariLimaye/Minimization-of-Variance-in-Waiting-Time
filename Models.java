import java.util.*;
import java.lang.*;


class Models
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
 
                                        d=Math.pow(d,2);
                 
					std_dev+=d;
			}
			std_dev=std_dev/process_list.size();
                        var=Math.sqrt(std_dev);
			return var;
		}

	public static void  execute_unsorted(ArrayList<Process> process_list, Time t)
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
							System.out.println(p.getBurstTime());
						}
					}
				}
		}	


	public static void execute_SJF(ArrayList<Process> process_list,Time t)
		{
				Collections.sort(process_list,new BurstTimeComparator());
				execute_unsorted(process_list,t);
		}

	public static void  execute_VModel(ArrayList<Process> process_list, Time t)
		{
			/*int i,j;
			ArrayList<Process> new_process_list = new ArrayList<Process>(process_list.size());

                        //Maintaining 7 user queues(0-6) for user processes where queue number 0 has the highest priority
			ArrayList<ArrayList<Process>> queues = new ArrayList<ArrayList<Process>>(7);

			for (i=0;i<7;i++)
			{
				queues.add(new ArrayList<Process>());	
			}	
			
			/*The processes which are sorted according to their burst time have to be added to the queues in the order 
                          0,6,1,5,2,,4,3,0,6,1..... to obtain a V shaped curve with respect to burst time*/

                          /*
			int count=0,ctr=6,qno=0,f=0;  //Used to generate the sequnce of queue numbers

			while (process_list.size()!=count)
				{
				
						/*In this case 1 process has been added to all the 7 queues.Re-initialize values
                                                  to re-generate the same sequene of queue numbers again*/

					/*	if(qno==3)
                                                { 
							ctr=6;
							qno=0;
							f=0;
						}
 						

						if(queues.get(qno).size()<MAX_QUEUE_SIZE)
						{
							queues.get(qno).add((Process)process_list.get(count));
							count++ ;
						}

                                                
						//Sequence generating logic 
						if(f==0)
						  qno=qno+ctr;
						else 
   						  qno=qno-ctr;

						ctr=ctr-1;
                                                if(f==1)
                                                  f=0;
                                                else
                                                  f=1;
						     
					
					for (i=0;i<7;i++)
					{
						while (queues.get(i).size()!=0)
						{
							Process p = queues.get(i).remove(0);
							p.Execute(t);
							System.out.println(p.getBurstTime());
						}
					}
				}
				*/
				Collections.sort(process_list,new BurstTimeComparator2());
				execute_unsorted(process_list,t);

		}



	public static void main(String args[])
		{
			Random rand = new Random();
			Time t = new Time();  //stores time for process sorting according to some rule
			Time t_clone = new Time();  //stores the time for unsorted processes
			Time t_clone_2 = new Time();
			int i;

			
			//Make the processes.
			ArrayList<Process> process_list = new ArrayList<Process>(50);
			Process p;
			for (i=0;i<50;i++)
				{
					p = new Process(0,rand.nextInt(Process.MAXIMUM_BURST_TIME)+1);
					process_list.add(p);
				}

			ArrayList<Process> process_list_clone = new ArrayList<Process>();
			ArrayList<Process> process_list_clone_2 = new ArrayList<Process>();
			copy_process_list(process_list_clone,process_list);
			copy_process_list(process_list_clone_2,process_list);
				

			
			
			Models.execute_VModel(process_list,t);
			System.out.println("\n\n");
			Models.execute_unsorted(process_list_clone,t_clone);

			System.out.println("\n\n");
			Models.execute_SJF(process_list_clone_2,t_clone_2);

			System.out.println(Models.findVariance(process_list,t)+"\n\n\n"+ Models.findVariance(process_list_clone,t_clone)+"\n\n\n"+Models.findVariance(process_list_clone_2,t_clone_2));
			
		}

}
