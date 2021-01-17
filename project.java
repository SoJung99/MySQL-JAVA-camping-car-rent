
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;




public class project extends JFrame{
   
   Connection con;
   Statement stmt;
   ResultSet rs;
   
   String Driver = "";
   String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
   String userid = "madang";
   String pwd = "madang";
   
   Mpanel mp = new Mpanel();
   Upanel up = new Upanel();
   
   Boolean[] carBoolean = new Boolean[30]; 		// ÃÊ±â°ªÀº false(´ë¿©ºÒ°¡´É)
   Boolean[] repairBoolean = new Boolean[30];	// ÃÊ±â°ª false(¼ö¸®ºÒÇÊ¿ä)
   
   
   public boolean CheckNumber(String str){
		char check;
		
		if(str.equals(""))
		{	
			return false;
		}
		
		for(int i = 0; i<str.length(); i++){
			check = str.charAt(i);
			if( check < 48 || check > 58)
			{
				
				return false;
			}
			
		}		
		return true;
	}
   
   class Mpanel extends JPanel implements ActionListener{
	      JLabel manager = new JLabel("°ü¸®ÀÚ");
	      JButton initB = new JButton("ÃÊ±âÈ­");
	      
	      JPanel northMP = new JPanel();
	      JPanel northMP0 = new JPanel();
	      JPanel northMP1 = new JPanel();
	      JPanel northMP2 = new JPanel();
	      JPanel northMP3 = new JPanel();
	      JPanel carReturnP = new JPanel();
	      JPanel carRepairP = new JPanel();
	      
	      JPanel sP1 = new JPanel();
	      JPanel sP2 = new JPanel();
	      JPanel sP3 = new JPanel();
	      JPanel sP4 = new JPanel();
	      
	      JPanel sP = new JPanel();
	      JPanel resultP = new JPanel();
  
	      JLabel company = new JLabel("Ä·ÇÎÄ«´ë¿©È¸»ç");
	      
	      // È¸»ç ÀÔ·Â
	      JLabel comp_namel = new JLabel("È¸»ç ÀÌ¸§");
	      JTextField comp_namef = new JTextField(8);
	      JLabel comp_addressl = new JLabel("ÁÖ¼Ò");
	      JTextField comp_addressf = new JTextField(7);
	      JLabel comp_phonel = new JLabel("ÀüÈ­¹øÈ£");
	      JTextField comp_phonef = new JTextField(10);
	      JLabel comp_personl = new JLabel("´ã´çÀÚ ÀÌ¸§");
	      JTextField comp_personf = new JTextField(7);
	      JLabel comp_emaill = new JLabel("ÀÌ¸ÞÀÏ");
	      JTextField comp_emailf = new JTextField(13);
	      JButton comp_insertB = new JButton("È¸»ç ÀÔ·Â");
	      JLabel comp_idl = new JLabel("È¸»ç ID");
	      JTextField comp_idf = new JTextField(3);
	      JButton comp_convertB = new JButton("È¸»ç º¯°æ");
	      JButton comp_deleteB = new JButton("È¸»ç »èÁ¦");
	      
	      
	      // Ä·ÇÎÄ« ÀÔ·Â
	      JLabel car_comp_idl = new JLabel("È¸»ç ID");
	      JTextField car_comp_idf = new JTextField(3);
	      JLabel car_namel = new JLabel("Ä·ÇÎÄ« ÀÌ¸§");
	      JTextField car_namef = new JTextField(10);
	      JLabel car_numl = new JLabel("Ä·ÇÎÄ« ¹øÈ£");
	      JTextField car_numf = new JTextField(10);
	      JLabel car_boardl = new JLabel("½ÂÂ÷ÀÎ¿ø¼ö");
	      JTextField car_boardf = new JTextField(3);
	      JLabel car_manucompl = new JLabel("Á¦Á¶È¸»ç");
	      JTextField car_manucompf = new JTextField(5);
	      JLabel car_manudatel = new JLabel("Á¦Á¶¿¬µµ");
	      JTextField car_manudatef = new JTextField(7);
	      JLabel car_distancel = new JLabel("´©ÀûÁÖÇà°Å¸®");
	      JTextField car_distancef = new JTextField(4);
	      JLabel car_pricel = new JLabel("´ë¿©ºñ¿ë");
	      JTextField car_pricef = new JTextField(5);
	      JLabel car_datel = new JLabel("µî·ÏÀÏÀÚ");
	      JTextField car_datef = new JTextField(7);
	      JButton car_insertB = new JButton("Ä·ÇÎÄ« ÀÔ·Â");
	      JLabel car_idl = new JLabel("Ä·ÇÎÄ« ID");
	      JTextField car_idf = new JTextField(3);
	      JButton car_deleteB = new JButton("Ä·ÇÎÄ« »èÁ¦");
	      JButton car_convertB = new JButton("Ä·ÇÎÄ« º¯°æ");

	      
	      // °í°´ Á¤º¸
	      JLabel cust_idl = new JLabel("¿îÀü¸éÇãÁõ¹øÈ£");
	      JTextField cust_idf = new JTextField(10);
	      JLabel cust_namel = new JLabel("°í°´¸í");
	      JTextField cust_namef = new JTextField(7);
	      JLabel cust_addressl = new JLabel("°í°´ ÁÖ¼Ò");
	      JTextField cust_addressf = new JTextField(7);
	      JLabel cust_phonel = new JLabel("°í°´ ÀüÈ­¹øÈ£");
	      JTextField cust_phonef = new JTextField(10);
	      JLabel cust_emaill = new JLabel("°í°´ ÀÌ¸ÞÀÏ");
	      JTextField cust_emailf = new JTextField(10);
	      JButton insert_cust = new JButton("°í°´ ÀÔ·Â");
	      JButton delete_cust = new JButton("°í°´ »èÁ¦"); //À§¿¡ ¿îÀü¸éÇãÁõ¹øÈ£·Î ¾²ÀÎ cust_idl, cust_idf Àç»ç¿ë
	      JButton convert_cust = new JButton("°í°´ º¯°æ");
	      
	      // Á¤ºñ¼Ò garage
	      JLabel gar_namel = new JLabel("Á¤ºñ¼Ò¸í");
	      JTextField gar_namef = new JTextField(8);
	      JLabel gar_addressl = new JLabel("Á¤ºñ¼Ò ÁÖ¼Ò");
	      JTextField gar_addressf = new JTextField(7);
	      JLabel gar_phonel = new JLabel("Á¤ºñ¼Ò ÀüÈ­¹øÈ£");
	      JTextField gar_phonef = new JTextField(10);
	      JLabel gar_personl = new JLabel("´ã´çÀÚ ÀÌ¸§");
	      JTextField gar_personf = new JTextField(7);
	      JLabel gar_emaill = new JLabel("ÀÌ¸ÞÀÏ");
	      JTextField gar_emailf = new JTextField(10);
	      JButton insert_gar = new JButton("Á¤ºñ¼Ò ÀÔ·Â");
	      JLabel gar_idl = new JLabel("Á¤ºñ¼Ò ID");
	      JTextField gar_idf = new JTextField(3);
	      JButton delete_gar = new JButton("Á¤ºñ¼Ò »èÁ¦");
	      JButton convert_gar = new JButton("Á¤ºñ¼Ò º¯°æ");
	      
	      
	      // Ä·ÇÎÄ« ¹ÝÈ¯
	      JLabel inspec_rentl = new JLabel("´ë¿©¹øÈ£");
	      JTextField inspec_rentf = new JTextField(3);
	      JLabel inspec_carl = new JLabel("Ä·ÇÎÄ« ID");
	      JTextField inspec_carf = new JTextField(3);
	      JLabel inspec_frontl = new JLabel("¾ÕºÎºÐ");
	      JTextField inspec_frontf = new JTextField(8);
	      JLabel inspec_leftl = new JLabel("¿ÞÂÊºÎºÐ");
	      JTextField inspec_leftf = new JTextField(8);
	      JLabel inspec_rightl = new JLabel("¿À¸¥ÂÊºÎºÐ");
	      JTextField inspec_rightf = new JTextField(8);
	      JLabel inspec_behindl = new JLabel("µÞºÎºÐ");
	      JTextField inspec_behindf = new JTextField(8);
	      JLabel inspec_repairl = new JLabel("¼ö¸®ÇÊ¿ä¿©ºÎ");
	      JTextField inspec_repairf = new JTextField(3);
	      JButton return_car = new JButton("Ä·ÇÎÄ« ¹ÝÈ¯/Á¡°Ë³»¿ª ÀúÀå");
	      
	      
	      // Ä·ÇÎÄ« Á¤ºñÀÇ·Ú
	      JLabel repair_idl= new JLabel("Á¤ºñ³»¿ª ID");
	      JTextField repair_idf = new JTextField(3);
	      JLabel repair_carl = new JLabel("Ä·ÇÎÄ« ID");
	      JTextField repair_carf = new JTextField(3);
	      JLabel repair_garl = new JLabel("Á¤ºñ¼Ò ID");
	      JTextField repair_garf = new JTextField(3);
	      JLabel repair_corpl = new JLabel("È¸»ç ID");
	      JTextField repair_corpf = new JTextField(3);
	      JLabel repair_custl = new JLabel("°í°´ ¿îÀü¸éÇãÁõ¹øÈ£");
	      JTextField repair_custf = new JTextField(10);
	      JLabel repair_infol = new JLabel("Á¤ºñ³»¿ª");
	      JTextField repair_infof = new JTextField(12);
	      JLabel repair_datel = new JLabel("¼ö¸®³¯Â¥");
	      JTextField repair_datef = new JTextField(7);
	      JLabel repair_pricel = new JLabel("¼ö¸®ºñ¿ë");
	      JTextField repair_pricef = new JTextField(5);
	      JLabel repair_paydatel = new JLabel("³³ÀÔ±âÇÑ");
	      JTextField repair_paydatef = new JTextField(7);
	      JLabel repair_extral = new JLabel("±âÅ¸Á¤ºñ³»¿ª");
	      JTextField repair_extraf = new JTextField(10);
	      JButton request_car = new JButton("Á¤ºñ ³»¿ª ÀÔ·Â");
	      JButton repair_delete = new JButton("Á¤ºñ ³»¿ª »èÁ¦");
	      JButton repair_convert = new JButton("Á¤ºñ ³»¿ª º¯°æ");      
	      
	      // °Ë»ö°á°ú
	      JTextArea result = new JTextArea("");
	      
	      // °Ë»ö1
	      JLabel s1_con1l = new JLabel("´ë¿©±â°£ÀÌ");
	      JTextField s1_con1f = new JTextField(3);
	      JLabel s1_con1l2 = new JLabel("ÀÏ ÀÌ»óÀÌ¸é¼­");
	      JLabel s1_con2l = new JLabel("Â÷·® ½ÂÂ÷ ÀÎ¿ø¼ö°¡");
	      JTextField s1_con2f = new JTextField(3);
	      JLabel s1_con2l2 = new JLabel("ÀÌ»óÀÎ °í°´ Á¤º¸");
	      JButton s1b = new JButton("°Ë»ö");	      
	      
	      // °Ë»ö2
	      JLabel s2_con1l = new JLabel("¹ÝÈ¯½Ã ¼ö¸®¿©ºÎ°¡");
	      JTextField s2_con1f = new JTextField(3);
	      JLabel s2_con1l2 = new JLabel("ÀÌ¸é¼­(ÇÊ¿ä=1,ºÒÇÊ¿ä=0)");
	      JLabel s2_con2l = new JLabel("´ë¿©±â°£ÀÌ");
	      JTextField s2_con2f = new JTextField(3);
	      JLabel s2_con2l2 = new JLabel("ÀÌ»óÀÎ Ä·ÇÎÄ« Á¤º¸");
	      JButton s2b = new JButton("°Ë»ö");
	      
	      // °Ë»ö3
	      JLabel s3_con1l = new JLabel("´©ÀûÁÖÇà°Å¸®°¡");
	      JTextField s3_con1f = new JTextField(5);
	      JLabel s3_con1l2 = new JLabel("kmÀÌ»óÀÌ¸é¼­");
	      JLabel s3_con2l = new JLabel("´ë¿©±â°£ÀÌ");
	      JTextField s3_con2f = new JTextField(3);
	      JLabel s3_con2l2 = new JLabel("ÀÌ»óÀÎ Ä·ÇÎÄ« Á¤º¸");
	      JButton s3b = new JButton("°Ë»ö");

	      // °Ë»ö4
	      JLabel s4_con1l = new JLabel("°í°´ÀÇ ¼ºÀÌ");
	      JTextField s4_con1f = new JTextField(3);
	      JLabel s4_con1l2 = new JLabel("ÀÌ¸é¼­");
	      JLabel s4_con2l = new JLabel("¼ö¸®ºñ¿ëÀÌ");
	      JTextField s4_con2f = new JTextField(5);
	      JLabel s4_con2l2 = new JLabel("ÀÌ»óÀÎ °í°´ Á¤º¸");
	      JButton s4b = new JButton("°Ë»ö");
	      
