package com.example.hoang.datingproject.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoang.datingproject.Adapter.FeedsAdapter;
import com.example.hoang.datingproject.Adapter.InboxAdapter;
import com.example.hoang.datingproject.Fragment.EmotionFragment;
import com.example.hoang.datingproject.Fragment.FeedsFragment;
import com.example.hoang.datingproject.Model.FeedModel;
import com.example.hoang.datingproject.Model.ImageItem;
import com.example.hoang.datingproject.Model.InboxModel;
import com.example.hoang.datingproject.R;
import com.example.hoang.datingproject.Utilities.Const;
import com.example.hoang.datingproject.Utilities.FontManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MessagesActivity extends AppCompatActivity implements View.OnClickListener, EmotionFragment.ListSelectionListener{

    private EditText message_edittext;
    private TextView back_button, ban_button;
    private Button message_button;
    private Button message_camera, message_emotion;
    private RecyclerView recyclerView;
    private InboxAdapter adapter;
    private ArrayList<InboxModel> arr = new ArrayList<InboxModel>();

    private FragmentManager mFragmentManager;
    private FrameLayout mEmotionsFrameLayout;
    private EmotionFragment emotionFragment = new EmotionFragment();
    private static int count;
    private FragmentTransaction fragmentTransaction;
    private Bitmap img = null;
    private ArrayList<Integer> imgId = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        getControls();
    }

    private void getControls() {
        message_edittext = (EditText) findViewById(R.id.message_edittext);
        message_camera = (Button) findViewById(R.id.message_camera);
        back_button = (TextView) findViewById(R.id.back_button);
        ban_button = (TextView) findViewById(R.id.ban_button);
        message_emotion = (Button) findViewById(R.id.message_emotion);
        message_button = (Button) findViewById(R.id.message_button);
        Typeface font = FontManager.getTypeface(MessagesActivity.this, FontManager.FONTAWESOME);
        message_camera.setTypeface(font);
        back_button.setTypeface(font);
        ban_button.setTypeface(font);
        message_emotion.setTypeface(font);

        message_camera.setOnClickListener(this);
        message_emotion.setOnClickListener(this);
        message_button.setOnClickListener(this);
        back_button.setOnClickListener(this);

        registerForContextMenu(message_camera);

        count = 0;

        mEmotionsFrameLayout = (FrameLayout) findViewById(R.id.emotion_fragment_container);
        // Get a reference to the FragmentManager
        mFragmentManager = getSupportFragmentManager();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        arr.clear();
        arr.addAll(getData());
        adapter = new InboxAdapter(MessagesActivity.this, arr);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MessagesActivity.this, LinearLayoutManager.VERTICAL, false);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),4);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        scrollToTop();
//        recyclerView.setHasFixedSize(true);
    }

    private ArrayList<InboxModel> getData() {
        ArrayList<InboxModel> arrayList = new ArrayList<InboxModel>();
        InboxModel model = new InboxModel("16:21", "こんばんは(^-^)/", 1);
        InboxModel model1 = new InboxModel("21:21", "ありがとう", 2);
        Bitmap icon = BitmapFactory.decodeResource(MessagesActivity.this.getResources(),
                R.drawable.avatar);
        InboxModel model2 = new InboxModel("01:05", icon);
        arrayList.add(model);
        arrayList.add(model1);
        arrayList.add(model2);

        return arrayList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_button:
                if (message_edittext.getText().toString().equals("")){
                    Toast.makeText(MessagesActivity.this, "empty messages", Toast.LENGTH_SHORT).show();
                }else {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
                    Date c = Calendar.getInstance().getTime();
                    String newFormat = sdf.format(c);
                    InboxModel model = new InboxModel(newFormat, message_edittext.getText().toString(), 10);
                    message_edittext.setText("");
                    arr.add(model);
                    adapter.notifyDataSetChanged();
                    scrollToTop();
                }
                break;
            case R.id.back_button:
                finish();
                break;
            case R.id.message_emotion:
                break;
        }
    }

    private void addEmotionFragment() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mEmotionsFrameLayout.setLayoutParams(layoutParams);
//                    mQuotesFrameLayout.setBackgroundResource(R.color.gray);
        fragmentTransaction = mFragmentManager
                .beginTransaction();
        fragmentTransaction.add(R.id.emotion_fragment_container,
                emotionFragment, "emotion");
        fragmentTransaction.addToBackStack(null);

        // Commit the FragmentTransaction
        fragmentTransaction.commit();
    }

    private void removeEmotionFragment() {
        getSupportFragmentManager().beginTransaction().
                remove(getSupportFragmentManager().findFragmentByTag("emotion")).commit();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        mEmotionsFrameLayout.setLayoutParams(layoutParams);
    }

    @Override
    public void onBackPressed() {
        if(count % 2 !=0){
            removeEmotionFragment();
            count++;
        }else {
            finish();
        }
    }

    @Override
    public void onListSelection(int index, ArrayList<ImageItem> arr) {

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
        InboxModel model;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
        Date c = Calendar.getInstance().getTime();
        String newFormat = sdf.format(c);
        if (requestCode == Const.REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (data != null) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");

                model = new InboxModel(newFormat,imageBitmap);
                arr.add(model);
                adapter.notifyDataSetChanged();
                scrollToTop();
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

                model = new InboxModel(newFormat,img);
                arr.add(model);
                adapter.notifyDataSetChanged();
                scrollToTop();
            }
        }
    }

    private void scrollToTop() {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
            }
        });
    }

}
