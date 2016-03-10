package fr.esiea.cours.tetris;

import java.util.Scanner;

public class Launcher {
	public static void main(String[] args) {	
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Scanner sc = new Scanner(System.in);
				System.out.println("Bonjour");
				System.out.println("Voulez-vous jouer en solo ou en multi?");
				int multi;
				while(1==1)
				{
					multi = sc.nextInt();
					if(multi == 2)
					{
						System.out.println("Vous avez saisi : multi");
						break;
					}
					if(multi==1)
					{
						System.out.println("Vous avez saisi : solo");
						break;
					}
					else System.out.println("Veuillez resaisir le mode : 1 pour solo , 2 pour multi");
				}
				Tetris t = new Tetris(multi);
				t.setVisible(true);
			}
		});
	}
}
