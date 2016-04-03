package fr.esiea.cours.tetris;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Serveur extends Thread {
	public static Scanner sc;
	private boolean connexion = false;
	private int port;
	public String str=null;
	public Thread t1;
	public ReadClient rc;
	//private String malus;
	
	public Serveur(int name)
	{
		this.port=name;
	}
	public void run(){
		ServerSocket socketserver = null  ;
		Socket socketduserveur ;
	//	malus=null;
		System.out.println("Port passé = " + this.port);
			try {
				socketserver = new ServerSocket(port);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.println("Le serveur est à l'écoute sur le port "+socketserver.getLocalPort());
			connexion=true;	          
	        try {
				while(true){
					socketduserveur = socketserver.accept();
					System.out.println("Adversaire connecté!  ");
					//rc = new ReadClient(socketduserveur, malus);
					rc=new ReadClient(socketduserveur);
					t1 = new Thread(rc);
					t1.start();
					System.out.println("Changement de la variable malus");
				}
			} catch (IOException e)	{ System.out.println("Erreur serveur"); }
	
	}	
	public boolean getconnexion(){
		return connexion;
	}
}