package com.invillia.acme.web.rest

import com.invillia.acme.service.OrderService
import com.invillia.acme.service.dto.command.CreateOrderCommand
import com.invillia.acme.service.dto.query.OrderQuery
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/order")
class OrderController(private val orderService: OrderService) {

    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun findOne(@PathVariable id: Long): ResponseEntity<OrderQuery> {
        return ResponseEntity(orderService.findOne(id), HttpStatus.OK)
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun create(b: UriComponentsBuilder, @Valid @RequestBody createOrderCommand: CreateOrderCommand): ResponseEntity<OrderQuery> {
        val orderQuery: OrderQuery = orderService.create(createOrderCommand)
        val uriComponents: UriComponents = b.path("/api/order/{id}").buildAndExpand(orderQuery.id)
        val headers = HttpHeaders()
        headers.location = uriComponents.toUri()
        return ResponseEntity(orderQuery, headers, HttpStatus.CREATED)
    }
}