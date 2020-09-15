package com.chenfangwei.octopus.core.share.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "forbidden")
class ForbiddenRequestException(message: String = "forbidden"): Exception(message)