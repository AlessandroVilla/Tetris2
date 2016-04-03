package fr.esiea.cours.tetris;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

	public class ReadClient implements Runnable {

		private Socket socket;
		public boolean authentifier = false;
		public Thread t2;
		private String malus;
		private String str=null;
		
		public ReadClient(Socket s, String m){
			setMalus(m);
			socket = s;
		}
		public ReadClient(Socket s){
			socket = s;
		}
		public void run() {
			try {
				BufferedReader plec = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		        PrintWriter pred = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);			
				while(true){
		            str = plec.readLine();          // lecture du message
		            if (str != null) {
		            	if ((str.equals("SpeedMalus")) || (str.equals("ScoreMalus"))) {
		            		setMalus(str);
		            		System.out.println(""+ str);
		            		break;
		            	}
		            	if (str.equals("END")) break;
		            	System.out.println("ECHO = " + str);   // trace locale
		            	pred.println(str);                     // renvoi d'un écho
		            	pred.flush();
		            }
				}
			} catch (IOException e){System.err.println("Erreur connexion !");}
		}
		public String getMalus() {
			return malus;
		}
		public void setMalus(String malus) {
			this.malus = malus;
		}

	}

