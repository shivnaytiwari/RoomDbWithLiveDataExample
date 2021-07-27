package com.example.roomdblivedataexample

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {
    @Insert
    fun addPerson(person: Person)

    @Delete
    fun removePerson(person: Person)

    @Query("select * from person")
    fun getPerson(): LiveData<List<Person>>
}