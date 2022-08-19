import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;


class  shisyutu {
	
    int DATE_MONTH = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
    int DATE_YEAR = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    JLabel J_Label = new JLabel("", JLabel.CENTER);
    String DATE_DAY = "";
    JDialog J_Dialog;
    //曜日ボタン
    JButton[] J_Button = new JButton[49];
    
   public  shisyutu(JFrame J_Frame_A) {
	  

	  // J_Dialog.setTitle("shisyutu");
        J_Dialog = new JDialog();
        J_Dialog.setModal(true);
       
        JLabel J_Label1 = new JLabel("名前:",JLabel.RIGHT);
		 JLabel J_Label2 = new JLabel("値段:",JLabel.RIGHT);
		 JTextField text4=new JTextField(20);
		
		 JPanel J_Panel4= new JPanel(new GridLayout(1,1));
		 //日付データ
		text4.setText(new DatePick(J_Frame_A).Set_Picked_Date());
		
		 J_Panel4.setPreferredSize(new Dimension(100, 20));
		
		 // J_Panel3について
		 JPanel J_Panel3 = new JPanel(new GridLayout(1, 2));
		 J_Panel3.setPreferredSize(new Dimension(200, 20));
		 
		 J_Panel3.add(J_Label2);
		
		
		 //名前
		 JTextField text1=new JTextField(20);
		 
		 //デー
		 JPanel J_PanelText = new JPanel(new GridLayout(2, 1));
		 J_PanelText.setPreferredSize(new Dimension(200, 50));
		 //中にデータ入る
		 JButton button5=new JButton("save");
		 JButton button6=new JButton("総支出");
		 J_PanelText.add(button5, BorderLayout.NORTH);
		 J_PanelText.add(button6,BorderLayout.CENTER);
		
		 JTextField text2=new JTextField(20);
		 //テキストコピー
		 
		 button5.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ae) {
	            	
	            	// テキストエリアの内容をリストに入れる
	    			List<String> list1 = new ArrayList<String>();
	    			List<String> list2 = new ArrayList<String>();
	    			List<String> list3 = new ArrayList<String>();
	    			String str1,str2,str3;
	    			
	    			list1.add(text1.getText());
	    			list2.add(text2.getText());
	    			list3.add(text4.getText());
	    			str1=String.join(",",list3);
	    			str2=String.join(",",list1);
	    			str3=String.join(",",list2);
	    			//カレンダー
	    			String filePath="shisyutu1.txt";
	    		//	list3.add(text4.getText());
	    			try(FileWriter fw= new FileWriter(filePath,true);) {
	    				 fw.write("---------------------------------\n");
	    				 fw.write("支出\n");
	 	    			fw.write(str1+"\n");
	 	    			fw.write("名前："+str2+"\n");
	 	    			fw.write("値段："+str3+"円\n");
	    			
	    			}catch(IOException e) {
	    				e.printStackTrace();
	    			}
          
	    			  String filePath2="shisyutu2.txt";
	    			try(FileWriter fw= new FileWriter(filePath2,true);) {
		    			
		    			fw.write(str3+"\n");
		    			}catch(IOException e) {
		    				e.printStackTrace();
		    		 	}
	    			
	    			
	    			
	    			
	    			try {
	    			      File f = new File("shisyutu1.txt");
	    			      BufferedReader br = new BufferedReader(new FileReader(f));
	    			 
	    			      int c = br.read();
	    			      while (c != -1) {
	    			        System.out.print((char)c);
	    			        c = br.read();
	    			      }
	    			      br.close();
	    			    } catch (IOException e) {
	    			      System.out.println(e);
	    			    }
	    			
	    			
	            }
	           
	            
	        });
		 
		 button6.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent ae) {
	    	//  JTextField text4=new JTextField(20);
	    	  J_Dialog = new JDialog();
	          J_Dialog.setModal(true);
		 JFrame frame1=new JFrame();
		 frame1.setTitle("Sum");
		 //frame1.setVisible(true);
		 frame1.setSize(300,200);
		 JPanel J_PanelText = new JPanel(new GridLayout(1, 4));
		 J_PanelText.setPreferredSize(new Dimension(400, 50));
		 int total = 0;
		 File file = new File("shisyutu2.txt");
	        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	            String line = null;
	           
	            while ((line = br.readLine()) != null) {

	                // 空行はスキップ
	                if (line.isEmpty()) {
	                    continue;
	                }

	                // 空行でない場合、合計する。
	                String[] nums = line.split(",");
	                for (String num : nums) {
	                    if (!num.isEmpty()) {
	                        total += Integer.parseInt(num);
	                    }
	                }
	            }
	      
	           
	        } catch (FileNotFoundException e) {
	            System.out.println("ファイルが存在しません。");
	        } catch (IOException e) {
	            System.out.println("エラーが発生しました。");
	        }
	        JLabel J_Label2 = new JLabel("総支出:",JLabel.RIGHT);
	       JLabel J_Label3 = new JLabel("",JLabel.LEFT);

	    //    System.out.println(total);
	        if(total<1000) {
	        	 J_Label3.setText("君は家計簿マスター！！");
	        	
	           }
	        else if(total<5000){
	        	 J_Label3.setText ("その調子で！！");
	        	
	        }
	        else if(total<15000){
	        	J_Label3.setText ("乞食生活スタート");
	        	
	        }
	        else if(total<40000){
	        	J_Label3.setText ("マグロ漁船行き決定！！");
	        	
	        }
	           String A=Integer.valueOf(total).toString();
	           
	           JLabel J_Label1 = new JLabel(A+"円",JLabel.CENTER);
	         	         
	           J_PanelText.add( J_Label2 ,BorderLayout.WEST);
	           J_PanelText.add(J_Label1,BorderLayout.CENTER);
	           J_PanelText.add( J_Label3 ,BorderLayout.EAST);
	          
	           J_Dialog.add( J_PanelText, BorderLayout.CENTER);
	           J_Dialog.pack();
	           J_Dialog.setLocationRelativeTo(frame1);
	           Data();
	           J_Dialog.setVisible(true);
		       J_Dialog.dispose(); 
	      }
		
		
		 
	            
	        });
		
		
        //Header=7
      String[] Header = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
      
        // GridLayout:　７行７列を指定
        JPanel J_Panel1 = new JPanel(new GridLayout(7, 7));
        //Dimension：幅、高さを指定
        J_Panel1.setPreferredSize(new Dimension(700, 120));

        //
        for (int i = 0; i < J_Button.length; i++) {
            final int selection = i;
            //setFocusPainted：ボタンを有効化？
            J_Button[i] = new JButton();
            J_Button[i].setFocusPainted(false);
            //カレンダー色
            J_Button[i].setBackground(Color.white);
           
            //日にちに機能追加
            if (i > 6)
                J_Button[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        DATE_DAY = J_Button[selection].getActionCommand();
                        J_Dialog.dispose();
                    }
                });
            //曜日名入力
            if (i < 7) {
                J_Button[i].setText(Header[i]);
                //曜日文字
                J_Button[i].setForeground(Color.blue);
            }
           //それぞれにパネルのフォントサイズ設定
            J_Panel1.add(J_Button[i]);
        }
        
        
      
        //先月を確認
        JPanel J_Panel2 = new JPanel(new GridLayout(1, 3));
        J_Panel2.setPreferredSize(new Dimension(200, 20));
      
        
        J_Panel2.add(J_Label1);
        J_Panel2.add(text1);
       // J_Panel2.add(J_Label3);
        J_Panel3.add(text2);
        J_Panel4.add(text4);
        
        //それぞれのパネルの位置指定
        
        J_Dialog.add( J_PanelText, BorderLayout.SOUTH);
        J_Dialog.add(J_Panel3, BorderLayout.EAST);
        J_Dialog.add(J_Panel2, BorderLayout.WEST);
        J_Dialog.add(J_Panel4, BorderLayout.NORTH);
        J_Dialog.pack();
        J_Dialog.setLocationRelativeTo(J_Frame_A);
        Data();
        J_Dialog.setVisible(true);
      
    }
     //真ん中の年と月
    public void Data() {
        for (int i = 7; i < J_Button.length; i++)
            J_Button[i].setText("");
        java.text.SimpleDateFormat Simple_Date_Format = new java.text.SimpleDateFormat(
                "MMMM yyyy");
        java.util.Calendar Calendar = java.util.Calendar.getInstance();
        Calendar.set(DATE_YEAR, DATE_MONTH, 1);
        int Day_Of_Week = Calendar.get(java.util.Calendar.DAY_OF_WEEK);
        int Days_In_Month = Calendar.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        for (int i = 6 + Day_Of_Week, Day = 1; Day <= Days_In_Month; i++, Day++)
          //全ての日にち
        	J_Button[i].setText("" + Day);
        J_Label.setText(Simple_Date_Format.format(Calendar.getTime()));
        //左上タイトル
        J_Dialog.setTitle("支出");
    }

    //テキスト部：年、月、日など
    public String Picked_Date() {
   
    	 return DATE_DAY;
    }

}

