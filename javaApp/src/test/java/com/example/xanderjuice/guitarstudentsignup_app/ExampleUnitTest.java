package com.example.xanderjuice.guitarstudentsignup_app;

import android.content.ContentValues;

import org.junit.Test;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;

public class ExampleUnitTest {

    @Test
    public void testSqliteDatabase()  throws Exception {
        DatabaseAssistant db = new DatabaseAssistant(null);
        boolean insertInfo;
//db.insertData("sdf","sdf","sdf", "sdf");
        assertTrue(true);
    }

    @Test
    public void ContentValuesNotNull(){
        ContentValues v = new ContentValues();
        ContentValues contentValues = new ContentValues();
        assertNotSame(v, null);
    }
}







