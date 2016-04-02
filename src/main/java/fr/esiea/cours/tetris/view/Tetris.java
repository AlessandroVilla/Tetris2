package fr.esiea.cours.tetris.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.esiea.cours.tetris.Client;
import fr.esiea.cours.tetris.Serveur;
import fr.esiea.cours.tetris.controller.PieceController;
import fr.esiea.cours.tetris.modele.Piecemodele;

@SuppressWarnings("serial")
public class Tetris extends JPanel {
	static JFrame frame;
	static Tetris game;
	int x = 0;int y = 0;
	static public int plateau[][];
	static PieceController pc;
	static Piecemodele pm;
	static PieceView pv;
	static Image img[] = new Image[10];
	static Image imgGO;
	static long sleep_time=700;
	static String IP;
	static Client c;
	static Serveur s;
	static int multi;
	int portserv;
	static int portclient;
	
	public Tetris() {
		initialisation();
	}
	public Tetris(String IP,int joueur) {
		Tetris.IP=IP;
		multi=1;
		initialisation();
		System.out.println("Mode Multijoueur");
		if(joueur==1){portserv =1500;portclient=1501;}
		else         {portserv =1501;portclient=1500;}
		s=new Serveur(portserv);
		s.start();
	}
	 void initialisation()
	 {
			pm = new Piecemodele();
			pc = new PieceController();
			pv = new PieceView();
			plateau = new int [pc.getCol()][pc.getLin()];
			for(int i=0; i<pc.getCol(); i++)  for(int j=0; j<pc.getLin(); j++) plateau[i][j]=0;
			for(int i =0; i<pc.getCol(); i++) plateau[i][pc.getLin()-1]=8;
			for (int i=0; i<10; i++) img[i] = pv.LoadDigit(i);
			imgGO = pv.LoadImage("GameOver.gif");
			pm.creationpiece();
			pc.setTabpiece(pm);
			plateau=pc.affiche_piece(plateau,pm.tableaupiece);
			KeyListener listener = new MyKeyListener();
			addKeyListener(listener);
			setFocusable(true);
	 }
		public static void frameinit() throws InterruptedException {
			frame = new JFrame("Tetris");
			game = new Tetris();
			frame.add(game);
			frame.setSize(547, 638);
			frame.setLocationRelativeTo(null);
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			System.out.println("Game Start");
			run();
		}
		public static void run() throws InterruptedException
		{
			while (true) {
				if(pc.isPerdu()){game.repaint();Thread.sleep(sleep_time*5);frame.dispose();break;}//visualise Game Over et met disable le KeyListener
				pc.descend(plateau, pm.tableaupiece);
				game.repaint();
				Thread.sleep(sleep_time);
				if(multi==1) multi();
			} 
		}
	public static void multi()
	{
		sendmalus();
		if (s.rc != null) {
			if ((s.getconnexion()==true) && (s.rc.getMalus() != null)){
				System.out.println("Apres sendmalus: " + s.rc.getMalus());
				if (s.rc.getMalus().equals("ScoreMalus")) {
					if (pc.getScore()>=1000) {
						pc.setScore((pc.getScore()-1000));
					} else {
						pc.setScore(0);
					}
				}
				if(s.rc.getMalus().equals("SpeedMalus"))sleep_time-=500;	
				s.rc.setMalus(null);
				
			}
		}
		
	}
	@Override
	 public void paint(Graphics g) {// Non réductible pour SOLID
		 super.paint(g);
		 for (int k=0; k<pc.getCol(); k++) {
			for (int l=0; l<pc.getLin(); l++) {
				if (plateau[k][l]==0) {
					g.setColor(new Color(160,160,160));
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
		 char[] text_tetris = { 'T', 'e', 't', 'r', 'i', 's'};
		 char[] text_score = { 'S', 'c', 'o', 'r', 'e'};
		 char[] text_author = { 'B', 'y', ':', ' ', 'A', 'l', 'e', 'x', 'a', 'n', 'd', 'r', 'e', ' ', 'V', 'I', 'L', 'L', 'A'};
		 g.setColor(Color.black);
		 g.draw3DRect (300,0,240,608,true);
		 g.fill3DRect (300,0,240,608,true);
		 g.setColor(Color.red);
		 g.setFont(new Font("Arial", 2,50)); 
		 g.drawChars(text_tetris, 0, 6, 360, 80);
		 g.setColor(Color.magenta);
		 g.setFont(new Font("Arial", 2, 25)); 
		 g.drawChars(text_score, 0, 5, 390, 290);
		 g.setColor(Color.white);
		 g.setFont(new Font("Arial", 1, 11)); 
		 g.drawChars(text_author, 0, 19, 370, 590);
		 setBackground(Color.WHITE);
		 int j = pc.getScore();
		 int z = j/10000;
		 if (z >= 0 && z <= 9)g.drawImage(img[z], 300, 300, 60, 80, null);
		 else g.drawImage(img[0], 300, 300, 60, 80, null);
		 j = j % 10000;z = j/1000;
		 if (z >= 0 && z <= 9)g.drawImage(img[z], 345, 300, 60, 80, null);
		 else g.drawImage(img[0], 345, 300, 60, 80, null);
		 j = j % 1000;z = j/100;
		 if (z >= 0 && z <= 9)g.drawImage(img[z], 390, 300, 60, 80, null);
		 else g.drawImage(img[0], 390, 300, 60, 80, null);
		 j = j % 100;z = j/10;
		 if (z >= 0 && z <= 9)g.drawImage(img[z], 435, 300, 60, 80, null);
		 else g.drawImage(img[0], 435, 300, 60, 80, null);
		 z = j % 10;
		 if (z >= 0 && z <= 9)g.drawImage(img[z], 480, 300, 60, 80, null); 
		 else g.drawImage(img[0], 480, 300, 60, 80, null);
		 if(pc.isPerdu()) g.drawImage(imgGO, 50, 200, 200, 200, null);
	} 
	private static void sendmalus() {
		if(pc.getCptsc()==10)
		{
			System.out.println("Send Malus");
			pc.setCptsc(0);
			c=new Client(portclient,IP);// design-pattern : lazy-initialization
			c.start();
			
		}
	}
	public class MyKeyListener implements KeyListener {
		public void keyTyped(KeyEvent e) {
		}
		public void keyPressed(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			switch(e.getKeyCode())
			{
				case KeyEvent.VK_LEFT:{pc.gauche(plateau,pm.tableaupiece);repaint();break;}
				case KeyEvent.VK_RIGHT:{pc.droite(plateau, pm.tableaupiece);repaint();break;}
				case KeyEvent.VK_SPACE:{pc.space(plateau, pm.tableaupiece);repaint();break;}
				case KeyEvent.VK_UP:{pc.rotateleft(plateau, pm.tableaupiece);repaint();break;}
				case KeyEvent.VK_DOWN:{pc.rotateright(plateau, pm.tableaupiece);repaint();break;}
			}	
		}
	}
}
