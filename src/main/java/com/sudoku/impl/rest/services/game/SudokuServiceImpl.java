package com.sudoku.impl.rest.services.game;

import java.util.Arrays;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sudoku.api.bean.Move;
import com.sudoku.api.bean.MoveResult;
import com.sudoku.api.bean.Sudoku;
import com.sudoku.api.rest.services.game.SudokuService;
import com.sudoku.api.util.ISudokuUtil;


@RestController
@RequestMapping("/rest/")
@Service
public class  SudokuServiceImpl implements SudokuService  {

public static final String EASY= "Easy";
public static final String MEDIUM= "Medium";
public static final String HARD= "Hard";
	
	@Inject 
	ISudokuUtil sudokuUtil;
	
	private Sudoku newGame;
	private Sudoku solvedGame;

	@PostConstruct
	public void init(){
		newGame = new Sudoku();
		solvedGame = new Sudoku();
	}
	
	@Override
	@RequestMapping(value = "sudoku/{level}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Sudoku getNewGame(@PathVariable String level) {
		int numberOfMisses =  calculateMisses(level);
		solvedGame.setSudokuBoard(sudokuUtil.generateSolvedGame());
		newGame.setSudokuBoard(sudokuUtil.generateNewGame(numberOfMisses));
				
		return newGame;
	}


	@Override
	@RequestMapping(value = "move", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MoveResult validateMoveAndCompleteGame(@Valid @RequestBody Move move) {
		Boolean moveSuccess = false;
		Boolean gameComplete = false;
		MoveResult moveResult= new MoveResult();
		Integer x = move.getX();
		Integer y = move.getY();
		Integer number = move.getNumber();
		if(solvedGame.getSudokuBoard()[x][y] == number){
			newGame.getSudokuBoard()[x][y] = number;
			moveSuccess = true;
			moveResult.setSuccess(moveSuccess);
			moveResult.setMessage("Move is successful");
		}else{
			moveResult.setSuccess(false);
			moveResult.setMessage("Wrong move");
		}
		if(moveSuccess && Arrays.deepEquals(solvedGame.getSudokuBoard(), newGame.getSudokuBoard())){
			gameComplete = true;
			moveResult.setSuccess(gameComplete);
			moveResult.setMessage("Game Complete! Try new game!");
		}
		
		return moveResult;
	}
	private int calculateMisses(String level){
		Random r = new Random();
		int low=0;
		int high=81;
		switch(level){
		case EASY:
		low =20;
		high =35; 
			break;
		case MEDIUM:
			low=35;
			high=50;
			break;
		case HARD:
			low=50;
			high=65;
			break;
		}
		return r.nextInt(high-low)+low;
	
	}


}
