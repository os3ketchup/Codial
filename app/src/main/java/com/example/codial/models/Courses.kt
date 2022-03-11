package com.example.codial.models

import java.io.Serializable

class Courses:Serializable {
    var id: Int? = null
    var name: String? = null
    var aboutCourses:String?=null

    constructor(id: Int?, name: String?, aboutCourses: String?) {
        this.id = id
        this.name = name
        this.aboutCourses = aboutCourses
    }

    constructor(name: String?, aboutCourses: String?) {
        this.name = name
        this.aboutCourses = aboutCourses
    }

}