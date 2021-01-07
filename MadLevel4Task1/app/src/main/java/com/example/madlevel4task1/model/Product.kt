package com.example.madlevel4task1.model

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey


@Entity(tableName = "productTable")
data class Product(

    @ColumnInfo(name = "productName")
    var productName: String,

    @ColumnInfo(name = "productAmount")
    var productAmount: Short,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)