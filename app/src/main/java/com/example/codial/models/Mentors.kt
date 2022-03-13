package com.example.codial.models

import java.io.Serializable

class Mentors:Serializable {
    var id: Int? = null
    var surname: String? = null
    var firstname: String? = null
    var phoneNumber: String? = null
    var courses:Courses? = null

    constructor(
        id: Int?,
        surname: String?,
        firstname: String?,
        phoneNumber: String?,
        courses: Courses?
    ) {
        this.id = id
        this.surname = surname
        this.firstname = firstname
        this.phoneNumber = phoneNumber
        this.courses = courses
    }

    constructor(surname: String?, firstname: String?, phoneNumber: String?, courses: Courses?) {
        this.surname = surname
        this.firstname = firstname
        this.phoneNumber = phoneNumber
        this.courses = courses
    }

}