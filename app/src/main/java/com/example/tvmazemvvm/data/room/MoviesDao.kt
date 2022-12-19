package com.example.tvmazemvvm.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tvmazemvvm.domain.models.Show


@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies() : LiveData<List<Show>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(shows : List<Show>)
}