//091895 조호철

package chc;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;

class Calculator extends JFrame implements ActionListener,ItemListener{ // ActionListener 포함. ItemListner포함 
	
	public JTextField resultText;
	public JTextField resultProcess;
	public JButton buttonArray[];
	public JPanel buttonGroup, resultGroup;
	public JButton saveButton;
	public JButton clearButton;
	public JButton exitButton;
	public Checkbox normal,save;
	
	
	
	
	
	public CheckboxGroup cbg = new CheckboxGroup(); //체크박스그룹설정
	
	
	public char process[] = new char[1000000];
	public int processI;
	public double result=0; // 결과 값 
	public double a=0,b=0,c=0;
	public int numberButton=0;
	public boolean clearText=true;
	public boolean clearprocesstext = true;
	public boolean saveMode=false; // saveMode인지 판단
	public int previousEventName=0; // 이전 연산자 이벤트 정보  
	
	
	public Calculator(String title){
	//  ----------------각종 버튼생성---------------//
		clearButton = new JButton("Clear");
		exitButton = new JButton("Exit");
		saveButton = new JButton("Save(Only Save Mode)");
		
		// 결과표시창 버튼
		resultText = new JTextField(50);
		resultProcess = new JTextField(50);
		
		resultGroup = new JPanel();            //resultGroup라는 패널만들어서 위쪽 레이아웃만듦
		resultGroup.setLayout(new GridLayout(2,2));
		
		setLayout(new BorderLayout());
		
		resultGroup.add(resultProcess);
		resultProcess.setEditable(false);
		
		resultGroup.add(resultText);
		resultText.setEditable(false);
		
		//체크박스구성 + 패널컨테이너 구성
		
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
				  
		  
		  
		// 버튼 구성 
		buttonGroup = new JPanel();
		buttonArray = new JButton[20];  
		  // 버튼 생성    
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

		
          //---------------디자인작업----------//
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

	
		
		// 가장 큰 틀의 BorderLayout
		add(buttonGroup, BorderLayout.CENTER);
		add(resultGroup, BorderLayout.NORTH);
		add(saveButton, BorderLayout.SOUTH);
	
		
		// 각종 메뉴 구성 
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		
		
		JMenuItem saveMenuItem = fileMenu.add("Save(Only Save Mode)");
		JMenuItem exitMenuItem = fileMenu.add("Exit");
		JMenuItem clearMenuItem = editMenu.add("Clear");
		
		  //----------------- 이벤트 등록 -----------//
		for ( int i = 0; i < buttonArray.length; i++)
		{
			buttonArray[i].addActionListener(this);
		}  
		  
		clearButton.addActionListener(this);
		exitButton.addActionListener(this);
		saveButton.addActionListener(this);
		
		
		// 메뉴 이벤트 등록
		clearMenuItem.addActionListener(this);
		saveMenuItem.addActionListener(this);
		exitMenuItem.addActionListener(this); 
		
		
		setTitle(title);
		setSize(700,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	

	
	//----------------각종 버튼액션 설정---------//
	public void actionPerformed(ActionEvent e) 
	{
		String eventName="";
	
		
		
		eventName=e.getActionCommand(); 

		if(eventName == "Clear" | eventName == "Clear"){ // Clear 버튼 및 메뉴의 Clear
			resultText.setText("");
			resultProcess.setText("");
			

			if(saveMode)
			{
			for(int ck=0; ck<1001; ck++)
			process[ck] = 0; // 클리어누르면 계산과정이 클리어됨
			processI=0;
			}
			
					
		}
		else if(eventName == "Exit" | eventName == "Exit"){ // . Exit 
			System.exit(0);
		}
		
		// 텍스트파일로 저장 하기
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
						 
		else if(eventName == "."){ // . 버튼 이벤트인 경우 
			resultText.setText(resultText.getText() + eventName);
			if(saveMode)
			{
				resultProcess.setText(resultProcess.getText() + eventName); 
			}
		}
		
		else if(eventName == "/"){// / 버튼 이벤트인 경우 
			result = Double.valueOf(resultText.getText());
			previousEventName=10; // 나누기 
			Double.valueOf(resultProcess.getText());
			if(saveMode)
			{
				resultProcess.setText(resultProcess.getText() + "/"); // 나눗셈누르면 /추가
			}
			
			
			clearText=true;
		}
		else if(eventName == "*"){// * 버튼 이벤트인 경우 
			result = Double.valueOf(resultText.getText());
			previousEventName=20; // 곱하기 
			
			if(saveMode)
			{
				
				resultProcess.setText(resultProcess.getText() + "*"); // 곱셈누르면 *추가
			}
			
			clearText=true;
		
		}
		else if(eventName == "+"){// + 버튼 이벤트인 경우 
			result = Double.valueOf(resultText.getText());
			previousEventName=30; // 더하기 
			
			if(saveMode)
			{
				resultProcess.setText(resultProcess.getText() + "+"); // 덧셈누르면 +추가
			}
			
			
			clearText=true;
		}
		else if(eventName == "-"){// - 버튼 이벤트인 경우 
			result = Double.valueOf(resultText.getText());
			previousEventName=40; // 빼기 
			
			if(saveMode)
			{
				resultProcess.setText(resultProcess.getText() + "-"); 
			} // 뺄셈누르면 -추가
			
						
			clearText=true;
		}
		//-----------삼각함수와 제곱이벤트   삼각함수와 제곱은 =을 눌르지않고, 바로 값이 출력되게 하였다.-----//
		else if(eventName == "sin"){// sin 버튼 이벤트인 경우 
			
			if(saveMode)  //saveMode일 경우 처리
			{
				resultProcess.setText("Sin" + resultText.getText() + " = ");
			}
			
			result = Double.valueOf(resultText.getText());
			previousEventName=90; // 싸인 
			
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
		else if(eventName == "cos"){// cos 버튼 이벤트인 경우  
			
			if(saveMode)
			{
				resultProcess.setText("Cos" + resultText.getText() + " = ");
			}
			
			result = Double.valueOf(resultText.getText());
			previousEventName=90; // 코싸인
			
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
		else if(eventName == "tan"){// tan 버튼 이벤트인 경우  
			
			if(saveMode)
			{
				resultProcess.setText("tan" + resultText.getText() + " = ");
			}
			
			result = Double.valueOf(resultText.getText());
			previousEventName=90; // 탄젠트
			
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
		
		
		
		else if(eventName == "x^2"){// 제곱 버튼 이벤트인 경우  
			
			if(saveMode)
			{
				resultProcess.setText(resultText.getText() + "^2" + " = ");
			}
			
			result = Double.valueOf(resultText.getText());
			previousEventName=90; // 제곱
			result = result * result;
			resultText.setText(Double.toString(result));
			clearText=true;
			
			if(saveMode)
			{
				resultProcess.setText(resultProcess.getText() + resultText.getText());
				clearprocesstext = true;			
			}
		}
		
	
		
		//------------------------- = 이벤트의 처리  --  이 전의 계산과정에서 변수를 전달받아서 그것을 바탕으로 처리한다---  //
		else if(eventName == "=")
		{
		
			
			{ //  = 버튼 이벤트인 경우 
				switch(previousEventName){
		
				case 10: // 나누기 
					result /= Double.valueOf(resultText.getText());
					break;
				case 20:// 곱하기 
					result *= Double.valueOf(resultText.getText());
					break;
				case 30://더하기
					result += Double.valueOf(resultText.getText());
		     		break;
				case 40://빼기
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
				//=을 누른후에 다시 숫자를 입력하면 새로운 식이 시작됨
				if(saveMode)
					resultProcess.setText(resultProcess.getText() + "=" + resultText.getText());
				
				clearprocesstext = true;
				clearText=true;
				
			}
		}
		
			
			
			//-------------saveMode 숫자버튼 이벤트--------
		else{ 
			
			// 숫자 버튼 이벤트 
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
				
				if(clearprocesstext)   //  계산과정이 클리어된다
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
			
		
			//-----------------숫자버튼 이벤트--------
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
				if(clearText){ // 연산자 버튼 입력 했을 경우 화면 삭제 
					resultText.setText("");
					clearText=false;
					
				}
				resultText.setText(resultText.getText() + numberButton);
				
				
				break;
			}
			
		}
		
	}

	

    //------------------- Checkbox선택에 대한 Item리스너----------------
	public void itemStateChanged(ItemEvent e){  //ItemEvent리스너 자격 메쏘드
		String judge=cbg.getSelectedCheckbox().getLabel();
		
		if(judge == "Save Mode")    //Save Mode에서 일어날 작업들 1. 계산과정출력 2. 텍스트파일로 저장
		{
			resultText.setText("");
			saveMode=true;
		}
		
		if(judge == "Normal Mode")    //각 모드 전환시 화면 클리어되게 만들었다.
		{
			resultText.setText("");
			resultProcess.setText("");
			saveMode=false;
		}
	
	}



}


// 메인
public class JavaCalcu {
	public static void main(String[] args){
	new Calculator("Java Calculator");
	}
}


