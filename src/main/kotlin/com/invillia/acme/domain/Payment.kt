package com.invillia.acme.domain

import com.invillia.acme.constant.enum.PaymentStatus
import com.invillia.acme.service.dto.query.OrderPaymentQuery
import com.invillia.acme.utils.DataSecurityUtils
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "payments")
class Payment(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,
        @Column(name = "payment_date")
        val paymentDate: LocalDateTime,
        @Enumerated(EnumType.STRING)
        var status: PaymentStatus,
        @Column(name = "card_number", columnDefinition = "char")
        val cardNumber: String,
        @OneToOne
        @JoinColumn(name = "orders_id")
        val order: Order
) {
    fun toOrderPaymentQuery(): OrderPaymentQuery = OrderPaymentQuery(
            id = this.id,
            paymentDate = this.paymentDate,
            status = this.status,
            cardNumber = DataSecurityUtils.maskCreditCardNumber(this.cardNumber),
            order = this.order.toOrderQuery())
}