	      public Mpanel(){
	         this.setBackground(new Color(194,247,255));
	         
	         // ÀüÃ¼ ¹è°æ
	         northMP.setBackground(new Color(194,247,255));
	         
	         // È¸»ç, Ä·ÇÎÄ«, °í°´, Á¤ºñ¼Ò
	         northMP0.setBackground(new Color(174,222,229));
	         northMP1.setBackground(new Color(174,222,229));
	         northMP2.setBackground(new Color(174,222,229));
	         northMP3.setBackground(new Color(174,222,229));
	         
	         // Ä·ÇÎÄ« ¹ÝÈ¯ ³»¿ª/Á¡°Ë³»¿ª ÀúÀå
	         carReturnP.setBackground(new Color(202,230,242));
	         
	         // Á¤ºñ³»¿ª
	         carRepairP.setBackground(new Color(202,230,242));
	         
	         // °Ë»ö
	         sP.setBackground(new Color(194,247,255));
	         sP1.setBackground(new Color(174,195,229));
	         sP2.setBackground(new Color(174,195,229));
	         sP3.setBackground(new Color(174,195,229));
	         sP4.setBackground(new Color(174,195,229));	         
	         
	         //ÃÊ±âÈ­¹öÆ°
	         initB.addActionListener(this);
	         
	         //È¸»ç
	         comp_insertB.addActionListener(this);
	         comp_deleteB.addActionListener(this);
	         comp_convertB.addActionListener(this);
	         
	         //Ä·ÇÎÄ« µî·Ï
	         car_insertB.addActionListener(this);
	         car_deleteB.addActionListener(this);
	         car_convertB.addActionListener(this);
	         
	         //°í°´
	         insert_cust.addActionListener(this);
	         delete_cust.addActionListener(this);
	         convert_cust.addActionListener(this);
	         
	         //Á¤ºñ¼Ò
	         insert_gar.addActionListener(this);
	         delete_gar.addActionListener(this);
	         convert_gar.addActionListener(this);
	         
	         //Ä·ÇÎÄ«¹ÝÈ¯/Á¡°Ë³»¿ª ÀúÀå
	         return_car.addActionListener(this);
	         
	         //Á¤ºñ³»¿ª
	         request_car.addActionListener(this);
	         repair_delete.addActionListener(this);
	         repair_convert.addActionListener(this);
	         
	         //°Ë»ö
	         s1b.addActionListener(this);
	         s2b.addActionListener(this);
	         s3b.addActionListener(this);
	         s4b.addActionListener(this);	         
	         
	         manager.setPreferredSize(new Dimension(500,20));

	         northMP.add(manager);
	         northMP.add(initB); //ÃÊ±âÈ­
	         
	         // È¸»ç
	         northMP0.add(comp_namel);
	         northMP0.add(comp_namef);
	         northMP0.add(comp_addressl);
	         northMP0.add(comp_addressf);
	         northMP0.add(comp_phonel);
	         northMP0.add(comp_phonef);
	         northMP0.add(comp_personl);
	         northMP0.add(comp_personf);
	         northMP0.add(comp_emaill);
	         northMP0.add(comp_emailf); 
	                  
	         northMP0.add(comp_insertB); //È¸»ç ÀÔ·Â¹öÆ°
	         northMP0.add(comp_idl);
	         northMP0.add(comp_idf);
	         northMP0.add(comp_deleteB); //È¸»ç »èÁ¦¹öÆ°
	         northMP0.add(comp_convertB); // È¸»ç º¯°æ¹öÆ°
	         
	         // Ä·ÇÎÄ« 
	         northMP1.add(car_comp_idl);
	         northMP1.add(car_comp_idf);
	         northMP1.add(car_namel);
	         northMP1.add(car_namef);
	         northMP1.add(car_numl);
	         northMP1.add(car_numf);
	         northMP1.add(car_boardl);
	         northMP1.add(car_boardf);
	         northMP1.add(car_manucompl);
	         northMP1.add(car_manucompf);
	         northMP1.add(car_manudatel);
	         northMP1.add(car_manudatef);
	         northMP1.add(car_distancel);
	         northMP1.add(car_distancef);
	         northMP1.add(car_pricel);
	         northMP1.add(car_pricef);
	         northMP1.add(car_datel);
	         northMP1.add(car_datef);
	         northMP1.add(car_insertB); //Ä·ÇÎÄ« ÀÔ·Â¹öÆ°
	         northMP1.add(car_idl);
	         northMP1.add(car_idf);
	         northMP1.add(car_deleteB); //Ä·ÇÎÄ« »èÁ¦¹öÆ°
	         northMP1.add(car_convertB); //Ä·ÇÎÄ« º¯°æ¹öÆ°
	         
	         // °í°´
	         northMP2.add(cust_idl);
	         northMP2.add(cust_idf);
	         
	         northMP2.add(cust_namel);
	         northMP2.add(cust_namef);
	         
	         northMP2.add(cust_addressl);
	         northMP2.add(cust_addressf);
	         
	         northMP2.add(cust_phonel);
	         northMP2.add(cust_phonef);
	         
	         northMP2.add(cust_emaill);
	         northMP2.add(cust_emailf);
	         
	         northMP2.add(insert_cust); //°í°´ ÀÔ·Â ¹öÆ°
	         northMP2.add(delete_cust); //°í°´ »èÁ¦¹öÆ°
	         northMP2.add(convert_cust); //°í°´ º¯°æ¹öÆ°
	         
	         // Á¤ºñ¼Ò
	         northMP3.add(gar_namel);
	         northMP3.add(gar_namef);
	         
	         northMP3.add(gar_addressl);
	         northMP3.add(gar_addressf);
	         
	         northMP3.add(gar_phonel);
	         northMP3.add(gar_phonef);
	         
	         northMP3.add(gar_personl);
	         northMP3.add(gar_personf);
	         
	         northMP3.add(gar_emaill);
	         northMP3.add(gar_emailf);
	         
	         northMP3.add(insert_gar); //Á¤ºñ¼Ò ÀÔ·Â¹öÆ°
	         northMP3.add(gar_idl);
	         northMP3.add(gar_idf);
	         northMP3.add(delete_gar); //Á¤ºñ¼Ò »èÁ¦¹öÆ°
	         northMP3.add(convert_gar); //Á¤ºñ¼Ò º¯°æ¹öÆ°
	         
	         // Â÷ ¹ÝÈ¯ ÆÐ³Î
	         carReturnP.add(inspec_rentl);
	         carReturnP.add(inspec_rentf);
	         carReturnP.add(inspec_carl);
	         carReturnP.add(inspec_carf);
	         carReturnP.add(inspec_frontl);
	         carReturnP.add(inspec_frontf);
	         carReturnP.add(inspec_leftl);
	         carReturnP.add(inspec_leftf);
	         carReturnP.add(inspec_rightl);
	         carReturnP.add(inspec_rightf);
	         carReturnP.add(inspec_behindl);
	         carReturnP.add(inspec_behindf);
	         carReturnP.add(inspec_repairl);
	         carReturnP.add(inspec_repairf);
	         carReturnP.add(return_car); //Â÷¹ÝÈ¯
	         
	         
	         // Â÷ ¼ö¸® ¿äÃ» ÆÐ³Î   
	         carRepairP.add(repair_carl);
	         carRepairP.add(repair_carf);
	         carRepairP.add(repair_garl);
	         carRepairP.add(repair_garf);
	         carRepairP.add(repair_corpl);
	         carRepairP.add(repair_corpf);
	         carRepairP.add(repair_custl);
	         carRepairP.add(repair_custf);
	         carRepairP.add(repair_infol);
	         carRepairP.add(repair_infof);
	         carRepairP.add(repair_datel);
	         carRepairP.add(repair_datef);
	         carRepairP.add(repair_pricel);
	         carRepairP.add(repair_pricef);
	         carRepairP.add(repair_paydatel);
	         carRepairP.add(repair_paydatef);
	         carRepairP.add(repair_extral);
	         carRepairP.add(repair_extraf);
	         carRepairP.add(request_car); //Á¤ºñ³»¿ª ÀÔ·Â¹öÆ°
	         carRepairP.add(repair_idl);
	         carRepairP.add(repair_idf);
	         carRepairP.add(repair_delete); //Á¤ºñ³»¿ª »èÁ¦¹öÆ°
	         carRepairP.add(repair_convert); //Á¤ºñ³»¿ª º¯°æ¹öÆ°
	         
	         
	         // °Ë»ö1 ÆÐ³Î
	         sP1.add(s1_con1l);
	         sP1.add(s1_con1f);
	         sP1.add(s1_con1l2);
	         sP1.add(s1_con2l);
	         sP1.add(s1_con2f);
	         sP1.add(s1_con2l2);
	         sP1.add(s1b);
	         
	         // °Ë»ö2 ÆÐ³Î
	         sP2.add(s2_con1l);
	         sP2.add(s2_con1f);
	         sP2.add(s2_con1l2);
	         sP2.add(s2_con2l);
	         sP2.add(s2_con2f);
	         sP2.add(s2_con2l2);
	         sP2.add(s2b);
	         
	         // °Ë»ö3 ÆÐ³Î
	         sP3.add(s3_con1l);
	         sP3.add(s3_con1f);
	         sP3.add(s3_con1l2);
	         sP3.add(s3_con2l);
	         sP3.add(s3_con2f);
	         sP3.add(s3_con2l2);
	         sP3.add(s3b);
	         
	         // °Ë»ö3 ÆÐ³Î
	         sP4.add(s4_con1l);
	         sP4.add(s4_con1f);
	         sP4.add(s4_con1l2);
	         sP4.add(s4_con2l);
	         sP4.add(s4_con2f);
	         sP4.add(s4_con2l2);
	         sP4.add(s4b);
	         
	         // °Ë»ö°á°ú
	         result.setEditable(false);
	         JScrollPane scroll = new JScrollPane(result);
	         resultP.add(scroll);         
	         
	         add(northMP);
	         northMP.setPreferredSize(new Dimension(580,40));
	         add(northMP0);
	         northMP0.setPreferredSize(new Dimension(580,70));
	         add(northMP1);
	         northMP1.setPreferredSize(new Dimension(580,95));
	         add(northMP2);
	         northMP2.setPreferredSize(new Dimension(580,70));
	         add(northMP3);
	         northMP3.setPreferredSize(new Dimension(580,100));
	         add(carReturnP);
	         carReturnP.setPreferredSize(new Dimension(580,70));
	         add(carRepairP);
	         carRepairP.setPreferredSize(new Dimension(580,125));
	         
	         add(sP1);
	         sP1.setPreferredSize(new Dimension(580,40));
	         add(sP2);
	         sP2.setPreferredSize(new Dimension(580,40));
	         add(sP3);
	         sP3.setPreferredSize(new Dimension(580,40));
	         add(sP4);
	         sP4.setPreferredSize(new Dimension(580,40));
	        
	         add(scroll);
	         scroll.setPreferredSize(new Dimension(580,200));
	         
	      }

	      
	      @Override
	      public void actionPerformed(ActionEvent e) {
	         int rowcount;
	         String query;
	          try {               
	              stmt = con.createStatement();
	               
	              	//ÃÊ±âÈ­ ¹öÆ°
	                if(e.getSource()==initB) {
	                   System.out.println("ÃÊ±âÈ­ ¹öÆ° Å¬¸¯!");
	                   
	                 stmt.execute("DROP DATABASE IF EXISTS  madang");
	                 stmt.execute("create database madang");
	                 stmt.execute("grant all privileges on madang.* to madang@localhost with grant option");
	                 stmt.execute("commit");
	                 
	                 // -- MySQL Workbench Forward Engineering
	                 
	                 stmt.execute("SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0");
	                 stmt.execute("SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0");
	                 stmt.execute("SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION'");
	                 
	                 /* -- -----------------------------------------------------
						-- Schema madang
						-- -----------------------------------------------------*/
	                 stmt.execute("CREATE SCHEMA IF NOT EXISTS `madang` DEFAULT CHARACTER SET utf8 ");
	                 stmt.execute("USE `madang` ");
	                 
	                 /* -- -----------------------------------------------------
						-- Table `madang`.`Company`
						-- -----------------------------------------------------*/
	                 stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Company` (\r\n" + 
	                 		"  `comp_id` INT NOT NULL,\r\n" + 
	                 		"  `comp_name` VARCHAR(45) NULL,\r\n" + 
	                 		"  `comp_address` VARCHAR(45) NULL,\r\n" + 
	                 		"  `comp_phone` VARCHAR(45) NULL,\r\n" + 
	                 		"  `comp_person` VARCHAR(45) NULL,\r\n" + 
	                 		"  `comp_email` VARCHAR(45) NULL,\r\n" + 
	                 		"  PRIMARY KEY (`comp_id`))");
	                 //stmt.execute("ENGINE = InnoDB");
	                 
	                 /* -- -----------------------------------------------------
						-- Table `madang`.`Car`
						-- -----------------------------------------------------*/
	                 stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Car` (\r\n" + 
	                 		"  `car_id` INT NOT NULL,\r\n" + 
	                 		"  `car_name` VARCHAR(45) NULL,\r\n" + 
	                 		"  `car_num` VARCHAR(45) NULL,\r\n" + 
	                 		"  `car_board` INT NULL,\r\n" + 
	                 		"  `car_manucomp` VARCHAR(45) NULL,\r\n" + 
	                 		"  `car_manudate` VARCHAR(45) NULL,\r\n" + 
	                 		"  `car_distance` INT NULL,\r\n" + 
	                 		"  `car_price` INT NULL,\r\n" + 
	                 		"  `car_date` DATE NULL,\r\n" + 
	                 		"  `Company_comp_id` INT NOT NULL,\r\n" + 
	                 		"  PRIMARY KEY (`car_id`, `Company_comp_id`),\r\n" + 
	                 		"  INDEX `fk_Car_Company_idx` (`Company_comp_id` ASC) VISIBLE,\r\n" + 
	                 		"  CONSTRAINT `fk_Car_Company`\r\n" + 
	                 		"    FOREIGN KEY (`Company_comp_id`)\r\n" + 
	                 		"    REFERENCES `madang`.`Company` (`comp_id`)\r\n" + 
	                 		"    ON DELETE NO ACTION\r\n" + 
	                 		"    ON UPDATE NO ACTION)");
	                 //stmt.execute("ENGINE = InnoDB");
	                 
	                 /* -- -----------------------------------------------------
						-- Table `madang`.`Customer`
						-- -----------------------------------------------------*/
	                 stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Customer` (\r\n" + 
	                 		"  `cust_id` VARCHAR(45) NOT NULL,\r\n" + 
	                 		"  `cust_name` VARCHAR(45) NULL,\r\n" + 
	                 		"  `cust_address` VARCHAR(45) NULL,\r\n" + 
	                 		"  `cust_phone` VARCHAR(45) NULL,\r\n" + 
	                 		"  `cust_email` VARCHAR(45) NULL,\r\n" + 
	                 		"  PRIMARY KEY (`cust_id`))");
	                 //stmt.execute("ENGINE = InnoDB");
	                 
	                 /* -- -----------------------------------------------------
						-- Table `madang`.`Rent`
						-- -----------------------------------------------------*/
	                 stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Rent` (\r\n" + 
	                 		"  `rent_id` INT NOT NULL,\r\n" + 
	                 		"  `rent_start` DATE NULL,\r\n" + 
	                 		"  `rent_period` INT NULL,\r\n" + 
	                 		"  `rent_price` INT NULL,\r\n" + 
	                 		"  `rent_paydate` DATE NULL,\r\n" + 
	                 		"  `rent_extra` VARCHAR(45) NULL,\r\n" + 
	                 		"  `rent_extrafee` INT NULL,\r\n" + 
	                 		"  `Car_car_id` INT NOT NULL,\r\n" + 
	                 		"  `Car_Company_comp_id` INT NOT NULL,\r\n" + 
	                 		"  `Customer_cust_id` VARCHAR(45) NOT NULL,\r\n" + 
	                 		"  PRIMARY KEY (`rent_id`, `Car_car_id`),\r\n" + 
	                 		"  INDEX `fk_Rent_Car1_idx` (`Car_Company_comp_id` ASC) VISIBLE,\r\n" + 
	                 		"  INDEX `fk_Rent_Customer1_idx` (`Customer_cust_id` ASC) VISIBLE,\r\n" + 
	                 		"  CONSTRAINT `fk_Rent_Car1`\r\n" + 
	                 		"    FOREIGN KEY (`Car_Company_comp_id`)\r\n" + 
	                 		"    REFERENCES `madang`.`Car` (`Company_comp_id`)\r\n" + 
	                 		"    ON DELETE NO ACTION\r\n" + 
	                 		"    ON UPDATE NO ACTION,\r\n" + 
	                 		"  CONSTRAINT `fk_Rent_Customer1`\r\n" + 
	                 		"    FOREIGN KEY (`Customer_cust_id`)\r\n" + 
	                 		"    REFERENCES `madang`.`Customer` (`cust_id`)\r\n" + 
	                 		"    ON DELETE NO ACTION\r\n" + 
	                 		"    ON UPDATE NO ACTION)");
	                 //stmt.execute("ENGINE = InnoDB");
	                 
	                 /* -- -----------------------------------------------------
						-- Table `madang`.`Inspection`
						-- -----------------------------------------------------*/
	                 stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Inspection` (\r\n" + 
	                 		"  `frontinfo` VARCHAR(45) NULL,\r\n" + 
	                 		"  `leftinfo` VARCHAR(45) NULL,\r\n" + 
	                 		"  `rightinfo` VARCHAR(45) NULL,\r\n" + 
	                 		"  `behindinfo` VARCHAR(45) NULL,\r\n" + 
	                 		"  `repairinfo` TINYINT NULL,\r\n" + 
	                 		"  `Rent_rent_id` INT NOT NULL,\r\n" + 
	                 		"  `Rent_Car_car_id` INT NOT NULL,\r\n" + 
	                 		"  INDEX `fk_Inspection_Rent1_idx` (`Rent_rent_id` ASC, `Rent_Car_car_id` ASC) VISIBLE,\r\n" + 
	                 		"  PRIMARY KEY (`Rent_rent_id`),\r\n" + 
	                 		"  CONSTRAINT `fk_Inspection_Rent1`\r\n" + 
	                 		"    FOREIGN KEY (`Rent_rent_id` , `Rent_Car_car_id`)\r\n" + 
	                 		"    REFERENCES `madang`.`Rent` (`rent_id` , `Car_car_id`)\r\n" + 
	                 		"    ON DELETE NO ACTION\r\n" + 
	                 		"    ON UPDATE NO ACTION)");
	                 //stmt.execute("ENGINE = InnoDB");
	                 
	                 /* -- -----------------------------------------------------
						-- Table `madang`.`Garage`
						-- -----------------------------------------------------*/
	                 stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Garage` (\r\n" + 
	                 		"  `gar_id` INT NOT NULL,\r\n" + 
	                 		"  `gar_name` VARCHAR(45) NULL,\r\n" + 
	                 		"  `gar_address` VARCHAR(45) NULL,\r\n" + 
	                 		"  `gar_phone` VARCHAR(45) NULL,\r\n" + 
	                 		"  `gar_person` VARCHAR(45) NULL,\r\n" + 
	                 		"  `gar_email` VARCHAR(45) NULL,\r\n" + 
	                 		"  PRIMARY KEY (`gar_id`))");
	                 //stmt.execute("ENGINE = InnoDB");
	                 
	                 /* -- -----------------------------------------------------
						-- Table `madang`.`Repair`
						-- -----------------------------------------------------*/
	                 stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Repair` (\r\n" + 
	                 		"  `repair_id` INT NOT NULL,\r\n" + 
	                 		"  `repair_info` VARCHAR(45) NULL,\r\n" + 
	                 		"  `repair_date` DATE NULL,\r\n" + 
	                 		"  `repair_price` INT NULL,\r\n" + 
	                 		"  `repair_paydate` DATE NULL,\r\n" + 
	                 		"  `repair_extra` VARCHAR(45) NULL,\r\n" + 
	                 		"  `Garage_gar_id` INT NOT NULL,\r\n" + 
	                 		"  `Car_car_id` INT NOT NULL,\r\n" + 
	                 		"  `Car_Company_comp_id` INT NOT NULL,\r\n" + 
	                 		"  `Customer_cust_id` VARCHAR(45) NOT NULL,\r\n" + 
	                 		"  PRIMARY KEY (`repair_id`),\r\n" + 
	                 		"  INDEX `fk_Repair_Garage1_idx` (`Garage_gar_id` ASC) VISIBLE,\r\n" + 
	                 		"  INDEX `fk_Repair_Car1_idx` (`Car_car_id` ASC, `Car_Company_comp_id` ASC) VISIBLE,\r\n" + 
	                 		"  INDEX `fk_Repair_Customer1_idx` (`Customer_cust_id` ASC) VISIBLE,\r\n" + 
	                 		"  CONSTRAINT `fk_Repair_Garage1`\r\n" + 
	                 		"    FOREIGN KEY (`Garage_gar_id`)\r\n" + 
	                 		"    REFERENCES `madang`.`Garage` (`gar_id`)\r\n" + 
	                 		"    ON DELETE NO ACTION\r\n" + 
	                 		"    ON UPDATE NO ACTION,\r\n" + 
	                 		"  CONSTRAINT `fk_Repair_Car1`\r\n" + 
	                 		"    FOREIGN KEY (`Car_car_id` , `Car_Company_comp_id`)\r\n" + 
	                 		"    REFERENCES `madang`.`Car` (`car_id` , `Company_comp_id`)\r\n" + 
	                 		"    ON DELETE NO ACTION\r\n" + 
	                 		"    ON UPDATE NO ACTION,\r\n" + 
	                 		"  CONSTRAINT `fk_Repair_Customer1`\r\n" + 
	                 		"    FOREIGN KEY (`Customer_cust_id`)\r\n" + 
	                 		"    REFERENCES `madang`.`Customer` (`cust_id`)\r\n" + 
	                 		"    ON DELETE NO ACTION\r\n" + 
	                 		"    ON UPDATE NO ACTION)");
	                 //stmt.execute("ENGINE = InnoDB");
	                 
	                 
	                 stmt.execute("SET SQL_MODE=@OLD_SQL_MODE");
	                 stmt.execute("SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS");
	                 stmt.execute("SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS");
	                 
	                 // company Æ©ÇÃ Ãß°¡
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(1,'°¡ÀÚÄ·ÇÎ','¼­¿ï ±¤Áø±¸','010-1253-5995','°¡ÀÚ¿µ','gocamping@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(2,'Ä·ÇÎÇÏ¸éÄ·ÇÎÄ«','¼­¿ï ¾çÃµ±¸','010-2954-1935','°í±æµ¿','gildong@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(3,'ÇÔ²²Ä·ÇÎ','ÀÎÃµ ºÎÆò±¸','010-8424-5357','ÀÌÇÏ´Ã','together@gmail.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(4,'½ºÅ¸Ä·ÇÎ','¼­¿ï °­³²±¸','010-7979-2462','Á¤¿ì¼º','starcamp@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(5,'Ä£±¸¿ÍÄ·ÇÎ','°æ±âµµ ¼ö¿ø½Ã','010-6423-1112','Àå±¸¹Ì','friendcamp@gmail.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(6,'Â÷¿¡¼­ÀÚ´Â¿©Çà','°æ±âµµ ÆòÅÃ½Ã','010-9669-7985','ÀÚ¹Î¼ö','sleepcamp@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(7,'Ä·ÇÎÄ«´Â¿©±â¾ß','ºÎ»ê ÇØ¿î´ë','010-2674-8831','¿©±â¾ß','herecamp@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(8,'´Ù°°ÀÌÄ·ÇÎÄ«','°­¿øµµ °­¸ª','010-1335-7524','¸ðµÎ¸®','moducamping@gmail.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(9,'½æÄ·ÇÎ','Àü¶óºÏµµ ÀüÁÖ','010-3345-7511','ÀÌ¼öÁø','somecamp@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(10,'ÆÛ½ºÆ®Ä·ÇÎÄ«','¼­¿ï ¿µµîÆ÷±¸','010-0023-0543','¹Ú¼ö¿µ','firstcamp@gmail.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(11,'´ºÄ·ÇÎÄ«·»Æ®','°æ±âµµ È­¼º½Ã','010-1168-9975','ÇÑ³ª·¡','newrent@gmail.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(12,'´õÁÁÀºÄ·ÇÎÄ«','ºÎ»ê Áß±¸','010-5555-6654','Á¶Àº¼­','goodcamping@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(13,'·ê·ç¶ö¶óÄ·ÇÎ','ÃæÃ»ºÏµµ Ã»ÁÖ½Ã','010-1128-8975','¹ÚÀÌ½½','llullu@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(14,'¸ÅÄ¡Ä·ÇÎÄ«·»Æ®','°æ±âµµ °¡Æò','010-3353-5542','±èÃ¤¿µ','matchrent@gmail.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(15,'ÇÃ¶ó¿öÄ·ÇÎÄ«','¼­¿ï Á¾·Î±¸','010-3377-5456','ÀÌ¿ì¼º','flowercamping@naver.com');");
	                 
	                 // customer Æ©ÇÃ Ãß°¡
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values ('12-24-123456-02', 'ÇÑ°­¹è', 'ºÎ»ê', '010-1234-2523', 'hanriver@as.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('11-20-578234-23' , '°­¿©¸°', '¼­¿ï', '010-2032-1242', 'strong14@bs.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('13-15-234923-25', 'ÃÖÁøµ¿', '°æ±â', '010-4823-2932', 'jindong@ls.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('12-29-363106-74', '¼Û¹Ì¶õ', 'ºÎ»ê', '010-2258-1125', 'ssong@bs.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('15-49-295264-19', 'Áö¼±¿ì', 'ÃæºÏ', '010-2592-2106', 'zizi@as.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('17-21-754167-55', 'ÀÌÅÂ¿À', 'ÀüºÏ', '010-9823-8216', 'bagegar@as.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('16-82-024902-21', '¿©´Ù°æ', 'Ãæ³²', '010-2482-9325', 'jennie@ls.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('15-52-430802-11', '°í¿¹¸²', 'ÃæºÏ', '010-3552-1051', 'jeolmi@bs.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('18-76-080706-66', '¼ÕÁ¦Çõ', 'Àü³²', '010-3203-0203', 'hand31@bs.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('19-41-104824-25', '¼³¸í¼÷', '°æºÏ', '010-2062-0432', 'jaesueobs@ls.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('20-15-429232-62', '¿©º´±Ô', '°æ³²', '010-7869-2914', 'gyugyu@as.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('21-52-203192-25', '¾öÈ¿Á¤', 'Á¦ÁÖ', '010-2033-2932', 'eomwo@as.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('11-23-527823-23', 'ÀÌÁØ¿µ', '¼­¿ï', '010-2402-1024', 'noans@ls.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('11-39-293826-72', '¹ÎÇö¼­', '¼­¿ï', '010-3406-2460', 'min51@as.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('13-21-391283-52', '¹ÚÀÎ±Ô', '°æ±â', '010-7632-1851', 'sseule@bs.com');");
	                 
	                 // garage Æ©ÇÃ Ãß°¡
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (1,'Á¶¹Ú»çÄ«Á¤ºñ¼Ò', '¼­¿ï', '010-1025-2012', 'ÀÌÀÍÁØ', 'ask@ab.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (2,'¿ì¸®Ä«¼¾ÅÍ', '°æ±â', '010-1144-1234', '¾ÈÁ¤¿ø', 'ksr@bb.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (3,'ÇÑµ¶ÀÚµ¿ÀÚÁ¤ºñ¼Ò', 'ºÎ»ê', '010-2524-1414', '±èÁØ¿Ï', 'lij@abc.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (4,'°Ç±¹ÀÚµ¿Â÷Á¤ºñ¼Ò', 'Á¦ÁÖ', '010-2020-1919', '¾ç¼®Çü', 'sd@cc.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (5,'¼¼Á¾Ä«¼¾Å¸', 'ÃæºÏ', '010-1717-7171', 'Ã¤¼ÛÈ­', 'wes@ab.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (6,'Ä«½Ã½ºÀÚµ¿Â÷Á¤ºñ¼Ò', 'Ãæ³²', '010-1515-5151', 'Á¤·Î»ç', 'kjw@ab.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (7,'¸Þ¸ð¸®Ä«', 'ÃæºÏ', '010-1616-6161', 'ÁÖÁ¾¼ö', 'a12@bb.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (8,'È­¾ç°Ë»çÁ¤ºñ»ç¾÷¼Ò', 'Àü³²', '010-1414-4141', 'µµÀçÇÐ', 'hgj@ab.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (9,'ÇÑ¾çÀÚµ¿Â÷Á¤ºñ°ø¾÷»ç', 'ÀüºÏ', '010-1313-3131', '¿ë¼®¹Î', 'sk1s@bb.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (10,'ÇÏ´ÃÄ«¼¾ÅÍ', '°æ±â', '010-2323-3232', 'Àå°Ü¿ï', 'bgh@ab.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (11,'ÅÂ¾çÄ«¼¾ÅÍ', '¼­¿ï', '010-5252-2324', '¾ÈÄ¡È«', 'q21rd3@cc.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (12,'¼¼ÀÍÄ«¼¾ÅÍ', '°æ±â', '010-6363-3636', 'ºÀ±¤Çö', 'djth@ab.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (13,'´ÚÅÍÄ«Á¤ºñ¼Ò', 'Á¦ÁÖ', '010-3737-7373', 'Ãß¹ÎÇÏ', 'jiaqdos@cc.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (14,'ÀÚµ¿Â÷¼¼»ó', 'ÃæºÏ', '010-2929-9292', 'Çã¼±ºó', 'sfdklj@abc.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (15,'°­º¯Ä«¼¾ÅÍ', 'ºÎ»ê', '010-1529-3921', '¸íÀº¿ø', 'uqpr@bb.com');");
	                  
	                 // car Æ©ÇÃ Ãß°¡
	                 stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                  		+ "values(1,'car1','23°¡ 1234',5,'Çö´ë','2016',1000,11000,'2020-06-06',1);");
	                  carBoolean[1]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                   		+ "values(2,'car2','19¶ó 2634',6,'±â¾Æ','2014',7000,10000,'2020-06-13',2);");            
	                  carBoolean[2]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                   		+ "values(3,'car3','15¸¶ 9231',3,'º¥Ã÷','2015',5000,16000,'2020-06-20',3);");
	                  carBoolean[3]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                   		+ "values(4,'car4','12´Ù 4712',8,'Çö´ë','2016',10000,8500,'2020-06-27',4);");             
	                  carBoolean[4]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                   		+ "values(5,'car5','25Â÷ 0293',7,'Jeep','2016',8000,14000,'2020-06-12',5);");             
	                  carBoolean[5]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                   		+ "values(6,'car6','21´õ 1012',8,'±â¾Æ','2013',40000,9000,'2020-06-01',6);");
	                  carBoolean[6]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                   		+ "values(7,'car7','17³ª 1539',9,'Çö´ë','2018',1000,10000,'2020-06-05',7);");
	                  carBoolean[7]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                   		+ "values(8,'car8','24ÇÏ 1278',5,'Çö´ë','2017',7000,7600,'2020-06-12',8);");
	                  carBoolean[8]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                    		+ "values(9,'car9','10¸¶ 7203',4,'º¼º¸','2015',17000,9500,'2020-06-24',9);");
	                  carBoolean[9]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                     		+ "values(10,'car10','13·¯ 8129',3,'º¼º¸','2016',19000,10000,'2020-06-30',10);");
	                  carBoolean[10]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                     		+ "values(11,'car11','27¶ó 4822',8,'º¥Ã÷','2013',47000,18000,'2020-06-08',11);");
	                  carBoolean[11]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                     		+ "values(12,'car12','22È£ 4831',6,'Jeep','2015',27000,19000,'2020-06-07',12);");
	                  carBoolean[12]=true;             
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                  		+ "values(13,'car13','17¸ð 6362',3,'Jeep','2015',27500,17500,'2020-06-19',13);");
	                  carBoolean[13]=true;             
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                  		+ "values(14,'car14','26Çã 1243',8,'Çö´ë','2017',22000,10000,'2020-06-27',14);");
	                  carBoolean[14]=true;             
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                  		+ "values(15,'car15','22¶ó 1920',4,'µð½ºÄ¿¹ö¸®','2018',18500,13000,'2020-06-25',15);");
	                  carBoolean[15]=true;
	                  
	                  // Ä·ÇÎÄ« ´ë¿©
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                       +"values(1,'2020-05-05',2,30000,'2020-05-03','´ã¿ä',2000,1,1,'11-20-578234-23');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        +"values(2,'2020-05-07',2,30000,'2020-05-05','¶ó¸é',1000,2,2,'11-23-527823-23');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        +"values(3,'2020-05-10',3,40000,'2020-05-08','´ã¿ä',2000,3,3,'11-39-293826-72');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        +"values(4,'2020-05-11',2,35000,'2020-05-09','½ºÇÇÄ¿',12000,4,4,'12-24-123456-02');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        +"values(5,'2020-05-06',4,50000,'2020-05-04','ÇÏ¸ð´ÏÄ«',5000,5,5,'12-29-363106-74');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                         +"values(6,'2020-05-21',3,40000,'2020-05-19','¹°',3000,6,6,'13-15-234923-25');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                         +"values(7,'2020-05-15',2,30000,'2020-05-13','¸ð±â¾à',7000,7,7,'13-21-391283-52');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                         +"values(8,'2020-05-17',4,50000,'2020-05-15','¶ó¸é',2000,8,8,'15-49-295264-19');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                         +"values(9,'2020-05-18',3,40000,'2020-05-16','ÇÏ¸ð´ÏÄ«',5000,9,9,'15-52-430802-11');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                         +"values(10,'2020-05-13',2,20000,'2020-05-11','¶ó¸é',3000,10,10,'12-29-363106-74');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                          +"values(11,'2020-05-26',3,30000,'2020-05-24','¸ð±â¾à',2500,11,11,'16-82-024902-21');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                          +"values(12,'2020-05-23',4,50000,'2020-05-21','¹°',5000,12,12,'17-21-754167-55');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                          +"values(13,'2020-05-30',2,20000,'2020-05-28','¸ð±â¾à',6000,13,13,'17-21-754167-55');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                          +"values(14,'2020-05-03',2,20000,'2020-05-01','¶ó¸é',2000,14,14,'20-15-429232-62');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                          +"values(15,'2020-05-21',3,35000,'2020-05-19','ÇÏ¸ð´ÏÄ«',3500,15,15,'21-52-203192-25');");               
	                  
	                  // inspection Æ©ÇÃ Ãß°¡                  
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                  			+ "values('½ºÅ©·¡Ä¡','ÀÌ»ó ¹«','ÀÌ»ó ¹«','ÀÌ»ó ¹«',true,1,1);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('½ºÅ©·¡Ä¡','½ºÅ©·¡Ä¡','ÀÌ»ó ¹«','ÀÌ»ó ¹«',true,2,2);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('ÀÌ»ó ¹«','ÀÌ»ó ¹«','½ºÅ©·¡Ä¡','ÈÄ¹æÀüµî °íÀå',true,3,3);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('½ºÅ©·¡Ä¡','ÀÌ»ó ¹«','ÀÌ»ó ¹«','ÀÌ»ó ¹«',true,4,4);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('½ºÅ©·¡Ä¡','½ºÅ©·¡Ä¡','ÀÌ»ó ¹«','ÀÌ»ó ¹«',true,5,5);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('ÀÌ»ó ¹«','ÀÌ»ó ¹«','ÀÌ»ó ¹«','¹üÆÛ ¸Á°¡Áü',true,6,6);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('¿ÍÀÌÆÛ °íÀå','ÀÌ»ó ¹«','ÀÌ»ó ¹«','ÀÌ»ó ¹«',true,7,7);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('ÀÌ»ó ¹«','ÀÌ»ó ¹«','ÀÌ»ó ¹«','Æ®··Å© °íÀå',true,8,8);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('½ºÅ©·¡Ä¡','ÀÌ»ó ¹«','ÀÌ»ó ¹«','ÀÌ»ó ¹«',true,9,9);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('½ºÅ©·¡Ä¡','ÀÌ»ó ¹«','ÀÌ»ó ¹«','ÀÌ»ó ¹«',true,10,10);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('ÀÌ»ó ¹«','¹® °íÀå','ÀÌ»ó ¹«','ÀÌ»ó ¹«',true,11,11);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('½ºÅ©·¡Ä¡','ÀÌ»ó ¹«','¹® °íÀå','ÀÌ»ó ¹«',true,12,12);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('¶óÀÌÆ® °íÀå','ÀÌ»ó ¹«','ÀÌ»ó ¹«','ÀÌ»ó ¹«',true,13,13);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('ÀÌ»ó ¹«','ÀÌ»ó ¹«','ÀÌ»ó ¹«','¹üÆÛ °íÀå',true,14,14);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('½ºÅ©·¡Ä¡','½ºÅ©·¡Ä¡','½ºÅ©·¡Ä¡','ÀÌ»ó ¹«',true,15,15);");
	                  
	                  // Á¤ºñ³»¿ª
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(1,'¹é¹Ì·¯ ±³Ã¼,½ºÅ©·¡Ä¡Á¦°Å','2020-05-10',20000,'2020-05-12','¿¹¹æÁ¡°Ë¼­ºñ½º',1,1,1,'11-20-578234-23');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(2,'ÈÄ¹æÀüµî ±³Ã¼','2020-05-12',60000,'2020-05-14','»ì±ÕÅ»Ãë¼­ºñ½º',2,2,2,'11-23-527823-23');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(3,'¿¬·áÇÊÅÍ Å»ºÎÂø,½ºÅ©·¡Ä¡Á¦°Å','2020-05-16',70000,'2020-05-18','¿¹¹æÁ¡°Ë¼­ºñ½º',3,3,3,'11-39-293826-72');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(4,'¿¡¾îÄÁ ¿¡¾îÇÊÅÍ Å»ºÎÂø,½ºÅ©·¡Ä¡Á¦°Å','2020-05-16',30000,'2020-05-18','¿£ÁøÁ¡°Ë¼­ºñ½º',4,4,4,'12-24-123456-02');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(5,'¿£Áø¿ÀÀÏ ±³È¯,½ºÅ©·¡Ä¡Á¦°Å','2020-05-13',50000,'2020-05-15','»ì±ÕÅ»Ãë¼­ºñ½º',5,5,5,'12-29-363106-74');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(6,'µÚ¹üÆÛ ±³Ã¼','2020-05-27',100000,'2020-05-29','¿¹¹æÁ¡°Ë¼­ºñ½º',6,6,6,'13-15-234923-25');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(7,'¿ÍÀÌÆÛ ±³Ã¼','2020-05-20',20000,'2020-05-22','¿£ÁøÁ¡°Ë¼­ºñ½º',7,7,7,'13-21-391283-52');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(8,'Æ®··Å© ¼ö¸®','2020-05-24',20000,'2020-05-22','¿¹¹æÁ¡°Ë¼­ºñ½º',8,8,8,'15-49-295264-19');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(9,'¿£Áø¿ÀÀÏ ±³Ã¼,½ºÅ©·¡Ä¡Á¦°Å','2020-05-24',50000,'2020-05-26','»ì±ÕÅ»Ãë¼­ºñ½º',9,9,9,'15-52-430802-11');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(10,'¾Õ¹üÆÛ ±³Ã¼,½ºÅ©·¡Ä¡Á¦°Å','2020-05-18',70000,'2020-05-20','¿¹¹æÁ¡°Ë¼­ºñ½º',10,10,10,'12-29-363106-74');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(11,'¹® ±³Ã¼','2020-05-28',30000,'2020-05-30','»ì±ÕÅ»Ãë¼­ºñ½º',11,11,11,'16-82-024902-21');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(12,'¹® ±³Ã¼, ½ºÅ©·¡Ä¡Á¦°Å','2020-05-30',50000,'2020-06-01','»ì±ÕÅ»Ãë¼­ºñ½º',12,12,12,'17-21-754167-55');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(13,'¶óÀÌÆ® ±³Ã¼','2020-06-04',20000,'2020-06-06','¿£ÁøÁ¡°Ë¼­ºñ½º',13,13,13,'17-21-754167-55');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(14,'µÚ¹üÆÛ ±³Ã¼','2020-05-08',50000,'2020-05-10','¿¹¹æÁ¡°Ë¼­ºñ½º',14,14,14,'20-15-429232-62');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(15,'¿¡¾îÄÁ ¿¡¾îÇÊÅÍ Å»ºÎÂø,½ºÅ©·¡Ä¡Á¦°Å','2020-05-27',50000,'2020-05-29','»ì±ÕÅ»Ãë¼­ºñ½º',15,15,15,'21-52-203192-25');");
	                  
	                  System.out.println("ÃÊ±âÈ­ ¿Ï·á!");
	                }
	                
	                //È¸»ç ÀÔ·Â¹öÆ°
	                else if(e.getSource()==comp_insertB) {
	                	System.out.println("È¸»ç ÀÔ·Â¹öÆ° Å¬¸¯!");
	                	
	                	Boolean isError = false;
	                	Boolean phoneError = false;
	                	Boolean emailError = false;
	                	int comp_idI;
	                	String comp_nameS = comp_namef.getText();
	                	String comp_addressS = comp_addressf.getText();
	                	String comp_phoneS = comp_phonef.getText();
	                	String comp_personS = comp_personf.getText();
	                	String comp_emailS = comp_emailf.getText();
	                   
	                   
	                	// ºóÄ­ ÀÖ´ÂÁö È®ÀÎ
	                	if(comp_nameS.length()==0 || comp_addressS.length()==0 || comp_phoneS.length()==0 || comp_personS.length()==0 || comp_emailS.length()==0) {
	                		System.out.println("[¿À·ù¹ß»ý : µ¥ÀÌÅÍ ÀÔ·Â ¾øÀ½] È¸»ç ID¸¦ Á¦¿ÜÇÑ ¸ðµç Ä­¿¡ µ¥ÀÌÅÍ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
	               			isError=true;
	                	}
	                	else {
	                		
	                		// phone ¿À·ù È®ÀÎ
	                		String p1, p2, p3;
				            StringTokenizer st = new StringTokenizer(comp_phoneS,"-");
				            p1=st.nextToken();
				            if(p1.length()!=3 || !CheckNumber(p1)) phoneError=true;
				            if(!st.hasMoreTokens())  phoneError=true;
				            else{
				            	p2=st.nextToken();
				            	if(p2.length()!=4 || !CheckNumber(p2)) phoneError=true;
				            	if(!st.hasMoreTokens()) phoneError=true;
				            	else {
				            		p3=st.nextToken();
				            		if(p3.length()!=4 || !CheckNumber(p3)) phoneError=true;
				            	}
				            }
				       
				            if(phoneError) {
				            	System.out.println("[¿À·ù¹ß»ý : ÀüÈ­¹øÈ£] ÀüÈ­¹øÈ£´Â XXX-XXXX-XXXX ¿©¾ß ÇÕ´Ï´Ù.(XÀÇ ¼ö´Â 3,4,4·Î Á¦ÇÑ, X´Â ¼ýÀÚ¿©¾ßÇÔ)");
				        		isError=true;
				             }
				             
				            // email ¿À·ù È®ÀÎ
				            String e1,e2=null,e3=null;
				            st = new StringTokenizer(comp_emailS,"@");
				            e1=st.nextToken();
				            if(!st.hasMoreTokens())  emailError=true;
				            else {
				            	e2=st.nextToken();
				            	 
				            	st = new StringTokenizer(e2,".");
				            	if(!st.hasMoreTokens()) emailError=true;
				            	else{
				            		e2=st.nextToken();
				            		if(!st.hasMoreTokens()) emailError=true;
				            		else {
				            			e3=st.nextToken();
					            	}
					            }
				            	
				           }
				            
				           if(emailError) {
				        	   System.out.println("[¿À·ù¹ß»ý : ÀÌ¸ÞÀÏ] ÀÌ¸ÞÀÏÀº XXX@XXX.XXX ¿©¾ß ÇÕ´Ï´Ù.(XÀÇ ¼ö´Â Á¦ÇÑ ¾øÀ½)");
				        	   isError=true;
				           }
		                   
				             
				           // ¿À·ù°¡ ¾øÀ¸¸é »ðÀÔ 
		                   if(!isError) {
		                	   query="SELECT * FROM company";
		                       rs=stmt.executeQuery(query);
		                       rs.last();
		                       comp_idI=rs.getInt(1)+1;
		                       
		                       PreparedStatement statement = null;
		                       statement = con.prepareStatement("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email)"+"value(?,?,?,?,?,?)");
		              
		                       statement.setInt(1,comp_idI);
		                       statement.setString(2, comp_nameS);
		                       statement.setString(3, comp_addressS);
		                       statement.setString(4, comp_phoneS);
		                       statement.setString(5, comp_personS);
		                       statement.setString(6, comp_emailS);
		                       
		                	   System.out.println("¿À·ù¾øÀ½");
		                	   statement.executeUpdate();
				             
		                	   System.out.println("Ä·ÇÎÄ« ´ë¿©È¸»ç ÀÔ·Â ¿Ï·áµÇ¾ú½À´Ï´Ù.");
		                	   System.out.println("È¸»ç ID \t È¸»ç ÀÌ¸§ \tÁÖ¼Ò \tÀüÈ­¹øÈ£ \t\t´ã´çÁ÷¿ø \tÀÌ¸ÞÀÏ");
		                   
		                	   rs=stmt.executeQuery(query);
		                	   rs.last();
		                   
		                	   String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)+ "\t" + rs.getString(5) + "\t" + rs.getString(6);
		                	   System.out.println(str);
		                    }
	                   }
	                }
	                
	                //È¸»ç »èÁ¦¹öÆ°
	                else if(e.getSource()==comp_deleteB) {
	                	System.out.println("È¸»ç »èÁ¦ ¹öÆ° Å¬¸¯!");
	                	
	                	String comp_idS = comp_idf.getText();
	                	
	                	if(comp_idS.length()==0) {
	                   		System.out.println("[¿À·ù¹ß»ý : µ¥ÀÌÅÍ ÀÔ·Â ¾øÀ½] È¸»ç ID¿¡ µ¥ÀÌÅÍ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
	                	}
	                	
	                	else {
		                	if(!CheckNumber(comp_idS)) {
		                		System.out.println("[¿À·ù¹ß»ý : È¸»ç ID] È¸»ç ID´Â ¾çÀÇ Á¤¼ö¿©¾ß ÇÕ´Ï´Ù.");
		                	}
		                	else{
		                		int comp_idI = Integer.parseInt(comp_idS);
			                	query = "SELECT * FROM company WHERE comp_id =" + comp_idS;
					        	rs = stmt.executeQuery(query);
					        	
					        	if(!rs.next()) { 
					        		System.out.println("[¿À·ù¹ß»ý : È¸»ç ID] ÇØ´ç IDÀÇ È¸»ç°¡ ¾ø½À´Ï´Ù.");
					        	}
					        	else {
					        		int rentI;
					        		
					        		// ´ë¿© Å×ÀÌºí
					        		String query1 = "SELECT * FROM rent WHERE Car_Company_comp_id =" + comp_idI;
						        	rs = stmt.executeQuery(query1);
						        	
						        	while(rs.next()) { 
						        		// Á¡°Ë ³»¿ª¿¡¼­ »èÁ¦
						        		Statement stmt2 = con.createStatement();
						        		rentI = rs.getInt(1);
						        		query = "DELETE FROM inspection WHERE Rent_rent_id = " + rentI;
							        	stmt2.executeUpdate(query);
						        	}
					        		
						        	// ´ë¿© Å×ÀÌºí¿¡¼­ »èÁ¦
					        		query = "DELETE FROM rent WHERE Car_Company_comp_id = " + comp_idI;
					        		stmt.executeUpdate(query);
					        		
					        		// Á¤ºñ ³»¿ª¿¡¼­ »èÁ¦
					        		query = "DELETE FROM repair WHERE Car_Company_comp_id = " + comp_idI;
					        		stmt.executeUpdate(query);
					        		
					        		// Ä·ÇÎÄ« µî·Ï¿¡¼­ »èÁ¦
					        		query = "DELETE FROM car WHERE Company_comp_id = " + comp_idI;
					        		stmt.executeUpdate(query);	
					        		
					        		// È¸»ç¿¡¼­ »èÁ¦
					        		query = "DELETE FROM company WHERE comp_id = " + comp_idI;
					        		stmt.executeUpdate(query);		        		
					        		
					        		System.out.println("ÇØ´ç IDÀÇ È¸»ç »èÁ¦°¡ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
					        		
					        		query="SELECT * FROM company WHERE comp_id >=" + 0;
				                    rs = stmt.executeQuery(query);
				                    
				                    System.out.println("È¸»ç ID \t È¸»çÀÌ¸§ ----»ý·«----");
			
				                    while(rs.next()) {
				                       System.out.println(rs.getInt(1)+"\t ³ª¸ÓÁö´Â »ý·« -----");
				                       
				                    }
					        	}
		                	}
	                	}
	                } 
	                
	                //È¸»ç º¯°æ¹öÆ°
	                else if(e.getSource()==comp_convertB) {
	                	System.out.println("È¸»ç º¯°æ ¹öÆ° Å¬¸¯!");
	                	
	                	Boolean isError=false;
	                	Boolean phoneError=false;
	                	Boolean emailError=false;
	                	
	                	Boolean name=false;
	                	Boolean address=false;
	                	Boolean phone=false;
	                	Boolean person=false;
	                	Boolean email=false;
	                	
	                	StringTokenizer st;
	               	
	                	String comp_idS = comp_idf.getText();	
	                    String comp_nameS = comp_namef.getText();
	                    String comp_addressS = comp_addressf.getText();
	                    String comp_phoneS = comp_phonef.getText();
	                    String comp_personS = comp_personf.getText();
	                    String comp_emailS = comp_emailf.getText();
	                	
	                    //ºóÄ­ÀÌ ÀÖ´ÂÁö È®ÀÎ
	                	if(comp_idS.length()==0 || (comp_nameS.length()==0 && comp_addressS.length()==0 && comp_phoneS.length()==0 && comp_personS.length()==0 && comp_emailS.length()==0)) {
	                   		System.out.println("[¿À·ù¹ß»ý : µ¥ÀÌÅÍ ÀÔ·Â ¾øÀ½] È¸»çIDÄ­°ú ¹Ù²Ù°í½ÍÀº ¼Ó¼ºÄ­¿¡ µ¥ÀÌÅÍ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
	                   		isError=true;
	                    }
	                	else {
	                		int comp_idI = Integer.parseInt(comp_idS);
	                		
		                	if(!CheckNumber(comp_idS)) {
		                		System.out.println("[¿À·ù¹ß»ý : È¸»ç ID] È¸»ç ID´Â ¾çÀÇ Á¤¼ö¿©¾ß ÇÕ´Ï´Ù.");
		                	}
		                	else{
			                	
			                    if(comp_nameS.length()!=0) {
			                    	name=true;
			                    }
			                    if(comp_addressS.length()!=0) {
			                    	address=true;
			                    }
			                    if(comp_personS.length()!=0) {
			                    	person=true;
			                    }
			                    
			                    // phone ¿À·ù È®ÀÎ
			                    if(comp_phoneS.length()!=0) {
						            String p1, p2, p3;
						            st = new StringTokenizer(comp_phoneS,"-");
						            p1=st.nextToken();
						            
						            if(p1.length()!=3 || !CheckNumber(p1)) phoneError=true;
						            
						            if(!st.hasMoreTokens())  phoneError=true;
						            else{
						            	p2=st.nextToken();
						            	if(p2.length()!=4 || !CheckNumber(p2)) phoneError=true;
						            	
						            	if(!st.hasMoreTokens()) phoneError=true;
						            	else {
						            		p3=st.nextToken();
						            		if(p3.length()!=4 || !CheckNumber(p3)) phoneError=true;
						            	 }
						             }
						       
						             if(phoneError) {
						            	 System.out.println("[¿À·ù¹ß»ý : ÀüÈ­¹øÈ£] ÀüÈ­¹øÈ£´Â XXX-XXXX-XXXX ¿©¾ß ÇÕ´Ï´Ù.(XÀÇ ¼ö´Â 3,4,4·Î Á¦ÇÑ, X´Â ¼ýÀÚ¿©¾ßÇÔ)");
						        		 isError=true;
						             }
						             else {
						            	 phone=true;
						             }
			                    }
			                    
					            // email ¿À·ù È®ÀÎ
			                    if(comp_emailS.length()!=0) {
						             String e1,e2=null,e3=null;
						             st = new StringTokenizer(comp_emailS,"@");
						             e1=st.nextToken();
						             
						             if(!st.hasMoreTokens())  emailError=true;
						             else {
						            	 e2=st.nextToken();
						            	 
						            	 st = new StringTokenizer(e2,".");
						            	 if(!st.hasMoreTokens()) emailError=true;
						            	 else{
						            		 e2=st.nextToken();
						            		 
						            		 if(!st.hasMoreTokens()) emailError=true;
						            		 else {
								            	 e3=st.nextToken();
							            	 }
							            }
						            	
						             }
						             
						             if(emailError) {
						            	 System.out.println("[¿À·ù¹ß»ý : ÀÌ¸ÞÀÏ] ÀÌ¸ÞÀÏÀº XXX@XXX.XXX ¿©¾ß ÇÕ´Ï´Ù.(XÀÇ ¼ö´Â Á¦ÇÑ ¾øÀ½)");
						        		 isError=true;
						             }
						             else {
						            	 email=true;
						             }
				                   
			                    }
			                    
					            // ¿À·ù°¡ ¾øÀ¸¸é º¯°æ
					            if(!isError) {
					            	if(name) {
						            	query = "UPDATE company SET comp_name='"+comp_nameS+"' WHERE comp_id=" + comp_idI;
						        		stmt.executeUpdate(query);
					            	}
					            	if(address) {
						        		query = "UPDATE company SET comp_address='"+comp_addressS+"' WHERE comp_id=" + comp_idI;
						        		stmt.executeUpdate(query);
					            	}
					        		if(phone) {
						        		query = "UPDATE company SET comp_phone='"+comp_phoneS+"' WHERE comp_id=" + comp_idI;
						        		stmt.executeUpdate(query);
					        		}
					        		if(person) {
						        		query = "UPDATE company SET comp_person='"+comp_personS+"' WHERE comp_id=" + comp_idI;
						        		stmt.executeUpdate(query);
					        		}
					        		if(email) {
						        		query = "UPDATE company SET comp_email='"+comp_emailS+"' WHERE comp_id=" + comp_idI;
						        		stmt.executeUpdate(query);
					        		}
					        		
					        		System.out.println("ÇØ´ç IDÀÇ È¸»ç Á¤º¸ º¯°æÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
					        		query="SELECT * FROM company WHERE comp_id >=" + 0;
				                    rs = stmt.executeQuery(query);
			
				                    
				                    System.out.println("È¸»ç ID \t È¸»çÀÌ¸§ ----»ý·«----");
			
				                    while(rs.next()) {
				                       System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t ³ª¸ÓÁö´Â »ý·« -----");
				                       
				                    }
			                    }
			                    
		                	}
	                	}
	                }
	                
	                //Ä·ÇÎÄ« ÀÔ·Â¹öÆ°
	                else if(e.getSource()==car_insertB) {
	                	System.out.println("Ä·ÇÎÄ« ÀÔ·Â¹öÆ° Å¬¸¯!");
	                	
	                	Boolean isError = false;
	                	Boolean numError = false;
	                	Boolean dateError =false;
	                	
	                	// ´ë¿©È¸»ç ID
	                	String car_comp_idS = car_comp_idf.getText();
	                	int car_comp_idI=0;
	                	
	                	// Ä·ÇÎÄ« ÀÌ¸§
	                	String car_nameS = car_namef.getText();
	                	// Ä·ÇÎÄ« µî·Ï ID
	                	String car_numS = car_numf.getText();
	                	
	                	//Ä·ÇÎÄ« ½ÂÂ÷ ÀÎ¿ø¼ö
	                	String car_boardS = car_boardf.getText();
	                	int car_boardI=0;
	                	
	                	// Á¦Á¶È¸»ç
	                	String car_manucompS = car_manucompf.getText();
	                	// Á¦Á¶¿¬µµ
	                	String car_manudateS = car_manudatef.getText();
	                	
	                	//´©ÀûÁÖÇà°Å¸®
	                	String car_distanceS = car_distancef.getText();
	                	int car_distanceI=0; 
	                	
	                	// ´ë¿©ºñ¿ë
	                	String car_priceS = car_pricef.getText();
	                	int car_priceI=0;
	                	
	                	// Ä·ÇÎÄ« µî·ÏÀÏÀÚ
	                	String car_dateS = car_datef.getText();
	                	
	                	// ºó °÷ÀÌ ¾ø´ÂÁö È®ÀÎ
	                	if(car_comp_idS.length()==0 || car_nameS.length()==0 || car_numS.length()==0 || car_boardS.length()==0 || car_manucompS.length()==0 
	                			|| car_manudateS.length()==0 || car_distanceS.length()==0 ||car_priceS.length()==0 || car_dateS.length()==0) {
	                		System.out.println("[¿À·ù¹ß»ý : µ¥ÀÌÅÍ ÀÔ·Â ¾øÀ½] ¸ðµç Ä­¿¡ µ¥ÀÌÅÍ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
	                		isError=true;
	                	}
	                	else {

		                	// È¸»ç ID ¿À·ù È®ÀÎ
		                	if(!CheckNumber(car_comp_idS)) { 
		                		isError=true;
		                		System.out.println("[¿À·ù¹ß»ý : È¸»ç ID] È¸»ç ID´Â ¾çÀÇ Á¤¼öÀÔ´Ï´Ù.");
		                	}
		                	else{
		                		car_comp_idI=Integer.parseInt(car_comp_idS);
		                		query = "SELECT * FROM company WHERE comp_id =" + car_comp_idI;
					        	 rs = stmt.executeQuery(query);
					        	 if(!rs.next()) { 
					        		 System.out.println("[¿À·ù¹ß»ý : È¸»ç ID] ÇØ´ç IDÀÇ È¸»ç°¡ ¾ø½À´Ï´Ù.");
					        		 isError=true;
					        	 }
				        	 }
				        	 
		                	// Â÷·®¹øÈ£ ¿À·ù È®ÀÎ
		                	StringTokenizer st;
		                	st = new StringTokenizer(car_numS," ");
			                String n1,n2;
			                	
			                n1=st.nextToken();
			                if( n1.length()!=3 || !(n1.charAt(0)>='0' && n1.charAt(0)<='9') || !(n1.charAt(1)>='0' && n1.charAt(1)<='9')
		                		|| !(n1.charAt(2)>='°¡' && n1.charAt(2)<='ÆR')) { 
			                	numError=true;
			       		
			                }
			                
			                else {
			                	if(!st.hasMoreTokens()) { numError=true; }
				                else{
				                	n2=st.nextToken();
				                	if(n2.length()!=4 || !CheckNumber(n2)) { numError=true; }
				                	else {
				                		query = "SELECT * FROM car WHERE car_num ='" + car_numS+"'";
				                			
								        rs = stmt.executeQuery(query);
								        if(rs.next()) {
								        	System.out.println("[¿À·ù¹ß»ý : Â÷·®¹øÈ£] ÇØ´ç Â÷·®¹øÈ£°¡ ÀÌ¹Ì Á¸ÀçÇÕ´Ï´Ù.");
								        	isError=true;
								        }
				                	}
				                }
				            }
		                	
		                	if(numError) {
		                		System.out.println("[¿À·ù¹ß»ý : Â÷·®¹øÈ£] Â÷·®¹øÈ£ Çü½ÄÀÌ Àß¸øµÇ¾ú½À´Ï´Ù. ¿¹½Ã)OOX OOOO  ÀÌ¶§ O´Â ¼ýÀÚ, X´Â ÇÑ±Û");
		                		isError=true;
		                	}
		                	
		          
		                	// Å¾½ÂÀÎ¿ø ¿À·ù È®ÀÎ
		                	if(!CheckNumber(car_boardS)) { 
		                		isError=true;
		                		System.out.println("[¿À·ù¹ß»ý : ½ÂÂ÷ÀÎ¿ø] ½ÂÂ÷ÀÎ¿øÀº  ¼ýÀÚ·Î ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
		                	}
		                	else {
		                		car_boardI=Integer.parseInt(car_boardS);
			                	if(car_boardI < 1) {
			                		System.out.println("[¿À·ù¹ß»ý : ½ÂÂ÷ÀÎ¿ø] ½ÂÂ÷ÀÎ¿øÀº 1¸íÀÌ»ó ÀÌ¾î¾ß ÇÕ´Ï´Ù.");
			                		isError=true;
			                	}
		                	}
		                	
		                	// Á¦Á¶¿¬µµ ¿À·ù È®ÀÎ
		                	if(!CheckNumber(car_manudateS)) {
		                		System.out.println("[¿À·ù¹ß»ý : Á¦Á¶¿¬µµ] Á¦Á¶¿¬µµ´Â 4ÀÚ¸® ¼ýÀÚ YYYY ÀÔ´Ï´Ù.");
				        		 isError=true;
				        		 dateError=true;
		                	}
		                	else {
		                		if(car_manudateS.length()!=4) {
		                			System.out.println("[¿À·ù¹ß»ý : Á¦Á¶¿¬µµ] Á¦Á¶¿¬µµ´Â 4ÀÚ¸® ¼ýÀÚ YYYY ÀÔ´Ï´Ù.");
					        		 isError=true;
					        		 dateError=true;
		                		}
		                	}
		                	
		                	// ÁÖÇà°Å¸® ¿À·ù È®ÀÎ
				            if(!CheckNumber(car_distanceS)) { 
			                		isError=true;
			                		System.out.println("[¿À·ù¹ß»ý : ÁÖÇà°Å¸®] ÁÖÇà°Å¸®´Â ¾çÀÇ Á¤¼öÀÔ´Ï´Ù.");
			                }
				            else {
				            	 car_distanceI = Integer.parseInt(car_distanceS);
				            	 if(car_distanceI < 0) {
				            		 System.out.println("[¿À·ù¹ß»ý : ÁÖÇà°Å¸®] ÁÖÇà°Å¸®´Â 0ÀÌ»ó ÀÌ¾î¾ß ÇÕ´Ï´Ù.");
				                	isError=true;
				            	}
				            }
				             
		                	// ´ë¿©ºñ¿ë ¿À·ù È®ÀÎ
		                	if(!CheckNumber(car_priceS)) { 
		                		isError=true;
		                		System.out.println("[¿À·ù¹ß»ý : ´ë¿©ºñ¿ë] ´ë¿©ºñ¿ëÀº ¾çÀÇ Á¤¼öÀÔ´Ï´Ù.");
		                	}
		                	else {
		                		car_priceI = Integer.parseInt(car_priceS);
			                	if(car_priceI < 1) {
			                		System.out.println("[¿À·ù¹ß»ý : ´ë¿©ºñ¿ë] ´ë¿©ºñ¿ëÀº 1¿øÀÌ»ó ÀÌ¾î¾ß ÇÕ´Ï´Ù.");
			                		isError=true;
			                	}
		                	}
		                	
		                	// µî·Ï³¯Â¥ ¿À·ù È®ÀÎ
		                	String Y, M, D;
				            int y,m,d;
				            st = new StringTokenizer(car_dateS,"-");
				            Y=st.nextToken();
				            
				            if(st.hasMoreTokens()) {
				            	M=st.nextToken();
				            	if(st.hasMoreTokens()) {
				            		D=st.nextToken();
						            y=Integer.parseInt(Y);
						            m=Integer.parseInt(M);
						            d=Integer.parseInt(D);
						            
						            if(m>12 || m <1 || d >31 || d<1) {
						            	System.out.println("[¿À·ù¹ß»ý : µî·Ï³¯Â¥] YYYY-MM-DD¿¡¼­ MMÀº 1~12»çÀÌ, DD´Â 1~31»çÀÌÀÇ ¼ö¿©¾ß ÇÕ´Ï´Ù.");
						        		isError=true;
						        		dateError=true;
						            }
					             }
				            	
					             else {
					            	 isError=true;
					            	 dateError=true;
					            	 System.out.println("[¿À·ù¹ß»ý : µî·Ï³¯Â¥] YYYY-MM-DD¿¡¼­ MMÀº 1~12»çÀÌ, DD´Â 1~31»çÀÌÀÇ ¼ö¿©¾ß ÇÕ´Ï´Ù.");
					             }
				             }
				            
				             else {
				            	 System.out.println("[¿À·ù¹ß»ý : µî·Ï³¯Â¥] YYYY-MM-DD¿¡¼­ MMÀº 1~12»çÀÌ, DD´Â 1~31»çÀÌÀÇ ¼ö¿©¾ß ÇÕ´Ï´Ù.");
				            	 isError=true;
				            	 dateError=true;
				             }
	                	
				             
				             // Á¦Á¶¿¬µµ<µî·Ï³¯Â¥ ¿À·ù È®ÀÎ
				             if(!dateError) {
				            	 String Y1;
					             int y1;
					             
					             st = new StringTokenizer(car_dateS,"-");
					             Y1=st.nextToken();
					           
							     y1=Integer.parseInt(Y1);
							   
							     if(Integer.parseInt(car_manudateS)>y1) {
							    	 isError=true;
							    	 dateError=true;
							     }
							     
							     if(dateError) {
							    	 System.out.println("[¿À·ù¹ß»ý : Á¦Á¶¿¬µµ¿Í µî·Ï³¯Â¥] Á¦Á¶¿¬µµ´Â µî·Ï³¯Â¥º¸´Ù °ú°Å¿©¾ßÇÕ´Ï´Ù.");
							     }
				             }
				             
				             // ¿À·ù°¡ ¾øÀ¸¸é »ðÀÔ 
				             if(!isError) {
				            	 query="SELECT * FROM car";
			                     rs=stmt.executeQuery(query);
			                     rs.last();
			                     
			                     int car_idI=rs.getInt(1)+1;
			                    
			                     PreparedStatement statement = null;
		                         statement = con.prepareStatement("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id)"+"value(?,?,?,?,?,?,?,?,?,?)");
		                    
		                         statement.setInt(1,car_idI);
		                         statement.setString(2, car_nameS);
		                         statement.setString(3, car_numS);
		                         statement.setInt(4, car_boardI);
		                         statement.setString(5,car_manucompS);
		                         statement.setString(6,car_manudateS);
		                         statement.setInt(7,car_distanceI);
		                         statement.setInt(8, car_priceI);
		                         statement.setDate(9, java.sql.Date.valueOf(car_dateS));
		                         statement.setInt(10,car_comp_idI);
		                             
		                         statement.executeUpdate();

			                     carBoolean[car_idI]=true;
			                       
			                     rs=stmt.executeQuery(query);
			                     rs.last();
			                     
			                     System.out.println("Ä·ÇÎÄ« ÀÔ·Â ¿Ï·áµÇ¾ú½À´Ï´Ù.");
			                     System.out.println("È¸»çID \t ´ë¿©°¡´É ¿©ºÎ \tÄ·ÇÎÄ«ID \tÂ÷ÀÌ¸§ \tÂ÷·®¹øÈ£ \t½ÂÂ÷ÀÎ¿ø¼ö \tÁ¦Á¶È¸»ç \tÁ¦Á¶³¯Â¥ \tÁÖÇà°Å¸® \t´ë¿©ºñ¿ë \tµî·ÏÀÏÀÚ ");
			                     String str = rs.getInt(10) +"\t"+ carBoolean[car_idI] + "\t\t" + rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + 
			                                rs.getInt(4) + "\t" + rs.getString(5)+ "\t" + rs.getString(6) + "\t" + rs.getInt(7) + "\t" +
			                                rs.getInt(8) + "\t" + rs.getDate(9);
			                      
			                     System.out.println(str);
			                      
			                  }
	                	}
			             
	                }
	                
	                //Ä·ÇÎÄ« »èÁ¦¹öÆ°
	                else if(e.getSource()==car_deleteB) {
	                	System.out.println("Ä·ÇÎÄ« »èÁ¦¹öÆ° Å¬¸¯!");
	                	
	                    String car_idS = car_idf.getText();
	                    
	                    // ºóÄ­
	                    if(car_idS.length()==0) {
	                   		System.out.println("[¿À·ù¹ß»ý : µ¥ÀÌÅÍ ÀÔ·Â ¾øÀ½] Ä·ÇÎÄ« ID¿¡ µ¥ÀÌÅÍ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
	                   		
	                    }
	                    else {
	                    	if(!CheckNumber(car_idS)) {
		                		System.out.println("[¿À·ù¹ß»ý : Ä·ÇÎÄ« ID] Ä·ÇÎÄ« ID´Â ¾çÀÇ Á¤¼ö¿©¾ß ÇÕ´Ï´Ù.");
		                	}
	                    	else {
			                   int car_idI = Integer.parseInt(car_idS);
			                   query = "SELECT * FROM car WHERE car_id =" + car_idI;
			                   rs = stmt.executeQuery(query);
			                
			                   if(!rs.next()) {
			                	   System.out.println("[¿À·ù¹ß»ý : Ä·ÇÎÄ«µî·Ï ID] ÇØ´ç IDÀÇ Ä·ÇÎÄ«°¡ ¾ø½À´Ï´Ù.");
			                   }
			                   else {
			                	   int rentI;
			                	   String query1 = "SELECT * FROM rent WHERE Car_car_id =" + car_idI;
			                	   rs = stmt.executeQuery(query1);
			                	
			                	   while(rs.next()) {
			                		   Statement stmt2 = con.createStatement();
			                		   rentI=rs.getInt(1);
			                		   query = "DELETE FROM inspection WHERE Rent_rent_id = " + rentI;
			                		   stmt2.executeUpdate(query);
			                		  
			                	   }
			                	  
			                	   
			                	   query = "DELETE FROM rent WHERE Car_car_id = " + car_idI;
			                	   stmt.executeUpdate(query);
			                	
			                
			                	   query = "DELETE FROM repair WHERE Car_car_id = " + car_idI;
			                	   stmt.executeUpdate(query);
			                	   
			                	
			                	   stmt.executeUpdate("SET foreign_key_checks = 0");
			                	
			                	   query = "DELETE FROM car WHERE car_id = " + car_idI;
			                	   stmt.executeUpdate(query);   
			                	
			                	   System.out.println("ÇØ´ç IDÀÇ Ä·ÇÎÄ« »èÁ¦°¡ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
			                	   
			                	   stmt.executeUpdate("SET foreign_key_checks = 1");
			                    
			                    
			                	   query="SELECT * FROM car WHERE car_id >=" + 0;
			                       rs = stmt.executeQuery(query);
			
			                       
			                       System.out.println("Ä·ÇÎÄ« ID \t Ä·ÇÎÄ«ÀÌ¸§ ----»ý·«----");
			
			                       while(rs.next()) {
			                          System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+" ³ª¸ÓÁö´Â »ý·« -----");
			                          
			                       }
			                 }
	                    }
	                 }
	                }
	                
	                // Ä·ÇÎÄ« º¯°æ¹öÆ°
	                else if(e.getSource()== car_convertB) {
	                	System.out.println("Ä·ÇÎÄ« º¯°æ¹öÆ° Å¬¸¯!");
	                	
	                	Boolean isError = false;
	                	Boolean numError = false;
	                	Boolean dateError =false;
	                	Boolean carError = false;
	                	
	                	Boolean name=false;
	                	Boolean num=false;
	                	Boolean board=false;
	                	Boolean manucomp=false;
	                	Boolean manudate=false;
	                	Boolean distance=false;
	                	Boolean price=false;
	                	Boolean date=false;
	                	
	                	String Y, M, D;
			            int y,m,d;
			            
	                	StringTokenizer st;
	                	
	                	String car_idS=car_idf.getText();
	                	int car_idI=0;
	                	
	                	String car_comp_idS = car_comp_idf.getText();
	                	int car_comp_idI=0;
	                	
	                	String car_nameS = car_namef.getText();
	                	String car_numS = car_numf.getText();
	                	
	                	String car_boardS = car_boardf.getText();
	                	int car_boardI=0;
	                	
	                	String car_manucompS = car_manucompf.getText();
	                	String car_manudateS = car_manudatef.getText();
	                	
	                	String car_distanceS = car_distancef.getText();
	                	int car_distanceI=0;
	                	
	                	String car_priceS = car_pricef.getText();
	                	int car_priceI=0;
	                	
	                	String car_dateS = car_datef.getText();
	                	
	                	// ºó °÷ÀÌ ¾ø´ÂÁö È®ÀÎ
	                	if(car_idS.length()==0 || car_comp_idS.length()==0 || (car_nameS.length()==0 && car_numS.length()==0 && car_boardS.length()==0 && car_manucompS.length()==0 
	                			&& car_manudateS.length()==0 && car_distanceS.length()==0 && car_priceS.length()==0 && car_dateS.length()==0)) {
	                		System.out.println("[¿À·ù¹ß»ý : µ¥ÀÌÅÍ ÀÔ·Â ¾øÀ½] Ä·ÇÎÄ«IDÄ­°ú È¸»çIDÄ­°ú ¹Ù²Ù°í½ÍÀº ¼Ó¼ºÄ­¿¡ µ¥ÀÌÅÍ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
	                   		isError=true;
	                	}
	                	else {
	                		// Ä·ÇÎÄ« ID ¿À·ùÈ®ÀÎ
	                		if(!CheckNumber(car_idS)) { 
		                		isError=true;
		                		carError = true;
		                		System.out.println("[¿À·ù¹ß»ý : Ä·ÇÎÄ« ID] Ä·ÇÎÄ« ID´Â ¾çÀÇ Á¤¼öÀÔ´Ï´Ù.");
		                	}
		                	else{
		                		car_idI=Integer.parseInt(car_idS);
		                		query = "SELECT * FROM car WHERE car_id =" + car_idI;
					        	 rs = stmt.executeQuery(query);
					        	 if(!rs.next()) { 
					        		 System.out.println("[¿À·ù¹ß»ý : Ä·ÇÎÄ« ID] ÇØ´ç IDÀÇ Ä·ÇÎÄ«°¡ ¾ø½À´Ï´Ù.");
					        		 isError=true;
					        		 carError = true;
					        	 }
					            
				        	 }

		                	// È¸»ç ID ¿À·ù È®ÀÎ
		                	if(!CheckNumber(car_comp_idS)) { 
		                		isError=true;
		                		carError = true;
		                		System.out.println("[¿À·ù¹ß»ý : È¸»ç ID] È¸»ç ID´Â ¾çÀÇ Á¤¼öÀÔ´Ï´Ù.");
		                	}
		                	else{
		                		car_comp_idI=Integer.parseInt(car_comp_idS);
		                		query = "SELECT * FROM company WHERE comp_id =" + car_comp_idI;
					        	 rs = stmt.executeQuery(query);
					        	 if(!rs.next()) { 
					        		 System.out.println("[¿À·ù¹ß»ý : È¸»ç ID] ÇØ´ç IDÀÇ È¸»ç°¡ ¾ø½À´Ï´Ù.");
					        		 isError=true;
					        		 carError = true;
					        	 }
				        	 }
		                	
		                	 if(!carError) {
		                    	 car_idI = Integer.parseInt(car_idS);
		                    	 if(!carError) {
				                     query = "SELECT * FROM car WHERE car_id =" + car_idI;
				                     rs = stmt.executeQuery(query);
				                     if(rs.next()) {
				                    	 int i = rs.getInt(10); 
				                    	 if(Integer.parseInt(car_comp_idS)!=i) {
				                    		 isError=true;
				                   		 System.out.println("[¿À·ù¹ß»ý : È¸»ç ID] Ä·ÇÎÄ« ID¿Í È¸»ç ID°¡ ¼­·Î ¸ÂÁö¾Ê½À´Ï´Ù.");
				                    	 }
				                     } 	 
				                 } 
		                     }
		                	
		                	// Â÷ÀÌ¸§ ¿À·ùÈ®ÀÎ
		                	if(car_nameS.length()!=0) {
		                		name=true;
		                	}
				        	 
		                	// Â÷·®¹øÈ£ ¿À·ù È®ÀÎ
		                	if(car_numS.length()!=0) {
		                		st = new StringTokenizer(car_numS," ");
				                String n1,n2;
				                	
				                n1=st.nextToken();
				                if( n1.length()!=3 || !(n1.charAt(0)>='0' && n1.charAt(0)<='9') || !(n1.charAt(1)>='0' && n1.charAt(1)<='9')
			                		|| !(n1.charAt(2)>='°¡' && n1.charAt(2)<='ÆR')) { 
				                	numError=true;	
				                }
				                else {
				                	if(!st.hasMoreTokens()) { numError=true; }
				                	else{
				                		n2=st.nextToken();
				                		if(n2.length()!=4 || !CheckNumber(n2)) { numError=true; }
				                		else {
				                			query = "SELECT * FROM car WHERE car_num ='" + car_numS+"'";
					                		
				                			rs = stmt.executeQuery(query);
				                			if(rs.next()) {
				                				System.out.println("[¿À·ù¹ß»ý : Â÷·®¹øÈ£] ÇØ´ç Â÷·®¹øÈ£°¡ ÀÌ¹Ì Á¸ÀçÇÕ´Ï´Ù.");
				                				isError=true;
									        }
					                	}
					                }
					            }
			                	
			                	if(numError) {
			                		System.out.println("[¿À·ù¹ß»ý : Â÷·®¹øÈ£] Â÷·®¹øÈ£ Çü½ÄÀÌ Àß¸øµÇ¾ú½À´Ï´Ù. ¿¹½Ã)OOX OOOO  ÀÌ¶§ O´Â ¼ýÀÚ, X´Â ÇÑ±Û");
			                		isError=true;
			                	}
			                	else {
			                		num=true;
			                	}
		                	
		                	}
		   		                	
		                	// Å¾½ÂÀÎ¿ø ¿À·ù È®ÀÎ
		                	if(car_boardS.length()!=0) {
			                	if(!CheckNumber(car_boardS)) { 
			                		isError=true;
			                		System.out.println("[¿À·ù¹ß»ý : ½ÂÂ÷ÀÎ¿ø] ½ÂÂ÷ÀÎ¿øÀº ¾çÀÇ Á¤¼öÀÔ´Ï´Ù.");
			                	}
			                	else {
			                		car_boardI=Integer.parseInt(car_boardS);
				                	if(car_boardI < 1) {
				                		System.out.println("[¿À·ù¹ß»ý : ½ÂÂ÷ÀÎ¿ø] ½ÂÂ÷ÀÎ¿øÀº 1¸íÀÌ»ó ÀÌ¾î¾ß ÇÕ´Ï´Ù.");
				                		isError=true;
				                	}
				                	else {
				                		board=true;
				                	}
			                	}
		                	}
		                	
		                	// Á¦Á¶È¸»ç ¿À·ù È®ÀÎ
		                	if(car_manucompS.length()!=0) {
		                		manucomp=true;
		                	}
		                	
		                	// Á¦Á¶¿¬µµ ¿À·ù È®ÀÎ
		                	if(car_manudateS.length()!=0) {
		                		if(!CheckNumber(car_manudateS)) {
			                		System.out.println("[¿À·ù¹ß»ý : Á¦Á¶¿¬µµ] Á¦Á¶¿¬µµ´Â 4ÀÚ¸® ¼ýÀÚ YYYY ÀÔ´Ï´Ù.");
					        		 isError=true;
					        		 dateError=true;
			                	}
			                	else {
			                		if(car_manudateS.length()!=4) {
			                			System.out.println("[¿À·ù¹ß»ý : Á¦Á¶¿¬µµ] Á¦Á¶¿¬µµ´Â 4ÀÚ¸® ¼ýÀÚ YYYY ÀÔ´Ï´Ù.");
						        		 isError=true;
						        		 dateError=true;
			                		}
			                		else {
			                			manudate=true;
			                		}
			                	}
		                	}
		                	
		                	// ´©ÀûÁÖÇà°Å¸® ¿À·ù È®ÀÎ
		                	if(car_distanceS.length()!=0) {
					             if(!CheckNumber(car_distanceS)) { 
				                		isError=true;
				                		System.out.println("[¿À·ù¹ß»ý : ´©ÀûÁÖÇà°Å¸®] ´©ÀûÁÖÇà°Å¸®´Â ¾çÀÇ Á¤¼öÀÔ´Ï´Ù.");
				                	}
				                	else {
				                		car_distanceI = Integer.parseInt(car_distanceS);
					                	if(car_distanceI < 0) {
					                		System.out.println("[¿À·ù¹ß»ý : ´©ÀûÁÖÇà°Å¸®] ´©ÀûÁÖÇà°Å¸®´Â 0ÀÌ»ó ÀÌ¾î¾ß ÇÕ´Ï´Ù.");
					                		isError=true;
					                	}
					                	else {
					                		distance=true;
					                	}
				                	}
		                	}
		                	
		                	// ´ë¿©ºñ¿ë ¿À·ù È®ÀÎ
		                	if(car_priceS.length()!=0) {
			                	if(!CheckNumber(car_priceS)) { 
			                		isError=true;
			                		System.out.println("[¿À·ù¹ß»ý : ´ë¿©ºñ¿ë] ´ë¿©ºñ¿ëÀº ¾çÀÇ Á¤¼öÀÔ´Ï´Ù.");
			                	}
			                	else {
			                		car_priceI = Integer.parseInt(car_priceS);
				                	if(car_priceI < 1) {
				                		System.out.println("[¿À·ù¹ß»ý : ´ë¿©ºñ¿ë] ´ë¿©ºñ¿ëÀº 1¿øÀÌ»ó ÀÌ¾î¾ß ÇÕ´Ï´Ù.");
				                		isError=true;
				                	}
				                	else {
				                		price=true;
				                		
				                	}
			                	}
		                	}
		                	
		                	// µî·Ï³¯Â¥ ¿À·ù È®ÀÎ
		                	if(car_dateS.length()!=0) {
					             st = new StringTokenizer(car_dateS,"-");
					             Y=st.nextToken();
					             if(st.hasMoreTokens()) {
						             M=st.nextToken();
						             
						             if(st.hasMoreTokens()) {
							             D=st.nextToken();
							             y=Integer.parseInt(Y);
							             m=Integer.parseInt(M);
							             d=Integer.parseInt(D);
							             
							             if(m>12 || m <1 || d >31 || d<1) {
							            	 System.out.println("[¿À·ù¹ß»ý : µî·Ï³¯Â¥] YYYY-MM-DD¿¡¼­ MMÀº 1~12»çÀÌ, DD´Â 1~31»çÀÌÀÇ ¼ö¿©¾ß ÇÕ´Ï´Ù.");
							        		 isError=true;
							        		 dateError=true;
							             }
						             }
						             else { 
						            	 isError=true;
						            	 dateError=true;
						            	 System.out.println("[¿À·ù¹ß»ý : µî·Ï³¯Â¥] YYYY-MM-DD¿¡¼­ MMÀº 1~12»çÀÌ, DD´Â 1~31»çÀÌÀÇ ¼ö¿©¾ß ÇÕ´Ï´Ù.");
						             }
					             }
					             
					             else {
					            	 System.out.println("[¿À·ù¹ß»ý : µî·Ï³¯Â¥] YYYY-MM-DD¿¡¼­ MMÀº 1~12»çÀÌ, DD´Â 1~31»çÀÌÀÇ ¼ö¿©¾ß ÇÕ´Ï´Ù.");
					            	 isError=true;
					            	 dateError=true;
					             }
					             
					             if(!dateError) {
					            	 date=true;
					            }
		                	}
		                	
		                	// Á¦Á¶¿¬µµ<µî·Ï³¯Â¥ ¿À·ù È®ÀÎ
	                         if(!date && manudate) {
	                           query = "SELECT * FROM car WHERE car_id ='" + car_idS+"'";
	                           rs=stmt.executeQuery(query);
	                           if(!rs.next()) {}
	                           else {
	                        	   String da=rs.getString(9);
	                               st = new StringTokenizer(da,"-");
	                               String Y2=st.nextToken();
	                               int y2=Integer.parseInt(Y2);
	                           
	                              
	                               int y1=Integer.parseInt(car_manudateS);
	                          
	                             
	                               if(y2<y1) {
	                            	   isError=true;
	                            	   dateError=true;
	                               }
	                            
	                           }
	                           
	                           if(dateError) {
	                        	   System.out.println("[¿À·ù¹ß»ý : Á¦Á¶¿¬µµ¿Í µî·Ï³¯Â¥] Á¦Á¶¿¬µµ´Â µî·Ï³¯Â¥º¸´Ù °ú°ÅÀÌ¾î¾ßÇÕ´Ï´Ù.");
	                            }
	                         }
	                          
	                	
	                         if(date && !manudate) {
	                        	 query = "SELECT * FROM car WHERE car_id ='" + car_idS+"'";
	                        	 rs=stmt.executeQuery(query);
	                           
	                        	 if(!rs.next()) {}
	                        	 else {
	                              String da=rs.getString(6);
	                              
	                              st = new StringTokenizer(car_dateS,"-");
	                              String Y2 =st.nextToken();
	                              int y2=Integer.parseInt(Y2);
	                            
	                              int y1 =Integer.parseInt(da);
	                              if(y2<y1) {
	                                isError=true;
	                                dateError=true;
	                             }
	                          
	                             }
	                             if(dateError) {
	                                System.out.println("[¿À·ù¹ß»ý : Á¦Á¶¿¬µµ¿Í µî·Ï³¯Â¥] µî·Ï³¯Â¥´Â Á¦Á¶¿¬µµº¸´Ù ÃÖ±ÙÀÌ¾î¾ßÇÕ´Ï´Ù.");
	                             }
	                         }
	                      
	                         if(date && manudate) {
	                        	st = new StringTokenizer(car_dateS,"-");
		                        String Y2=st.nextToken();
		                        int y2 = Integer.parseInt(Y2);
		                        int y1 = Integer.parseInt(car_manudateS);
		                         
		                        if(y2<y1) {
		                        	isError=true;
		                            dateError=true;
		                        }
		                        
		                        if(dateError) {
		                           System.out.println("[¿À·ù¹ß»ý : Á¦Á¶¿¬µµ¿Í µî·Ï³¯Â¥] µî·Ï³¯Â¥´Â Á¦Á¶¿¬µµº¸´Ù ÃÖ±ÙÀÌ¾î¾ßÇÕ´Ï´Ù.");
		                        }
	                         }
	                          
	                	}
				             
			            if(!isError) {
			            	if(name) {
			            		query = "UPDATE car SET car_name='"+car_nameS+"' WHERE car_id=" + car_idI;
						        stmt.executeUpdate(query);
					        }
					        if(num) {
					        	query = "UPDATE car SET car_num='"+car_numS+"' WHERE car_id=" + car_idI;
						        stmt.executeUpdate(query);
					        }
					        if(board) {
					        	query = "UPDATE car SET car_board='"+car_boardS+"' WHERE car_id=" + car_idI;
						        stmt.executeUpdate(query);
					        }
					        if(manudate) {
					        	query = "UPDATE car SET car_manudate='"+car_manudateS+"' WHERE car_id=" + car_idI;
						        stmt.executeUpdate(query);
					        }
					        if(manucomp) {
					        	query = "UPDATE car SET car_manucomp='"+car_manucompS+"' WHERE car_id=" + car_idI;
						        stmt.executeUpdate(query);
					        }
					        if(distance) {
					        	query = "UPDATE car SET car_distance='"+car_distanceS+"' WHERE car_id=" + car_idI;
						        stmt.executeUpdate(query);
					        }
					        if(price) {
					        	query = "UPDATE car SET car_price='"+car_priceS+"' WHERE car_id=" + car_idI;
						        stmt.executeUpdate(query);
					        }
					        if(date) {
					        	query = "UPDATE car SET car_date='"+car_dateS+"' WHERE car_id=" + car_idI;
						        stmt.executeUpdate(query);
					        }
					        		
					        System.out.println("ÇØ´ç IDÀÇ Ä·ÇÎÄ« Á¤º¸ º¯°æÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
					        query="SELECT * FROM car WHERE car_id >=" + 0;
				            rs = stmt.executeQuery(query);
			
				                    
				            System.out.println("Ä·ÇÎÄ« ID \t Ä·ÇÎÄ«ÀÌ¸§ ----»ý·«----");
			
				            while(rs.next()) {
				            	System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t ³ª¸ÓÁö´Â »ý·« -----");
				            }
 
			             }
	          		}
	                
	                //°í°´ ÀÔ·Â¹öÆ°
	                else if(e.getSource() == insert_cust) {
	                    Boolean isError = false;
	                    Boolean phoneError=false;
	                    Boolean emailError=false;
	                    
	                   StringTokenizer st;
	                    
	                   System.out.println("°í°´ ÀÔ·Â¹öÆ° Å¬¸¯!");
	                   
	                   String cust_id = cust_idf.getText();;
	                   String cust_name = cust_namef.getText();
	                   String cust_address = cust_addressf.getText();
	                   String cust_phone = cust_phonef.getText();
	                   String cust_email = cust_emailf.getText();
	                   
	                   if(cust_id.length()==0 || cust_name.length()==0 || cust_address.length()==0 || cust_phone.length()==0 || cust_email.length()==0) {
	                		System.out.println("[¿À·ù¹ß»ý : µ¥ÀÌÅÍ ÀÔ·Â ¾øÀ½] ¸ðµç Ä­¿¡ µ¥ÀÌÅÍ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
	                   		isError=true;
	                	}
	                   else {
	                   
		                   // ¿îÀü¸éÇãÁõ¹øÈ£
		                   if(cust_id.length()!=15 || cust_id.charAt(2)!='-' || cust_id.charAt(5)!='-' || cust_id.charAt(12)!='-' ||
		                         !Character.isDigit(cust_id.charAt(0)) || !Character.isDigit(cust_id.charAt(1)) || !Character.isDigit(cust_id.charAt(3)) ||
		                         !Character.isDigit(cust_id.charAt(4)) || !Character.isDigit(cust_id.charAt(6)) || !Character.isDigit(cust_id.charAt(7)) ||
		                         !Character.isDigit(cust_id.charAt(8)) || !Character.isDigit(cust_id.charAt(9)) || !Character.isDigit(cust_id.charAt(10)) ||
		                         !Character.isDigit(cust_id.charAt(11)) || !Character.isDigit(cust_id.charAt(13)) || !Character.isDigit(cust_id.charAt(14))) {
		                      System.out.println("[¿À·ù ¹ß»ý : ¿îÀü¸éÇãÁõ ¹øÈ£] ¿îÀü¸éÇãÁõ¹øÈ£´Â nn-nn-nnnnnn-nn Çü½ÄÀ¸·Î ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù. nÀº ¼ýÀÚ");
		                      isError = true;
		                   }
		                   else {
		                      query = "select * from customer where cust_id = '"+cust_id+"'";
		                      rs = stmt.executeQuery(query);
		                      
		                      while(rs.next()) {
		                       if(rs.getString(1).length()!=0) {
		                          System.out.println("[¿À·ù¹ß»ý : ¿îÀü¸éÇãÁõ¹øÈ£] ÇØ´ç ¿îÀü¸éÇãÁõ¹øÈ£´Â µî·ÏµÇ¾î ÀÖ½À´Ï´Ù.");
		                             isError = true;
		                       }
		                      }
		                   
		                   }
		                   
		                  //°í°´¸í
		                    if(cust_name.length()==0) {
		                      System.out.println("[¿À·ù¹ß»ý : °í°´¸í] °í°´¸íÀ» ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
		                      isError = true;
		                    }
		                    
		                    // phone ¿À·ù È®ÀÎ
		                    if(cust_phone.length()!=0) {
					            String p1, p2, p3;
					            st = new StringTokenizer(cust_phone,"-");
					             p1=st.nextToken();
					             if(p1.length()!=3 || !CheckNumber(p1)) phoneError=true;
					             if(!st.hasMoreTokens())  phoneError=true;
					             else{
					            	 p2=st.nextToken();
					            	 if(p2.length()!=4 || !CheckNumber(p2)) phoneError=true;
					            	 if(!st.hasMoreTokens()) phoneError=true;
					            	 else {
					            	 p3=st.nextToken();
					            	 if(p3.length()!=4 || !CheckNumber(p3)) phoneError=true;
					            	 }
					             }
					       
					             if(phoneError) {
					            	 System.out.println("[¿À·ù¹ß»ý : ÀüÈ­¹øÈ£] ÀüÈ­¹øÈ£´Â XXX-XXXX-XXXX ¿©¾ß ÇÕ´Ï´Ù.(XÀÇ ¼ö´Â 3,4,4·Î Á¦ÇÑ, X´Â ¼ýÀÚ¿©¾ßÇÔ)");
					        		 isError=true;
					             }
					             
		                    }
				            // email ¿À·ù È®ÀÎ
		                    if(cust_email.length()!=0) {
					             String e1,e2=null,e3=null;
					             st = new StringTokenizer(cust_email,"@");
					             e1=st.nextToken();
					             if(!st.hasMoreTokens())  emailError=true;
					             else {
					            	 e2=st.nextToken();
					            	 
					            	 st = new StringTokenizer(e2,".");
					            	 if(!st.hasMoreTokens()) emailError=true;
					            	 else{
					            		 e2=st.nextToken();
					            		 if(!st.hasMoreTokens()) emailError=true;
					            		 else {
							            	 e3=st.nextToken();
						            	 }
						            }
					            	
					             }
					             if(emailError) {
					            	 System.out.println("[¿À·ù¹ß»ý : ÀÌ¸ÞÀÏ] ÀÌ¸ÞÀÏÀº XXX@XXX.XXX ¿©¾ß ÇÕ´Ï´Ù.(XÀÇ ¼ö´Â Á¦ÇÑ ¾øÀ½)");
					        		 isError=true;
					             }
					             
		                    }
		                   
		                   
		                    if(!isError) {
	                            query = "insert into customer(cust_id,cust_name,cust_address,cust_phone,cust_email) values('"+ cust_id +"','"+cust_name+"','"+
	                               cust_address+"','"+cust_phone+"','"+cust_email+"');";
	                                 
	                            stmt.executeUpdate(query);
	                            System.out.println("°í°´ ÀÔ·Â ¿Ï·á");
	                            
	                            query="SELECT * FROM customer WHERE cust_id = '" +cust_id +"'";
	                            rs=stmt.executeQuery(query);
	                            
	                            if(rs.next()) {
	                              System.out.println("¿îÀü¸éÇãÁõ¹øÈ£ \t °í°´¸í \t °í°´ÁÖ¼Ò \t°í°´ ÀüÈ­¹øÈ£ \t°í°´ ÀÌ¸ÞÀÏ ");
	                              String str = rs.getString(1) +"\t"+ rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + 
	                                            rs.getString(5) ;
	                                  
	                              System.out.println(str);
	                           }
	                       }
		                   
	                   }
	                }
	      			
	                // °í°´ »èÁ¦¹öÆ°
	                else if(e.getSource() == delete_cust) {
	                	String cust_idS = cust_idf.getText();
	                    
	                    if(cust_idS.length()==0) {
	                   		System.out.println("[¿À·ù¹ß»ý : µ¥ÀÌÅÍ ÀÔ·Â ¾øÀ½] ¿îÀü¸éÇãÁõ¹øÈ£¿¡ µ¥ÀÌÅÍ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");		
	                    }
	                    else {
	                    	if(cust_idS.length()!=15 || cust_idS.charAt(2)!='-' || cust_idS.charAt(5)!='-' || cust_idS.charAt(12)!='-' ||
	   	                         !Character.isDigit(cust_idS.charAt(0)) || !Character.isDigit(cust_idS.charAt(1)) || !Character.isDigit(cust_idS.charAt(3)) ||
	   	                         !Character.isDigit(cust_idS.charAt(4)) || !Character.isDigit(cust_idS.charAt(6)) || !Character.isDigit(cust_idS.charAt(7)) ||
	   	                         !Character.isDigit(cust_idS.charAt(8)) || !Character.isDigit(cust_idS.charAt(9)) || !Character.isDigit(cust_idS.charAt(10)) ||
	   	                         !Character.isDigit(cust_idS.charAt(11)) || !Character.isDigit(cust_idS.charAt(13)) || !Character.isDigit(cust_idS.charAt(14))) {
	   	                      System.out.println("[¿À·ù ¹ß»ý : ¿îÀü¸éÇãÁõ ¹øÈ£ Çü½Ä ¿À·ù] ¿îÀü¸éÇãÁõ¹øÈ£´Â nn-nn-nnnnnn-nn Çü½ÄÀ¸·Î ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù. nÀº ¼ýÀÚ");
	   	                   	}
	                    	
	                    	else {
			                   query = "SELECT * FROM customer WHERE cust_id ='" + cust_idS+"'";
			                   rs = stmt.executeQuery(query);
			                   
			                   System.out.println("°í°´ »èÁ¦¹öÆ° Å¬¸¯!");
			                 
			                 
			                 if(!rs.next()) { 
			                    System.out.println("[¿À·ù¹ß»ý : ¿îÀü¸éÇãÁõ¹øÈ£] ÇØ´ç ¿îÀü¸éÇãÁõÀ» °¡Áø °í°´ÀÌ ¾ø½À´Ï´Ù.");
			                 }
			                 else {
			                	 query = "SELECT * FROM rent WHERE Customer_cust_id ='" + cust_idS+"'";
			                	 rs = stmt.executeQuery(query);
			                	 while(rs.next()) {
			                		 Statement stmt2 = con.createStatement();
			                		 int car_pk = rs.getInt(1);
			                		 query = "DELETE FROM inspection WHERE Rent_Car_car_id = " + car_pk;
					                 stmt2.executeUpdate(query);
			                	 }
			                	 
			                	 
			                    query = "DELETE FROM rent WHERE Customer_cust_id = '" + cust_idS+"'";
			                    stmt.executeUpdate(query);
			                    
			                    query = "DELETE FROM repair WHERE Customer_cust_id = '" + cust_idS+"'";
			                    stmt.executeUpdate(query);
			                    
			                    query = "DELETE FROM customer WHERE cust_id = '" + cust_idS+ "'";
			                    stmt.executeUpdate(query);   
			                                                       
			                    
			                    System.out.println("ÇØ´ç ¿îÀü¸éÇãÁõÀ» °¡Áø °í°´ÀÇ »èÁ¦°¡ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
			                    
			                    
			                    query="SELECT * FROM customer WHERE cust_id >= '00-00-000000-00'";
			                    rs = stmt.executeQuery(query);
			
			                       
			                    System.out.println("¿îÀü¸éÇãÁõ¹øÈ£ \t °í°´¸í ----»ý·«----");
			
			                    while(rs.next()) {
			                        System.out.println(rs.getString(1)+"\t"+rs.getString(2)+" ³ª¸ÓÁö´Â »ý·« -----");
			                          
			                      }
			                 }
	                    }
	                 }
	                }
	                

	                else if(e.getSource()==convert_cust) {
	                	 Boolean isError = false;
	                	 Boolean phoneError=false;
	                	 Boolean emailError=false;
	                	 Boolean name=false;
		                 Boolean address=false;
		                 Boolean phone=false;
		                 Boolean email=false;
		                    
		                 StringTokenizer st;
		                   
		                 String cust_id = cust_idf.getText();
		                 String cust_name = cust_namef.getText();
		                 String cust_address = cust_addressf.getText();
		                 String cust_phone = cust_phonef.getText();
		                 String cust_email = cust_emailf.getText();
		                   
		                 if(cust_id.length()==0 ||  (cust_name.length()==0 && cust_address.length()==0 && cust_phone.length()==0 && cust_email.length()==0)) {
		                		System.out.println("[¿À·ù¹ß»ý : µ¥ÀÌÅÍ ÀÔ·Â ¾øÀ½] ¿îÀü¸éÇã¹øÈ£Ä­°ú ¹Ù²Ù°í½ÍÀº ¼Ó¼ºÄ­¿¡ µ¥ÀÌÅÍ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		                   		isError=true;
		                 }
		                 else {
			                   // ¿îÀü¸éÇãÁõ¹øÈ£
		                	   query = "select * from customer where cust_id = '"+cust_id+"'";
			                      rs = stmt.executeQuery(query);
						        if(!rs.next()) { 
						        	 System.out.println("[¿À·ù¹ß»ý : ¿îÀü¸éÇã¹øÈ£] ÇØ´ç ¿îÀü¸éÇã¹øÈ£ÀÇ °í°´ÀÌ ¾ø½À´Ï´Ù.");
						        	 isError=true;
						        }
						        else {
				                   
				                    //°í°´¸í
				                    if(cust_name.length()!=0) {
				                      name=true;
				                    }
				                    
				                    //°í°´ ÁÖ¼Ò
				                    if(cust_address.length()!=0) {
				                       address=true;
				                    }
				                    
				                    // phone ¿À·ù È®ÀÎ
				                    if(cust_phone.length()!=0) {
							            String p1, p2, p3;
							            st = new StringTokenizer(cust_phone,"-");
							             p1=st.nextToken();
							             if(p1.length()!=3 || !CheckNumber(p1)) phoneError=true;
							             if(!st.hasMoreTokens())  phoneError=true;
							             else{
							            	 p2=st.nextToken();
							            	 if(p2.length()!=4 || !CheckNumber(p2)) phoneError=true;
							            	 if(!st.hasMoreTokens()) phoneError=true;
							            	 else {
							            	 p3=st.nextToken();
							            	 if(p3.length()!=4 || !CheckNumber(p3)) phoneError=true;
							            	 }
							             }
							       
							             if(phoneError) {
							            	 System.out.println("[¿À·ù¹ß»ý : ÀüÈ­¹øÈ£] ÀüÈ­¹øÈ£´Â XXX-XXXX-XXXX ¿©¾ß ÇÕ´Ï´Ù.(XÀÇ ¼ö´Â 3,4,4·Î Á¦ÇÑ, X´Â ¼ýÀÚ¿©¾ßÇÔ)");
							        		 isError=true;
							             }
							             else {
							            	 phone=true;
							             }
				                    }
						            // email ¿À·ù È®ÀÎ
				                    if(cust_email.length()!=0) {
							             String e1,e2=null,e3=null;
							             st = new StringTokenizer(cust_email,"@");
							             e1=st.nextToken();
							             if(!st.hasMoreTokens())  emailError=true;
							             else {
							            	 e2=st.nextToken();
							            	 
							            	 st = new StringTokenizer(e2,".");
							            	 if(!st.hasMoreTokens()) emailError=true;
							            	 else{
							            		 e2=st.nextToken();
							            		 if(!st.hasMoreTokens()) emailError=true;
							            		 else {
									            	 e3=st.nextToken();
								            	 }
								            }
							            	
							             }
							             if(emailError) {
							            	 System.out.println("[¿À·ù¹ß»ý : ÀÌ¸ÞÀÏ] ÀÌ¸ÞÀÏÀº XXX@XXX.XXX ¿©¾ß ÇÕ´Ï´Ù.(XÀÇ ¼ö´Â Á¦ÇÑ ¾øÀ½)");
							        		 isError=true;
							             }
							             else {
							            	 email=true;
							             }
					                   
				                    }
				                   
				                   
				                   if(!isError) {
				                	   if(name) {
							            	query = "UPDATE customer SET cust_name='"+cust_name+"' WHERE cust_id='" + cust_id+"'";
							        		stmt.executeUpdate(query);
						            	}
						            	if(address) {
							        		query = "UPDATE customer SET cust_address='"+cust_address+"' WHERE cust_id='" + cust_id+"'";
							        		stmt.executeUpdate(query);
						            	}
						        		if(phone) {
							        		query = "UPDATE customer SET cust_phone='"+cust_phone+"' WHERE cust_id='" + cust_id+"'";
							        		stmt.executeUpdate(query);
						        		}
						        		
						        		if(email) {
							        		query = "UPDATE customer SET cust_email='"+cust_email+"' WHERE cust_id='" + cust_id+"'";
							        		stmt.executeUpdate(query);
						        		}
						        		
						        		System.out.println("ÇØ´ç ¿îÀü¸éÇã¹øÈ£ÀÇ °í°´Á¤º¸°¡ º¯°æÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
						        		query="SELECT * FROM customer WHERE cust_id='"+cust_id+"'";
					                    rs = stmt.executeQuery(query);
				
					                    
					                    System.out.println("¿îÀü¸éÇã¹øÈ£ \t ÀÌ¸§ ----»ý·«----");
				
					                    while(rs.next()) {
					                       System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t ³ª¸ÓÁö´Â »ý·« -----");
					                    }
				                   }
						        }
		                   }
	                }
	                
	                //Á¤ºñ¼Ò ÀÔ·Â¹öÆ°
	                else if(e.getSource() == insert_gar) {
	                	Boolean isError = false;
	                    Boolean phoneError=false;
	                    Boolean emailError=false;
	                     
	                    System.out.println("Á¤ºñ¼Ò ÀÔ·Â¹öÆ° Å¬¸¯!");
	                     
	                    String gar_name = gar_namef.getText();
	                    String gar_address = gar_addressf.getText();
	                    String gar_phone = gar_phonef.getText();
	                    String gar_person = gar_personf.getText();
	                    String gar_email = gar_emailf.getText();
	                    int gar_id;
	                     
	                     //Á¤ºñ¼Ò¸í
	                    if(gar_name.length()==0) {
	                    	System.out.println("[¿À·ù¹ß»ý : Á¤ºñ¼Ò¸í] Á¤ºñ¼Ò¸íÀ» ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
	                        isError = true;
	                    }
	                     
	                     //Á¤ºñ¼Ò ÁÖ¼Ò
	                    if(gar_address.length()==0) {
	                        System.out.println("[¿À·ù¹ß»ý : Á¤ºñ¼Ò ÁÖ¼Ò] Á¤ºñ¼Ò ÁÖ¼Ò¸¦ ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
	                        isError = true;
	                    }
	                     
	                    //Á¤ºñ¼Ò ÀüÈ­¹øÈ£
	                    if(gar_phone.length()==0) {
	                        System.out.println("[¿À·ù¹ß»ý : Á¤ºñ¼Ò ÀüÈ­¹øÈ£] Á¤ºñ¼Ò ÀüÈ­¹øÈ£¸¦ ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
	                        isError=true;
	                    }
	                    else {
	                        // phone ¿À·ù È®ÀÎ
	                         String p1, p2, p3;
	                         StringTokenizer st = new StringTokenizer(gar_phone,"-");
	                          p1=st.nextToken();
	                          
	                          if(p1.length()!=3 || !CheckNumber(p1)) phoneError=true;
	                          
	                          if(!st.hasMoreTokens())  phoneError=true;
	                          else{
	                             p2=st.nextToken();
	                             if(p2.length()!=4 || !CheckNumber(p2)) phoneError=true;
	                             if(!st.hasMoreTokens()) phoneError=true;
	                             else {
	                            	 p3=st.nextToken();
	                             	 if(p3.length()!=4 || !CheckNumber(p3)) phoneError=true;
	                             }
	                          }
	                    
	                          if(phoneError) {
	                             System.out.println("[¿À·ù¹ß»ý : Á¤ºñ¼Ò ÀüÈ­¹øÈ£] ÀüÈ­¹øÈ£´Â XXX-XXXX-XXXX ¿©¾ß ÇÕ´Ï´Ù.(XÀÇ ¼ö´Â 3,4,4·Î Á¦ÇÑ, X´Â ¼ýÀÚ¿©¾ßÇÔ)");
	                            isError=true;
	                          }
	                     }
	                     
	                     //´ã´çÀÚ ÀÌ¸§
	                     if(gar_person.length()==0) {
	                        System.out.println("[¿À·ù¹ß»ý : ´ã´çÀÚ ÀÌ¸§] ´ã´çÀÚ ÀÌ¸§À» ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
	                        isError=true;
	                     }
	                     
	                     //Á¤ºñ¼Ò ÀÌ¸ÞÀÏ
	                     if(gar_email.length()==0) {
	                        System.out.println("[¿À·ù¹ß»ý : ÀÌ¸ÞÀÏ] ÀÌ¸ÞÀÏÀ» ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
	                        isError = true;
	                     }
	                     else {
	                         // email ¿À·ù È®ÀÎ
	                         String e1,e2=null,e3=null;
	                         StringTokenizer st = new StringTokenizer(gar_email,"@");
	                         e1=st.nextToken();
	                         
	                         if(!st.hasMoreTokens())  emailError=true;
	                         else {
	                            e2=st.nextToken();
	                            
	                            st = new StringTokenizer(e2,".");
	                            if(!st.hasMoreTokens()) emailError=true;
	                            else{
	                               e2=st.nextToken();
	                               if(!st.hasMoreTokens()) emailError=true;
	                               else {
	                                  e3=st.nextToken();
	                               }
	                           }
	                           
	                         }
	                         if(emailError) {
	                            System.out.println("[¿À·ù¹ß»ý : ÀÌ¸ÞÀÏ] ÀÌ¸ÞÀÏÀº XXX@XXX.XXX ¿©¾ß ÇÕ´Ï´Ù.(XÀÇ ¼ö´Â Á¦ÇÑ ¾øÀ½)");
	                           isError=true;
	                         }
	                     }          
	                                    

	                    if(!isError) {
	                       query="SELECT * FROM garage";
	                        rs=stmt.executeQuery(query);
	                        rs.last();
	                        gar_id=rs.getInt(1)+1;
	                         
	                       
	                       query = "insert into garage(gar_id,gar_name,gar_address,gar_phone,gar_person,gar_email) values('"+ gar_id +"','"+gar_name+"','"+
	                          gar_address+"','"+gar_phone+"','"+gar_person+"','"+gar_email+"');";
	                            
	                       stmt.executeUpdate(query);
	                       System.out.println("Á¤ºñ¼Ò ÀÔ·Â ¿Ï·áµÇ¾ú½À´Ï´Ù.");
	                       
	                       query="SELECT * FROM garage";
		                   rs=stmt.executeQuery(query);
			               rs.last();
			                 
			               System.out.println("Ä·ÇÎÄ« Á¤ºñ¼Ò ID \t Á¤ºñ¼Ò¸í \t Á¤ºñ¼Ò ÁÖ¼Ò \tÁ¤ºñ¼Ò ÀüÈ­¹øÈ£ \t´ã´çÀÚ ÀÌ¸§ \tÀÌ¸ÞÀÏ ");
			               String str = rs.getInt(1) +"\t"+ rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + 
			                                rs.getString(5)+"\t"+rs.getString(6) ;
			                      
			               System.out.println(str);
	                    
	                    }
	                     
	                }
	                
	                //Á¤ºñ¼Ò »èÁ¦
	                else if(e.getSource()==delete_gar) {
	                	System.out.println("Á¤ºñ¼Ò »èÁ¦¹öÆ° Å¬¸¯!");
	                	
	                	String gar_idS = gar_idf.getText();
	                    
	                    if(gar_idS.length()==0) {
	                   		System.out.println("[¿À·ù¹ß»ý : µ¥ÀÌÅÍ ÀÔ·Â ¾øÀ½] Á¤ºñ¼Ò ID¿¡ µ¥ÀÌÅÍ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
	                   		
	                    }
	                    else {
	                    	if(!CheckNumber(gar_idS)) {
		                		System.out.println("[¿À·ù¹ß»ý : Á¤ºñ¼Ò ID] Á¤ºñ¼Ò ID´Â ¾çÀÇ Á¤¼ö¿©¾ß ÇÕ´Ï´Ù.");
		                	}
	                    	else {
			                   int gar_idI = Integer.parseInt(gar_idS);
			                   query = "SELECT * FROM garage WHERE gar_id =" + gar_idI;
			                   rs = stmt.executeQuery(query);
			              
			                 
			                 if(!rs.next()) { 
			                    System.out.println("[¿À·ù¹ß»ý : Ä·ÇÎÄ« Á¤ºñ¼Ò ID] ÇØ´ç IDÀÇ Á¤ºñ¼Ò°¡ ¾ø½À´Ï´Ù.");
			                 }
			                 else {
		                
			                    query = "DELETE FROM repair WHERE Garage_gar_id = " + gar_idI;
			                    stmt.executeUpdate(query);
			                    
			                    query = "DELETE FROM garage WHERE gar_id = " + gar_idI;
			                    stmt.executeUpdate(query);   
			                                                       
			                    
			                    System.out.println("ÇØ´ç IDÀÇ Á¤ºñ¼Ò »èÁ¦°¡ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
			                    
			     
			                    
			                       query="SELECT * FROM garage WHERE gar_id >=" + 0;
			                       rs = stmt.executeQuery(query);
			
			                       
			                       System.out.println("Á¤ºñ¼Ò ID \t Á¤ºñ¼ÒÀÌ¸§ ----»ý·«----");
			
			                       while(rs.next()) {
			                          System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+" ³ª¸ÓÁö´Â »ý·« -----");
			                          
			                       }
			                 }
	                    }
	                 }
	                }
	                
	                //Á¤ºñ¼Ò º¯°æ¹öÆ°
	                else if(e.getSource()==convert_gar) {
	                	System.out.println("Á¤ºñ¼Ò º¯°æ¹öÆ° Å¬¸¯!");
	                	
	                	 Boolean isError = false;
	                     Boolean phoneError=false;
	                     Boolean emailError=false;
	                     
	                     StringTokenizer st;
	                     
	                     Boolean name=false;
	                     Boolean address=false;
	                     Boolean phone=false;
	                     Boolean person=false;
	                     Boolean email=false;
	                    
	                     
	                     String gar_name = gar_namef.getText();
	                     String gar_address = gar_addressf.getText();
	                     String gar_phone = gar_phonef.getText();
	                     String gar_person = gar_personf.getText();
	                     String gar_email = gar_emailf.getText();
	                     String gar_idS = gar_idf.getText();
	                     int gar_id;
	                     
	                     if(gar_idS.length()==0 ||  (gar_name.length()==0 && gar_address.length()==0 && gar_phone.length()==0 && gar_email.length()==0)) {
		                		System.out.println("[¿À·ù¹ß»ý : µ¥ÀÌÅÍ ÀÔ·Â ¾øÀ½] Á¤ºñ¼Ò IDÄ­°ú ¹Ù²Ù°í½ÍÀº ¼Ó¼ºÄ­¿¡ µ¥ÀÌÅÍ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		                   		isError=true;
		                }
	                     else { 
	                     
		                     //Á¤ºñ¼Ò¸í
		                     if(gar_name.length()!=0) {
		                       name=true;
		                     }
		                     
		                     //Á¤ºñ¼Ò ÁÖ¼Ò
		                     if(gar_address.length()!=0) {
		                        address=true;
		                     }
		                     
		                     // phone ¿À·ù È®ÀÎ
			                    if(gar_phone.length()!=0) {
						            String p1, p2, p3;
						            st = new StringTokenizer(gar_phone,"-");
						            p1=st.nextToken();
						            if(p1.length()!=3 || !CheckNumber(p1)) phoneError=true;
						            if(!st.hasMoreTokens())  phoneError=true;
						            else{
						            	 p2=st.nextToken();
						            	 if(p2.length()!=4 || !CheckNumber(p2)) phoneError=true;
						            	 if(!st.hasMoreTokens()) phoneError=true;
						            	 else {
						            	 p3=st.nextToken();
						            	 if(p3.length()!=4 || !CheckNumber(p3)) phoneError=true;
						            	 }
						             }
						       
						             if(phoneError) {
						            	 System.out.println("[¿À·ù¹ß»ý : ÀüÈ­¹øÈ£] ÀüÈ­¹øÈ£´Â XXX-XXXX-XXXX ¿©¾ß ÇÕ´Ï´Ù.(XÀÇ ¼ö´Â 3,4,4·Î Á¦ÇÑ, X´Â ¼ýÀÚ¿©¾ßÇÔ)");
						        		 isError=true;
						             }
						             else {
						            	 phone=true;
						             }
			                    }
			                    
					            // email ¿À·ù È®ÀÎ
			                    if(gar_email.length()!=0) {
						             String e1,e2=null,e3=null;
						             st = new StringTokenizer(gar_email,"@");
						             e1=st.nextToken();
						             if(!st.hasMoreTokens())  emailError=true;
						             else {
						            	 e2=st.nextToken();
						            	 
						            	 st = new StringTokenizer(e2,".");
						            	 if(!st.hasMoreTokens()) emailError=true;
						            	 else{
						            		 e2=st.nextToken();
						            		 if(!st.hasMoreTokens()) emailError=true;
						            		 else {
								            	 e3=st.nextToken();
							            	 }
							            }
						            	
						             }
						             if(emailError) {
						            	 System.out.println("[¿À·ù¹ß»ý : ÀÌ¸ÞÀÏ] ÀÌ¸ÞÀÏÀº XXX@XXX.XXX ¿©¾ß ÇÕ´Ï´Ù.(XÀÇ ¼ö´Â Á¦ÇÑ ¾øÀ½)");
						        		 isError=true;
						             }
						             else {
						            	 email=true;
						             }
				                   
			                    }
			                   
		                     
		                     //´ã´çÀÚ ÀÌ¸§
		                     if(gar_person.length()!=0) {
		                        person=true;
		                     }
		                     
		                    
		                    if(!isError) {
		                    	if(name) {
					            	query = "UPDATE garage SET gar_name='"+gar_name+"' WHERE gar_id='" + gar_idS+"'";
					        		stmt.executeUpdate(query);
				            	}
				            	if(address) {
					        		query = "UPDATE garage SET gar_address='"+gar_address+"' WHERE gar_id='" + gar_idS+"'";
					        		stmt.executeUpdate(query);
				            	}
				        		if(phone) {
					        		query = "UPDATE garage SET gar_phone='"+gar_phone+"' WHERE gar_id='" + gar_idS+"'";
					        		stmt.executeUpdate(query);
				        		}
				        		if(person) {
				        			query = "UPDATE garage SET gar_person='"+gar_person+"' WHERE gar_id='" + gar_idS+"'";
					        		stmt.executeUpdate(query);
				        		}
				        		if(email) {
					        		query = "UPDATE garage SET gar_email='"+gar_email+"' WHERE gar_id='" + gar_idS+"'";
					        		stmt.executeUpdate(query);
				        		}
				        		
				        		
				        		System.out.println("ÇØ´ç IDÀÇ Á¤ºñ¼Ò Á¤º¸°¡ º¯°æÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
				        		query="SELECT * FROM garage WHERE gar_id > '0'";
			                    rs = stmt.executeQuery(query);
		
			                    
			                    System.out.println("Á¤ºñ¼ÒID \t ÀÌ¸§ ----»ý·«----");
		
			                    while(rs.next()) {
			                       System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t ³ª¸ÓÁö´Â »ý·« -----");
			                    }
		                    }
	                     }
	                }
	                
	                //¹ÝÈ¯
	                else if(e.getSource() == return_car) {
	                      String inspec_rents = inspec_rentf.getText(); //´ë¿©¹øÈ£                      
	                      String inspec_cars = inspec_carf.getText(); // Ä·ÇÎÄ« µî·Ï ID
	                      String inspec_fronts = inspec_frontf.getText();
	                      String inspec_lefts = inspec_leftf.getText();
	                      String inspec_rights = inspec_rightf.getText();
	                      String inspec_behinds = inspec_behindf.getText();
	                      String inspec_repairs = inspec_repairf.getText(); // ¼ö¸®ÇÊ¿ä¿©ºÎ ÇÊ¿ä 1,ºÒÇÊ¿ä 0
	                      int inspec_carI;
	                      int inspec_rentI;
	                      int inspec_repairI;
	                      
	                      Boolean isError = false;
	                      
	                      System.out.println("¹ÝÈ¯/Á¡°Ë³»¿ª ÀúÀå¹öÆ° Å¬¸¯!");
	                      
	                   
	                      if(inspec_rents.length()==0||inspec_cars.length()==0||inspec_fronts.length()==0||inspec_lefts.length()==0
	                            ||inspec_rights.length()==0||inspec_behinds.length()==0||inspec_repairs.length()==0) {
	                         System.out.println("[¿À·ù ¹ß»ý] ¸ðµç Ä­¿¡ µ¥ÀÌÅÍ¸¦ ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
	                         isError= true;
	                      }
	                      else { 
	                         if(!CheckNumber(inspec_repairs) || !CheckNumber(inspec_cars) || !CheckNumber(inspec_rents)
	                               || !inspec_repairs.contentEquals("0") && !inspec_repairs.contentEquals("1")) {
	                            System.out.println("[¿À·ù ¹ß»ý] ´ë¿©¹øÈ£, Ä·ÇÎÄ« µî·ÏID, ¼ö¸®ÇÊ¿ä¿©ºÎ´Â 0º¸´Ù Å« Á¤¼ö·Î¸¸ ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
	                            System.out.println("´Ü, ¼ö¸®ÇÊ¿ä¿©ºÎ´Â ÇÊ¿äÇÏ´Ù¸é 1, ºÒÇÊ¿äÇÏ´Ù¸é 0À» ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
	                            isError = true;
	                         }
	                         else {
	                            inspec_carI= Integer.parseInt(inspec_cars); //Ä·ÇÎÄ« µî·ÏID
	                            inspec_rentI = Integer.parseInt(inspec_rents); //´ë¿©¹øÈ£
	                                 
	                           query = "SELECT * FROM rent WHERE Car_car_id =" + inspec_carI;
	                           rs = stmt.executeQuery(query);
	                                  
	                           if(!rs.next()) { 
	                              System.out.println("[¿À·ù¹ß»ý : Ä·ÇÎÄ« ID] ÇØ´ç IDÀÇ Ä·ÇÎÄ«°¡ ¾ø½À´Ï´Ù.");
	                              isError = true;
	                            }
	                                
	                           query = "SELECT * FROM rent WHERE rent_id =" + inspec_rentI;
	                           rs = stmt.executeQuery(query);
	                                  
	                           if(!rs.next()) { 
	                                System.out.println("[¿À·ù¹ß»ý : ´ë¿© ¹øÈ£ID] ÇØ´ç ´ë¿© ¹øÈ£°¡ ¾ø½À´Ï´Ù.");
	                                isError = true;
	                           }
	                                
	                          
	                           query = "select * from rent where rent_id = '"+inspec_rents+"'";
	                           rs = stmt.executeQuery(query);
	                           if(!rs.next()) { 
	                              System.out.println("[¿À·ù¹ß»ý] ÇØ´ç ´ë¿© ¹øÈ£¿Í Ä·ÇÎÄ«ID¿¡ ¸Â´Â ´ë¿© ±â·ÏÀÌ ¾ø½À´Ï´Ù.");
	                              isError = true;
	                          }
	                           else {
	                        	   if(rs.getInt(8)!=Integer.parseInt(inspec_cars)) {
	                        		   System.out.println("[¿À·ù¹ß»ý] ÇØ´ç ´ë¿© ¹øÈ£¿Í Ä·ÇÎÄ«ID¿¡ ¸Â´Â ´ë¿© ±â·ÏÀÌ ¾ø½À´Ï´Ù.");
	                        		   isError = true;
	                        	   }
	                           }
	                           
	                           //Áßº¹¿À·ù
	                           query = "select * from inspection where Rent_rent_id = "+inspec_rentI;
	                           rs= stmt.executeQuery(query);
	                           
	                           while(rs.next()) {
			                       if(rs.getString(1).length()!=0) {
			                          System.out.println("[¿À·ù¹ß»ý : ´ë¿©¹øÈ£] ÀÌ¹Ì Á¡°Ë³»¿ª¿¡ µî·ÏµÇ¾î ÀÖ½À´Ï´Ù.");
			                          isError = true;
			                       }
			                   }
	                               
	                          }
	                         
	                       }

	                      
	                      if(!isError) {
	                         
	                         inspec_repairI = Integer.parseInt(inspec_repairs);
	                         inspec_carI= Integer.parseInt(inspec_cars); //Ä·ÇÎÄ« µî·ÏID
	                         inspec_rentI = Integer.parseInt(inspec_rents); //´ë¿©¹øÈ£
	                     
	                         query = "insert into inspection(frontinfo,leftinfo,rightinfo,behindinfo,repairinfo,Rent_rent_id,Rent_Car_car_id) values('"+ inspec_fronts +"','"+inspec_lefts+"','"+
	                                inspec_rights+"','"+inspec_behinds+"',"+inspec_repairI+","+inspec_rentI+","+inspec_carI+")";
	                                stmt.executeUpdate(query); 
	                         
	                         if(inspec_repairI==0) //¼ö¸®ÇÊ¿äÇÏÁö¾Ê´Ù¸é ´Ù½Ã Â÷ ´ë¿© °¡´É
	                            carBoolean[inspec_carI]=true;
	                         
	                         System.out.println("¹ÝÈ¯/Á¡°Ë³»¿ª ÀúÀå ¿Ï·á");
	                         if(inspec_repairI==1) { // ¼ö¸®ÇÊ¿äÇÏ¸é Á¤ºñ³»¿ª ÀÛ¼º ÇÊ¿ä
	                        	 repairBoolean[inspec_carI]=true;
	                         }
	                         
	                         query="SELECT * FROM inspection";
		                     rs=stmt.executeQuery(query);
			                 rs.last();
			                 
			                 System.out.println("¾ÕºÎºÐ ¼³¸í \t ¿ÞÂÊ ¼³¸í \t ¿À¸¥ÂÊ ¼³¸í \t µÚÂÊ ¼³¸í \t¼ö¸®ÇÊ¿ä ¿©ºÎ \t°íÀ¯ ´ë¿© ¹øÈ£ \tÄ·ÇÎÄ« µî·Ï ID ");
			                 String str = rs.getString(1) +"\t"+ rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + 
			                                rs.getInt(5)+"\t" + rs.getInt(6) +"\t" + rs.getInt(7);
			                      
			                 System.out.println(str);
	                     }
	               }
	                
	                //Á¤ºñ³»¿ª ÀÔ·Â
	                else if(e.getSource()==request_car) {
	                	System.out.println("Á¤ºñ³»¿ª ÀÔ·Â ¹öÆ° Å¬¸¯!");
	                	Boolean isError = false;
	                	Boolean carError = false;
	                	Boolean compError =false;
	                	Boolean dateError =false;
	                	
	                	String car_idS=repair_carf.getText();
	                	int car_id=0;
	                	String gar_idS=repair_garf.getText();
	                	int gar_id=0;
	                	String comp_idS=repair_corpf.getText();
	                	int comp_id=0;
	                	String cust_idS=repair_custf.getText();
	                	String infoS=repair_infof.getText();
	                	String dateS=repair_datef.getText();
	                	String priceS=repair_pricef.getText();
	                	int price=0;
	                	String paydateS=repair_paydatef.getText();
	                	String extraS=repair_extraf.getText();
	                	
	                	// ºó °÷ÀÌ ¾ø´ÂÁö È®ÀÎ
	                	if(car_idS.length()==0 || gar_idS.length()==0 || comp_idS.length()==0 || cust_idS.length()==0 || infoS.length()==0 
	                			|| dateS.length()==0 || priceS.length()==0 ||paydateS.length()==0 || extraS.length()==0) {
	                		System.out.println("[¿À·ù¹ß»ý : µ¥ÀÌÅÍ ÀÔ·Â ¾øÀ½] ¸ðµç Ä­¿¡ µ¥ÀÌÅÍ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
	                		isError=true;
	                	}
	                	else {
	                		// Â÷ ID ¿À·ùÈ®ÀÎ
	                		if(CheckNumber(car_idS)) {
		                    	 car_id = Integer.parseInt(car_idS);
			                     query = "SELECT * FROM car WHERE car_id =" + car_id;
			                     rs = stmt.executeQuery(query);
			                     if(!rs.next()) { 
			                        System.out.println("[¿À·ù¹ß»ý : Ä·ÇÎÄ« ID] ÇØ´ç IDÀÇ Ä·ÇÎÄ«°¡ ¾ø½À´Ï´Ù.");
			                        isError=true;
			                        carError=true;
			                     }
			                     else {
			                    	 int i = rs.getInt(1);
			                    	 if(repairBoolean[i]==false) {
			                    		 System.out.println("[¿À·ù¹ß»ý : ¼ö¸®ºÒÇÊ¿ä Ä·ÇÎÄ«] ÇØ´ç IDÀÇ Ä·ÇÎÄ«´Â ¼ö¸® ºÒÇÊ¿äÇÕ´Ï´Ù.");
			 	                        isError=true;
			 	                        carError=true;
			                    	 }
			                     }
		                     }
		                     else {
		                    	 isError=true;
		                    	 carError=true;
		                    	 System.out.println("[¿À·ù¹ß»ý : Ä·ÇÎÄ« ID] Ä·ÇÎÄ« ID´Â ¾çÀÇ Á¤¼ö¿©¾ß ÇÕ´Ï´Ù.");
		                     }
	                		
	                		// Á¤ºñ¼Ò ID ¿À·ùÈ®ÀÎ
	                		if(!CheckNumber(gar_idS)) { 
		                		isError=true;
		                		System.out.println("[¿À·ù¹ß»ý : Á¤ºñ¼Ò ID] Á¤ºñ¼Ò ID´Â ¾çÀÇ Á¤¼öÀÔ´Ï´Ù.");
		                	}
		                	else{
		                		gar_id=Integer.parseInt(gar_idS);
		                		query = "SELECT * FROM garage WHERE gar_id =" + gar_id;
					        	 rs = stmt.executeQuery(query);
					        	 if(!rs.next()) { 
					        		 System.out.println("[¿À·ù¹ß»ý : Á¤ºñ¼Ò ID] Á¤ºñ¼Ò IDÀÇ È¸»ç°¡ ¾ø½À´Ï´Ù.");
					        		 isError=true;
					        	 }
				        	 }
	                		
	                		
		                	// È¸»ç ID ¿À·ù È®ÀÎ
		                	if(!CheckNumber(comp_idS)) { 
		                		isError=true;
		                		compError=true;
		                		System.out.println("[¿À·ù¹ß»ý : È¸»ç ID] È¸»ç ID´Â ¾çÀÇ Á¤¼öÀÔ´Ï´Ù.");
		                	}
		                	else{
		                		comp_id=Integer.parseInt(comp_idS);
		                		query = "SELECT * FROM company WHERE comp_id =" + comp_id;
					        	 rs = stmt.executeQuery(query);
					        	 if(!rs.next()) { 
					        		 System.out.println("[¿À·ù¹ß»ý : È¸»ç ID] ÇØ´ç IDÀÇ È¸»ç°¡ ¾ø½À´Ï´Ù.");
					        		 isError=true;
					        		 compError=true;
					        	 }
					        	 else {
					        		 if(!carError) {
					        			 query = "SELECT * FROM car WHERE car_id =" + car_id;
					        			 rs = stmt.executeQuery(query);
					        			 if(rs.next()) {
					                    	 int i = rs.getInt(10); 
					                    	 if(Integer.parseInt(comp_idS)!=i) {
					                    		 isError=true;
					                    		 compError=true;
					                   		 System.out.println("[¿À·ù¹ß»ý : È¸»ç ID] Ä·ÇÎÄ« ID¿Í È¸»ç ID°¡ ¼­·Î ¸ÂÁö¾Ê½À´Ï´Ù.");
					                    	 }
					                     }
					        		 }
					        	 }
				        	 }
		                	
		                	// °í°´ ¿îÀü¸éÇãÁõ ¹øÈ£ ¿À·ù È®ÀÎ		
		                	if(!(compError||carError)) {
			                	query = "SELECT * FROM rent WHERE Car_car_id = '" + car_idS +"'";
			        			rs = stmt.executeQuery(query);
			        			
			        			if(rs.next()) {
			        				rs.last();
			        				if(!rs.getString(10).contentEquals(cust_idS)) {
				        				System.out.println("[¿À·ù¹ß»ý : ¿îÀü¸éÇã¹øÈ£] ¿îÀü¸éÇã¹øÈ£°¡ ÀÏÄ¡ÇÏÁö ¾ø½À´Ï´Ù.");
				                        isError=true;
			                        }
			        			}
		                	}
		                     
		                	// ¼ö¸®³¯Â¥ ¿À·ù È®ÀÎ
		                	String Y, M, D;
				             int y,m,d;
				             StringTokenizer st;
				             st = new StringTokenizer(dateS,"-");
				             Y=st.nextToken();
				             if(st.hasMoreTokens()) {
					             M=st.nextToken();
					             if(st.hasMoreTokens()) {
						             D=st.nextToken();
						             y=Integer.parseInt(Y);
						             m=Integer.parseInt(M);
						             d=Integer.parseInt(D);
						             if(m>12 || m <1 || d >31 || d<1) {
						            	 System.out.println("[¿À·ù¹ß»ý : ¼ö¸®³¯Â¥] YYYY-MM-DD¿¡¼­ MMÀº 1~12»çÀÌ, DD´Â 1~31»çÀÌÀÇ ¼ö¿©¾ß ÇÕ´Ï´Ù.");
						        		 isError=true;
						        		 dateError=true;
						             }
					             }
					             else { 
					            	 isError=true;
					            	 dateError=true;
					            	 System.out.println("[¿À·ù¹ß»ý : ¼ö¸®³¯Â¥] YYYY-MM-DD¿¡¼­ MMÀº 1~12»çÀÌ, DD´Â 1~31»çÀÌÀÇ ¼ö¿©¾ß ÇÕ´Ï´Ù.");
					             }
				             }
				             else {
				            	 System.out.println("[¿À·ù¹ß»ý : ¼ö¸®³¯Â¥] YYYY-MM-DD¿¡¼­ MMÀº 1~12»çÀÌ, DD´Â 1~31»çÀÌÀÇ ¼ö¿©¾ß ÇÕ´Ï´Ù.");
				            	 isError=true;
				            	 dateError=true;
				             }
				             if(!dateError) {
				            	 SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
				            	 String date1="";
				            	
				            	 query = "SELECT * FROM rent WHERE Car_car_id = '" + car_idS +"'";
				        			rs = stmt.executeQuery(query); 
				        			if(rs.next()) {
				        				rs.last();
				        				date1 = fm.format(rs.getDate(5)); //´ë¿©Å×ÀÌºí¿¡¼­ ´ë¿© ³³ÀÔ±âÇÑ
				        			}
				        			
				            	 
				            	 if(dateS.compareTo(date1)<=0) {
		                              System.out.println("[¿À·ù¹ß»ý : ¼ö¸®³¯Â¥] ¼ö¸®³¯Â¥´Â ´ë¿©ºñ¿ë³³ÀÔ±âÇÑ º¸´Ù ³ªÁßÀÌ¾î¾ßÇÕ´Ï´Ù.");
		                              isError = true;
		                              dateError=true;
		                       }
				            	 
				            	 
				             }
				             
				             
		                	// ¼ö¸®ºñ¿ë ¿À·ù È®ÀÎ
				             if(!CheckNumber(priceS)) { 
			                		isError=true;
			                		System.out.println("[¿À·ù¹ß»ý : ¼ö¸®ºñ¿ë] ¼ö¸®ºñ¿ëÀº ¾çÀÇ Á¤¼öÀÔ´Ï´Ù.");
			                }
			                else {
			                	price = Integer.parseInt(priceS);
				                if(price < 0) {
				                	System.out.println("[¿À·ù¹ß»ý : ¼ö¸®ºñ¿ë] ¼ö¸®ºñ¿ëÀº 0ÀÌ»ó ÀÌ¾î¾ß ÇÕ´Ï´Ù.");
				                	isError=true;
				               	}
			                }
				    	
		                	
		                	// ³³ÀÔ±âÇÑ ¿À·ù È®ÀÎ
			                Boolean date1Error=false;  
				             st = new StringTokenizer(paydateS,"-");
				             Y=st.nextToken();
				             if(st.hasMoreTokens()) {
					             M=st.nextToken();
					             if(st.hasMoreTokens()) {
						             D=st.nextToken();
						             y=Integer.parseInt(Y);
						             m=Integer.parseInt(M);
						             d=Integer.parseInt(D);
						             if(m>12 || m <1 || d >31 || d<1) {
						            	 System.out.println("[¿À·ù¹ß»ý : ³³ÀÔ±âÇÑ] YYYY-MM-DD¿¡¼­ MMÀº 1~12»çÀÌ, DD´Â 1~31»çÀÌÀÇ ¼ö¿©¾ß ÇÕ´Ï´Ù.");
						        		 isError=true;
						        		 date1Error=true;
						             }
					             }
					             else { 
					            	 isError=true;
					            	 date1Error=true;
					            	 System.out.println("[¿À·ù¹ß»ý : ³³ÀÔ±âÇÑ] YYYY-MM-DD¿¡¼­ MMÀº 1~12»çÀÌ, DD´Â 1~31»çÀÌÀÇ ¼ö¿©¾ß ÇÕ´Ï´Ù.");
					             }
				             }
				             else {
				            	 System.out.println("[¿À·ù¹ß»ý : ³³ÀÔ±âÇÑ] YYYY-MM-DD¿¡¼­ MMÀº 1~12»çÀÌ, DD´Â 1~31»çÀÌÀÇ ¼ö¿©¾ß ÇÕ´Ï´Ù.");
				            	 isError=true;
				            	 date1Error=true;
				             }
				             if(!date1Error && !dateError) {
				            	 if(paydateS.compareTo(dateS)<=0) {
		                         System.out.println("[¿À·ù¹ß»ý : ³³ÀÔ±âÇÑ] ³³ÀÔ±âÇÑÀº ¼ö¸®³¯Â¥ º¸´Ù ³ªÁßÀÌ¾î¾ßÇÕ´Ï´Ù.");
		                         isError = true;
		                          date1Error=true;
		                       }
				             }
				             
				             // ¿À·ù°¡ ¾øÀ¸¸é »ðÀÔ 
			                   if(!isError) {
			                	   query="SELECT * FROM repair";
			                       rs=stmt.executeQuery(query);
			                       rs.last();
			                       int repair_idI=rs.getInt(1)+1;
			                    
			                       PreparedStatement statement = null;
		                           statement = con.prepareStatement("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id)"
		                            		 +"value(?,?,?,?,?,?,?,?,?,?)");
		                    
		                           statement.setInt(1,repair_idI);
		                           statement.setString(2, infoS);
		                           statement.setDate(3, java.sql.Date.valueOf(dateS));
		                           statement.setInt(4, Integer.parseInt(priceS));
		                           statement.setDate(5,java.sql.Date.valueOf(paydateS));
		                           statement.setString(6,extraS);
		                           statement.setInt(7,gar_id);
		                           statement.setInt(8, car_id);
		                           statement.setInt(9, comp_id);
		                           statement.setString(10,cust_idS);
		                             
		                           statement.executeUpdate();

			                       carBoolean[car_id]=true;
			                       repairBoolean[car_id]=false;
			                       
			                       rs=stmt.executeQuery(query);
			                     
			                       rs.last();
			                       System.out.println("Á¤ºñ³»¿ª ÀÔ·Â ¿Ï·áµÇ¾ú½À´Ï´Ù.");
			                       System.out.println("Á¤ºñID \t Á¤ºñ Á¤º¸ \t ---- ³ª¸ÓÁö »ý·« ----");
			                       String str = rs.getInt(1) +"\t" + rs.getString(2) ;
			                      
			                       System.out.println(str);
			                      
			                    }
	                	}
	                	
	                }
	                else if(e.getSource()==repair_delete) {
	                	System.out.println("Á¤ºñ³»¿ª »èÁ¦ ¹öÆ° Å¬¸¯!");
	                	Boolean isError=false;
	                	String repair_idS = repair_idf.getText();
	                	if(repair_idS.length()==0) {
	                		System.out.println("[¿À·ù¹ß»ý : Á¤ºñ¹øÈ£ ÀÔ·Â ¾øÀ½] Á¤ºñ ¹øÈ£¸¦ ÀÔ·ÂÇØ¾ßÇÕ´Ï´Ù.");
	                		isError=true;
	                	}
	                	else {
	                		if(CheckNumber(repair_idS)) {
	                			query = "SELECT * FROM repair WHERE repair_id = '" + repair_idS +"'";
			        			rs = stmt.executeQuery(query);
			        			if(!rs.next()) {
			        				System.out.println("[¿À·ù¹ß»ý : Á¤ºñ¹øÈ£] ÇØ´ç Á¤ºñ ¹øÈ£ÀÇ Á¤ºñ³»¿ªÀÌ ¾ø½À´Ï´Ù.");
		                			isError=true;
			        			}
	                		}
	                		else {
	                			System.out.println("[¿À·ù¹ß»ý : Á¤ºñ¹øÈ£] Á¤ºñ ¹øÈ£´Â ¾çÀÇÁ¤¼öÀÌ¾î¾ß ÇÕ´Ï´Ù.");
	                			isError=true;
	                		}
	                	}
	                	if(!isError) {
	                		query = "DELETE FROM repair WHERE repair_id = '" + repair_idS + "'";
		                    stmt.executeUpdate(query);   
		                                                       
		                    
		                    System.out.println("ÇØ´ç IDÀÇ Á¤ºñ³»¿ª »èÁ¦°¡ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
		                    
		                    
		                    query="SELECT * FROM repair WHERE repair_id >=" + 0;
		                       rs = stmt.executeQuery(query);
		
		                       
		                       System.out.println("Á¤ºñ³»¿ª ID \t Á¤ºñ³»¿ª ----»ý·«----");
		
		                       while(rs.next()) {
		                          System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+" ³ª¸ÓÁö´Â »ý·« -----");
		                          
		                       }
	                	}
	                }
	                
	                else if(e.getSource()==repair_convert) {
	                	System.out.println("Á¤ºñ³»¿ª º¯°æ ¹öÆ° Å¬¸¯!");

	                	String repair_ids = repair_idf.getText();//Á¤ºñ³»¿ª ID(PK)
	                	String repair_cars = repair_carf.getText(); // Ä·ÇÎÄ« µî·ÏID (FK)
	                	String repair_gars = repair_garf.getText(); //Á¤ºñ¼ÒID (FK)
	                	String repair_corps = repair_corpf.getText(); //È¸»çID (FK)
	                	String repair_custs = repair_custf.getText(); //°í°´  ¿îÀü¸éÇãÁõ¹øÈ£(FK)
	                	String repair_infos = repair_infof.getText(); // Á¤ºñ³»¿ª
	                	String repair_dates = repair_datef.getText(); //¼ö¸®³¯Â¥
	                	String repair_prices = repair_pricef.getText(); // ¼ö¸®ºñ¿ë
	                	String repair_paydates = repair_paydatef.getText(); //³³ÀÔ ±âÇÑ
	                	String repair_extras = repair_extraf.getText(); //±âÅ¸ Á¤ºñ ³»¿ª Á¤º¸
	                	
	                	Boolean isError = false;
	                	Boolean dateError = false;
	                	
	                	Boolean date1 = false; //¼ö¸®³¯Â¥
	                	Boolean date2 = false; //³³ÀÔ±âÇÑ
	                	Boolean price = false; //¼ö¸® ºñ¿ë
	                	Boolean info = false; //Á¤ºñ³»¿ª
	                	Boolean extra = false; //±âÅ¸ Á¤ºñ ³»¿ª
	                	
	                	int car_idI=0;
	                	int repair_idI = 0;
	                	int repair_priceI = 0;
	                	
	                	String Y, M, D;
			            int y,m,d;
	                	StringTokenizer st;
	                	
	                	
	                	//ºó °÷ ÀÖ´ÂÁö È®ÀÎ, ºñ¾îÀÖ´ÂÁö È®ÀÎ
	                	if(repair_ids.length()==0 || repair_cars.length()!=0|| repair_gars.length()!=0|| repair_corps.length()!=0||
	                			repair_custs.length()!=0 ||(repair_infos.length()==0 && repair_dates.length()==0 
	                			&& repair_prices.length()==0 && repair_paydates.length()==0 && repair_extras.length()==0)) {
	                		System.out.println("[¿À·ù¹ß»ý] Á¤ºñ³»¿ª IDÄ­°ú ¹Ù²Ù°í½ÍÀº ¼Ó¼ºÄ­¿¡ µ¥ÀÌÅÍ¸¦ ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
	                		System.out.println("¶ÇÇÑ, Á¤ºñ¼ÒID, ´ë¿©È¸»çID, °í°´ ¿îÀü¸éÇãÁõ¹øÈ£, Ä·ÇÎÄ« µî·ÏID´Â ºñ¿ö¾ß ÇÕ´Ï´Ù.");
	                		isError = true;
	                	}
	                	else {
	                		//Á¤ºñ³»¿ª ID ¿À·ù
	                		if(repair_ids.length()!=0) {
	                			repair_idI = Integer.parseInt(repair_ids);
	                			query = "select * from repair where repair_id ="+repair_idI;
	                			rs = stmt.executeQuery(query);
	                			
	                			if(!rs.next()){
	                				System.out.println("[¿À·ù¹ß»ý: Á¤ºñ³»¿ªID] ÇØ´ç Á¤ºñ³»¿ªID¸¦ °¡Áø Á¤ºñ³»¿ªÀº ¾ø½À´Ï´Ù.");
	                				isError = true;
	                			}
	                			
	                			else {
	                				car_idI=rs.getInt(7);
		                			if(repair_infos.length()!=0) {
			                			info = true;
			                		}
			                		
			                		if(repair_extras.length()!=0) {
			                			extra = true;
			                		}
			               		
				                     // ¼ö¸® ³¯Â¥ ¿À·ù
				                  	if(repair_dates.length()!=0) {		                  		
							             st = new StringTokenizer(repair_dates,"-");
							             Y=st.nextToken();
							             if(st.hasMoreTokens()) {
								             M=st.nextToken();
								             if(st.hasMoreTokens()) {
									             D=st.nextToken();
									             y=Integer.parseInt(Y);
									             m=Integer.parseInt(M);
									             d=Integer.parseInt(D);
									             if(m>12 || m <1 || d >31 || d<1) {
									            	 System.out.println("[¿À·ù¹ß»ý : ¼ö¸®³¯Â¥] YYYY-MM-DD¿¡¼­ MMÀº 1~12»çÀÌ, DD´Â 1~31»çÀÌÀÇ ¼ö¿©¾ß ÇÕ´Ï´Ù.");
									        		 isError=true;
									        		 dateError=true;
									             }
								             }
								             else { 
								            	 isError=true;
								            	 dateError=true;
								            	 System.out.println("[¿À·ù¹ß»ý : ¼ö¸®³¯Â¥] YYYY-MM-DD¿¡¼­ MMÀº 1~12»çÀÌ, DD´Â 1~31»çÀÌÀÇ ¼ö¿©¾ß ÇÕ´Ï´Ù.");
								             }
							             }
							             else {
							            	 System.out.println("[¿À·ù¹ß»ý : ¼ö¸®³¯Â¥] YYYY-MM-DD¿¡¼­ MMÀº 1~12»çÀÌ, DD´Â 1~31»çÀÌÀÇ ¼ö¿©¾ß ÇÕ´Ï´Ù.");
							            	 isError=true;
							            	 dateError=true;
							             }
							             if(!dateError) {
							            	 date1=true;
							            	 SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
							            	 String datel="";
							            	
							            	 query = "SELECT * FROM rent WHERE Car_car_id = " + car_idI;
							        			rs = stmt.executeQuery(query);
							        			if(rs.next()) {
							        				rs.last();
							        				datel = fm.format(rs.getDate(5));
							        				
							        			}
							        			
							            	 
							            	 if(repair_dates.compareTo(datel)<0) { //´ë¿©Å×ÀÌºí ´ë¿©ºñ¿ë³³ÀÔ±âÇÑ
					                              System.out.println("[¿À·ù¹ß»ý : ¼ö¸®³¯Â¥] ¼ö¸®³¯Â¥´Â ´ë¿©ºñ¿ë³³ÀÔ±âÇÑ º¸´Ù ³ªÁßÀÌ¾î¾ßÇÕ´Ï´Ù.");
					                              isError = true;
					                              dateError=true;
					                       }
							             } 
							            
				                  	}	 
				                  	
				                  	//³³ÀÔ ±âÇÑ ¿À·ù
				                  	Boolean date1Error=false;
				                	if(repair_paydates.length()!=0) {				             
							             st = new StringTokenizer(repair_paydates,"-");
							             Y=st.nextToken();
							             if(st.hasMoreTokens()) {
								             M=st.nextToken();
								             if(st.hasMoreTokens()) {
									             D=st.nextToken();
									             y=Integer.parseInt(Y);
									             m=Integer.parseInt(M);
									             d=Integer.parseInt(D);
									             if(m>12 || m <1 || d >31 || d<1) {
									            	 System.out.println("[¿À·ù¹ß»ý : ³³ÀÔ±âÇÑ] YYYY-MM-DD¿¡¼­ MMÀº 1~12»çÀÌ, DD´Â 1~31»çÀÌÀÇ ¼ö¿©¾ß ÇÕ´Ï´Ù.");
									        		 isError=true;
									        		 date1Error=true;
									             }
								             }
								             else { 
								            	 isError=true;
								            	 date1Error=true;
								            	 System.out.println("[¿À·ù¹ß»ý : ³³ÀÔ±âÇÑ] YYYY-MM-DD¿¡¼­ MMÀº 1~12»çÀÌ, DD´Â 1~31»çÀÌÀÇ ¼ö¿©¾ß ÇÕ´Ï´Ù.");
								             }
							             }
							             else {
							            	 System.out.println("[¿À·ù¹ß»ý : ³³ÀÔ±âÇÑ] YYYY-MM-DD¿¡¼­ MMÀº 1~12»çÀÌ, DD´Â 1~31»çÀÌÀÇ ¼ö¿©¾ß ÇÕ´Ï´Ù.");
							            	 isError=true;
							            	 date1Error=true;
							             }
							             if(!dateError) {
							            	 date2=true;  	 
							             }
				                	}
				                	
				                	// ¼ö¸® ³¯Â¥, ³³ÀÔ±âÇÑ 
				                	if(!date1 && date2 && !isError) { //date1: ¼ö¸®³¯Â¥, date2: ³³ÀÔ±âÇÑ(Á¤ºñ³»¿ª) ¼ö¸®³¯Â¥°¡ ¾ø°í, ³³ÀÔ±âÇÑ¸¸ ÀÔ·Â¹ÞÀ½
				                		
				                		query = "select repair_date from repair where repair_id ="+repair_idI; //ÀÌ¹Ì ÀÔ·ÂµÈ ¼ö¸®³¯Â¥
							        	 rs=stmt.executeQuery(query);
							        	 
							        	 if(!rs.next()) {
							        		 System.out.println("[¿À·ù¹ß»ý: Á¤ºñ³»¿ªID] ÇØ´ç Á¤ºñ³»¿ªID¸¦ °¡Áø Á¤ºñ³»¿ªÀº ¾ø½À´Ï´Ù.");
							        	 }
							        	 
							        	 else {
							        		 String da=rs.getString(1); 
								        	 
								        	 String Y2, M2, D2;
								             int y2,m2,d2;
								             
								             st = new StringTokenizer(da,"-");
								             Y2=st.nextToken();
								             M2=st.nextToken();
									         D2=st.nextToken();
										     y2=Integer.parseInt(Y2);
										     m2=Integer.parseInt(M2);
										     d2=Integer.parseInt(D2);
							            	 
							            	 
							            	 String Y1, M1, D1;
								             int y1,m1,d1;
								             
								             st = new StringTokenizer(repair_paydates,"-"); //³³ÀÔ±âÇÑ
								             Y1=st.nextToken();
								             M1=st.nextToken();
									         D1=st.nextToken();
										     y1=Integer.parseInt(Y1);
										     m1=Integer.parseInt(M1);
										     d1=Integer.parseInt(D1);
										     
										     if(y1<y2) { //y2 : ¼ö¸®³¯Â¥, y1 : ³³ÀÔ±âÇÑ
										    	 isError=true;
										    	 dateError=true;
										     }
										     else if(y1==y2){
										    	 if(m1<m2) { 
										    		 dateError=true;
										    		 isError=true;
										    	 }
										    	 else if(m2==m1){
										    		 if(d1<d2) { 
										    			 dateError=true;
										    			 isError=true;
										    		 }
										    	 }
										     }
										     if(dateError) {
										    	 System.out.println("[¿À·ù¹ß»ý : ³³ÀÔ ±âÇÑ°ú ¼ö¸®³¯Â¥] ³³ÀÔ ±âÇÑÀº ÀÌ¹Ì µî·ÏµÈ ¼ö¸®³¯Â¥ º¸´Ù ³ªÁßÀÌ¾î¾ßÇÕ´Ï´Ù.");
										     }
							        	 }
							        	
				                	}
							         
				                	//¼ö¸®³¯Â¥, ³³ÀÔ±âÇÑ
				                	if(date1 && !date2 && !isError) { //date1: ¼ö¸®³¯Â¥, date2: ³³ÀÔ±âÇÑ / ¼ö¸®³¯Â¥¸¸ ÀÔ·Â¹ÞÀ½
				                		query = "select repair_paydate from repair where repair_id ="+repair_idI;
							        	 rs=stmt.executeQuery(query);
							        	
							        	 if(!rs.next()) {}
							        	else {
							        		 String da =rs.getString(1); // ÀÌ¹Ì °¡Áö°í ÀÖ´Â ³³ÀÔ±âÇÑ
								        	 
								        	 String Y2, M2, D2;
								             int y2,m2,d2;
								             
								             st = new StringTokenizer(repair_dates,"-"); //¼ö¸®³¯Â¥
								             Y2=st.nextToken();
								             M2=st.nextToken();
									         D2=st.nextToken();
										     y2=Integer.parseInt(Y2);
										     m2=Integer.parseInt(M2);
										     d2=Integer.parseInt(D2);
							            	 
							            	 
							            	 String Y1, M1, D1;
								             int y1,m1,d1;
								             
								             st = new StringTokenizer(da,"-"); //³³ÀÔ±âÇÑ
								             Y1=st.nextToken();
								             M1=st.nextToken();
									         D1=st.nextToken();
										     y1=Integer.parseInt(Y1);
										     m1=Integer.parseInt(M1);
										     d1=Integer.parseInt(D1);
										     
										     if(y1<y2) { //y1: ³³ÀÔ±âÇÑ, y2: ¼ö¸®³¯Â¥
										    	 isError=true;
										    	 dateError=true;
										     }
										     else if(y1==y2){
										    	 if(m1<m2) { 
										    		 dateError=true;
										    		 isError=true;
										    	 }
										    	 else if(m2==m1){
										    		 if(d1<d2) { 
										    			 dateError=true;
										    			 isError=true;
										    		 }
										    	 }
										     }
										     if(dateError) {
										    	 System.out.println("[¿À·ù¹ß»ý : ¼ö¸® ³¯Â¥¿Í ³³ÀÔ ±âÇÑ] ÀÌ¹Ì µî·ÏµÈ ³³ÀÔ±âÇÑÀÌ ¼ö¸®³¯Â¥ º¸´Ù ³ªÁßÀÌ¾î¾ßÇÕ´Ï´Ù.");
										     }
							        	 }
									 }
				               
				                	
				                	if(date1 && date2 && !isError) { //date1: ¼ö¸®³¯Â¥, date2: ³³ÀÔ±âÇÑ
							        	 String Y2, M2, D2;
							             int y2,m2,d2;
							             
							             st = new StringTokenizer(repair_dates,"-"); 
							             Y2=st.nextToken();
							             M2=st.nextToken();
								         D2=st.nextToken();
									     y2=Integer.parseInt(Y2);
									     m2=Integer.parseInt(M2);
									     d2=Integer.parseInt(D2);
						            	 
						            	 
						            	 String Y1, M1, D1;
							             int y1,m1,d1;
							             
							             st = new StringTokenizer(repair_paydates,"-");
							             Y1=st.nextToken();
							             M1=st.nextToken();
								         D1=st.nextToken();
									     y1=Integer.parseInt(Y1);
									     m1=Integer.parseInt(M1);
									     d1=Integer.parseInt(D1);
									     
									     if(y1<y2) { //y1 : ³³ÀÔ±âÇÑ, y2: ¼ö¸®³¯Â¥
									    	 isError=true;
									    	 dateError=true;
									     }
									     else if(y1==y2){
									    	 if(m1<m2) { 
									    		 dateError=true;
									    		 isError=true;
									    	 }
									    	 else if(m1==m2){
									    		 if(d1<d2) { 
									    			 dateError=true;
									    			 isError=true;
									    		 }
									    	 }
									     }
									     if(dateError) {
									    	 System.out.println("[¿À·ù¹ß»ý : ³³ÀÔ±âÇÑ°ú ¼ö¸®³¯Â¥] ³³ÀÔ±âÇÑÀº ¼ö¸®³¯Â¥ º¸´Ù ³ªÁßÀÌ¾î¾ßÇÕ´Ï´Ù.");
									     }
				                	 }
				                	
				                	
				                	// ¼ö¸® ºñ¿ë
				                	if(repair_prices.length()!=0) {
				                		if(!CheckNumber(repair_prices)) {
				                			System.out.println("[¿À·ù ¹ß»ý : ¼ö¸® ºñ¿ë] ¼ö¸®ºñ¿ëÀº ¾çÀÇ Á¤¼ö·Î ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
				                			isError = true;
				                		}
				                	
				                	}
	                			}
	                		
	                		}
	                
	                		
	                	}
	                	
	                	if(!isError) {
	               
	                		if(date1) { //¼ö¸®³¯Â¥
	                			query = "UPDATE repair SET repair_date='"+repair_dates+"' WHERE repair_id=" + repair_idI;
				        		stmt.executeUpdate(query);
	                		}
		                	if(date2) { //³³ÀÔ±âÇÑ
		                		query = "UPDATE repair SET repair_paydate='"+repair_paydates+"' WHERE repair_id=" + repair_idI;
				        		stmt.executeUpdate(query);
		                	}
		                	if(price) { //¼ö¸®ºñ¿ë
		                		query = "UPDATE repair SET repair_price="+repair_priceI+" WHERE repair_id=" + repair_idI;
				        		stmt.executeUpdate(query);
		                	}
		                	if(info) { //Á¤ºñ³»¿ª
		                		query = "UPDATE repair SET repair_info='"+repair_infos+"' WHERE repair_id=" + repair_idI;
				        		stmt.executeUpdate(query);
		                	}
		                	if(extra) { //±âÅ¸Á¤ºñ³»¿ª
		                		query = "UPDATE repair SET repair_extra='"+repair_extras+"' WHERE repair_id=" + repair_idI;
				        		stmt.executeUpdate(query);
		                	}
		                	
		                	System.out.println("ÇØ´ç IDÀÇ Á¤ºñ³»¿ª º¯°æÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
			        		query="SELECT * FROM repair WHERE repair_id >=" + 0;
		                    rs = stmt.executeQuery(query);
	
		                    
		                    System.out.println("Á¤ºñ³»¿ª ID \t Á¤ºñ ³»¿ª ----»ý·«----");
	
		                    while(rs.next()) {
		                       System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t ³ª¸ÓÁö´Â »ý·« -----");
		                       
		                    }
	                	}
	                }
	                   
	                 
	             // °Ë»ö1 °í°´Á¤º¸
	                else if(e.getSource() == s1b) {
	                	String s1_con1s = s1_con1f.getText(); //´ë¿©±â°£
	                	String s1_con2s = s1_con2f.getText(); //Â÷·® ½ÂÂ÷ ÀÎ¿ø¼ö
	                	boolean isError=false;
	                	
	                	System.out.println("°Ë»ö1¹öÆ° Å¬¸¯");
	                	
	                	if(s1_con1s.length()==0 || s1_con2s.length()==0) {
	                		System.out.println("[¿À·ù¹ß»ý] °Ë»ö1 Á¶°ÇÀ» ¸ðµÎ Àû¾î¾ß ÇÕ´Ï´Ù.");
	                		isError = true;
	                	}
	                	else {
	                		if(!CheckNumber(s1_con1s) || !CheckNumber(s1_con2s)) {
	                			System.out.println("[¿À·ù¹ß»ý] ´ë¿©±â°£°ú Â÷·® ½ÂÂ÷ ÀÎ¿ø¼ö´Â ¼ýÀÚ·Î Àû¾î¾ß ÇÕ´Ï´Ù.");
	                			isError = true;	
	                		}
	                		else {
	                			int s1_con1I =  Integer.parseInt(s1_con1s);
	                			int s1_con2I =  Integer.parseInt(s1_con2s);
	                			
	                			if(s1_con1I ==0) {
	                				System.out.println("[¿À·ù¹ß»ý] ´ë¿©±â°£Àº 0º¸´Ù Ä¿¾ß ÇÕ´Ï´Ù.");
	                				isError = true;
	                			}
	                			
	                			if(s1_con2I==0) {
	                				System.out.println("[¿À·ù¹ß»ý] ½ÂÂ÷ÀÎ¿ø¼ö´Â 0º¸´Ù Ä¿¾ß ÇÕ´Ï´Ù.");
	                				isError = true;
	                			}
	                			
	                			query = "select Customer_cust_id from rent where rent_period >= "+s1_con1I;
	                			rs = stmt.executeQuery(query);
	                			if(!rs.next()) {
	                				System.out.println("[´ë¿©±â°£] ÇØ´ç Á¶°Ç¿¡ ¸Â´Â °í°´ÀÌ ¾ø½À´Ï´Ù.");
	                				isError = true;
	                			}
	                			
	                			query = "select car_id from car where car_board >= "+s1_con2I;
	                			rs = stmt.executeQuery(query);
	                			if(!rs.next()) {
	                				System.out.println("[Â÷·® ½ÂÂ÷ÀÎ¿ø¼ö] ÇØ´ç Á¶°Ç¿¡ ¸Â´Â °í°´ÀÌ ¾ø½À´Ï´Ù.");
	                				isError = true;
	                			}
	                			
	                		}
	                	}
	                	
	                	
	                	if(!isError) {
	                		System.out.println("°Ë»ö1ÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
	                		
	                		int s1_con1I =  Integer.parseInt(s1_con1s);
                			int s1_con2I =  Integer.parseInt(s1_con2s);
	                		
                			query = "select *\r\n" + 
                					"from customer\r\n" + 
                					"where cust_id IN(select Customer_cust_id\r\n" + 
                					"				from rent\r\n" + 
                					"				where rent_period >= "+s1_con1I+" And\r\n" + 
                					"                     			         Car_car_id IN(select car_id\r\n" + 
                					"						from car\r\n" + 
                					"						where car_board >="+s1_con2I+"))";
                			
                			
                			 rs = stmt.executeQuery(query);
                			 
    	                     result.setText("");
    	                     result.setText("¿îÀü¸éÇãÁõ¹øÈ£\t °í°´¸í \t°í°´ ÁÖ¼Ò \t°í°´ ÀüÈ­¹øÈ£ \t°í°´ ÀÌ¸ÞÀÏ  \n");
    	                     
    	                     while(rs.next()) {                    
    	                        String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + 
    	                              rs.getString(4) +  "\t" + rs.getString(5) +"\n";
    	                        result.append(str);
    	                     }
	                	}
	                }
	                
	                //°Ë»ö2 Ä·ÇÎÄ«Á¤º¸
	                else if(e.getSource() == s2b) {
	                	String s2_con1s = s2_con1f.getText(); //¼ö¸®¿©ºÎÆÇ´Ü
	                	String s2_con2s = s2_con2f.getText(); //´ë¿©±â°£
	                	boolean isError=false;
	                	
	                	System.out.println("°Ë»ö2¹öÆ° Å¬¸¯");
	                	
	                	if(s2_con1s.length()==0 || s2_con2s.length()==0) {
	                		System.out.println("[¿À·ù¹ß»ý] °Ë»ö2 Á¶°ÇÀ» ¸ðµÎ Àû¾î¾ß ÇÕ´Ï´Ù.");
	                		isError = true;
	                	}
	                	else {
	                		if(!CheckNumber(s2_con1s) || !CheckNumber(s2_con2s)) {
	                			System.out.println("[¿À·ù¹ß»ý] ¼ö¸®¿©ºÎÆÇ´Ü°ú ´ë¿©±â°£Àº ¼ýÀÚ·Î Àû¾î¾ß ÇÕ´Ï´Ù.");
	                			isError = true;
	                		}
	                		else {
	                			
	                			int s2_con1I =  Integer.parseInt(s2_con1s); //¼ö¸®¿©ºÎÆÇ´Ü
	                			
	                			Boolean b;
	                			if(s2_con1I==1) b= true;
	                			else b=false;
	                			
	                			int s2_con2I =  Integer.parseInt(s2_con2s); //´ë¿©±â°£
	                			if(s2_con2I>0) {
	                				query = "select Rent_rent_id from inspection where repairinfo = "+b;
		                			rs = stmt.executeQuery(query);
		                			if(!rs.next()) {
		                				System.out.println("[¼ö¸®¿©ºÎ] ÇØ´ç Á¶°Ç¿¡ ¸Â´Â Ä·ÇÎÄ«°¡ ¾ø½À´Ï´Ù.");
		                				isError = true;
		                			}
		                			
		                			query = "select Car_car_id from rent where rent_period >= "+s2_con2I;
		                			rs = stmt.executeQuery(query);
		                			if(!rs.next()) {
		                				System.out.println("[´ë¿©±â°£] ÇØ´ç Á¶°Ç¿¡ ¸Â´Â Ä·ÇÎÄ«°¡ ¾ø½À´Ï´Ù.");
		                				isError = true;
		                			}
	                			}
	                			else {
	                				System.out.println("[¿À·ù¹ß»ý] ´ë¿©±â°£Àº 0º¸´Ù Å« ¼ýÀÚ¿©¾ß ÇÕ´Ï´Ù.");
	                				isError = true;
	                			}
	                	
	                			
	                		}
	                	}
	                	
	                	
	                	if(!isError) {
	                		System.out.println("°Ë»ö2°¡ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
	                		
	                		int s2_con1I =  Integer.parseInt(s2_con1s);
                			int s2_con2I =  Integer.parseInt(s2_con2s);
                			
                			Boolean b;
                			if(s2_con1I==1) b= true;
                			else b=false;
	                		
                			query = "select *\r\n" + 
                					"from car\r\n" + 
                					"where car_id IN(select Car_car_id\r\n" + 
                					"			 from rent\r\n" + 
                					"			 where rent_period >= "+s2_con2I+" And\r\n" + 
                					"		                      rent_id In(select Rent_rent_id\r\n" + 
                					"                                                             from   inspection\r\n" + 
                					"		                                    where  repairinfo = "+b+"))";
                			
                			 rs = stmt.executeQuery(query);
                			 
                	         result.setText("");
    	                     result.setText("´ë¿©°¡´É¿©ºÎ \t Ä·ÇÎÄ«ID \tÂ÷ÀÌ¸§ \tÂ÷·®¹øÈ£ \t½ÂÂ÷ÀÎ¿ø¼ö  \tÁ¦Á¶È¸»ç \tÁ¦Á¶¿¬µµ \t´©ÀûÁÖÇà°Å¸® \t´ë¿©ºñ¿ë \tµî·ÏÀÏÀÚ \t´ë¿©È¸»çID \n");
    	                     
    	                     while(rs.next()) {                    
    	                        String str = carBoolean[rs.getInt(1)] + "\t" + rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + 
    	                              rs.getInt(4) +  "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" +
    	                              rs.getInt(7) + "\t" + rs.getInt(8)+"\t" + rs.getDate(9)+"\t" + rs.getInt(10)+"\n";
    	                        result.append(str);
    	                       
    	                     }
	                	}
	                }
	                
	                //°Ë»ö3 Ä·ÇÎÄ«Á¤º¸
	                else if(e.getSource() == s3b) {
	                	String s3_con1s = s3_con1f.getText(); //´©ÀûÁÖÇà°Å¸®
	                	String s3_con2s = s3_con2f.getText(); //´ë¿©±â°£
	                	boolean isError=false;
	                	
	                	System.out.println("°Ë»ö3¹öÆ° Å¬¸¯");
	                	
	                	if(s3_con1s.length()==0 || s3_con2s.length()==0) {
	                		System.out.println("[¿À·ù¹ß»ý] °Ë»ö3 Á¶°ÇÀ» ¸ðµÎ Àû¾î¾ß ÇÕ´Ï´Ù.");
	                		isError = true;
	                	}
	                	else {
	                		if(!CheckNumber(s3_con1s) || !CheckNumber(s3_con2s)) {
	                			System.out.println("[¿À·ù¹ß»ý] ´©ÀûÁÖÇà°Å¸®¿Í ´ë¿©±â°£Àº ¼ýÀÚ·Î Àû¾î¾ß ÇÕ´Ï´Ù.");
	                			isError = true;
	                		}
	                		else {
	                			int s3_con1I =  Integer.parseInt(s3_con1s); //´©ÀûÁÖÇà°Å¸®
	                			int s3_con2I =  Integer.parseInt(s3_con2s); //´ë¿©±â°£
	                			
	                			if(s3_con2I>0) {
	                				query = "select * from car where car_distance >="+s3_con1I;
		                			rs = stmt.executeQuery(query);
		                			if(!rs.next()) {
		                				System.out.println("[¿À·ù¹ß»ý : ´©ÀûÁÖÇà°Å¸®] ÇØ´ç Á¶°Ç¿¡ ¸Â´Â °í°´ÀÌ ¾ø½À´Ï´Ù.");
		                				isError = true;
		                			}
		                			
		                			query = "select Car_car_id from rent where rent_period >= "+s3_con2I;
		                			rs = stmt.executeQuery(query);
		                			if(!rs.next()) {
		                				System.out.println("[¿À·ù¹ß»ý : ´ë¿©±â°£] ÇØ´ç Á¶°Ç¿¡ ¸Â´Â °í°´ÀÌ ¾ø½À´Ï´Ù.");
		                				isError = true;
		                			}
	                			}
	                			else {
	                				System.out.println("[¿À·ù¹ß»ý : ´ë¿©±â°£] ´ë¿©±â°£Àº 0º¸´Ù Å« ¼ýÀÚ·Î ÇØ¾ßÇÕ´Ï´Ù.");
	                				isError = true;
	                			}
	                		
	                		}
	                	}
	                	
	                	
	                	if(!isError) {
	                		System.out.println("°Ë»ö3ÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
	                		
	                		int s3_con1I =  Integer.parseInt(s3_con1s);
                			int s3_con2I =  Integer.parseInt(s3_con2s);
	                		
                			query = "select *\r\n" + 
                					"from car\r\n" + 
                					"where car_distance >="+s3_con1I+" And\r\n" + 
                					"      car_id IN(select Car_car_id\r\n" + 
                					"                 from rent\r\n" + 
                					"	    where rent_period >= "+s3_con2I+")";
                			
                			 rs = stmt.executeQuery(query);
                			 
                	         result.setText("");
    	                     result.setText("´ë¿©°¡´É¿©ºÎ \t Ä·ÇÎÄ«ID \tÂ÷ÀÌ¸§ \tÂ÷·®¹øÈ£ \t½ÂÂ÷ÀÎ¿ø¼ö  \tÁ¦Á¶È¸»ç \tÁ¦Á¶¿¬µµ \t´©ÀûÁÖÇà°Å¸® \t´ë¿©ºñ¿ë \tµî·ÏÀÏÀÚ \t´ë¿©È¸»çID \n");
    	                     
    	                     while(rs.next()) {                    
    	                        String str = carBoolean[rs.getInt(1)] + "\t" + rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + 
    	                              rs.getInt(4) +  "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" +
    	                              rs.getInt(7) + "\t" + rs.getInt(8)+"\t" + rs.getDate(9)+"\t" + rs.getInt(10)+"\n";
    	                        result.append(str);
    	                       
    	                     }
	                	}
	                }
	                
	                // °Ë»ö4 °í°´Á¤º¸
	                else if(e.getSource() == s4b) {
	                	String s4_con1s = s4_con1f.getText(); //°í°´ÀÇ ¼º 
	                	String s4_con2s = s4_con2f.getText(); //¼ö¸®ºñ¿ë
	                	boolean isError=false;
	                	
	                	System.out.println("°Ë»ö4¹öÆ° Å¬¸¯");
	                	
	                	if(s4_con1s.length()==0 || s4_con2s.length()==0) {
	                		System.out.println("[¿À·ù¹ß»ý] °Ë»ö4 Á¶°ÇÀ» ¸ðµÎ Àû¾î¾ß ÇÕ´Ï´Ù.");
	                		isError = true;
	                	}
	                	else {
	                		if( CheckNumber(s4_con1s) || !CheckNumber(s4_con2s)) {
	                			System.out.println("[¿À·ù¹ß»ý] °í°´ÀÇ ¼ºÀº ¹®ÀÚ·Î ¼ö¸®ºñ¿ëÀº ¼ýÀÚ·Î Àû¾î¾ß ÇÕ´Ï´Ù.");
	                			isError = true;
	                		}
	                		else {
	                			int s4_con2I =  Integer.parseInt(s4_con2s); //¼ö¸®ºñ¿ë
	                			
	                			if(s4_con2I>0) {
	                				query = "select * from customer where cust_name Like'"+s4_con1s+"%'";
	    	                		
		                			rs = stmt.executeQuery(query);
		                			if(!rs.next()) {
		                				System.out.println("[°í°´ÀÇ ¼º] ÇØ´ç Á¶°Ç¿¡ ¸Â´Â °í°´ÀÌ ¾ø½À´Ï´Ù.");
		                				isError = true;
		                			}
		                			
		                			query = "select * from repair where repair_price >= "+s4_con2I;
		                			rs = stmt.executeQuery(query);
		                			if(!rs.next()) {
		                				System.out.println("[¼ö¸®ºñ¿ë] ÇØ´ç Á¶°Ç¿¡ ¸Â´Â °í°´ÀÌ ¾ø½À´Ï´Ù.");
		                				isError = true;
		                			}
	                			}
	                			else {
	                				System.out.println("[¿À·ù¹ß»ý] ¼ö¸®ºñ¿ëÀº 0º¸´Ù Å« ¼ýÀÚ·Î ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
	                				isError = true;
	                			}
	                			
	                			
	                		}
	                	}
	                	
	                	
	                	if(!isError) {
	                		System.out.println("°Ë»ö4°¡ ¿Ï·áµÇ¾ú½À´Ï´Ù."); 	
                			int s4_con2I =  Integer.parseInt(s4_con2s);
	                		
                			query ="select * from customer where cust_name like '"+s4_con1s+"%' And "
                                    + "cust_id IN(select Customer_cust_id from repair where repair_price >="+s4_con2I+")";

                			
                			 rs = stmt.executeQuery(query);
                			 
    	                     result.setText("");
    	                     result.setText("¿îÀü¸éÇãÁõ¹øÈ£\t °í°´¸í \t°í°´ ÁÖ¼Ò \t°í°´ ÀüÈ­¹øÈ£ \t°í°´ ÀÌ¸ÞÀÏ  \n");
    	                     
    	                     while(rs.next()) {                    
    	                        String str = rs.getString(1) + "\t" + rs.getString(2)  + "\t" + rs.getString(3) + "\t" + 
    	                              rs.getString(4) +  "\t" + rs.getString(5) +"\n";
    	                        result.append(str);
    	                     }
	                	}
	                }
	                  
	              
	            } catch (Exception e2) {
	                System.out.println("Äõ¸® ÀÐ±â ½ÇÆÐ :" + e2);
	            }  
	         
	      }
	   }

   class Upanel extends JPanel implements ActionListener{
	      
	      JLabel user = new JLabel("ÀÏ¹Ý»ç¿ëÀÚ");
	      JTextArea resultArea = new JTextArea("");
	      JLabel b_num = new JLabel("½ÂÂ÷ ÀÎ¿ø¼ö");
	      JTextField b_n = new JTextField(3);
	      JLabel b_label = new JLabel("¸í ÀÌ»ó");
	      JButton searchB = new JButton("°Ë»ö");
	      
	      JButton ableCarB = new JButton("´ë¿©°¡´ÉÇÑ Ä·ÇÎÄ« º¸±â");
	      
	      JLabel car_idl = new JLabel("Ä·ÇÎÄ« ID");
	      JTextField car_idf = new JTextField(3);
	      JLabel comp_idl = new JLabel("Ä·ÇÎÄ«´ë¿©È¸»ç ID");
	      JTextField comp_idf = new JTextField(3);
	      JLabel cust_idl = new JLabel("¿îÀü¸éÇã¹øÈ£");
	      JTextField cust_idf = new JTextField(12);
	      JLabel start_datel = new JLabel("´ë¿©½ÃÀÛÀÏ");
	      JTextField start_datef = new JTextField(9);
	      JLabel periodl = new JLabel("´ë¿©±â°£");
	      JTextField periodf = new JTextField(3);
	      
	      JLabel pricel = new JLabel("Ã»±¸¿ä±Ý");
	      JTextField pricef = new JTextField(6);
	      JLabel paydatel = new JLabel("³³ÀÔ±âÇÑ");
	      JTextField paydatef = new JTextField(7);
	      JLabel extral = new JLabel("±âÅ¸Ã»±¸³»¿ª");
	      JTextField extraf = new JTextField(7);
	      JLabel extrafeel = new JLabel("±âÅ¸Ã»±¸¿ä±ÝÁ¤º¸");
	      JTextField extrafeef = new JTextField(5);
	      
	      JButton rentB = new JButton("´ë¿©");
	      
	      JPanel northUP = new JPanel();
	      JPanel centerUP = new JPanel();
	      //JPanel center2UP = new JPanel();
	      JPanel southUP = new JPanel();
	    
	     
	      public Upanel() {
	         northUP.setBackground(new Color(252,231,231));
	         centerUP.setBackground(new Color(252,231,231));
	         southUP.setBackground(new Color(252,231,231));
	         
	         
	         resultArea.setEditable(false);
	         JScrollPane scrollPane = new JScrollPane(resultArea);
	        
	         
	         user.setPreferredSize(new Dimension(350,20));
	         
	         searchB.addActionListener(this);
	         rentB.addActionListener(this);
	         ableCarB.addActionListener(this);
	         
	         northUP.add(user);
	         northUP.add(b_num);
	         northUP.add(b_n);
	         northUP.add(b_label);
	         northUP.add(searchB);
	         northUP.add(ableCarB);
	         
	         
	         southUP.add(car_idl);
	         southUP.add(car_idf);
	         southUP.add(comp_idl);
	         southUP.add(comp_idf);
	        
	         southUP.add(cust_idl);
	         southUP.add(cust_idf);
	         southUP.add(start_datel);
	         southUP.add(start_datef);
	         southUP.add(periodl);
	         southUP.add(periodf);
	         
	         southUP.add(pricel);
	         southUP.add(pricef);
	         southUP.add(paydatel);
	         southUP.add(paydatef);
	         southUP.add(extral);
	         southUP.add(extraf);
	         southUP.add(extrafeel);
	         southUP.add(extrafeef);
	         
	         
	         southUP.add(rentB);
	         
	         setLayout(new BorderLayout(0,0));
	         add(northUP,BorderLayout.NORTH);
	         northUP.setPreferredSize(new Dimension(375,100));
	         add(scrollPane,BorderLayout.CENTER);
	         
	         add(southUP,BorderLayout.SOUTH);
	         southUP.setPreferredSize(new Dimension(375,300));

	    
	      }
	      
	   
	      public Boolean isNum(String str) {
	          char tmp;
	          boolean output = true;
	          for(int i=0; i<str.length(); i++) {
	             tmp = str.charAt(i);
	             if(Character.isDigit(tmp)==false)
	                output=false;
	          }
	          return output;
	      }
	      
	      
	      @Override
	      public void actionPerformed(ActionEvent e) {
	         String query;
	         try {
	               
	             stmt = con.createStatement();
	              
	               if(e.getSource()==searchB) {
	                  System.out.println("°Ë»ö!");
	                  String b_numS = b_n.getText();
	                  
	                  if(b_numS.length()==0) {
	                	  System.out.println("[¿À·ù¹ß»ý: ½ÂÂ÷ÀÎ¿ø¼ö] ½ÂÂ÷ÀÎ¿ø¼ö¿¡ ¼ýÀÚ·Î ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
	                  }
	                  else {
	                	   // ¼ýÀÚ·Î¸¸ ÀÔ·ÂÇØ¾ßÇÔ
		                  if(!isNum(b_numS)) {
		                    System.out.println("[¿À·ù¹ß»ý: ½ÂÂ÷ÀÎ¿ø¼ö] ½ÂÂ÷ÀÎ¿ø¼ö¸¦ ¼ýÀÚ·Î¸¸ ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù");
		                  }
		                  
		                  else {
		                     int b_numI = Integer.parseInt(b_numS);
		                     
		                     if(b_numI>0) {
		                    	   query="SELECT * FROM Car WHERE car_board >=" + b_numI;
				                     rs = stmt.executeQuery(query);

				                     resultArea.setText("");
				                     resultArea.setText("´ë¿©°¡´É¿©ºÎ \t Ä·ÇÎÄ«ID \tÂ÷ÀÌ¸§ \tÂ÷·®¹øÈ£ \t½ÂÂ÷ÀÎ¿ø¼ö  \tÁ¦Á¶È¸»ç \tÁ¦Á¶¿¬µµ \t´©ÀûÁÖÇà°Å¸® \t´ë¿©ºñ¿ë \tµî·ÏÀÏÀÚ \t´ë¿©È¸»çID \n");
				                     
				                     int i=0;
				                     while(rs.next()) {                    
				                        String str = carBoolean[rs.getInt(1)] + "\t" + rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + 
				                              rs.getInt(4) +  "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" +
				                              rs.getInt(7) + "\t" + rs.getInt(8)+"\t" + rs.getDate(9)+"\t" + rs.getInt(10)+"\n";
				                        resultArea.append(str);
				                        
				                        i++;
				                     }
		                     }
		                     else {
		                    	 System.out.println("[¿À·ù¹ß»ý : ½ÂÂ÷ÀÎ¿ø¼ö] ½ÂÂ÷ÀÎ¿ø¼ö¸¦ 0º¸´Ù Å« ¼ýÀÚ·Î ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
		                     }
		               
		                    
		                  }
	                  }
	               
	               }
	               else if(e.getSource()==ableCarB) {
	            	   System.out.println("´ë¿©°¡´ÉÇÑ Ä·ÇÎÄ«º¸±â!");
	                    
		                  query="SELECT * FROM Car";
		                  rs = stmt.executeQuery(query);
		                  resultArea.setText("");
		                  resultArea.setText("Ä·ÇÎÄ«ID \tÂ÷ÀÌ¸§ \tÂ÷·®¹øÈ£ \t½ÂÂ÷ÀÎ¿ø¼ö  \tÁ¦Á¶È¸»ç \tÁ¦Á¶¿¬µµ \t´©ÀûÁÖÇà°Å¸® \t´ë¿©ºñ¿ë \tµî·ÏÀÏÀÚ \t´ë¿©È¸»çID \n");
		                  int i=0;
		                  while(rs.next()) {                    
		                        String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + 
		                              rs.getInt(4) +  "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" +
		                              rs.getInt(7) + "\t" + rs.getInt(8)+"\t" + rs.getDate(9)+"\t" + rs.getInt(10)+"\n";
		                        if(carBoolean[rs.getInt(1)]==true) resultArea.append(str);
		                        
		                        i++;
		                  }
		                    
		                 
	               }
	               else if(e.getSource()==rentB) {
	                  Boolean isError = false;
	                  Boolean carError=false;
	                  
	                  
	                  System.out.println("´ë¿©¹öÆ° Å¬¸¯!");
	                  
	                  String car_idS = car_idf.getText();
	                  String comp_idS = comp_idf.getText();
	                  String cust_idS = cust_idf.getText();
	                  String car_start_dateS = start_datef.getText();
	                  String car_periodS = periodf.getText();
	                  String priceS=pricef.getText();
	                  String pay_dateS = paydatef.getText();
	                  String extraS = extraf.getText();
	                  String extrafeeS = extrafeef.getText();
	                  
	                  
	                  if(car_idS.length()==0 || comp_idS.length()==0 || cust_idS.length()==0 || car_start_dateS.length()==0 ||
	                		  car_periodS.length()==0 || pay_dateS.length()==0 || extraS.length()==0 || extrafeeS.length()==0) {
	                	  isError = true;
	                	  System.out.println("[¿À·ù¹ß»ý : µ¥ÀÌÅÍ ÀÔ·Â ¾øÀ½] ¸ðµç Ä­¿¡ µ¥ÀÌÅÍ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
	                  }
	                  else {
	                	  
	                	//´ë¿©±â°£ ¿À·ù È®ÀÎ
	                	 if(CheckNumber(car_periodS)) {
	                		 int car_periodI = Integer.parseInt(car_periodS);
	                		 if(car_periodI>0) {}
		                     else {
		                    	 System.out.println("[¿À·ù¹ß»ý : ´ë¿©±â°£] ´ë¿©±â°£Àº 0º¸´Ù Å« ¼ýÀÚ·Î ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
		                    	 isError = true;
		                     }
	                	 }
	                	 else {
	                		 System.out.println("[¿À·ù¹ß»ý : ´ë¿©±â°£] ´ë¿©±â°£Àº 0º¸´Ù Å« ¼ýÀÚ·Î ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
	                		 isError = true;
	                	 }
	                     
	                     	                     
	                     // Ä·ÇÎÄ« ID ¿À·ù È®ÀÎ
	                     if(CheckNumber(car_idS)) {
	                    	 int car_idI = Integer.parseInt(car_idS);
		                     query = "SELECT * FROM car WHERE car_id =" + car_idI;
		                     rs = stmt.executeQuery(query);
		                     if(!rs.next()) { 
		                        System.out.println("[¿À·ù¹ß»ý : Ä·ÇÎÄ« ID] ÇØ´ç IDÀÇ Ä·ÇÎÄ«°¡ ¾ø½À´Ï´Ù.");
		                        isError=true;
		                        carError=true;
		                     }
		                     else {
		                    	 
		                    	 int i = rs.getInt(1);
		                    	 if(carBoolean[i]==false) {
		                    		 System.out.println("[¿À·ù¹ß»ý : ´ë¿©ºÒ°¡´É Ä·ÇÎÄ«] ÇØ´ç IDÀÇ Ä·ÇÎÄ«´Â ÇöÀç ´ë¿© ºÒ°¡´ÉÀÔ´Ï´Ù.");
		 	                        isError=true;
		 	                        carError=true;
		                    	 }
		                     }
	                     }
	                     else {
	                    	 isError=true;
	                    	 carError=true;
	                    	 System.out.println("[¿À·ù¹ß»ý : Ä·ÇÎÄ« ID] Ä·ÇÎÄ« ID´Â ¾çÀÇ Á¤¼ö¿©¾ß ÇÕ´Ï´Ù.");
	                     }
	                     
	                     // Ä·ÇÎÄ«´ë¿©È¸»ç ID ¿À·ù È®ÀÎ
	                     if(CheckNumber(comp_idS)) {
	                    	 int car_idI = Integer.parseInt(car_idS);
	                    	 if(!carError) {
			                     query = "SELECT * FROM car WHERE car_id =" + car_idI;
			                     rs = stmt.executeQuery(query);
			                     if(rs.next()) {
			                    	 int i = rs.getInt(10); 
			                    	 if(Integer.parseInt(comp_idS)!=i) {
			                    		 isError=true;
			                   		 System.out.println("[¿À·ù¹ß»ý : È¸»ç ID] Ä·ÇÎÄ« ID¿Í È¸»ç ID°¡ ¼­·Î ¸ÂÁö¾Ê½À´Ï´Ù.");
			                    	 }
			                     }
			                   	
			                    	 
			                 }
	                    	 
	                     }
	                     else {
	                    	 System.out.println("[¿À·ù¹ß»ý : È¸»ç ID] È¸»ç ID´Â ¾çÀÇ Á¤¼ö¿©¾ß ÇÕ´Ï´Ù.");
	                     }

	                     // ¿îÀü¸éÇã¹øÈ£ ¿À·ù È®ÀÎ
	                     if(cust_idS.length()!=0) {
	                     query = "SELECT * FROM customer WHERE cust_id = '" + cust_idS+"'";
	                     rs = stmt.executeQuery(query);
	                     if(!rs.next()) { 
	                        System.out.println("[¿À·ù¹ß»ý : ¿îÀü¸éÇã¹øÈ£] ÇØ´ç ¿îÀü¸éÇã¹øÈ£ÀÇ °í°´ÀÌ ¾ø½À´Ï´Ù.");
	                        isError=true;
	                     }
	                     


	                     // Ä·ÇÎÄ« ´ë¿©½ÃÀÛÀÏ ³¯Â¥ ¿À·ù
	                     String Y, M, D;
	                     int y,m,d;

	                     StringTokenizer st = new StringTokenizer(car_start_dateS,"-");
	                     Y=st.nextToken();
	                     M=st.nextToken();
	                     D=st.nextToken();

	                     y=Integer.parseInt(Y);
	                     m=Integer.parseInt(M);
	                     d=Integer.parseInt(D);

	                     if(m>12 || m <1 || d >31 || d<1) {
	                        System.out.println("[¿À·ù¹ß»ý : ´ë¿©½ÃÀÛÀÏ ¿À·ù] ´ë¿©½ÃÀÛÀÏ  YYYY-MM-DD¿¡¼­ MMÀº 1~12»çÀÌ, DD´Â 1~31»çÀÌÀÇ ¼ö¿©¾ß ÇÕ´Ï´Ù.");
	                        isError=true;
	                     }   
	                     else {
	                       
	                        // Ä·ÇÎÄ« µî·ÏÀÏÀÚ, Ä·ÇÎÄ« ´ë¿©½ÃÀÛÀÏ ºñ±³ 
	                        query = "SELECT car_date FROM car where car_id = '" + car_idS +"'"; //µî·ÏÀÏÀÚ
	                        rs = stmt.executeQuery(query);
	                        if(rs.next()) {
	                           String str = rs.getString(1);
	                           if(str.compareTo(car_start_dateS)>0) {
	                              System.out.println("[¿À·ù¹ß»ý : Ä·ÇÎÄ« µî·ÏÀÏÀÚº¸´Ù ºü¸§] ÇØ´ç Ä·ÇÎÄ«´Â ÇöÀç µî·ÏµÇ¾î ÀÖÁö ¾Ê½À´Ï´Ù.");
	                              isError = true;
	                           }
	                        }
	                     }
	                 
	                  }
	                     
	                   // °¡°Ý ¿À·ù È®ÀÎ
	                   if(CheckNumber(priceS)) {
	                	   int price = Integer.parseInt(priceS);
	                	   if(price<=0) {
	                		   System.out.println("[¿À·ù¹ß»ý : Ã»±¸¿ä±Ý] Ã»±¸¿ä±ÝÀº ¾çÀÇ Á¤¼öÀÌ¾î¾ßÇÕ´Ï´Ù.");
	                           isError = true;
	                	   }
	                   }
	                   else {
	                	   System.out.println("[¿À·ù¹ß»ý : Ã»±¸¿ä±Ý] Ã»±¸¿ä±ÝÀº ¾çÀÇ Á¤¼öÀÌ¾î¾ßÇÕ´Ï´Ù.");
                           isError = true;
	                   }
	                   
	                   // ±âÅ¸Ã»±¸¿ä±Ý ¿À·ù È®ÀÎ
	                   if(CheckNumber(extrafeeS)) {
	                	   int price = Integer.parseInt(extrafeeS);
	                	   if(price<=0) {
	                		   System.out.println("[¿À·ù¹ß»ý : ±âÅ¸Ã»±¸¿ä±Ý] ±âÅ¸Ã»±¸¿ä±ÝÀº ¾çÀÇ Á¤¼öÀÌ¾î¾ßÇÕ´Ï´Ù.");
	                           isError = true;
	                	   }
	                   }
	                   else {
	                	   System.out.println("[¿À·ù¹ß»ý : ±âÅ¸Ã»±¸¿ä±Ý] ±âÅ¸Ã»±¸¿ä±ÝÀº ¾çÀÇ Á¤¼öÀÌ¾î¾ßÇÕ´Ï´Ù.");
                           isError = true;
	                   }
	                   
	                   // ³³ÀÔ±âÇÑ³¯Â¥ ¿À·ùÈ®ÀÎ
	                   
	                   Boolean date=false;
	                   StringTokenizer st;
	                   String Y,M,D;
	                   int y,m,d;
	                   
	                   if(pay_dateS.length()!=0) {
				             st = new StringTokenizer(pay_dateS,"-");
				             Y=st.nextToken();
				             if(st.hasMoreTokens()) {
					             M=st.nextToken();
					             if(st.hasMoreTokens()) {
						             D=st.nextToken();
						             y=Integer.parseInt(Y);
						             m=Integer.parseInt(M);
						             d=Integer.parseInt(D);
						             if(m>12 || m <1 || d >31 || d<1) {
						            	 System.out.println("[¿À·ù¹ß»ý : ³³ÀÔ±âÇÑ] YYYY-MM-DD¿¡¼­ MMÀº 1~12»çÀÌ, DD´Â 1~31»çÀÌÀÇ ¼ö¿©¾ß ÇÕ´Ï´Ù.");
						        		 isError=true;
						        		 date=true;
						             }
					             }
					             else { 
					            	 isError=true;	
					            	 date=true;
					            	 System.out.println("[¿À·ù¹ß»ý : ³³ÀÔ±âÇÑ] YYYY-MM-DD¿¡¼­ MMÀº 1~12»çÀÌ, DD´Â 1~31»çÀÌÀÇ ¼ö¿©¾ß ÇÕ´Ï´Ù.");
					             }
				             }
				             else {
				            	 System.out.println("[¿À·ù¹ß»ý : ³³ÀÔ±âÇÑ] YYYY-MM-DD¿¡¼­ MMÀº 1~12»çÀÌ, DD´Â 1~31»çÀÌÀÇ ¼ö¿©¾ß ÇÕ´Ï´Ù.");
				            	 isError=true;		
				            	 date=true;
				             }
				            
	                	}
	                   if(!date) {
	                         
	                         if(car_start_dateS.compareTo(pay_dateS)>=0) {
	                                 System.out.println("[¿À·ù¹ß»ý : ³³ÀÔ±âÇÑ] ³³ÀÔ±âÇÑÀº ´ë¿©³¯Â¥º¸´Ù ³ªÁßÀÌ¾î¾ßÇÕ´Ï´Ù.");
	                                 isError = true;
	                                 date=true;
	                          }
	                    }
		                 
	                   	int car_idI = Integer.parseInt(car_idS);
		                 // ¿À·ù°¡ ¾øÀ¸¸é ´ë¿©
		                 if(!isError) {
		                    carBoolean[car_idI]=false;
		                   
		                    System.out.println("Ä·ÇÎÄ« ´ë¿©°¡ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
		                    
		                    query="SELECT * FROM rent";
		                    rs=stmt.executeQuery(query);
		                    rs.last();
		                     
		                    int rent_idI=rs.getInt(1)+1;
		                       
		                    PreparedStatement statement = null;
	                       	statement = con.prepareStatement("insert into rent (rent_id,rent_start,rent_period,rent_price,rent_paydate,rent_extra,rent_extrafee,Car_car_id,Car_Company_comp_id,Customer_cust_id)"+"value(?,?,?,?,?,?,?,?,?,?)");
	                    
	                        statement.setInt(1,rent_idI);
	                        statement.setDate(2, java.sql.Date.valueOf(car_start_dateS));
	                        statement.setInt(3, Integer.parseInt(car_periodS));
	                        statement.setInt(4, Integer.parseInt(priceS));
	                        statement.setDate(5,java.sql.Date.valueOf(pay_dateS));
	                        statement.setString(6,extraS);
	                        statement.setInt(7,Integer.parseInt(extrafeeS));
	                        statement.setInt(8,Integer.parseInt(car_idS));
	                        statement.setInt(9, Integer.parseInt(comp_idS));
	                        statement.setString(10,cust_idS);
	                             
	                             
					        statement.executeUpdate();
					             

	                        rs=stmt.executeQuery(query);
		                    rs.last();
		                    System.out.println("Ä·ÇÎÄ« ´ë¿©°¡ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
		                    System.out.println("´ë¿©ID \t ´ë¿©½ÃÀÛÀÏ \t ---- »ý·« ----");
		                    String str = rs.getInt(1) +"\t"+ rs.getDate(2) + "\t---- »ý·« ----";
		               
		                    System.out.println(str);
		                       
		                 }
	                  }
	               }
	             
	           } catch (Exception e2) {
	               System.out.println("Äõ¸® ÀÐ±â ½ÇÆÐ :" + e2);
	           } 
	         
	      }
	   }

	   
	   public project() {
	        contactDB();
	        setTitle("18011571_18011584/ÀÌÀºÈ¿_ÀÌ¼ÒÁ¤");
	        setSize(1015,650);
	        this.setResizable(false);
	        setVisible(true);
	       
	        Arrays.fill(carBoolean, Boolean.FALSE);
	        Arrays.fill(repairBoolean, Boolean.FALSE);
	        
	        Container c=getContentPane();
	        c.setLayout(new BorderLayout());
	        
	        add(new JScrollPane(mp, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),BorderLayout.WEST);
	        mp.setPreferredSize(new Dimension(590,1000));
	        add(up,BorderLayout.EAST);
	        up.setPreferredSize(new Dimension(390,650));
	        
	        
	        
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	       
	   }
	   
   
   public void contactDB() {
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           System.out.println("µå¶óÀÌ¹ö ·Îµå ¼º°ø");
        } catch(ClassNotFoundException e) {
           e.printStackTrace();
        }
        try {
            System.out.println("µ¥ÀÌÅÍº£ÀÌ½º ¿¬°á ÁØºñ ...");
            con=DriverManager.getConnection(url,userid,pwd);
            System.out.println("µ¥ÀÌÅÍº£ÀÌ½º ¿¬°á ¼º°ø");
          } catch(SQLException e1) {
             e1.printStackTrace();
       }
       
   }
   
   public static void main(String[] args) {
      project pro=new project();
      
   }
   
   
      
}