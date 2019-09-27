package com.invillia.acme.service.impl

import com.invillia.acme.constant.*
import com.invillia.acme.constant.enum.OrderStatus
import com.invillia.acme.constant.enum.PaymentStatus
import com.invillia.acme.domain.Payment
import com.invillia.acme.exception.CommandBadRequest
import com.invillia.acme.exception.EntityNotFoundException
import com.invillia.acme.repository.OrderRepository
import com.invillia.acme.repository.PaymentRepository
import com.invillia.acme.service.PaymentService
import com.invillia.acme.service.dto.command.CreateOrderPaymentCommand
import com.invillia.acme.service.dto.query.OrderPaymentQuery
import com.invillia.acme.utils.DataSecurityUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PaymentServiceImpl(val paymentRepository: PaymentRepository,
                         val orderRepository: OrderRepository) : PaymentService {

    override fun findOne(id: Long): OrderPaymentQuery? {
        return paymentRepository.findByIdOrNull(id)?.toOrderPaymentQuery()
                ?: throw EntityNotFoundException("$ENTITY_NOT_FOUND$id")
    }

    override fun create(createOrderPaymentCommand: CreateOrderPaymentCommand): OrderPaymentQuery {
        val order = orderRepository.findByIdOrNull(createOrderPaymentCommand.orderId)
                ?: throw CommandBadRequest(ORDER_MUST_EXIST_FOR_PAYMENT)
        DataSecurityUtils.verifyCreditCardNumber(createOrderPaymentCommand.cardNumber)
                ?: throw CommandBadRequest(CREDIT_CARD_NUMBER_BAD_FORMATTED)

        if (order.status == OrderStatus.PAYMENT_PENDING) {
            val payment = Payment(null, LocalDateTime.now(), PaymentStatus.PENDING, createOrderPaymentCommand.cardNumber, order)
            return paymentRepository.save(payment).toOrderPaymentQuery()
        }
        throw CommandBadRequest(ORDER_MUST_BE_WITH_PAYMENT_PENDING_TO_BE_PAID)
    }

    override fun confirm(id: Long): OrderPaymentQuery {
        val payment = paymentRepository.findByIdOrNull(id) ?: throw EntityNotFoundException("$ENTITY_NOT_FOUND$id")

        if (payment.status == PaymentStatus.PENDING) {
            payment.status = PaymentStatus.ACCEPTED
            payment.order.status = OrderStatus.PAID
            return paymentRepository.save(payment).toOrderPaymentQuery()
        }
        throw CommandBadRequest(PAYMENT_MUST_BE_IN_PENDING_STATE_TO_BE_CONFIRMED)
    }
}