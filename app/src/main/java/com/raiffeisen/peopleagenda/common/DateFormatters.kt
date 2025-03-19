package com.raiffeisen.peopleagenda.common

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun ZonedDateTime.hourAndMinutes(): String = format(DateTimeFormatter.ofPattern("HH:mm"))