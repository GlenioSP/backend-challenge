package com.invillia.acme.domain

import com.invillia.acme.service.dto.query.RefundOrderQuery
import javax.persistence.*

@Entity
@Table(name = "refunds")
class Refund(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,
        @Column(name = "orders_id")
        val orderId: Long,
        @Column(name = "order_items_id")
        val orderItemsIds: String
) {
    fun toRefundOrderQuery(): RefundOrderQuery = RefundOrderQuery(
            id = this.id,
            orderId = this.orderId,
            orderItemsIds = this.orderItemsIds)
}