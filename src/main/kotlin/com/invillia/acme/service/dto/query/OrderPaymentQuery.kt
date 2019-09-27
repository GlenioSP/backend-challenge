package com.invillia.acme.service.dto.query

import com.fasterxml.jackson.annotation.JsonFormat
import com.invillia.acme.constant.enum.PaymentStatus
import java.time.LocalDateTime

class OrderPaymentQuery(
        val id: Long?,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val paymentDate: LocalDateTime,
        val status: PaymentStatus,
        val cardNumber: String,
        val order: OrderQuery
)