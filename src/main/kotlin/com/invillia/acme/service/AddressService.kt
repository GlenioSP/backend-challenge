package com.invillia.acme.service

import com.invillia.acme.service.dto.AddressDTO

interface AddressService {

    fun findOne(id: Long): AddressDTO?

    fun create(addressDTO: AddressDTO): AddressDTO

    fun search(street: String?, city: String?, state: String?, zipCode: String?, number: Short?): List<AddressDTO>
}