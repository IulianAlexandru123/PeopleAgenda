package com.raiffeisen.peopleagenda.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.raiffeisen.peopleagenda.domain.model.User
import java.time.ZonedDateTime

internal data class UsersResponseDto(
    @SerializedName("results")
    val results: List<UserDto>,
)

internal data class UserDto(
    @SerializedName("name")
    val name: UserNameDto,
    @SerializedName("location")
    val location: LocationDto,
    @SerializedName("dob")
    val dateOfBirth: DateOfBirthDto,
    @SerializedName("registered")
    val registeredStatus: RegisteredStatusDto,
    @SerializedName("picture")
    val picture: PictureDto,
) {
    fun toDomain(): User = User(
        fistName = name.first,
        lastName = name.last,
        country = location.country,
        age = dateOfBirth.age,
        registeredDate = registeredStatus.date,
        profilePictureUrl = picture.thumbnail,
    )
}

internal data class UserNameDto(
    @SerializedName("first")
    val first: String,
    @SerializedName("last")
    val last: String,
)

internal data class LocationDto(
    @SerializedName("country")
    val country: String,
)

internal data class DateOfBirthDto(
    @SerializedName("age")
    val age: Int,
)

internal data class RegisteredStatusDto(
    @SerializedName("date")
    val date: ZonedDateTime,
)

internal data class PictureDto(
    @SerializedName("thumbnail")
    val thumbnail: String,
)