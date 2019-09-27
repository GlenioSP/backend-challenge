package com.invillia.acme.service.dto.command

class CreateOrderPaymentCommand(
        val orderId: Long,
        val cardNumber: String
)