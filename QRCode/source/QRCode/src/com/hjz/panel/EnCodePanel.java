package com.hjz.panel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.hjz.filter.MyFilter;
import com.hjz.panel.ImagePanel;
import com.hjz.util.TwoDimensionCode;

public class EnCodePanel extends JPanel{
	private String logoPath = null;
	private TwoDimensionCode codeUtil = new TwoDimensionCode();
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image image = new ImageIcon(".images/bg.jpg").getImage();
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), 0, 0, image.getWidth(null), image.getHeight(null), null);
	}

	public EnCodePanel() {
		setForeground(Color.BLACK);
		setLayout(null);
		
		final JTextArea saveArea = new JTextArea();
		saveArea.setLineWrap(true);
		saveArea.setFont(new Font("宋体", Font.PLAIN, 20));
		saveArea.setForeground(Color.RED);
		saveArea.setEditable(false);
		saveArea.setBounds(26, 510, 499, 72);
		add(saveArea);
		
		final JTextField choosePathField = new JTextField();
		String jarFilePath = "./binaryCode";
        try {
            jarFilePath = URLDecoder.decode(jarFilePath, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
		choosePathField.setText(jarFilePath);
		choosePathField.setToolTipText("\u9ED8\u8BA4\u8DEF\u5F84\u4E3A\u5F53\u524D\u5E94\u7528\u6240\u5728\u8DEF\u5F84");
		choosePathField.setEditable(false);
		choosePathField.setBounds(129, 179, 209, 21);
		add(choosePathField);
		choosePathField.setColumns(10);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u5185\u5BB9");
		label.setFont(new Font("宋体", Font.BOLD, 15));
		label.setBounds(26, 20, 93, 29);
		add(label);
		
		JTextArea content = new JTextArea();
		content.setLineWrap(true);
		content.setFont(new Font("宋体", Font.PLAIN, 20));
		content.setBounds(26, 59, 312, 77);
		add(content);
		
		final ImagePanel codePane = new ImagePanel();
		codePane.setBackground(Color.WHITE);
		codePane.setToolTipText("");
		codePane.setBounds(49, 210, 290, 290);
		add(codePane);
		
		JButton codeBtn = new JButton("\u751F\u6210\u4E8C\u7EF4\u7801");
		codeBtn.setFont(new Font("宋体", Font.BOLD, 15));
		codeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isContentSuit(content.getText())) return;
				String imgName =  UUID.randomUUID().toString().substring(0, 8) + ".png";
				String savePath = choosePathField.getText() + "/" + imgName;
				codeUtil.encoderQRCode(content.getText(), savePath, "png", null);
				File imgFile = new File(savePath);
				if(imgFile.exists()){
					saveArea.setText("图片已经保存到:" + imgFile.getAbsolutePath());
					try {
						Image image = ImageIO.read(imgFile);
						codePane.setImage(image);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		codeBtn.setBounds(26, 140, 116, 29);
		add(codeBtn);
		
		JButton logoBtn = new JButton("\u751F\u6210\u5E26\u56FE\u7247\u4E8C\u7EF4\u7801");
		logoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isContentSuit(content.getText())) return;
				String imgName =  UUID.randomUUID().toString().substring(0, 8) + ".png";
				String savePath = choosePathField.getText() + "/" + imgName;
				if(logoPath == null) {
					JOptionPane.showMessageDialog(null, "请选择logo！", "提示", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				codeUtil.encoderQRCode(content.getText(), savePath, "png", logoPath);
				File imgFile = new File(savePath);
				if(imgFile.exists()){
					saveArea.setText("图片已经保存到:" + imgFile.getAbsolutePath());
					try {
						Image image = ImageIO.read(imgFile);
						codePane.setImage(image);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		logoBtn.setFont(new Font("宋体", Font.BOLD, 15));
		logoBtn.setBounds(165, 140, 173, 29);
		add(logoBtn);
		
		final ImagePanel logoPane = new ImagePanel();
		logoPane.setBackground(Color.WHITE);
		logoPane.setForeground(Color.LIGHT_GRAY);
		logoPane.setBounds(394, 20, 110, 110);
		add(logoPane);
		
		JLabel choosePathLabel = new JLabel("\u5B58\u50A8\u8DEF\u5F84:");
		choosePathLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		choosePathLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                //获取jar包位置，设置JFileChooser当前路径
                String jarFilePath = "./binaryCode";
                try {
                    jarFilePath = URLDecoder.decode(jarFilePath, "UTF-8");
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
                jfc.setCurrentDirectory(new File(jarFilePath));
                jfc.showOpenDialog(null);
                File fl = jfc.getSelectedFile();
                if(fl != null)
                	choosePathField.setText(fl.getAbsolutePath());
			}
		});
		choosePathLabel.setForeground(Color.RED);
		choosePathLabel.setBackground(Color.BLACK);
		choosePathLabel.setFont(new Font("华文隶书", Font.BOLD, 18));
		choosePathLabel.setBounds(26, 179, 93, 21);
		add(choosePathLabel);
		
		JButton chooseLogoBtn = new JButton("\u9009\u62E9logo");
		chooseLogoBtn.addActionListener(new ActionListener() {
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
                Image image = compressImage(logoPath = fl.getAbsolutePath());
                logoPane.setImage(image);
			}
		});
		chooseLogoBtn.setFont(new Font("宋体", Font.BOLD, 15));
		chooseLogoBtn.setBounds(388, 140, 116, 29);
		add(chooseLogoBtn);
	}
	
	//压缩图片
	private Image compressImage(String imgPath){
		final int INSERT_IMG_SIZE = 100;
		Image src = null;
		try {
			src = ImageIO.read(new File(imgPath));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
        int width = src.getWidth(null);  
        int height = src.getHeight(null);  
        if (width > INSERT_IMG_SIZE) {  
            width = INSERT_IMG_SIZE;  
        }  
        if (height > INSERT_IMG_SIZE) {  
            height = INSERT_IMG_SIZE;  
        }  
        Image image = src.getScaledInstance(width, height,  
                Image.SCALE_SMOOTH);  
        BufferedImage tag = new BufferedImage(width, height,  
                BufferedImage.TYPE_INT_RGB);  
        Graphics g = tag.getGraphics();  
        g.drawImage(image, 0, 0, null); // 绘制缩小后的图  
        g.dispose();  
        return image;
	}
	
	private boolean isContentSuit(String content){
		if(content.getBytes().length <=0 || content.getBytes().length >= 800){
			JOptionPane.showMessageDialog(null, "内容过长或者不存在!", "提示", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
