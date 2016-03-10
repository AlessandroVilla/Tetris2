package fr.esiea.cours.tetris;

import java.util.Scanner;

public class Launcher {
	public static void main(String[] args) {	
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			private int connex;
			private int multi;
			public void run() {
				System.out.println("Bonjour");
				players();
				Tetris t = new Tetris(multi);
				t.setVisible(true);
			}
			public void players(){
				System.out.println("Voulez-vous jouer en solo(1) ou en multi(2)?");
				Scanner sc = new Scanner(System.in);
				while(true)
				{
					multi = sc.nextInt();
					if(multi==1){
						System.out.println("Vous avez saisi : solo");
						break;}
					if(multi == 2){
						System.out.println("Vous avez saisi : multi");
						connexion();
						break;}
					else System.out.println("Veuillez re-saisir le mode : 1 pour solo , 2 pour multi");
				}
			}
			public void connexion()
			{
				System.out.println("Etes-vous serveur(1) ou client(2)");
				Scanner sc = new Scanner(System.in);
				connex=sc.nextInt();
				if(connex==1)
				{
					
					new Serveur();
					Serveur.main(null);
				}
				if(connex==2)
				{
					new Client();
					Client.main(null);
				}
			}
		});
	}
}