/**********************************ここまで支出*************************************************/
class  Syunyu{

	
    int DATE_MONTH = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
    int DATE_YEAR = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    JLabel J_Label = new JLabel("", JLabel.CENTER);
    String DATE_DAY = "";
    JDialog J_Dialog;
    //曜日ボタン
    JButton[] J_Button = new JButton[49];
    
   public   Syunyu(JFrame J_Frame_A) {
	  

	  // J_Dialog.setTitle("shisyutu");
        J_Dialog = new JDialog();
        J_Dialog.setModal(true);
       
        JLabel J_Label1 = new JLabel("名前:",JLabel.RIGHT);
		 JLabel J_Label2 = new JLabel("値段:",JLabel.RIGHT);
		 JTextField text4=new JTextField(20);
		
		 JPanel J_Panel4= new JPanel(new GridLayout(1,1));
		 //日付データ
		text4.setText(new DatePick(J_Frame_A).Set_Picked_Date());
		
		 J_Panel4.setPreferredSize(new Dimension(100, 20));
		
		 // J_Panel3について
		 JPanel J_Panel3 = new JPanel(new GridLayout(1, 2));
		 J_Panel3.setPreferredSize(new Dimension(200, 20));
		 
		 J_Panel3.add(J_Label2);
		
		
		 //名前
		 JTextField text1=new JTextField(20);
		 
		 //デー
		 JPanel J_PanelText = new JPanel(new GridLayout(2, 1));
		 J_PanelText.setPreferredSize(new Dimension(200, 50));
		 //中にデータ入る
		 JButton button5=new JButton("save");
		 JButton button6=new JButton("総収入");
		 J_PanelText.add(button5, BorderLayout.NORTH);
		 J_PanelText.add(button6,BorderLayout.CENTER);
		
		 JTextField text2=new JTextField(20);
		 //テキストコピー
		 
		 button5.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ae) {
	            	//J_LabelText.setText(text1.getText());
	            	//text2.setText(text1.getText());
	            	// テキストエリアの内容をリストに入れる
	    			List<String> list1 = new ArrayList<String>();
	    			List<String> list2 = new ArrayList<String>();
	    			List<String> list3 = new ArrayList<String>();
	    			String str1,str2,str3;
	    			
	    			list1.add(text1.getText());
	    			list2.add(text2.getText());
	    			list3.add(text4.getText());
	    			str1=String.join(",",list3);
	    			str2=String.join(",",list1);
	    			str3=String.join(",",list2);
	    			//カレンダー
	    			String filePath="syunyu1.txt";
	    		//	list3.add(text4.getText());
	    			try(FileWriter fw= new FileWriter(filePath,true);) {
	    		    fw.write("---------------------------------\n");
	    		    fw.write("収入\n");
	    			fw.write(str1+"\n");
	    			fw.write("名前："+str2+"\n");
	    			fw.write("値段："+str3+"円\n");
	    			}catch(IOException e) {
	    				e.printStackTrace();
	    			}
          
	    			  String filePath2="syunyu2.txt";
	    			try(FileWriter fw= new FileWriter(filePath2,true);) {
		    			
		    			fw.write(str3+"\n");
		    			}catch(IOException e) {
		    				e.printStackTrace();
		    		 	}
	    			
	    			
	    			
	    			
	    			try {
	    			      File f = new File("syunyu1.txt");
	    			      BufferedReader br = new BufferedReader(new FileReader(f));
	    			 
	    			      int c = br.read();
	    			      while (c != -1) {
	    			        System.out.print((char)c);
	    			        c = br.read();
	    			      }
	    			      br.close();
	    			    } catch (IOException e) {
	    			      System.out.println(e);
	    			    } 
	    			
	            }
	           
	            
	        });
		 
		 button6.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent ae) {
	    	//  JTextField text4=new JTextField(20);
	    	  J_Dialog = new JDialog();
	          J_Dialog.setModal(true);
		 JFrame frame1=new JFrame();
		
		 frame1.setSize(400,200);
		 JPanel J_PanelText = new JPanel(new GridLayout(1, 4));
		 J_PanelText.setPreferredSize(new Dimension(500, 50));
		 int total = 0;
		 File file = new File("syunyu2.txt");
	        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	            String line = null;
	           
	            while ((line = br.readLine()) != null) {

	                // 空行はスキップ
	                if (line.isEmpty()) {
	                    continue;
	                }

	                // 空行でない場合、合計する。
	                String[] nums = line.split(",");
	                for (String num : nums) {
	                    if (!num.isEmpty()) {
	                        total += Integer.parseInt(num);
	                    }
	                }
	            }
	      
	           
	        } catch (FileNotFoundException e) {
	            System.out.println("ファイルが存在しません。");
	        } catch (IOException e) {
	            System.out.println("エラーが発生しました。");
	        }
	        JLabel J_Label2 = new JLabel("総収入:",JLabel.RIGHT);
	       JLabel J_Label3 = new JLabel("",JLabel.LEFT);

	    //    System.out.println(total);
	        if(total<1000) {
	        	 J_Label3.setText("働こう！！");
	        	
	           }
	        else if(total<5000){
	        	 J_Label3.setText ("その調子で！！");
	        	
	        }
	        else if(total<15000){
	        	J_Label3.setText ("大富豪");
	        	
	        }
	        else if(total>40000){
	        	J_Label3.setText ("まるでビルゲイツ！");
	        	
	        }
	        else if(total>60000){
	        	J_Label3.setText ("まるで海賊王！");
	        	
	        }
	           String A=Integer.valueOf(total).toString();
	           
	           JLabel J_Label1 = new JLabel(A+"円",JLabel.CENTER);
	         	         
	           J_PanelText.add( J_Label2 ,BorderLayout.WEST);
	           J_PanelText.add(J_Label1,BorderLayout.CENTER);
	           J_PanelText.add( J_Label3 ,BorderLayout.EAST);
	         //  J_PanelText.add( J_Label3 ,BorderLayout.EAST);
	         // J_PanelText.add(text4,BorderLayout.CENTER);
	          
	           J_Dialog.add( J_PanelText, BorderLayout.CENTER);
	           J_Dialog.pack();
	           J_Dialog.setLocationRelativeTo(frame1);
	           Data();
	           J_Dialog.setVisible(true);
		       J_Dialog.dispose(); 
	      }
		
		
		 
	            
	        });
		
		
        //Header=7
      String[] Header = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
      
        // GridLayout:　７行７列を指定
        JPanel J_Panel1 = new JPanel(new GridLayout(7, 7));
        //Dimension：幅、高さを指定
        J_Panel1.setPreferredSize(new Dimension(700, 120));

        //
        for (int i = 0; i < J_Button.length; i++) {
            final int selection = i;
            //setFocusPainted：ボタンを有効化？
            J_Button[i] = new JButton();
            J_Button[i].setFocusPainted(false);
            //カレンダー色
            J_Button[i].setBackground(Color.white);
           
            //日にちに機能追加
            if (i > 6)
                J_Button[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        DATE_DAY = J_Button[selection].getActionCommand();
                        J_Dialog.dispose();
                    }
                });
            //曜日名入力
            if (i < 7) {
                J_Button[i].setText(Header[i]);
                //曜日文字
                J_Button[i].setForeground(Color.blue);
            }
           //それぞれにパネルのフォントサイズ設定
            J_Panel1.add(J_Button[i]);
        }
        
        
      
        //先月を確認
        JPanel J_Panel2 = new JPanel(new GridLayout(1, 3));
        J_Panel2.setPreferredSize(new Dimension(200, 20));
      
        
        J_Panel2.add(J_Label1);
        J_Panel2.add(text1);
       // J_Panel2.add(J_Label3);
        J_Panel3.add(text2);
        J_Panel4.add(text4);
        
        //それぞれのパネルの位置指定
        
        J_Dialog.add( J_PanelText, BorderLayout.SOUTH);
        J_Dialog.add(J_Panel3, BorderLayout.EAST);
        J_Dialog.add(J_Panel2, BorderLayout.WEST);
        J_Dialog.add(J_Panel4, BorderLayout.NORTH);
        J_Dialog.pack();
        J_Dialog.setLocationRelativeTo(J_Frame_A);
        Data();
        J_Dialog.setVisible(true);
      
    }
     //真ん中の年と月
    public void Data() {
        for (int i = 7; i < J_Button.length; i++)
            J_Button[i].setText("");
        java.text.SimpleDateFormat Simple_Date_Format = new java.text.SimpleDateFormat(
                "MMMM yyyy");
        java.util.Calendar Calendar = java.util.Calendar.getInstance();
        Calendar.set(DATE_YEAR, DATE_MONTH, 1);
        int Day_Of_Week = Calendar.get(java.util.Calendar.DAY_OF_WEEK);
        int Days_In_Month = Calendar.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        for (int i = 6 + Day_Of_Week, Day = 1; Day <= Days_In_Month; i++, Day++)
          //全ての日にち
        	J_Button[i].setText("" + Day);
        J_Label.setText(Simple_Date_Format.format(Calendar.getTime()));
        //左上タイトル
        J_Dialog.setTitle("収入");
    }

    //テキスト部：年、月、日など
    public String Picked_Date() {
   
    	 return DATE_DAY;
    }

}


