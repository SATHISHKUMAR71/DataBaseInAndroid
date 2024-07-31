package com.example.databaseinandroid

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION){
    companion object{
        private val DB_NAME = "notes_app.db"
        private val DB_VERSION = 1
        private val TABLE_NAME = "notes_app"
        private val COLUMN_ID = "id"
        private val COLUMN_TITLE = "title"
        private val COLUMN_CONTENT = "content"
        private val COLUMN_CREATED_AT = "created_at"
        private val COLUMN_UPDATED_AT = "updated_at"
        private val COLUMN_IS_PINNED = "is_pinned"
    }

    fun insert(note:Notes){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE,note.title)
            put(COLUMN_CONTENT,note.content)
            put(COLUMN_CREATED_AT,note.createdAt)
            put(COLUMN_IS_PINNED,note.isPinned)
            put(COLUMN_UPDATED_AT,note.updatedAt)
        }
        db.insert(TABLE_NAME,null,values)
        db.close()
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_TITLE " +
                "TEXT, $COLUMN_CONTENT TEXT, $COLUMN_CREATED_AT TEXT, $COLUMN_UPDATED_AT TEXT, $COLUMN_IS_PINNED INTEGER DEFAULT 0)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val dropQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropQuery)
        onCreate(db)
    }

}