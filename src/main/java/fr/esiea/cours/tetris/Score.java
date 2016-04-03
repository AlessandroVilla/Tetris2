package fr.esiea.cours.tetris;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Score {
	String score = "ScoreActuel.txt";
    @SuppressWarnings("rawtypes")
	ArrayList al = new ArrayList();
	private Score()
	{}
	private static Score INSTANCE = new Score();
	public static Score getINSTANCE() {
		return INSTANCE;
	}
	@SuppressWarnings("unchecked")
	public void saveScore(int score) {
		lectureScore();
		File f = new File (this.score);int i =0;
		al.add(0, Integer.toString(score));
		test5score();
		try
		{
			System.out.println("Les Meilleurs Scores sont : ");
		    PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter (f)));
		    while(i<al.size())
		    {
		    	System.out.println(i+" : "+al.get(i));
		    	String s=(String) al.get(i);
		    	pw.write(s+"\n");
		    	i++;
		    }
		    System.out.println ("Le nouveau score est de :"+al.get(0));
		    pw.close();
		}
		catch (IOException exception){System.out.println ("Erreur lors de la lecture : " + exception.getMessage());System.exit(0);}}
	@SuppressWarnings("unchecked")
	public void lectureScore()
	{
		try
		{		 
		    BufferedReader br = new BufferedReader (new FileReader (new File (score)));
		    try
		    {
		        String line = br.readLine();
		        while (line != null)
		        {
		        	al.add(line);
		            line = br.readLine();
		        }
		        br.close();
		    }
		    catch (IOException exception){System.out.println ("Erreur lors de la lecture : " + exception.getMessage());}}
		catch (FileNotFoundException exception){System.out.println ("Le fichier n'a pas été trouvé");}}
	public void test5score()
	{
		if(al.size()>5)
		{
			int min=999999999;int indmin = 0;int i;
			for(i=0;i<al.size();i++)
			{
				int j= Integer.parseInt((String) al.get(i));
				if(j<=min)
				{
					min=j;
					indmin=i;
				}
			}
			al.remove(indmin);
		}

	}

}