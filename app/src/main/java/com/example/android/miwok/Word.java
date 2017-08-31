package com.example.android.miwok;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.view.View;

/**
 * Created by frzbg_orpozj7 on 8/28/2017.
 */

public class Word {
    //Default translation for the word
    private String mDefaultTranslation;
    //Miwok translation for the word
    private String mMiwokTranslation;
    //Image resourse ID for the word
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    //Audio for the word
    private int mWordAudio;

    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Create a new Word object with associated image and audio in addition to the word pairs
     *
     * @param mDefaultTranslation is the word in a language that the user is familiar with (such as English)
     * @param mMiwokTranslation   is the word in the Miwok language
     * @param mImageResourceId    is the image resource id associated with the word
     * @param mWordAudio          is the audio resource id associated with the word
     */
    public Word(String mDefaultTranslation, String mMiwokTranslation, int mImageResourceId, int mWordAudio) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mImageResourceId = mImageResourceId;
        this.mWordAudio = mWordAudio;
    }

    /**
     * Create a new Word object with associated image resource id in addtion to the word pairs
     *
     * @param mDefaultTranslation is the word in a language that the user is familiar with (such as English)
     * @param mMiwokTranslation   is the word in the Miwok language
     * @param mWordAudio    is the audio resource id associated with the word
     */
    public Word(String mDefaultTranslation, String mMiwokTranslation, int mWordAudio) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mWordAudio = mWordAudio;
    }


    //Get the default translation of the word
    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    //Get the Miwok translation of the word
    public String getmMiwokTranslation() {

        return mMiwokTranslation;
    }

    //Get the image resource id of the word
    public int getmImageResourceId() {
        return mImageResourceId;
    }

    //Return whether or not the word has an image
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    //Get the Audio file of the word
    public int getmWordAudio() {
        return mWordAudio;
    }

}