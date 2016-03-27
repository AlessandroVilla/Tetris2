package fr.esiea.cours.tetris;

import java.util.Scanner;
// import fr.esiea.cours.tetris.Tetris;

public class Launcher {
	public static void main(String[] args) {
		// Tetris t = new Tetris(1);
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			private int connex;
			private int multi;
			Client c;
			Serveur s;
			
			public void run() {
				System.out.println("Bonjour");
				players();
				if(multi==2) 
					while(true)
						if(s != null)
							if(s.getconnexion()==true && c.getconnexion()==true) 
								break;

	//			JFrame frame = new JFrame("Tetris");
	//			frame.add(new Tetris(frame));
	//			frame.setSize(500, 900);
	//			frame.setLocationRelativeTo(null);
	//			frame.setResizable(false);
	//			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//			frame.setVisible(true);
			}
			
			public void players(){
				Scanner sc = new Scanner(System.in);
				while(true)
				{
					System.out.println("Voulez-vous jouer en solo(1) ou en multi(2)? 0 pour sortir.");
					if (sc.hasNextInt()) {
						multi = sc.nextInt();
						if(multi==0){
							System.out.println("Application terminée!");
							System.exit(0);
						}
						if(multi==1){
							System.out.println("Vous avez saisi : solo");
							break;
						}
						if(multi == 2){
							System.out.println("Vous avez saisi : multi");
							connexion();
							break;
						}
					} else {
						sc.next();
					}			
					System.out.println("Mode non valide.");
				}
				sc.close();
			}
			
			public void connexion()
			{
				double x, y;
				System.out.println("Etes-vous serveur(1) ou client(2)");
				Scanner sc = new Scanner(System.in);
				connex=sc.nextInt();
				if(connex==1) {
					s = new Serveur(1500);
					s.start();
					x = 0;
					y = 0;
					while(true) {
						boolean flag = s.getconnexion();
						if (flag) {
							System.out.print("T\n");
							break;
						} else {
								if (++x >= 1E9) {
									System.out.print(".");
									x = 0;
									if (++y >=80) {
										System.out.print("\n");
										y = 0;
									}
								}
						}
					}
					System.out.println("Class Tetris : Server started on port 1500");
					
					c=new Client(1501);
					c.start();
					x = 0;
					y = 0;
					while(true) {
						boolean flag = c.getconnexion();
						if (flag) {
							System.out.print("T\n");
							break;
						} else {
								if (++x >= 1E9) {
									System.out.print(".");
									x = 0;
									if (++y >=80) {
										System.out.print("\n");
										y = 0;
									}
								}
						}
					}
					System.out.println("Class Tetris : Client connected on port 1501");
					System.out.println("Fin du demarrage du serveur");
				}
				
				if(connex==2) {
					c = new Client(1500);
					c.start();
					x = 0;
					y = 0;
					while(true) {
						boolean flag = c.getconnexion();
						if (flag) {
							System.out.print("T\n");
							break;
						} else {
								if (++x >= 1E9) {
									System.out.print(".");
									x = 0;
									if (++y >=80) {
										System.out.print("\n");
										y = 0;
									}
								}
						}
					}
					System.out.println("Class Tetris : Client connected on port 1500");
					
					s=new Serveur(1501);
					s.start();
					x = 0;
					y = 0;
					while(true) {
						boolean flag = s.getconnexion();
						if (flag) {
							System.out.print("T\n");
							break;
						} else {
								if (++x >= 1E9) {
									System.out.print(".");
									x = 0;
									if (++y >=80) {
										System.out.print("\n");
										y = 0;
									}
								}
						}
					}
					System.out.println("Class Tetris : Server started on port 1501");
					System.out.println("Fin du demarrage du client");
				}
				
				sc.close();
			}
		});

	}
}