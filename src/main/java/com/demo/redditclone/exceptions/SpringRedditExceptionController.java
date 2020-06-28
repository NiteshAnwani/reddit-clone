package com.demo.redditclone.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.redditclone.dto.ExceptionResponse;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class SpringRedditExceptionController {

	@ExceptionHandler(value = SpringRedditException.class)
	public ResponseEntity<ExceptionResponse> springRedditException(SpringRedditException exception) {
		ExceptionResponse excep = new ExceptionResponse();
		excep.setExceptionMessage(exception.getMessage());
		excep.setExceptionType("SpringRedditException");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(excep);
	}

	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<ExceptionResponse> userNotFoundException(UserNotFoundException exception) {
		ExceptionResponse excep = new ExceptionResponse();
		excep.setExceptionMessage(exception.getMessage());
		excep.setExceptionType("UserNotFoundException");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(excep);
	}

	@ExceptionHandler(value = PostNotFoundException.class)
	public ResponseEntity<ExceptionResponse> postNotFoundException(PostNotFoundException exception) {
		ExceptionResponse excep = new ExceptionResponse();
		excep.setExceptionMessage(exception.getMessage());
		excep.setExceptionType("PostNotFoundException");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(excep);
	}

	@ExceptionHandler(value = RefreshTokenNotFoundException.class)
	public ResponseEntity<ExceptionResponse> refreshTokenException(RefreshTokenNotFoundException exception) {
		ExceptionResponse excep = new ExceptionResponse();
		excep.setExceptionMessage(exception.getMessage());
		excep.setExceptionType("RefreshTokenNotFoundException");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(excep);
	}

	@ExceptionHandler(value = SubredditNotFoundException.class)
	public ResponseEntity<ExceptionResponse> subredditExceptionException(SubredditNotFoundException exception) {
		ExceptionResponse excep = new ExceptionResponse();
		excep.setExceptionMessage(exception.getMessage());
		excep.setExceptionType("SubredditNotFoundException");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(excep);
	}

	@ExceptionHandler(value = VoteException.class)
	public ResponseEntity<ExceptionResponse> voteException(VoteException exception) {
		ExceptionResponse excep = new ExceptionResponse();
		excep.setExceptionMessage(exception.getMessage());
		excep.setExceptionType("VoteException");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(excep);
	}

	@ExceptionHandler(value = VerificationTokenNotFound.class)
	public ResponseEntity<ExceptionResponse> verificationException(VerificationTokenNotFound exception) {
		ExceptionResponse excep = new ExceptionResponse();
		excep.setExceptionMessage(exception.getMessage());
		excep.setExceptionType("VerificationTokenNotFound");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(excep);
	}

	@ExceptionHandler(value = JWTTokenException.class)
	public ResponseEntity<ExceptionResponse> jwtTokenException(JWTTokenException exception) {
		ExceptionResponse excep = new ExceptionResponse();
		excep.setExceptionMessage(exception.getMessage());
		excep.setExceptionType("JWTTokenException");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(excep);
	}
}
