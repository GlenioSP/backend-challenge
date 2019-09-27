package com.invillia.acme.service

import com.invillia.acme.service.dto.AddressDTO

interface AddressService {

    fun findOne(id: Long): AddressDTO?

    fun create(addressDTO: AddressDTO): AddressDTO
}