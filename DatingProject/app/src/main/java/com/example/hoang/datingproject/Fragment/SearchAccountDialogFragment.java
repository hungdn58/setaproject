package com.example.hoang.datingproject.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hoang.datingproject.R;
import com.example.hoang.datingproject.Utilities.FontManager;

/**
 * Created by hoang on 4/17/2016.
 */
public class SearchAccountDialogFragment extends DialogFragment implements View.OnClickListener{

    private Button bt1, bt2, bt3;
    private Dialog dialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.search_account_dialog, null);
        builder.setView(view);

        dialog = builder.create();

        TextView exit_button = (TextView) view.findViewById(R.id.exit_button);
        Typeface font = FontManager.getTypeface(getActivity(), FontManager.FONTAWESOME);
        exit_button.setTypeface(font);

        bt1 = (Button) view.findViewById(R.id.personal_btn1);
        bt2 = (Button) view.findViewById(R.id.personal_btn2);
        bt3 = (Button) view.findViewById(R.id.personal_btn3);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        exit_button.setOnClickListener(this);

        bt1.setSelected(true);
        bt1.setPressed(true);

        return dialog;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_btn1:
                bt1.setSelected(true);
                bt1.setPressed(true);
                bt2.setSelected(false);
                bt2.setPressed(false);
                bt3.setSelected(false);
                bt3.setPressed(false);
                break;
            case R.id.personal_btn2:
                bt1.setSelected(false);
                bt1.setPressed(false);
                bt2.setSelected(true);
                bt2.setPressed(true);
                bt3.setSelected(false);
                bt3.setPressed(false);
                break;
            case R.id.personal_btn3:
                bt1.setSelected(false);
                bt1.setPressed(false);
                bt2.setSelected(false);
                bt2.setPressed(false);
                bt3.setSelected(true);
                bt3.setPressed(true);
                break;
            case R.id.exit_button:
                dialog.dismiss();
                break;
        }
    }
}
