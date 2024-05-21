package org.example.kotodo.domain.common.exception

data class InfoNotMatchException(val info: String): RuntimeException("$info is not match")
