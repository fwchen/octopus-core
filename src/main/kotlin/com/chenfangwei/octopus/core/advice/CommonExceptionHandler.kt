package com.chenfangwei.octopus.core.advice

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception

@ControllerAdvice
class CommonExceptionHandler : ResponseEntityExceptionHandler() {
  override fun handleMethodArgumentNotValid(
          ex: MethodArgumentNotValidException,
          headers: HttpHeaders,
          status: HttpStatus,
          request: WebRequest): ResponseEntity<Any> {
    logger.warn(ex.printStackTrace())
    return ResponseEntity(HttpStatus.BAD_REQUEST)
  }

  override fun handleExceptionInternal(ex: Exception, body: Any?, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
    logger.error(ex.printStackTrace())
    return super.handleExceptionInternal(ex, body, headers, status, request)
  }
}