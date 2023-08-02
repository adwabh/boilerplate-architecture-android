package com.artha.todo.data

import javax.inject.Inject

class UserPreference @Inject constructor() {
    fun getCurrentUser(): User {
        //FIXME: get current user
        return User("U0010960",
            name = "Adwait"
        )
    }
}