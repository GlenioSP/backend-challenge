package com.invillia.acme.domain

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
        @OneToOne
        @JoinColumn(name="orders_id")
        val order: Order
)