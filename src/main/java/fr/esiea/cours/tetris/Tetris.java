package fr.esiea.cours.tetris;

public class Tetris {
	public int grille[][];
	public void init(){
		grille = new int [12][25];
		for(int i=0;i<12;i++)
			for(int j=0;j<25;j++)
				grille[i][j]=0;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
