package com.example.xanderjuice.guitarstudentsignup_app;

/**
 * Created by Andrew Gaines
 *
 * Program: Editable program that add's a guitar student to a database with name,
 *          lesson day, lesson time, payment, and id.
 *
 */

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.OnClick;
import butterknife.ButterKnife;
import butterknife.BindView;


public class MainActivity extends AppCompatActivity {
    DatabaseAssistant database;

    // creating EditText variables to store and retreive data inputed by user
    EditText editName, editLessonDay, editLessonTime, editPayment, editId;

    // using butterknife for buttons. Reduces code in the on create method, more efficient
    @BindView(R.id.button_add) Button btnAddData;
    @BindView(R.id.button_viewAll) Button BtnViewAll;
    @BindView(R.id.button_delete) Button btnDelete;
    @BindView(R.id.button_update) Button btnViewUpdate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // tying in the html id's to correct buttons
        database = new DatabaseAssistant(this);
        editName = (EditText) findViewById(R.id.editText_name);
        editLessonDay = (EditText) findViewById(R.id.editText_lessonDay);
        editLessonTime = (EditText) findViewById(R.id.editText_lessonTime);
        editPayment = (EditText) findViewById(R.id.editText_payment);//added
        editId = (EditText) findViewById(R.id.editText_id);

        ButterKnife.bind(this);
    }

    @OnClick({R.id.button_viewAll, R.id.button_add, R.id.button_delete, R.id.button_update})
    public void click(View view) {
        switch (view.getId()) {

            case R.id.button_add :

                // returns true if inserted correctly
                boolean isInserted = database.insertData(editName.getText().toString(), editLessonDay.getText().toString(),
                        editLessonTime.getText().toString(), editPayment.getText().toString());

                //clearing the text fields in the app
                editId.getText().clear();
                editName.getText().clear();
                editLessonDay.getText().clear();
                editLessonTime.getText().clear();
                editPayment.getText().clear();

                if (isInserted == true)
                    Toast.makeText(MainActivity.this, "Student Added", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Can not add student", Toast.LENGTH_LONG).show();
                break;

            case R.id.button_delete :

                Integer deleteData = database.deleteData(editId.getText().toString());

                if (deleteData > 0)
                    Toast.makeText(MainActivity.this, "Student Removed", Toast.LENGTH_LONG).show();

                else
                    Toast.makeText(MainActivity.this, "Can not remove student!", Toast.LENGTH_LONG).show();

                editId.getText().clear();
                break;

            case R.id.button_update :

                boolean isUpdate = database.updateData(editId.getText().toString(),
                        editName.getText().toString(),
                        editLessonDay.getText().toString(), editLessonTime.getText().toString(), editPayment.getText().toString());
                editId.getText().clear();
                editName.getText().clear();
                editLessonDay.getText().clear();
                editLessonTime.getText().clear();
                editPayment.getText().clear();

                if (isUpdate == true)
                    Toast.makeText(MainActivity.this, "Student is updated", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Can not update student!", Toast.LENGTH_LONG).show();

            case R.id.button_viewAll :

                // cursor to traverse db
                Cursor res = database.getStudents();
                if (res.getCount() == 0) {
                    // show message
                    displayMessage("Woops!", "No current students found!");
                    return;
                }

                // using a string builder to build message
                StringBuilder sb = new StringBuilder();
                String border = "------------------------------------\n";

                // while there is still data in the tablet to retrieve
                while (res.moveToNext()) {
                    sb.append(border);
                    sb.append("Name :" + res.getString(1) + "\n");
                    sb.append("Lesson Day :" + res.getString(2) + "\n");
                    sb.append("Lesson Time :" + res.getString(3) + "\n");
                    sb.append("Payment : " + res.getString(4) + "\n");
                    sb.append("Student Id :" + res.getString(0) + "\n");
                }

                // display student info
                displayMessage("My Students", sb.toString());
        }
    }

    // using an alert dialog box to display students
    public void displayMessage(String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this, R.style.AlertDialogStyle);
        alertDialog.setCancelable(true);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.show();
    }
}




























