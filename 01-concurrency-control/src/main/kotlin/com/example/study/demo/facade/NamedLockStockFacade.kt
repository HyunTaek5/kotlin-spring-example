package com.example.study.demo.facade

import com.example.study.demo.repository.LockRepository
import com.example.study.demo.service.StockService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class NamedLockStockFacade(
    private val lockRepository: LockRepository,
    private val stockService: StockService
) {
  @Transactional
  fun decrease(id: Long, quantity: Long) {
    try {
      lockRepository.getLock("stock_$id")
      stockService.decrease(id, quantity)
    } finally {
      lockRepository.releaseLock("stock_$id")
    }
  }
}
