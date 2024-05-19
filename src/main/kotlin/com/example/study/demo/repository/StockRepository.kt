package com.example.study.demo.repository

import com.example.study.demo.domain.Stock
import org.springframework.data.jpa.repository.JpaRepository

interface StockRepository : JpaRepository<Stock, Long> {}
