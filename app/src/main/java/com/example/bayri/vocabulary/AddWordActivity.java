package com.example.bayri.vocabulary;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddWordActivity extends AppCompatActivity {
    public static final String ENGLISH_WORD = "english_word";
    public static final String RUSSIAN_WORD = "russian_word";
    public static final String CATEGORY_ID = "category_word";
    public static final String CATEGORIES_LIST = "categories list";

    private int mCategoryPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        ArrayList<String> categories = (ArrayList<String>)
                getIntent().getExtras().getSerializable(CATEGORIES_LIST);

        assert categories != null;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        categorySpinner.setAdapter(adapter);

        Button addButton = (Button) findViewById(R.id.addWordButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText englishEditText = (EditText) findViewById(R.id.englishEditText);
                EditText russianEditText = (EditText) findViewById(R.id.russianEditText);
                Intent mainIntent =
                        new Intent(AddWordActivity.this, SwapActivity.class);
                mainIntent.putExtra(ENGLISH_WORD, englishEditText.getText().toString());
                mainIntent.putExtra(RUSSIAN_WORD, russianEditText.getText().toString());
                mCategoryPosition = categorySpinner.getSelectedItemPosition();
                mainIntent.putExtra(CATEGORY_ID, mCategoryPosition);
                setResult(Activity.RESULT_OK, mainIntent);
                onBackPressed();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setResult(Activity.RESULT_CANCELED, null);
    }
}
