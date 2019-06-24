package com.deepmehtait.priorityapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.deepmehtait.priorityapp.room.dao.NoteDao;
import com.deepmehtait.priorityapp.room.entity.Note;
import com.deepmehtait.priorityapp.room.sql.NoteDatabase;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository (Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        new InsertNotesAsyncTask(noteDao).execute(note);

    }

    public void update(Note note) {
        new UpdateNotesAsyncTask(noteDao).execute(note);

    }

    public void delete(Note note) {
        new DeleteNotesAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(noteDao).execute();

    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }



    // Insert Note Async task

    private static class InsertNotesAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private InsertNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.instert(notes[0]);
            return null;
        }
    }

    // Update Note Async task

    private static class UpdateNotesAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private UpdateNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    // Delete Note Async task

    private static class DeleteNotesAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private DeleteNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    // Delete All Notes Async task

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private DeleteAllNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deteteAll();
            return null;
        }
    }
}
