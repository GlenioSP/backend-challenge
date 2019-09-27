package com.invillia.acme.service.impl

import com.invillia.acme.domain.Order
import com.invillia.acme.domain.OrderItem
import com.invillia.acme.domain.Refund
import com.invillia.acme.repository.RefundRepository
import com.invillia.acme.service.RefundService
import org.springframework.stereotype.Service

@Service
class RefundServiceImpl(val refundRepository: RefundRepository) : RefundService {

    override fun create(order: Order, orderItems: List<OrderItem>): Refund {
        val refund = Refund(null, order.id!!, orderItems.map { it.id }.filterNotNull().joinToString { "," })
        return refundRepository.save(refund)
    }
}