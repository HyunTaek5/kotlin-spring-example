package com.example.study.demo.service

import com.example.study.demo.repository.StockRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class StockService (
    private var stockRepository: StockRepository
) {
    @Transactional
    fun decrease(productId: Long, quantity: Long) {
        val stock = stockRepository.findById(productId).get()
        stock.decrease(quantity);

        stockRepository.saveAndFlush(stock)
    }
}
