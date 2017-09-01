package com.example.android.miwok;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

public class ColorsActivity extends AppCompatActivity {
    /**Handles playback of all the sound files*/
    private MediaPlayer mMediaPlayer;
    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed playing the audio file
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    private AudioManager mAudioManager;
    /**audio focus change listener to manage changes in focus */
    private AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // Permanent loss of audio focus
                // Stop the MediaPlayer and release resources
                releaseMediaPlayer();
            }
            else if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // Pause playback
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // Your app has been granted audio focus again
                // restart playback if necessary
                mMediaPlayer.start();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Create an arraylist of English/Miwok word combos for common colors
        final ArrayList<Word> colorWords = new ArrayList<>();
        colorWords.add(new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        colorWords.add(new Word("green","chokokki", R.drawable.color_green, R.raw.color_green));
        colorWords.add(new Word("brown","ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        colorWords.add(new Word("gray","ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        colorWords.add(new Word("black","kululli", R.drawable.color_black, R.raw.color_black));
        colorWords.add(new Word("white","kelelli", R.drawable.color_white, R.raw.color_white));
        colorWords.add(new Word("dusty yellow","ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colorWords.add(new Word("mustard yellow","chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        //adapter knows how to create layouts for each item in the list, using the
        //simple_list_item_1.xml layout resource defined in the Android framework.
        //This list item layout contains a single {@link TextView}, which the adapter will set to
        //display a single word
        WordAdapter itemsAdapter = new WordAdapter(this, colorWords, R.color.category_colors);

        //Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        //There should be a {@link LIstView} with the view ID called list, which is declared in
        //word_listyout file.
        ListView listView = (ListView) findViewById(R.id.list);

        //Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        //{@link ListView} will display list items for each word in the list of words.
        //Do this by calling the setAdapter method on the {@link ListView} object and pass in
        //1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Release the media player if it currently exists because we are about to play a different sound file
                releaseMediaPlayer();

                //Gets the Word object that was clicked
                Word word = colorWords.get(position);

                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(afChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    //Initializes the audio file associated with the Word object
                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getmWordAudio());
                    //Plays the audio file associated with the Word object and sets a listener
                    //to listen for end of audio file
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });


    }

    /**
     * Clean up the media player by releasing its resourses
     */
    private void releaseMediaPlayer(){
        //If the media player is not null, then it may be currently playing a sound.
        if(mMediaPlayer != null){
            //Regardless of the current state of teh media player, release its resources because we no longer need it
            mMediaPlayer.release();

            // Abandon audio focus when playback complete
            mAudioManager.abandonAudioFocus(afChangeListener);

            //Set the media player back to null. For our code, we've decided that setting the media player to null is
            // an easy way to tell that the media player is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}

