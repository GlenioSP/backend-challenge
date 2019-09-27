package com.invillia.acme.service

import com.invillia.acme.domain.Order
import com.invillia.acme.domain.OrderItem
import com.invillia.acme.domain.Refund

interface RefundService {

    fun create(order: Order, orderItems: List<OrderItem>): Refund
}