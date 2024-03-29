package jp.co.who.spring_tutorial.view.controller;

import jp.co.who.spring_tutorial.api.ApiError;
import jp.co.who.spring_tutorial.exception.SomethingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class EchoErrorController extends ResponseEntityExceptionHandler {

    private final Map<Class<? extends Exception>, String> messageMappings =
            (
                Map.of(HttpMessageNotReadableException.class,
                        "Request body is Invalid.")
            );

    private String resolveMessage(Exception ex, String defaultMessage) {
        return messageMappings.entrySet().stream()
                .filter(entry -> entry.getKey().isAssignableFrom(ex.getClass())).findFirst()
                .map(Map.Entry::getValue).orElse(defaultMessage);
    }

    private ApiError create(Exception e) {
        return create(e, e.getMessage());
    }

    private ApiError create(Exception e, String defaultMessage) {
        String message = resolveMessage(e, defaultMessage);
        log.error("failed request", e);
        return new ApiError(message, e.getClass().toString());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception e,
            Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        ApiError a = create(e);
        return super.handleExceptionInternal(
                e, a, headers, status, request
        );
    }

    // User Exception handled.
    @ExceptionHandler
    public ResponseEntity<Object> handleSomethinsNotFoundException(
            SomethingException e, WebRequest request
    ) {
        return handleExceptionInternal(
                e, null, HttpHeaders.EMPTY, HttpStatus.NOT_FOUND, request
        );
    }
    // System Exception handled.
    @ExceptionHandler
    public ResponseEntity<Object> handleSystemException(
            Exception e, WebRequest req
    ) {
        ApiError apiError = create(e, "System Error.");
        return super.handleExceptionInternal(
                e, apiError, HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR, req
        );
    }

    // 入力チェック例外のハンドリングは省略した.

}
