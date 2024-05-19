package com.example.study.demo.facade

import com.example.study.demo.service.StockService
import java.util.concurrent.TimeUnit
import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component

@Component
class RedissonLockStockFacade(
    private val redissonClient: RedissonClient,
    private val stockService: StockService
) {
  fun decrease(id: Long, quantity: Long) {
    val lock: RLock = redissonClient.getLock("stock:$id")

    try {
      val available = lock.tryLock(10, 1, TimeUnit.SECONDS)
      if (!available) {
        println("Lock 획득 실패")
        return
      }

      stockService.decrease(id, quantity)
    } catch (e: Exception) {
      throw RuntimeException(e)
    } finally {
      lock.unlock()
    }
  }
}
