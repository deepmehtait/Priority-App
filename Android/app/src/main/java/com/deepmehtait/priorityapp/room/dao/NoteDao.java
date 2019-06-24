package com.deepmehtait.priorityapp.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.deepmehtait.priorityapp.room.entity.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void instert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deteteAll();

    @Query("SELECT * FROM NOTE_TABLE ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();
}
