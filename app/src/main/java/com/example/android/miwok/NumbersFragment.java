package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {
    /**
     * Handles playback of all the sound files
     */
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
    /**
     * audio focus change listener to manage changes in focus
     */
    private AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // Permanent loss of audio focus
                // Stop the MediaPlayer and release resources
                releaseMediaPlayer();
            } else if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
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

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        //Create an arraylist of English/Miwok word combos for common numbers
        final ArrayList<Word> numberWords = new ArrayList<>();
        numberWords.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        numberWords.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        numberWords.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        numberWords.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        numberWords.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        numberWords.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        numberWords.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        numberWords.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        numberWords.add(new Word("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        numberWords.add(new Word("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));

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
        WordAdapter itemsAdapter = new WordAdapter(getActivity(), numberWords, R.color.category_numbers);

        //Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        //There should be a {@link LIstView} with the view ID called list, which is declared in
        //word_listyout file.
        final ListView listView = (ListView) rootView.findViewById(R.id.list);

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
                Word word = numberWords.get(position);

                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(afChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //Initializes the audio file associated with the Word object
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getmWordAudio());
                    //Plays the audio file associated with the Word object and sets a listener
                    //to listen for end of audio file
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
        return rootView;
    }

    /**
     * Clean up the media player by releasing its resourses
     */
    private void releaseMediaPlayer() {
        //If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            //Regardless of the current state of teh media player, release its resources because we no longer need it
            mMediaPlayer.release();

            // Abandon audio focus when playback complete
            mAudioManager.abandonAudioFocus(afChangeListener);

            //Set the media player back to null. For our code, we've decided that setting the media player to null is
            // an easy way to tell that the media player is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }

    /**
     * Called when the Fragment is no longer started.  This is generally
     * tied to {@link NumbersActivity #onStop() Activity.onStop} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
