package com.lookfor.yanaorental.exceptions;

import com.lookfor.json.schemas.generated.ErrorResponse;
import com.lookfor.yanaorental.exceptions.auth.InvalidTokenException;
import com.lookfor.yanaorental.exceptions.auth.OAuth2AuthenticationProcessingException;
import com.lookfor.yanaorental.exceptions.auth.VerificationTokenExpiredException;
import com.lookfor.yanaorental.exceptions.rest.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler({Exception.class, RuntimeException.class})
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        return handle(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({NotFoundException.class, InvalidTokenException.class, InternalAuthenticationServiceException.class})
    protected ResponseEntity<ErrorResponse> handleNotFoundException(Exception e) {
        return handle(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class, ValidationException.class, IOException.class})
    protected ResponseEntity<ErrorResponse> handleBadRequestException(Exception e) {
        return handle(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AlreadyExistsException.class, OAuth2AuthenticationProcessingException.class})
    protected ResponseEntity<ErrorResponse> handleAlreadyExistsException(Exception e) {
        return handle(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({AccessDeniedException.class, UserIsNotInRentalException.class})
    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(Exception e) {
        return handle(e, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({UnauthorizedException.class, UsernameNotFoundException.class, BadCredentialsException.class, DisabledException.class, VerificationTokenExpiredException.class})
    protected ResponseEntity<ErrorResponse> handleUnauthorizedException(Exception e) {
        return handle(e, HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<ErrorResponse> handle(Exception e, HttpStatus status) {
        log.error(e.getMessage());
        ErrorResponse response = getErrorResponse(e, status);
        return ResponseEntity
                .status(status)
                .body(response);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ErrorResponse response = getErrorResponse(e, status);
        return new ResponseEntity<>(response, headers, status);
    }

    private ErrorResponse getErrorResponse(Exception e, HttpStatus status) {
        ErrorResponse response = new ErrorResponse();
        response.setException(e.getClass().getName());
        response.setReason(status.value() + " " + status.getReasonPhrase());
        response.setDescription(e.getMessage());
        return response;
    }
}
