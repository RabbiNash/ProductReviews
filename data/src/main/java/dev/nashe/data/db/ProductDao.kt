package dev.nashe.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.nashe.data.entity.ProductEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDao {

    @Query("select * from products")
    suspend fun getProducts() : List<ProductEntity>

    @Query("SELECT * from products where name like :searchParam or description like :searchParam")
    suspend fun searchProduct(searchParam : String) : List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(obj: Collection<ProductEntity>?): LongArray?
}