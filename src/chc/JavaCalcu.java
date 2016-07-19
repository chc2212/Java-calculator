//091895 ��ȣö

package chc;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;

class Calculator extends JFrame implements ActionListener,ItemListener{ // ActionListener ����. ItemListner���� 
	
	public JTextField resultText;
	public JTextField resultProcess;
	public JButton buttonArray[];
	public JPanel buttonGroup, resultGroup;
	public JButton saveButton;
	public JButton clearButton;
	public JButton exitButton;
	public Checkbox normal,save;
	
	
	
	
	
	public CheckboxGroup cbg = new CheckboxGroup(); //üũ�ڽ��׷켳��
	
	
	public char process[] = new char[1000000];
	public int processI;
	public double result=0; // ��� �� 
	public double a=0,b=0,c=0;
	public int numberButton=0;
	public boolean clearText=true;
	public boolean clearprocesstext = true;
	public boolean saveMode=false; // saveMode���� �Ǵ�
	public int previousEventName=0; // ���� ������ �̺�Ʈ ����  
	
	
	public Calculator(String title){
	//  ----------------���� ��ư����---------------//
		clearButton = new JButton("Clear");
		exitButton = new JButton("Exit");
		saveButton = new JButton("Save(Only Save Mode)");
		
		// ���ǥ��â ��ư
		resultText = new JTextField(50);
		resultProcess = new JTextField(50);
		
		resultGroup = new JPanel();            //resultGroup��� �гθ��� ���� ���̾ƿ�����
		resultGroup.setLayout(new GridLayout(2,2));
		
		setLayout(new BorderLayout());
		
		resultGroup.add(resultProcess);
		resultProcess.setEditable(false);
		
		resultGroup.add(resultText);
		resultText.setEditable(false);
		
		//üũ�ڽ����� + �г������̳� ����
		
		 normal = new Checkbox("Normal Mode",cbg,true);
		 save = new Checkbox("Save Mode",cbg,false);
		
		  Panel ptop =new Panel();
		  ptop.setLayout(new GridLayout(4,1));
		  ptop.add(normal);
		  ptop.add(save);
		  ptop.add(clearButton);
		  ptop.add(exitButton);
		 		  		  
	
		  normal.addItemListener(this);
		  save.addItemListener(this);
				  
		  
		  
		// ��ư ���� 
		buttonGroup = new JPanel();
		buttonArray = new JButton[20];  
		  // ��ư ����    
		for (int i = 0; i <= 9; i++)
		{
			
			buttonArray [i]= new JButton(Integer.toString(i));
		}
		buttonArray[10] = new JButton("/");
		buttonArray[11] = new JButton("*");
		buttonArray[12] = new JButton("-");
		buttonArray[13] = new JButton("+");
		buttonArray[14] = new JButton(".");
		buttonArray[15] = new JButton("=");
		buttonArray[16] = new JButton("x^2");
		buttonArray[17] = new JButton("sin");
		buttonArray[18] = new JButton("cos");
		buttonArray[19] = new JButton("tan");

		
          //---------------�������۾�----------//
		buttonGroup.setLayout(new GridLayout(6,4)); 
		
		
		  setVisible(true);
 
		
		    buttonGroup.add(normal);
			buttonGroup.add(save);
			buttonGroup.add(clearButton);
			buttonGroup.add(exitButton);     
			
			
		for( int i = 7; i <= 10; i++)
		{
			buttonGroup.add(buttonArray[i]);
		}

		for( int i = 4; i <= 6; i++)
		{
			buttonGroup.add(buttonArray[i]);
		}
  
		buttonGroup.add(buttonArray[11]);

		for( int i = 1; i <= 3; i++)
		{
			buttonGroup.add(buttonArray[i]);
		}

		buttonGroup.add(buttonArray[12]);

		buttonGroup.add(buttonArray[0]);
		buttonGroup.add(buttonArray[14]);
		buttonGroup.add(buttonArray[13]);
		buttonGroup.add(buttonArray[15]);
		
		
		for(int i= 16; i<=19 ; i++)
		{
			buttonGroup.add(buttonArray[i]);
		}

	
		
		// ���� ū Ʋ�� BorderLayout
		add(buttonGroup, BorderLayout.CENTER);
		add(resultGroup, BorderLayout.NORTH);
		add(saveButton, BorderLayout.SOUTH);
	
		
		// ���� �޴� ���� 
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		
		
		JMenuItem saveMenuItem = fileMenu.add("Save(Only Save Mode)");
		JMenuItem exitMenuItem = fileMenu.add("Exit");
		JMenuItem clearMenuItem = editMenu.add("Clear");
		
		  //----------------- �̺�Ʈ ��� -----------//
		for ( int i = 0; i < buttonArray.length; i++)
		{
			buttonArray[i].addActionListener(this);
		}  
		  
		clearButton.addActionListener(this);
		exitButton.addActionListener(this);
		saveButton.addActionListener(this);
		
		
		// �޴� �̺�Ʈ ���
		clearMenuItem.addActionListener(this);
		saveMenuItem.addActionListener(this);
		exitMenuItem.addActionListener(this); 
		
		
		setTitle(title);
		setSize(700,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	

	
	//----------------���� ��ư�׼� ����---------//
	public void actionPerformed(ActionEvent e) 
	{
		String eventName="";
	
		
		
		eventName=e.getActionCommand(); 

		if(eventName == "Clear" | eventName == "Clear"){ // Clear ��ư �� �޴��� Clear
			resultText.setText("");
			resultProcess.setText("");
			

			if(saveMode)
			{
			for(int ck=0; ck<1001; ck++)
			process[ck] = 0; // Ŭ������� �������� Ŭ�����
			processI=0;
			}
			
					
		}
		else if(eventName == "Exit" | eventName == "Exit"){ // . Exit 
			System.exit(0);
		}
		
		// �ؽ�Ʈ���Ϸ� ���� �ϱ�
		else if(eventName =="Save(Only Save Mode)" | eventName == "Save(Only Save Mode)"){
		
			if(saveMode)
			{
		try{
			
			File file = new File("d:\\091895.txt");
			if(!file.exists()){
			  FileWriter fw = new FileWriter("d:\\091895.txt");
				
			}

	
		
		     RandomAccessFile save = new RandomAccessFile("d:\\091895.txt", "rw");
		      save.seek(save.length());
		      save.writeBytes(resultProcess.getText() + "\r\n");

			
			}catch(Exception a) { 
				   
			 }
			}
		
		}
						 
		else if(eventName == "."){ // . ��ư �̺�Ʈ�� ��� 
			resultText.setText(resultText.getText() + eventName);
			if(saveMode)
			{
				resultProcess.setText(resultProcess.getText() + eventName); 
			}
		}
		
		else if(eventName == "/"){// / ��ư �̺�Ʈ�� ��� 
			result = Double.valueOf(resultText.getText());
			previousEventName=10; // ������ 
			Double.valueOf(resultProcess.getText());
			if(saveMode)
			{
				resultProcess.setText(resultProcess.getText() + "/"); // ������������ /�߰�
			}
			
			
			clearText=true;
		}
		else if(eventName == "*"){// * ��ư �̺�Ʈ�� ��� 
			result = Double.valueOf(resultText.getText());
			previousEventName=20; // ���ϱ� 
			
			if(saveMode)
			{
				
				resultProcess.setText(resultProcess.getText() + "*"); // ���������� *�߰�
			}
			
			clearText=true;
		
		}
		else if(eventName == "+"){// + ��ư �̺�Ʈ�� ��� 
			result = Double.valueOf(resultText.getText());
			previousEventName=30; // ���ϱ� 
			
			if(saveMode)
			{
				resultProcess.setText(resultProcess.getText() + "+"); // ���������� +�߰�
			}
			
			
			clearText=true;
		}
		else if(eventName == "-"){// - ��ư �̺�Ʈ�� ��� 
			result = Double.valueOf(resultText.getText());
			previousEventName=40; // ���� 
			
			if(saveMode)
			{
				resultProcess.setText(resultProcess.getText() + "-"); 
			} // ���������� -�߰�
			
						
			clearText=true;
		}
		//-----------�ﰢ�Լ��� �����̺�Ʈ   �ﰢ�Լ��� ������ =�� �������ʰ�, �ٷ� ���� ��µǰ� �Ͽ���.-----//
		else if(eventName == "sin"){// sin ��ư �̺�Ʈ�� ��� 
			
			if(saveMode)  //saveMode�� ��� ó��
			{
				resultProcess.setText("Sin" + resultText.getText() + " = ");
			}
			
			result = Double.valueOf(resultText.getText());
			previousEventName=90; // ���� 
			
			Double a = Double.valueOf(resultText.getText());
			Double rad = Math.toRadians(a);
			result = Math.sin(rad);
			resultText.setText(Double.toString(result));
			clearText=true;
			
			if(saveMode)
			{
				resultProcess.setText(resultProcess.getText() + resultText.getText());
				clearprocesstext = true;			
			}
			
			
			
			
		}
		else if(eventName == "cos"){// cos ��ư �̺�Ʈ�� ���  
			
			if(saveMode)
			{
				resultProcess.setText("Cos" + resultText.getText() + " = ");
			}
			
			result = Double.valueOf(resultText.getText());
			previousEventName=90; // �ڽ���
			
			Double b = Double.valueOf(resultText.getText());
			Double rad2 = Math.toRadians(b);
			result = Math.cos(rad2);	
			resultText.setText(Double.toString(result));
			clearText=true;
			
			if(saveMode)
			{
				resultProcess.setText(resultProcess.getText() + resultText.getText());
				clearprocesstext = true;			
			}
			
		}
		else if(eventName == "tan"){// tan ��ư �̺�Ʈ�� ���  
			
			if(saveMode)
			{
				resultProcess.setText("tan" + resultText.getText() + " = ");
			}
			
			result = Double.valueOf(resultText.getText());
			previousEventName=90; // ź��Ʈ
			
			Double c = Double.valueOf(resultText.getText());
			Double rad3 = Math.toRadians(c);
			result = Math.tan(rad3);
			resultText.setText(Double.toString(result));
			clearText=true;
			
			if(saveMode)
			{
				resultProcess.setText(resultProcess.getText() + resultText.getText());
				clearprocesstext = true;			
			}
			
		}
		
		
		
		else if(eventName == "x^2"){// ���� ��ư �̺�Ʈ�� ���  
			
			if(saveMode)
			{
				resultProcess.setText(resultText.getText() + "^2" + " = ");
			}
			
			result = Double.valueOf(resultText.getText());
			previousEventName=90; // ����
			result = result * result;
			resultText.setText(Double.toString(result));
			clearText=true;
			
			if(saveMode)
			{
				resultProcess.setText(resultProcess.getText() + resultText.getText());
				clearprocesstext = true;			
			}
		}
		
	
		
		//------------------------- = �̺�Ʈ�� ó��  --  �� ���� ���������� ������ ���޹޾Ƽ� �װ��� �������� ó���Ѵ�---  //
		else if(eventName == "=")
		{
		
			
			{ //  = ��ư �̺�Ʈ�� ��� 
				switch(previousEventName){
		
				case 10: // ������ 
					result /= Double.valueOf(resultText.getText());
					break;
				case 20:// ���ϱ� 
					result *= Double.valueOf(resultText.getText());
					break;
				case 30://���ϱ�
					result += Double.valueOf(resultText.getText());
		     		break;
				case 40://����
					result -= Double.valueOf(resultText.getText());
					break;
				case 50:
					Double a = Double.valueOf(resultText.getText());
					Double rad = Math.toRadians(a);
					result = Math.sin(rad);
					break;
				case 60:
					Double b = Double.valueOf(resultText.getText());
					Double rad2 = Math.toRadians(b);
					result = Math.cos(rad2);
					break;
				case 70:
					Double c = Double.valueOf(resultText.getText());
					Double rad3 = Math.toRadians(c);
					result = Math.tan(rad3);
					break;
				case 80:
					result = result * result;
					break;
				case 90:
					break;
				
						
				}
				
				resultText.setText(Double.toString(result));
				//=�� �����Ŀ� �ٽ� ���ڸ� �Է��ϸ� ���ο� ���� ���۵�
				if(saveMode)
					resultProcess.setText(resultProcess.getText() + "=" + resultText.getText());
				
				clearprocesstext = true;
				clearText=true;
				
			}
		}
		
			
			
			//-------------saveMode ���ڹ�ư �̺�Ʈ--------
		else{ 
			
			// ���� ��ư �̺�Ʈ 
			numberButton=Integer.parseInt(eventName);
			switch(numberButton){
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				
				if(clearprocesstext)   //  �������� Ŭ����ȴ�
				{
					if(saveMode)
					{
					for(int ck=0; ck<1001; ck++)
					process[ck] = 0; 
					processI=0;
					}
					
					resultProcess.setText("");
				clearprocesstext = false;
				}
					
				if(saveMode)
			resultProcess.setText(resultProcess.getText() + numberButton);
				
				
				break;
			}
			
		
			//-----------------���ڹ�ư �̺�Ʈ--------
			switch(numberButton){
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				if(clearText){ // ������ ��ư �Է� ���� ��� ȭ�� ���� 
					resultText.setText("");
					clearText=false;
					
				}
				resultText.setText(resultText.getText() + numberButton);
				
				
				break;
			}
			
		}
		
	}

	

    //------------------- Checkbox���ÿ� ���� Item������----------------
	public void itemStateChanged(ItemEvent e){  //ItemEvent������ �ڰ� �޽��
		String judge=cbg.getSelectedCheckbox().getLabel();
		
		if(judge == "Save Mode")    //Save Mode���� �Ͼ �۾��� 1. ��������� 2. �ؽ�Ʈ���Ϸ� ����
		{
			resultText.setText("");
			saveMode=true;
		}
		
		if(judge == "Normal Mode")    //�� ��� ��ȯ�� ȭ�� Ŭ����ǰ� �������.
		{
			resultText.setText("");
			resultProcess.setText("");
			saveMode=false;
		}
	
	}



}


// ����
public class JavaCalcu {
	public static void main(String[] args){
	new Calculator("Java Calculator");
	}
}


