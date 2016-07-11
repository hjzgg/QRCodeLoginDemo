package com.hjz.panel;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	private Image image = null; 
	public void setImage(Image image){
		this.image = image;
		this.updateUI();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(image != null) {
			int iw = image.getWidth(null);
			int ih = image.getHeight(null);
			if(!(iw > this.getWidth() || ih > this.getHeight())){
				int dx = (this.getWidth()-iw)/2;
				int dy = (this.getHeight()-ih)/2;
				g.drawImage(image, dx, dy, dx+iw, dy+ih, 0, 0, iw, ih, null);
			} else {
				g.drawImage(image, 0, 0,this.getWidth(), this.getHeight(), 0, 0, iw, ih, null);
			}
		}
	}
}
