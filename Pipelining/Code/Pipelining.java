import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class Pipelining {
	private static JLabel lblFetch;
	private static JLabel lblDecode;
	private static JLabel lblRegRead;
	private static JLabel lblExecute;
	private static JLabel lblMem;
	private static JLabel lblWriteBack;
	
	private static Vector<String> input = new Vector<String>() ;
	//private static int PC=0;
	private static int NPC=0;
	private static int CPC=-1;
	private static String InstFetch="";
	//private static String Decode;
	//private static String RegFetch;
	//private static String Execute;
	//private static String ALU_Value;
	//private static String ALU_Register;
	//private static String WriteBack;
	private static String presentWB;
	private static String[] op3 = new String[6];
	private static String[] op2 = new String[6];
	private static String[] op1 = new String[6];
	private static String[] imm = new String[6];
	private static String[] operator = new String[6];
	private static String[] ALU = new String[6];
	private static String[] LMD = new String[6];
	private static String[] WB = new String[6];
	private static int[] PCarr = new int[6];
	private static int stall=0;
	

    private static JFrame frame = new JFrame();
	private static JTextField txt[]=new JTextField[50];
	private static JPanel p = new JPanel();
	private static JPanel p1 = new JPanel();
	private static JPanel p2 = new JPanel();
	private static Insets insets;
	private static int j=0,halt=0;
	
  public static void addComponentsToPane(Container pane) {
    pane.setLayout(null);

    JButton b1 = new JButton("START");
    b1.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		
    		
    		
    		
    	}
    });
    JButton b2 = new JButton("PAUSE");
    JButton b3 = new JButton("RESUME");
    

    pane.add(b1);
    pane.add(b2);
    pane.add(b3);

    Insets insets = pane.getInsets();
    Dimension size = b1.getPreferredSize();
    b1.setBounds(25 + insets.left, 5 + insets.top, size.width, size.height);
    size = b2.getPreferredSize();
    b2
        .setBounds(125, 5, size.width,
            size.height);
    size = b3.getPreferredSize();
    b3.setBounds(225, 5, size.width,
        size.height);
    
    p.setBackground(Color.DARK_GRAY);
    //p.setColor(Color.red);
    pane.add(p);
    p.setBounds(25,82,450,120);
    p.setLayout(null);
    
    p1.setBackground(Color.DARK_GRAY);
    //p.setColor(Color.red);
    pane.add(p1);
    p1.setBounds(500,82,310,397);
    p1.setLayout(null);
    
    p2.setBackground(Color.DARK_GRAY);
    //p.setColor(Color.red);
    pane.add(p2);
    p2.setBounds(25,250,400,400);
    p2.setLayout(null);
    
    
    
    lblFetch = new JLabel("FETCH");
    lblFetch.setForeground(Color.WHITE);
    lblFetch.setBounds(38, 35, 75, 15);
    p2.add(lblFetch);
    
    lblDecode = new JLabel("DECODE");
    lblDecode.setForeground(Color.WHITE);
    lblDecode.setBounds(38, 90, 75, 15);
    p2.add(lblDecode);
    
    lblRegRead = new JLabel("REG READ");
    lblRegRead.setForeground(Color.WHITE);
    lblRegRead.setBounds(38, 145, 75, 15);
    p2.add(lblRegRead);
    
    lblExecute = new JLabel("EXECUTE");
    lblExecute.setForeground(Color.WHITE);
    lblExecute.setBounds(38, 200, 75, 15);
    p2.add(lblExecute);
    
    lblMem = new JLabel("MEM");
    lblMem.setForeground(Color.WHITE);
    lblMem.setBounds(38, 255, 75, 15);
    p2.add(lblMem);
    
    lblWriteBack = new JLabel("WB");
    lblWriteBack.setForeground(Color.WHITE);
    lblWriteBack.setBounds(38, 310, 75, 15);
    p2.add(lblWriteBack);
    
    txt[29] = new JTextField();
    txt[29].setColumns(10);
    txt[29].setBounds(115, 33, 200, 20);
    p2.add(txt[29]);
    
    txt[30] = new JTextField();
    txt[30].setColumns(10);
    txt[30].setBounds(115, 88, 200, 20);
    p2.add(txt[30]);
    
    txt[31] = new JTextField();
    txt[31].setColumns(10);
    txt[31].setBounds(115, 143, 200, 20);
    p2.add(txt[31]);
    
    txt[32] = new JTextField();
    txt[32].setColumns(10);
    txt[32].setBounds(115, 198, 200, 20);
    p2.add(txt[32]);
    
    txt[33] = new JTextField();
    txt[33].setColumns(10);
    txt[33].setBounds(115, 253, 200, 20);
    p2.add(txt[33]);
    
    txt[34]= new JTextField();
    txt[34].setColumns(10);
    txt[34].setBounds(115, 308, 200, 20);
    p2.add(txt[34]);
    
    
    
    txt[16] = new JTextField();
    txt[16].setColumns(10);
    txt[16].setBounds(24, 29, 264, 15);
    p1.add(txt[16]);
    
    txt[17] = new JTextField();
    txt[17].setColumns(10);
    txt[17].setBounds(24, 56, 264, 15);
    p1.add(txt[17]);
   
    txt[18] = new JTextField();
    txt[18].setColumns(10);
    txt[18].setBounds(24, 83, 264, 15);
    p1.add(txt[18]);
    
    txt[19] = new JTextField();
    txt[19].setColumns(10);
    txt[19].setBounds(24, 110, 264, 15);
    p1.add(txt[19]);
    
    txt[20] = new JTextField();
    txt[20].setColumns(10);
    txt[20].setBounds(24, 137, 264, 15);
    p1.add(txt[20]);
    
    txt[21] = new JTextField();
    txt[21].setColumns(10);
    txt[21].setBounds(24, 164, 264, 15);
    p1.add(txt[21]);
    
    txt[22] = new JTextField();
    txt[22].setColumns(10);
    txt[22].setBounds(24, 191, 264, 15);
    p1.add(txt[22]);
    
    txt[23] = new JTextField();
    txt[23].setColumns(10);
    txt[23].setBounds(24, 218, 264, 15);
    p1.add(txt[23]);
    
    txt[24] = new JTextField();
    txt[24].setColumns(10);
    txt[24].setBounds(24, 245, 264, 15);
    p1.add(txt[24]);
    
    txt[25] = new JTextField();
    txt[25].setColumns(10);
    txt[25].setBounds(24, 272, 264, 15);
    p1.add(txt[25]);
    
    txt[26] = new JTextField();
    txt[26].setColumns(10);
    txt[26].setBounds(24, 299, 264, 15);
    p1.add(txt[26]);
    
    txt[27] = new JTextField();
    txt[27].setColumns(10);
    txt[27].setBounds(24, 326, 264, 15);
    p1.add(txt[27]);
    
    txt[28] = new JTextField();
    txt[28].setColumns(10);
    txt[28].setBounds(24, 353, 264, 15);
    p1.add(txt[28]);
    
    JLabel lblNewLabel = new JLabel("R0");
    lblNewLabel.setForeground(Color.WHITE);
    lblNewLabel.setBounds(10,10,28,15);
    p.add(lblNewLabel);
    
    txt[0] = new JTextField();
    txt[0].setBounds(40, 10, 58, 15);
    p.add(txt[0]);
    txt[0].setColumns(10);
    
    JLabel lblR = new JLabel("R1");
    lblR.setForeground(Color.WHITE);
    lblR.setBounds(120, 10, 28, 15);
    p.add(lblR);
    
    txt[1] = new JTextField();
    txt[1].setColumns(10);
    txt[1].setBounds(150, 10, 58, 15);
    p.add(txt[1]);
    
    JLabel lblR_1 = new JLabel("R2");
    lblR_1.setForeground(Color.WHITE);
    lblR_1.setBounds(230, 10, 28, 15);
    p.add(lblR_1);
    
    txt[2] = new JTextField();
    txt[2].setColumns(10);
    txt[2].setBounds(260, 10, 58, 15);
    p.add(txt[2]);
    
    JLabel lblR_2 = new JLabel("R3");
    lblR_2.setForeground(Color.WHITE);
    lblR_2.setBounds(330, 10, 28, 15);
    p.add(lblR_2);
    
    txt[3] = new JTextField();
    txt[3].setColumns(10);
    txt[3].setBounds(360, 10, 58, 15);
    p.add(txt[3]);
    
    JLabel lblR_3 = new JLabel("R4");
    lblR_3.setForeground(Color.WHITE);
    lblR_3.setBounds(10, 37, 28, 15);
    p.add(lblR_3);
    
    txt[4] = new JTextField();
    txt[4].setColumns(10);
    txt[4].setBounds(40, 37, 58, 15);
    p.add(txt[4]);
    
    JLabel lblR_4 = new JLabel("R5");
    lblR_4.setForeground(Color.WHITE);
    lblR_4.setBounds(120, 37, 28, 15);
    p.add(lblR_4);
    
    txt[5] = new JTextField();
    txt[5].setColumns(10);
    txt[5].setBounds(150, 37, 58, 15);
    p.add(txt[5]);
    
    JLabel lblR_5 = new JLabel("R6");
    lblR_5.setForeground(Color.WHITE);
    lblR_5.setBounds(230, 37, 28, 15);
    p.add(lblR_5);
    
    txt[6] = new JTextField();
    txt[6].setColumns(10);
    txt[6].setBounds(260, 37, 58, 15);
    p.add(txt[6]);
    
    JLabel lblR_6 = new JLabel("R7");
    lblR_6.setForeground(Color.WHITE);
    lblR_6.setBounds(330, 37, 28, 15);
    p.add(lblR_6);
    
    txt[7] = new JTextField();
    txt[7].setColumns(10);
    txt[7].setBounds(360, 37, 58, 15);
    p.add(txt[7]);
    
    JLabel lblR_7 = new JLabel("R8");
    lblR_7.setForeground(Color.WHITE);
    lblR_7.setBounds(10, 64, 28, 15);
    p.add(lblR_7);
    
    txt[8] = new JTextField();
    txt[8].setColumns(10);
    txt[8].setBounds(40, 62, 58, 15);
    p.add(txt[8]);
    
    JLabel lblR_8 = new JLabel("R9");
    lblR_8.setForeground(Color.WHITE);
    lblR_8.setBounds(120, 64, 28, 15);
    p.add(lblR_8);
    
    txt[9] = new JTextField();
    txt[9].setColumns(10);
    txt[9].setBounds(150, 64, 58, 15);
    p.add(txt[9]);
    
    JLabel lblR_9 = new JLabel("R10");
    lblR_9.setForeground(Color.WHITE);
    lblR_9.setBounds(230, 64, 28, 15);
    p.add(lblR_9);
    
    txt[10] = new JTextField();
    txt[10].setColumns(10);
    txt[10].setBounds(260, 64, 58, 15);
    p.add(txt[10]);
    
    JLabel lblR_10 = new JLabel("R11");
    lblR_10.setForeground(Color.WHITE);
    lblR_10.setBounds(330, 64, 28, 15);
    p.add(lblR_10);
    
    txt[11] = new JTextField();
    txt[11].setColumns(10);
    txt[11].setBounds(360, 64, 58, 15);
    p.add(txt[11]);
    
    JLabel lblR_11 = new JLabel("R12");
    lblR_11.setForeground(Color.WHITE);
    lblR_11.setBounds(10, 91, 28, 15);
    p.add(lblR_11);
    
    txt[12] = new JTextField();
    txt[12].setColumns(10);
    txt[12].setBounds(40, 91, 58, 15);
    p.add(txt[12]);
    
    JLabel lblR_12 = new JLabel("R13");
    lblR_12.setForeground(Color.WHITE);
    lblR_12.setBounds(120, 91, 28, 15);
    p.add(lblR_12);
    
    txt[13] = new JTextField();
    txt[13].setColumns(10);
    txt[13].setBounds(150, 91, 58, 15);
    p.add(txt[13]);
    
    JLabel lblR_13 = new JLabel("R14");
    lblR_13.setForeground(Color.WHITE);
    lblR_13.setBounds(230, 91, 28, 15);
    p.add(lblR_13);
    
    txt[14] = new JTextField();
    txt[14].setColumns(10);
    txt[14].setBounds(260, 91, 58, 15);
    p.add(txt[14]);
    
    JLabel lblR_14 = new JLabel("R15");
    lblR_14.setForeground(Color.WHITE);
    lblR_14.setBounds(330, 91, 58, 15);
    p.add(lblR_14);
    
    txt[15] = new JTextField();
    txt[15].setColumns(10);
    txt[15].setBounds(360, 91, 58, 15);
    p.add(txt[15]);
    
    String gg=new String("Sinda");
    txt[15].setText(gg);
    ////////////////////////////////////////////////////////////////////////////////
    
    
    try{
    BufferedReader br = new BufferedReader(new FileReader("/home/dileep/workspace/skyslash/input.txt"));
    String st = new String();
	while ((st = br.readLine()) != null) {
		input.add(st);
		System.out.println(input.get(input.size()-1));
	}
	br.close();
    }
    
    catch(Exception e)
    {
    	
    }
    txt[34].setText(gg);
    
    	
    	
    	
    	
    }
    
    
    
    
    
    

  /**
   * Create the GUI and show it. For thread safety, this method should be
   * invoked from the event-dispatching thread.
   */
  private static void createAndShowGUI() {
    //Make sure we have nice window decorations.
    JFrame.setDefaultLookAndFeelDecorated(false);

    //Create and set up the window.
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Set up the content pane.
    addComponentsToPane(frame.getContentPane());

    //Size and display the window.
    insets = frame.getInsets();
    frame.setSize(900 + insets.left + insets.right, 900 + insets.top
        + insets.bottom);
   
    txt[34].setText("Pakodi");
    txt[34].setText("Pakodi");
    
    frame.setVisible(true);
    
    j=0;
   
    	
	    
   
    
	    

    
    ///////////////////////////////////////////////////////////////
    
   
    
    
    
    
    
    //////////////////////////////////////////////////////////////
  }

  public static void main(String[] args) {
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
    op3[5]="";
	op2[5]="";
	op1[5]="";
	operator[5]="";
	imm[5]="";
	ALU[5]="";
	LMD[5]="";
	WB[5]="";
	
	op3[4]="";
	op2[4]="";
	op1[4]="";
	operator[4]="";
	imm[4]="";
	ALU[4]="";
	LMD[4]="";
	WB[4]="";
	
	op3[3]="";
	op2[3]="";
	op1[3]="";
	operator[3]="";
	imm[3]="";
	ALU[3]="";
	LMD[3]="";
	WB[3]="";
	
	op3[2]="";
	op2[2]="";
	op1[2]="";
	operator[2]="";
	imm[2]="";
	ALU[2]="";
	LMD[2]="";
	WB[2]="";
	
	op3[1]="";
	op2[1]="";
	op1[1]="";
	operator[1]="";
	imm[1]="";
	ALU[1]="";
	LMD[1]="";
	WB[1]="";
	
	op3[0]="";
	op2[0]="";
	op1[0]="";
	operator[0]="";
	imm[0]="";
	ALU[0]="";
	LMD[0]="";
	WB[0]="";
	
    ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            
        	   if(NPC==input.size())
        	   {
        		   txt[29].setText(Integer.toString(j));
    	     	   ++j;
        	   }
        	   else
        	   {
	        	txt[29].setText(Integer.toString(j));
	     	   ++j;
	            	//WRITE BACK STAGE
	            	String WBStage="";
	            	if(operator[5]=="011")
	            		WBStage+="R"+WB[5]+" <---- "+"LMD";
	            	else
	            		WBStage+="R"+WB[5]+" <---- "+"ALUOutput";
	            	if(WB[5]=="")
	            		txt[34].setText("WB STALL");
	            	presentWB=WB[5];
	            	op3[5]=op3[4];
	            	op2[5]=op2[4];
	            	op1[5]=op1[4];
	            	operator[5]=operator[4];
	            	imm[5]=imm[4];
	            	ALU[5]=ALU[4];
	            	LMD[5]=LMD[4];
	            	WB[5]=WB[4];
	            	
	            	//////////////////////////////////////////////////
	            	
	            	//MEM STAGE
	            	String MEMStage="";
	            	if(operator[4]=="011")
	            		WBStage+="LMD <---- D$["+ALU[4]+"]";
	            	if(operator[4]=="100")
	            		WBStage+="D$["+ALU[4]+"]"+" <---- "+op3[4];
	            	if(WB[4]=="")
	            		txt[33].setText("MEM STALL");
	            	else
	            		txt[33].setText("NOT CARING");
	            	op3[4]=op3[3];
	            	op2[4]=op2[3];
	            	op1[4]=op1[3];
	            	operator[4]=operator[3];
	            	imm[4]=imm[3];
	            	ALU[4]=ALU[3];
	            	LMD[4]=LMD[3];
	            	WB[4]=WB[3];
	            	
	            	//////////////////////////////////////////////////
	            	
	            	//EXECUTE STAGE

	        		op3[3]=op3[2];
	            	op2[3]=op2[2];
	            	op1[3]=op1[2];
	            	operator[3]=operator[2];
	            	imm[3]=imm[2];
	            	ALU[3]=ALU[2];
	            	LMD[3]=LMD[2];
	            	WB[3]=WB[2];
	            	String EXECStage="";
	            	CPC=-1;
	            	String gg=operator[3];
	            	System.out.println("-------------opppp"+operator[3]+"----");
	            	System.out.println("-------------ogggg"+gg+"----");
	            	if(operator[3]=="000")
	            	{
	            		if(imm[3]=="0")
	            		{
	            			EXECStage+="ALUOutput <---- R"+op2[3]+" + R"+op3[3];
	            		}
	            		else if(imm[3]=="1")
	            		{
	            			EXECStage+="ALUOutput <---- R"+op2[3]+" + "+op3[3];
	            		}
	            		txt[32].setText(EXECStage);
	            		
	            	}
	            	System.out.println("-------------opppp1"+gg+"----");
	            	if(operator[3]=="001")
	            	{
	            		if(imm[3]=="0")
	            		{
	            			EXECStage+="ALUOutput <---- R"+op2[3]+" - R"+op3[3];
	            		}
	            		else if(imm[3]=="1")
	            		{
	            			EXECStage+="ALUOutput <---- R"+op2[3]+" - "+op3[3];
	            		}
	            		txt[32].setText(EXECStage);
	            	}
	            	System.out.println("-------------opppp2"+gg+"----");
	            	if(operator[3]=="010")
	            	{
	            		if(imm[3]=="0")
	            		{
	            			EXECStage+="ALUOutput <---- R"+op2[3]+" * R"+op3[3];
	            		}
	            		else if(imm[3]=="1")
	            		{
	            			EXECStage+="ALUOutput <---- R"+op2[3]+" * "+op3[3];
	            		}
	            		txt[32].setText(EXECStage);
	            	}
	            	System.out.println("-------------opppp3"+gg+"----");
	            	if(operator[3].equals("011"))
	            	{
            			System.out.println("*************"+imm[3]);
	            		if(imm[3]=="0")
	            		{
	            			System.out.println("-------------");
	            			EXECStage+="ALUOutput <---- R0"+" + R"+op3[3];
	            		}
	            		else if(imm[3]=="1")
	            		{
	            			EXECStage+="ALUOutput <---- R0"+" + "+op3[3];
	            		}
	            		txt[32].setText(EXECStage);
	            	}
	            	if(operator[3]=="100")
	            	{
	            		if(imm[3]=="0")
	            		{
	            			EXECStage+="ALUOutput <---- R0"+" + R"+op3[3];
	            		}
	            		else if(imm[3]=="1")
	            		{
	            			EXECStage+="ALUOutput <---- R0"+" + "+op3[3];
	            		}
	            		txt[32].setText(EXECStage);
	            	}
	            	if(operator[3]=="101")
	            	{
	            		CPC=Integer.parseInt(op3[3]);
	            		EXECStage+="ALUOutput <---- PC"+" + "+op3[3];
	            		txt[32].setText(EXECStage);
	            	}
	            	if(operator[3]=="110")
	            	{
	            		int o1,o2;
	            		o1=Integer.parseInt(txt[Integer.parseInt(op1[3])].getText());
	            		o2=Integer.parseInt(txt[Integer.parseInt(op2[3])].getText());
	            		if(o1==o2)
	            		{
	            			CPC=Integer.parseInt(op3[3]);
	            			EXECStage+="ALUOutput <---- PC"+" + "+op3[3]+";";
	            			EXECStage+="Branch Cond=TRUE;";
	            		}
	            		else
	            		{
	            			EXECStage+="ALUOutput <---- PC"+" + "+op3[3]+";";
	            			EXECStage+="Branch Cond=FALSE;";
	            		}
	            		txt[32].setText(EXECStage);
	            	}
	            	if(operator[3]=="111")
	            	{
	            		EXECStage+="HALT";
	            		txt[32].setText(EXECStage);
	            	}
	            	if(WB[3]=="")
	            	{
	            		txt[32].setText(gg+"hehe");
	            		
	            	}
	            	else
	            		txt[32].setText(operator[3]);
	            	
	            	
	            	
	            	
	            	
	            	////////////////////////////////////////////////////////////
	                
	            	//DATA FETCH
	            	String Fetch="";
	            	if(imm[2]=="0")
	            	{
	            		System.out.println("First IF");
	            		Fetch+="A <---- R"+op1[2]+"; B <---- R"+op2[3]+";";
	            	}
	            	else if(imm[2]=="1")
	            	{
	            		System.out.println("Second IF");
	            		Fetch+="A <---- R"+op1[2]+"; Imm <---- "+op2[3]+";";
	            	}
	        		txt[31].setText(Fetch);
	        		System.out.println(op1[3]+"++++++++++++++++"+op2[1]);
	        		op1[3]="";
	            	if((op1[3]==op2[1] || op1[3]==op3[1] || op1[4]==op2[1] || op1[4]==op3[1] || op1[5]==op2[1] || op1[5]==op3[1]) && (op1[3]!=""))
	            	{
	            		System.out.println("Third IF");
	            		System.out.println(op1[3]);
	            		op3[2]="";
	                	op2[2]="";
	                	op1[2]="";
	                	operator[2]="";
	                	imm[2]="";
	                	ALU[2]="";
	                	LMD[2]="";
	                	WB[2]="";
	                	stall=1;
	            	}
	            	else
	            	{
	            		System.out.println(op1[3]+"++++++++++++++++"+op2[1]);
	            		System.out.println();
	            		op3[2]=op3[1];
	                	op2[2]=op2[1];
	                	op1[2]=op1[1];
	                	operator[2]=operator[1];
	                	imm[2]=imm[1];
	                	ALU[2]=ALU[1];
	                	LMD[2]=LMD[1];
	                	WB[2]=WB[1];
	                	stall=0;
	                	System.out.println(imm[2]+" "+operator[2]+" "+op1[2]+" "+op2[2]+" "+op3[2]+";");
	            		txt[31].setText(imm[2]+" "+operator[2]+" "+op1[2]+" "+op2[2]+" "+op3[2]+";");
	            	}
	            	
	            	/////////////////////////////////////////////////////////////
	            	
	            	//INSTRUCTION DECODE
	            	 System.out.println("hello1");
	            	
	            	if(stall==1)
	            	{
	            		txt[30].setText("STALL");
	            		txt[30].setText("STALL");
		            	 System.out.println("hello");
	            	}
	            	if(InstFetch.length()==0)
	            	{
	            		op3[3]="";
	                	op2[3]="";
	                	op1[3]="";
	                	operator[3]="";
	                	imm[3]="";
	                	ALU[3]="";
	                	LMD[3]="";
	                	WB[3]="";
	            	}
	            	else if(InstFetch.length()==16)
	            	{
	            		String im="",top="",top1="",top2="",top3="";
	            		if(halt==1)
	            		{
	            			op3[3]="";
		                	op2[3]="";
		                	op1[3]="";
		                	operator[3]="";
		                	imm[3]="";
		                	ALU[3]="";
		                	LMD[3]="";
		                	WB[3]="";
	            		}
	            		else
		            		{
			            	String instruction=InstFetch;
			            	System.out.println("gg wp");
			            	
			            	im+=instruction.substring(3,4);
			            	top+=instruction.substring(0,3);
			            	top1+=instruction.substring(4,8);
			            	top2+=instruction.substring(8,12);
			            	top3+=instruction.substring(12,16);
			            	op3[1]=Integer.toString(Integer.parseInt(top3,2));
			            	op2[1]=Integer.toString(Integer.parseInt(top2,2));
			            	op1[1]=Integer.toString(Integer.parseInt(top1,2));
			            	operator[1]=top;
			            	System.out.println(op3[1]);
			            	System.out.println(op2[1]);
			            	System.out.println(op1[1]);
			            	imm[1]=im;
			            	ALU[1]="";
			            	LMD[1]="";
			            	WB[1]=Integer.toString(Integer.parseInt(top1,2));
			            	txt[30].setText(im+" "+top+" "+top1+" "+top2+" "+top3+";");
	            		}
		            	if(top=="111")
		            	{
		            		halt=1;
		            	}
	            	}
	            	/////////////////////////////////////////////////////////////
	            	
	            	//INSTRUCTION FETCH

	            	System.out.println("gg wp");
	            	if(halt==1)
	            	{
	            		op3[3]="";
	                	op2[3]="";
	                	op1[3]="";
	                	operator[3]="";
	                	imm[3]="";
	                	ALU[3]="";
	                	LMD[3]="";
	                	WB[3]="";
	                	InstFetch="";
	            	}
	            	else if(CPC!=-1)
	            	{
		            	System.out.println("gg wp");
	            		op3[3]="";
	                	op2[3]="";
	                	op1[3]="";
	                	operator[3]="";
	                	imm[3]="";
	                	ALU[3]="";
	                	LMD[3]="";
	                	WB[3]="";
	                	
	                	op3[2]="";
	                	op2[2]="";
	                	op1[2]="";
	                	operator[2]="";
	                	imm[2]="";
	                	ALU[2]="";
	                	LMD[2]="";
	                	WB[2]="";
	                	
	                	op3[1]="";
	                	op2[1]="";
	                	op1[1]="";
	                	operator[1]="";
	                	imm[1]="";
	                	ALU[1]="";
	                	LMD[1]="";
	                	WB[1]="";
	                	
	                	InstFetch=input.get(CPC);
	                	txt[29].setText(InstFetch);
	                	NPC=CPC;
	                	
	            	}
	            	
	            	else
	            	{
	                	InstFetch=input.get(NPC);
		            	System.out.println("gg wp");
	                	txt[29].setText(InstFetch);
	                	NPC=NPC+1;
	            	}
            	
        	   }
           	
        	
            System.out.println("Reading SMTP Info.");
        }
    };
    Timer timer = new Timer(1000 ,taskPerformer);
    timer.setRepeats(true);
    timer.start();

    
  }
}
    