package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create an arraylist of English/Miwok word combos for common family members
        ArrayList<Word> familyWords = new ArrayList<>();
        familyWords.add(new Word("father", "әpә"));
        familyWords.add(new Word("mother", "әṭa"));
        familyWords.add(new Word("son", "angsi"));
        familyWords.add(new Word("daughter", "tune"));
        familyWords.add(new Word("older brother", "taachi"));
        familyWords.add(new Word("younger brother", "chalitti"));
        familyWords.add(new Word("older sister", "teṭe"));
        familyWords.add(new Word("younger sister", "kolliti"));
        familyWords.add(new Word("grandmother", "ama"));
        familyWords.add(new Word("grandfather", "paapa"));

        //adapter knows how to create layouts for each item in the list, using the
        //simple_list_item_1.xml layout resource defined in the Android framework.
        //This list item layout contains a single {@link TextView}, which the adapter will set to
        //display a single word
        WordAdapter itemsAdapter = new WordAdapter(this, familyWords);

        //Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        //There should be a {@link LIstView} with the view ID called list, which is declared in
        //word_listyout file.
        ListView listView = (ListView) findViewById(R.id.list);

        //Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        //{@link ListView} will display list items for each word in the list of words.
        //Do this by calling the setAdapter method on the {@link ListView} object and pass in
        //1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(itemsAdapter);
    }
}

