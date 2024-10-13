package com.estefano.codelabroom;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

class WordRepository {

    private final WordDao wordDao;
    private final LiveData<List<Word>> allWords;
    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        wordDao = db.wordDao();
        allWords = wordDao.getAlphabetizedWords();
    }

    LiveData<List<Word>> getAllWords() {
        return allWords;
    }

    void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> {
            wordDao.insert(word);
        });
    }
}
