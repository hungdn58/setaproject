package com.example.hoang.newsproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by hoang on 3/17/2016.
 */
public class MyDialog extends DialogFragment {

    private String title;
    private String content;
    private EditText mailTitle, mailContent;
    private Button send, cancel;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_background, null);
        builder.setView(view);

        mailTitle = (EditText) view.findViewById(R.id.mail_title);
        mailContent = (EditText) view.findViewById(R.id.mail_content);

        send = (Button) view.findViewById(R.id.send);
        cancel = (Button) view.findViewById(R.id.cancel);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent;

                sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, mailTitle.getText().toString());
                sendIntent.putExtra(Intent.EXTRA_TEXT, mailContent.getText().toString());
                sendIntent.setType("text/plain");

                startActivity(Intent.createChooser(sendIntent, "Send Mail"));
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mailTitle.setText("");
                mailContent.setText("");
                mailTitle.requestFocus();
            }
        });

        Dialog dialog = builder.create();
        return dialog;
    }
}
