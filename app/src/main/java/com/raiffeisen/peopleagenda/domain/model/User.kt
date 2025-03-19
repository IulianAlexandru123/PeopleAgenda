package com.raiffeisen.peopleagenda.domain.model

import java.time.ZonedDateTime

internal data class User(
    val fistName: String,
    val lastName: String,
    val country: String,
    val age: Int,
    val registeredDate: ZonedDateTime,
    val profilePictureUrl: String,
)