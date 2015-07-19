package com.sudoku.api.rest.exception.handler;

import java.util.HashMap;
import java.util.Map;


public class SudokuExceptionConverter implements IConverter {

   private static SudokuExceptionConverter INSTANCE = null;

   public static synchronized SudokuExceptionConverter getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new SudokuExceptionConverter();
      }
      return INSTANCE;
   }

   public SudokuExceptionConverter() {
      super();
   }

   public Object convert( Object src, Object type)  {
	   Throwable source = (Throwable)src;
	   SudokuException sudokuEx = (SudokuException)type;
	   sudokuEx.setStackTrace(source.getStackTrace());
	   sudokuEx.setSeverity(SudokuException.SEVERITY_ERROR);
	   sudokuEx.setExceptionClass(SudokuException.class.getName());
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("message", source.getMessage());
		sudokuEx.setParameters(parameters);
		return sudokuEx;
}

   public Object convert( Object[] srcs, Object type) {
	   return null;
     // return _convert(srcs, type);
//      return context == null ? _convert(srcs, type) : _convert(context, srcs, type);
   }



}
