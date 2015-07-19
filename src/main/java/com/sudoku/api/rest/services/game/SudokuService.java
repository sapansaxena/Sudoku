package com.sudoku.api.rest.services.game;

import javax.validation.Valid;

import com.sudoku.api.bean.Move;
import com.sudoku.api.bean.MoveResult;
import com.sudoku.api.bean.Sudoku;



public interface  SudokuService {
	
		public  Sudoku getNewGame(String level);
		

	    public MoveResult validateMoveAndCompleteGame(@Valid Move move);
	    
	 	
	
}
