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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        build();
        toDoList();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterToDo);

        bttAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recyclerView.getVisibility() == View.VISIBLE){
                    recyclerView.setVisibility(View.GONE);
                    layoutAdd.setVisibility(View.VISIBLE);
                }
            }
        });

        bttSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (task.getText().toString().equals("") || date.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "please fill entire form", Toast.LENGTH_SHORT).show();
                } else {
                    modelToDos.add(new ModelToDo(task.getText().toString(), date.getText().toString()));
                    recyclerView.setVisibility(View.VISIBLE);
                    layoutAdd.setVisibility(View.GONE);
                }
            }
        });

        adapterToDo.setOnItemClickListener(new AdapterToDo.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                modelToDos.remove(position);
                adapterToDo.notifyItemRemoved(position);
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
        task  = findViewById(R.id.et_task);
        date = findViewById(R.id.et_date);
    }

    public void toDoList () {
        modelToDos.add(new ModelToDo("Pemograman Mobile", "12/04/2022"));
        modelToDos.add(new ModelToDo("IESI", "14/04/2022"));
    }
}