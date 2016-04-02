package fr.esiea.cours.tetris;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Score {
	String score = "ScoreActuel.txt";
	private Score()
	{}
	private static Score INSTANCE = new Score();
	public static Score getINSTANCE() {
		return INSTANCE;
	}
	public void savescore(int score) {
		File f = new File (this.score);
		try
		{
		    PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter (f)));
		    String res = String.valueOf(score);
		    pw.write(res);
		    System.out.println ("Le nouveau score est de :"+res);
		    pw.close();
		}
		catch (IOException exception){System.out.println ("Erreur lors de la lecture : " + exception.getMessage());}
	}
	public void lecturescore()
	{
		try
		{		 
		    BufferedReader br = new BufferedReader (new FileReader (new File (score)));
		    try
		    {
		        String line = br.readLine();
		        while (line != null)
		        {
		            System.out.println ("Le score précédent était de :"+line);
		            line = br.readLine();
		        }
		        br.close();
		    }
		    catch (IOException exception){System.out.println ("Erreur lors de la lecture : " + exception.getMessage());}
		}
		catch (FileNotFoundException exception){System.out.println ("Le fichier n'a pas été trouvé");}
	}

}