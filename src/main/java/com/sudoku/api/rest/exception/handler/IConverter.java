package com.sudoku.api.rest.exception.handler;


public interface IConverter {
   Object convert(Object src, Object type);

   Object convert(Object[] srcs, Object type);

}
