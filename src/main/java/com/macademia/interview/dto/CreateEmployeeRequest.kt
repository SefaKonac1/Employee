package com.macademia.interview.dto

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class CreateEmployeeRequest(

    val employeeId: String?,

    @field:NotBlank(message = "Name field cannot be empty!")
    val name: String?,

    val surname: String?,
    val department: String?,
    val startedDate: Int?,
    val officeLocation: String?,

    @field:Min(value = 0, message = "Income cannot be negative number !")
    val income: Double?
)
