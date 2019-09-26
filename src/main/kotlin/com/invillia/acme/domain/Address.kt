package com.invillia.acme.domain

import javax.persistence.*

@Entity
@Table(name="addresses")
class Address(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val street: String,
    val city: String,
    @Column(columnDefinition = "char")
    val state: String,
    @Column(name="zip_code", columnDefinition = "char")
    val zipCode: String,
    val number: Short
)