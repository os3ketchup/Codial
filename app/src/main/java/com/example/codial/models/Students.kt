package com.example.codial.models

class Students {
    var id:Int? = null
    var surname:String? = null
    var firstname:String? = null
    var phoneNumber:String? = null
    var mentors:Mentors? = null
    var groups:Groups? = null
    var courses:Courses? = null

    constructor(
        id: Int?,
        surname: String?,
        firstname: String?,
        phoneNumber: String?,
        mentors: Mentors?,
        groups: Groups?,
        courses: Courses?
    ) {
        this.id = id
        this.surname = surname
        this.firstname = firstname
        this.phoneNumber = phoneNumber
        this.mentors = mentors
        this.groups = groups
        this.courses = courses
    }

    constructor(
        surname: String?,
        firstname: String?,
        phoneNumber: String?,
        mentors: Mentors?,
        groups: Groups?,
        courses: Courses?
    ) {
        this.surname = surname
        this.firstname = firstname
        this.phoneNumber = phoneNumber
        this.mentors = mentors
        this.groups = groups
        this.courses = courses
    }


}