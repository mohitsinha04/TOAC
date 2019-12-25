package dto;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Color;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.lang.Math;

public class ChatBot extends JFrame implements KeyListener{

	JPanel p=new JPanel();
	JTextArea dialog=new JTextArea(30,50);
	JTextArea input=new JTextArea(1,50);
	JScrollPane scroll=new JScrollPane(
		dialog,
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
		);

	//This contains all the phrases---->
	String[][] chatBot={
		//standard
		{"hi","hello","hola","namaste","pranam"},
		{"hi","hello","hey","pranam sir"},
		//questions
		{"how are you","kaise ho","how are you doing","how r u","how are u","hw r u"},
		{"good","doing well","ache hai!","Bindash hai! :)"},
		//mislenious
		{"i love you","i love u"},
		{"i love you too", "i love u 2!","you are cute","dont be flirty with me!!"},
		//default
		{"shut up","chup ho jao ab","bye ab meko kaam hai","huh, ab chato mt, bye!!",
	     "(Mohit is not available, due to lazinesssssss......!!!!!!)"},
	};

	public static void main(String[] args){
		new ChatBot();
	} 

	public ChatBot(){
		super("Chat Bot");
		setSize(600,400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		dialog.setEditable(false);
		input.addKeyListener(this);

		p.add(scroll);
		p.add(input);
		p.setBackground(new Color(153,255,204));
		add(p);

		setVisible(true);
	}
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(false);
			//quote--
			String quote=input.getText();
			input.setText("");
			addText("-->You:\t"+quote);
			quote.trim();
			//punctuation
			while(
				quote.charAt(quote.length()-1)=='!'||
				quote.charAt(quote.length()-1)=='.'||
				quote.charAt(quote.length()-1)=='?')
			{
				quote=quote.substring(0,quote.length()-1);   //take away punctuation!
			}
			quote.trim();
			byte response=0;
			/*
			0:we are searching through chatbot[][] for matches
			1:we didnt find anything in chatbot
			2:we did find something 
			*/
			//matches or duplicates---
			int j=0;//which grp we r checking
			while(response==0){
				if(inArray(quote.toLowerCase(),chatBot[j*2])){
					response=2;
					int r=(int)Math.floor(Math.random()*chatBot[(j*2)+1].length);
					addText("\n-->Mohit\t"+chatBot[(j*2)+1][r]);
				}
				j++;
				if(j*2==chatBot.length-1 && response==0){
					response=1;
				}
			}
			//default
			if(response==1){
				int r=(int)Math.floor(Math.random()*chatBot[chatBot.length-1].length);
				addText("\n-->Mohit\t"+chatBot[chatBot.length-1][r]);
			}
			addText("\n");
		}
	}

	public void keyReleased(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(true);
		}
	}

	public void keyTyped(KeyEvent e){}

	public void addText(String str){
		dialog.setText(dialog.getText()+str);
	}

	public boolean inArray(String in,String[] str){
		boolean match=false;
		for(int i=0;i<str.length;i++){
			if(str[i].equals(in)){
				match=true;
			}
		}
		return match;
	}
}