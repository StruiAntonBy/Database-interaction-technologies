package view;

import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import controller.NextStartFrameButtonClick;
import controller.NextTask1StartFrameButtonClick;
import controller.NextTask21StartFrameButtonClick;
import controller.NextTask22StartFrameButtonClick;
import controller.NextTask23StartFrameButtonClick;
import controller.NextTask24StartFrameButtonClick;
import controller.NextTask25StartFrameButtonClick;
import controller.Task31StartFrameButtonClick;
import controller.Task32StartFrameButtonClick;
import controller.Task33StartFrameButtonClick;
import controller.Task41StartFrameButtonClick;
import controller.Task42StartFrameButtonClick;
import helper.DigitFilter;
import ioc.IocContainer;

public class StartFrame extends JFrame{
	private JTextField nameTableTextField,nameTableTask1TextField,pkTask1TextField,pkTask21TextField,pkTask22TextField,pkTask23TextField,pkTask24TextField,pkTask25TextField;
	
	public StartFrame(IocContainer iocContainer) throws HeadlessException {
		super("Laboratory 7");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel label = new JLabel("Enter the name of the table where you want to make changes:");
		label.setLocation(20,20);
		label.setSize(350,20);
	    panel.add(label);
	    
	    nameTableTextField= new JTextField(30);
	    nameTableTextField.setLocation(340,20);
	    nameTableTextField.setSize(200,20);
	    panel.add(nameTableTextField);
	    
	    JButton nextButton = new JButton("Next");
	    nextButton.setLocation(550,15);
	    nextButton.setSize(75,30);
	    nextButton.addActionListener(new NextStartFrameButtonClick(this,iocContainer));
	    panel.add(nextButton);
	    
	    JLabel lab4Label = new JLabel("Lab 4");
	    lab4Label.setLocation(340,60);
	    lab4Label.setSize(100,20);
	    panel.add(lab4Label);
	    
	    JLabel task1Label = new JLabel("Task 1");
	    task1Label.setLocation(20,80);
	    task1Label.setSize(100,20);
	    panel.add(task1Label);
	    
	    JLabel task1TableNameLabel = new JLabel("Table name:");
	    task1TableNameLabel.setLocation(20,110);
	    task1TableNameLabel.setSize(100,20);
	    panel.add(task1TableNameLabel);
	    
	    nameTableTask1TextField= new JTextField(30);
	    nameTableTask1TextField.setLocation(100,110);
	    nameTableTask1TextField.setSize(200,20);
	    panel.add(nameTableTask1TextField);
	    
	    JLabel task1PrimaryKeyLabel = new JLabel("Primary key:");
	    task1PrimaryKeyLabel.setLocation(20,140);
	    task1PrimaryKeyLabel.setSize(110,20);
	    panel.add(task1PrimaryKeyLabel);
	    
	    pkTask1TextField=new JTextField(30);
	    PlainDocument doc = (PlainDocument) pkTask1TextField.getDocument();
		doc.setDocumentFilter(new DigitFilter());
	    pkTask1TextField.setLocation(100,140);
	    pkTask1TextField.setSize(200,20);
	    panel.add(pkTask1TextField);
	    
	    JButton nextTask1Button = new JButton("Next");
	    nextTask1Button.addActionListener(new NextTask1StartFrameButtonClick(this,iocContainer));
	    nextTask1Button.setLocation(100,170);
	    nextTask1Button.setSize(75,30);
	    panel.add(nextTask1Button);
	    
	    JLabel task2Label = new JLabel("Task 2");
	    task2Label.setLocation(20,200);
	    task2Label.setSize(100,20);
	    panel.add(task2Label);
	    
	    JLabel task21PrimaryKeyLabel = new JLabel("Primary key:");
	    task21PrimaryKeyLabel.setLocation(20,240);
	    task21PrimaryKeyLabel.setSize(110,20);
	    panel.add(task21PrimaryKeyLabel);
	    
	    pkTask21TextField=new JTextField(30);
	    PlainDocument doc1 = (PlainDocument) pkTask21TextField.getDocument();
		doc1.setDocumentFilter(new DigitFilter());
		pkTask21TextField.setLocation(100,240);
		pkTask21TextField.setSize(200,20);
	    panel.add(pkTask21TextField);
	    
	    JButton nextTask21Button = new JButton("Next");
	    nextTask21Button.addActionListener(new NextTask21StartFrameButtonClick(this,iocContainer));
	    nextTask21Button.setLocation(100,270);
	    nextTask21Button.setSize(75,30);
	    panel.add(nextTask21Button);
	    
	    JLabel task22PrimaryKeyLabel = new JLabel("Primary key:");
	    task22PrimaryKeyLabel.setLocation(20,310);
	    task22PrimaryKeyLabel.setSize(110,20);
	    panel.add(task22PrimaryKeyLabel);
	    
	    pkTask22TextField=new JTextField(30);
	    PlainDocument doc2 = (PlainDocument) pkTask22TextField.getDocument();
		doc2.setDocumentFilter(new DigitFilter());
		pkTask22TextField.setLocation(100,310);
		pkTask22TextField.setSize(200,20);
	    panel.add(pkTask22TextField);
	    
	    JButton nextTask22Button = new JButton("Next");
	    nextTask22Button.addActionListener(new NextTask22StartFrameButtonClick(this,iocContainer));
	    nextTask22Button.setLocation(100,340);
	    nextTask22Button.setSize(75,30);
	    panel.add(nextTask22Button);
	    
	    JLabel task23PrimaryKeyLabel = new JLabel("Primary key:");
	    task23PrimaryKeyLabel.setLocation(20,380);
	    task23PrimaryKeyLabel.setSize(110,20);
	    panel.add(task23PrimaryKeyLabel);
	    
	    pkTask23TextField=new JTextField(30);
	    PlainDocument doc3 = (PlainDocument) pkTask23TextField.getDocument();
		doc3.setDocumentFilter(new DigitFilter());
		pkTask23TextField.setLocation(100,380);
		pkTask23TextField.setSize(200,20);
	    panel.add(pkTask23TextField);
	    
	    JButton nextTask23Button = new JButton("Next");
	    nextTask23Button.addActionListener(new NextTask23StartFrameButtonClick(this,iocContainer));
	    nextTask23Button.setLocation(100,410);
	    nextTask23Button.setSize(75,30);
	    panel.add(nextTask23Button);
	    
	    JLabel task24PrimaryKeyLabel = new JLabel("Primary key:");
	    task24PrimaryKeyLabel.setLocation(20,450);
	    task24PrimaryKeyLabel.setSize(110,20);
	    panel.add(task24PrimaryKeyLabel);
	    
	    pkTask24TextField=new JTextField(30);
	    PlainDocument doc4 = (PlainDocument) pkTask24TextField.getDocument();
		doc4.setDocumentFilter(new DigitFilter());
		pkTask24TextField.setLocation(100,450);
		pkTask24TextField.setSize(200,20);
	    panel.add(pkTask24TextField);
	    
	    JButton nextTask24Button = new JButton("Next");
	    nextTask24Button.addActionListener(new NextTask24StartFrameButtonClick(this,iocContainer));
	    nextTask24Button.setLocation(100,480);
	    nextTask24Button.setSize(75,30);
	    panel.add(nextTask24Button);
	    
	    JLabel task25PrimaryKeyLabel = new JLabel("Primary key:");
	    task25PrimaryKeyLabel.setLocation(20,520);
	    task25PrimaryKeyLabel.setSize(110,20);
	    panel.add(task25PrimaryKeyLabel);
	    
	    pkTask25TextField=new JTextField(30);
	    PlainDocument doc5 = (PlainDocument) pkTask25TextField.getDocument();
		doc5.setDocumentFilter(new DigitFilter());
		pkTask25TextField.setLocation(100,520);
		pkTask25TextField.setSize(200,20);
	    panel.add(pkTask25TextField);
	    
	    JButton nextTask25Button = new JButton("Next");
	    nextTask25Button.addActionListener(new NextTask25StartFrameButtonClick(this,iocContainer));
	    nextTask25Button.setLocation(100,550);
	    nextTask25Button.setSize(75,30);
	    panel.add(nextTask25Button);
	    
	    JButton task31Button = new JButton("Task 3.1");
	    task31Button.addActionListener(new Task31StartFrameButtonClick(iocContainer));
	    task31Button.setLocation(20,600);
	    task31Button.setSize(75,30);
	    panel.add(task31Button);
	    
	    JButton task32Button = new JButton("Task 3.2");
	    task32Button.addActionListener(new Task32StartFrameButtonClick(iocContainer));
	    task32Button.setLocation(100,600);
	    task32Button.setSize(75,30);
	    panel.add(task32Button);
	    
	    JButton task33Button = new JButton("Task 3.3");
	    task33Button.addActionListener(new Task33StartFrameButtonClick(iocContainer));
	    task33Button.setLocation(180,600);
	    task33Button.setSize(75,30);
	    panel.add(task33Button);
	    
	    JButton task41Button = new JButton("Task 4.1");
	    task41Button.addActionListener(new Task41StartFrameButtonClick(iocContainer));
	    task41Button.setLocation(20,640);
	    task41Button.setSize(75,30);
	    panel.add(task41Button);
	    
	    JButton task42Button = new JButton("Task 4.2");
	    task42Button.addActionListener(new Task42StartFrameButtonClick(iocContainer));
	    task42Button.setLocation(100,640);
	    task42Button.setSize(75,30);
	    panel.add(task42Button);
	    
	    add(panel);
	    
		setVisible(true);
	}
	
