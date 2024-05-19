package com.example.study.demo.repository

import com.example.study.demo.domain.Stock
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface StockRepository : JpaRepository<Stock, Long> {

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("SELECT s FROM Stock s WHERE s.id = :id")
  fun findByIdWithPessimisticLock(id: Long): Stock

  @Lock(LockModeType.OPTIMISTIC)
  @Query("SELECT s FROM Stock s WHERE s.id = :id")
  fun findByIdWithOptimisticLock(id: Long): Stock
}
