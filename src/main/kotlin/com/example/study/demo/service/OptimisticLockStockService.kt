package com.example.study.demo.service

import com.example.study.demo.repository.StockRepository
import org.springframework.stereotype.Service

@Service
class OptimisticLockStockService(private val stockRepository: StockRepository) {
  fun decrease(productId: Long, quantity: Long) {
    val stock = stockRepository.findByIdWithOptimisticLock(productId)
    stock.decrease(quantity)

    stockRepository.save(stock)
  }
}
