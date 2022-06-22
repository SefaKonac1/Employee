package com.macademia.interview.entity

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Employee(

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String?,

        val name: String?,
        val surname: String?,
        val startedDate: Int?,
        val department: String?,
        val officeLocation: String?,
        val income: Double?

) {
    constructor() : this(id="",name="",
                surname="",startedDate= 2000,
                department="",officeLocation="", income=0.0) {

    }
    constructor(name: String?, surname: String?, department: String?, income: Double? ) :this(
        "",name, surname, 2000,department,"",income) {


    }

    override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as Employee

                if (id != other.id) return false
                if (name != other.name) return false
                if (surname != other.surname) return false
                if (startedDate != other.startedDate) return false
                if (department != other.department) return false
                if (officeLocation != other.officeLocation) return false
                if (income != other.income) return false
                return true
        }

        override fun hashCode(): Int {
                var result = id?.hashCode() ?: 0
                result = 31 * result + (name?.hashCode() ?: 0)
                result = 31 * result + (surname?.hashCode() ?: 0)
                result = 31 * result + (startedDate?.hashCode() ?: 0)
                result = 31 * result + (department?.hashCode() ?: 0)
                result = 31 * result + (officeLocation?.hashCode() ?: 0)
                result = 31 * result + (income?.hashCode() ?: 0)
                return result
        }
}
