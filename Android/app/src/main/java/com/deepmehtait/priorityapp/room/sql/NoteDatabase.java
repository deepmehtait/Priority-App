package com.deepmehtait.priorityapp.room.sql;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.deepmehtait.priorityapp.room.dao.NoteDao;
import com.deepmehtait.priorityapp.room.entity.Note;

@Database(entities = Note.class, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    // To populate DB with Initial entries
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopluateDbAsyncTask(instance).execute();
        }
    };

    // Async task for adding entries when DB is created

    private static class PopluateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private NoteDao noteDao;

        private PopluateDbAsyncTask(NoteDatabase db) {
            noteDao = db.noteDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.instert(new Note("Welcome", "Add description", 10));
            noteDao.instert(new Note("Here You can", "Add Priority", 9));
            noteDao.instert(new Note("Here You can also", "Edit Priority", 8));
            noteDao.instert(new Note("And", "Swipe to delete", 7));

            return null;
        }
    }
}
