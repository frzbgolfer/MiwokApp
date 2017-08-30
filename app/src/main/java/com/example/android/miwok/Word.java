package com.example.android.miwok;

import android.graphics.drawable.Drawable;

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

    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Create a new Word object with associated image resource id in addtion to the word pairs
     * @param mDefaultTranslation is the word in a language that the user is familiar with (such as English)
     * @param mMiwokTranslation is the word in the Miwok language
     * @param mImageResourceId is the image resource id associated with the word
     */
    public Word(String mDefaultTranslation, String mMiwokTranslation, int mImageResourceId) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mImageResourceId = mImageResourceId;
    }

    /**
     * Create a new Word object with just the word pairs
     * @param mDefaultTranslation is the word in a language that the user is familiar with (such as English)
     * @param mMiwokTranslation is the word in the Miwok language
     */
    public Word(String mDefaultTranslation, String mMiwokTranslation) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
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

    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
