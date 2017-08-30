package com.example.android.miwok;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by frzbg_orpozj7 on 8/28/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    // Resource ID used to set the TextView background color for the list of words
    private int mColorResourceId;

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param wordCombos  The objects to represent in the ListView.
     */
    public WordAdapter(Context context, ArrayList<Word> wordCombos, int background) {
        super(context, 0, wordCombos);
        mColorResourceId = background;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        //Check if the existing view is being reused, otherwise inflate the view
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //Get the {@link Word} object located at this position in the list
        final Word currentWord = getItem(position);

        //Find the TextView in the list_item.xml layout with default translation
        TextView defaultTransTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        //Get the default translation from the current Word object and set this text on the default TextView
        defaultTransTextView.setText(currentWord.getmDefaultTranslation());

        //Find the TextView in the list_item.xml layout with miwok translation
        TextView miwokTransTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        //Get the miwok translation from the current Word object and set this text on the miwok TextView
        miwokTransTextView.setText(currentWord.getmMiwokTranslation());

        //Find the ImageView in the list_item.xml layout with the item image
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.item_image_view);
        //Get the image resource ID from the current Word object and set the image to iconView if there is one
        //Otherwise hide the ImageView
        if(currentWord.hasImage()){
            iconView.setImageResource(currentWord.getmImageResourceId());
            iconView.setVisibility(View.VISIBLE);
        }else{
            iconView.setVisibility(View.GONE);
        }

        //Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.word_combo_view_group);
        //Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        //Set the background color of the text container View
        textContainer.setBackgroundColor(color);
        //The below line effectively does the same as the above 3 lines?
        //listItemView.setBackgroundResource(mColorResourceId);

        //Return the whole list item layout (containing 2 TextViews) so that it can be shown in the ListView
        return listItemView;

    }
}
