package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frzbg_orpozj7 on 8/28/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param wordCombos  The objects to represent in the ListView.
     */
    public WordAdapter(Context context, ArrayList<Word> wordCombos) {
        super(context, 0, wordCombos);
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
        Word currentWord = getItem(position);

        //Find the TextView in the list_item.xml layout with default translation
        TextView defaultTransTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        //Get the default translation from the current Word object and set this text on the default TextView
        defaultTransTextView.setText(currentWord.getmDefaultTranslation());

        //Find the TextView in the list_item.xml layout with miwok translation
        TextView miwokTransTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        //Get the miwok translation from the current Word object and set this text on the miwok TextView
        miwokTransTextView.setText(currentWord.getmMiwokTranslation());

        //Return the whole list item layout (containing 2 TextViews) so that it can be shown in the ListView
        return listItemView;

    }
}
