package com.macademia.Employee.entity

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Employee(

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String?,

        val name: String,
        val surname: String?,
        val startedDate: LocalDateTime?,
        val department: String?,
        val officeLocation: String?,

        @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinColumn(name = "personal_id", nullable = false)
        val personalInfo: EmployeePersonalInfo?,


)
