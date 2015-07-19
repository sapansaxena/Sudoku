package com.sudoku.api.exception.handler;

import java.util.Map;

public class PublicException extends RuntimeException {
	private static final long serialVersionUID = 8634456051135426741L;
	   
	   public static final String SEVERITY_ERROR = "ERROR";
	   public static final String SEVERITY_WARNING = "WARNING";
	   public static final String SEVERITY_INFO = "INFO";
	   
	   /**
	    * Error code for the exception. For WFC exceptions, the error code combined
	    * with the exception class name uniquely identifies the exception. This
	    * attribute is 0 for a non-WFC error code.
	    * <p>For other java exceptions, the message combined with 
	    * the exception class name  should uniquely identify the exception.
	    */
	   private int errorCode = 0;
	   
	   /**
	    * Exception class name. Any class deriving from Throwable.
	    */
	   private String exceptionClass;
	   
	   private String severity = SEVERITY_ERROR;
	   
	   private Map<String, Object> parameters = null;
	      
	   public PublicException() {
	      super();
	   }

	   public PublicException(String message) {
	      super(message);
	   }
	   
	   public PublicException(String message, Throwable ex) {
	      super(message, ex);
	   }
	    
	   /**
	    * @return Returns the errorCode.
	    */
	   public int getErrorCode() {
	      return errorCode;
	   }

	   /**
	    * @return Returns the errorMessage.
	    */
	   public String getErrorMessage() {
	      return this.getMessage();
	   }

	   /**
	    * @param errorCode The errorCode to set.
	    */
	   public void setErrorCode(int errorCode) {
	      this.errorCode = errorCode;
	   }

	   /**
	    * @return Returns the exceptionClass.
	    */
	   public String getExceptionClass() {
	      return exceptionClass;
	   }

	   /**
	    * @param exceptionClass The exceptionClass to set.
	    */
	   public void setExceptionClass(String exceptionClass) {
	      this.exceptionClass = exceptionClass;
	   }

	   /**
	    * @return The severity of the exception
	    */
	   public String getSeverity() {
		   return severity;
	   }

	   /**
	    * Sets the exception severity.
	    * @param severity the exception severity
	    */
	   public void setSeverity(String severity) {
		   this.severity = severity;
	   }

	   public Map<String, Object> getParameters() {
	      return parameters;
	   }

	   public void setParameters(Map<String, Object> parameters) {
	      this.parameters = parameters;
	   }
	  

}
