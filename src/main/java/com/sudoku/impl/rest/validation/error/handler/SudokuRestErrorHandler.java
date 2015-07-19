package com.sudoku.impl.rest.validation.error.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sudoku.api.rest.exception.handler.SudokuException;

	@ControllerAdvice
	public class SudokuRestErrorHandler {
	 
	    private MessageSource messageSource;
	 
	    @Autowired
	    public SudokuRestErrorHandler(MessageSource messageSource) {
	        this.messageSource = messageSource;
	    }
	 
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	    public @ResponseBody ValidationErrorDTO handleValidationException(MethodArgumentNotValidException ex) throws IOException {
	        ValidationErrorDTO dto = new ValidationErrorDTO();
	        BindingResult result = ex.getBindingResult();
	        List<FieldError> fieldErrors = result.getFieldErrors();
	 
	        return processFieldErrors(fieldErrors);
	    }
	    @ExceptionHandler(SudokuException.class)
	    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	    public @ResponseBody Map<String, Object> handleInternalServerError(SudokuException ex) throws IOException {
	    	Map<String, Object>  map = new HashMap<String, Object>();
	    	map.put("error", "Sudoku Exception");
	    	map.put("message", ex.getMessage());
	    	map.put("detailMessage", ex.getErrorMessage());
	        return map;
	    }
	    private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
	        ValidationErrorDTO dto = new ValidationErrorDTO();
	 
	        for (FieldError fieldError: fieldErrors) {
	            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
	            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
	        }
	 
	        return dto;
	    }
	 
	    private String resolveLocalizedErrorMessage(FieldError fieldError) {
	        Locale currentLocale =  LocaleContextHolder.getLocale();
	        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
	 
	        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
	            String[] fieldErrorCodes = fieldError.getCodes();
	            if(fieldErrorCodes != null)
	            localizedErrorMessage = fieldErrorCodes[0];
	        }
	 
	        return localizedErrorMessage;
	    }

	    }
