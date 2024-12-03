package com.yono.kickstat.exception;

import com.yono.kickstat.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ErrorResponse> handleRestClientException(RestClientException e) {
        log.error("API 호출 에러: ", e);
        return new ResponseEntity<>(new ErrorResponse("외부 API 호출 실패"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("데이터 저장 에러: ", e);
        return new ResponseEntity<>(new ErrorResponse("데이터 저장 실패"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("서버 에러: ", e);
        return new ResponseEntity<>(new ErrorResponse("서버 내부 오류"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
