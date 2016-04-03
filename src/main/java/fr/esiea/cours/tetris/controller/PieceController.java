package fr.esiea.cours.tetris.controller;

import fr.esiea.cours.tetris.Score;
import fr.esiea.cours.tetris.modele.Piecemodele;

public class PieceController {
	final private int col=12; // Nombre de colonnes du jeu
	final private int lin=25; // Nombre de lignes du jeu
	final public int tp=16;   // 16 représente les 4 positions d'une pièce ( 4*4 )
	private int p;  		  // Position de la pièce
	private int x = 6;        // Position x 
	private int x1;           // Futur déplacement en x 
	private int y;            // Position y
	private int y1;           // Futur déplacement en y 
	private int score;
	private boolean perdu;
	public boolean b;
	private boolean flagpiece = false;
	Piecemodele Tabpiece;
	private int cptsc;
	
	//teste si la piece peut se déplacer à droite
	public boolean BD( int[][] tableau,int piece[][]){
		for(int i=0; i<tp; i++) {
			if(piece[p][i]!=0) {
				int m=i%4;		
				int n=i/4;
				if(x+m>=col-1)return true;
				if (testPieceD(i, piece) == true) {
					if(tableau[x+1+m][y+n]!=0)return true;
				}
			}
		}
		return false;	
	}

	public boolean testPieceD (int i, int piece[][]) {
		switch(i)
		{
		case 0: if (piece[p][1] + piece[p][2] + piece[p][3] > 0) return false; break;
		case 1: if (piece[p][2] + piece[p][3] > 0) return false; break;
		case 2: if (piece[p][3] > 0) return false; break;		
		case 4: if (piece[p][5] + piece[p][6] + piece[p][7] > 0) return false; break;
		case 5: if (piece[p][6] + piece[p][7] > 0) return false; break;				
		case 6: if (piece[p][7] > 0) return false; break;	
		case 8: if (piece[p][9] + piece[p][10] + piece[p][11] > 0) return false; break;
		case 9: if (piece[p][10] + piece[p][11] > 0) return false; break;
		case 10: if (piece[p][11] > 0) return false; break;
		case 12: if (piece[p][13] + piece[p][14] + piece[p][15] > 0) return false; break;
		case 13: if (piece[p][14] + piece[p][15] > 0) return false; break;
		case 14: if (piece[p][15] > 0) return false; break;
		}
		return true;
	}
	
	//teste si la piece peut se déplacer à gauche
	public boolean BG( int[][] tableau,int piece[][]){
		for(int i=0;i<tp;i++) {
			if(piece[p][i]!=0) {
				int m=i%4;
				int n=i/4;
				if(x+m<=0) return true;
				if (testPieceG(i, piece)) {
					if(tableau[x-1+m][y+n]!=0) return true;
				}
			}
		}
		return false;	
	}
	
	public boolean testPieceG (int i, int piece[][]) {
		switch(i)
		{
		case 1: if (piece[p][0] > 0) return false; break;
		case 2: if (piece[p][0] + piece[p][1] > 0) return false; break;
		case 3: if (piece[p][0] + piece[p][1] + piece[p][2] > 0) return false; break;
		case 5: if (piece[p][4] > 0) return false; break;
		case 6: if (piece[p][4] + piece[p][5] > 0) return false; break;
		case 7: if (piece[p][4] + piece[p][5] + piece[p][6] > 0) return false; break;
		case 9: if (piece[p][8] > 0) return false; break;
		case 10: if (piece[p][8] + piece[p][9] > 0) return false; break;
		case 11: if (piece[p][8] + piece[p][9] + piece[p][10] > 0) return false; break;	
		case 13: if (piece[p][12] > 0) return false; break;
		case 14: if (piece[p][12] + piece[p][13] > 0) return false; break;
		case 15: if (piece[p][12] + piece[p][13] + piece[p][14] > 0) return false; break;
		}
		return true;
	}
	
	//teste si la piece peut tourner
	public boolean rotate( int[][] tableau, int piece[][], int pnew){
		for(int i=0; i<tp; i++) {
			if(piece[pnew][i]!=0) {
				int m=i%4;		
				int n=i/4;
				if ((x+m<0) || (x+m>=col)) return true;
				if (tableau[x+m][y+n]!=0)return true;
			}
		} 
		return false; 	
	}
	
	public boolean perdu(int[][] tableau,int piece[][]){
		for (int m=0;m<tp;m++) {
			if (piece[p][m] != 0) {
				x1 = m % 4;
				y1 = m / 4;
				if (tableau[x+x1][y+y1] != 0) {
					for (int i=0;i<col;i++)
						for (int j=0;j<lin;j++)
							tableau[i][j]=1;
					for(int i =0; i<col; i++) tableau[i][lin-1]=8;
					return true;
				}
			}
		}
		return false;
	}
	