/******************************ここまで収入**************/
 
	class DatePick {
	    int DATE_MONTH = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
	    int DATE_YEAR = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);;
	    JLabel J_Label = new JLabel("", JLabel.CENTER);
	    String DATE_DAY = "";
	    JDialog J_Dialog;
	    //曜日ボタン
	    JButton[] J_Button = new JButton[49];
	    
	    public DatePick(JFrame J_Frame_Parent) {
	        J_Dialog = new JDialog();
	        J_Dialog.setModal(true);
	    //    JLabel J_Label = new JLabel("指定shi");
	        //Header=7
	      String[] Header = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
	      
	     
	        // GridLayout:　７行７列を指定
	        JPanel J_Panel1 = new JPanel(new GridLayout(7, 7));
	        //Dimension：幅、高さを指定
	      J_Panel1.setPreferredSize(new Dimension(2000,500));
	       // J_Panel1.setPreferredSize(new Dimension(1260, 960));
	        EtchedBorder border = new EtchedBorder(EtchedBorder.RAISED,
	        		Color.white, Color.black);// Borderクラスを使ってボーダーを作成。

	        //
	        for (int i = 0; i < J_Button.length; i++) {
	            final int selection = i;
	            //setFocusPainted：ボタンを有効化？
	            J_Button[i] = new JButton();
	            J_Button[i].setFocusPainted(false);
	            //カレンダー色
	            J_Button[i].setBackground(Color.white);
	           
	            
	            //日にちに機能追加
	            if (i > 6)
	                J_Button[i].addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent ae) {
	                    	//DATE_DAY にselection（日）を渡す
	                        DATE_DAY = J_Button[selection].getActionCommand();
	                      J_Dialog.dispose();	                      
	                    }
	                });
	            //曜日名入力
	            if (i < 7) {
	                J_Button[i].setText(Header[i]);
	                //曜日文字
	                J_Button[i].setForeground(Color.blue);
	            }
	           //それぞれにパネルのフォントサイズ設定
	            J_Panel1.add(J_Button[i],BorderLayout.NORTH);
	            JTextArea memo = new JTextArea();
	            memo.setEditable(false);
	            memo.setBorder(border);
	            memo.setBackground(Color.white);
	            memo.setOpaque(true);
	           J_Panel1.add(memo, BorderLayout.CENTER);
	           
	           
	        
	        }
	        //先月を確認
	        
	        JPanel J_Panel2 = new JPanel(new GridLayout(1, 3));
	        //JButonで作成
	        JButton Previous_Button = new JButton("<< Previous");
	        Previous_Button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ae) {
	                DATE_MONTH--;
	                Display_Date();
	            }
	        });
	        
	        //来月確認
	        //J_Panel2でPrevious_ButtonとJ_Labelの位置設定
	        J_Panel2.add(Previous_Button);
	        J_Panel2.add(J_Label);
	        
	        JButton Next_Button = new JButton("Next >>");
	        Next_Button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ae) {
	                DATE_MONTH++;
	                Display_Date();
	            }
	        });
	        //右に配置される自動的に
	        J_Panel2.add(Next_Button);
	        //それぞれのパネルの位置指定
	        J_Dialog.add(J_Panel1, BorderLayout.CENTER);
	        J_Dialog.add(J_Panel2, BorderLayout.NORTH);
	        J_Dialog.pack();
	        J_Dialog.setLocationRelativeTo(J_Frame_Parent);
	        Display_Date();
	        J_Dialog.setVisible(true);
	    }
         //真ん中の年と月
	    public void Display_Date() {
	        for (int i = 7; i < J_Button.length; i++)
	            J_Button[i].setText("");
	        java.text.SimpleDateFormat Simple_Date_Format = new java.text.SimpleDateFormat(
	                "MMMM yyyy");
	        java.util.Calendar Calendar = java.util.Calendar.getInstance();
	        Calendar.set(DATE_YEAR, DATE_MONTH, 1);
	        int Day_Of_Week = Calendar.get(java.util.Calendar.DAY_OF_WEEK);
	        int Days_In_Month = Calendar.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
	        for (int i = 6 + Day_Of_Week, Day = 1; Day <= Days_In_Month; i++, Day++)
	          //全ての日にち
	        	J_Button[i].setText("" + Day);
	        //ラベル
	        J_Label.setText(Simple_Date_Format.format(Calendar.getTime()));
	        //左上タイトル
	        J_Dialog.setTitle("Date Picker");
	    }

	    //テキスト部：年、月、日など
	    public String Set_Picked_Date() {
	        if (DATE_DAY.equals(""))
	            return DATE_DAY;
	        java.text.SimpleDateFormat Simple_Date_Format = new java.text.SimpleDateFormat(
	                "yyyy-MM-dd"); //"dd-MM-yyyy"
	        java.util.Calendar Calendar = java.util.Calendar.getInstance();
	      Calendar.set(DATE_YEAR, DATE_MONTH, Integer.parseInt( DATE_DAY));
	        return Simple_Date_Format.format(Calendar.getTime());
	    }
	}
