package com.example.xanderjuice.guitarstudentsignup_app;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public Context context() {

        // app context is almost an android app, need it to test
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.xanderjuice.guitarstudentsignup_app", appContext.getPackageName());
        return appContext;
    }

    @Test
    public void userAppContext () throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.xanderjuice.guitarstudentsignup_app", appContext.getPackageName());

    }

    //@Test
    //public void testSqliteDatabase() throws Exception {
   //     DatabaseAssistant database=null;
      //  boolean insertInfo = database.insertData("Dr. Macevoy","Saturday's","1pm", "60");
  //      assertEquals(false,insertInfo);

   // }

  //  @Test
    //public void addition_isCorrect() throws Exception {
      // assertEquals(4, 2 + 2);
    //}


}
