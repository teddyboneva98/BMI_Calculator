package com.example.bmicalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    DatabaseHelper myDB;
    ArrayList<Calculations> calculationsList;
    ListView listView;
    Calculations bmicalculations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Инициализира DatabaseHelper
        myDB = new DatabaseHelper(this);

        calculationsList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        int numRows = data.getCount(); // Сумата от редове, съхранени в базата данни

        // Ако data е празна, ще изведе следното съобщение:
        if (numRows == 0) {
            Toast.makeText(History.this, "There is nothing in this database!" ,Toast.LENGTH_LONG).show();
        }
        else {

            while (data.moveToNext()) {
                bmicalculations = new Calculations(data.getString(1), data.getString(2), data.getString(3));
                calculationsList.add(bmicalculations);
            }
            final ThreeColumn_ListAdapter adapter = new ThreeColumn_ListAdapter (this, R.layout.list_adapter_view,calculationsList);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);

            //Изтрива записите от listView
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    final int selected_item = position;

                    new AlertDialog.Builder(History.this)
                            .setIcon(android.R.drawable.ic_delete)
                            .setTitle("Are you sure ?")
                            .setMessage("Do you want to delete this item")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    calculationsList.remove(selected_item);
                                    adapter.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("No",null).show();

                    return true;
                }
            });
        }
    }
}
