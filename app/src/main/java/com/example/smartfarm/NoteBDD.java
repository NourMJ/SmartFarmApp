package com.example.smartfarm;

import static com.example.smartfarm.DatabaseHandler.COLUMN_CONTENT;
import static com.example.smartfarm.DatabaseHandler.COLUMN_ID;
import static com.example.smartfarm.DatabaseHandler.DATABASE_NAME;
import static com.example.smartfarm.DatabaseHandler.DATABASE_VERSION;
import static com.example.smartfarm.DatabaseHandler.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class NoteBDD implements Serializable {
    private SQLiteDatabase mDb;
    private DatabaseHandler DBnote;

    public  NoteBDD(Context context)
    {
        DBnote = new DatabaseHandler(context,DATABASE_NAME, null, DATABASE_VERSION);
    }
    public SQLiteDatabase openEcriture()
    {
        mDb = DBnote.getWritableDatabase();
        return mDb;
    }
    public SQLiteDatabase openLecture()
    {
        mDb = DBnote.getReadableDatabase();
        return mDb;
    }
    public long addNote(ClasseNote N)
    {
        mDb=this.openEcriture();
        ContentValues v = new ContentValues();

        v.put(COLUMN_CONTENT, N.getContent());


        long i = mDb.insert(TABLE_NAME, null, v);
        return i;
    }
    public ClasseNote searchNote(int id)
    {
        String Query = "SELECT * FROM " + TABLE_NAME+" where "+COLUMN_ID+"=?";
        mDb = this.openLecture();
        Cursor curseur = mDb.rawQuery(Query, new String[] {String.valueOf(id)});

        if (curseur.getCount()==0)
        {
            return null;
        }

        ClasseNote note= new ClasseNote();

        if(curseur.moveToFirst())
        {
            note.setContent(curseur.getString(1));
        }

        curseur.close();
        mDb.close();
        return note;
    }
    public void deleteNote(ClasseNote N)
    {
        mDb=this.openEcriture();
        mDb.delete(TABLE_NAME, COLUMN_ID  + "=?", new String[] {String.valueOf(N.getId())});
        mDb.close();
    }
    public int updateContact(ClasseNote note)
    {
        SQLiteDatabase DB =openEcriture();
        ContentValues values = new ContentValues();

        values.put(COLUMN_CONTENT, note.getContent());

        return DB.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[] { String.valueOf(note.getId()) });

    }
    public ArrayList<ClasseNote> getAllNote()
    {
        mDb=openLecture();
        Cursor c = mDb.query(TABLE_NAME, new String[]{COLUMN_ID,COLUMN_CONTENT},
                null, null, null,
                null, null);

        if (c.getCount() == 0)
        {
            return new ArrayList<>(0);
        }

        //creation de la liste note
        ArrayList<ClasseNote> retNote = new ArrayList<>(c.getCount());
        c.moveToFirst();

        do {
            ClasseNote CN = new ClasseNote();

            CN.setId(c.getInt(0));
            CN.setContent(c.getString(1));

            retNote.add(CN);
        } while (c.moveToNext());

        c.close();
        mDb.close();
        return retNote;
    }

}
