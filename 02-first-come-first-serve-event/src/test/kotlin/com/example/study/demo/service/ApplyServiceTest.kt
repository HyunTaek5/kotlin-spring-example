package com.example.study.demo.service

import com.example.study.demo.repository.CouponRepository
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
}
