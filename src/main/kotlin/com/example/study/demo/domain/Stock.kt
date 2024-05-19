package com.example.study.demo.domain

import jakarta.persistence.*

@Entity
class Stock (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column
    var productId: Long,

    @Column
    var quantity: Long,
) {
    @JvmName("getStockQuantity")
    fun getQuantity(): Long {
        return quantity
    }

    fun decrease(quantity: Long): Unit {
        if(this.quantity - quantity < 0) {
            throw Exception("Not enough stock")
        }

        this.quantity -= quantity
    }
}
