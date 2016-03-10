package fr.esiea.cours.tetris;

import javax.swing.JFrame;

import fr.esiea.cours.tetris.controller.PieceController;
import fr.esiea.cours.tetris.modele.Piecemodele;

@SuppressWarnings("serial")
public class Tetris extends JFrame implements Runnable  {
	public int plateau[][];
	PieceController pc;
	Piecemodele pm;
	int multi;
	Thread thread;
	public void init(){
		plateau = new int [12][25];
		for(int i=0;i<12;i++)
			for(int j=0;j<25;j++)
				plateau[i][j]=0;
		for(int i =0;i < 12;i++) plateau[i][24]=1;
	}
	public Tetris(int multi)
	{
		this.multi=multi;
		start();
	}
	public void start()
	{
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	public void run(){
		System.out.println("Game Start");
		init();
		pc = new PieceController();
		pm = new Piecemodele();
		pm.creationpiece();
	}
}
