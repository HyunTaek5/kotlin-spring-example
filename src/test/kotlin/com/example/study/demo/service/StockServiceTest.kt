package com.example.study.demo.service

import com.example.study.demo.DemoApplication
import com.example.study.demo.domain.Stock
import com.example.study.demo.repository.StockRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [DemoApplication::class])
class StockServiceTest(
    @Autowired private val stockService: StockService,
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
  fun 재고감소() {
    stockService.decrease(1, 5)

    val stock: Stock = stockRepository.findById(1).get()

    assertEquals(95, stock.getQuantity())
  }
}
