package com.example.demo.consumer.consumer

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class CouponCreatedConsumer {
    @KafkaListener(topics=["coupon_create"], groupId="group_1")
    fun listener(userId: Long) {
        println("Coupon created for user: $userId")
    }
}
