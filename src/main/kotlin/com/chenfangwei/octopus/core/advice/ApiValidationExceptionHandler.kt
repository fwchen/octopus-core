package com.chenfangwei.octopus.core.advice

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ApiValidationExceptionHandler : ResponseEntityExceptionHandler() {
  override fun handleMethodArgumentNotValid(
          ex: MethodArgumentNotValidException,
          headers: HttpHeaders,
          status: HttpStatus,
          request: WebRequest): ResponseEntity<Any> {
    return ResponseEntity(HttpStatus.BAD_REQUEST)
  }
}