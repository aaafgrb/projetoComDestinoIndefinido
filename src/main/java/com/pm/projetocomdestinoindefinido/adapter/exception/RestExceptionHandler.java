package com.pm.projetocomdestinoindefinido.adapter.exception;

import com.pm.projetocomdestinoindefinido.adapter.exception.exceptions.ResourceNotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {
//  @ExceptionHandler(ResourceNotFoundException.class)
//  public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
//    Map<String, Object> response = new HashMap<>();
//    response.put("error", "Not Found");
//    response.put("message", ex.getMessage());
//    response.put("status", HttpStatus.NOT_FOUND.value());
//    response.put("timestamp", LocalDateTime.now());
//    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) throws Exception {
    Map<String, Object> response = new HashMap<>();
    response.put("error", "Internal Server Error");
    response.put("message", ex.getMessage());
    response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    response.put("timestamp", LocalDateTime.now());
//    throw ex;
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
