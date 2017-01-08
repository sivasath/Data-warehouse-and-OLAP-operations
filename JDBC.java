package latest;
import java.lang.Math;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.* ;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.math3.TestUtils;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.stat.inference.OneWayAnova;
import org.apache.commons.math3.stat.inference.TTest; 
public class JDBC{
	
	public static double mean(double[] arr_with_all) {
        int N = arr_with_all.length;
        double sum = 0.0;
        for (int i = 0; i < N; i++) {
            sum =sum+ arr_with_all[i];
        }
        return sum / N;
    }
	

	
	 public static double variance(double[] x) {
	        int N = x.length;
	        double mu = mean(x);
	        double sum = 0.0;
	        for (int i = 0; i < N; i++) 
	        {
	            double delta = x[i] - mu;
	            sum =sum+ delta * delta;
	        }
	        return sum / (N-1);
	    }
	 public static double tstat(double[] x, double[] y) {
	        int n1 = x.length;
	        int n2 = y.length;
	  
	        // means
	        double mu1 = mean(x);
	        double mu2 = mean(y);

	        // variances
	        double var1 = variance(x);
	        double var2 = variance(y);
            double intermediate=(var1/n1)+ (var2/n2);
	        // t-statistic
            double muz=mu1 - mu2;
            double sqrt=Math.sqrt(intermediate);
	        return (muz / sqrt);
	    }
	
