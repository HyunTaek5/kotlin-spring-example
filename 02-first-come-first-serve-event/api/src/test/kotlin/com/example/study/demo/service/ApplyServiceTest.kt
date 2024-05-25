package com.example.study.demo.service

import com.example.study.demo.repository.CouponRepository
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ApplyServiceTest(
    @Autowired private val applyService: ApplyService,
    @Autowired private val couponRepository: CouponRepository
) {
  @Test
  fun 한번만_응모() {
    applyService.apply(1L)

    val count = couponRepository.count()

    assertThat(count).isEqualTo(1)
  }

  @Test
  fun 여러명_응모() {
    val threadCount = 1000
    val executorService: ExecutorService = Executors.newFixedThreadPool(32)
    val latch: CountDownLatch = CountDownLatch(threadCount)

    for (i in 1..threadCount) {
      val userId = i.toLong()
      executorService.submit {
        try {
          applyService.apply(userId)
        } finally {
          latch.countDown()
        }
      }
    }

    latch.await()

    val count = couponRepository.count()

    assertThat(count).isEqualTo(100)
  }
}
