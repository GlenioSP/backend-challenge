package com.invillia.acme.web.rest

import com.invillia.acme.service.PaymentService
import com.invillia.acme.service.dto.command.CreateOrderPaymentCommand
import com.invillia.acme.service.dto.query.OrderPaymentQuery
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/payment")
class PaymentController(private val paymentService: PaymentService) {

    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun findOne(@PathVariable id: Long): ResponseEntity<OrderPaymentQuery> {
        return ResponseEntity(paymentService.findOne(id), HttpStatus.OK)
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun create(b: UriComponentsBuilder, @Valid @RequestBody createOrderPaymentCommand: CreateOrderPaymentCommand): ResponseEntity<OrderPaymentQuery> {
        val orderPaymentQuery: OrderPaymentQuery = paymentService.create(createOrderPaymentCommand)
        val uriComponents: UriComponents = b.path("/api/payment/{id}").buildAndExpand(orderPaymentQuery.id)
        val headers = HttpHeaders()
        headers.location = uriComponents.toUri()
        return ResponseEntity(orderPaymentQuery, headers, HttpStatus.CREATED)
    }

    @GetMapping(value = ["/confirm/{id}"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun confirm(@PathVariable id: Long): ResponseEntity<OrderPaymentQuery> {
        return ResponseEntity(paymentService.confirm(id), HttpStatus.OK)
    }
}