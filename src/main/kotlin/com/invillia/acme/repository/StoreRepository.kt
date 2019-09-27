package com.invillia.acme.repository

import com.invillia.acme.domain.Store
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreRepository : JpaRepository<Store, Long> {

    fun findByName(name: String): Store?

    fun findByAddressId(addressId: Long): Store?
}