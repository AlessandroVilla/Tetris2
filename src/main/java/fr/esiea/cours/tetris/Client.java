package fr.esiea.cours.tetris;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {
	
	private static Scanner sc;
	public String message_distant;
	private boolean connexion=false;
	private int port;
	public Client(int name)
	{
		this.port=name;
	//	System.out.println("Port passé = " + this.port);
	}
	public void run(){
		Socket socket;
		BufferedReader in;
//		int port=1500;
		try {
		sc = new Scanner(System.in);
		System.out.println("Entrez l'ip du serveur : ");
		String string=sc.nextLine();
		socket = new Socket(string,port);
		System.out.println("Demande de connexion au port "+port);
		in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
		System.out.println("Connexion réussie!");
		connexion=true;
		System.out.println(connexion);
		while(true)
		{
			String message_distant = in.readLine();
			System.out.println(message_distant);
			if(message_distant.equals("stop")) break;
		}
		System.out.println("Connexion terminée");
		socket.close();
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public boolean getconnexion(){
		// System.out.println("Client : " + this.connexion);
		return connexion;
	}
}
