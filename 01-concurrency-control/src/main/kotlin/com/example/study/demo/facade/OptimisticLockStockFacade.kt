package com.example.study.demo.facade

import com.example.study.demo.service.OptimisticLockStockService
import org.springframework.stereotype.Component

@Component
class OptimisticLockStockFacade(
    private val optimisticLockStockService: OptimisticLockStockService
) {
  fun decrease(productId: Long, quantity: Long) {
    while (true) {
      try {
        optimisticLockStockService.decrease(productId, quantity)
        break
      } catch (e: Exception) {
        Thread.sleep(50)
      }
    }
  }
}
