package com.mhandharbeni.firstkotlin

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Customer(
        @PrimaryKey var id : Long = 0,
        var name : String = "",
        var email : String = ""
) : RealmObject() {
//    var id : Integer?
//    get() = this.id
//    set(value) {
//        this.id = value
//    }
//
//    var name : String?
//    get() = this.name
//    set(value) {
//        this.name = value
//    }
//
//    var email : String?
//    get() = this.email
//    set(value) {
//        this.email = value
//    }
}