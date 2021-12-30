#include <iostream>
#include <conio.h>
#include <math.h>
using namespace std;

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

int h1, h2, h3;

void not_possible(int i, int j) {	//Calculating NotPossible values****
	//rows and columns
	for(int t = 0; t < 9; t++) {
		if(Sudoku[i][t] != 0)
			NotPossible[i][j][Sudoku[i][t]] = true;
		if(Sudoku[t][j] != 0) 
			NotPossible[i][j][Sudoku[t][j]] = true;
	};
	
	//blocks
	h1 = (i/3)*3;
	h2 = (j/3)*3;
	for(int ii = h1; ii < h1 + 3; ii++)
		for(int jj = h2; jj < h2 + 3; jj++)
			if(Sudoku[ii][jj] != 0)
				NotPossible[i][j][Sudoku[ii][jj]] = true;
}

void replace(int i, int j) {	//assign value if possible
	h1=0;
	for(int t = 1; t < 10; t++)
		if(NotPossible[i][j][t] == false)
			h1++;
	if(h1 == 1) {
		int t = 1;
		while(NotPossible[i][j][t])
			t++;
		Sudoku[i][j] = t;
		};
}

void output() {
	//output
	for(int i = 0; i < 9; i++) {
		if(i==3||i==6)
			cout << endl << endl;
		for(int j = 0; j < 9; j++) {
			if(j==3||j==6)
				cout << "   ";
			cout << Sudoku[i][j] << " ";
		};
		cout << endl;
	};
}

bool loop_out() {
//Loop checkout
	h1 = 0;
	for(int i = 0; i < 9; i++)
		for(int j = 0; j < 9; j++)
			if(Sudoku[i][j] == 0)
				h1++;
	if(h1 == 0)
		return false;
	else
		return true;
}

int main(int argc, char** argv) {
	//***************
	
	for(int i = 1; i < 10; i++)
		for(int j = 1; j < 10; j++)
			for(int t = 1; t < 10; t++)
				NotPossible[i][j][t] = false;

	bool loop = true;
	while(loop) {
		//Calculating values
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				if(Sudoku[i][j] == 0) {
					not_possible(i, j);	
					replace(i, j);
				};
		
			loop = loop_out();
	};
	
	output();
		
	//***************
	-getch();
	return 0;
}
	


