package com.example.jugalbeats.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class RExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleUnauthException(UnauthorizedException except) {
		ErrorResponse error = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), except.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}
//	@ExceptionHandler
//	public ResponseEntity<ErrorResponse> handleException(Exception except) {
//		ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), except.getMessage(),
//				System.currentTimeMillis());
//		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleJSONParseException(HttpMessageNotReadableException except) {
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Invalid JSON string",
				System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
		StringBuilder validErromsg = new StringBuilder(
				exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), validErromsg.toString(),
				System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
