package project2;
import java.util.concurrent.Semaphore;
import java.util.LinkedList;
import java.util.Queue;

public class Process_synchronization {
	
	
	    private final int MAX_LEN = 10;
	    private static int money = 0;
	    private static Semaphore full=new Semaphore(10); 
	    private static Semaphore Null =new Semaphore(0); 
	    final Semaphore mutex = new Semaphore(1); //Global Semaphore
	    
	    class Worker extends Thread {
	        @Override
	        public void run() {
	            worker();
	        }
	        private void worker() {//print thread name
	        	 String name=Thread.currentThread().getName();
	             String subName[]=name.split("\\-");
	                int i=Integer.parseInt(subName[1]);
	                
	        		
	          if(i%2!=0)//odd one
	          { 
	              try {
	            addMoney(i); 
	           } catch (InterruptedException e) {
	            e.printStackTrace();
	           }
	          }
	          else//even one
	          {   
	           try {
	            reduceMoney(i); 
	           } catch (InterruptedException e) {
	            e.printStackTrace();
	           }
	          } 
	        }
	        		
	        }
	    

	    public void addMoney(int i) throws InterruptedException {
	    	try {
	      full.acquire();  //if is more than 10
	      mutex.acquire();//critical section
	      money++;
	      System.out.println(Thread.currentThread().getName()+" add 1 "+" now money is"+ money);
	      sleep(1000);
	    	} catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           
	      mutex.release();
	      Null.release();
	    }
	    }
	    
	    public void reduceMoney(int i) throws InterruptedException {
	    	try {
	      Null.acquire();  //if is lower than 0
	      mutex.acquire();//Critical section
	      money--;
	      System.out.println(Thread.currentThread().getName()+" reduce 1 "+" now money is"+ money);
	      sleep(1000);
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}finally {
	      mutex.release();
	      full.release();
	     }
	    }
	    
	    
	    private void sleep(int i) {
			// TODO Auto-generated method stub
			
		}

		public static void main(String[] args) {
	    	Process_synchronization pc = new Process_synchronization();
	    	for (int i = 0; i < 10; i++) {  
	       Worker worker = pc.new Worker();
	      
	       worker.start();
	    
	    	
		}
		}
}
	

