package com.invillia.acme.exception

import com.fasterxml.jackson.annotation.JsonFormat
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

class ErrorDetails(@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                   val timestamp: LocalDateTime,
                   val message: String, val details: String)

class EntityConflictException(val msg: String) : BaseException(msg)

class EntityNotFoundException(val msg: String) : BaseException(msg)