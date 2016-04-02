package fr.esiea.cours.tetris;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import fr.esiea.cours.tetris.view.Tetris;

public class Launcher2 {
	private static String connex;
	private static int multi;
	private static int joueur;
	public static void main(String[] args) {
		players();
		if(multi==1)
		{
			try {
				new Tetris();
				Tetris.frameinit();
			} catch (InterruptedException e) {
				e.printStackTrace();
			};}
		if(multi==2)
		{
			try {
				new Tetris(connex,joueur);Tetris.frameinit();
			} catch (InterruptedException e) {
				e.printStackTrace();
			};}
	}
	public static void players(){
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
			} else sc.next();			
			System.out.println("Mode non valide.");
		}sc.close();
	}
	public static void connexion()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrer l'IP de l'autre joueur :");
		while (((connex = sc.nextLine()) != null)) {
			if(!connex.equals("")) {
				if (verif_adresse_IP(connex)) {
					break;
				} else {
						System.out.println("Adresse IP non valide (" + connex + ")");
						System.out.println("Veuillez entrer l'IP de l'autre joueur :");
				}
			}
		}
		while(true)
		{
			System.out.println("Etes-vous le Joueur 1 ou 2?");
			joueur=sc.nextInt();
			if(joueur==1 || joueur==2)break;
		}
		sc.close();
	}
	public static boolean verif_adresse_IP( String ip ) {
		if (ip == null || ip.isEmpty()) return false;
		ip = ip.trim();
		if ((ip.length() < 6) & (ip.length() > 15)) return false;
		try {
		Pattern pattern = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
		Matcher matcher = pattern.matcher(ip);
		return matcher.matches();
		}
		catch (PatternSyntaxException ex) {
			return false;
		}
	}
}