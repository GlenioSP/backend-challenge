package com.invillia.acme.service.dto.query

import com.fasterxml.jackson.annotation.JsonFormat
import com.invillia.acme.constant.enum.OrderStatus
import com.invillia.acme.service.dto.AddressDTO
import com.invillia.acme.service.dto.StoreDTO
import java.time.LocalDateTime

class OrderQuery(
        val id: Long?,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val confirmationDate: LocalDateTime,
        val status: OrderStatus,
        val address: AddressDTO,
        val store: StoreDTO,
        val orderItems: List<OrderItemQuery>
)