package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapterToDo adapterToDo;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ModelToDo> modelToDos = new ArrayList<>();
    private FloatingActionButton bttAdd;
    private Button bttSave;
    private LinearLayout layoutAdd;
    private EditText task, date;
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DatabaseManager(this);
        dbManager.open();

        build();
        loadToDoList();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterToDo);

        bttAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setVisibility(View.GONE);
                layoutAdd.setVisibility(View.VISIBLE);
            }
        });

        bttSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (task.getText().toString().isEmpty() || date.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill the entire form", Toast.LENGTH_SHORT).show();
                } else {
                    String taskText = task.getText().toString();
                    String dateText = date.getText().toString();
                    ModelToDo modelToDo = new ModelToDo(taskText, dateText);
                    dbManager.insertTask(modelToDo);
                    loadToDoList();
                    recyclerView.setVisibility(View.VISIBLE);
                    layoutAdd.setVisibility(View.GONE);
                    task.setText("");
                    date.setText("");
                }
            }
        });

        adapterToDo.setOnItemClickListener(new AdapterToDo.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ModelToDo modelToDo = modelToDos.get(position);
                dbManager.deleteTask(modelToDo.getId());
                loadToDoList();
            }
        });
    }

    public void build() {
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        adapterToDo = new AdapterToDo(this, modelToDos);
        bttAdd = findViewById(R.id.btt_addToDo);
        bttSave = findViewById(R.id.btt_save);
        layoutAdd = findViewById(R.id.layout_add);
        task = findViewById(R.id.et_task);
        date = findViewById(R.id.et_date);
    }

    private void loadToDoList() {
        modelToDos.clear();
        modelToDos.addAll(dbManager.getAllTasks());
        adapterToDo.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }
}
