package com.sudoku.impl.util;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.sudoku.api.bean.Sudoku;
import com.sudoku.api.util.ISudokuUtil;


@Service
public class SudokuUtil implements ISudokuUtil {
	
	
	private int[][] game;
	private static Integer MINI_SQUARE_FIRST_RIGHT_BOTTOM = 2; 
	private static Integer MINI_SQUARE_MID_LEFT_TOP = 3;
	private static Integer MINI_SQUARE_LAST_LEFT_TOP = 6;
	private static Integer MINI_SQUARE_MID_RIGHT_BOTTOM = 5;
	private static Integer MINI_SQUARE_SIZE=3;
	private static Integer ALL_SQUARES=81;
	
	
	@Override
	public int[][] generateSolvedGame() {
		game = new int[Sudoku.SUDOKU_SIZE][Sudoku.SUDOKU_SIZE];
		eachBox(0, 0);
		return game;
	}
	public int[][] generateNewGame(int misses) {
		int newGame[][] = new int[Sudoku.SUDOKU_SIZE][Sudoku.SUDOKU_SIZE];
		for(int i=0;i<Sudoku.SUDOKU_SIZE;i++){
			for(int j=0;j<Sudoku.SUDOKU_SIZE;j++){
				newGame[i][j] = game[i][j];
			}
		}
		double allSquares = (double)ALL_SQUARES;
		double restSquares = (double)misses;
		
		for(int i=0;i<Sudoku.SUDOKU_SIZE;i++)
			for(int j=0;j<Sudoku.SUDOKU_SIZE;j++)
			{
				double probHole = restSquares/allSquares;
				if(Math.random() <= probHole)
				{
					newGame[i][j] = 0;
					restSquares--;
				}
				allSquares--;
			}
		return newGame;
	}
	
	
	private boolean eachBox(int x, int y){
		int curX = x;
		int curY = y;
		int[] fullStack = {1,2,3,4,5,6,7,8,9};
		Random r = new Random();
		int temp = 0;
		int currentValue = 0;
		int top = fullStack.length;

   		for(int i=top-1;i>0;i--)
		{
   			currentValue = r.nextInt(i);
		    temp = fullStack[currentValue];
		    fullStack[currentValue] = fullStack[i];
		    fullStack[i] = temp;
    	}
		
		for(int i=0;i<fullStack.length;i++)
		{
			if(validPlacement(x, y, fullStack[i]))
			{
				game[x][y] = fullStack[i];
				if(x == Sudoku.SUDOKU_SIZE-1)
				{
					if(y == Sudoku.SUDOKU_SIZE-1)
						return true;
					else
					{
						curX = 0;
						curY = y + 1;
					}
				}
				else
				{
					curX = x + 1;
				}
				if(eachBox(curX, curY))
					return true;
			}
		}
		game[x][y] = 0;
		return false;
	}

	private boolean validPlacement(int x, int y, int currentValue) {
		for(int i=0;i<9;i++) {
			if(currentValue == game[x][i])
				return false;
		}
		for(int i=0;i<9;i++) {
			if(currentValue == game[i][y])
				return false;
		}
		int miniSquareX = 0;
		int miniSquareY = 0;
		if(x > MINI_SQUARE_FIRST_RIGHT_BOTTOM)
			if(x > MINI_SQUARE_MID_RIGHT_BOTTOM)
				miniSquareX = MINI_SQUARE_LAST_LEFT_TOP;
			else
				miniSquareX = MINI_SQUARE_MID_LEFT_TOP;
		if(y > MINI_SQUARE_FIRST_RIGHT_BOTTOM)
			if(y > MINI_SQUARE_MID_RIGHT_BOTTOM)
				miniSquareY = MINI_SQUARE_LAST_LEFT_TOP;
			else
				miniSquareY = MINI_SQUARE_MID_LEFT_TOP;
		for(int i=miniSquareX;i<10 && i<miniSquareX+MINI_SQUARE_SIZE;i++)
			for(int j=miniSquareY;j<10 && j<miniSquareY+MINI_SQUARE_SIZE;j++)
				if(currentValue == game[i][j])
					return false;
		return true;
	}

}
