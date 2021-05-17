package de.strasser.peter.hexagonal.web.errors;

import de.strasser.peter.hexagonal.application.exception.BusinessException;
import io.swagger.v3.oas.annotations.media.Schema;
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
  public ResponseEntity<ErrorResponse> constrainViolationException(
      HttpServletRequest req, ConstraintViolationException exc) {
    return ErrorResponse.createErrorResp(req, HttpStatus.BAD_REQUEST, exc);
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResponse> constrainViolationException(
      HttpServletRequest req, BusinessException exc) {
    return ErrorResponse.createErrorResp(req, HttpStatus.BAD_REQUEST, exc);
  }

  @Value
  public static class ErrorResponse {
    @Schema(
        description = "Timestamp in ISO-Format",
        example = "2021-05-02T00:31:20.884820",
        required = true)
    String timestamp;

    @Schema(description = "HTTP Status", example = "404", required = true)
    Integer status;

    @Schema(
        description = "Error that was thrown",
        example = "CustomerDoesNotExist",
        required = true)
    String error;

    @Schema(
        description = "Descriptive error message",
        example = "Customer with id '3' does not exist!",
        required = true)
    String message;

    @Schema(
        description = "request path that was called",
        example = "/v1/customer/address",
        required = true)
    String path;

    public static ResponseEntity<ErrorResponse> createErrorResp(
        HttpServletRequest req, HttpStatus code, Exception e) {
      final ErrorResponse errResponse =
          new ErrorResponse(
              LocalDateTime.now().toString(),
              code.value(),
              e.getClass().getSimpleName(),
              e.getMessage(),
              req.getRequestURI());
      return ResponseEntity.status(code).body(errResponse);
    }
  }
}
