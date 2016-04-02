package fr.esiea.cours.tetris.view;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class PieceView {
	public Image LoadDigit(int i) {
		char c = Character.forDigit(i, 10); 
		String imgFileName = c + ".PNG";
		Image img = null;
		URL imgUrl = getClass().getClassLoader().getResource(imgFileName);
		if (imgUrl == null) {
			System.err.println("Couldn't find file: " + imgFileName);
		} else {
			try {
				img = ImageIO.read(imgUrl);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return img;
	}
	
	public Image LoadImage(String name) {	
		String imgFileName = name;
		Image img = null;
		URL imgUrl = getClass().getClassLoader().getResource(imgFileName);
		if (imgUrl == null) {
			System.err.println("Couldn't find file: " + imgFileName);
		} else {
			try {
				img = ImageIO.read(imgUrl);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return img;
	}
}