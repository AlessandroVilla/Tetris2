package fr.esiea.cours.tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.esiea.cours.tetris.controller.PieceController;
import fr.esiea.cours.tetris.modele.Piecemodele;

@SuppressWarnings("serial")
public class Tetris extends JPanel {

	int x = 0;
	int y = 0;
	static public int plateau[][];
	static PieceController pc;
	static Piecemodele pm;
	final static long sleep_time=700;

	public Tetris() {
		pm = new Piecemodele();
		pc = new PieceController();
		plateau = new int [pc.getCol()][pc.getLin()];
		for(int i=0; i<pc.getCol(); i++)
			for(int j=0; j<pc.getLin(); j++)
				plateau[i][j]=0;
		for(int i =0; i<pc.getCol(); i++) plateau[i][pc.getLin()-1]=8;
		
		pm.creationpiece();
		pc.setTabpiece(pm);
		plateau=pc.affiche_piece(plateau,pm.tableaupiece);
		
		KeyListener listener = new MyKeyListener();
		addKeyListener(listener);
		setFocusable(true);
		
	}
	
	 @Override
	 public void paint(Graphics g) {
		 super.paint(g);
		 for (int k=0; k<pc.getCol(); k++) {
			for (int l=0; l<pc.getLin(); l++) {
				if (plateau[k][l]==0) {
					g.setColor(Color.white);
					g.draw3DRect (25*k,25*l,25,25,true);
				}
				else
				{
					switch (plateau[k][l]) {
					case 1:     g.setColor(new Color(0,192,0));       g.fill3DRect(25*k,25*l,25,25,true); break; //vert
					case 2:     g.setColor(Color.pink);               g.fill3DRect(25*k,25*l,25,25,true); break; //rouge
					case 3:     g.setColor(new Color(0,128,224));     g.fill3DRect(25*k,25*l,25,25,true); break; //bleu
					case 4:     g.setColor(new Color(0,192,192));     g.fill3DRect(25*k,25*l,25,25,true); break; //cyan
					case 5:     g.setColor(Color.orange);             g.fill3DRect(25*k,25*l,25,25,true); break; //orange
					case 6:     g.setColor(Color.darkGray);           g.fill3DRect(25*k,25*l,25,25,true); break; //gris
					case 7:     g.setColor(Color.magenta);            g.fill3DRect(25*k,25*l,25,25,true); break; //magenta
					case 8:     g.setColor(Color.black);              g.fill3DRect(25*k,25*l,25,10,true); break; //noir
					}
				}
			}
		}

	} 

		
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Tetris");
		Tetris game = new Tetris();
		frame.add(game);
		frame.setSize(306, 638);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		System.out.println("Game Start");
		
		while (true) {

			pc.descend(plateau, pm.tableaupiece);
			game.repaint();
			Thread.sleep(sleep_time);
		} 
	
	}
	
	public class MyKeyListener implements KeyListener {
		public void keyTyped(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
	//		System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
		}

		public void keyReleased(KeyEvent e) {
			switch(e.getKeyCode())
			{
				case KeyEvent.VK_LEFT:
				{
					if (!pc.BG(plateau, pm.tableaupiece)) {
						pc.clean(plateau, pm.tableaupiece);
						int x = pc.getX();
						x--;
						pc.setX(x);
						plateau=pc.affiche_piece(plateau,pm.tableaupiece);
						repaint();
					}
					break;
				}
				case KeyEvent.VK_RIGHT:
				{
					if (!pc.BD(plateau, pm.tableaupiece)) {
						pc.clean(plateau, pm.tableaupiece);
						int x = pc.getX();
						x++;
						pc.setX(x);
						plateau=pc.affiche_piece(plateau,pm.tableaupiece);
						repaint();
					}
					break;
				}
				case KeyEvent.VK_SPACE:
				{
					int y = pc.getY();
					pc.setFlagPiece(false);
		
					while (!pc.getFlagPiece()) {
						pc.descend(plateau, pm.tableaupiece);
						repaint();
					}
			//		System.out.println("space=drop");
				//	plateau=pc.affiche_piece(plateau,pm.tableaupiece);
			//		repaint();
					break;
				}
				case KeyEvent.VK_UP:
				{
					System.out.println("space=rotate-right");
					plateau=pc.affiche_piece(plateau,pm.tableaupiece);
					repaint();
					break;
				}
				case KeyEvent.VK_DOWN:
				{
					System.out.println("space=rotate-left");
					plateau=pc.affiche_piece(plateau,pm.tableaupiece);
					repaint();
					break;
				}
			}	
		}
	}
}
