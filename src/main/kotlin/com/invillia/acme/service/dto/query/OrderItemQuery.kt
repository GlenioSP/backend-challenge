package com.invillia.acme.service.dto.query

import java.math.BigDecimal

class OrderItemQuery(
        val id: Long?,
        val description: String,
        val quantity: Long,
        val unitPrice: BigDecimal
)