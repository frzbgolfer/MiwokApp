package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create an arraylist of English/Miwok word combos for common phrases
        ArrayList<Word> phrasesWords = new ArrayList<>();
        phrasesWords.add(new Word("Where are you going?", "minto wuksus"));
        phrasesWords.add(new Word("What is your name?", "tinnә oyaase'nә"));
        phrasesWords.add(new Word("My name is...", "oyaaset..."));
        phrasesWords.add(new Word("How are you feeling?", "michәksәs?"));
        phrasesWords.add(new Word("I'm feeling good.", "kuchi achit"));
        phrasesWords.add(new Word("Are you coming?", "әәnәs'aa?"));
        phrasesWords.add(new Word("Yes, I'm coming.", "hәә’ әәnәm"));
        phrasesWords.add(new Word("I'm coming.", "әәnәm"));
        phrasesWords.add(new Word("Let's go.", "yoowutis"));
        phrasesWords.add(new Word("Come here.", "әnni'nem"));

        //adapter knows how to create layouts for each item in the list, using the
        //simple_list_item_1.xml layout resource defined in the Android framework.
        //This list item layout contains a single {@link TextView}, which the adapter will set to
        //display a single word
        WordAdapter itemsAdapter = new WordAdapter(this, phrasesWords);

        //Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        //There should be a {@link LIstView} with the view ID called list, which is declared in
        //word_list.xml file.
        ListView listView = (ListView) findViewById(R.id.list);

        //Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        //{@link ListView} will display list items for each word in the list of words.
        //Do this by calling the setAdapter method on the {@link ListView} object and pass in
        //1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(itemsAdapter);
    }
}
