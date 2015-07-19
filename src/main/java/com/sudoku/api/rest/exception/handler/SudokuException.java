package com.sudoku.api.rest.exception.handler;

import com.sudoku.api.exception.handler.PublicException;

public class SudokuException extends PublicException{
	private static final long serialVersionUID = 4899718906287352188L;
	   
	   private static IConverter converter;
	   
	   public SudokuException() {
	      super();
	   }

	   public SudokuException(String message, Throwable ex) {
	      super(message, ex);
	   }

	   public SudokuException(String message) {
	      super(message);
	   }

		protected static IConverter getConverter() {
			if (converter == null) {
				converter = new SudokuExceptionConverter();
			}
			return converter;
		}
		
		public static SudokuException unify(Throwable e) {
		   if(e instanceof PublicException) {
		      throw (PublicException) e;
		   } else {
		      try {
		         return (SudokuException) getConverter().convert( e, new SudokuException());
		      } catch (Exception converterException) {
		         throw converterException;
		      }
		   }
		}


}
