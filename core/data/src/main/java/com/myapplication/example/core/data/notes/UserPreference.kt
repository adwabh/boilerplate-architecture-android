package com.myapplication.example.core.data.notes

import com.myapplication.example.core.data.notes.model.User

class UserPreference {
    fun getCurrentUser(): User {
        //Fixme: get current user
        return User("U0010960",
            name = "Adwait"
        )
    }
}