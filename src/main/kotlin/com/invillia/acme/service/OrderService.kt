package com.invillia.acme.service

import com.invillia.acme.service.dto.command.CreateOrderCommand
import com.invillia.acme.service.dto.command.RefundOrderCommand
import com.invillia.acme.service.dto.command.SearchOrderCommand
import com.invillia.acme.service.dto.query.OrderQuery
import com.invillia.acme.service.dto.query.RefundOrderQuery

interface OrderService {

    fun findOne(id: Long): OrderQuery?

    fun create(createOrderCommand: CreateOrderCommand): OrderQuery

    fun search(searchOrderDTO: SearchOrderCommand): List<OrderQuery>

    fun refundOrder(refundOrderCommand: RefundOrderCommand): RefundOrderQuery
}