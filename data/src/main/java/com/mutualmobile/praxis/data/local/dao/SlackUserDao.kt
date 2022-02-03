package com.mutualmobile.praxis.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mutualmobile.praxis.data.local.model.DBPraxisUser

@Dao
interface PraxisUserDao {
  @Query("SELECT * FROM slackuser")
  fun getAll(): List<DBPraxisUser>

  @Query("SELECT * FROM slackuser WHERE uuid IN (:slackuserIds)")
  fun loadAllByIds(slackuserIds: IntArray): List<DBPraxisUser>

  @Query(
    "SELECT * FROM slackuser WHERE first_name LIKE :first AND " +
        "last_name LIKE :last LIMIT 1"
  )
  fun findByName(first: String, last: String): DBPraxisUser

  @Insert
  fun insertAll( slackUsers: List<DBPraxisUser>)

  @Delete
  fun delete(slackUser: DBPraxisUser)
}