package com.invillia.acme.service.dto.command

class CreateOrderCommand(
        val addressId: Long,
        val storeId: Long,
        val orderItems: List<CreateOrderItemCommand>
)