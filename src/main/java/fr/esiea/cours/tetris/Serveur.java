package fr.esiea.cours.tetris;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Serveur {

	public static void main(String[] zero) {

		ServerSocket socketserver  ;
		Socket socketduserveur ;
		BufferedReader in;
		PrintWriter out;
		Scanner sc = new Scanner(System.in);

		try {

			socketserver = new ServerSocket(2009);
			System.out.println("Le serveur est à l'écoute du port "+socketserver.getLocalPort());
			socketduserveur = socketserver.accept(); 
			System.out.println("Un zéro s'est connecté");
			out = new PrintWriter(socketduserveur.getOutputStream());
			out.println("Vous êtes connecté zéro !");
			while(1==1)	
			{
				String string=sc.nextLine();
				out.println(string);
				out.flush();
				if(string.equals("stop")) break;
			}
			socketduserveur.close();
			socketserver.close();

		}catch (IOException e) {

			e.printStackTrace();
		}
	}

}
