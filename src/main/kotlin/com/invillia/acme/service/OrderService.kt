package com.invillia.acme.service

import com.invillia.acme.service.dto.command.CreateOrderCommand
import com.invillia.acme.service.dto.command.SearchOrderCommand
import com.invillia.acme.service.dto.query.OrderQuery

interface OrderService {

    fun findOne(id: Long): OrderQuery?

    fun create(createOrderCommand: CreateOrderCommand): OrderQuery

    fun search(searchOrderDTO: SearchOrderCommand): List<OrderQuery>
}