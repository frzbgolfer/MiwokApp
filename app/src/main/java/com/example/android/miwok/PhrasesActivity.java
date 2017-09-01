package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

public class PhrasesActivity extends AppCompatActivity {
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

        //Create an arraylist of English/Miwok word combos for common phrases
        final ArrayList<Word> phrasesWords = new ArrayList<>();
        phrasesWords.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        phrasesWords.add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        phrasesWords.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        phrasesWords.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        phrasesWords.add(new Word("I'm feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        phrasesWords.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        phrasesWords.add(new Word("Yes, I'm coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        phrasesWords.add(new Word("I'm coming.", "әәnәm", R.raw.phrase_im_coming));
        phrasesWords.add(new Word("Let's go.", "yoowutis", R.raw.phrase_lets_go));
        phrasesWords.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

        //adapter knows how to create layouts for each item in the list, using the
        //simple_list_item_1.xml layout resource defined in the Android framework.
        //This list item layout contains a single {@link TextView}, which the adapter will set to
        //display a single word
        WordAdapter itemsAdapter = new WordAdapter(this, phrasesWords, R.color.category_phrases);

        //Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        //There should be a {@link LIstView} with the view ID called list, which is declared in
        //word_list.xml file.
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
                Word word = phrasesWords.get(position);

                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(afChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    //Initializes the audio file associated with the Word object
                    mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getmWordAudio());
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
