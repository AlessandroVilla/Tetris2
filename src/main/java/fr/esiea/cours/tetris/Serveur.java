package fr.esiea.cours.tetris;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Serveur extends Thread {
	public static Scanner sc;
	private boolean connexion = false;
	private int port;
	public Serveur(int name)
	{
		this.port=name;
	//	System.out.println("Port passé = " + this.port);
	}
	public void run(){
		ServerSocket socketserver  ;
		Socket socketduserveur ;
		PrintWriter out;
		sc = new Scanner(System.in);
		try {
			socketserver = new ServerSocket(port);
			System.out.println("Le serveur est à l'écoute sur le port "+socketserver.getLocalPort());
			socketduserveur = socketserver.accept(); 
			connexion=true;
	//		System.out.println("Server connexion flag = " + connexion);
			out = new PrintWriter(socketduserveur.getOutputStream());
			while(true)	
			{
				String string=sc.nextLine();
				if(string.equals("display")) System.out.println(socketduserveur);
				if(string.equals("stop")) {
					out.println(string);
					out.flush();
					break;
				}
			}
			socketduserveur.close();
			socketserver.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public boolean getconnexion(){
	//	System.out.println("Serveur: " + this.connexion);
		return connexion;
	}
}