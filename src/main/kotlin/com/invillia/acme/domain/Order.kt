package com.invillia.acme.domain

import com.invillia.acme.constant.enum.OrderStatus
import com.invillia.acme.service.dto.query.OrderQuery
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
        var status: OrderStatus,
        @OneToOne
        @JoinColumn(name = "addresses_id")
        val address: Address,
        @OneToOne
        @JoinColumn(name = "stores_id")
        val store: Store,
        @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
        var orderItems: List<OrderItem> = emptyList(),
        @OneToOne(mappedBy = "order", orphanRemoval = true)
        var payment: Payment? = null
) {
    fun toOrderQuery(): OrderQuery = OrderQuery(
            id = this.id,
            confirmationDate = this.confirmationDate,
            status = this.status,
            address = this.address.toDto(),
            store = this.store.toDto(),
            orderItems = this.orderItems.map { it.toOrderItemQuery() })

    companion object {
        fun fromOrderQuery(orderQuery: OrderQuery) = Order(
                id = orderQuery.id,
                confirmationDate = orderQuery.confirmationDate,
                status = orderQuery.status,
                address = Address.fromDto(orderQuery.address),
                store = Store.fromDto(orderQuery.store))
    }
}
