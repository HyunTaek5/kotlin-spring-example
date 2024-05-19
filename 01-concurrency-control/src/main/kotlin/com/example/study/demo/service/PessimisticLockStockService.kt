package com.example.study.demo.service

import com.example.study.demo.repository.StockRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PessimisticLockStockService(private val stackRepository: StockRepository) {

  @Transactional
  fun decrease(productId: Long, quantity: Long) {
    val stock = stackRepository.findByIdWithPessimisticLock(productId)
    stock.decrease(quantity)

    stackRepository.save(stock)
  }
}
