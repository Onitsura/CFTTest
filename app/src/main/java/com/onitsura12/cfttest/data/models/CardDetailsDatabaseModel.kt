package com.onitsura12.cfttest.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "details_table")
data class CardDetailsDatabaseModel(
    var number: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    @Embedded(prefix = "country")
    val country: Country?,
    @Embedded(prefix = "bank")
    val bank: Bank?
)


