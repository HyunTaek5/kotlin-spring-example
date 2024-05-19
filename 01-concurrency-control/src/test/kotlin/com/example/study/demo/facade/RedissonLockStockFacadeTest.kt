package com.example.study.demo.facade

import com.example.study.demo.DemoApplication
import com.example.study.demo.domain.Stock
import com.example.study.demo.repository.StockRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@SpringBootTest(classes = [DemoApplication::class])
class RedissonLockStockFacadeTest(
    @Autowired private val redissonLockStockFacade: RedissonLockStockFacade,
    @Autowired private val stockRepository: StockRepository
) {
  @BeforeEach
  fun before() {
    stockRepository.saveAndFlush(Stock(1, 1L, 100L))
  }

  @AfterEach
  fun after() {
    stockRepository.deleteAll()
  }

  @Test
  fun 동시에_100개_요청() {
    val threadCount = 100
    val executorService: ExecutorService = Executors.newFixedThreadPool(32)

    val latch = CountDownLatch(threadCount)

    for (i in 1..threadCount) {
      executorService.submit {
        try {
          redissonLockStockFacade.decrease(1L, 1L)
        } catch (e: InterruptedException) {
          throw RuntimeException(e)
        } finally {
          latch.countDown()
        }
      }
    }

    latch.await()

    val stock: Stock = stockRepository.findById(1).get()

    assertEquals(0, stock.getQuantity())
  }
}
