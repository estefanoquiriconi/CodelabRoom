package com.estefano.codelabroom;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    private WordViewModel wordViewModel;

    private final ActivityResultLauncher<Intent> addWordLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String newWord = result.getData().getStringExtra(NewWordActivity.EXTRA_REPLY);
                    if (newWord != null) {
                        wordViewModel.insert(new Word(newWord));
                    }
                } else {
                    Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WordListAdapter adapter = new WordListAdapter(new WordListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);

        wordViewModel.getAllWords().observe(this, adapter::submitList);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
            addWordLauncher.launch(intent);
        });
    }

}