//指定した日にち
	public class Date_Picker extends JFrame  {
		JPanel caldPanel;
		 CardLayout layout;
	    public static void main(String[] args) {
	    	
	    	//JPanel cardPanel;
	        JLabel J_Label = new JLabel("指定した日:");
	        //JTextField：テキストに記入可能にする
	        final JTextField J_Text_Field = new JTextField(20);
	     //   JButton J_Button = new JButton("Choose the Date");
	        JButton J_Button = new JButton("カレンダー閲覧");
	        JPanel J_Panel1 = new JPanel();
	        //文字のみ
	        J_Panel1.add(J_Label);
	        
	        J_Panel1.add(J_Text_Field);
	        J_Panel1.add(J_Button);
	       
	        final JFrame J_Frame = new JFrame();
	        J_Frame.getContentPane().add(J_Panel1);
	        J_Frame.pack();
	        J_Frame.setVisible(true);
	    
	        
	       JButton A= new JButton("収入");
	       JButton B= new JButton("支出");
	       
	       A.setPreferredSize(new Dimension(300,20));
	       JPanel J_Panel2 = new JPanel(new GridLayout(1, 2));
	      
	       J_Panel2.add(A);
	       J_Panel2.add(B);
	       JDialog   J_Dialog1=new  JDialog();
	        J_Dialog1.add(J_Panel1, BorderLayout.NORTH);
	        J_Dialog1.add(J_Panel2, BorderLayout.CENTER);
	     //  A.addActionListener(this);
	        //以下の２行で表時位置と画面操作
	        J_Dialog1.pack();
	        J_Dialog1.setLocationRelativeTo(null);
	      
	        J_Dialog1.setVisible(true); 
	        
	        
	        //J_Buttonの処理
	        J_Button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ae) {
	                J_Text_Field.setText(new DatePick(J_Frame).Set_Picked_Date());
	            }
	        }); 
	       
	        
	        //Aの処理
	        
	        A.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ae) {
	                J_Text_Field.setText(new  Syunyu(J_Frame).Picked_Date());
	            
	            	
	            }
	        }); 
	        
	        //Bの処理
	        
	        B.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ae) {
	              J_Text_Field.setText(new shisyutu(J_Frame).Picked_Date());
	       	
	            
	            }
	        });
	       
	     
	    	
	    }
	
	}