	public String getNameTable() {
		return nameTableTextField.getText();
	}
	
	public String getNameTableTask1() {
		return nameTableTask1TextField.getText();
	}
	
	public Long getPrimarykeyTask1() {
		if(pkTask1TextField.getText().trim().length()!=0) {
			return Long.parseLong(pkTask1TextField.getText());
		}
		else {
			return null;
		}
	}
	
	public Long getPrimarykeyTask21() {
		if(pkTask21TextField.getText().trim().length()!=0) {
			return Long.parseLong(pkTask21TextField.getText());
		}
		else {
			return null;
		}
	}
	
	public Long getPrimarykeyTask22() {
		if(pkTask22TextField.getText().trim().length()!=0) {
			return Long.parseLong(pkTask22TextField.getText());
		}
		else {
			return null;
		}
	}
	
	public Long getPrimarykeyTask23() {
		if(pkTask23TextField.getText().trim().length()!=0) {
			return Long.parseLong(pkTask23TextField.getText());
		}
		else {
			return null;
		}
	}
	
	public Long getPrimarykeyTask24() {
		if(pkTask24TextField.getText().trim().length()!=0) {
			return Long.parseLong(pkTask24TextField.getText());
		}
		else {
			return null;
		}
	}
	
	public Long getPrimarykeyTask25() {
		if(pkTask25TextField.getText().trim().length()!=0) {
			return Long.parseLong(pkTask25TextField.getText());
		}
		else {
			return null;
		}
	}
}
