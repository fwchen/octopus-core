package com.chenfangwei.octopus.core.share.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "bad request")
class BadRequestException(message: String = "bad request") : Exception(message)
