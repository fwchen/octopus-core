package com.chenfangwei.octopus.core.share.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "not found")
class EntityNotFoundException(message: String) : Exception(message)
