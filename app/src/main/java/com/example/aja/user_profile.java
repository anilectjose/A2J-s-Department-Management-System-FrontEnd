package com.example.aja;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class user_profile extends AppCompatActivity {
    TextView ename, efname, eyear, eperc, email, eno, ereg,ye,att;
    String RegUrl, BaseUrl;
    Button logout;
    ImageView show, upload;
    ImageView edit;

    private static final int pic_code = 1000;
    String strImage = "";
    String strImagePath;
    String UploadURL;
    String IMAGE_URL = "";
    String URL;
    ProgressBar pgBar;
    JSONParser parser = new JSONParser();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == pic_code && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            strImagePath = cursor.getString(columnIndex);
            Log.e("path", strImagePath);
            cursor.close();

            show.setImageURI(selectedImage);
            strImage = getImageName(data.getData());
            Toast.makeText(this, strImage, Toast.LENGTH_SHORT).show();
            new Imagetodb().execute();

            new Uploaduserimage().execute();
        }
    }

    private String getImageName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    SharedPreferences shp;
    String str_login_id;
    String str_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ActionBar newbar = getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Profile");

        BaseUrl = getResources().getString(R.string.Base_URL);
        RegUrl = BaseUrl + "profile.php";//of apiUploadURL = getResources().getString(R.string.Base_URL)+"Uploaduserimage.php";
        URL = getResources().getString(R.string.Base_URL);
        IMAGE_URL = getResources().getString(R.string.Base_URL) + "profile_img.php";
        UploadURL = getResources().getString(R.string.Base_URL) + "upload_image.php";

        shp = getSharedPreferences("login_daa", MODE_PRIVATE);
        str_login_id = shp.getString("login_id", "");
        str_role = shp.getString("user_role", "");

        Log.e("LOGIN ID", str_login_id);


        ename = findViewById(R.id.nm);
        efname = findViewById(R.id.fullname);
        eyear = findViewById(R.id.yea);
        ye = findViewById(R.id.tv2);
        att = findViewById(R.id.tv4);
        eperc = findViewById(R.id.percent);
        email = findViewById(R.id.mail);
        eno = findViewById(R.id.no);
        ereg = findViewById(R.id.reg);
        logout = findViewById(R.id.lgout);
        upload = findViewById(R.id.upload);
        show = findViewById(R.id.imageView2);
        edit=findViewById(R.id.editall);
        pgBar = (ProgressBar) findViewById(R.id.progress);

        if(str_role.equals("teacher"))
        {
            ye.setText("Mentor");
            att.setText(" ");
        }

        //Makes the image view in round in shape.
       /* Bitmap bitmap=BitmapFactory.decodeResource(getResources(),);
        RoundedBitmapDrawable roundedBitmapDrawable= RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);
        show.setImageDrawable(roundedBitmapDrawable);*/

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, pic_code);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Logging out", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = shp.edit();
                editor.clear();
                editor.commit();
                Intent j = new Intent(getApplicationContext(), splash_screen.class);
                startActivity(j);
                finish();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        new GetDetails().execute();

    }

    private class uploadImg extends AsyncTask<String, String, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);

            try {
                int success_value = jsonObject.getInt("success");

                if (success_value == 1) {
                    String msg = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    String msg = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(jsonObject);
        }
    }


    /* private void showProgressResult(String s)
     {
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setMessage("Registration Success!").setTitle("Success")
                 .setCancelable(false)
                 .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int id) {

                         finish();
                     }
                 });
         AlertDialog alert = builder.create();
         alert.show();

     }
 */
    private class Imagetodb extends AsyncTask<String, String, JSONObject> {


        @Override
        protected JSONObject doInBackground(String... strings) {
            JSONParser parser = new JSONParser();
            Map<String, String> hashmap = new HashMap<>();
            hashmap.put("file_name", strImage);
            hashmap.put("stu_id", str_login_id);
            hashmap.put("file_name_url", "" + getResources().getString(R.string.Base_URL) + "user_photo/" + strImage);


            JSONObject jObj = parser.makeHttpRequest(IMAGE_URL, "POST", hashmap);

            return jObj;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try {
                int success_value = jsonObject.getInt("success");
                if (success_value == 1) {
                    String msg = jsonObject.getString("message");

                    Log.e("RESPONSE: ", msg);

                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                    finish();

                } else {
                    String msg = jsonObject.getString("message");

                    Log.e("RESPONSE ERROR: ", msg);


                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                Log.e("SUCCESS: ", jsonObject.getString("message").toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }


    private class Uploaduserimage extends AsyncTask<Void, Integer, String> {

        @Override
        protected void onPreExecute() {
// setting progress bar to zero
            pgBar.setProgress(0);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
// Making progress bar visible
            pgBar.setVisibility(View.VISIBLE);

// updating progress bar value
            pgBar.setProgress(progress[0]);

        }

        @Override
        protected String doInBackground(Void... params) {
            return uploadFile();
        }

        @SuppressWarnings("deprecation")
        private String uploadFile() {
            String responseString = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(UploadURL);

            try {
                AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                        new AndroidMultiPartEntity.ProgressListener() {

                            @Override
                            public void transferred(long num) {
                                publishProgress((int) ((num / (float) 0) * 100));

                            }
                        });

                File sourceFile = new File(strImagePath);

// Adding file data to http body
                entity.addPart("image", new FileBody(sourceFile));

                httppost.setEntity(entity);

// Making server call
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity r_entity = response.getEntity();

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
// Server response
                    responseString = EntityUtils.toString(r_entity);
                } else {
                    responseString = "Error occurred! Http Status Code: "
                            + statusCode;
                }

            } catch (ClientProtocolException e) {
                responseString = e.toString();
            } catch (IOException e) {
                responseString = e.toString();
            }
            Log.e("RESPONSESTRING",responseString);

            return responseString;


        }

        @Override
        protected void onPostExecute(String result) {

            finish();

        }

    }

    private class GetDetails extends AsyncTask<String, String, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try {
                int success = jsonObject.getInt("success");


                if (success == 1) {
                    String event_title = jsonObject.getString("nme");
                    String usrn = jsonObject.getString("name");
                    String years = jsonObject.getString("year");
                    String emai = jsonObject.getString("email");
                    String per = jsonObject.getString("attendance");
                    String no = jsonObject.getString("mobile");
                    String reg = jsonObject.getString("register_no");
                    String image = jsonObject.getString("image_url");


                    ename.setText(event_title);
                    efname.setText(usrn);
                    eyear.setText(years);
                    email.setText(emai);
                    eno.setText(no);
                    ereg.setText(reg);
                   /* show.setText(image);*/ //u can't call directly to imageview .
                    eperc.setText(per);
                    new DownLoadImageTask(show).execute(image);

                } else {
                    Log.e("ERROR", "API ERROR");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            JSONParser parser = new JSONParser();
            Map<String, String> hashmap = new HashMap<>();
            hashmap.put("stud_id", str_login_id); //is it stands for role_id?

            JSONObject jsonObject = parser.makeHttpRequest(RegUrl, "POST", hashmap);

            return jsonObject;
        }
    }

    private class DownLoadImageTask extends AsyncTask<String,Void, Bitmap>
    {
        ImageView imageView;

        public DownLoadImageTask(ImageView show_img) {
            this.imageView = show_img;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urlOfImage = strings[0];
            Bitmap logo = null;

            try
            {
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }

            catch(Exception e)
            {
                // Catch the download exception
                e.printStackTrace();
            }

            return logo;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap)
        {

            imageView.setImageBitmap(bitmap);
        }

    }
}

