package com.invillia.acme.domain

import javax.persistence.*

@Entity
@Table(name="stores")
class Store(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val name: String,
        @ManyToOne
        @JoinColumn(name = "addresses_id")
        val address: Address
)