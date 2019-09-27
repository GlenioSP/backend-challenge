package com.invillia.acme.service.dto.query

class RefundOrderQuery(
        val id: Long?,
        val orderId: Long,
        val orderItemsIds: String
)