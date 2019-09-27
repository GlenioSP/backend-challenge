package com.invillia.acme.service.impl

import com.invillia.acme.constant.*
import com.invillia.acme.domain.Store
import com.invillia.acme.exception.EntityNotFoundException
import com.invillia.acme.repository.StoreRepository
import com.invillia.acme.service.AddressService
import com.invillia.acme.service.StoreService
import com.invillia.acme.service.dto.SearchStoreDTO
import com.invillia.acme.service.dto.StoreDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
class StoreServiceImpl(private val storeRepository: StoreRepository,
                       private val addressService: AddressService) : StoreService {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Transactional(readOnly = true)
    override fun findAll(pageable: Pageable): Page<StoreDTO> {
        logger.debug("Request to find all stores")
        return storeRepository.findAll(pageable).map { it.toDto() }
    }

    @Transactional(readOnly = true)
    override fun findOne(id: Long): StoreDTO {
        logger.debug("Request to find store with id {}", id)
        return storeRepository.findByIdOrNull(id)?.toDto() ?: throw EntityNotFoundException("$ENTITY_NOT_FOUND$id")
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
        addressService.findOne(storeDTO.addressId) ?: throw EntityNotFoundException(STORE_MUST_HAVE_VALID_ADDRESS)
        return storeRepository.save(Store.fromDto(storeDTO)).toDto()
    }

    override fun update(id: Long, storeDTO: StoreDTO): StoreDTO {
        logger.debug("Request to update store with id {}", id)
        findOne(id)
        storeDTO.id = id
        return storeRepository.save(Store.fromDto(storeDTO)).toDto()
    }

    override fun search(searchStoreDTO: SearchStoreDTO): List<StoreDTO> {
       if (searchStoreDTO.name != null) {
           val store = storeRepository.findByName(searchStoreDTO.name) ?: throw EntityNotFoundException(NO_STORE_WAS_FOUND)
           val addresses = addressService.search(searchStoreDTO.street, searchStoreDTO.city, searchStoreDTO.state, searchStoreDTO.zipCode, searchStoreDTO.number)
           if (addresses.isNotEmpty()) {
               val address = addresses.firstOrNull { store.addressId == it.id  }
               address ?: throw EntityNotFoundException(NO_STORE_WAS_FOUND)
           }
           return listOf(store.toDto())
       } else {
           val addresses = addressService.search(searchStoreDTO.street, searchStoreDTO.city, searchStoreDTO.state, searchStoreDTO.zipCode, searchStoreDTO.number)
           val stores = addresses.map { storeRepository.findByAddressId(it.id!!) }
           return stores.filterNotNull().map { it.toDto() }
       }
    }
}