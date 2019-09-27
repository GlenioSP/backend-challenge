package com.invillia.acme.domain

import com.invillia.acme.constant.enum.OrderStatus
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
class Order(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,
        @Column(name = "confirmation_date")
        val confirmationDate: LocalDateTime,
        @Enumerated(EnumType.STRING)
        val status: OrderStatus,
        @OneToOne
        @JoinColumn(name="addresses_id")
        val address: Address,
        @OneToOne
        @JoinColumn(name="stores_id")
        val store: Store
)