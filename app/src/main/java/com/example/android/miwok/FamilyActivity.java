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
        familyWords.add(new Word("father", "әpә", R.drawable.family_father));
        familyWords.add(new Word("mother", "әṭa", R.drawable.family_mother));
        familyWords.add(new Word("son", "angsi", R.drawable.family_son));
        familyWords.add(new Word("daughter", "tune", R.drawable.family_daughter));
        familyWords.add(new Word("older brother", "taachi", R.drawable.family_older_brother));
        familyWords.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother));
        familyWords.add(new Word("older sister", "teṭe", R.drawable.family_older_sister));
        familyWords.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister));
        familyWords.add(new Word("grandmother", "ama", R.drawable.family_grandmother));
        familyWords.add(new Word("grandfather", "paapa", R.drawable.family_grandfather));

        //adapter knows how to create layouts for each item in the list, using the
        //simple_list_item_1.xml layout resource defined in the Android framework.
        //This list item layout contains a single {@link TextView}, which the adapter will set to
        //display a single word
        WordAdapter itemsAdapter = new WordAdapter(this, familyWords, R.color.category_family);

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

