package com.example.madlevel4task1.repository

import android.content.Context
import com.example.madlevel4task1.dao.ProductDao
import com.example.madlevel4task1.database.ShoppingListRoomDatabase
import com.example.madlevel4task1.model.Product

class ProductRepository(context: Context) {

    private val productDao: ProductDao

    init {
        val database = ShoppingListRoomDatabase.getDatabase(context)
        productDao = database!!.productDao()
    }

    suspend fun getAllProducts(): List<Product> {
        return productDao.getAllProducts()
    }

    suspend fun addProductToList(product: Product) {
        productDao.addProductToList(product)
    }

    suspend fun deleteProduct(product: Product) {
        productDao.deleteProduct(product)
    }

    suspend fun deleteAllProduct() {
        productDao.deleteAllProduct()
    }

}