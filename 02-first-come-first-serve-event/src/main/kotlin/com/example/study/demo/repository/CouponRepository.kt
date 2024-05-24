package com.example.study.demo.repository

import com.example.study.demo.domain.Coupon
import org.springframework.data.jpa.repository.JpaRepository

interface CouponRepository : JpaRepository<Coupon, Long>{}
