package com.estefano.codelabroom;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.estefano.codelabroom.REPLY";

    private EditText editText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        editText = findViewById(R.id.edit_word);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(editText.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String word = editText.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, word);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}