	public void clean(int[][] tableau, int[][] piece) {
		for (int i=0; i<4; i++) 
			if (piece[p][i]!=0) 
				tableau[x+i][y] = 0;
		for (int i=0; i<4; i++) 
			if (piece[p][i+4]!=0) 
				tableau[x+i][y+1] = 0;
		for (int i=0; i<4; i++) 
			if (piece[p][i+8]!=0) 
				tableau[x+i][y+2] = 0;
		for (int i=0; i<4; i++) 
			if (piece[p][i+12]!=0) 
				tableau[x+i][y+3] = 0;
	}
	
	public void descend(int[][] tableau,int[][] piece) {
		clean(tableau, piece);
		y++;
		if (bloque(tableau, piece)) {
			flagpiece = true;
			y--;
			affichePiece(tableau, piece);
			verifieLigne(tableau);
			y = 0;x = 6;
			Tabpiece.creationpiece();
			if(perdu(tableau,piece)){
				Score score =  Score.getINSTANCE();//singleton
				System.out.println("Vous avez perdu");
				//score.lecturescore();
				score.saveScore(this.score);
				setPerdu(true);}
		} else affichePiece(tableau, piece);
	}
	
	public void verifieLigne(int[][] tableau) {
		for (int i=0; i<lin-1; i++) {
			b=false;
			for (int j=0; j<col; j++)
				if (tableau[j][i] == 0) b=true;	
			if (b==false) {
				score+=1000;
				setCptsc(getCptsc() + 1);
				System.out.println("Le score actuel est de : "+score);
				for (int m=0; m<col; m++) 
					tableau[m][i]=0;
				for (int t=i; t>0; t--)
					for (int o=0; o<col; o++)
						tableau[o][t]=tableau[o][t-1]; 
			}
		}
	}
	
	public int[][] affichePiece(int[][] tableau,int[][] piece) {
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
		return tableau;
	}
	
	public boolean bloque(int[][] tableau, int[][] piece) {
		for (int m=0; m<4; m++)
			if (((x+m)<col) && (y < lin) && ((x+m)>=0)) 
				if ((tableau[x+m][y]!=0) && (piece[p][m]!=0))return true;
		for (int m=0; m<4; m++) 
			if (((x+m)<col) && (y < lin-1) && ((x+m)>=0))
				if ((tableau[x+m][y+1]!=0) && (piece[p][m+4]!=0))return true;
		for (int m=0; m<4; m++)
			if (((x+m)<col) && (y < lin-2) && ((x+m)>=0))
				if ((tableau[x+m][y+2]!=0) && (piece[p][m+8]!=0))
					return true;
		for (int m=0; m<4; m++)
			if (((x+m)<col) && (y < lin-3) && ((x+m)>=0))
				if ((tableau[x+m][y+3]!=0) && (piece[p][m+12]!=0))return true;
		return false;
	}
	public void gauche(int[][] tableau, int[][] piece)
	{
		if(!isPerdu()) {
			if (!BG(tableau,piece)) {
				clean(tableau,piece);
				int x = getX();
				x--;
				setX(x);
				affichePiece(tableau, piece);
			}
		}
	}
	public void droite(int[][] tableau, int[][] piece)
	{
		if(!isPerdu()) {
			if (!BD(tableau,piece)) {
				clean(tableau,piece);
				int x = getX();
				x++;
				setX(x);
				affichePiece(tableau, piece);
			}
		}
	}
	public void space(int[][] tableau, int[][] piece)
	{
		if(!isPerdu()) {
			setFlagPiece(false);
			while (!getFlagPiece()) {
				descend(tableau, piece);
			}
		}
	}
	public void rotateLeft(int[][] tableau, int[][] piece)
	{
		if(!isPerdu()) {
			clean(tableau,piece);
			int x = getP();
			if (++x > 3) x = 0;
			if (!rotate(tableau, piece, x)) {
				setP(x);
				affichePiece(tableau,piece);
			}
		}
	}
	public void rotateRight(int[][] tableau, int[][] piece)
	{
		if(!isPerdu()) {
			clean(tableau, piece);
			int x = getP();
			if (--x < 0) x = 3;
			if (!rotate(tableau, piece, x)) {
				setP(x);
				affichePiece(tableau,piece);
			}
		}
	}
	public void setTabpiece(Piecemodele z) {
		Tabpiece = z;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int z) {
		x = z;
	}
	
	public void setY(int z) {
		y = z;
	}
	
	public int getCol() {
		return col;
	}
	
	public int getLin() {
		return lin;
	}
	
	public boolean getFlagPiece() {
		return flagpiece;
	}
	
	public void setFlagPiece(boolean z) {
		flagpiece = z;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public boolean isPerdu() {
		return perdu;
	}
	public void setPerdu(boolean perdu) {
		this.perdu = perdu;
	}
	
	public int getP() {
		return p;
	}
	
	public void setP(int z) {
		p = z;
	}

	public int getCptsc() {
		return cptsc;
	}

	public void setCptsc(int cptsc) {
		this.cptsc = cptsc;
	}
}