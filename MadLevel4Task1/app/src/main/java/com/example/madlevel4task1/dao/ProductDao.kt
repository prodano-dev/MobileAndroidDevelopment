package com.example.madlevel4task1.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.madlevel4task1.model.Product

@Dao
interface ProductDao {

    @Query( "SELECT * FROM productTable")
    suspend fun getAllProducts(): List<Product>

    @Insert
    suspend fun addProductToList(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("DELETE FROM productTable")
    suspend fun deleteAllProduct()

}