    public static void main(String args[]) throws SQLException{
        String URL = "**************";
        String USER = "*******";
        String PASS = "********";

        try {
              Class.forName("oracle.jdbc.driver.OracleDriver");
              
              Connection conn = DriverManager.getConnection(URL, USER, PASS);
              
              final Statement stmt=conn.createStatement();  
              
              //GUI
              
                final JFrame jf=new JFrame();
	      		jf.setTitle("Oracle GUI");
	      		jf.setLocationRelativeTo(null);
	      		jf.setSize(600, 600);
	      		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      		Container content = jf.getContentPane(); 
	      		final JPanel jp=new JPanel(new GridLayout(8, 3,30,20));
	      			
      		
      		     String str="Query 1.1";
              	 final JLabel jl=new JLabel(str,JLabel.TRAILING);
              	 final JTextField tf=new JTextField(10);
              	 jl.setLabelFor(tf);  
              	 jp.add(jl);
              	 jp.add(tf);
              	 JButton button = null;
              	 content.add(button = new JButton(" Q1.1 " ));
              	 jp.add(button);
              	       	 
              	 button.addActionListener(new ActionListener()
              	    {        	  
      				@Override
      				public void actionPerformed(ActionEvent e) {
      					// TODO Auto-generated method stub
      					Thread t = new Thread(new Runnable() {
      				        @Override
      				        public void run() {
      				        	 int ans = 0;
      			            try {
      			            	    ResultSet q1=stmt.executeQuery("SELECT COUNT(P_ID) FROM CLINICAL_FACT WHERE DS_ID IN (SELECT DS_ID FROM DISEASE WHERE DESCRIPTION='tumor')");
            			           while(q1.next())
            			           {
									ans=Integer.parseInt(q1.getString(1));
            			           }
								} catch (NumberFormatException | SQLException e) 
      			                {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} 
      			         
      			            jl.setText(String.valueOf(ans));     				       
      				        }     
      				    });
      				    t.start();					
      				}
              	    });
              	 
              	 
              	 
              	 String str2="Query 1.2";
              	 final JLabel jl2=new JLabel(str2,JLabel.TRAILING);
              	 final JTextField tf2=new JTextField(10);
              	 jl2.setLabelFor(tf2);  
              	 jp.add(jl2);
              	 jp.add(tf2);
              	 JButton button2 = null;
              	 content.add(button2= new JButton("Q 1.2" ));
              	 jp.add(button2);
              	 
              	 button2.addActionListener(new ActionListener()
              	    {        	  
      				@Override
      				public void actionPerformed(ActionEvent e) {
      					// TODO Auto-generated method stub
      					Thread t = new Thread(new Runnable() {
      				        @Override
      				        public void run() {
      				        	 int ans = 0;
      			            try {
      			            	    ResultSet q1=stmt.executeQuery("SELECT COUNT(P_ID) FROM CLINICAL_FACT WHERE DS_ID IN (SELECT DS_ID FROM DISEASE WHERE DISEASE.TYPE='leukemia')");
            			           while(q1.next())
            			           {
									ans=Integer.parseInt(q1.getString(1));
            			           }
								} catch (NumberFormatException | SQLException e) 
      			                {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} 
      			         
      			            jl2.setText(String.valueOf(ans));     				       
      				        }     
      				    });
      				    t.start();					
      				}
              	    });
              	 
              	 
              	 String str3="Query 1.3";
              	 final JLabel jl3=new JLabel(str3,JLabel.TRAILING);
              	 final JTextField tf3=new JTextField(10);
              	 jl3.setLabelFor(tf3);  
              	 jp.add(jl3);
              	 jp.add(tf3);
              	 JButton button3 = null;
              	 content.add(button3= new JButton("Q 1.3" ));
              	 jp.add(button3);
              	       	 
            	 button3.addActionListener(new ActionListener()
           	    {        	  
   				@Override
   				public void actionPerformed(ActionEvent e) {
   					// TODO Auto-generated method stub
   					Thread t = new Thread(new Runnable() {
   				        @Override
   				        public void run() {
   				        	 int ans = 0;
   			            try {
   			            	    ResultSet q1=stmt.executeQuery("SELECT COUNT(P_ID) FROM CLINICAL_FACT WHERE DS_ID IN (SELECT DS_ID FROM DISEASE WHERE DISEASE.NAME='ALL')");

         			           while(q1.next())
         			           {
									ans=Integer.parseInt(q1.getString(1));
         			           }
								} catch (NumberFormatException | SQLException e) 
   			                {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} 
   			         
   			            jl3.setText(String.valueOf(ans));     				       
   				        }     
   				    });
   				    t.start();					
   				}
           	    });
           	 
            	 
            	 
            	 
            	 String str22="Query 2";
              	 final JLabel jl22=new JLabel(str22,JLabel.TRAILING);
              	 final JTextField tf22=new JTextField(10);
              	 jl22.setLabelFor(tf22);  
              	 jp.add(jl22);
              	 jp.add(tf22);
              	 JButton button22 = null;
              	 content.add(button22= new JButton("Q 2" ));
              	 jp.add(button22);
              	       	 
            	 button22.addActionListener(new ActionListener()
           	    {        	  
   				@Override
   				public void actionPerformed(ActionEvent e) {
   					// TODO Auto-generated method stub
   					Thread t = new Thread(new Runnable() {
   				        @Override
   				        public void run() {
   				        	try {
   			            	    ResultSet q1=stmt.executeQuery("SELECT Count(TYPE) FROM DRUG WHERE DR_ID IN(SELECT DR_ID FROM CLINICAL_FACT WHERE DS_ID IN (SELECT DS_ID FROM DISEASE WHERE DESCRIPTION='tumor'))");

         			           while(q1.next())
         			           {
									System.out.println(q1.getString(1));
									 jl22.setText(q1.getString(1)); 
									
         			           }
         			         
								} catch (NumberFormatException | SQLException e) 
   			                    {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} 
   			       
   			                 				       
   				        }     
   				    });
   				    t.start();					
   				}
           	    });
           	 

	              jf.getContentPane().add(jp); 
	              jf.setVisible(true);

            	 

            	 String str4="Query 3";
              	 final JLabel jl4=new JLabel(str4,JLabel.TRAILING);
              	 final JTextField tf4=new JTextField(10);
              	 jl3.setLabelFor(tf4);  
              	 jp.add(jl4);
              	 jp.add(tf4);
              	 JButton button4 = null;
              	 content.add(button4= new JButton("Q 3" ));
              	 jp.add(button4);
              	       	 
            	 button4.addActionListener(new ActionListener()
           	    {        	  
   				@Override
   				public void actionPerformed(ActionEvent e) {
   					// TODO Auto-generated method stub
   					Thread t = new Thread(new Runnable() {
   				        @Override
   				        public void run() {
   				        	try {
   			            	    ResultSet q1=stmt.executeQuery("SELECT COUNT(EXP) FROM MICROARRAY_FACT WHERE MICROARRAY_FACT.MU_ID='1' AND MICROARRAY_FACT.S_ID IN (select s_id from CLINICAL_FACT where ds_id in (select ds_id from disease where NAME='ALL')) AND MICROARRAY_FACT.PB_ID IN (select pb_id from probe join gene_fact on uid_probe=UID_GENEFACT where cl_id ='2' )");

         			           while(q1.next())
         			           {
									System.out.println(q1.getString(1));
									jl4.setText(q1.getString(1)); 
         			           }
								} catch (NumberFormatException | SQLException e) 
   			                    {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} 
   			       
   			                 				       
   				        }     
   				    });
   				    t.start();					
   				}
           	    });
           	 

              jf.getContentPane().add(jp); 
              jf.setVisible(true);
       
         	 String str5="T_stats";
          	 final JLabel jl5=new JLabel(str5,JLabel.TRAILING);
          	 final JTextField tf5=new JTextField(10);
          	 jl3.setLabelFor(tf5);  
          	 jp.add(jl5);
          	 jp.add(tf5);
          	 JButton button5 = null;
          	 content.add(button5= new JButton("T_stats"));
          	 jp.add(button5);
          	       	 
        	 button5.addActionListener(new ActionListener()
       	    {        	  
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Thread tt = new Thread(new Runnable() {
				        @Override
				        public void run() {
				        	
				        	 try {
ResultSet rs_tstat=stmt.executeQuery("select count(*) from dnotall_final2");
				              int count_withoutall=0;
				          //  while(rs_tstat!=null)  {
				              while(rs_tstat.next())  
				              {
				            	  count_withoutall=rs_tstat.getInt(1);
				            	  
				            	 System.out.println("count withoutall:"+count_withoutall);  
				            	
				              }
				         // }
				              System.out.println("intermediate debug");
 ResultSet rs_tstat_entry=stmt.executeQuery("select exp from dnotall_final2");
				              //get it into array
				              System.out.println("count withoutall:"+count_withoutall); 
				              double[] arr_without_all=new double[count_withoutall];
				              int i=0;
				              while(rs_tstat_entry.next())
				              {
				            	  arr_without_all[i]=Integer.parseInt(rs_tstat_entry.getString(1));
				            	  i++;
				              }
				            System.out.println("total number of entires arr_without_all :"+arr_without_all.length);
				           
				            System.out.println("with_ALL begins**************"); 
				             //withall
 ResultSet rs_tstat2=stmt.executeQuery("select count(*) from dallfinal3");
				             int count_withall=0;
				            // while(rs_tstat2!=null)
				             {
				             while(rs_tstat2.next())  
				             {
				           	  count_withall=rs_tstat2.getInt(1);
				              System.out.println("count  withallnew 2 :"+count_withall);  
				          	 
				             }
				             }
				             
				             ResultSet rs_tstat_entry2=stmt.executeQuery("select exp from dallfinal3");
				             //get it into array
				             double[] arr_with_all=new double[count_withall];
				             int i2=0;
				            
								while(rs_tstat_entry2.next())
								 {
								  arr_with_all[i2]=Integer.parseInt(rs_tstat_entry2.getString(1));
								  i2++;
								 }
															
								TTest t1=new TTest();
								double tfinal=t1.homoscedasticT(arr_with_all, arr_without_all);
								System.out.println("tfinal::tstats--->>>"+tfinal);
								//double dny_pval=	t1.pairedTTest(arr_with_all, arr_without_all);
								//dimensions mismatch
								//System.out.println("dny_pval::--->>>"+dny_pval);
								double result1 = t1.tTest(arr_with_all, arr_without_all);
								System.out.printf("p-value result1::--->>>%.1f",result1);
								System.out.println("\n");						
							    
							
								System.out.println("size with_all:"+(arr_with_all.length));
								System.out.println("size without_all:"+(arr_without_all.length));
														
								
								
								
							
								jl5.setText(String.valueOf(tfinal)); 
							} catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							                 				       
				        }     
				    });
				    tt.start();					
				}
       	    });
          jf.getContentPane().add(jp); 
          jf.setVisible(true);

     	 String str6="F-Stats";
      	 final JLabel jl6=new JLabel(str6,JLabel.TRAILING);
      	 final JTextField tf6=new JTextField(10);
      	 jl6.setLabelFor(tf6);  
      	 jp.add(jl6);
      	 jp.add(tf6);
      	 JButton button6 = null;
      	 content.add(button6= new JButton("F-Stats"));
      	 jp.add(button6);
      	   
    	 button6.addActionListener(new ActionListener()
    	    {        	  
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Thread t = new Thread(new Runnable() {
			        @Override
			        public void run() {
			        	try {
		            	    ResultSet q1=stmt.executeQuery("select count(*) from all_q5_final");
                               int all_count=0;
                             
		  			           while(q1.next())
		  			           {
		  			        	        all_count=q1.getInt(1);
										System.out.println("count all:"+q1.getInt(1));
										
		  			           }
		  			         double[] f_all=new double[all_count];
		  			       ResultSet q11=stmt.executeQuery("select exp from all_q5_final");
		  			       int i1=0;
		  			       while(q11.next())
		  			       {
		  			    	 f_all[i1]=q11.getInt(1);
		  			    	 i1++;
		  			       }
							System.out.println("all array created");
			        	
				        	ResultSet q2=stmt.executeQuery("select count(*) from aml_q5_final");
	                        int aml_count=0;
		  			           while(q2.next())
		  			           {
		  			        	        aml_count=q2.getInt(1);
										System.out.println("count aml:"+q2.getInt(1));
										
		  			           }
		  			         double[] f_aml=new double[aml_count];
		  			       ResultSet q22=stmt.executeQuery("select exp from aml_q5_final");
		  			       int i2=0;
		  			       while(q22.next())
		  			       {
		  			    	 f_aml[i2]=q22.getInt(1);
		  			    	 i2++;
		  			       }
							System.out.println("aml array created");
							
				        ResultSet q3=stmt.executeQuery("select count(*) from BT_q5_final");
	                    int BT_count=0;
				           while(q3.next())
				           {
				        	        BT_count=q3.getInt(1);
									System.out.println("count BT:"+q3.getInt(1));
									
				           }
				           double[] f_BT=new double[BT_count];
				           ResultSet q33=stmt.executeQuery("select exp from BT_q5_final");
		  			       int i3=0;
		  			       while(q33.next())
		  			       {
		  			    	 f_BT[i3]=q33.getInt(1);
		  			    	 i3++;
		  			       }
							System.out.println("BT array created");
							
						ResultSet q4=stmt.executeQuery("select count(*) from CT_q5_final");
		                int CT_count=0;
				           while(q4.next())
				           {
				        	        CT_count=q4.getInt(1);
									System.out.println("count CT:"+q4.getInt(1));
									
				           }
				           double[] f_CT=new double[CT_count];
				           
				           ResultSet q44=stmt.executeQuery("select exp from CT_q5_final");
		  			       int i4=0;
		  			       while(q44.next())
		  			       {
		  			    	 f_CT[i4]=q44.getInt(1);
		  			    	 i4++;
		  			       }
						System.out.println("CT array created");
						
					List<double[]> classes = new ArrayList();
					classes.add(f_all);
					classes.add(f_aml);
					classes.add(f_BT);
					classes.add(f_CT);
					System.out.println("classes added to list");
					OneWayAnova o1=new OneWayAnova();
					double fstat=o1.anovaFValue(classes);
					double f_pvalue=o1.anovaPValue(classes);
					System.out.println("fstats::::"+fstat);
					System.out.println("f_pvalue::::"+f_pvalue);
							           
				}
			        	    catch (NumberFormatException | SQLException e) 
		                    {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
		       
		                 				       
			        }     
			    });
			    t.start();					
			}
    	    });
      	 
      	  jf.getContentPane().add(jp); 
	      jf.setVisible(true);
              
	      
	      
	      
      	 String str7="knowledge discovery";
      	 final JLabel jl7=new JLabel(str7,JLabel.TRAILING);
      	 final JTextField tf7=new JTextField(10);
      	 jl3.setLabelFor(tf7);  
      	 jp.add(jl7);
      	 jp.add(tf7);
      	 JButton button7 = null;
      	 content.add(button7= new JButton("pearsons"));
      	 jp.add(button7);
      	       	 
    	 button7.addActionListener(new ActionListener()
   	    {        	  
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Thread tt = new Thread(new Runnable() {
			        @Override
			        public void run() {
			        	
			        	 try {
			        		 // With all
			        		 ResultSet rs_tstat=stmt.executeQuery("select count( distinct s_id ) from q6_all");
			              int patient_count=0;
			          
			              while(rs_tstat.next())  
			              {
			            	  patient_count=rs_tstat.getInt(1);
			            	  
			            	 System.out.println("count patient_count:"+patient_count);  
			            	
			              }
			         
			              System.out.println("intermediate debug: PART3:");
			              ResultSet rs_tstat_entry=stmt.executeQuery("select  distinct s_id  from q6_all");
			              
			            //distinct s_id in patients
			              int[] patients=new int[patient_count];
			              int i=0;
			              while(rs_tstat_entry.next())
			              {
			            	  patients[i]=Integer.parseInt(rs_tstat_entry.getString(1));
			            	  i++;
			              }
			            System.out.println("total number of entires patients :"+patients.length);
			        
			        	PearsonsCorrelation corr = new PearsonsCorrelation();
			            List<Double> avg_corelation=new ArrayList<Double>();
			            System.out.println("pateints with all"+(patients.length-1));
			            double[] pat2=new double[24];
			            double[] pat1=new double[24];
			            int counter=0;
			            for(int k=0;k<patients.length-1;k++){
		            		 String s="select exp from q6_all where s_id='"+patients[k]+"'";
		            		 ResultSet r1=stmt.executeQuery(s);
		            		 int i_local=0;
			            		while(r1.next())
						           {
						        	        pat1[i_local]=r1.getInt(1);
						        	        i_local++;
											
						           }
			            		
			            	for(int j=k+1;j<patients.length;j++){
			            		counter+=1;
			            		 String s1="select exp from q6_all where s_id='"+patients[j]+"'";
			            		ResultSet r2=stmt.executeQuery(s1);
						       int i_local2=0;
			            		while(r2.next())
						           {
						        	        pat2[i_local2]=r2.getInt(1);
						        	        i_local2++;
											
						           }
			            		
			            		double result = corr.correlation(pat1, pat2);
								avg_corelation.add(result);
			            	
			            	}
							
			            }
			            System.out.println("no iterated"+counter);
			            System.out.println("number of list"+avg_corelation.size());
			            //find the avg of avg_correlation
			            double average=0;
			            double sum=0;
			            double count=0;
			            for(int p=0;p<avg_corelation.size();p++){
			            	sum+=avg_corelation.get(p);
			            	count+=1;
			            }
			            average=sum/count;
			            System.out.println("average"+average);
					
			            
			          //one with all and aml
			   		 ResultSet rs_tstat1=stmt.executeQuery("select count( distinct s_id ) from q6_aml");
		              int patient_count1=0;
		          
		              while(rs_tstat1.next())  
		              {
		            	  patient_count1=rs_tstat1.getInt(1);
		            	  
		            	 System.out.println("count patient_count:"+patient_count1);  
		            	
		              }
		         
		              System.out.println("intermediate debug: PART3:");
		              ResultSet rs_tstat_entry1=stmt.executeQuery("select  distinct s_id  from q6_aml");
		              //get it into array
		            //distinct s_id in patients aml
		              int[] patients_aml=new int[patient_count1];
		              int i1=0;
		              while(rs_tstat_entry1.next())
		              {
		            	  patients_aml[i1]=Integer.parseInt(rs_tstat_entry1.getString(1));
		            	  i1++;
		              }
		            System.out.println("total number of entires patients :"+patients_aml.length);
		        
		        	PearsonsCorrelation corr1 = new PearsonsCorrelation();
		            List<Double> avg_corelation1=new ArrayList<Double>();
		            
		            double[] patient2=new double[24];
		            double[] patient1=new double[24];// fill in the exp value for pat1;patients[k];
		            for(int k=0;k<patients.length;k++){
	            		 String s="select exp from q6_all where s_id='"+patients[k]+"'";
	            		 ResultSet r1=stmt.executeQuery(s);
	            		 int i_local=0;
		            		while(r1.next())
					           {
					        	        patient1[i_local]=r1.getInt(1);
					        	        i_local++;
					           }
		            		
		            	for(int j=0;j<patients_aml.length;j++){
		            		
		            		 String s1="select exp from q6_aml where s_id='"+patients_aml[j]+"'";
		            		ResultSet r2=stmt.executeQuery(s1);
					       int i_local2=0;
		            		while(r2.next())
					           {
					        	        patient2[i_local2]=r2.getInt(1);
					        	        i_local2++;
										
					           }
		            		
		            		double result1 = corr1.correlation(patient1, patient2);
							avg_corelation1.add(result1);
		            	}
		            }
		            //find the avg of avg_correlation
		            double average1=0;
		            double sum1=0;
		            double count1=0;
		            for(int p=0;p<avg_corelation1.size();p++){
		            	sum1+=avg_corelation1.get(p);
		            	count1+=1;
		            }
		            average1=sum1/count1;
		            System.out.println("average aml"+average1);
				 
		            
				
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						                 				       
			        }     
			    });
			    tt.start();					
			}
   	    });
      jf.getContentPane().add(jp); 
      jf.setVisible(true);

	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
            // code reference :http://www.cs.princeton.edu/courses/archive/spr15/cos126/docs/Tstat.java.html
            // http://stackoverflow.com/questions/7988486/how-do-you-calculate-the-variance-median-and-standard-deviation-in-c-or-java/7988556
   
            //	  conn.close();  
            }
              catch(ClassNotFoundException ex) {
              System.out.println("Error: unable to load driver class!");
               System.exit(1);
        }
    }



	
}