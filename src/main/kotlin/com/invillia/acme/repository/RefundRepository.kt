package com.invillia.acme.repository

import com.invillia.acme.domain.Refund
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RefundRepository : JpaRepository<Refund, Long>