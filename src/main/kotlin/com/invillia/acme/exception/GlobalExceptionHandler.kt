package com.invillia.acme.exception

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime

@ControllerAdvice
class GlobalExceptionHandler {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(value = [EntityNotFoundException::class])
    fun handleEntityNotFound(e: EntityNotFoundException, req: WebRequest): ResponseEntity<ErrorDetails> {
        logger.error("A EntityNotFoundException was raised: ", e)
        val errorDetails: ErrorDetails = ErrorDetails(LocalDateTime.now(), e.msg, req.getDescription(false))
        return ResponseEntity(errorDetails, HttpStatus.NOT_FOUND)
    }
}

class ErrorDetails(val timestamp: LocalDateTime, val message: String, val details: String)

class EntityConflictException(val msg: String) : BaseException(msg)

class EntityNotFoundException(val msg: String) : BaseException(msg)