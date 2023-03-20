package dev.be.learnable.common.exception;

import static dev.be.learnable.common.exception.ErrorCodeAndMessages.BAD_REQUEST_ERROR;

import dev.be.learnable.common.response.BaseResponse;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 400 Bad Request
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    private BaseResponse<Void> handleBadRequest(HttpMessageNotReadableException e) {
        Throwable mostSpecificCause = e.getMostSpecificCause();
        String errorMessage = mostSpecificCause.getMessage();
        return BaseResponse.error(BAD_REQUEST_ERROR.getCode(), errorMessage);
    }

    /**
     * 400 Bad Request : Binding Parameter
     */
    @ExceptionHandler(BindException.class)
    private BaseResponse<Void> handleBadRequestWithBindingParameter(BindException e) {
        String errorMessage = e.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.joining("\n"));
        log.error("BindException: {}", errorMessage);
        return BaseResponse.error(BAD_REQUEST_ERROR.getCode(), errorMessage);
    }

    /**
     * 400 Bad Request : Path Variable
     */
    @ExceptionHandler(ConstraintViolationException.class)
    private BaseResponse<Void> handleBadRequestWithPathVariable(ConstraintViolationException e) {
        log.error("ConstraintViolationException: {}", e.getMessage());
        return BaseResponse.error(BAD_REQUEST_ERROR.getCode(), e.getMessage());
    }

    /**
     * 400 Bad Request : HTTP Method
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    private BaseResponse<Void> handleBadRequestWithHttpMethod(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException: {}", e.getMessage());
        return BaseResponse.error(BAD_REQUEST_ERROR.getCode(), e.getMessage());
    }

    // TODO feign 구현 후 feign 에러 처리도 구성해야함

    @ExceptionHandler(BaseException.class)
    public BaseResponse<Void> handleConversionFailed(BaseException e) {

        log.error("Error Code={}, Error Message={}", e.getCode(), e.getMessage());
        return BaseResponse.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse<Void> handleException(Exception e) {
        log.error("Exception Message={}", e.getMessage());
        return BaseResponse.error(BAD_REQUEST_ERROR.getCode(), e.getMessage());
    }
}
