package com.hjz.main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.hjz.panel.DeCodePanel;
import com.hjz.panel.EnCodePanel;

public class CodeFrame extends JFrame{
	private static int FW = 600;
	private static int FH = 650;
	private void initFrame(){
		JTabbedPane tabPane = new JTabbedPane();
		EnCodePanel enCodePanel = new EnCodePanel();
		DeCodePanel deCodePanel = new DeCodePanel();
		JCheckBoxMenuItem jcbmi = null;
		tabPane.setFont(new Font("宋体", Font.BOLD, 20));
		tabPane.add("编码", enCodePanel);
		tabPane.add("解码", deCodePanel);
		this.add(tabPane);
		this.setIconImage(new ImageIcon("./images/icon.png").getImage());
	    //设置界面外观的样式
        try {
    	   UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
		   e.printStackTrace();
        }
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width-FW)/2, (screenSize.height-FH)/2, FW, FH);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public CodeFrame() throws HeadlessException {
		super();
		initFrame();
	}

	public CodeFrame(String title) throws HeadlessException {
		super(title);
		initFrame();
	}
	
	public static void main(String[] args){
		new CodeFrame("二维码编码和解码");
	}
}
