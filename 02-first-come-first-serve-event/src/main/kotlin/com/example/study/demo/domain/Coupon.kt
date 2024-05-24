package com.example.study.demo.domain

import jakarta.persistence.*

@Entity
class Coupon(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private var id: Long = 0,

    @Column
    private var userId: Long,
) {
  fun getId(): Long {
    return id
  }
}
