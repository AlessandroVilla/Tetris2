package fr.esiea.cours.tetris;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

public class Client extends Thread {
	
	public String message_distant;
	private int port;
	private String IP;
	String str;
	public Client(int name, String IP)
	{
		this.IP=IP;
		this.port=name;
	}
	public void run(){
		Socket socket;
		try {
		socket = new Socket(IP,port);
		System.out.println("Demande de connexion au port "+port);
		System.out.println("Connexion réussie!");
		sending(socket);
		System.out.println("Connexion terminée");
		socket.close();
		} catch (ConnectException e) {System.out.println("Connexion non réussi au port tcp de l'adversaire");
		} catch (SocketException e) {System.out.println("Connexion adversaire terminée");
		} catch (IOException e) {e.printStackTrace();}
	}
	public void sending(Socket socket) throws IOException
	{
		int random = (int)(Math.random() * 2);
        BufferedReader plec = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pred = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
		if( random == 0)
		{
			str = "SpeedMalus";
	        pred.println(str);          // envoi d'un message
	        str = plec.readLine();      // lecture de l'écho
		}
		else
		{
			str="ScoreMalus";
		    pred.println(str);          // envoi d'un message
		    str = plec.readLine();      // lecture de l'écho
		}
	}
}