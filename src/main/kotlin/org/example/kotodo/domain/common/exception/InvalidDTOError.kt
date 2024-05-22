package org.example.kotodo.domain.common.exception

data class InvalidateDTOError(val dto: String, val msg: String):
    RuntimeException("$dto has invalid value: $msg")