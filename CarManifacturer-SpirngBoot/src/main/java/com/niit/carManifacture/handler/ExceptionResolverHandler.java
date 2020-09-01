package com.niit.carManifacture.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.niit.carManifacture.Service.ResponseService;
import com.niit.carManifacture.exception.DataNotFindEXception;
import com.niit.carManifacture.exception.NotSupportedDataException;
import com.niit.carManifacture.exception.UnexpectedError;
import com.niit.carManifacture.model.ResponseMesg;

@ControllerAdvice
public class ExceptionResolverHandler {

	ResponseService responseService;

	@ExceptionHandler(NotSupportedDataException.class)
	protected ResponseEntity<ResponseMesg> handleDataValidationException(NotSupportedDataException ex,
			HttpServletRequest servletRequest) {
		ResponseMesg rMesg = new ResponseMesg();
		rMesg.setId(0);
		rMesg.setMessege(ex.getMessage());
		rMesg.setStatus(false);
		return new ResponseEntity<ResponseMesg>(rMesg, HttpStatus.BAD_REQUEST);

	}

	/*
	 * HttpServletRequest is used to get the information about server like weather
	 * it accept the request, What is status of server , what are current code run
	 * on server
	 */
	@ExceptionHandler(DataNotFindEXception.class)
	protected ResponseEntity<ResponseMesg> handleDataNotFind(DataNotFindEXception ex,
			HttpServletRequest servletRequest) {
		ResponseMesg rMesg = new ResponseMesg();
		System.out.println(servletRequest.getSession().toString());
		rMesg.setId(0);
		rMesg.setMessege(ex.getMessage());
		rMesg.setStatus(false);
		return new ResponseEntity<>(rMesg, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UnexpectedError.class)
	protected ResponseEntity<ResponseMesg> unknownExceptionHndler(UnexpectedError ex) {
		ResponseMesg rMesg = new ResponseMesg();
		rMesg.setId(0);
		rMesg.setMessege(ex.getMessage());
		rMesg.setStatus(false);
		return new ResponseEntity<ResponseMesg>(rMesg, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<ResponseMesg> dataTypeMismatchException(HttpMessageNotReadableException iex,
			HttpServletRequest req) {
		ResponseMesg rMesg = new ResponseMesg();
		rMesg.setId(0);
		rMesg.setMessege("Please Cheack your input Format May be there is an error with your JSON input");
		rMesg.setStatus(false);
		return new ResponseEntity<ResponseMesg>(rMesg, HttpStatus.BAD_REQUEST);
	}

}
