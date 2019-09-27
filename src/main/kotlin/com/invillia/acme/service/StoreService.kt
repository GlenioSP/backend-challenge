package com.invillia.acme.service

import com.invillia.acme.service.dto.SearchStoreDTO
import com.invillia.acme.service.dto.StoreDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface StoreService {

    fun findAll(pageable: Pageable): Page<StoreDTO>

    fun findOne(id: Long): StoreDTO?

    fun create(storeDTO: StoreDTO): StoreDTO

    fun update(id: Long, storeDTO: StoreDTO): StoreDTO

    fun search(searchStoreDTO: SearchStoreDTO): List<StoreDTO>
}