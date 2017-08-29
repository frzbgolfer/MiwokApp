package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create an arraylist of English/Miwok word combos for common colors
        ArrayList<Word> colorWords = new ArrayList<>();
        colorWords.add(new Word("red", "weṭeṭṭi"));
        colorWords.add(new Word("green","chokokki"));
        colorWords.add(new Word("brown","ṭakaakki"));
        colorWords.add(new Word("gray","ṭopoppi"));
        colorWords.add(new Word("black","kululli"));
        colorWords.add(new Word("white","kelelli"));
        colorWords.add(new Word("dusty yellow","ṭopiisә"));
        colorWords.add(new Word("mustard yellow","chiwiiṭә"));

        //adapter knows how to create layouts for each item in the list, using the
        //simple_list_item_1.xml layout resource defined in the Android framework.
        //This list item layout contains a single {@link TextView}, which the adapter will set to
        //display a single word
        WordAdapter itemsAdapter = new WordAdapter(this, colorWords);

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

