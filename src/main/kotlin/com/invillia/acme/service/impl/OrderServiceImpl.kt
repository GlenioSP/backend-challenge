package com.invillia.acme.service.impl

import com.invillia.acme.constant.ENTITY_NOT_FOUND
import com.invillia.acme.constant.ORDER_CONFIRMATION_DATE_EXPIRATION_AND_PAID
import com.invillia.acme.constant.ORDER_ITEMS_MUST_BE_INFORMED_FOR_REFUND
import com.invillia.acme.constant.ORDER_MUST_EXIST_FOR_REFUND
import com.invillia.acme.constant.enum.OrderStatus
import com.invillia.acme.constant.enum.PaymentStatus
import com.invillia.acme.domain.Address
import com.invillia.acme.domain.Order
import com.invillia.acme.domain.OrderItem
import com.invillia.acme.domain.Store
import com.invillia.acme.exception.CommandBadRequest
import com.invillia.acme.exception.EntityNotFoundException
import com.invillia.acme.repository.OrderRepository
import com.invillia.acme.service.AddressService
import com.invillia.acme.service.OrderService
import com.invillia.acme.service.RefundService
import com.invillia.acme.service.StoreService
import com.invillia.acme.service.dto.command.CreateOrderCommand
import com.invillia.acme.service.dto.command.RefundOrderCommand
import com.invillia.acme.service.dto.command.SearchOrderCommand
import com.invillia.acme.service.dto.query.OrderQuery
import com.invillia.acme.service.dto.query.RefundOrderQuery
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.stream.Collectors

@Service
class OrderServiceImpl(private val orderRepository: OrderRepository,
                       private val addressService: AddressService,
                       private val storeService: StoreService,
                       private val refundService: RefundService) : OrderService {

    val DAYS_FOR_VALID_REFUND: Short = 10

    override fun findOne(id: Long): OrderQuery? {
        return orderRepository.findByIdOrNull(id)?.toOrderQuery()
                ?: throw EntityNotFoundException("$ENTITY_NOT_FOUND$id")
    }

    override fun create(createOrderCommand: CreateOrderCommand): OrderQuery {
        val store = storeService.findOne(createOrderCommand.storeId)
                ?: throw EntityNotFoundException("$ENTITY_NOT_FOUND${createOrderCommand.storeId}")
        val address = addressService.findOne(createOrderCommand.addressId)
                ?: throw EntityNotFoundException("$ENTITY_NOT_FOUND${createOrderCommand.addressId}")

        val order = Order(null, LocalDateTime.now(), OrderStatus.PAYMENT_PENDING, Address.fromDto(address), Store.fromDto(store))
        val orderItems = createOrderCommand.orderItems.map { OrderItem.fromCreateOrderItemCommand(it, order) }
        order.orderItems = orderItems

        return orderRepository.save(order).toOrderQuery()
    }

    override fun search(searchOrderDTO: SearchOrderCommand): List<OrderQuery> {
        return emptyList()
    }

    override fun refundOrder(refundOrderCommand: RefundOrderCommand): RefundOrderQuery {
        val order = orderRepository.findByIdOrNull(refundOrderCommand.orderId)
                ?: throw EntityNotFoundException("$ORDER_MUST_EXIST_FOR_REFUND${refundOrderCommand.orderId}")
        if (order.orderItems.isEmpty()) {
            throw CommandBadRequest(ORDER_ITEMS_MUST_BE_INFORMED_FOR_REFUND)
        }
        if (isRefundValid(order)) {
            order.status = OrderStatus.REFUNDED
            order.payment?.status = PaymentStatus.REFUNDED

            val newOrderItems = order.orderItems.filter { it.id in refundOrderCommand.orderItemsIds }

            return refundService.create(order, newOrderItems).toRefundOrderQuery()
        }
        throw CommandBadRequest(ORDER_CONFIRMATION_DATE_EXPIRATION_AND_PAID)
    }

    fun isRefundValid(order: Order): Boolean {
        return isRefundNotExpired(order.confirmationDate) && order.status == OrderStatus.PAID
    }

    fun isRefundNotExpired(date: LocalDateTime): Boolean {
        val currentDate = LocalDateTime.now()
        val daysAfter = date.plusDays(DAYS_FOR_VALID_REFUND.toLong())
        return currentDate.isBefore(daysAfter) || currentDate.isEqual(daysAfter)
    }
}