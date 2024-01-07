package com.agarment.OrderService.exception;

import com.agarment.OrderService.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class RestResponseEntityHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException  orderServiceException){
        return new ResponseEntity<>(new ErrorResponse().builder()
                .errorMessage(orderServiceException.getMessage())
                .errorCode(orderServiceException.getErrorCode())
                .build(), HttpStatus.NOT_FOUND);
    }
}
