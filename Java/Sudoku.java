
public class Sudoku {


	public static void main(String[] args) {
		//***************
		int Sudoku[][] = //[row][column]
		/*	//easy
			{
				{0,4,3 ,0,9,7, 0,0,1},
				{1,0,0 ,6,3,2, 0,0,7},
				{8,0,0 ,5,4,0, 9,6,3},

				{0,3,0 ,0,0,0, 1,5,0},
				{4,5,2 ,7,0,3, 8,9,6},
				{9,6,0 ,2,5,0, 0,0,0},

				{0,7,4 ,3,0,0, 0,0,9},
				{0,1,9 ,4,2,5, 6,0,0},
				{2,0,6 ,1,7,0, 3,0,0}
		};*/
		//expert
		{
				{8,0,0 ,5,3,0, 0,6,0},
				{0,0,2 ,0,4,0, 0,8,0},
				{0,0,0 ,9,0,2, 3,0,0},

				{6,0,5 ,0,0,8, 7,0,4},
				{0,0,3 ,0,0,0, 6,0,0},
				{9,0,7 ,2,0,0, 0,3,5},

				{0,0,8 ,1,0,9, 0,0,0},
				{0,5,0 ,0,2,0, 1,0,0},
				{0,1,0 ,0,5,3, 0,0,6}
			};


		boolean NotPossible[][][] = new boolean[9][9][10]; //[row][column][number] set to false
		int h1=0, h2=0;
		boolean loop = true;
		while(loop) {
		///////////////Calculating NotPossible values///////////////
			//Existing values
			for(int i = 0; i < 9; i++)
				for(int j = 0; j < 9; j++)
					if(Sudoku[i][j] != 0)
						for(int t=1; t<=9; t++)
							NotPossible[i][j][t] = true;
			//columns and rows
			for(int i = 0; i < 9; i++)
				for(int j = 0; j < 9; j++) {
					if(Sudoku[i][j] != 0)
						for(int p=0; p<9; p++)
							NotPossible[i][p][Sudoku[i][j]]=true;
					if(Sudoku[j][i] != 0)
						for(int p=0; p<9; p++)
							NotPossible[p][i][Sudoku[j][i]]=true;
				}
			//blocks
			for(int i=0; i<=6; i+=3)
				for(int j=0; j<=6; j+=3) {
					for(int p=0; p<3; p++)
						for(int q=0; q<3; q++)
							if(Sudoku[i+p][j+q] != 0)
								for(int k=0; k<3; k++)
									for(int l=0; l<3; l++)
										NotPossible[i+k][j+l][Sudoku[i+p][j+q]] = true;
				}

			/////special cases/////
			//column
			for(int t=1; t<=9; t++)
				for(int i=0; i<=6; i+=3)
					for(int j=0; j<=6; j+=3) {
						int h[] = {0,0,0};
						for(int p=0; p<3; p++)
							for(int q=0; q<3; q++)
								if(!NotPossible[i+p][j+q][t])
									h[p]++;
						h1=h[0]+h[1]+h[2];
						if(h1==2 || h1==3)
							for(int p=0; p<3; p++)
								if(h[p]==h1) {
									h2=-1;
									for(int q=0; q<3; q++)
										if(NotPossible[i+p][j+q][t])
											h2=q;
									for(int q=0; q<9; q++)
										NotPossible[i+p][q][t]=true;
									for(int q=0; q<3; q++)
										NotPossible[i+p][j+q][t]=false;
									if(h2!=-1)
										NotPossible[i+p][j+h2][t]=true;
								}
					}
			//row
			for(int t=1; t<=9; t++)
				for(int i=0; i<=6; i+=3)
					for(int j=0; j<=6; j+=3) {
						int h[] = {0,0,0};
						for(int p=0; p<3; p++)
							for(int q=0; q<3; q++)
								if(!NotPossible[i+q][j+p][t])
									h[p]++;
						h1=h[0]+h[1]+h[2];
						if(h1==2 || h1==3)
							for(int p=0; p<3; p++)
								if(h[p]==h1) {
									h2=-1;
									for(int q=0; q<3; q++)
										if(NotPossible[i+q][j+p][t])
											h2=q;
									for(int q=0; q<9; q++)
										NotPossible[q][j+p][t]=true;
									for(int q=0; q<3; q++)
										NotPossible[i+q][j+p][t]=false;
									if(h2!=-1)
										NotPossible[i+h2][j+p][t]=true;
								}
					}

		///////////////Replacing where needed///////////////
			//only one possible
			for(int i = 0; i < 9; i++)
				for(int j = 0; j < 9; j++)
					if(Sudoku[i][j] == 0) {
						h1=0;
						for(int t = 1; t<=9; t++)
							if(!NotPossible[i][j][t])
								h1++;
						if(h1 == 1) {
							int t = 1;
							while(NotPossible[i][j][t])
								t++;
							Sudoku[i][j] = t;
						}
					}
			//blocks
			for(int t=1; t<=9; t++)
				for(int i=0; i<=6; i+=3)
					for(int j=0; j<=6; j+=3) {
						h1=0;
						for(int p=0; p<3; p++)
							for(int q=0; q<3; q++)
								if(!NotPossible[i+p][j+q][t])
									h1++;
						if(h1==1)
							for(int p=0; p<3; p++)
								for(int q=0; q<3; q++)
									if(!NotPossible[i+p][j+q][t])
										Sudoku[i+p][j+q]=t;
					}
			//column
			for(int t=1; t<=9; t++)
				for(int i=0; i<9; i++) {
					h1=0;
					for(int p=0; p<9; p++)
						if(!NotPossible[i][p][t])
							h1++;
					if(h1==1)
						for(int p=0; p<9; p++)
							if(!NotPossible[i][p][t])
								Sudoku[i][p]=t;
				}
			//row
			for(int t=1; t<=9; t++)
				for(int i=0; i<9; i++) {
					h1=0;
					for(int p=0; p<9; p++)
						if(!NotPossible[p][i][t])
							h1++;
					if(h1==1)
						for(int p=0; p<9; p++)
							if(!NotPossible[p][i][t])
								Sudoku[p][i]=t;
				}


		///////////////Loop checkout///////////////
			h1 = 0;
			for(int i = 0; i < 9; i++)
				for(int j = 0; j < 9; j++)
					if(Sudoku[i][j] == 0)
						h1++;
			if(h1 == 0)
				loop = false;
		};

		///////////////output///////////////
		for(int i = 0; i < 9; i++) {
			if(i==3||i==6)
				System.out.print(System.lineSeparator() + System.lineSeparator());
			for(int j = 0; j < 9; j++) {
				if(j==3||j==6)
					System.out.print("   ");
				System.out.print(Sudoku[i][j] + " ");
			};
			System.out.print(System.lineSeparator());
		}
		//***************
	}
}
