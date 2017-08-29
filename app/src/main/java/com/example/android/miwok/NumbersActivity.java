package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        //Create an arraylist of English words for common numbers
        ArrayList<Word> numberEnglishWords = new ArrayList<Word>();
        numberEnglishWords.add(new Word("one","lutti"));
        numberEnglishWords.add(new Word("two","otiiko"));
        numberEnglishWords.add(new Word("three","tolookosu"));
        numberEnglishWords.add(new Word("four","oyyisa"));
        numberEnglishWords.add(new Word("five","massokka"));
        numberEnglishWords.add(new Word("six","temmokka"));
        numberEnglishWords.add(new Word("seven","kenekaku"));
        numberEnglishWords.add(new Word("eight","kawinta"));
        numberEnglishWords.add(new Word("nine","wo'e"));
        numberEnglishWords.add(new Word("ten","na'aacha"));

//        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
//
//        for(int i=0; i < numberEnglishWords.size(); i++){
//            TextView wordView = new TextView(this);
//            wordView.setText(numberEnglishWords.get(i));
//            rootView.addView(wordView);
//        }

        //adapter knows how to create layouts for each item in the list, using the
        //simple_list_item_1.xml layout resouce defined in the Android framework.
        //This list item layout contains a single {@link TextView}, which the adapter will set to
        //display a single word
        ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this, R.layout.list_item, numberEnglishWords);

        //Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        //There should be a {@link LIstView} with the view ID called list, which is declared in
        //activity_numbers.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        //Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        //{@link ListView} will display list items for each word in the list of words.
        //Do this by calling the setAdapter method on the {@link ListView} object and pass in
        //1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(itemsAdapter);
    }
}
