package com.estefano.codelabroom;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class WordViewModel extends AndroidViewModel {

    private final WordRepository repository;
    private final LiveData<List<Word>> allWords;

    public WordViewModel(Application application) {
        super(application);
        repository = new WordRepository(application);
        allWords = repository.getAllWords();
    }

    LiveData<List<Word>> getAllWords() {
        return allWords;
    }

    void insert(Word word) {
        repository.insert(word);
    }
}
