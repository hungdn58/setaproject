package com.example.hoang.datingproject.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoang.datingproject.Model.FeedModel;
import com.example.hoang.datingproject.Model.InboxModel;
import com.example.hoang.datingproject.R;
import com.example.hoang.datingproject.Utilities.Const;
import com.example.hoang.datingproject.Utilities.FontManager;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewNoteActivity extends AppCompatActivity {

    private TextView plus_icon, new_note_camera, new_note_tag, new_note_location, new_note_emotion, friend_icon, right_icon;
    private ImageView new_note_image;
    private Bitmap img = null;
    private EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        getControls();
    }

    private void getControls() {
        plus_icon = (TextView) findViewById(R.id.plus_icon);
        new_note_camera = (TextView) findViewById(R.id.new_note_camera);
        new_note_tag = (TextView) findViewById(R.id.new_note_tag);
        new_note_emotion = (TextView) findViewById(R.id.new_note_emotion);
        new_note_location = (TextView) findViewById(R.id.new_note_location);
        friend_icon = (TextView) findViewById(R.id.friend_icon);
        right_icon = (TextView) findViewById(R.id.right_icon);
        description = (EditText) findViewById(R.id.new_note_description);

        Typeface font = FontManager.getTypeface(NewNoteActivity.this, FontManager.FONTAWESOME);
        plus_icon.setTypeface(font);
        new_note_camera.setTypeface(font);
        new_note_tag.setTypeface(font);
        new_note_emotion.setTypeface(font);
        new_note_location.setTypeface(font);
        friend_icon.setTypeface(font);
        right_icon.setTypeface(font);

        new_note_image = (ImageView) findViewById(R.id.new_note_image);

        plus_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedModel model = new FeedModel();
                if (description.getText() != null) {
                    model.setFeedDescription(description.getText().toString());
                }
                Toast.makeText(NewNoteActivity.this, "bug", Toast.LENGTH_LONG).show();
                model.setFeedIcon(R.drawable.profile);
                model.setFeedTitle("ハン");
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                img.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] image = stream.toByteArray();
                bundle.putSerializable("feed_item", model);
                bundle.putByteArray("img", image);
                intent.putExtra("data", bundle);
                setResult(Const.RETURN_NEW_NOTE, intent);
                finish();
            }
        });

        registerForContextMenu(new_note_camera);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.my_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.capture:
                dispatchTakePictureIntent();
                break;
            case R.id.gallery:
                getImageFromGallery();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void getImageFromGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, Const.PICTURE_CHOOSE);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, Const.REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        FeedModel model;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
        Date c = Calendar.getInstance().getTime();
        String newFormat = sdf.format(c);
        if (requestCode == Const.REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (data != null) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                img = imageBitmap;

                new_note_image.setImageBitmap(img);
//                model = new FeedModel(newFormat,imageBitmap);
//                arr.add(model);
//                adapter.notifyDataSetChanged();
//                Toast.makeText(MessagesActivity.this, "Hello", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == Const.PICTURE_CHOOSE && resultCode == RESULT_OK) {
            if (data != null) {
                Cursor cursor = getContentResolver().query(data.getData(), null, null, null, null);
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                String fileSrc = cursor.getString(idx);

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                img = BitmapFactory.decodeFile(fileSrc, options);

                //scale size to read
                options.inSampleSize = Math.max(1, (int) Math.ceil(Math.max((double) options.outWidth / 1024f, (double) options.outHeight / 1024f)));
                options.inJustDecodeBounds = false;
                img = BitmapFactory.decodeFile(fileSrc, options);
                new_note_image.setImageBitmap(img);

//                model = new InboxModel(newFormat,img);
//                arr.add(model);
//                adapter.notifyDataSetChanged();
            }
        }
    }
}
