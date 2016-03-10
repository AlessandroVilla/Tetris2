package fr.esiea.cours.tetris.controller;
import fr.esiea.cours.tetris.modele.Piecemodele;

public class PieceController {
	final public int col=12; // Nombre de colonne du jeu
	final public int lin=24; //Nombre de ligne du jeu
	final public int tp=16; // 16 représente les 4 positions d'une pièce ( 4*4 )
	public int p;  // Position de la pièce
	public int x;  // Position x 
	public int x1; // futur déplacement en x 
	public int y;  // position y
	public int y1; // futur déplacement en y 
	public boolean b;
	//test si la piece peut se déplacer à droite
	public boolean BD( int[][] tableau,int piece[][]){
		for(int i=0;i<tp;i++)
		{
			if(piece[p][i]!=0)
			{
				x=i%4;		
				y=i/4;
				if(x+x1>col-1) return true;
				if(tableau[x+x1][y+y1]!=0) return true;
			}
		}
		return false;	
	}
	//test si la piece peut se déplacer à gauche
	public boolean BG( int[][] tableau,int piece[][]){
		for(int i=0;i<tp;i++)
		{

			if(piece[p][i]!=0)
			{
				x=i%4;
				y=i/4;
				if(x+x1<0) return true;
				if(tableau[x+x1][y+y1]!=0) return true;
			}
		}
		return false;	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void perdu(int[][] tableau,int piece[][]){
		for (int m=0;m<tp;m++) {
			if (piece[p][m] != 0) {

				x1 = m % 4;
				y1 = m / 4;

				if (tableau[x+x1][y+y1] != 0) {
					for (int i=0;i<col;i++) {
						for (int j=0;j<lin;j++) {
							tableau[i][j]=1;
						}
					}
				}
			}

		}
	}
	public void clean(int[][] tableau,int[][] piece) {

		for (int i=0;i<4;i++) 
			if (piece[p][i]!=0) 
				tableau[x+i][y] = 0;
		for (int i=0;i<4;i++) 
			if (piece[p][i+4]!=0) 
				tableau[x+i][y+1] = 0;
		for (int i=0;i<4;i++) 
			if (piece[p][i+8]!=0) 
				tableau[x+i][y+2] = 0;
		for (int i=0;i<4;i++) 
			if (piece[p][i+12]!=0) 
				tableau[x+i][y+3] = 0;
	}
	public void descend(int[][] tableau,int[][] piece)
	{

		clean(tableau, piece );
		y++;
		if (bloque(tableau,piece)) {
			y --;
			affiche_piece(tableau,piece);
			verifie_ligne(tableau);
			y = 0;
			x = 3;
			new Piecemodele().creationpiece();
			perdu(tableau, piece);
		} else affiche_piece(tableau, piece);

	}
	public void verifie_ligne(int[][] tableau) {
		for (int i=0;i<lin;i++) {
			b=false;
			for (int j=0;j<col;j++)
				if (tableau[j][i] == 0) b=true;		    
			if (b==false) {
				for (int m=0;m<col;m++) 
					tableau[i][0]=0;
				for (int t=i;t>0;t--) {
					for (int o=0;o<col;o++) 
						tableau[o][t]=tableau[o][t-1]; 
				}
			}
		}
	}
	public void affiche_piece(int[][] tableau,int[][] piece) {
		for (int m=0;m<4;m++)
			if ( ((x+m)<col) && ((x+m)>=0) && (tableau[x+m][y]==0) && (piece[p][m] != 0))
				tableau[x+m][y] = piece[p][m];
		if ((y+1)<lin)
			for (int m=0;m<4;m++)
				if (  ((x+m)<col)   && ((x+m)>=0) && (tableau[x+m][y+1]==0) && (piece[p][m+4] != 0))
					tableau[x+m][y+1] = piece[p][m+4];
		if ((y+2)<lin)
			for (int m=0;m<4;m++)
				if ( ((x+m)<col)  && ((x+m)>=0) && (tableau[x+m][y+2]==0) && (piece[p][m+8] != 0))
					tableau[x+m][y+2] = piece[p][m+8];
		if ((y+3)<lin)
			for (int m=0;m<4;m++)
				if ( ((x+m)<col)  && ((x+m)>=0) && (tableau[x+m][y+3]==0) && (piece[p][m+12] != 0))
					tableau[x+m][y+3] = piece[p][m+12];
	}
	public boolean bloque(int[][] tableau, int[][]piece) {
		for (int m=0;m<4;m++)
			if (((x+m)<col) && (y < lin) && ((x+m)>=0))
				if ((tableau[x+m][y]!=0) && (piece[p][m]!=0)) 
					return true;
		for (int m=0;m<4;m++) 
			if (((x+m)<col) && (y < lin-1) && ((x+m)>=0))
				if ((tableau[x+m][y+1]!=0) && (piece[p][m+4]!=0)) 
					return true;
		for (int m=0;m<4;m++)
			if (((x+m)<col) && (y < lin-2) && ((x+m)>=0))
				if ((tableau[x+m][y+2]!=0) && (piece[p][m+8]!=0)) 
					return true;
		for (int m=0;m<4;m++)
			if (((x+m)<col) && (y < lin-3) && ((x+m)>=0))
				if ((tableau[x+m][y+3]!=0) && (piece[p][m+12]!=0)) 
					return true;
		return false;
	}
}
