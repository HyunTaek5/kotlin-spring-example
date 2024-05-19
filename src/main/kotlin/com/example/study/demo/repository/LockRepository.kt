package com.example.study.demo.repository

import com.example.study.demo.domain.Stock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface LockRepository : JpaRepository<Stock, Long>{

    @Query("SELECT GET_LOCK(:key, 3000)", nativeQuery = true)
    fun getLock(key: String)

    @Query("SELECT RELEASE_LOCK(:key)", nativeQuery = true)
    fun releaseLock(key: String)
}
