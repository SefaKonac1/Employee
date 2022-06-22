package com.macademia.interview.dto

import java.time.LocalDateTime

data class EmployeeDto(
    val id: String?,
    val name: String?,
    val surname: String?,
    val startedDate: Int?,
    val department: String?,
    val officeLocation: String?,
    val income: Double?
)