package com.example.lenovo.retrofit;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;
// This class is mediator between DB_DAO and UI Activity
public class NoteRepository {

    private NoteDAO noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDB noteDB = NoteDB.getInstance(application);
        noteDao = noteDB.noteDao();
        allNotes = noteDao.getAllNotes();
    }
    public void insert(Note note){
        new InsertNoteAsyncTask(noteDao).execute(note);
    }
    public void update(Note note){
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }
    public void delete(Note note){
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }
    public void deleteAllNotes(){
        new DeleteAllNoteAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }
    //=========================Insert AsyncTask=====================================================
     public static class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void>{
        private NoteDAO MnoteDAO;

        private InsertNoteAsyncTask(NoteDAO noteDAO){
            this.MnoteDAO = noteDAO;
        }
         @Override
         protected Void doInBackground(Note... notes) {
            MnoteDAO.insert(notes[0]);
             return null;
         }
     }
    //=========================Update AsyncTask=====================================================
    public static class UpdateNoteAsyncTask extends AsyncTask<Note,Void,Void>{
        private NoteDAO MnoteDAO;

        private UpdateNoteAsyncTask(NoteDAO noteDAO){
            this.MnoteDAO = noteDAO;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            MnoteDAO.update(notes[0]);
            return null;
        }
    }
    //=========================Delete AsyncTask=====================================================
    public static class DeleteNoteAsyncTask extends AsyncTask<Note,Void,Void>{
        private NoteDAO MnoteDAO;

        private DeleteNoteAsyncTask(NoteDAO noteDAO){
            this.MnoteDAO = noteDAO;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            MnoteDAO.delete(notes[0]);
            return null;
        }
    }
    //=========================DeleteAll AsyncTask==================================================
    public static class DeleteAllNoteAsyncTask extends AsyncTask<Void,Void,Void>{
        private NoteDAO MnoteDAO;

        private DeleteAllNoteAsyncTask(NoteDAO noteDAO){
            this.MnoteDAO = noteDAO;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            MnoteDAO.deleteAllNotes();
            return null;
        }
    }

}
