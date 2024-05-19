package com.example.study.demo.facade

import com.example.study.demo.repository.RedisLockRepository
import com.example.study.demo.service.StockService
import org.springframework.stereotype.Component

@Component
class LettuceLockStockFacade(
    private val redisLockRepository: RedisLockRepository,
    private val stockService: StockService
) {
  fun decrease(id: Long, quantity: Long) {
    while (!redisLockRepository.lock(id)!!) {
      Thread.sleep(100)
    }

      try {
      stockService.decrease(id, quantity)
      } finally {
        redisLockRepository.unlock(id)
      }
  }
}
