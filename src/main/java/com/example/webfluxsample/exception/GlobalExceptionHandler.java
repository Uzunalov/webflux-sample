package com.example.webfluxsample.exception;

import java.time.LocalDateTime;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@ConditionalOnWebApplication(type = Type.REACTIVE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotificationNotFoundException.class)
  public Mono<ResponseEntity<Object>> handleNotificationNotFoundException(
      NotificationNotFoundException exception, ServerWebExchange request) {
    return handleExceptionInternal(exception,
        ErrorResponse.builder()
            .status(HttpStatus.NOT_FOUND.value())
            .error(HttpStatus.NOT_FOUND.getReasonPhrase())
            .message(exception.getMessage())
            .timestamp(LocalDateTime.now())
            .path(request.getRequest().getPath().value())
            .build(),
        new HttpHeaders(),
        HttpStatus.NOT_FOUND,
        request
    );
  }
}
