package com.invillia.acme.domain

import com.invillia.acme.service.dto.AddressDTO
import javax.persistence.*

@Entity
@Table(name = "addresses")
class Address(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,
        val street: String,
        val city: String,
        @Column(columnDefinition = "char")
        val state: String,
        @Column(name = "zip_code", columnDefinition = "char")
        val zipCode: String,
        val number: Short
) {
    fun toDto(): AddressDTO = AddressDTO(
            id = this.id,
            street = this.street,
            city = this.city,
            state = this.state,
            zipCode = this.zipCode,
            number = this.number)

    companion object {
        fun fromDto(dto: AddressDTO) = Address(
                id = dto.id,
                street = dto.street,
                city = dto.city,
                state = dto.state,
                zipCode = dto.zipCode,
                number = dto.number)
    }
}