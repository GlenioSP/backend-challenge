package com.invillia.acme.web.rest

import com.invillia.acme.constant.ENTITY_NOT_FOUND
import com.invillia.acme.service.AddressService
import com.invillia.acme.service.dto.AddressDTO
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/address")
class AddressController(private val addressService: AddressService) {

    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun findOne(@PathVariable id: Long): ResponseEntity<AddressDTO> {
        val address = addressService.findOne(id) ?: throw Exception(ENTITY_NOT_FOUND)
        return ResponseEntity(address, HttpStatus.OK)
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun create(b: UriComponentsBuilder, @Valid @RequestBody addressDTO: AddressDTO): ResponseEntity<AddressDTO> {
        val createdAddressDTO: AddressDTO = addressService.create(addressDTO)
        val uriComponents: UriComponents = b.path("/api/address/{id}").buildAndExpand(createdAddressDTO.id)
        val headers = HttpHeaders()
        headers.location = uriComponents.toUri()
        return ResponseEntity(createdAddressDTO, headers, HttpStatus.CREATED)
    }
}