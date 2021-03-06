
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
   
   Boolean[] carBoolean = new Boolean[30]; 		// 초기값은 false(대여불가능)
   Boolean[] repairBoolean = new Boolean[30];	// 초기값 false(수리불필요)
   
   
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
	      JLabel manager = new JLabel("관리자");
	      JButton initB = new JButton("초기화");
	      
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
  
	      JLabel company = new JLabel("캠핑카대여회사");
	      
	      // 회사 입력
	      JLabel comp_namel = new JLabel("회사 이름");
	      JTextField comp_namef = new JTextField(8);
	      JLabel comp_addressl = new JLabel("주소");
	      JTextField comp_addressf = new JTextField(7);
	      JLabel comp_phonel = new JLabel("전화번호");
	      JTextField comp_phonef = new JTextField(10);
	      JLabel comp_personl = new JLabel("담당자 이름");
	      JTextField comp_personf = new JTextField(7);
	      JLabel comp_emaill = new JLabel("이메일");
	      JTextField comp_emailf = new JTextField(13);
	      JButton comp_insertB = new JButton("회사 입력");
	      JLabel comp_idl = new JLabel("회사 ID");
	      JTextField comp_idf = new JTextField(3);
	      JButton comp_convertB = new JButton("회사 변경");
	      JButton comp_deleteB = new JButton("회사 삭제");
	      
	      
	      // 캠핑카 입력
	      JLabel car_comp_idl = new JLabel("회사 ID");
	      JTextField car_comp_idf = new JTextField(3);
	      JLabel car_namel = new JLabel("캠핑카 이름");
	      JTextField car_namef = new JTextField(10);
	      JLabel car_numl = new JLabel("캠핑카 번호");
	      JTextField car_numf = new JTextField(10);
	      JLabel car_boardl = new JLabel("승차인원수");
	      JTextField car_boardf = new JTextField(3);
	      JLabel car_manucompl = new JLabel("제조회사");
	      JTextField car_manucompf = new JTextField(5);
	      JLabel car_manudatel = new JLabel("제조연도");
	      JTextField car_manudatef = new JTextField(7);
	      JLabel car_distancel = new JLabel("누적주행거리");
	      JTextField car_distancef = new JTextField(4);
	      JLabel car_pricel = new JLabel("대여비용");
	      JTextField car_pricef = new JTextField(5);
	      JLabel car_datel = new JLabel("등록일자");
	      JTextField car_datef = new JTextField(7);
	      JButton car_insertB = new JButton("캠핑카 입력");
	      JLabel car_idl = new JLabel("캠핑카 ID");
	      JTextField car_idf = new JTextField(3);
	      JButton car_deleteB = new JButton("캠핑카 삭제");
	      JButton car_convertB = new JButton("캠핑카 변경");

	      
	      // 고객 정보
	      JLabel cust_idl = new JLabel("운전면허증번호");
	      JTextField cust_idf = new JTextField(10);
	      JLabel cust_namel = new JLabel("고객명");
	      JTextField cust_namef = new JTextField(7);
	      JLabel cust_addressl = new JLabel("고객 주소");
	      JTextField cust_addressf = new JTextField(7);
	      JLabel cust_phonel = new JLabel("고객 전화번호");
	      JTextField cust_phonef = new JTextField(10);
	      JLabel cust_emaill = new JLabel("고객 이메일");
	      JTextField cust_emailf = new JTextField(10);
	      JButton insert_cust = new JButton("고객 입력");
	      JButton delete_cust = new JButton("고객 삭제"); //위에 운전면허증번호로 쓰인 cust_idl, cust_idf 재사용
	      JButton convert_cust = new JButton("고객 변경");
	      
	      // 정비소 garage
	      JLabel gar_namel = new JLabel("정비소명");
	      JTextField gar_namef = new JTextField(8);
	      JLabel gar_addressl = new JLabel("정비소 주소");
	      JTextField gar_addressf = new JTextField(7);
	      JLabel gar_phonel = new JLabel("정비소 전화번호");
	      JTextField gar_phonef = new JTextField(10);
	      JLabel gar_personl = new JLabel("담당자 이름");
	      JTextField gar_personf = new JTextField(7);
	      JLabel gar_emaill = new JLabel("이메일");
	      JTextField gar_emailf = new JTextField(10);
	      JButton insert_gar = new JButton("정비소 입력");
	      JLabel gar_idl = new JLabel("정비소 ID");
	      JTextField gar_idf = new JTextField(3);
	      JButton delete_gar = new JButton("정비소 삭제");
	      JButton convert_gar = new JButton("정비소 변경");
	      
	      
	      // 캠핑카 반환
	      JLabel inspec_rentl = new JLabel("대여번호");
	      JTextField inspec_rentf = new JTextField(3);
	      JLabel inspec_carl = new JLabel("캠핑카 ID");
	      JTextField inspec_carf = new JTextField(3);
	      JLabel inspec_frontl = new JLabel("앞부분");
	      JTextField inspec_frontf = new JTextField(8);
	      JLabel inspec_leftl = new JLabel("왼쪽부분");
	      JTextField inspec_leftf = new JTextField(8);
	      JLabel inspec_rightl = new JLabel("오른쪽부분");
	      JTextField inspec_rightf = new JTextField(8);
	      JLabel inspec_behindl = new JLabel("뒷부분");
	      JTextField inspec_behindf = new JTextField(8);
	      JLabel inspec_repairl = new JLabel("수리필요여부");
	      JTextField inspec_repairf = new JTextField(3);
	      JButton return_car = new JButton("캠핑카 반환/점검내역 저장");
	      
	      
	      // 캠핑카 정비의뢰
	      JLabel repair_idl= new JLabel("정비내역 ID");
	      JTextField repair_idf = new JTextField(3);
	      JLabel repair_carl = new JLabel("캠핑카 ID");
	      JTextField repair_carf = new JTextField(3);
	      JLabel repair_garl = new JLabel("정비소 ID");
	      JTextField repair_garf = new JTextField(3);
	      JLabel repair_corpl = new JLabel("회사 ID");
	      JTextField repair_corpf = new JTextField(3);
	      JLabel repair_custl = new JLabel("고객 운전면허증번호");
	      JTextField repair_custf = new JTextField(10);
	      JLabel repair_infol = new JLabel("정비내역");
	      JTextField repair_infof = new JTextField(12);
	      JLabel repair_datel = new JLabel("수리날짜");
	      JTextField repair_datef = new JTextField(7);
	      JLabel repair_pricel = new JLabel("수리비용");
	      JTextField repair_pricef = new JTextField(5);
	      JLabel repair_paydatel = new JLabel("납입기한");
	      JTextField repair_paydatef = new JTextField(7);
	      JLabel repair_extral = new JLabel("기타정비내역");
	      JTextField repair_extraf = new JTextField(10);
	      JButton request_car = new JButton("정비 내역 입력");
	      JButton repair_delete = new JButton("정비 내역 삭제");
	      JButton repair_convert = new JButton("정비 내역 변경");      
	      
	      // 검색결과
	      JTextArea result = new JTextArea("");
	      
	      // 검색1
	      JLabel s1_con1l = new JLabel("대여기간이");
	      JTextField s1_con1f = new JTextField(3);
	      JLabel s1_con1l2 = new JLabel("일 이상이면서");
	      JLabel s1_con2l = new JLabel("차량 승차 인원수가");
	      JTextField s1_con2f = new JTextField(3);
	      JLabel s1_con2l2 = new JLabel("이상인 고객 정보");
	      JButton s1b = new JButton("검색");	      
	      
	      // 검색2
	      JLabel s2_con1l = new JLabel("반환시 수리여부가");
	      JTextField s2_con1f = new JTextField(3);
	      JLabel s2_con1l2 = new JLabel("이면서(필요=1,불필요=0)");
	      JLabel s2_con2l = new JLabel("대여기간이");
	      JTextField s2_con2f = new JTextField(3);
	      JLabel s2_con2l2 = new JLabel("이상인 캠핑카 정보");
	      JButton s2b = new JButton("검색");
	      
	      // 검색3
	      JLabel s3_con1l = new JLabel("누적주행거리가");
	      JTextField s3_con1f = new JTextField(5);
	      JLabel s3_con1l2 = new JLabel("km이상이면서");
	      JLabel s3_con2l = new JLabel("대여기간이");
	      JTextField s3_con2f = new JTextField(3);
	      JLabel s3_con2l2 = new JLabel("이상인 캠핑카 정보");
	      JButton s3b = new JButton("검색");

	      // 검색4
	      JLabel s4_con1l = new JLabel("고객의 성이");
	      JTextField s4_con1f = new JTextField(3);
	      JLabel s4_con1l2 = new JLabel("이면서");
	      JLabel s4_con2l = new JLabel("수리비용이");
	      JTextField s4_con2f = new JTextField(5);
	      JLabel s4_con2l2 = new JLabel("이상인 고객 정보");
	      JButton s4b = new JButton("검색");
	      
	      public Mpanel(){
	         this.setBackground(new Color(194,247,255));
	         
	         // 전체 배경
	         northMP.setBackground(new Color(194,247,255));
	         
	         // 회사, 캠핑카, 고객, 정비소
	         northMP0.setBackground(new Color(174,222,229));
	         northMP1.setBackground(new Color(174,222,229));
	         northMP2.setBackground(new Color(174,222,229));
	         northMP3.setBackground(new Color(174,222,229));
	         
	         // 캠핑카 반환 내역/점검내역 저장
	         carReturnP.setBackground(new Color(202,230,242));
	         
	         // 정비내역
	         carRepairP.setBackground(new Color(202,230,242));
	         
	         // 검색
	         sP.setBackground(new Color(194,247,255));
	         sP1.setBackground(new Color(174,195,229));
	         sP2.setBackground(new Color(174,195,229));
	         sP3.setBackground(new Color(174,195,229));
	         sP4.setBackground(new Color(174,195,229));	         
	         
	         //초기화버튼
	         initB.addActionListener(this);
	         
	         //회사
	         comp_insertB.addActionListener(this);
	         comp_deleteB.addActionListener(this);
	         comp_convertB.addActionListener(this);
	         
	         //캠핑카 등록
	         car_insertB.addActionListener(this);
	         car_deleteB.addActionListener(this);
	         car_convertB.addActionListener(this);
	         
	         //고객
	         insert_cust.addActionListener(this);
	         delete_cust.addActionListener(this);
	         convert_cust.addActionListener(this);
	         
	         //정비소
	         insert_gar.addActionListener(this);
	         delete_gar.addActionListener(this);
	         convert_gar.addActionListener(this);
	         
	         //캠핑카반환/점검내역 저장
	         return_car.addActionListener(this);
	         
	         //정비내역
	         request_car.addActionListener(this);
	         repair_delete.addActionListener(this);
	         repair_convert.addActionListener(this);
	         
	         //검색
	         s1b.addActionListener(this);
	         s2b.addActionListener(this);
	         s3b.addActionListener(this);
	         s4b.addActionListener(this);	         
	         
	         manager.setPreferredSize(new Dimension(500,20));

	         northMP.add(manager);
	         northMP.add(initB); //초기화
	         
	         // 회사
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
	                  
	         northMP0.add(comp_insertB); //회사 입력버튼
	         northMP0.add(comp_idl);
	         northMP0.add(comp_idf);
	         northMP0.add(comp_deleteB); //회사 삭제버튼
	         northMP0.add(comp_convertB); // 회사 변경버튼
	         
	         // 캠핑카 
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
	         northMP1.add(car_insertB); //캠핑카 입력버튼
	         northMP1.add(car_idl);
	         northMP1.add(car_idf);
	         northMP1.add(car_deleteB); //캠핑카 삭제버튼
	         northMP1.add(car_convertB); //캠핑카 변경버튼
	         
	         // 고객
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
	         
	         northMP2.add(insert_cust); //고객 입력 버튼
	         northMP2.add(delete_cust); //고객 삭제버튼
	         northMP2.add(convert_cust); //고객 변경버튼
	         
	         // 정비소
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
	         
	         northMP3.add(insert_gar); //정비소 입력버튼
	         northMP3.add(gar_idl);
	         northMP3.add(gar_idf);
	         northMP3.add(delete_gar); //정비소 삭제버튼
	         northMP3.add(convert_gar); //정비소 변경버튼
	         
	         // 차 반환 패널
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
	         carReturnP.add(return_car); //차반환
	         
	         
	         // 차 수리 요청 패널   
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
	         carRepairP.add(request_car); //정비내역 입력버튼
	         carRepairP.add(repair_idl);
	         carRepairP.add(repair_idf);
	         carRepairP.add(repair_delete); //정비내역 삭제버튼
	         carRepairP.add(repair_convert); //정비내역 변경버튼
	         
	         
	         // 검색1 패널
	         sP1.add(s1_con1l);
	         sP1.add(s1_con1f);
	         sP1.add(s1_con1l2);
	         sP1.add(s1_con2l);
	         sP1.add(s1_con2f);
	         sP1.add(s1_con2l2);
	         sP1.add(s1b);
	         
	         // 검색2 패널
	         sP2.add(s2_con1l);
	         sP2.add(s2_con1f);
	         sP2.add(s2_con1l2);
	         sP2.add(s2_con2l);
	         sP2.add(s2_con2f);
	         sP2.add(s2_con2l2);
	         sP2.add(s2b);
	         
	         // 검색3 패널
	         sP3.add(s3_con1l);
	         sP3.add(s3_con1f);
	         sP3.add(s3_con1l2);
	         sP3.add(s3_con2l);
	         sP3.add(s3_con2f);
	         sP3.add(s3_con2l2);
	         sP3.add(s3b);
	         
	         // 검색3 패널
	         sP4.add(s4_con1l);
	         sP4.add(s4_con1f);
	         sP4.add(s4_con1l2);
	         sP4.add(s4_con2l);
	         sP4.add(s4_con2f);
	         sP4.add(s4_con2l2);
	         sP4.add(s4b);
	         
	         // 검색결과
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
	               
	              	//초기화 버튼
	                if(e.getSource()==initB) {
	                   System.out.println("초기화 버튼 클릭!");
	                   
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
	                 
	                 // company 튜플 추가
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(1,'가자캠핑','서울 광진구','010-1253-5995','가자영','gocamping@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(2,'캠핑하면캠핑카','서울 양천구','010-2954-1935','고길동','gildong@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(3,'함께캠핑','인천 부평구','010-8424-5357','이하늘','together@gmail.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(4,'스타캠핑','서울 강남구','010-7979-2462','정우성','starcamp@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(5,'친구와캠핑','경기도 수원시','010-6423-1112','장구미','friendcamp@gmail.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(6,'차에서자는여행','경기도 평택시','010-9669-7985','자민수','sleepcamp@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(7,'캠핑카는여기야','부산 해운대','010-2674-8831','여기야','herecamp@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(8,'다같이캠핑카','강원도 강릉','010-1335-7524','모두리','moducamping@gmail.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(9,'썸캠핑','전라북도 전주','010-3345-7511','이수진','somecamp@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(10,'퍼스트캠핑카','서울 영등포구','010-0023-0543','박수영','firstcamp@gmail.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(11,'뉴캠핑카렌트','경기도 화성시','010-1168-9975','한나래','newrent@gmail.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(12,'더좋은캠핑카','부산 중구','010-5555-6654','조은서','goodcamping@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(13,'룰루랄라캠핑','충청북도 청주시','010-1128-8975','박이슬','llullu@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(14,'매치캠핑카렌트','경기도 가평','010-3353-5542','김채영','matchrent@gmail.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(15,'플라워캠핑카','서울 종로구','010-3377-5456','이우성','flowercamping@naver.com');");
	                 
	                 // customer 튜플 추가
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values ('12-24-123456-02', '한강배', '부산', '010-1234-2523', 'hanriver@as.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('11-20-578234-23' , '강여린', '서울', '010-2032-1242', 'strong14@bs.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('13-15-234923-25', '최진동', '경기', '010-4823-2932', 'jindong@ls.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('12-29-363106-74', '송미란', '부산', '010-2258-1125', 'ssong@bs.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('15-49-295264-19', '지선우', '충북', '010-2592-2106', 'zizi@as.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('17-21-754167-55', '이태오', '전북', '010-9823-8216', 'bagegar@as.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('16-82-024902-21', '여다경', '충남', '010-2482-9325', 'jennie@ls.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('15-52-430802-11', '고예림', '충북', '010-3552-1051', 'jeolmi@bs.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('18-76-080706-66', '손제혁', '전남', '010-3203-0203', 'hand31@bs.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('19-41-104824-25', '설명숙', '경북', '010-2062-0432', 'jaesueobs@ls.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('20-15-429232-62', '여병규', '경남', '010-7869-2914', 'gyugyu@as.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('21-52-203192-25', '엄효정', '제주', '010-2033-2932', 'eomwo@as.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('11-23-527823-23', '이준영', '서울', '010-2402-1024', 'noans@ls.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('11-39-293826-72', '민현서', '서울', '010-3406-2460', 'min51@as.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('13-21-391283-52', '박인규', '경기', '010-7632-1851', 'sseule@bs.com');");
	                 
	                 // garage 튜플 추가
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (1,'조박사카정비소', '서울', '010-1025-2012', '이익준', 'ask@ab.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (2,'우리카센터', '경기', '010-1144-1234', '안정원', 'ksr@bb.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (3,'한독자동자정비소', '부산', '010-2524-1414', '김준완', 'lij@abc.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (4,'건국자동차정비소', '제주', '010-2020-1919', '양석형', 'sd@cc.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (5,'세종카센타', '충북', '010-1717-7171', '채송화', 'wes@ab.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (6,'카시스자동차정비소', '충남', '010-1515-5151', '정로사', 'kjw@ab.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (7,'메모리카', '충북', '010-1616-6161', '주종수', 'a12@bb.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (8,'화양검사정비사업소', '전남', '010-1414-4141', '도재학', 'hgj@ab.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (9,'한양자동차정비공업사', '전북', '010-1313-3131', '용석민', 'sk1s@bb.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (10,'하늘카센터', '경기', '010-2323-3232', '장겨울', 'bgh@ab.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (11,'태양카센터', '서울', '010-5252-2324', '안치홍', 'q21rd3@cc.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (12,'세익카센터', '경기', '010-6363-3636', '봉광현', 'djth@ab.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (13,'닥터카정비소', '제주', '010-3737-7373', '추민하', 'jiaqdos@cc.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (14,'자동차세상', '충북', '010-2929-9292', '허선빈', 'sfdklj@abc.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (15,'강변카센터', '부산', '010-1529-3921', '명은원', 'uqpr@bb.com');");
	                  
	                 // car 튜플 추가
	                 stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                  		+ "values(1,'car1','23가 1234',5,'현대','2016',1000,11000,'2020-06-06',1);");
	                  carBoolean[1]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                   		+ "values(2,'car2','19라 2634',6,'기아','2014',7000,10000,'2020-06-13',2);");            
	                  carBoolean[2]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                   		+ "values(3,'car3','15마 9231',3,'벤츠','2015',5000,16000,'2020-06-20',3);");
	                  carBoolean[3]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                   		+ "values(4,'car4','12다 4712',8,'현대','2016',10000,8500,'2020-06-27',4);");             
	                  carBoolean[4]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                   		+ "values(5,'car5','25차 0293',7,'Jeep','2016',8000,14000,'2020-06-12',5);");             
	                  carBoolean[5]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                   		+ "values(6,'car6','21더 1012',8,'기아','2013',40000,9000,'2020-06-01',6);");
	                  carBoolean[6]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                   		+ "values(7,'car7','17나 1539',9,'현대','2018',1000,10000,'2020-06-05',7);");
	                  carBoolean[7]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                   		+ "values(8,'car8','24하 1278',5,'현대','2017',7000,7600,'2020-06-12',8);");
	                  carBoolean[8]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                    		+ "values(9,'car9','10마 7203',4,'볼보','2015',17000,9500,'2020-06-24',9);");
	                  carBoolean[9]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                     		+ "values(10,'car10','13러 8129',3,'볼보','2016',19000,10000,'2020-06-30',10);");
	                  carBoolean[10]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                     		+ "values(11,'car11','27라 4822',8,'벤츠','2013',47000,18000,'2020-06-08',11);");
	                  carBoolean[11]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                     		+ "values(12,'car12','22호 4831',6,'Jeep','2015',27000,19000,'2020-06-07',12);");
	                  carBoolean[12]=true;             
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                  		+ "values(13,'car13','17모 6362',3,'Jeep','2015',27500,17500,'2020-06-19',13);");
	                  carBoolean[13]=true;             
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                  		+ "values(14,'car14','26허 1243',8,'현대','2017',22000,10000,'2020-06-27',14);");
	                  carBoolean[14]=true;             
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                  		+ "values(15,'car15','22라 1920',4,'디스커버리','2018',18500,13000,'2020-06-25',15);");
	                  carBoolean[15]=true;
	                  
	                  // 캠핑카 대여
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                       +"values(1,'2020-05-05',2,30000,'2020-05-03','담요',2000,1,1,'11-20-578234-23');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        +"values(2,'2020-05-07',2,30000,'2020-05-05','라면',1000,2,2,'11-23-527823-23');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        +"values(3,'2020-05-10',3,40000,'2020-05-08','담요',2000,3,3,'11-39-293826-72');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        +"values(4,'2020-05-11',2,35000,'2020-05-09','스피커',12000,4,4,'12-24-123456-02');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        +"values(5,'2020-05-06',4,50000,'2020-05-04','하모니카',5000,5,5,'12-29-363106-74');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                         +"values(6,'2020-05-21',3,40000,'2020-05-19','물',3000,6,6,'13-15-234923-25');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                         +"values(7,'2020-05-15',2,30000,'2020-05-13','모기약',7000,7,7,'13-21-391283-52');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                         +"values(8,'2020-05-17',4,50000,'2020-05-15','라면',2000,8,8,'15-49-295264-19');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                         +"values(9,'2020-05-18',3,40000,'2020-05-16','하모니카',5000,9,9,'15-52-430802-11');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                         +"values(10,'2020-05-13',2,20000,'2020-05-11','라면',3000,10,10,'12-29-363106-74');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                          +"values(11,'2020-05-26',3,30000,'2020-05-24','모기약',2500,11,11,'16-82-024902-21');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                          +"values(12,'2020-05-23',4,50000,'2020-05-21','물',5000,12,12,'17-21-754167-55');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                          +"values(13,'2020-05-30',2,20000,'2020-05-28','모기약',6000,13,13,'17-21-754167-55');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                          +"values(14,'2020-05-03',2,20000,'2020-05-01','라면',2000,14,14,'20-15-429232-62');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                          +"values(15,'2020-05-21',3,35000,'2020-05-19','하모니카',3500,15,15,'21-52-203192-25');");               
	                  
	                  // inspection 튜플 추가                  
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                  			+ "values('스크래치','이상 무','이상 무','이상 무',true,1,1);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('스크래치','스크래치','이상 무','이상 무',true,2,2);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('이상 무','이상 무','스크래치','후방전등 고장',true,3,3);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('스크래치','이상 무','이상 무','이상 무',true,4,4);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('스크래치','스크래치','이상 무','이상 무',true,5,5);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('이상 무','이상 무','이상 무','범퍼 망가짐',true,6,6);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('와이퍼 고장','이상 무','이상 무','이상 무',true,7,7);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('이상 무','이상 무','이상 무','트렁크 고장',true,8,8);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('스크래치','이상 무','이상 무','이상 무',true,9,9);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('스크래치','이상 무','이상 무','이상 무',true,10,10);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('이상 무','문 고장','이상 무','이상 무',true,11,11);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('스크래치','이상 무','문 고장','이상 무',true,12,12);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('라이트 고장','이상 무','이상 무','이상 무',true,13,13);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('이상 무','이상 무','이상 무','범퍼 고장',true,14,14);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('스크래치','스크래치','스크래치','이상 무',true,15,15);");
	                  
	                  // 정비내역
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(1,'백미러 교체,스크래치제거','2020-05-10',20000,'2020-05-12','예방점검서비스',1,1,1,'11-20-578234-23');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(2,'후방전등 교체','2020-05-12',60000,'2020-05-14','살균탈취서비스',2,2,2,'11-23-527823-23');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(3,'연료필터 탈부착,스크래치제거','2020-05-16',70000,'2020-05-18','예방점검서비스',3,3,3,'11-39-293826-72');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(4,'에어컨 에어필터 탈부착,스크래치제거','2020-05-16',30000,'2020-05-18','엔진점검서비스',4,4,4,'12-24-123456-02');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(5,'엔진오일 교환,스크래치제거','2020-05-13',50000,'2020-05-15','살균탈취서비스',5,5,5,'12-29-363106-74');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(6,'뒤범퍼 교체','2020-05-27',100000,'2020-05-29','예방점검서비스',6,6,6,'13-15-234923-25');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(7,'와이퍼 교체','2020-05-20',20000,'2020-05-22','엔진점검서비스',7,7,7,'13-21-391283-52');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(8,'트렁크 수리','2020-05-24',20000,'2020-05-22','예방점검서비스',8,8,8,'15-49-295264-19');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(9,'엔진오일 교체,스크래치제거','2020-05-24',50000,'2020-05-26','살균탈취서비스',9,9,9,'15-52-430802-11');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(10,'앞범퍼 교체,스크래치제거','2020-05-18',70000,'2020-05-20','예방점검서비스',10,10,10,'12-29-363106-74');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(11,'문 교체','2020-05-28',30000,'2020-05-30','살균탈취서비스',11,11,11,'16-82-024902-21');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(12,'문 교체, 스크래치제거','2020-05-30',50000,'2020-06-01','살균탈취서비스',12,12,12,'17-21-754167-55');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(13,'라이트 교체','2020-06-04',20000,'2020-06-06','엔진점검서비스',13,13,13,'17-21-754167-55');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(14,'뒤범퍼 교체','2020-05-08',50000,'2020-05-10','예방점검서비스',14,14,14,'20-15-429232-62');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(15,'에어컨 에어필터 탈부착,스크래치제거','2020-05-27',50000,'2020-05-29','살균탈취서비스',15,15,15,'21-52-203192-25');");
	                  
	                  System.out.println("초기화 완료!");
	                }
	                
	                //회사 입력버튼
	                else if(e.getSource()==comp_insertB) {
	                	System.out.println("회사 입력버튼 클릭!");
	                	
	                	Boolean isError = false;
	                	Boolean phoneError = false;
	                	Boolean emailError = false;
	                	int comp_idI;
	                	String comp_nameS = comp_namef.getText();
	                	String comp_addressS = comp_addressf.getText();
	                	String comp_phoneS = comp_phonef.getText();
	                	String comp_personS = comp_personf.getText();
	                	String comp_emailS = comp_emailf.getText();
	                   
	                   
	                	// 빈칸 있는지 확인
	                	if(comp_nameS.length()==0 || comp_addressS.length()==0 || comp_phoneS.length()==0 || comp_personS.length()==0 || comp_emailS.length()==0) {
	                		System.out.println("[오류발생 : 데이터 입력 없음] 회사 ID를 제외한 모든 칸에 데이터를 입력해주세요.");
	               			isError=true;
	                	}
	                	else {
	                		
	                		// phone 오류 확인
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
				            	System.out.println("[오류발생 : 전화번호] 전화번호는 XXX-XXXX-XXXX 여야 합니다.(X의 수는 3,4,4로 제한, X는 숫자여야함)");
				        		isError=true;
				             }
				             
				            // email 오류 확인
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
				        	   System.out.println("[오류발생 : 이메일] 이메일은 XXX@XXX.XXX 여야 합니다.(X의 수는 제한 없음)");
				        	   isError=true;
				           }
		                   
				             
				           // 오류가 없으면 삽입 
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
		                       
		                	   System.out.println("오류없음");
		                	   statement.executeUpdate();
				             
		                	   System.out.println("캠핑카 대여회사 입력 완료되었습니다.");
		                	   System.out.println("회사 ID \t 회사 이름 \t주소 \t전화번호 \t\t담당직원 \t이메일");
		                   
		                	   rs=stmt.executeQuery(query);
		                	   rs.last();
		                   
		                	   String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)+ "\t" + rs.getString(5) + "\t" + rs.getString(6);
		                	   System.out.println(str);
		                    }
	                   }
	                }
	                
	                //회사 삭제버튼
	                else if(e.getSource()==comp_deleteB) {
	                	System.out.println("회사 삭제 버튼 클릭!");
	                	
	                	String comp_idS = comp_idf.getText();
	                	
	                	if(comp_idS.length()==0) {
	                   		System.out.println("[오류발생 : 데이터 입력 없음] 회사 ID에 데이터를 입력해주세요.");
	                	}
	                	
	                	else {
		                	if(!CheckNumber(comp_idS)) {
		                		System.out.println("[오류발생 : 회사 ID] 회사 ID는 양의 정수여야 합니다.");
		                	}
		                	else{
		                		int comp_idI = Integer.parseInt(comp_idS);
			                	query = "SELECT * FROM company WHERE comp_id =" + comp_idS;
					        	rs = stmt.executeQuery(query);
					        	
					        	if(!rs.next()) { 
					        		System.out.println("[오류발생 : 회사 ID] 해당 ID의 회사가 없습니다.");
					        	}
					        	else {
					        		int rentI;
					        		
					        		// 대여 테이블
					        		String query1 = "SELECT * FROM rent WHERE Car_Company_comp_id =" + comp_idI;
						        	rs = stmt.executeQuery(query1);
						        	
						        	while(rs.next()) { 
						        		// 점검 내역에서 삭제
						        		Statement stmt2 = con.createStatement();
						        		rentI = rs.getInt(1);
						        		query = "DELETE FROM inspection WHERE Rent_rent_id = " + rentI;
							        	stmt2.executeUpdate(query);
						        	}
					        		
						        	// 대여 테이블에서 삭제
					        		query = "DELETE FROM rent WHERE Car_Company_comp_id = " + comp_idI;
					        		stmt.executeUpdate(query);
					        		
					        		// 정비 내역에서 삭제
					        		query = "DELETE FROM repair WHERE Car_Company_comp_id = " + comp_idI;
					        		stmt.executeUpdate(query);
					        		
					        		// 캠핑카 등록에서 삭제
					        		query = "DELETE FROM car WHERE Company_comp_id = " + comp_idI;
					        		stmt.executeUpdate(query);	
					        		
					        		// 회사에서 삭제
					        		query = "DELETE FROM company WHERE comp_id = " + comp_idI;
					        		stmt.executeUpdate(query);		        		
					        		
					        		System.out.println("해당 ID의 회사 삭제가 완료되었습니다.");
					        		
					        		query="SELECT * FROM company WHERE comp_id >=" + 0;
				                    rs = stmt.executeQuery(query);
				                    
				                    System.out.println("회사 ID \t 회사이름 ----생략----");
			
				                    while(rs.next()) {
				                       System.out.println(rs.getInt(1)+"\t 나머지는 생략 -----");
				                       
				                    }
					        	}
		                	}
	                	}
	                } 
	                
	                //회사 변경버튼
	                else if(e.getSource()==comp_convertB) {
	                	System.out.println("회사 변경 버튼 클릭!");
	                	
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
	                	
	                    //빈칸이 있는지 확인
	                	if(comp_idS.length()==0 || (comp_nameS.length()==0 && comp_addressS.length()==0 && comp_phoneS.length()==0 && comp_personS.length()==0 && comp_emailS.length()==0)) {
	                   		System.out.println("[오류발생 : 데이터 입력 없음] 회사ID칸과 바꾸고싶은 속성칸에 데이터를 입력해주세요.");
	                   		isError=true;
	                    }
	                	else {
	                		int comp_idI = Integer.parseInt(comp_idS);
	                		
		                	if(!CheckNumber(comp_idS)) {
		                		System.out.println("[오류발생 : 회사 ID] 회사 ID는 양의 정수여야 합니다.");
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
			                    
			                    // phone 오류 확인
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
						            	 System.out.println("[오류발생 : 전화번호] 전화번호는 XXX-XXXX-XXXX 여야 합니다.(X의 수는 3,4,4로 제한, X는 숫자여야함)");
						        		 isError=true;
						             }
						             else {
						            	 phone=true;
						             }
			                    }
			                    
					            // email 오류 확인
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
						            	 System.out.println("[오류발생 : 이메일] 이메일은 XXX@XXX.XXX 여야 합니다.(X의 수는 제한 없음)");
						        		 isError=true;
						             }
						             else {
						            	 email=true;
						             }
				                   
			                    }
			                    
					            // 오류가 없으면 변경
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
					        		
					        		System.out.println("해당 ID의 회사 정보 변경이 완료되었습니다.");
					        		query="SELECT * FROM company WHERE comp_id >=" + 0;
				                    rs = stmt.executeQuery(query);
			
				                    
				                    System.out.println("회사 ID \t 회사이름 ----생략----");
			
				                    while(rs.next()) {
				                       System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t 나머지는 생략 -----");
				                       
				                    }
			                    }
			                    
		                	}
	                	}
	                }
	                
	                //캠핑카 입력버튼
	                else if(e.getSource()==car_insertB) {
	                	System.out.println("캠핑카 입력버튼 클릭!");
	                	
	                	Boolean isError = false;
	                	Boolean numError = false;
	                	Boolean dateError =false;
	                	
	                	// 대여회사 ID
	                	String car_comp_idS = car_comp_idf.getText();
	                	int car_comp_idI=0;
	                	
	                	// 캠핑카 이름
	                	String car_nameS = car_namef.getText();
	                	// 캠핑카 등록 ID
	                	String car_numS = car_numf.getText();
	                	
	                	//캠핑카 승차 인원수
	                	String car_boardS = car_boardf.getText();
	                	int car_boardI=0;
	                	
	                	// 제조회사
	                	String car_manucompS = car_manucompf.getText();
	                	// 제조연도
	                	String car_manudateS = car_manudatef.getText();
	                	
	                	//누적주행거리
	                	String car_distanceS = car_distancef.getText();
	                	int car_distanceI=0; 
	                	
	                	// 대여비용
	                	String car_priceS = car_pricef.getText();
	                	int car_priceI=0;
	                	
	                	// 캠핑카 등록일자
	                	String car_dateS = car_datef.getText();
	                	
	                	// 빈 곳이 없는지 확인
	                	if(car_comp_idS.length()==0 || car_nameS.length()==0 || car_numS.length()==0 || car_boardS.length()==0 || car_manucompS.length()==0 
	                			|| car_manudateS.length()==0 || car_distanceS.length()==0 ||car_priceS.length()==0 || car_dateS.length()==0) {
	                		System.out.println("[오류발생 : 데이터 입력 없음] 모든 칸에 데이터를 입력해주세요.");
	                		isError=true;
	                	}
	                	else {

		                	// 회사 ID 오류 확인
		                	if(!CheckNumber(car_comp_idS)) { 
		                		isError=true;
		                		System.out.println("[오류발생 : 회사 ID] 회사 ID는 양의 정수입니다.");
		                	}
		                	else{
		                		car_comp_idI=Integer.parseInt(car_comp_idS);
		                		query = "SELECT * FROM company WHERE comp_id =" + car_comp_idI;
					        	 rs = stmt.executeQuery(query);
					        	 if(!rs.next()) { 
					        		 System.out.println("[오류발생 : 회사 ID] 해당 ID의 회사가 없습니다.");
					        		 isError=true;
					        	 }
				        	 }
				        	 
		                	// 차량번호 오류 확인
		                	StringTokenizer st;
		                	st = new StringTokenizer(car_numS," ");
			                String n1,n2;
			                	
			                n1=st.nextToken();
			                if( n1.length()!=3 || !(n1.charAt(0)>='0' && n1.charAt(0)<='9') || !(n1.charAt(1)>='0' && n1.charAt(1)<='9')
		                		|| !(n1.charAt(2)>='가' && n1.charAt(2)<='힣')) { 
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
								        	System.out.println("[오류발생 : 차량번호] 해당 차량번호가 이미 존재합니다.");
								        	isError=true;
								        }
				                	}
				                }
				            }
		                	
		                	if(numError) {
		                		System.out.println("[오류발생 : 차량번호] 차량번호 형식이 잘못되었습니다. 예시)OOX OOOO  이때 O는 숫자, X는 한글");
		                		isError=true;
		                	}
		                	
		          
		                	// 탑승인원 오류 확인
		                	if(!CheckNumber(car_boardS)) { 
		                		isError=true;
		                		System.out.println("[오류발생 : 승차인원] 승차인원은  숫자로 입력해야 합니다.");
		                	}
		                	else {
		                		car_boardI=Integer.parseInt(car_boardS);
			                	if(car_boardI < 1) {
			                		System.out.println("[오류발생 : 승차인원] 승차인원은 1명이상 이어야 합니다.");
			                		isError=true;
			                	}
		                	}
		                	
		                	// 제조연도 오류 확인
		                	if(!CheckNumber(car_manudateS)) {
		                		System.out.println("[오류발생 : 제조연도] 제조연도는 4자리 숫자 YYYY 입니다.");
				        		 isError=true;
				        		 dateError=true;
		                	}
		                	else {
		                		if(car_manudateS.length()!=4) {
		                			System.out.println("[오류발생 : 제조연도] 제조연도는 4자리 숫자 YYYY 입니다.");
					        		 isError=true;
					        		 dateError=true;
		                		}
		                	}
		                	
		                	// 주행거리 오류 확인
				            if(!CheckNumber(car_distanceS)) { 
			                		isError=true;
			                		System.out.println("[오류발생 : 주행거리] 주행거리는 양의 정수입니다.");
			                }
				            else {
				            	 car_distanceI = Integer.parseInt(car_distanceS);
				            	 if(car_distanceI < 0) {
				            		 System.out.println("[오류발생 : 주행거리] 주행거리는 0이상 이어야 합니다.");
				                	isError=true;
				            	}
				            }
				             
		                	// 대여비용 오류 확인
		                	if(!CheckNumber(car_priceS)) { 
		                		isError=true;
		                		System.out.println("[오류발생 : 대여비용] 대여비용은 양의 정수입니다.");
		                	}
		                	else {
		                		car_priceI = Integer.parseInt(car_priceS);
			                	if(car_priceI < 1) {
			                		System.out.println("[오류발생 : 대여비용] 대여비용은 1원이상 이어야 합니다.");
			                		isError=true;
			                	}
		                	}
		                	
		                	// 등록날짜 오류 확인
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
						            	System.out.println("[오류발생 : 등록날짜] YYYY-MM-DD에서 MM은 1~12사이, DD는 1~31사이의 수여야 합니다.");
						        		isError=true;
						        		dateError=true;
						            }
					             }
				            	
					             else {
					            	 isError=true;
					            	 dateError=true;
					            	 System.out.println("[오류발생 : 등록날짜] YYYY-MM-DD에서 MM은 1~12사이, DD는 1~31사이의 수여야 합니다.");
					             }
				             }
				            
				             else {
				            	 System.out.println("[오류발생 : 등록날짜] YYYY-MM-DD에서 MM은 1~12사이, DD는 1~31사이의 수여야 합니다.");
				            	 isError=true;
				            	 dateError=true;
				             }
	                	
				             
				             // 제조연도<등록날짜 오류 확인
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
							    	 System.out.println("[오류발생 : 제조연도와 등록날짜] 제조연도는 등록날짜보다 과거여야합니다.");
							     }
				             }
				             
				             // 오류가 없으면 삽입 
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
			                     
			                     System.out.println("캠핑카 입력 완료되었습니다.");
			                     System.out.println("회사ID \t 대여가능 여부 \t캠핑카ID \t차이름 \t차량번호 \t승차인원수 \t제조회사 \t제조날짜 \t주행거리 \t대여비용 \t등록일자 ");
			                     String str = rs.getInt(10) +"\t"+ carBoolean[car_idI] + "\t\t" + rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + 
			                                rs.getInt(4) + "\t" + rs.getString(5)+ "\t" + rs.getString(6) + "\t" + rs.getInt(7) + "\t" +
			                                rs.getInt(8) + "\t" + rs.getDate(9);
			                      
			                     System.out.println(str);
			                      
			                  }
	                	}
			             
	                }
	                
	                //캠핑카 삭제버튼
	                else if(e.getSource()==car_deleteB) {
	                	System.out.println("캠핑카 삭제버튼 클릭!");
	                	
	                    String car_idS = car_idf.getText();
	                    
	                    // 빈칸
	                    if(car_idS.length()==0) {
	                   		System.out.println("[오류발생 : 데이터 입력 없음] 캠핑카 ID에 데이터를 입력해주세요.");
	                   		
	                    }
	                    else {
	                    	if(!CheckNumber(car_idS)) {
		                		System.out.println("[오류발생 : 캠핑카 ID] 캠핑카 ID는 양의 정수여야 합니다.");
		                	}
	                    	else {
			                   int car_idI = Integer.parseInt(car_idS);
			                   query = "SELECT * FROM car WHERE car_id =" + car_idI;
			                   rs = stmt.executeQuery(query);
			                
			                   if(!rs.next()) {
			                	   System.out.println("[오류발생 : 캠핑카등록 ID] 해당 ID의 캠핑카가 없습니다.");
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
			                	
			                	   System.out.println("해당 ID의 캠핑카 삭제가 완료되었습니다.");
			                	   
			                	   stmt.executeUpdate("SET foreign_key_checks = 1");
			                    
			                    
			                	   query="SELECT * FROM car WHERE car_id >=" + 0;
			                       rs = stmt.executeQuery(query);
			
			                       
			                       System.out.println("캠핑카 ID \t 캠핑카이름 ----생략----");
			
			                       while(rs.next()) {
			                          System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+" 나머지는 생략 -----");
			                          
			                       }
			                 }
	                    }
	                 }
	                }
	                
	                // 캠핑카 변경버튼
	                else if(e.getSource()== car_convertB) {
	                	System.out.println("캠핑카 변경버튼 클릭!");
	                	
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
	                	
	                	// 빈 곳이 없는지 확인
	                	if(car_idS.length()==0 || car_comp_idS.length()==0 || (car_nameS.length()==0 && car_numS.length()==0 && car_boardS.length()==0 && car_manucompS.length()==0 
	                			&& car_manudateS.length()==0 && car_distanceS.length()==0 && car_priceS.length()==0 && car_dateS.length()==0)) {
	                		System.out.println("[오류발생 : 데이터 입력 없음] 캠핑카ID칸과 회사ID칸과 바꾸고싶은 속성칸에 데이터를 입력해주세요.");
	                   		isError=true;
	                	}
	                	else {
	                		// 캠핑카 ID 오류확인
	                		if(!CheckNumber(car_idS)) { 
		                		isError=true;
		                		carError = true;
		                		System.out.println("[오류발생 : 캠핑카 ID] 캠핑카 ID는 양의 정수입니다.");
		                	}
		                	else{
		                		car_idI=Integer.parseInt(car_idS);
		                		query = "SELECT * FROM car WHERE car_id =" + car_idI;
					        	 rs = stmt.executeQuery(query);
					        	 if(!rs.next()) { 
					        		 System.out.println("[오류발생 : 캠핑카 ID] 해당 ID의 캠핑카가 없습니다.");
					        		 isError=true;
					        		 carError = true;
					        	 }
					            
				        	 }

		                	// 회사 ID 오류 확인
		                	if(!CheckNumber(car_comp_idS)) { 
		                		isError=true;
		                		carError = true;
		                		System.out.println("[오류발생 : 회사 ID] 회사 ID는 양의 정수입니다.");
		                	}
		                	else{
		                		car_comp_idI=Integer.parseInt(car_comp_idS);
		                		query = "SELECT * FROM company WHERE comp_id =" + car_comp_idI;
					        	 rs = stmt.executeQuery(query);
					        	 if(!rs.next()) { 
					        		 System.out.println("[오류발생 : 회사 ID] 해당 ID의 회사가 없습니다.");
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
				                   		 System.out.println("[오류발생 : 회사 ID] 캠핑카 ID와 회사 ID가 서로 맞지않습니다.");
				                    	 }
				                     } 	 
				                 } 
		                     }
		                	
		                	// 차이름 오류확인
		                	if(car_nameS.length()!=0) {
		                		name=true;
		                	}
				        	 
		                	// 차량번호 오류 확인
		                	if(car_numS.length()!=0) {
		                		st = new StringTokenizer(car_numS," ");
				                String n1,n2;
				                	
				                n1=st.nextToken();
				                if( n1.length()!=3 || !(n1.charAt(0)>='0' && n1.charAt(0)<='9') || !(n1.charAt(1)>='0' && n1.charAt(1)<='9')
			                		|| !(n1.charAt(2)>='가' && n1.charAt(2)<='힣')) { 
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
				                				System.out.println("[오류발생 : 차량번호] 해당 차량번호가 이미 존재합니다.");
				                				isError=true;
									        }
					                	}
					                }
					            }
			                	
			                	if(numError) {
			                		System.out.println("[오류발생 : 차량번호] 차량번호 형식이 잘못되었습니다. 예시)OOX OOOO  이때 O는 숫자, X는 한글");
			                		isError=true;
			                	}
			                	else {
			                		num=true;
			                	}
		                	
		                	}
		   		                	
		                	// 탑승인원 오류 확인
		                	if(car_boardS.length()!=0) {
			                	if(!CheckNumber(car_boardS)) { 
			                		isError=true;
			                		System.out.println("[오류발생 : 승차인원] 승차인원은 양의 정수입니다.");
			                	}
			                	else {
			                		car_boardI=Integer.parseInt(car_boardS);
				                	if(car_boardI < 1) {
				                		System.out.println("[오류발생 : 승차인원] 승차인원은 1명이상 이어야 합니다.");
				                		isError=true;
				                	}
				                	else {
				                		board=true;
				                	}
			                	}
		                	}
		                	
		                	// 제조회사 오류 확인
		                	if(car_manucompS.length()!=0) {
		                		manucomp=true;
		                	}
		                	
		                	// 제조연도 오류 확인
		                	if(car_manudateS.length()!=0) {
		                		if(!CheckNumber(car_manudateS)) {
			                		System.out.println("[오류발생 : 제조연도] 제조연도는 4자리 숫자 YYYY 입니다.");
					        		 isError=true;
					        		 dateError=true;
			                	}
			                	else {
			                		if(car_manudateS.length()!=4) {
			                			System.out.println("[오류발생 : 제조연도] 제조연도는 4자리 숫자 YYYY 입니다.");
						        		 isError=true;
						        		 dateError=true;
			                		}
			                		else {
			                			manudate=true;
			                		}
			                	}
		                	}
		                	
		                	// 누적주행거리 오류 확인
		                	if(car_distanceS.length()!=0) {
					             if(!CheckNumber(car_distanceS)) { 
				                		isError=true;
				                		System.out.println("[오류발생 : 누적주행거리] 누적주행거리는 양의 정수입니다.");
				                	}
				                	else {
				                		car_distanceI = Integer.parseInt(car_distanceS);
					                	if(car_distanceI < 0) {
					                		System.out.println("[오류발생 : 누적주행거리] 누적주행거리는 0이상 이어야 합니다.");
					                		isError=true;
					                	}
					                	else {
					                		distance=true;
					                	}
				                	}
		                	}
		                	
		                	// 대여비용 오류 확인
		                	if(car_priceS.length()!=0) {
			                	if(!CheckNumber(car_priceS)) { 
			                		isError=true;
			                		System.out.println("[오류발생 : 대여비용] 대여비용은 양의 정수입니다.");
			                	}
			                	else {
			                		car_priceI = Integer.parseInt(car_priceS);
				                	if(car_priceI < 1) {
				                		System.out.println("[오류발생 : 대여비용] 대여비용은 1원이상 이어야 합니다.");
				                		isError=true;
				                	}
				                	else {
				                		price=true;
				                		
				                	}
			                	}
		                	}
		                	
		                	// 등록날짜 오류 확인
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
							            	 System.out.println("[오류발생 : 등록날짜] YYYY-MM-DD에서 MM은 1~12사이, DD는 1~31사이의 수여야 합니다.");
							        		 isError=true;
							        		 dateError=true;
							             }
						             }
						             else { 
						            	 isError=true;
						            	 dateError=true;
						            	 System.out.println("[오류발생 : 등록날짜] YYYY-MM-DD에서 MM은 1~12사이, DD는 1~31사이의 수여야 합니다.");
						             }
					             }
					             
					             else {
					            	 System.out.println("[오류발생 : 등록날짜] YYYY-MM-DD에서 MM은 1~12사이, DD는 1~31사이의 수여야 합니다.");
					            	 isError=true;
					            	 dateError=true;
					             }
					             
					             if(!dateError) {
					            	 date=true;
					            }
		                	}
		                	
		                	// 제조연도<등록날짜 오류 확인
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
	                        	   System.out.println("[오류발생 : 제조연도와 등록날짜] 제조연도는 등록날짜보다 과거이어야합니다.");
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
	                                System.out.println("[오류발생 : 제조연도와 등록날짜] 등록날짜는 제조연도보다 최근이어야합니다.");
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
		                           System.out.println("[오류발생 : 제조연도와 등록날짜] 등록날짜는 제조연도보다 최근이어야합니다.");
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
					        		
					        System.out.println("해당 ID의 캠핑카 정보 변경이 완료되었습니다.");
					        query="SELECT * FROM car WHERE car_id >=" + 0;
				            rs = stmt.executeQuery(query);
			
				                    
				            System.out.println("캠핑카 ID \t 캠핑카이름 ----생략----");
			
				            while(rs.next()) {
				            	System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t 나머지는 생략 -----");
				            }
 
			             }
	          		}
	                
	                //고객 입력버튼
	                else if(e.getSource() == insert_cust) {
	                    Boolean isError = false;
	                    Boolean phoneError=false;
	                    Boolean emailError=false;
	                    
	                   StringTokenizer st;
	                    
	                   System.out.println("고객 입력버튼 클릭!");
	                   
	                   String cust_id = cust_idf.getText();;
	                   String cust_name = cust_namef.getText();
	                   String cust_address = cust_addressf.getText();
	                   String cust_phone = cust_phonef.getText();
	                   String cust_email = cust_emailf.getText();
	                   
	                   if(cust_id.length()==0 || cust_name.length()==0 || cust_address.length()==0 || cust_phone.length()==0 || cust_email.length()==0) {
	                		System.out.println("[오류발생 : 데이터 입력 없음] 모든 칸에 데이터를 입력해주세요.");
	                   		isError=true;
	                	}
	                   else {
	                   
		                   // 운전면허증번호
		                   if(cust_id.length()!=15 || cust_id.charAt(2)!='-' || cust_id.charAt(5)!='-' || cust_id.charAt(12)!='-' ||
		                         !Character.isDigit(cust_id.charAt(0)) || !Character.isDigit(cust_id.charAt(1)) || !Character.isDigit(cust_id.charAt(3)) ||
		                         !Character.isDigit(cust_id.charAt(4)) || !Character.isDigit(cust_id.charAt(6)) || !Character.isDigit(cust_id.charAt(7)) ||
		                         !Character.isDigit(cust_id.charAt(8)) || !Character.isDigit(cust_id.charAt(9)) || !Character.isDigit(cust_id.charAt(10)) ||
		                         !Character.isDigit(cust_id.charAt(11)) || !Character.isDigit(cust_id.charAt(13)) || !Character.isDigit(cust_id.charAt(14))) {
		                      System.out.println("[오류 발생 : 운전면허증 번호] 운전면허증번호는 nn-nn-nnnnnn-nn 형식으로 입력해야 합니다. n은 숫자");
		                      isError = true;
		                   }
		                   else {
		                      query = "select * from customer where cust_id = '"+cust_id+"'";
		                      rs = stmt.executeQuery(query);
		                      
		                      while(rs.next()) {
		                       if(rs.getString(1).length()!=0) {
		                          System.out.println("[오류발생 : 운전면허증번호] 해당 운전면허증번호는 등록되어 있습니다.");
		                             isError = true;
		                       }
		                      }
		                   
		                   }
		                   
		                  //고객명
		                    if(cust_name.length()==0) {
		                      System.out.println("[오류발생 : 고객명] 고객명을 입력해야 합니다.");
		                      isError = true;
		                    }
		                    
		                    // phone 오류 확인
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
					            	 System.out.println("[오류발생 : 전화번호] 전화번호는 XXX-XXXX-XXXX 여야 합니다.(X의 수는 3,4,4로 제한, X는 숫자여야함)");
					        		 isError=true;
					             }
					             
		                    }
				            // email 오류 확인
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
					            	 System.out.println("[오류발생 : 이메일] 이메일은 XXX@XXX.XXX 여야 합니다.(X의 수는 제한 없음)");
					        		 isError=true;
					             }
					             
		                    }
		                   
		                   
		                    if(!isError) {
	                            query = "insert into customer(cust_id,cust_name,cust_address,cust_phone,cust_email) values('"+ cust_id +"','"+cust_name+"','"+
	                               cust_address+"','"+cust_phone+"','"+cust_email+"');";
	                                 
	                            stmt.executeUpdate(query);
	                            System.out.println("고객 입력 완료");
	                            
	                            query="SELECT * FROM customer WHERE cust_id = '" +cust_id +"'";
	                            rs=stmt.executeQuery(query);
	                            
	                            if(rs.next()) {
	                              System.out.println("운전면허증번호 \t 고객명 \t 고객주소 \t고객 전화번호 \t고객 이메일 ");
	                              String str = rs.getString(1) +"\t"+ rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + 
	                                            rs.getString(5) ;
	                                  
	                              System.out.println(str);
	                           }
	                       }
		                   
	                   }
	                }
	      			
	                // 고객 삭제버튼
	                else if(e.getSource() == delete_cust) {
	                	String cust_idS = cust_idf.getText();
	                    
	                    if(cust_idS.length()==0) {
	                   		System.out.println("[오류발생 : 데이터 입력 없음] 운전면허증번호에 데이터를 입력해주세요.");		
	                    }
	                    else {
	                    	if(cust_idS.length()!=15 || cust_idS.charAt(2)!='-' || cust_idS.charAt(5)!='-' || cust_idS.charAt(12)!='-' ||
	   	                         !Character.isDigit(cust_idS.charAt(0)) || !Character.isDigit(cust_idS.charAt(1)) || !Character.isDigit(cust_idS.charAt(3)) ||
	   	                         !Character.isDigit(cust_idS.charAt(4)) || !Character.isDigit(cust_idS.charAt(6)) || !Character.isDigit(cust_idS.charAt(7)) ||
	   	                         !Character.isDigit(cust_idS.charAt(8)) || !Character.isDigit(cust_idS.charAt(9)) || !Character.isDigit(cust_idS.charAt(10)) ||
	   	                         !Character.isDigit(cust_idS.charAt(11)) || !Character.isDigit(cust_idS.charAt(13)) || !Character.isDigit(cust_idS.charAt(14))) {
	   	                      System.out.println("[오류 발생 : 운전면허증 번호 형식 오류] 운전면허증번호는 nn-nn-nnnnnn-nn 형식으로 입력해야 합니다. n은 숫자");
	   	                   	}
	                    	
	                    	else {
			                   query = "SELECT * FROM customer WHERE cust_id ='" + cust_idS+"'";
			                   rs = stmt.executeQuery(query);
			                   
			                   System.out.println("고객 삭제버튼 클릭!");
			                 
			                 
			                 if(!rs.next()) { 
			                    System.out.println("[오류발생 : 운전면허증번호] 해당 운전면허증을 가진 고객이 없습니다.");
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
			                                                       
			                    
			                    System.out.println("해당 운전면허증을 가진 고객의 삭제가 완료되었습니다.");
			                    
			                    
			                    query="SELECT * FROM customer WHERE cust_id >= '00-00-000000-00'";
			                    rs = stmt.executeQuery(query);
			
			                       
			                    System.out.println("운전면허증번호 \t 고객명 ----생략----");
			
			                    while(rs.next()) {
			                        System.out.println(rs.getString(1)+"\t"+rs.getString(2)+" 나머지는 생략 -----");
			                          
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
		                		System.out.println("[오류발생 : 데이터 입력 없음] 운전면허번호칸과 바꾸고싶은 속성칸에 데이터를 입력해주세요.");
		                   		isError=true;
		                 }
		                 else {
			                   // 운전면허증번호
		                	   query = "select * from customer where cust_id = '"+cust_id+"'";
			                      rs = stmt.executeQuery(query);
						        if(!rs.next()) { 
						        	 System.out.println("[오류발생 : 운전면허번호] 해당 운전면허번호의 고객이 없습니다.");
						        	 isError=true;
						        }
						        else {
				                   
				                    //고객명
				                    if(cust_name.length()!=0) {
				                      name=true;
				                    }
				                    
				                    //고객 주소
				                    if(cust_address.length()!=0) {
				                       address=true;
				                    }
				                    
				                    // phone 오류 확인
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
							            	 System.out.println("[오류발생 : 전화번호] 전화번호는 XXX-XXXX-XXXX 여야 합니다.(X의 수는 3,4,4로 제한, X는 숫자여야함)");
							        		 isError=true;
							             }
							             else {
							            	 phone=true;
							             }
				                    }
						            // email 오류 확인
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
							            	 System.out.println("[오류발생 : 이메일] 이메일은 XXX@XXX.XXX 여야 합니다.(X의 수는 제한 없음)");
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
						        		
						        		System.out.println("해당 운전면허번호의 고객정보가 변경이 완료되었습니다.");
						        		query="SELECT * FROM customer WHERE cust_id='"+cust_id+"'";
					                    rs = stmt.executeQuery(query);
				
					                    
					                    System.out.println("운전면허번호 \t 이름 ----생략----");
				
					                    while(rs.next()) {
					                       System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t 나머지는 생략 -----");
					                    }
				                   }
						        }
		                   }
	                }
	                
	                //정비소 입력버튼
	                else if(e.getSource() == insert_gar) {
	                	Boolean isError = false;
	                    Boolean phoneError=false;
	                    Boolean emailError=false;
	                     
	                    System.out.println("정비소 입력버튼 클릭!");
	                     
	                    String gar_name = gar_namef.getText();
	                    String gar_address = gar_addressf.getText();
	                    String gar_phone = gar_phonef.getText();
	                    String gar_person = gar_personf.getText();
	                    String gar_email = gar_emailf.getText();
	                    int gar_id;
	                     
	                     //정비소명
	                    if(gar_name.length()==0) {
	                    	System.out.println("[오류발생 : 정비소명] 정비소명을 입력해야 합니다.");
	                        isError = true;
	                    }
	                     
	                     //정비소 주소
	                    if(gar_address.length()==0) {
	                        System.out.println("[오류발생 : 정비소 주소] 정비소 주소를 입력해야 합니다.");
	                        isError = true;
	                    }
	                     
	                    //정비소 전화번호
	                    if(gar_phone.length()==0) {
	                        System.out.println("[오류발생 : 정비소 전화번호] 정비소 전화번호를 입력해야 합니다.");
	                        isError=true;
	                    }
	                    else {
	                        // phone 오류 확인
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
	                             System.out.println("[오류발생 : 정비소 전화번호] 전화번호는 XXX-XXXX-XXXX 여야 합니다.(X의 수는 3,4,4로 제한, X는 숫자여야함)");
	                            isError=true;
	                          }
	                     }
	                     
	                     //담당자 이름
	                     if(gar_person.length()==0) {
	                        System.out.println("[오류발생 : 담당자 이름] 담당자 이름을 입력해야 합니다.");
	                        isError=true;
	                     }
	                     
	                     //정비소 이메일
	                     if(gar_email.length()==0) {
	                        System.out.println("[오류발생 : 이메일] 이메일을 입력해야 합니다.");
	                        isError = true;
	                     }
	                     else {
	                         // email 오류 확인
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
	                            System.out.println("[오류발생 : 이메일] 이메일은 XXX@XXX.XXX 여야 합니다.(X의 수는 제한 없음)");
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
	                       System.out.println("정비소 입력 완료되었습니다.");
	                       
	                       query="SELECT * FROM garage";
		                   rs=stmt.executeQuery(query);
			               rs.last();
			                 
			               System.out.println("캠핑카 정비소 ID \t 정비소명 \t 정비소 주소 \t정비소 전화번호 \t담당자 이름 \t이메일 ");
			               String str = rs.getInt(1) +"\t"+ rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + 
			                                rs.getString(5)+"\t"+rs.getString(6) ;
			                      
			               System.out.println(str);
	                    
	                    }
	                     
	                }
	                
	                //정비소 삭제
	                else if(e.getSource()==delete_gar) {
	                	System.out.println("정비소 삭제버튼 클릭!");
	                	
	                	String gar_idS = gar_idf.getText();
	                    
	                    if(gar_idS.length()==0) {
	                   		System.out.println("[오류발생 : 데이터 입력 없음] 정비소 ID에 데이터를 입력해주세요.");
	                   		
	                    }
	                    else {
	                    	if(!CheckNumber(gar_idS)) {
		                		System.out.println("[오류발생 : 정비소 ID] 정비소 ID는 양의 정수여야 합니다.");
		                	}
	                    	else {
			                   int gar_idI = Integer.parseInt(gar_idS);
			                   query = "SELECT * FROM garage WHERE gar_id =" + gar_idI;
			                   rs = stmt.executeQuery(query);
			              
			                 
			                 if(!rs.next()) { 
			                    System.out.println("[오류발생 : 캠핑카 정비소 ID] 해당 ID의 정비소가 없습니다.");
			                 }
			                 else {
		                
			                    query = "DELETE FROM repair WHERE Garage_gar_id = " + gar_idI;
			                    stmt.executeUpdate(query);
			                    
			                    query = "DELETE FROM garage WHERE gar_id = " + gar_idI;
			                    stmt.executeUpdate(query);   
			                                                       
			                    
			                    System.out.println("해당 ID의 정비소 삭제가 완료되었습니다.");
			                    
			     
			                    
			                       query="SELECT * FROM garage WHERE gar_id >=" + 0;
			                       rs = stmt.executeQuery(query);
			
			                       
			                       System.out.println("정비소 ID \t 정비소이름 ----생략----");
			
			                       while(rs.next()) {
			                          System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+" 나머지는 생략 -----");
			                          
			                       }
			                 }
	                    }
	                 }
	                }
	                
	                //정비소 변경버튼
	                else if(e.getSource()==convert_gar) {
	                	System.out.println("정비소 변경버튼 클릭!");
	                	
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
		                		System.out.println("[오류발생 : 데이터 입력 없음] 정비소 ID칸과 바꾸고싶은 속성칸에 데이터를 입력해주세요.");
		                   		isError=true;
		                }
	                     else { 
	                     
		                     //정비소명
		                     if(gar_name.length()!=0) {
		                       name=true;
		                     }
		                     
		                     //정비소 주소
		                     if(gar_address.length()!=0) {
		                        address=true;
		                     }
		                     
		                     // phone 오류 확인
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
						            	 System.out.println("[오류발생 : 전화번호] 전화번호는 XXX-XXXX-XXXX 여야 합니다.(X의 수는 3,4,4로 제한, X는 숫자여야함)");
						        		 isError=true;
						             }
						             else {
						            	 phone=true;
						             }
			                    }
			                    
					            // email 오류 확인
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
						            	 System.out.println("[오류발생 : 이메일] 이메일은 XXX@XXX.XXX 여야 합니다.(X의 수는 제한 없음)");
						        		 isError=true;
						             }
						             else {
						            	 email=true;
						             }
				                   
			                    }
			                   
		                     
		                     //담당자 이름
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
				        		
				        		
				        		System.out.println("해당 ID의 정비소 정보가 변경이 완료되었습니다.");
				        		query="SELECT * FROM garage WHERE gar_id > '0'";
			                    rs = stmt.executeQuery(query);
		
			                    
			                    System.out.println("정비소ID \t 이름 ----생략----");
		
			                    while(rs.next()) {
			                       System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t 나머지는 생략 -----");
			                    }
		                    }
	                     }
	                }
	                
	                //반환
	                else if(e.getSource() == return_car) {
	                      String inspec_rents = inspec_rentf.getText(); //대여번호                      
	                      String inspec_cars = inspec_carf.getText(); // 캠핑카 등록 ID
	                      String inspec_fronts = inspec_frontf.getText();
	                      String inspec_lefts = inspec_leftf.getText();
	                      String inspec_rights = inspec_rightf.getText();
	                      String inspec_behinds = inspec_behindf.getText();
	                      String inspec_repairs = inspec_repairf.getText(); // 수리필요여부 필요 1,불필요 0
	                      int inspec_carI;
	                      int inspec_rentI;
	                      int inspec_repairI;
	                      
	                      Boolean isError = false;
	                      
	                      System.out.println("반환/점검내역 저장버튼 클릭!");
	                      
	                   
	                      if(inspec_rents.length()==0||inspec_cars.length()==0||inspec_fronts.length()==0||inspec_lefts.length()==0
	                            ||inspec_rights.length()==0||inspec_behinds.length()==0||inspec_repairs.length()==0) {
	                         System.out.println("[오류 발생] 모든 칸에 데이터를 입력해야 합니다.");
	                         isError= true;
	                      }
	                      else { 
	                         if(!CheckNumber(inspec_repairs) || !CheckNumber(inspec_cars) || !CheckNumber(inspec_rents)
	                               || !inspec_repairs.contentEquals("0") && !inspec_repairs.contentEquals("1")) {
	                            System.out.println("[오류 발생] 대여번호, 캠핑카 등록ID, 수리필요여부는 0보다 큰 정수로만 입력해야 합니다.");
	                            System.out.println("단, 수리필요여부는 필요하다면 1, 불필요하다면 0을 입력해야 합니다.");
	                            isError = true;
	                         }
	                         else {
	                            inspec_carI= Integer.parseInt(inspec_cars); //캠핑카 등록ID
	                            inspec_rentI = Integer.parseInt(inspec_rents); //대여번호
	                                 
	                           query = "SELECT * FROM rent WHERE Car_car_id =" + inspec_carI;
	                           rs = stmt.executeQuery(query);
	                                  
	                           if(!rs.next()) { 
	                              System.out.println("[오류발생 : 캠핑카 ID] 해당 ID의 캠핑카가 없습니다.");
	                              isError = true;
	                            }
	                                
	                           query = "SELECT * FROM rent WHERE rent_id =" + inspec_rentI;
	                           rs = stmt.executeQuery(query);
	                                  
	                           if(!rs.next()) { 
	                                System.out.println("[오류발생 : 대여 번호ID] 해당 대여 번호가 없습니다.");
	                                isError = true;
	                           }
	                                
	                          
	                           query = "select * from rent where rent_id = '"+inspec_rents+"'";
	                           rs = stmt.executeQuery(query);
	                           if(!rs.next()) { 
	                              System.out.println("[오류발생] 해당 대여 번호와 캠핑카ID에 맞는 대여 기록이 없습니다.");
	                              isError = true;
	                          }
	                           else {
	                        	   if(rs.getInt(8)!=Integer.parseInt(inspec_cars)) {
	                        		   System.out.println("[오류발생] 해당 대여 번호와 캠핑카ID에 맞는 대여 기록이 없습니다.");
	                        		   isError = true;
	                        	   }
	                           }
	                           
	                           //중복오류
	                           query = "select * from inspection where Rent_rent_id = "+inspec_rentI;
	                           rs= stmt.executeQuery(query);
	                           
	                           while(rs.next()) {
			                       if(rs.getString(1).length()!=0) {
			                          System.out.println("[오류발생 : 대여번호] 이미 점검내역에 등록되어 있습니다.");
			                          isError = true;
			                       }
			                   }
	                               
	                          }
	                         
	                       }

	                      
	                      if(!isError) {
	                         
	                         inspec_repairI = Integer.parseInt(inspec_repairs);
	                         inspec_carI= Integer.parseInt(inspec_cars); //캠핑카 등록ID
	                         inspec_rentI = Integer.parseInt(inspec_rents); //대여번호
	                     
	                         query = "insert into inspection(frontinfo,leftinfo,rightinfo,behindinfo,repairinfo,Rent_rent_id,Rent_Car_car_id) values('"+ inspec_fronts +"','"+inspec_lefts+"','"+
	                                inspec_rights+"','"+inspec_behinds+"',"+inspec_repairI+","+inspec_rentI+","+inspec_carI+")";
	                                stmt.executeUpdate(query); 
	                         
	                         if(inspec_repairI==0) //수리필요하지않다면 다시 차 대여 가능
	                            carBoolean[inspec_carI]=true;
	                         
	                         System.out.println("반환/점검내역 저장 완료");
	                         if(inspec_repairI==1) { // 수리필요하면 정비내역 작성 필요
	                        	 repairBoolean[inspec_carI]=true;
	                         }
	                         
	                         query="SELECT * FROM inspection";
		                     rs=stmt.executeQuery(query);
			                 rs.last();
			                 
			                 System.out.println("앞부분 설명 \t 왼쪽 설명 \t 오른쪽 설명 \t 뒤쪽 설명 \t수리필요 여부 \t고유 대여 번호 \t캠핑카 등록 ID ");
			                 String str = rs.getString(1) +"\t"+ rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + 
			                                rs.getInt(5)+"\t" + rs.getInt(6) +"\t" + rs.getInt(7);
			                      
			                 System.out.println(str);
	                     }
	               }
	                
	                //정비내역 입력
	                else if(e.getSource()==request_car) {
	                	System.out.println("정비내역 입력 버튼 클릭!");
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
	                	
	                	// 빈 곳이 없는지 확인
	                	if(car_idS.length()==0 || gar_idS.length()==0 || comp_idS.length()==0 || cust_idS.length()==0 || infoS.length()==0 
	                			|| dateS.length()==0 || priceS.length()==0 ||paydateS.length()==0 || extraS.length()==0) {
	                		System.out.println("[오류발생 : 데이터 입력 없음] 모든 칸에 데이터를 입력해주세요.");
	                		isError=true;
	                	}
	                	else {
	                		// 차 ID 오류확인
	                		if(CheckNumber(car_idS)) {
		                    	 car_id = Integer.parseInt(car_idS);
			                     query = "SELECT * FROM car WHERE car_id =" + car_id;
			                     rs = stmt.executeQuery(query);
			                     if(!rs.next()) { 
			                        System.out.println("[오류발생 : 캠핑카 ID] 해당 ID의 캠핑카가 없습니다.");
			                        isError=true;
			                        carError=true;
			                     }
			                     else {
			                    	 int i = rs.getInt(1);
			                    	 if(repairBoolean[i]==false) {
			                    		 System.out.println("[오류발생 : 수리불필요 캠핑카] 해당 ID의 캠핑카는 수리 불필요합니다.");
			 	                        isError=true;
			 	                        carError=true;
			                    	 }
			                     }
		                     }
		                     else {
		                    	 isError=true;
		                    	 carError=true;
		                    	 System.out.println("[오류발생 : 캠핑카 ID] 캠핑카 ID는 양의 정수여야 합니다.");
		                     }
	                		
	                		// 정비소 ID 오류확인
	                		if(!CheckNumber(gar_idS)) { 
		                		isError=true;
		                		System.out.println("[오류발생 : 정비소 ID] 정비소 ID는 양의 정수입니다.");
		                	}
		                	else{
		                		gar_id=Integer.parseInt(gar_idS);
		                		query = "SELECT * FROM garage WHERE gar_id =" + gar_id;
					        	 rs = stmt.executeQuery(query);
					        	 if(!rs.next()) { 
					        		 System.out.println("[오류발생 : 정비소 ID] 정비소 ID의 회사가 없습니다.");
					        		 isError=true;
					        	 }
				        	 }
	                		
	                		
		                	// 회사 ID 오류 확인
		                	if(!CheckNumber(comp_idS)) { 
		                		isError=true;
		                		compError=true;
		                		System.out.println("[오류발생 : 회사 ID] 회사 ID는 양의 정수입니다.");
		                	}
		                	else{
		                		comp_id=Integer.parseInt(comp_idS);
		                		query = "SELECT * FROM company WHERE comp_id =" + comp_id;
					        	 rs = stmt.executeQuery(query);
					        	 if(!rs.next()) { 
					        		 System.out.println("[오류발생 : 회사 ID] 해당 ID의 회사가 없습니다.");
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
					                   		 System.out.println("[오류발생 : 회사 ID] 캠핑카 ID와 회사 ID가 서로 맞지않습니다.");
					                    	 }
					                     }
					        		 }
					        	 }
				        	 }
		                	
		                	// 고객 운전면허증 번호 오류 확인		
		                	if(!(compError||carError)) {
			                	query = "SELECT * FROM rent WHERE Car_car_id = '" + car_idS +"'";
			        			rs = stmt.executeQuery(query);
			        			
			        			if(rs.next()) {
			        				rs.last();
			        				if(!rs.getString(10).contentEquals(cust_idS)) {
				        				System.out.println("[오류발생 : 운전면허번호] 운전면허번호가 일치하지 없습니다.");
				                        isError=true;
			                        }
			        			}
		                	}
		                     
		                	// 수리날짜 오류 확인
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
						            	 System.out.println("[오류발생 : 수리날짜] YYYY-MM-DD에서 MM은 1~12사이, DD는 1~31사이의 수여야 합니다.");
						        		 isError=true;
						        		 dateError=true;
						             }
					             }
					             else { 
					            	 isError=true;
					            	 dateError=true;
					            	 System.out.println("[오류발생 : 수리날짜] YYYY-MM-DD에서 MM은 1~12사이, DD는 1~31사이의 수여야 합니다.");
					             }
				             }
				             else {
				            	 System.out.println("[오류발생 : 수리날짜] YYYY-MM-DD에서 MM은 1~12사이, DD는 1~31사이의 수여야 합니다.");
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
				        				date1 = fm.format(rs.getDate(5)); //대여테이블에서 대여 납입기한
				        			}
				        			
				            	 
				            	 if(dateS.compareTo(date1)<=0) {
		                              System.out.println("[오류발생 : 수리날짜] 수리날짜는 대여비용납입기한 보다 나중이어야합니다.");
		                              isError = true;
		                              dateError=true;
		                       }
				            	 
				            	 
				             }
				             
				             
		                	// 수리비용 오류 확인
				             if(!CheckNumber(priceS)) { 
			                		isError=true;
			                		System.out.println("[오류발생 : 수리비용] 수리비용은 양의 정수입니다.");
			                }
			                else {
			                	price = Integer.parseInt(priceS);
				                if(price < 0) {
				                	System.out.println("[오류발생 : 수리비용] 수리비용은 0이상 이어야 합니다.");
				                	isError=true;
				               	}
			                }
				    	
		                	
		                	// 납입기한 오류 확인
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
						            	 System.out.println("[오류발생 : 납입기한] YYYY-MM-DD에서 MM은 1~12사이, DD는 1~31사이의 수여야 합니다.");
						        		 isError=true;
						        		 date1Error=true;
						             }
					             }
					             else { 
					            	 isError=true;
					            	 date1Error=true;
					            	 System.out.println("[오류발생 : 납입기한] YYYY-MM-DD에서 MM은 1~12사이, DD는 1~31사이의 수여야 합니다.");
					             }
				             }
				             else {
				            	 System.out.println("[오류발생 : 납입기한] YYYY-MM-DD에서 MM은 1~12사이, DD는 1~31사이의 수여야 합니다.");
				            	 isError=true;
				            	 date1Error=true;
				             }
				             if(!date1Error && !dateError) {
				            	 if(paydateS.compareTo(dateS)<=0) {
		                         System.out.println("[오류발생 : 납입기한] 납입기한은 수리날짜 보다 나중이어야합니다.");
		                         isError = true;
		                          date1Error=true;
		                       }
				             }
				             
				             // 오류가 없으면 삽입 
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
			                       System.out.println("정비내역 입력 완료되었습니다.");
			                       System.out.println("정비ID \t 정비 정보 \t ---- 나머지 생략 ----");
			                       String str = rs.getInt(1) +"\t" + rs.getString(2) ;
			                      
			                       System.out.println(str);
			                      
			                    }
	                	}
	                	
	                }
	                else if(e.getSource()==repair_delete) {
	                	System.out.println("정비내역 삭제 버튼 클릭!");
	                	Boolean isError=false;
	                	String repair_idS = repair_idf.getText();
	                	if(repair_idS.length()==0) {
	                		System.out.println("[오류발생 : 정비번호 입력 없음] 정비 번호를 입력해야합니다.");
	                		isError=true;
	                	}
	                	else {
	                		if(CheckNumber(repair_idS)) {
	                			query = "SELECT * FROM repair WHERE repair_id = '" + repair_idS +"'";
			        			rs = stmt.executeQuery(query);
			        			if(!rs.next()) {
			        				System.out.println("[오류발생 : 정비번호] 해당 정비 번호의 정비내역이 없습니다.");
		                			isError=true;
			        			}
	                		}
	                		else {
	                			System.out.println("[오류발생 : 정비번호] 정비 번호는 양의정수이어야 합니다.");
	                			isError=true;
	                		}
	                	}
	                	if(!isError) {
	                		query = "DELETE FROM repair WHERE repair_id = '" + repair_idS + "'";
		                    stmt.executeUpdate(query);   
		                                                       
		                    
		                    System.out.println("해당 ID의 정비내역 삭제가 완료되었습니다.");
		                    
		                    
		                    query="SELECT * FROM repair WHERE repair_id >=" + 0;
		                       rs = stmt.executeQuery(query);
		
		                       
		                       System.out.println("정비내역 ID \t 정비내역 ----생략----");
		
		                       while(rs.next()) {
		                          System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+" 나머지는 생략 -----");
		                          
		                       }
	                	}
	                }
	                
	                else if(e.getSource()==repair_convert) {
	                	System.out.println("정비내역 변경 버튼 클릭!");

	                	String repair_ids = repair_idf.getText();//정비내역 ID(PK)
	                	String repair_cars = repair_carf.getText(); // 캠핑카 등록ID (FK)
	                	String repair_gars = repair_garf.getText(); //정비소ID (FK)
	                	String repair_corps = repair_corpf.getText(); //회사ID (FK)
	                	String repair_custs = repair_custf.getText(); //고객  운전면허증번호(FK)
	                	String repair_infos = repair_infof.getText(); // 정비내역
	                	String repair_dates = repair_datef.getText(); //수리날짜
	                	String repair_prices = repair_pricef.getText(); // 수리비용
	                	String repair_paydates = repair_paydatef.getText(); //납입 기한
	                	String repair_extras = repair_extraf.getText(); //기타 정비 내역 정보
	                	
	                	Boolean isError = false;
	                	Boolean dateError = false;
	                	
	                	Boolean date1 = false; //수리날짜
	                	Boolean date2 = false; //납입기한
	                	Boolean price = false; //수리 비용
	                	Boolean info = false; //정비내역
	                	Boolean extra = false; //기타 정비 내역
	                	
	                	int car_idI=0;
	                	int repair_idI = 0;
	                	int repair_priceI = 0;
	                	
	                	String Y, M, D;
			            int y,m,d;
	                	StringTokenizer st;
	                	
	                	
	                	//빈 곳 있는지 확인, 비어있는지 확인
	                	if(repair_ids.length()==0 || repair_cars.length()!=0|| repair_gars.length()!=0|| repair_corps.length()!=0||
	                			repair_custs.length()!=0 ||(repair_infos.length()==0 && repair_dates.length()==0 
	                			&& repair_prices.length()==0 && repair_paydates.length()==0 && repair_extras.length()==0)) {
	                		System.out.println("[오류발생] 정비내역 ID칸과 바꾸고싶은 속성칸에 데이터를 입력해야 합니다.");
	                		System.out.println("또한, 정비소ID, 대여회사ID, 고객 운전면허증번호, 캠핑카 등록ID는 비워야 합니다.");
	                		isError = true;
	                	}
	                	else {
	                		//정비내역 ID 오류
	                		if(repair_ids.length()!=0) {
	                			repair_idI = Integer.parseInt(repair_ids);
	                			query = "select * from repair where repair_id ="+repair_idI;
	                			rs = stmt.executeQuery(query);
	                			
	                			if(!rs.next()){
	                				System.out.println("[오류발생: 정비내역ID] 해당 정비내역ID를 가진 정비내역은 없습니다.");
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
			               		
				                     // 수리 날짜 오류
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
									            	 System.out.println("[오류발생 : 수리날짜] YYYY-MM-DD에서 MM은 1~12사이, DD는 1~31사이의 수여야 합니다.");
									        		 isError=true;
									        		 dateError=true;
									             }
								             }
								             else { 
								            	 isError=true;
								            	 dateError=true;
								            	 System.out.println("[오류발생 : 수리날짜] YYYY-MM-DD에서 MM은 1~12사이, DD는 1~31사이의 수여야 합니다.");
								             }
							             }
							             else {
							            	 System.out.println("[오류발생 : 수리날짜] YYYY-MM-DD에서 MM은 1~12사이, DD는 1~31사이의 수여야 합니다.");
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
							        			
							            	 
							            	 if(repair_dates.compareTo(datel)<0) { //대여테이블 대여비용납입기한
					                              System.out.println("[오류발생 : 수리날짜] 수리날짜는 대여비용납입기한 보다 나중이어야합니다.");
					                              isError = true;
					                              dateError=true;
					                       }
							             } 
							            
				                  	}	 
				                  	
				                  	//납입 기한 오류
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
									            	 System.out.println("[오류발생 : 납입기한] YYYY-MM-DD에서 MM은 1~12사이, DD는 1~31사이의 수여야 합니다.");
									        		 isError=true;
									        		 date1Error=true;
									             }
								             }
								             else { 
								            	 isError=true;
								            	 date1Error=true;
								            	 System.out.println("[오류발생 : 납입기한] YYYY-MM-DD에서 MM은 1~12사이, DD는 1~31사이의 수여야 합니다.");
								             }
							             }
							             else {
							            	 System.out.println("[오류발생 : 납입기한] YYYY-MM-DD에서 MM은 1~12사이, DD는 1~31사이의 수여야 합니다.");
							            	 isError=true;
							            	 date1Error=true;
							             }
							             if(!dateError) {
							            	 date2=true;  	 
							             }
				                	}
				                	
				                	// 수리 날짜, 납입기한 
				                	if(!date1 && date2 && !isError) { //date1: 수리날짜, date2: 납입기한(정비내역) 수리날짜가 없고, 납입기한만 입력받음
				                		
				                		query = "select repair_date from repair where repair_id ="+repair_idI; //이미 입력된 수리날짜
							        	 rs=stmt.executeQuery(query);
							        	 
							        	 if(!rs.next()) {
							        		 System.out.println("[오류발생: 정비내역ID] 해당 정비내역ID를 가진 정비내역은 없습니다.");
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
								             
								             st = new StringTokenizer(repair_paydates,"-"); //납입기한
								             Y1=st.nextToken();
								             M1=st.nextToken();
									         D1=st.nextToken();
										     y1=Integer.parseInt(Y1);
										     m1=Integer.parseInt(M1);
										     d1=Integer.parseInt(D1);
										     
										     if(y1<y2) { //y2 : 수리날짜, y1 : 납입기한
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
										    	 System.out.println("[오류발생 : 납입 기한과 수리날짜] 납입 기한은 이미 등록된 수리날짜 보다 나중이어야합니다.");
										     }
							        	 }
							        	
				                	}
							         
				                	//수리날짜, 납입기한
				                	if(date1 && !date2 && !isError) { //date1: 수리날짜, date2: 납입기한 / 수리날짜만 입력받음
				                		query = "select repair_paydate from repair where repair_id ="+repair_idI;
							        	 rs=stmt.executeQuery(query);
							        	
							        	 if(!rs.next()) {}
							        	else {
							        		 String da =rs.getString(1); // 이미 가지고 있는 납입기한
								        	 
								        	 String Y2, M2, D2;
								             int y2,m2,d2;
								             
								             st = new StringTokenizer(repair_dates,"-"); //수리날짜
								             Y2=st.nextToken();
								             M2=st.nextToken();
									         D2=st.nextToken();
										     y2=Integer.parseInt(Y2);
										     m2=Integer.parseInt(M2);
										     d2=Integer.parseInt(D2);
							            	 
							            	 
							            	 String Y1, M1, D1;
								             int y1,m1,d1;
								             
								             st = new StringTokenizer(da,"-"); //납입기한
								             Y1=st.nextToken();
								             M1=st.nextToken();
									         D1=st.nextToken();
										     y1=Integer.parseInt(Y1);
										     m1=Integer.parseInt(M1);
										     d1=Integer.parseInt(D1);
										     
										     if(y1<y2) { //y1: 납입기한, y2: 수리날짜
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
										    	 System.out.println("[오류발생 : 수리 날짜와 납입 기한] 이미 등록된 납입기한이 수리날짜 보다 나중이어야합니다.");
										     }
							        	 }
									 }
				               
				                	
				                	if(date1 && date2 && !isError) { //date1: 수리날짜, date2: 납입기한
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
									     
									     if(y1<y2) { //y1 : 납입기한, y2: 수리날짜
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
									    	 System.out.println("[오류발생 : 납입기한과 수리날짜] 납입기한은 수리날짜 보다 나중이어야합니다.");
									     }
				                	 }
				                	
				                	
				                	// 수리 비용
				                	if(repair_prices.length()!=0) {
				                		if(!CheckNumber(repair_prices)) {
				                			System.out.println("[오류 발생 : 수리 비용] 수리비용은 양의 정수로 입력해야 합니다.");
				                			isError = true;
				                		}
				                	
				                	}
	                			}
	                		
	                		}
	                
	                		
	                	}
	                	
	                	if(!isError) {
	               
	                		if(date1) { //수리날짜
	                			query = "UPDATE repair SET repair_date='"+repair_dates+"' WHERE repair_id=" + repair_idI;
				        		stmt.executeUpdate(query);
	                		}
		                	if(date2) { //납입기한
		                		query = "UPDATE repair SET repair_paydate='"+repair_paydates+"' WHERE repair_id=" + repair_idI;
				        		stmt.executeUpdate(query);
		                	}
		                	if(price) { //수리비용
		                		query = "UPDATE repair SET repair_price="+repair_priceI+" WHERE repair_id=" + repair_idI;
				        		stmt.executeUpdate(query);
		                	}
		                	if(info) { //정비내역
		                		query = "UPDATE repair SET repair_info='"+repair_infos+"' WHERE repair_id=" + repair_idI;
				        		stmt.executeUpdate(query);
		                	}
		                	if(extra) { //기타정비내역
		                		query = "UPDATE repair SET repair_extra='"+repair_extras+"' WHERE repair_id=" + repair_idI;
				        		stmt.executeUpdate(query);
		                	}
		                	
		                	System.out.println("해당 ID의 정비내역 변경이 완료되었습니다.");
			        		query="SELECT * FROM repair WHERE repair_id >=" + 0;
		                    rs = stmt.executeQuery(query);
	
		                    
		                    System.out.println("정비내역 ID \t 정비 내역 ----생략----");
	
		                    while(rs.next()) {
		                       System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t 나머지는 생략 -----");
		                       
		                    }
	                	}
	                }
	                   
	                 
	             // 검색1 고객정보
	                else if(e.getSource() == s1b) {
	                	String s1_con1s = s1_con1f.getText(); //대여기간
	                	String s1_con2s = s1_con2f.getText(); //차량 승차 인원수
	                	boolean isError=false;
	                	
	                	System.out.println("검색1버튼 클릭");
	                	
	                	if(s1_con1s.length()==0 || s1_con2s.length()==0) {
	                		System.out.println("[오류발생] 검색1 조건을 모두 적어야 합니다.");
	                		isError = true;
	                	}
	                	else {
	                		if(!CheckNumber(s1_con1s) || !CheckNumber(s1_con2s)) {
	                			System.out.println("[오류발생] 대여기간과 차량 승차 인원수는 숫자로 적어야 합니다.");
	                			isError = true;	
	                		}
	                		else {
	                			int s1_con1I =  Integer.parseInt(s1_con1s);
	                			int s1_con2I =  Integer.parseInt(s1_con2s);
	                			
	                			if(s1_con1I ==0) {
	                				System.out.println("[오류발생] 대여기간은 0보다 커야 합니다.");
	                				isError = true;
	                			}
	                			
	                			if(s1_con2I==0) {
	                				System.out.println("[오류발생] 승차인원수는 0보다 커야 합니다.");
	                				isError = true;
	                			}
	                			
	                			query = "select Customer_cust_id from rent where rent_period >= "+s1_con1I;
	                			rs = stmt.executeQuery(query);
	                			if(!rs.next()) {
	                				System.out.println("[대여기간] 해당 조건에 맞는 고객이 없습니다.");
	                				isError = true;
	                			}
	                			
	                			query = "select car_id from car where car_board >= "+s1_con2I;
	                			rs = stmt.executeQuery(query);
	                			if(!rs.next()) {
	                				System.out.println("[차량 승차인원수] 해당 조건에 맞는 고객이 없습니다.");
	                				isError = true;
	                			}
	                			
	                		}
	                	}
	                	
	                	
	                	if(!isError) {
	                		System.out.println("검색1이 완료되었습니다.");
	                		
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
    	                     result.setText("운전면허증번호\t 고객명 \t고객 주소 \t고객 전화번호 \t고객 이메일  \n");
    	                     
    	                     while(rs.next()) {                    
    	                        String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + 
    	                              rs.getString(4) +  "\t" + rs.getString(5) +"\n";
    	                        result.append(str);
    	                     }
	                	}
	                }
	                
	                //검색2 캠핑카정보
	                else if(e.getSource() == s2b) {
	                	String s2_con1s = s2_con1f.getText(); //수리여부판단
	                	String s2_con2s = s2_con2f.getText(); //대여기간
	                	boolean isError=false;
	                	
	                	System.out.println("검색2버튼 클릭");
	                	
	                	if(s2_con1s.length()==0 || s2_con2s.length()==0) {
	                		System.out.println("[오류발생] 검색2 조건을 모두 적어야 합니다.");
	                		isError = true;
	                	}
	                	else {
	                		if(!CheckNumber(s2_con1s) || !CheckNumber(s2_con2s)) {
	                			System.out.println("[오류발생] 수리여부판단과 대여기간은 숫자로 적어야 합니다.");
	                			isError = true;
	                		}
	                		else {
	                			
	                			int s2_con1I =  Integer.parseInt(s2_con1s); //수리여부판단
	                			
	                			Boolean b;
	                			if(s2_con1I==1) b= true;
	                			else b=false;
	                			
	                			int s2_con2I =  Integer.parseInt(s2_con2s); //대여기간
	                			if(s2_con2I>0) {
	                				query = "select Rent_rent_id from inspection where repairinfo = "+b;
		                			rs = stmt.executeQuery(query);
		                			if(!rs.next()) {
		                				System.out.println("[수리여부] 해당 조건에 맞는 캠핑카가 없습니다.");
		                				isError = true;
		                			}
		                			
		                			query = "select Car_car_id from rent where rent_period >= "+s2_con2I;
		                			rs = stmt.executeQuery(query);
		                			if(!rs.next()) {
		                				System.out.println("[대여기간] 해당 조건에 맞는 캠핑카가 없습니다.");
		                				isError = true;
		                			}
	                			}
	                			else {
	                				System.out.println("[오류발생] 대여기간은 0보다 큰 숫자여야 합니다.");
	                				isError = true;
	                			}
	                	
	                			
	                		}
	                	}
	                	
	                	
	                	if(!isError) {
	                		System.out.println("검색2가 완료되었습니다.");
	                		
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
    	                     result.setText("대여가능여부 \t 캠핑카ID \t차이름 \t차량번호 \t승차인원수  \t제조회사 \t제조연도 \t누적주행거리 \t대여비용 \t등록일자 \t대여회사ID \n");
    	                     
    	                     while(rs.next()) {                    
    	                        String str = carBoolean[rs.getInt(1)] + "\t" + rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + 
    	                              rs.getInt(4) +  "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" +
    	                              rs.getInt(7) + "\t" + rs.getInt(8)+"\t" + rs.getDate(9)+"\t" + rs.getInt(10)+"\n";
    	                        result.append(str);
    	                       
    	                     }
	                	}
	                }
	                
	                //검색3 캠핑카정보
	                else if(e.getSource() == s3b) {
	                	String s3_con1s = s3_con1f.getText(); //누적주행거리
	                	String s3_con2s = s3_con2f.getText(); //대여기간
	                	boolean isError=false;
	                	
	                	System.out.println("검색3버튼 클릭");
	                	
	                	if(s3_con1s.length()==0 || s3_con2s.length()==0) {
	                		System.out.println("[오류발생] 검색3 조건을 모두 적어야 합니다.");
	                		isError = true;
	                	}
	                	else {
	                		if(!CheckNumber(s3_con1s) || !CheckNumber(s3_con2s)) {
	                			System.out.println("[오류발생] 누적주행거리와 대여기간은 숫자로 적어야 합니다.");
	                			isError = true;
	                		}
	                		else {
	                			int s3_con1I =  Integer.parseInt(s3_con1s); //누적주행거리
	                			int s3_con2I =  Integer.parseInt(s3_con2s); //대여기간
	                			
	                			if(s3_con2I>0) {
	                				query = "select * from car where car_distance >="+s3_con1I;
		                			rs = stmt.executeQuery(query);
		                			if(!rs.next()) {
		                				System.out.println("[오류발생 : 누적주행거리] 해당 조건에 맞는 고객이 없습니다.");
		                				isError = true;
		                			}
		                			
		                			query = "select Car_car_id from rent where rent_period >= "+s3_con2I;
		                			rs = stmt.executeQuery(query);
		                			if(!rs.next()) {
		                				System.out.println("[오류발생 : 대여기간] 해당 조건에 맞는 고객이 없습니다.");
		                				isError = true;
		                			}
	                			}
	                			else {
	                				System.out.println("[오류발생 : 대여기간] 대여기간은 0보다 큰 숫자로 해야합니다.");
	                				isError = true;
	                			}
	                		
	                		}
	                	}
	                	
	                	
	                	if(!isError) {
	                		System.out.println("검색3이 완료되었습니다.");
	                		
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
    	                     result.setText("대여가능여부 \t 캠핑카ID \t차이름 \t차량번호 \t승차인원수  \t제조회사 \t제조연도 \t누적주행거리 \t대여비용 \t등록일자 \t대여회사ID \n");
    	                     
    	                     while(rs.next()) {                    
    	                        String str = carBoolean[rs.getInt(1)] + "\t" + rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + 
    	                              rs.getInt(4) +  "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" +
    	                              rs.getInt(7) + "\t" + rs.getInt(8)+"\t" + rs.getDate(9)+"\t" + rs.getInt(10)+"\n";
    	                        result.append(str);
    	                       
    	                     }
	                	}
	                }
	                
	                // 검색4 고객정보
	                else if(e.getSource() == s4b) {
	                	String s4_con1s = s4_con1f.getText(); //고객의 성 
	                	String s4_con2s = s4_con2f.getText(); //수리비용
	                	boolean isError=false;
	                	
	                	System.out.println("검색4버튼 클릭");
	                	
	                	if(s4_con1s.length()==0 || s4_con2s.length()==0) {
	                		System.out.println("[오류발생] 검색4 조건을 모두 적어야 합니다.");
	                		isError = true;
	                	}
	                	else {
	                		if( CheckNumber(s4_con1s) || !CheckNumber(s4_con2s)) {
	                			System.out.println("[오류발생] 고객의 성은 문자로 수리비용은 숫자로 적어야 합니다.");
	                			isError = true;
	                		}
	                		else {
	                			int s4_con2I =  Integer.parseInt(s4_con2s); //수리비용
	                			
	                			if(s4_con2I>0) {
	                				query = "select * from customer where cust_name Like'"+s4_con1s+"%'";
	    	                		
		                			rs = stmt.executeQuery(query);
		                			if(!rs.next()) {
		                				System.out.println("[고객의 성] 해당 조건에 맞는 고객이 없습니다.");
		                				isError = true;
		                			}
		                			
		                			query = "select * from repair where repair_price >= "+s4_con2I;
		                			rs = stmt.executeQuery(query);
		                			if(!rs.next()) {
		                				System.out.println("[수리비용] 해당 조건에 맞는 고객이 없습니다.");
		                				isError = true;
		                			}
	                			}
	                			else {
	                				System.out.println("[오류발생] 수리비용은 0보다 큰 숫자로 입력해야 합니다.");
	                				isError = true;
	                			}
	                			
	                			
	                		}
	                	}
	                	
	                	
	                	if(!isError) {
	                		System.out.println("검색4가 완료되었습니다."); 	
                			int s4_con2I =  Integer.parseInt(s4_con2s);
	                		
                			query ="select * from customer where cust_name like '"+s4_con1s+"%' And "
                                    + "cust_id IN(select Customer_cust_id from repair where repair_price >="+s4_con2I+")";

                			
                			 rs = stmt.executeQuery(query);
                			 
    	                     result.setText("");
    	                     result.setText("운전면허증번호\t 고객명 \t고객 주소 \t고객 전화번호 \t고객 이메일  \n");
    	                     
    	                     while(rs.next()) {                    
    	                        String str = rs.getString(1) + "\t" + rs.getString(2)  + "\t" + rs.getString(3) + "\t" + 
    	                              rs.getString(4) +  "\t" + rs.getString(5) +"\n";
    	                        result.append(str);
    	                     }
	                	}
	                }
	                  
	              
	            } catch (Exception e2) {
	                System.out.println("쿼리 읽기 실패 :" + e2);
	            }  
	         
	      }
	   }

   class Upanel extends JPanel implements ActionListener{
	      
	      JLabel user = new JLabel("일반사용자");
	      JTextArea resultArea = new JTextArea("");
	      JLabel b_num = new JLabel("승차 인원수");
	      JTextField b_n = new JTextField(3);
	      JLabel b_label = new JLabel("명 이상");
	      JButton searchB = new JButton("검색");
	      
	      JButton ableCarB = new JButton("대여가능한 캠핑카 보기");
	      
	      JLabel car_idl = new JLabel("캠핑카 ID");
	      JTextField car_idf = new JTextField(3);
	      JLabel comp_idl = new JLabel("캠핑카대여회사 ID");
	      JTextField comp_idf = new JTextField(3);
	      JLabel cust_idl = new JLabel("운전면허번호");
	      JTextField cust_idf = new JTextField(12);
	      JLabel start_datel = new JLabel("대여시작일");
	      JTextField start_datef = new JTextField(9);
	      JLabel periodl = new JLabel("대여기간");
	      JTextField periodf = new JTextField(3);
	      
	      JLabel pricel = new JLabel("청구요금");
	      JTextField pricef = new JTextField(6);
	      JLabel paydatel = new JLabel("납입기한");
	      JTextField paydatef = new JTextField(7);
	      JLabel extral = new JLabel("기타청구내역");
	      JTextField extraf = new JTextField(7);
	      JLabel extrafeel = new JLabel("기타청구요금정보");
	      JTextField extrafeef = new JTextField(5);
	      
	      JButton rentB = new JButton("대여");
	      
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
	                  System.out.println("검색!");
	                  String b_numS = b_n.getText();
	                  
	                  if(b_numS.length()==0) {
	                	  System.out.println("[오류발생: 승차인원수] 승차인원수에 숫자로 입력해야 합니다.");
	                  }
	                  else {
	                	   // 숫자로만 입력해야함
		                  if(!isNum(b_numS)) {
		                    System.out.println("[오류발생: 승차인원수] 승차인원수를 숫자로만 입력해야 합니다");
		                  }
		                  
		                  else {
		                     int b_numI = Integer.parseInt(b_numS);
		                     
		                     if(b_numI>0) {
		                    	   query="SELECT * FROM Car WHERE car_board >=" + b_numI;
				                     rs = stmt.executeQuery(query);

				                     resultArea.setText("");
				                     resultArea.setText("대여가능여부 \t 캠핑카ID \t차이름 \t차량번호 \t승차인원수  \t제조회사 \t제조연도 \t누적주행거리 \t대여비용 \t등록일자 \t대여회사ID \n");
				                     
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
		                    	 System.out.println("[오류발생 : 승차인원수] 승차인원수를 0보다 큰 숫자로 입력해야 합니다.");
		                     }
		               
		                    
		                  }
	                  }
	               
	               }
	               else if(e.getSource()==ableCarB) {
	            	   System.out.println("대여가능한 캠핑카보기!");
	                    
		                  query="SELECT * FROM Car";
		                  rs = stmt.executeQuery(query);
		                  resultArea.setText("");
		                  resultArea.setText("캠핑카ID \t차이름 \t차량번호 \t승차인원수  \t제조회사 \t제조연도 \t누적주행거리 \t대여비용 \t등록일자 \t대여회사ID \n");
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
	                  
	                  
	                  System.out.println("대여버튼 클릭!");
	                  
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
	                	  System.out.println("[오류발생 : 데이터 입력 없음] 모든 칸에 데이터를 입력해주세요.");
	                  }
	                  else {
	                	  
	                	//대여기간 오류 확인
	                	 if(CheckNumber(car_periodS)) {
	                		 int car_periodI = Integer.parseInt(car_periodS);
	                		 if(car_periodI>0) {}
		                     else {
		                    	 System.out.println("[오류발생 : 대여기간] 대여기간은 0보다 큰 숫자로 입력해야 합니다.");
		                    	 isError = true;
		                     }
	                	 }
	                	 else {
	                		 System.out.println("[오류발생 : 대여기간] 대여기간은 0보다 큰 숫자로 입력해야 합니다.");
	                		 isError = true;
	                	 }
	                     
	                     	                     
	                     // 캠핑카 ID 오류 확인
	                     if(CheckNumber(car_idS)) {
	                    	 int car_idI = Integer.parseInt(car_idS);
		                     query = "SELECT * FROM car WHERE car_id =" + car_idI;
		                     rs = stmt.executeQuery(query);
		                     if(!rs.next()) { 
		                        System.out.println("[오류발생 : 캠핑카 ID] 해당 ID의 캠핑카가 없습니다.");
		                        isError=true;
		                        carError=true;
		                     }
		                     else {
		                    	 
		                    	 int i = rs.getInt(1);
		                    	 if(carBoolean[i]==false) {
		                    		 System.out.println("[오류발생 : 대여불가능 캠핑카] 해당 ID의 캠핑카는 현재 대여 불가능입니다.");
		 	                        isError=true;
		 	                        carError=true;
		                    	 }
		                     }
	                     }
	                     else {
	                    	 isError=true;
	                    	 carError=true;
	                    	 System.out.println("[오류발생 : 캠핑카 ID] 캠핑카 ID는 양의 정수여야 합니다.");
	                     }
	                     
	                     // 캠핑카대여회사 ID 오류 확인
	                     if(CheckNumber(comp_idS)) {
	                    	 int car_idI = Integer.parseInt(car_idS);
	                    	 if(!carError) {
			                     query = "SELECT * FROM car WHERE car_id =" + car_idI;
			                     rs = stmt.executeQuery(query);
			                     if(rs.next()) {
			                    	 int i = rs.getInt(10); 
			                    	 if(Integer.parseInt(comp_idS)!=i) {
			                    		 isError=true;
			                   		 System.out.println("[오류발생 : 회사 ID] 캠핑카 ID와 회사 ID가 서로 맞지않습니다.");
			                    	 }
			                     }
			                   	
			                    	 
			                 }
	                    	 
	                     }
	                     else {
	                    	 System.out.println("[오류발생 : 회사 ID] 회사 ID는 양의 정수여야 합니다.");
	                     }

	                     // 운전면허번호 오류 확인
	                     if(cust_idS.length()!=0) {
	                     query = "SELECT * FROM customer WHERE cust_id = '" + cust_idS+"'";
	                     rs = stmt.executeQuery(query);
	                     if(!rs.next()) { 
	                        System.out.println("[오류발생 : 운전면허번호] 해당 운전면허번호의 고객이 없습니다.");
	                        isError=true;
	                     }
	                     


	                     // 캠핑카 대여시작일 날짜 오류
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
	                        System.out.println("[오류발생 : 대여시작일 오류] 대여시작일  YYYY-MM-DD에서 MM은 1~12사이, DD는 1~31사이의 수여야 합니다.");
	                        isError=true;
	                     }   
	                     else {
	                       
	                        // 캠핑카 등록일자, 캠핑카 대여시작일 비교 
	                        query = "SELECT car_date FROM car where car_id = '" + car_idS +"'"; //등록일자
	                        rs = stmt.executeQuery(query);
	                        if(rs.next()) {
	                           String str = rs.getString(1);
	                           if(str.compareTo(car_start_dateS)>0) {
	                              System.out.println("[오류발생 : 캠핑카 등록일자보다 빠름] 해당 캠핑카는 현재 등록되어 있지 않습니다.");
	                              isError = true;
	                           }
	                        }
	                     }
	                 
	                  }
	                     
	                   // 가격 오류 확인
	                   if(CheckNumber(priceS)) {
	                	   int price = Integer.parseInt(priceS);
	                	   if(price<=0) {
	                		   System.out.println("[오류발생 : 청구요금] 청구요금은 양의 정수이어야합니다.");
	                           isError = true;
	                	   }
	                   }
	                   else {
	                	   System.out.println("[오류발생 : 청구요금] 청구요금은 양의 정수이어야합니다.");
                           isError = true;
	                   }
	                   
	                   // 기타청구요금 오류 확인
	                   if(CheckNumber(extrafeeS)) {
	                	   int price = Integer.parseInt(extrafeeS);
	                	   if(price<=0) {
	                		   System.out.println("[오류발생 : 기타청구요금] 기타청구요금은 양의 정수이어야합니다.");
	                           isError = true;
	                	   }
	                   }
	                   else {
	                	   System.out.println("[오류발생 : 기타청구요금] 기타청구요금은 양의 정수이어야합니다.");
                           isError = true;
	                   }
	                   
	                   // 납입기한날짜 오류확인
	                   
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
						            	 System.out.println("[오류발생 : 납입기한] YYYY-MM-DD에서 MM은 1~12사이, DD는 1~31사이의 수여야 합니다.");
						        		 isError=true;
						        		 date=true;
						             }
					             }
					             else { 
					            	 isError=true;	
					            	 date=true;
					            	 System.out.println("[오류발생 : 납입기한] YYYY-MM-DD에서 MM은 1~12사이, DD는 1~31사이의 수여야 합니다.");
					             }
				             }
				             else {
				            	 System.out.println("[오류발생 : 납입기한] YYYY-MM-DD에서 MM은 1~12사이, DD는 1~31사이의 수여야 합니다.");
				            	 isError=true;		
				            	 date=true;
				             }
				            
	                	}
	                   if(!date) {
	                         
	                         if(car_start_dateS.compareTo(pay_dateS)>=0) {
	                                 System.out.println("[오류발생 : 납입기한] 납입기한은 대여날짜보다 나중이어야합니다.");
	                                 isError = true;
	                                 date=true;
	                          }
	                    }
		                 
	                   	int car_idI = Integer.parseInt(car_idS);
		                 // 오류가 없으면 대여
		                 if(!isError) {
		                    carBoolean[car_idI]=false;
		                   
		                    System.out.println("캠핑카 대여가 완료되었습니다.");
		                    
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
		                    System.out.println("캠핑카 대여가 완료되었습니다.");
		                    System.out.println("대여ID \t 대여시작일 \t ---- 생략 ----");
		                    String str = rs.getInt(1) +"\t"+ rs.getDate(2) + "\t---- 생략 ----";
		               
		                    System.out.println(str);
		                       
		                 }
	                  }
	               }
	             
	           } catch (Exception e2) {
	               System.out.println("쿼리 읽기 실패 :" + e2);
	           } 
	         
	      }
	   }

	   
	   public project() {
	        contactDB();
	        setTitle("18011571_18011584/이은효_이소정");
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
           System.out.println("드라이버 로드 성공");
        } catch(ClassNotFoundException e) {
           e.printStackTrace();
        }
        try {
            System.out.println("데이터베이스 연결 준비 ...");
            con=DriverManager.getConnection(url,userid,pwd);
            System.out.println("데이터베이스 연결 성공");
          } catch(SQLException e1) {
             e1.printStackTrace();
       }
       
   }
   
   public static void main(String[] args) {
      project pro=new project();
      
   }
   
   
      
}