package com.invillia.acme.web.rest

import com.invillia.acme.service.StoreService
import com.invillia.acme.service.dto.StoreDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/store")
class StoreController(private val storeService: StoreService) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun findAll(pageable: Pageable): ResponseEntity<List<StoreDTO>> {
        val page: Page<StoreDTO> = storeService.findAll(pageable)
        return ResponseEntity(page.content, HttpStatus.OK)
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun create(b: UriComponentsBuilder, @Valid @RequestBody storeDTO: StoreDTO): ResponseEntity<StoreDTO> {
        val createdStoreDTO: StoreDTO = storeService.create(storeDTO)
        val uriComponents: UriComponents = b.path("/api/store/{id}").buildAndExpand(createdStoreDTO.id)
        val headers = HttpHeaders()
        headers.location = uriComponents.toUri()
        return ResponseEntity(createdStoreDTO, headers, HttpStatus.CREATED)
    }
}