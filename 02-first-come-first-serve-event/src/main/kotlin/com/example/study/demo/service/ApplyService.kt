package com.example.study.demo.service

import com.example.study.demo.domain.Coupon
import com.example.study.demo.repository.CouponRepository
import org.springframework.stereotype.Service

@Service
class ApplyService(private val couponRepository: CouponRepository) {
  fun apply(userId: Long) {
    val count = couponRepository.count()

    if (count > 100) {
      return
    }

    val coupon = Coupon(userId=userId)

    couponRepository.save(coupon)
  }
}
