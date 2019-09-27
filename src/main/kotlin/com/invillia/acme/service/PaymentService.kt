package com.invillia.acme.service

import com.invillia.acme.service.dto.command.CreateOrderPaymentCommand
import com.invillia.acme.service.dto.query.OrderPaymentQuery

interface PaymentService {

    fun findOne(id: Long): OrderPaymentQuery?

    fun create(createOrderPaymentCommand: CreateOrderPaymentCommand): OrderPaymentQuery

    fun confirm(id: Long): OrderPaymentQuery
}