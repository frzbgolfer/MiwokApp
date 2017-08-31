package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create an arraylist of English/Miwok word combos for common numbers
        final ArrayList<Word> numberWords = new ArrayList<>();
        numberWords.add(new Word("one","lutti", R.drawable.number_one, R.raw.number_one));
        numberWords.add(new Word("two","otiiko", R.drawable.number_two, R.raw.number_two));
        numberWords.add(new Word("three","tolookosu", R.drawable.number_three, R.raw.number_three));
        numberWords.add(new Word("four","oyyisa", R.drawable.number_four, R.raw.number_four));
        numberWords.add(new Word("five","massokka", R.drawable.number_five, R.raw.number_five));
        numberWords.add(new Word("six","temmokka", R.drawable.number_six, R.raw.number_six));
        numberWords.add(new Word("seven","kenekaku", R.drawable.number_seven, R.raw.number_seven));
        numberWords.add(new Word("eight","kawinta", R.drawable.number_eight, R.raw.number_eight));
        numberWords.add(new Word("nine","wo'e", R.drawable.number_nine, R.raw.number_nine));
        numberWords.add(new Word("ten","na'aacha", R.drawable.number_ten, R.raw.number_ten));

//        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
//
//        for(int i=0; i < numberEnglishWords.size(); i++){
//            TextView wordView = new TextView(this);
//            wordView.setText(numberEnglishWords.get(i));
//            rootView.addView(wordView);
//        }

        //adapter knows how to create layouts for each item in the list, using the
        //simple_list_item_1.xml layout resource defined in the Android framework.
        //This list item layout contains a single {@link TextView}, which the adapter will set to
        //display a single word
        WordAdapter itemsAdapter = new WordAdapter(this, numberWords, R.color.category_numbers);

        //Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        //There should be a {@link LIstView} with the view ID called list, which is declared in
        //word_listyout file.
        final ListView listView = (ListView) findViewById(R.id.list);

        //Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        //{@link ListView} will display list items for each word in the list of words.
        //Do this by calling the setAdapter method on the {@link ListView} object and pass in
        //1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Gets the Word object that was clicked
                Word word = numberWords.get(position);

                //Plays the audio file associated with the Word object
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getmWordAudio());
                mMediaPlayer.start();
            }
        });
    }
}
