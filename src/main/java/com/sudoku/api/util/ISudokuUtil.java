package com.sudoku.api.util;


public interface ISudokuUtil {

	public int[][] generateSolvedGame();
	public int[][] generateNewGame(int misses);

}
