package com.chenfangwei.octopus.core.share.factory

import java.util.*

fun generateId(): String {
    val uuid = UUID.randomUUID()
    return uuid.toString().replace("-", "")
}