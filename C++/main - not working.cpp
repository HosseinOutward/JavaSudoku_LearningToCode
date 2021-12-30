#include <iostream>
#include <conio.h>
#include <math.h>
using namespace std;

int main(int argc, char** argv) {
	//***************
	
	
	int Sudoku[9][9] = {
	 //[row][column]
		{0,4,3 ,0,9,7, 0,0,1},
		{1,0,0 ,6,3,2, 0,0,7},
		{8,0,0 ,5,4,0, 9,6,3},
		
		{0,3,0 ,0,0,0, 1,5,0},
		{4,5,2 ,7,0,3, 8,9,6},
		{9,6,0 ,2,5,0, 0,0,0},
				
		{0,7,4 ,3,0,0, 0,0,9},
		{0,1,9 ,4,2,5, 6,0,0},
		{2,0,6 ,1,7,0, 3,0,0}
	};
	
	
	bool NotPossible[10][10][10]; //[row][column][number]
	for(int i = 1; i < 10; i++)
		for(int j = 1; j < 10; j++)
			for(int t = 1; t < 10; t++)
				NotPossible[i][j][t] = false;
	int h1, h2;
	bool loop = true;
	while(loop) {
		//Calculating values
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				if(Sudoku[i][j] == 0) {
				//Calculating NotPossible values****
					//existing values in rows and columns
					for(int t = 0; t < 9; t++) {
						if(Sudoku[i][t] != 0)
							NotPossible[i][j][Sudoku[i][t]] = true;
						if(Sudoku[t][j] != 0) 
							NotPossible[i][j][Sudoku[t][j]] = true;
					}

					//existing values in blocks
					h1 = (i/3)*3;
					h2 = (j/3)*3;
					for(int k = h1; k < h1 + 3; k++)
						for(int t = h2; t < h2 + 3; t++)
							if(Sudoku[k][t] != 0)
								NotPossible[i][j][Sudoku[k][t]] = true;
								
				
				//Replacing if needed
					h1=0;
					for(int t = 1; t < 10; t++)
						if(NotPossible[i][j][t] == false)
							h1++;
					if(h1 == 1) {
						int t = 1;
						while(NotPossible[i][j][t])
							t++;
						Sudoku[i][j] = t;
					}
				}
		
		
		//Loop checkout
		h1 = 0;
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				if(Sudoku[i][j] == 0)
					h1++;
		if(h1 == 0)
			loop = false;
	}
	
	//output
	for(int i = 0; i < 9; i++) {
		if(i==3||i==6)
			cout << endl << endl;
		for(int j = 0; j < 9; j++) {
			if(j==3||j==6)
				cout << "   ";
			cout << Sudoku[i][j] << " ";
		}
		cout << endl;
	}
		
	//***************
	-getch();
	return 0;
}
	


