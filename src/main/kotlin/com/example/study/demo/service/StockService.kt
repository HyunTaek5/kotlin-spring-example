package com.example.study.demo.service

import com.example.study.demo.repository.StockRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StockService(private var stockRepository: StockRepository) {
  @Transactional
  fun decrease(productId: Long, quantity: Long) {
    val stock = stockRepository.findById(productId).get()
    stock.decrease(quantity)

    stockRepository.saveAndFlush(stock)
  }
}
