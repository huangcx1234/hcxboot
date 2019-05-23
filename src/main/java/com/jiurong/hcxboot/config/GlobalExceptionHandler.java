package com.jiurong.hcxboot.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@ControllerAdvice("com.jiurong.hcxboot.controller")
@ResponseBody
public class GlobalExceptionHandler {
    //客户端异常
    @ExceptionHandler(ClientException.class)
    public ResponseEntity clientExceptionHandler(ClientException ce) {
        return new ResponseEntity<>(ce.toResponseBody(), HttpStatus.BAD_REQUEST);
    }

    //数据校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        ObjectError oe = bindingResult.getAllErrors().get(0);
        return new ResponseEntity<>(new ClientException(oe.getDefaultMessage()).toResponseBody(), HttpStatus.BAD_REQUEST);
    }

    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    public ResponseEntity classCastExceptionHandler(ClassCastException ex) {
        return new ResponseEntity<>(new ClientException("类型转换异常！").toResponseBody(), HttpStatus.BAD_REQUEST);
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    public ResponseEntity iOExceptionHandler(IOException ex) {
        return new ResponseEntity<>(new ClientException("IO异常！").toResponseBody(), HttpStatus.BAD_REQUEST);
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    public ResponseEntity noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        return new ResponseEntity<>(new ClientException("未知方法异常！").toResponseBody(), HttpStatus.BAD_REQUEST);
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        return new ResponseEntity<>(new ClientException("数组越界异常！").toResponseBody(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity nullPointerExceptionHandler(NullPointerException ex) {
        return new ResponseEntity<>(new ClientException("空指针异常！").toResponseBody(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //栈溢出
    @ExceptionHandler({StackOverflowError.class})
    public ResponseEntity requestStackOverflow(StackOverflowError ex) {
        return new ResponseEntity<>(new ClientException("栈溢出！").toResponseBody(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //运行时异常
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity runtimeExceptionHandler(RuntimeException ex) {
        return new ResponseEntity<>(new ClientException("服务器异常！").toResponseBody(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}