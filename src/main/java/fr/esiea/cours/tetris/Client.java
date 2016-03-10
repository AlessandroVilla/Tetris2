package fr.esiea.cours.tetris;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	private static Scanner sc;
	public String message_distant;

	public static void main(String[] zero){

		Socket socket;
		BufferedReader in;
		try {
		sc = new Scanner(System.in);
		System.out.println("Entrez l'ip du serveur : ");
		String string=sc.nextLine();
		socket = new Socket(string,2009);
		System.out.println("Demande de connexion");

		in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
		while(true)
		{
			String message_distant = in.readLine();
			System.out.println(message_distant);
			if(message_distant.equals("stop")) break;
		}
		System.out.println("Connexion terminée");
		socket.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}

