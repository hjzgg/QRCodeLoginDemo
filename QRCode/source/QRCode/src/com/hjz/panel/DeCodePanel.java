package com.hjz.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextArea;

import com.hjz.filter.MyFilter;
import com.hjz.util.TwoDimensionCode;

public class DeCodePanel extends JPanel{
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image image = new ImageIcon("./images/bg.jpg").getImage();
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), 0, 0, image.getWidth(null), image.getHeight(null), null);
	}
	
	private String imgPath = null;
	private TwoDimensionCode codeUtil = new TwoDimensionCode();
	public DeCodePanel() {
		setLayout(null);
		
		JLabel nameLabel = new JLabel("\u4E8C\u7EF4\u7801\u56FE\u7247");
		nameLabel.setFont(new Font("宋体", Font.BOLD, 16));
		nameLabel.setBounds(30, 25, 92, 24);
		add(nameLabel);
		
		final ImagePanel imagePanel = new ImagePanel();
		imagePanel.setToolTipText("");
		imagePanel.setBackground(Color.WHITE);
		imagePanel.setBounds(72, 85, 290, 290);
		add(imagePanel);
		
		JButton chooseImgBtn = new JButton("\u9009\u62E9\u56FE\u7247");
		chooseImgBtn.setFont(new Font("宋体", Font.BOLD, 16));
		chooseImgBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				//设置文件的过滤器
                String[] filterString = {".png", ".jpg", ".icon"};
                MyFilter filter = new MyFilter(filterString);
                //获取jar包位置，设置JFileChooser当前路径
                String jarFilePath = "./binaryCode";
                try {
                    jarFilePath = URLDecoder.decode(jarFilePath, "UTF-8");
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
                jfc.setCurrentDirectory(new File(jarFilePath));
                jfc.setFileFilter(filter);
                jfc.showOpenDialog(null);
                File fl = jfc.getSelectedFile();
                if(fl.exists()) {
                	imgPath = fl.getAbsolutePath();
                	try {
						Image image = ImageIO.read(fl);
						imagePanel.setImage(image);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
                }
			}
		});
		chooseImgBtn.setBounds(154, 26, 115, 23);
		add(chooseImgBtn);
		
		final JTextArea resultArea = new JTextArea();
		resultArea.setForeground(Color.RED);
		resultArea.setLineWrap(true);
		resultArea.setEditable(false);
		resultArea.setBounds(30, 445, 374, 116);
		add(resultArea);
		
		JButton deCodeBtn = new JButton("\u89E3\u7801");
		deCodeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(imgPath == null) {
					JOptionPane.showMessageDialog(null, "请选择二维码图片!", "提示", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				String result = codeUtil.decoderQRCode(imgPath);
				resultArea.setText(result);
			}
		});
		deCodeBtn.setFont(new Font("宋体", Font.BOLD, 16));
		deCodeBtn.setBounds(312, 27, 92, 23);
		add(deCodeBtn);
		
		JLabel contentLabel = new JLabel("\u5185\u5BB9\u5982\u4E0B");
		contentLabel.setFont(new Font("宋体", Font.BOLD, 16));
		contentLabel.setBounds(30, 411, 92, 24);
		add(contentLabel);
	}
}
