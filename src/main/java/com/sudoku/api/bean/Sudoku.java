package com.sudoku.api.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class Sudoku {
	
	private static final long serialVersionUID = 7990671649158125863L;
	public static final int SUDOKU_SIZE = 9;
	private int[][] sudokuBoard;
	
	public int[][] getSudokuBoard() {
		return sudokuBoard;
	}
	public void setSudokuBoard(int[][] sudokuBoard) {
		this.sudokuBoard = sudokuBoard;
	}
	
	
}
