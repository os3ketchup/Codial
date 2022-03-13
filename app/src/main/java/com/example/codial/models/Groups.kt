package com.example.codial.models

import java.io.Serializable

class Groups:Serializable {
    var id:Int?=null
    var name:String?=null
    var mentors:Mentors?=null
    var timeStarting:String? = null
    var dataAttending:String? = null
    var courses:Courses? = null
    var amountStudent:Int? = 0

    constructor(
        id: Int?,
        name: String?,
        mentors: Mentors?,
        timeStarting: String?,
        dataAttending: String?,
        courses: Courses?,
        amountStudent: Int?
    ) {
        this.id = id
        this.name = name
        this.mentors = mentors
        this.timeStarting = timeStarting
        this.dataAttending = dataAttending
        this.courses = courses
        this.amountStudent = amountStudent
    }

    constructor(
        name: String?,
        mentors: Mentors?,
        timeStarting: String?,
        dataAttending: String?,
        courses: Courses?,
        amountStudent: Int?
    ) {
        this.name = name
        this.mentors = mentors
        this.timeStarting = timeStarting
        this.dataAttending = dataAttending
        this.courses = courses
        this.amountStudent = amountStudent
    }

    constructor(name: String?, timeStarting: String?, dataAttending: String?) {
        this.name = name
        this.timeStarting = timeStarting
        this.dataAttending = dataAttending
    }


}