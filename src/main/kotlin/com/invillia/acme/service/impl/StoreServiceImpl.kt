package com.invillia.acme.service.impl

import com.invillia.acme.constant.ENTITY_MUST_HAVE_NULL_ID
import com.invillia.acme.constant.STORE_MUST_HAVE_VALID_ADDRESS
import com.invillia.acme.constant.STORE_NAME_ALREADY_EXISTS
import com.invillia.acme.domain.Store
import com.invillia.acme.repository.StoreRepository
import com.invillia.acme.service.AddressService
import com.invillia.acme.service.StoreService
import com.invillia.acme.service.dto.StoreDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StoreServiceImpl(private val storeRepository: StoreRepository,
                       private val addressService: AddressService) : StoreService {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Transactional(readOnly = true)
    override fun findAll(pageable: Pageable): Page<StoreDTO> {
        logger.debug("Request to find all stores")
        return storeRepository.findAll(pageable).map { it.toDto() }
    }

    @Transactional
    override fun create(storeDTO: StoreDTO): StoreDTO {
        logger.debug("Request to create store {}", storeDTO)
        if (storeDTO.id != null) {
            throw Exception(ENTITY_MUST_HAVE_NULL_ID)
        }
        if (storeRepository.findByName(storeDTO.name) != null) {
            throw Exception(STORE_NAME_ALREADY_EXISTS)
        }
        addressService.findOne(storeDTO.addressId) ?: throw Exception(STORE_MUST_HAVE_VALID_ADDRESS)
        return storeRepository.save(Store.fromDto(storeDTO)).toDto()
    }
}