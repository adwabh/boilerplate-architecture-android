package com.example.myapplication.data.repository

import com.example.myapplication.data.datasource.OfflineFirstNotesDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(val dataSource: OfflineFirstNotesDataSource) : NotesRepository {
    override fun getNotes(index: Int, offset: Int): Flow<NotesResponse> {

    }
}