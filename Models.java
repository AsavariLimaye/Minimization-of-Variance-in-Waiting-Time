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
		int i,j;
		Collections.sort(process_list,new BurstTimeComparator2());
		ArrayList<Process> new_process_list = new ArrayList<Process>(process_list.size());

   		//Maintaining 7 user queues(0-6) for user processes where queue number 0 has the highest priority
		ArrayList<ArrayList<Process>> queues = new ArrayList<ArrayList<Process>>(7);

		for (i=0;i<7;i++)
		{
			queues.add(new ArrayList<Process>());	
		}	
			
		/*The processes which are sorted according to their burst time have to be added to the queues in the order to obtain a 				a V shaped curve with respect to burst time*/

		int count=0,qno=0,n,m=0;  //m keeps track of the number of processes in the current queue
		n=process_list.size();    

		System.out.println("n="+n);
		//while (process_list.size()!=count)
	//		{
				System.out.println("count="+count + "   qno="+qno);	
                 /*Adding alternate processes while traversing the sorted list from left to right*/

				for(count=0;count<n;count+=2)
				{               
					System.out.println("UP:count = " + count);
				queues.get(qno).add((Process)process_list.get(count));
				m++;							
				if(m>=20)  //current queue is full so move to the next one
                	{
                	qno++;
                	if (qno >= 7 ) {System.out.println("ERROR>"); return;}
					m=0;
					}
					
				}

				if(n%2!=0)   //Incase the total number of processes is odd
					n=n-1;
                                           

                           /*Adding alternate processes while traversing the sorted list from right to left*/

                for(count=n-1;count>=0;count-=2)
					{
						System.out.println("DOWN: count:" + count);
						queues.get(qno).add((Process)process_list.get(count));
						m++;
	
						if(m>=20)
      						{
							qno++;
							if (qno >= 7 ) {System.out.println("ERROR>"); return;}
							m=0;
							}
					}

                                      
					//Executing the processes in the queue moving from a high priority to low priority queue
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
		//}


	public static void  execute_DoubleVModel(ArrayList<Process> process_list, Time t)
		{
			Collections.sort(process_list,new BurstTimeComparator2());
		
				int i,j;
			
	
                        //Maintaining 7 user queues(0-6) for user processes where queue number 0 has the highest priority
			ArrayList<ArrayList<Process>> queues = new ArrayList<ArrayList<Process>>(7);

			for (i=0;i<7;i++)
			{
				queues.add(new ArrayList<Process>());	
			}	
			
			/*The processes which are sorted according to their burst time have to be added to the queues in the order to obtain a 				a V shaped curve with respect to burst time*/

			int count=0,qno=0,n,m=0,ctr=0;  //m keeps track of the number of processes in the current queue
			n=process_list.size();    

			
                                        /*Adding alternate processes while traversing the sorted list from left to right*/

					for(count=0;count<n;count+=4)
					{       
                                     		 
						queues.get(qno).add((Process)process_list.get(count));
						
						
						if((count+1)<n)
					         {	   
                                           		queues.get(qno).add((Process)process_list.get(count+1));
							
						 }

						m+=2;							
						

						if(m>=20)  //current queue is full so move to the next one
                                                 {
                                                       qno++;
							m=0;
						}
					}

					if(n%2==0 && count==n)      //test case : n=6
                                             count=n-3;
					else if(n%2==0 && count>n)  //test case : n=8
                                              count=n-1;
					else if(n%2!=0 && count==n)  //test case : n=5
						count=n-2;
					else if(n%2==0 && count>n)   //test case : n=7
					{
						queues.get(qno).add((Process)process_list.get(n-1));
						count=n-4;
					}

														

                                          
                                        /*Adding alternate processes while traversing the sorted list from right to left*/
	
                             		for(;count>=0;count-=4)
					{
						queues.get(qno).add((Process)process_list.get(count));
						if((count-1)>=0)
						   queues.get(qno).add((Process)process_list.get(count-1));
						m++;
	
						if(m>=20)
                                                 {
							qno++;
							m=0;
						}
				
					}

                                      
					//Executing the processes in the queue moving from a high priority to low priority queue
					for (i=0;i<7;i++)
					{        
						while (queues.get(i).size()!=0)
						{	
							Process p = queues.get(i).remove(0);
                                                    
							p.Execute(t);
						}
					}
				
		}

	public static void  execute_Spiral(ArrayList<Process> process_list, Time t)
		{
			System.out.println("\nTo be Implemented");
		}

	public static void main(String args[])
		{
			Random rand = new Random();

			
			Time t_unsorted = new Time();  //stores time for unsorted processes
			Time t_sjf = new Time();  //stores the time for sjf
			Time t_VModel = new Time(); //for VModel
			Time t_DoubleVModel = new Time(); //for double V model
			Time t_Spiral = new Time(); //for spiral model 


			int i;

			
			//Make the processes.
			ArrayList<Process> process_list = new ArrayList<Process>(100);
			Process p;
			for (i=0;i<100;i++)
				{
					p = new Process(0,rand.nextInt(Process.MAXIMUM_BURST_TIME)+1);
					process_list.add(p);
				}


			ArrayList<Process> process_list_sjf = new ArrayList<Process>();
			ArrayList<Process> process_list_VModel = new ArrayList<Process>();
			ArrayList<Process> process_list_DoubleVModel = new ArrayList<Process>();
			ArrayList<Process> process_list_Spiral = new ArrayList<Process>();

			copy_process_list(process_list_sjf,process_list);
			copy_process_list(process_list_VModel,process_list);
			copy_process_list(process_list_DoubleVModel,process_list);
			copy_process_list(process_list_Spiral,process_list);
				

			System.out.println("V Model..");
			Models.execute_VModel(process_list_VModel,t_VModel);
			System.out.println("\n\n");

			
			System.out.println("Unsorted..");
			Models.execute_unsorted(process_list,t_unsorted);
			System.out.println("\n\n");
			
			
			System.out.println("SJF..");
			Models.execute_SJF(process_list_sjf,t_sjf);
			System.out.println("\n\n");

			
			System.out.println("Spiral..");
			Models.execute_Spiral(process_list_Spiral,t_Spiral);
			System.out.println("\n\n");

			System.out.println("Double V Model..");
			Models.execute_DoubleVModel(process_list_DoubleVModel,t_DoubleVModel);
			System.out.println("\n\n");


			System.out.println("Variance:\n\nUnsorted:\n"+Models.findVariance(process_list,t_unsorted)+"\n\n\n");
			System.out.println("Shortest Job Next:\n"+Models.findVariance(process_list_sjf,t_sjf)+"\n\n\n");
			System.out.println("V Model:\n"+Models.findVariance(process_list_VModel,t_VModel)+"\n\n\n");
			System.out.println("Double V Model:\n"+Models.findVariance(process_list_DoubleVModel,t_DoubleVModel)+"\n\n\n");
			System.out.println("Spiral Model:\n"+Models.findVariance(process_list_Spiral,t_Spiral)+"\n\n\n");
		}

}
