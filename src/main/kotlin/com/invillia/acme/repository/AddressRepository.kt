package com.invillia.acme.repository

import com.invillia.acme.domain.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository : JpaRepository<Address, Long> {

    fun findByStreetAndCityAndStateAndZipCodeAndNumber(street: String, city: String, state: String, zipCode: String, number: Short): Address?
}