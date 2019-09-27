package com.invillia.acme.service.impl

import com.invillia.acme.constant.ENTITY_NOT_FOUND
import com.invillia.acme.constant.enum.OrderStatus
import com.invillia.acme.domain.Address
import com.invillia.acme.domain.Order
import com.invillia.acme.domain.OrderItem
import com.invillia.acme.domain.Store
import com.invillia.acme.exception.EntityNotFoundException
import com.invillia.acme.repository.OrderRepository
import com.invillia.acme.service.AddressService
import com.invillia.acme.service.OrderService
import com.invillia.acme.service.StoreService
import com.invillia.acme.service.dto.command.CreateOrderCommand
import com.invillia.acme.service.dto.command.SearchOrderCommand
import com.invillia.acme.service.dto.query.OrderQuery
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OrderServiceImpl(private val orderRepository: OrderRepository,
                       private val addressService: AddressService,
                       private val storeService: StoreService) : OrderService {

    override fun findOne(id: Long): OrderQuery? {
        return orderRepository.findByIdOrNull(id)?.toOrderQuery() ?: throw EntityNotFoundException("$ENTITY_NOT_FOUND$id")
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
}