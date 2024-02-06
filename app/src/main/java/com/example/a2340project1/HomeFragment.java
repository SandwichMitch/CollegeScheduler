package com.example.a2340project1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2340project1.Utils.ToDoAdapter;
import com.example.a2340project1.Utils.ToDoModel;
import com.example.a2340project1.Utils.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends AppCompatActivity implements DialogCloseListener {

    private RecyclerView tasksRecyclerView;

    private ToDoAdapter tasksAdapter;

    private List<ToDoModel> taskList;

    private DatabaseHandler database;

    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar();

        database = new DatabaseHandler(this);
        database.openDatabase();

        taskList = new ArrayList<>();

        tasksRecyclerView = findViewById(R.id.toDoList);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new ToDoAdapter(database, this);
        tasksRecyclerView.setAdapter(tasksAdapter);


        fab = findViewById(R.id.fab);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        taskList = database.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }

        });

    }


    @Override
    public void handleDialogCLose(DialogInterface dialog) {
        taskList = database.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
        tasksAdapter.notifyDataSetChanged();
    }

    public void handleDialogClose(DialogInterface dialog) {

    }
}

