package com.example.lenovo.retrofit;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao //abbreviation for Data Access Object
// This interface is resposible for acceessing data from out Entity Class (Note) and to write SQL syntax code
public interface NoteDAO {
    @Insert // we don't have to declare out fn we just write this annotation and it will do every thing for us
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table") // This query will delete all notes from the table named "note_table"
    void deleteAllNotes();

    // get all notes from table ordered by any attribute (Priority for example)
    @Query("SELECT * FROM  note_table ORDER BY Priority DESC")
    //LiveData Class is responsible for changing our data to UI without doing anything
    //we return a List of our notes >>if we don't a run time error will occur
    LiveData<List<Note>> getAllNotes();

}
