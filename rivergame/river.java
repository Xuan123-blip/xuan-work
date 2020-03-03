package game;

import javax.swing.*;
import javax.swing.ImageIcon;

import work.Rivercross;
import work.Rivercross.CountingThread;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class river {

	//frame
	                                             
      
		private int r;
		private int c;
		private int[] Man= {0,0};
		private int length=0;
		private int[][] ma=new int[13][9];


		
		ImageIcon bank1 = new ImageIcon("picture\\bank1.jpg");
		ImageIcon bank2 = new ImageIcon("picture\\bank2.jpg");
		ImageIcon plank1 = new ImageIcon("picture\\plank1.jpg");
		ImageIcon plank1_man = new ImageIcon("picture\\plank1_man.jpg");
		ImageIcon plank2 = new ImageIcon("picture\\plank2.jpg");
		ImageIcon plank2_man = new ImageIcon("picture\\plank2_man.jpg");
		ImageIcon stump1 = new ImageIcon("picture\\stump1.jpg");
		ImageIcon stump1_man = new ImageIcon("picture\\stump1_man.jpg");
		ImageIcon stump2 = new ImageIcon("picture\\stump2.jpg");
		ImageIcon stump2_man = new ImageIcon("picture\\stump2_man.jpg");
		ImageIcon stump3 = new ImageIcon("picture\\stump3.jpg");
		ImageIcon stump3_man = new ImageIcon("picture\\stump3_man.jpg");
		ImageIcon water1 = new ImageIcon("picture\\water1.jpg");
		

		
		
		
		
		
		JFrame a = new JFrame();
		JPanel centerPane = new JPanel();
		JButton level1= new JButton("1");
		JButton level2= new JButton("2");
		JButton level3= new JButton("3");
		JButton[][] button = new JButton[13][9];

		
		
		
		JPanel time = new JPanel(new BorderLayout());
	    private long timerStart = System.currentTimeMillis();
	    private long timeUsed = 0;
	    JLabel timerlabel = new JLabel("00:00");
	    
	    private int minute, second;
		
	    /**
	     * This is the class to control the timer which uses thread.
	     */
	    private class Mytimer extends Thread {
	        public boolean stop = false; 
	        private Mytimer() {    
	            setDaemon(true);    
	        }
	        @Override    
	        public void run() {    
	            while (true) {    
	                if (!stop) {    
	                    timeUsed = System.currentTimeMillis() - timerStart;    
	                    timerlabel.setText("TIME: "+format(timeUsed));
	                    
	                }
	                try {    
	                    sleep(1);  //Update every one millisecond  
	                } 
	                catch (InterruptedException e) {    
	                    e.printStackTrace();    
	                    System.exit(1);    
	                }    
	            }    
	        }    
	     
	        /**
	         * This is a method to format the timer.    
	         * @param timeUsed Time has been used. Counting in millisecond. 
	         * @return Time has been formatted. 
	         */
	        private String format(long timeUsed) {
	            timeUsed = timeUsed / 1000;    
	            second = (int) (timeUsed % 60);
	            timeUsed = timeUsed / 60;    
	            minute = (int) (timeUsed % 60);
	        
	            return String.format("%02d:%02d", minute, second);    
	        }    
	    }
		
		
		
		
		
		
		
		public void begin(){

			
			
			bank1 = new ImageIcon(bank1.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			bank2 = new ImageIcon(bank2.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			plank1 = new ImageIcon(plank1.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			plank1_man = new ImageIcon(plank1_man.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			plank2 = new ImageIcon(plank2.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			plank2_man = new ImageIcon(plank2_man.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			stump1 = new ImageIcon(stump1.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			stump1_man = new ImageIcon(stump1_man.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			stump2 = new ImageIcon(stump2.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			stump2_man = new ImageIcon(stump2_man.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			stump3 = new ImageIcon(stump3.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			stump3_man = new ImageIcon(stump3_man.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			water1 = new ImageIcon(water1.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			
			
			
			
			
			
			
		centerPane.setSize(400, 400);
		centerPane.setLayout(new GridLayout(13, 9));
		a.add(centerPane,BorderLayout.CENTER);
	
		
		
		for(r=0;r<13;r++){
			for(c=0;c<9;c++){
				
								if(r==0){
	               
					button[r][c] = new JButton(bank2);
					button[r][c].setIcon(bank2);
				}
				else if(r==12){
	                                                                             //brackground
					button[r][c] = new JButton(bank1);
					button[r][c].setIcon(bank1);
				}
				else{
	             
					button[r][c] = new JButton(water1);
					button[r][c].setIcon(water1);
				}
				button[r][c].setBorderPainted(false);
				centerPane.add(button[r][c]);
			
		    
			}	
		}//for over
		
		JPanel downPane =new JPanel();
		downPane.setSize(100,400);
		downPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		downPane.setBackground(Color.green);
		a.add(downPane, BorderLayout.SOUTH);
		JLabel method = new JLabel("Click stump to move or place plank;Click plank near man to pick up");
		method.setFont(new java.awt.Font("Dialog", 1, 12));
		method.setForeground(Color.black);
		downPane.add(method);
		
		JPanel upPane = new JPanel();
		upPane.setSize(600, 100);
		upPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		upPane.setBackground(Color.gray);
		a.add(upPane, BorderLayout.NORTH);
		JLabel choice = new JLabel("choose level ");
		choice.setFont(new java.awt.Font("Dialog", 1, 14));
		choice.setForeground(Color.white);
	
		upPane.add(choice);
		
		upPane.add(level1);
		upPane.add(level2);
		upPane.add(level3);
		
		time.setSize(300, 100);
		time.setBackground(Color.gray);
		Mytimer thread = new Mytimer();
		timerlabel.setFont(new java.awt.Font("Dialog", 1, 20));
        time.add(timerlabel);
		upPane.add(time);
      //Start to time. 
		
		
		
		
		    a.setTitle("RIVER CROSSING: The Perilous Plank Puzzle Conundrum");
			a.setBounds(650, 150, 460,730);
			a.setResizable(false);
			a.setVisible(true);
			a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			for(int i=0;i<13;i++) {
				for(int j=0;j<9;j++) {
					 button[i][j].addMouseListener(new MouseAdapter(){
						 int X=0,Y=0;
						 boolean next=true;
						 boolean Allwater=false;
							public void mouseClicked(MouseEvent e) {
								next=true;
								Allwater=false;
				 for(int k=0;k<13;k++) {
					 for(int l=0;l<9;l++) {
						 if(e.getSource()==button[k][l]) {
							 X=k;
							 Y=l;
							            //get the click place
						 }
					 }
				 }
				
				if(ma[X][Y]==3||ma[X][Y]==6||ma[X][Y]==5) {
						if(X-Man[0]==0) {               
						if(Y>Man[1]) {                  //right
							for(int k=1;k<Y-Man[1];k++) {
								if(k+Man[1]>8||ma[X][Man[1]+k]!=2) {     
 									next=false;
									break;
								}
						}
						if(next==false) {
							for(int k=1;k<Y-Man[1];k++) {
								if(k+Man[1]>8||ma[X][k+Man[1]]!=0) {
									Allwater=false;
									break;
								}
							Allwater=true;
							}
						}
							
							
							if(next==true&&Allwater==false) {
								if(ma[Man[0]][Man[1]]!=5&&ma[X][Y]!=6) {
									button[X][Y].setIcon(stump1_man);
									button[Man[0]][Man[1]].setIcon(stump1);
									Man[0]=X;
									Man[1]=Y;
									}
									else if(ma[Man[0]][Man[1]]==5){
										button[X][Y].setIcon(stump1_man);
										button[Man[0]][Man[1]].setIcon(stump2);
										Man[0]=X;
										Man[1]=Y;
									}
									else if(ma[X][Y]==6) {
										button[X][Y].setIcon(stump3_man);
										button[Man[0]][Man[1]].setIcon(stump1);
										Man[0]=X;
										Man[1]=Y;
									}
							}
						if(next==false&&Allwater==true&&length==Y-Man[1]-1) {
							for(int k=1;k<Y-Man[1];k++) {
								ma[X][k+Man[1]]=2;
								button[X][k+Man[1]].setIcon(plank1);
							}
						length=0;
						}
						}
					
						if(Y<Man[1]) {                   //left
						for(int k=1;k<Man[1]-Y;k++) {
							if(Man[1]-k<0||ma[X][Man[1]-k]!=2) {
								next=false;
								break;
							}
						}
						
						if(next==false) {
							for(int k=1;k<Man[1]-Y;k++) {
								if(Man[1]-k<0||ma[X][Man[1]-k]!=0) {
						           Allwater=false;
						           break;
								}
							Allwater=true;
							}
						}
						
						if(next==true&&Allwater==false) {
							if(ma[Man[0]][Man[1]]!=5&&ma[X][Y]!=6) {
								button[X][Y].setIcon(stump1_man);
								button[Man[0]][Man[1]].setIcon(stump1);
								Man[0]=X;
								Man[1]=Y;
								}
								else if(ma[Man[0]][Man[1]]==5){
									button[X][Y].setIcon(stump1_man);
									button[Man[0]][Man[1]].setIcon(stump2);
									Man[0]=X;
									Man[1]=Y;
								}
								else if(ma[X][Y]==6) {
									button[X][Y].setIcon(stump3_man);
									button[Man[0]][Man[1]].setIcon(stump1);
									Man[0]=X;
									Man[1]=Y;
								}
						}
						if(next==false&&Allwater==true&&length==Man[1]-Y-1) {
							for(int k=1;k<Man[1]-Y;k++) {
							ma[X][Man[1]-k]=2;
							button[X][Man[1]-k].setIcon(plank1);
							}
							length=0;
						}
							
						}
						}
						
						
						
						
					
						if(Y-Man[1]==0) {              
							if(Man[0]>X) {               //up
								for(int k=1;k<Man[0]-X;k++) {
									System.out.println("zzzz"+ma[Man[0]-k][Y]);
									if(Man[0]-k<0||ma[Man[0]-k][Y]!=1) {        
										next=false;
										break;
									}
							}
								
								if(next==false) {                             //if all water
								for(int k=1;k<Man[0]-X;k++) {
									if(Man[0]-k<0||ma[Man[0]-k][Y]!=0) {    
										Allwater=false;
										break;
									}
									Allwater=true;
								}
								}
								if(next==true&&Allwater==false) {                   //all plank
									if(ma[Man[0]][Man[1]]!=5&&ma[X][Y]!=6) {
										button[X][Y].setIcon(stump1_man);
										button[Man[0]][Man[1]].setIcon(stump1);
										Man[0]=X;
										Man[1]=Y;
										}
										else if(ma[Man[0]][Man[1]]==5){
											button[X][Y].setIcon(stump1_man);
											button[Man[0]][Man[1]].setIcon(stump2);
											Man[0]=X;
											Man[1]=Y;
										}
										else if(ma[X][Y]==6) {
											button[X][Y].setIcon(stump3_man);
											button[Man[0]][Man[1]].setIcon(stump1);
											Man[0]=X;
											Man[1]=Y;
											thread.stop=true;
										
										}
								}
								
								if(next==false&&Allwater==true&&length==Man[0]-X-1) {  //all water and length same
									for(int k=1;k<Man[0]-X;k++) {
										ma[Man[0]-k][Y]=1;
												button[Man[0]-k][Y].setIcon(plank2);
										
									}
									length=0;
								}
							}
							
							if(Man[0]<X) {          //down
								for(int k=1;k<X-Man[0];k++) {
									if(k+Man[0]>12||ma[k+Man[0]][Y]!=1) {
										next=false;
										break;
									}
								}
							if(next==false) {
								for(int k=1;k<X-Man[0];k++) {
									if(k+Man[0]>12||ma[k+Man[0]][Y]!=0) {
										Allwater=false;
										break;
									}
									Allwater=true;
								}
								
							}
							if(next==true&&Allwater==false) {                   //all plank
								if(ma[Man[0]][Man[1]]!=5&&ma[X][Y]!=6) {
									button[X][Y].setIcon(stump1_man);
									button[Man[0]][Man[1]].setIcon(stump1);
									Man[0]=X;
									Man[1]=Y;
									}
									else if(ma[Man[0]][Man[1]]==5){
										button[X][Y].setIcon(stump1_man);
										button[Man[0]][Man[1]].setIcon(stump2);
										Man[0]=X;
										Man[1]=Y;
									}
									else if(ma[X][Y]==6) {
										button[X][Y].setIcon(stump3_man);
										button[Man[0]][Man[1]].setIcon(stump1);
										Man[0]=X;
										Man[1]=Y;
									}
							}
							if(next==false&&Allwater==true&&length==X-Man[0]-1) {  //all water length same put plank
								for(int k=1;k<X-Man[0];k++) {
									ma[k+Man[0]][Y]=1;
											button[k+Man[0]][Y].setIcon(plank2);
									
								}
								length=0;
							}
							
							
							}
							
	
							}
				}
					
				 
				 
				 
				 
				 
				 
				 
					 
				if(ma[X][Y]==1||ma[X][Y]==2) {
					if(Y==Man[1]&&Man[0]-X==1&&length==0) {    //up  pick up plank
							for(int k=0;k<3;k++) {
								if(ma[X-k][Y]!=1) {
									break;
								}
								length++;
								button[X-k][Y].setIcon(water1);
								ma[X-k][Y]=0;
							}
					}
		             if(Y==Man[1]&&X-Man[0]==1&&length==0) {    //down
			             for(int k=0;k<3;k++) {
			            	 if(ma[X+k][Y]!=1) {
			            		 break;
			            	 }
			             length++;
			             button[X+k][Y].setIcon(water1);
			             ma[X+k][Y]=0;
			             
			             }
		            }
				System.out.println(Man[1]+"   "+Y+"  "+Man[0]);
				   if(X==Man[0]&&Y-Man[1]==1&&length==0) {       //right
					   for(int k=0;k<3;k++) {
						   if(ma[X][Y+k]!=2) {
							   break;
						   }
					    length++;
					    button[X][Y+k].setIcon(water1);
					    ma[X][Y+k]=0;
					   }
					   
				   }
				
				if(X==Man[0]&&Man[1]-Y==1&&length==0) {           //left
					for(int k=0;k<3;k++) {
						if(ma[X][Y-k]!=2) {
							break;
						}
					length++;
					button[X][Y-k].setIcon(water1);
					ma[X][Y-k]=0;
					}
				}
				
				}
			
							}
					
				});	
			
			
			
			
			
			
			
			
	
	
	
				}
		}
                                                       
			level1.addActionListener(new ActionListener() {

				//@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub  
					length=0;
				thread.start();

				
					int [][] ma1 = {
							{8,8,8,8,6,8,8,8,8},
							{0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0},
							{0,0,0,0,3,0,0,0,3},
							{0,0,0,0,0,0,0,0,1},
							{0,0,0,0,3,0,0,0,3},
							{0,0,0,0,0,0,0,0,1},
							{0,0,0,0,0,0,0,0,1},
							{0,0,0,0,3,0,0,0,3},
							{0,0,0,0,1,0,0,0,0},
							{0,0,0,0,1,0,0,0,0},
							{0,0,0,0,1,0,0,0,0},
							{9,9,9,9,5,9,9,9,9},
							
							
					};

					ma=ma1;
					for(int i=0;i<13;i++) {
						for(int j=0;j<9;j++) {
						if(ma[i][j]==0) {
							button[i][j].setIcon(water1);
						}
							if(ma[i][j]==6) {
								button[i][j].setIcon(stump3);
							}
							if(ma[i][j]==1) {
								button[i][j].setIcon(plank2);
							}
							if(ma[i][j]==3) {
								button[i][j].setIcon(stump1);
							}
							if(ma[i][j]==5) {
								button[i][j].setIcon(stump2_man);
								Man[0]=i;
								Man[1]=j;
							}
							
							if(ma[i][j]==8) {
								button[i][j].setIcon(bank2);
							
							}
						
							if(ma[i][j]==9) {
								button[i][j].setIcon(bank1);
							}
							
						}
					}
					
					
					//event
					
			
			
					
					
					
				}
				});
    	 
          

			
			
			level2.addActionListener(new ActionListener() {

           //@Override
             public void actionPerformed(ActionEvent e) {
             // TODO Auto-generated method stub  
            	 length=0;
		
            	 timeUsed=0;
					Mytimer t2 = new Mytimer();
					t2.start();
            	 
            	 
            		int [][] ma2 = {
							{8,8,8,8,6,8,8,8,8},
							{0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0},
							{0,0,0,0,3,0,0,0,0},
							{0,0,0,0,0,0,0,0,0},
							{0,3,0,0,3,2,3,0,0},
							{0,1,0,0,0,0,0,0,0},
							{0,1,0,0,0,0,0,0,0},
							{0,1,0,0,0,0,0,0,0},
							{0,3,0,0,3,0,0,0,0},
							{0,0,0,0,1,0,0,0,0},
							{0,0,0,0,1,0,0,0,0},
							{9,9,9,9,5,9,9,9,9},
							
							
					};
            		ma=ma2;
            		for(int i=0;i<13;i++) {
						for(int j=0;j<9;j++) {
						if(ma[i][j]==0) {
							button[i][j].setIcon(water1);
						}
							if(ma[i][j]==6) {
								button[i][j].setIcon(stump3);
							}
							if(ma[i][j]==1) {
								button[i][j].setIcon(plank2);
							}
							if(ma[i][j]==3) {
								button[i][j].setIcon(stump1);
							}
							if(ma[i][j]==5) {
								button[i][j].setIcon(stump2_man);

								Man[0]=i;
								Man[1]=j;
							}
							
							if(ma[i][j]==8) {
								button[i][j].setIcon(bank2);
							
							}
						
							if(ma[i][j]==9) {
								button[i][j].setIcon(bank1);
							}
							
							if(ma[i][j]==2) {
								button[i][j].setIcon(plank1);
							}
						
						}
					}
					
             
             
             }	
		                                                          
		});
	
		
			
			level3.addActionListener(new ActionListener() {

           //@Override
             public void actionPerformed(ActionEvent e) {
             // TODO Auto-generated method stub  
          length=0;
			
          Mytimer t3 = new Mytimer();
			t3.start();
         		int [][] ma3 = {
							{8,8,8,8,6,8,8,8,8},
							{0,0,0,0,0,0,0,0,0},
							{3,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0},
							{0,0,0,0,3,0,0,0,0},
							{0,0,0,0,0,0,0,0,0},
							{3,0,3,2,2,2,3,0,0},
							{0,0,0,0,0,0,0,0,0},
							{3,0,0,0,3,0,0,0,0},
							{1,0,0,0,1,0,0,0,0},
							{3,0,0,0,1,0,0,0,0},
							{0,0,0,0,1,0,0,0,0},
							{9,9,9,9,5,9,9,9,9},
							
							
					};
             
                 ma=ma3;
         		for(int i=0;i<13;i++) {
					for(int j=0;j<9;j++) {
					if(ma[i][j]==0) {
						button[i][j].setIcon(water1);
					}
						if(ma[i][j]==6) {
							button[i][j].setIcon(stump3);
						}
						if(ma[i][j]==1) {
							button[i][j].setIcon(plank2);
						}
						if(ma[i][j]==3) {
							button[i][j].setIcon(stump1);
						}
						if(ma[i][j]==5) {
							button[i][j].setIcon(stump2_man);
							Man[0]=i;
							Man[1]=j;
						}
						
						if(ma[i][j]==8) {
							button[i][j].setIcon(bank2);
						
						}
					
						if(ma[i][j]==9) {
							button[i][j].setIcon(bank1);
						}
						if(ma[i][j]==2) {
							button[i][j].setIcon(plank1);
						}
				
					
					}
				}
				 
             
             }
			});
			
		
		
		}
	                                         	                         	
		                                                              
	public static void main(String[] args) {
		river river = new river();
		river.begin();
	
       }

	}
