package fr.esiea.cours.tetris.modele;

public class Piecemodele {
	public int rand;
	public int[][] P1={ {0,1,0,0,0,1,1,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,0,0,1,1,0,1,1,0,0,0,0,0},
			{0,1,0,0,0,1,1,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,0,0,1,1,0,1,1,0,0,0,0,0} };//serpent1
	public int[][] P2={ {0,2,2,0,0,2,2,0,0,0,0,0,0,0,0,0},
			{0,2,2,0,0,2,2,0,0,0,0,0,0,0,0,0},
			{0,2,2,0,0,2,2,0,0,0,0,0,0,0,0,0},
			{0,2,2,0,0,2,2,0,0,0,0,0,0,0,0,0} };//carré
	public int[][] P3={ {0,3,0,0,0,3,0,0,0,3,0,0,0,3,0,0},
			{3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,3,0,0,0,3,0,0,0,3,0,0,0,3,0,0},
			{3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0} };
	public int[][] P4={ {0,0,4,0,0,4,4,0,0,4,0,0,0,0,0,0},
			{0,0,0,0,0,4,4,0,0,0,4,4,0,0,0,0},
			{0,0,4,0,0,4,4,0,0,4,0,0,0,0,0,0},
			{0,0,0,0,0,4,4,0,0,0,4,4,0,0,0,0} };//barre
	public int[][] P5={ {0,5,0,0,0,5,5,0,0,5,0,0,0,0,0,0},
			{0,0,0,0,0,0,5,0,0,5,5,5,0,0,0,0},
			{0,0,0,5,0,0,5,5,0,0,0,5,0,0,0,0},
			{0,5,5,5,0,0,5,0,0,0,0,0,0,0,0,0} }; //triangle                         
	public int[][] P6={ {0,0,6,0,0,0,6,0,0,6,6,0,0,0,0,0},
			{0,0,0,0,0,6,6,6,0,0,0,6,0,0,0,0},
			{0,6,6,0,0,6,0,0,0,6,0,0,0,0,0,0},
			{0,0,0,0,0,6,0,0,0,6,6,6,0,0,0,0} };    //L   
	public int[][] P7={ {0,7,0,0,0,7,0,0,0,7,7,0,0,0,0,0},
			{0,0,0,0,0,0,0,7,0,7,7,7,0,0,0,0},
			{0,7,7,0,0,0,7,0,0,0,7,0,0,0,0,0},
			{0,0,0,0,0,7,7,7,0,7,0,0,0,0,0,0} }; //serpent 2s                
	public int [][] tableaupiece =  new int [4][16];
	public void creationpiece (){
		rand = (int)(Math.random() * 100);
		rand = (rand % 7) + 1;
		System.out.println(""+rand);
		switch(rand){
		case 1:
			for(int i=0;i<4;i++)
				for (int j=0;j<16;j++)
					tableaupiece[i][j]=P1[i][j];
			break;
		case 2:
			for(int i=0;i<4;i++)
				for (int j=0;j<16;j++)
					tableaupiece[i][j]=P2[i][j];
			break;
		case 3:
			for(int i=0;i<4;i++)
				for (int j=0;j<16;j++)
					tableaupiece[i][j]=P3[i][j];
			break;
		case 4:
			for(int i=0;i<4;i++)
				for (int j=0;j<16;j++)
					tableaupiece[i][j]=P4[i][j];
			break;
		case 5:
			for(int i=0;i<4;i++)
				for (int j=0;j<16;j++)
					tableaupiece[i][j]=P5[i][j];
			break;
		case 6:
			for(int i=0;i<4;i++)
				for (int j=0;j<16;j++)
					tableaupiece[i][j]=P6[i][j];
			break;
		case 7:
			for(int i=0;i<4;i++)
				for (int j=0;j<16;j++)
					tableaupiece[i][j]=P7[i][j];
			break;
		}	
	}

}
