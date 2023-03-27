package com.example.coursework
import androidx.room.*
import androidx.room.Dao
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert
    fun insertItem(item: parking)
    @Query("Delete FROM Cars where carId = :id")
    fun deleteCarForId(id: Int?)
    @Query("Delete FROM parkings where id = :idP")
    fun deleteParkingForId(idP: Int)
    @Query("SELECT * FROM Cars")
    fun getAllItem(): Flow<List<Cars>>
    @Insert
    fun insertCar(item: Cars)
    @Insert
    fun insertRta(item: Rta)
    @Insert
    fun insertContracts(item: Contracts)
    @Insert
    fun insertUser(item: Users)
    @Insert
    fun insertParking(item: parking)
    @Update
    fun updateCar(item: Cars)
    @Update
    fun updateUser(item: Users)




//@Delete()
    //fun deleteItem(item: parking)

}