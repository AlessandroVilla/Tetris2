package fr.esiea.cours.tetris;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Serveur {

	private static Scanner sc;

	public static void main(String[] zero) {

		ServerSocket socketserver  ;
		Socket socketduserveur ;
		PrintWriter out;
		sc = new Scanner(System.in);

		try {

			socketserver = new ServerSocket(2009);
			System.out.println("Le serveur est � l'�coute du port "+socketserver.getLocalPort());
			socketduserveur = socketserver.accept(); 
			System.out.println("Un z�ro s'est connect�");
			out = new PrintWriter(socketduserveur.getOutputStream());
			out.println("Vous �tes connect� z�ro !");
			while(true)	
			{
				String string=sc.nextLine();
				out.println(string);
				out.flush();
				if(string.equals("stop")) break;
			}
			System.out.println("Connexion termin�e");
			socketduserveur.close();
			socketserver.close();

		}catch (IOException e) {

			e.printStackTrace();
		}
	}

}
