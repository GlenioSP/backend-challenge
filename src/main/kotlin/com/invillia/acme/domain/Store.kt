package com.invillia.acme.domain

import com.invillia.acme.service.dto.StoreDTO
import javax.persistence.*

@Entity
@Table(name = "stores")
class Store(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,
        val name: String,
        @Column(name = "addresses_id")
        val addressId: Long
) {
    fun toDto(): StoreDTO = StoreDTO(
            id = this.id,
            name = this.name,
            addressId = this.addressId)

    companion object {
        fun fromDto(dto: StoreDTO) = Store(
                id = dto.id,
                name = dto.name,
                addressId = dto.addressId)
    }
}