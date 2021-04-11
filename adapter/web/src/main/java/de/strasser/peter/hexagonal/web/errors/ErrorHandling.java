package de.strasser.peter.hexagonal.web.errors;

import de.strasser.peter.hexagonal.application.customer.exception.BusinessException;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorHandling {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> constrainViolationException(HttpServletRequest req, ConstraintViolationException exc) {
        return ErrorResponse.createErrorResp(req, HttpStatus.BAD_REQUEST, exc);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> constrainViolationException(HttpServletRequest req, BusinessException exc) {
        return ErrorResponse.createErrorResp(req, HttpStatus.BAD_REQUEST, exc);
    }

    @Value
    public static class ErrorResponse {
        String timestamp;
        Integer status;
        String error;
        String message;
        String path;

        public static ResponseEntity<ErrorResponse> createErrorResp(HttpServletRequest req, HttpStatus code, Exception e) {
            final ErrorResponse errResponse = new ErrorResponse(
                    LocalDateTime.now().toString(),
                    code.value(),
                    e.getClass().getSimpleName(),
                    e.getMessage(),
                    req.getRequestURI());
            return ResponseEntity.status(code).body(errResponse);
        }
    }
}
