package com.example.aja;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
import java.util.HashMap;
import java.util.Map;

public class Assignment extends AppCompatActivity {
    EditText edt_name, edt_sub, edt_des;
    String str_name, str_sub, str_des, strFileName, strFilePath;
    Button upload, btn_assi;
    String BaseURL = "";
    TextView txtfilename;
    String UPLOAD_URL;

    //Image request code
    private int PICK_PDF_REQUEST = 1;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;

    //Uri to store the image uri
    private Uri filePath;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            strFileName = getFileName(data.getData());
            strFilePath = FilePath.getPath(Assignment.this, filePath);
            Log.e("FILENAME: ", strFilePath);
            /* strFilePath = getFilePath(data.getData());*/
        }
    }

   /* private String getFilePath(Uri data) {
        Cursor cursor = getContentResolver().query(data, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);

        cursor.close();

        cursor = getContentResolver().query(
                MediaStore.Files.FileColumns.DATA.EXTERNAL_CONTENT_URI,
                null, MediaStore.Files.FileColumns.MIME_TYPE + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA));
        cursor.close();

        return path;
    }*/

    private String getFileName(Uri data) {
        String result = null;
        if (data.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(data, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = data.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        ActionBar newbar = getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Assignment Submission");

        requestStoragePermission();

        BaseURL = getResources().getString(R.string.Base_URL) + "stu_assignment.php";
        UPLOAD_URL = getResources().getString(R.string.Base_URL) + "upload_assignment.php";

        edt_name = findViewById(R.id.name);
        edt_sub = findViewById(R.id.subject);
        edt_des = findViewById(R.id.des);
        btn_assi = findViewById(R.id.assi_btn);
        txtfilename = (TextView) findViewById(R.id.txtFile);
        upload = (Button) findViewById(R.id.upload);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PICK_PDF_REQUEST);
            }
        });

        btn_assi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_name = edt_name.getText().toString().trim();
                str_sub = edt_sub.getText().toString().trim();
                str_des = edt_des.getText().toString().trim();

                Log.e("FileName:", strFileName);

                if (str_name.equals("")) {
                    Toast.makeText(Assignment.this, "Name is required", Toast.LENGTH_SHORT).show();
                } else if (str_sub.equals("")) {
                    Toast.makeText(Assignment.this, "Subject is required", Toast.LENGTH_SHORT).show();
                } else if (str_des.equals("")) {
                    Toast.makeText(Assignment.this, "Description no requied", Toast.LENGTH_SHORT).show();
                } else {

                    new assignmentTask().execute();
                    new uploadMultipartFile().execute();
                }
            }
        });
    }



   /* private void uploadMultipartFile() {

        String path = FilePath.getPath(Assignment.this, filePath);

        if (path == null) {

            Toast.makeText(this, "Please move your .pdf file to internal storage and retry", Toast.LENGTH_LONG).show();
        } else {
            //Uploading code
            try {
                String uploadId = UUID.randomUUID().toString();

                //Creating a multi part request
                new MultipartUploadRequest(this, uploadId, UPLOAD_URL)
                        .addFileToUpload(path, "pdf") //Adding file
                        .addParameter("name", strFileName) //Adding text parameter to the request
                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload

                Log.e("UPLOADING","Started");

            } catch (Exception exc) {
                Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }*/

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    private class assignmentTask extends AsyncTask<String, String, JSONObject> {


        @Override
        protected JSONObject doInBackground(String... strings) {
            JSONParser parser = new JSONParser();
            Map<String, String> hashmap = new HashMap<>();
            hashmap.put("name", str_name);
            hashmap.put("subj", str_sub);
            hashmap.put("descri", str_des);
            hashmap.put("file_name", strFileName);
            hashmap.put("file_name_url", "" + getResources().getString(R.string.Base_URL) + "uploads/" + strFileName);


            JSONObject jObj = parser.makeHttpRequest(BaseURL, "POST", hashmap);

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

    private class uploadMultipartFile extends AsyncTask<Void, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            showProgressResult(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Void... voids) {
            return uploadFile();
        }

        private String uploadFile() {

            String responseString = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(UPLOAD_URL);

            try {
                AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                        new AndroidMultiPartEntity.ProgressListener() {

                            @Override
                            public void transferred(long num) {
                                publishProgress((int) ((num / (float) 0) * 100));

                            }
                        });

                File sourceFile = new File(strFilePath);

// Adding file data to http body
                entity.addPart("pdf", new FileBody(sourceFile));

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

            Log.e("RESPONSE URL", responseString);

            return responseString;
        }
    }

    private void showProgressResult(String s) {
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
}
