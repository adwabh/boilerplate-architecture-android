package com.myapplication.example.core.data.notes

import javax.inject.Inject
import javax.inject.Singleton


interface NotesRepository {
    fun getNotes(user: User): Flow<>
}


class NotesRepositoryImpl @Inject constructor(): NotesRepository {
    override fun getNotes(user: User): Flow<> {
        
    }

}
