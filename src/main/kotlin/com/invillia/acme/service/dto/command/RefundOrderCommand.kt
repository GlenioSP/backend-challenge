package com.invillia.acme.service.dto.command

class RefundOrderCommand(
        val orderId: Long,
        val orderItemsIds: List<Long> = emptyList()
)