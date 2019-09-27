package com.invillia.acme.service.impl

import com.invillia.acme.constant.ADDRESS_ALREADY_EXISTS
import com.invillia.acme.constant.ENTITY_MUST_HAVE_NULL_ID
import com.invillia.acme.domain.Address
import com.invillia.acme.repository.AddressRepository
import com.invillia.acme.repository.specification.AddressSpecification
import com.invillia.acme.service.AddressService
import com.invillia.acme.service.dto.AddressDTO
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AddressServiceImpl(private val addressRepository: AddressRepository): AddressService {

    override fun findOne(id: Long): AddressDTO? {
        return addressRepository.findByIdOrNull(id)?.toDto()
    }

    override fun create(addressDTO: AddressDTO): AddressDTO {
        if (addressDTO.id != null) {
            throw Exception(ENTITY_MUST_HAVE_NULL_ID)
        }

        val address: Address? = addressRepository.findByStreetAndCityAndStateAndZipCodeAndNumber(
                addressDTO.street,
                addressDTO.city,
                addressDTO.state,
                addressDTO.zipCode,
                addressDTO.number
        )
        if (address != null) {
            throw Exception(ADDRESS_ALREADY_EXISTS)
        }
        return addressRepository.save(Address.fromDto(addressDTO)).toDto()
    }

    override fun search(street: String?, city: String?, state: String?, zipCode: String?, number: Short?): List<AddressDTO> {
        val addressSpecification = AddressSpecification()
        val specs: Specification<Address> = addressSpecification.addressContains(street, city, state, zipCode, number)
        return addressRepository.findAll(specs).map { it.toDto() }
    }
}