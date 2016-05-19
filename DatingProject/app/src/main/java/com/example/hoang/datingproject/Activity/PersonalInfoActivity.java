package com.example.hoang.datingproject.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoang.datingproject.R;
import com.example.hoang.datingproject.Utilities.Const;
import com.example.hoang.datingproject.Utilities.FontManager;
import com.example.hoang.datingproject.Utilities.RegisterUserClass;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class PersonalInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView exit_button, save_button;
    private EditText editInfo;
    private Button bt1, bt2, bt3;
    private ImageView avatar;
    private String gender = "";
    private Bitmap img = null;
    Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        Firebase.setAndroidContext(PersonalInfoActivity.this);
        ref = new Firebase(Const.FIRE_BASE_URL);
        Typeface font = FontManager.getTypeface(PersonalInfoActivity.this, FontManager.FONTAWESOME);

        editInfo = (EditText) findViewById(R.id.editInfo);
        save_button = (TextView) findViewById(R.id.save);
        exit_button = (TextView) findViewById(R.id.exit_button);
        exit_button.setTypeface(font);
        editInfo.setText(R.string.personal_info_item13);

        avatar = (ImageView) findViewById(R.id.avatar);

        bt1 = (Button) findViewById(R.id.personal_btn1);
        bt2 = (Button) findViewById(R.id.personal_btn2);
        bt3 = (Button) findViewById(R.id.personal_btn3);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        avatar.setOnClickListener(this);
        exit_button.setOnClickListener(this);
        save_button.setOnClickListener(this);
        editInfo.setOnClickListener(this);

        bt1.setSelected(true);
        bt1.setPressed(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
                finish();
                break;
            case R.id.avatar:
                getImageFromGallery();
                break;
            case R.id.save:
                String nickname = editInfo.getText().toString();
                Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.avatar);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                icon.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                byte[] b = baos.toByteArray();
                String profileImage = Base64.encodeToString(b, Base64.DEFAULT);
                createUser(nickname,profileImage);
                break;
        }
    }

    private void getImageFromGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, Const.PROFILE_CHOOSE);
    }

    public void createUser(final String nickname, String profileImage){
        class CreateUser extends AsyncTask<String, Void, String> {

            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = new ProgressDialog(PersonalInfoActivity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String result = jsonObject.getString("result");
                    if(result.equalsIgnoreCase("1")){
                        Log.d(Const.LOG_TAG, "success");
                        String password = "123";
                        registerUser(nickname, password);

                        Toast.makeText(PersonalInfoActivity.this, "update profile success", Toast.LENGTH_SHORT).show();

                    }else{
                        Log.d(Const.LOG_TAG, "failed");
                        Toast.makeText(PersonalInfoActivity.this, "update profile failed", Toast.LENGTH_LONG).show();
                    }
                    progressDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String,String> data = new HashMap<>();
                data.put("nickname",params[0]);
                data.put("profileImage",params[1]);

                RegisterUserClass ruc = new RegisterUserClass();

                String result = ruc.sendPostRequest(Const.USER_CREATE_URL,data);

                Log.d(Const.LOG_TAG, result + "");
                return result;
            }
        }
        CreateUser getData = new CreateUser();
        getData.execute(nickname, profileImage);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Const.PROFILE_CHOOSE && resultCode == RESULT_OK) {
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

                BitmapDrawable ob = new BitmapDrawable(getResources(), img);
                avatar.setImageDrawable(ob);
            }
        }
    }

    public static void setDefault(String key, String value, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, "123");
    }

    public void registerUser(final String nickname, final String pass) {
        final String email = nickname + "@gmail.com";
        ref.createUser(email, pass, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                System.out.println("Successfully created user account with uid: " + result.get("uid"));
                setDefault("password", pass, PersonalInfoActivity.this);
                setDefault("email", email, PersonalInfoActivity.this);
                updateUser(nickname, result.get("uid").toString());
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
            }
        });
    }

    public void updateUser(final String nickname, String uid){
        class UpdateUser extends AsyncTask<String, Void, String> {

            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
//                progressDialog = new ProgressDialog(PersonalInfoActivity.this,
//                        R.style.AppTheme_Dark_Dialog);
//                progressDialog.setIndeterminate(true);
//                progressDialog.setMessage("Loading...");
//                progressDialog.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String result = jsonObject.getString("result");
                    if(result.equalsIgnoreCase("1")){
                        String userId = jsonObject.getString("userId");
                        setDefault("id", userId, PersonalInfoActivity.this);
                        Log.d(Const.LOG_TAG, "update success");
                        Toast.makeText(PersonalInfoActivity.this, "update profile id success", Toast.LENGTH_SHORT).show();

                    }else{
                        Log.d(Const.LOG_TAG, "failed update id");
                        Toast.makeText(PersonalInfoActivity.this, "update profile id failed", Toast.LENGTH_LONG).show();
                    }
//                    progressDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String,String> data = new HashMap<>();
                data.put("nickname",params[0]);
                data.put("uid", params[1]);

                RegisterUserClass ruc = new RegisterUserClass();

                String result = ruc.sendPostRequest(Const.USER_UPDATE_ID_URL + nickname,data);

                Log.d(Const.LOG_TAG, result + "");
                return result;
            }
        }
        UpdateUser getData = new UpdateUser();
        getData.execute(nickname, uid);
    }
}
