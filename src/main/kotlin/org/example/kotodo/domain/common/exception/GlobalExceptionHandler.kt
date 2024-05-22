package org.example.kotodo.domain.common.exception

import org.example.kotodo.domain.common.exception.dto.ErrorDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(e: ModelNotFoundException): ResponseEntity<ErrorDTO> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorDTO(message = e.message))
    }

    @ExceptionHandler(InvalidateDTOError::class)
    fun handleInvalidDTOError(e: InvalidateDTOError): ResponseEntity<ErrorDTO> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorDTO(message = e.message))
    }

}