package com.example.hoang.datingproject.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.hoang.datingproject.R;
import com.example.hoang.datingproject.Utilities.FontManager;

/**
 * Created by hoang on 4/14/2016.
 */
public class WriteNoteDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.write_note_dialog, null);
        builder.setView(view);

        final Dialog dialog = builder.create();

        TextView exit_button = (TextView) view.findViewById(R.id.exit_button);
        TextView post_button = (TextView) view.findViewById(R.id.post);
        Typeface font = FontManager.getTypeface(getActivity(), FontManager.FONTAWESOME);
        exit_button.setTypeface(font);
        post_button.setTypeface(font);

        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;
    }
}
