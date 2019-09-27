package com.invillia.acme.service.dto.command

import java.math.BigDecimal

class CreateOrderItemCommand(
        val description: String,
        val quantity: Long,
        val unitPrice: BigDecimal
)