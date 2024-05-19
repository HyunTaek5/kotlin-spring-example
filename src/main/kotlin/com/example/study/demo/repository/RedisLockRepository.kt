package com.example.study.demo.repository

import java.time.Duration
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class RedisLockRepository(private val redisTemplate: RedisTemplate<String, String>) {
  fun lock(key: Long): Boolean? {
    return redisTemplate
        .opsForValue()
        .setIfAbsent(generateKey(key), "lock", Duration.ofMillis(3_000))
  }

    fun unlock(key: Long): Boolean {
        return redisTemplate.delete(generateKey(key))
    }

  private fun generateKey(key: Long): String {
    return "lock:$key"
  }
}
