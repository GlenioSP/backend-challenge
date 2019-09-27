package com.invillia.acme.domain

import com.invillia.acme.service.dto.command.CreateOrderItemCommand
import com.invillia.acme.service.dto.query.OrderItemQuery
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "order_items")
class OrderItem(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,
        val description: String,
        val quantity: Long,
        @Column(name = "unit_price")
        val unitPrice: BigDecimal,
        @ManyToOne
        @JoinColumn(name = "orders_id")
        val order: Order
) {
    fun toOrderItemQuery(): OrderItemQuery = OrderItemQuery(
            id = this.id,
            description = this.description,
            quantity = this.quantity,
            unitPrice = this.unitPrice)

    companion object {
        fun fromCreateOrderItemCommand(createOrderItemCommand: CreateOrderItemCommand, order: Order) = OrderItem(
                id = null,
                description = createOrderItemCommand.description,
                quantity = createOrderItemCommand.quantity,
                unitPrice = createOrderItemCommand.unitPrice,
                order = order)
    }
}