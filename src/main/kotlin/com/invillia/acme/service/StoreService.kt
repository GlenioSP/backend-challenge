package com.invillia.acme.service

import com.invillia.acme.service.dto.StoreDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface StoreService {

    fun findAll(pageable: Pageable): Page<StoreDTO>

    fun create(storeDTO: StoreDTO): StoreDTO
}