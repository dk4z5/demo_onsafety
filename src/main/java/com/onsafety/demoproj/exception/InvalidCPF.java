package com.onsafety.demoproj.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "CPF Invalido")
public class InvalidCPF extends RuntimeException {
}