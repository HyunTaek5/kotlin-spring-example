package com.example.study.demo.service

import com.example.study.demo.domain.Coupon
import com.example.study.demo.producer.CouponCreateProducer
import com.example.study.demo.repository.CouponCountRepository
import com.example.study.demo.repository.CouponRepository
import org.springframework.stereotype.Service

@Service
class ApplyService(
    private val couponRepository: CouponRepository,
    private val couponCountRepository: CouponCountRepository,
    private val couponCreateProducer: CouponCreateProducer
) {
  fun apply(userId: Long) {
    val count = couponCountRepository.increment()

    if (count != null) {
      if (count > 100) {
        return
      }
    }

    val coupon = Coupon(userId = userId)

    couponCreateProducer.create(userId)
  }
}
