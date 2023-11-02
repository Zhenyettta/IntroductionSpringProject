package com.naukma.introductionspringproject.exception;

import com.naukma.introductionspringproject.exception.UniqueEmailException;
import jakarta.servlet.ServletException;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final ErrorAttributes errorAttributes;

    public GlobalExceptionHandler(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
        Map<String, Object> errorAttributes = getErrorAttributes(request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorAttributes);
    }

    @ExceptionHandler(UniqueEmailException.class)
    public final ResponseEntity<Object> handleUniqueEmailException(UniqueEmailException ex, WebRequest request) {
        Map<String, Object> errorAttributes = getErrorAttributes(request);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorAttributes);
    }

    @ExceptionHandler(io.jsonwebtoken.MalformedJwtException.class)
    public ResponseEntity<?> handleMalformedJwtException(io.jsonwebtoken.MalformedJwtException ex, WebRequest request) {
        Map<String, Object> errorAttributes = getErrorAttributes(request);
        return new ResponseEntity<>(errorAttributes, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(io.jsonwebtoken.security.SignatureException.class)
    public ResponseEntity<?> handleSignatureException(io.jsonwebtoken.security.SignatureException ex, WebRequest request) {
        Map<String, Object> errorAttributes = getErrorAttributes(request);
        return new ResponseEntity<>(errorAttributes, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        Map<String, Object> errorAttributes = getErrorAttributes(request);
        return new ResponseEntity<>(errorAttributes, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> getErrorAttributes(WebRequest webRequest) {
        ErrorAttributeOptions options = ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE, ErrorAttributeOptions.Include.EXCEPTION);
        return new HashMap<>(errorAttributes.getErrorAttributes(webRequest, options));
    }
}