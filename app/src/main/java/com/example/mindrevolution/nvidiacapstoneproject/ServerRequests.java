package com.example.mindrevolution.nvidiacapstoneproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ServerRequests {
    ProgressDialog progressDialog;
    //public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://mindrevolution.comli.com/";

    public ServerRequests(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please wait...");
    }

    public void storeUserDataInBackground(User user, GetUserCallback userCallback) {
        progressDialog.show();
        new StoreUserDataAsyncTask(user, userCallback).execute();
    }

    public void fetchUserDataInBackground(User user, GetUserCallback userCallback) {
        progressDialog.show();
        new fetchUserDataAsyncTask(user, userCallback).execute();
    }

    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void> {
        User user;
        GetUserCallback userCallback;

        public StoreUserDataAsyncTask(User user, GetUserCallback userCallback) {
            this.user = user;
            this.userCallback = userCallback;
        }

        @Override
        protected Void doInBackground(Void... params) {
            Map<String, String> dataToSend = new HashMap<>();
            dataToSend.put("name", user.name);
            dataToSend.put("age", user.age + "");
            dataToSend.put("username", user.username);
            dataToSend.put("password", user.password);

            String encodedStr = getEncodedData(dataToSend);
            BufferedReader reader = null;

            try {

                URL url = new URL(SERVER_ADDRESS + "Register.php");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                writer.write(encodedStr);
                writer.flush();
                StringBuilder sb = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) { //Read till there is something available
                    sb.append(line + "\n");     //Reading and saving line by line - not all at once
                }
                line = sb.toString();           //Saving complete data received in string, you can do it differently

                Log.i("custom_check", "The values received in the store part are as follows:");
                Log.i("custom_check", line);

            } catch (Exception e) {
                e.printStackTrace();
            } finally
            {
                if (reader != null)
                {
                    try
                    {
                        reader.close();     //Closing the
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            userCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }
    public class fetchUserDataAsyncTask extends AsyncTask<Void, Void, User> {
        User user;
        GetUserCallback userCallback;

        public fetchUserDataAsyncTask(User user, GetUserCallback userCallback) {
            this.user = user;
            this.userCallback = userCallback;
        }

        @Override
        protected  User doInBackground(Void... params) {
            Map<String, String> dataToSend = new HashMap<>();
            dataToSend.put("username", user.username);
            dataToSend.put("password", user.password);

            String encodedStr = getEncodedData(dataToSend);
            BufferedReader reader = null;

            User returnedUser = null;
            try {

                URL url = new URL(SERVER_ADDRESS + "FetchUserData.php");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                writer.write(encodedStr);
                writer.flush();
                StringBuilder sb = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) { //Read till there is something available
                    sb.append(line + "\n");     //Reading and saving line by line - not all at once
                }
                line = sb.toString();           //Saving complete data received in string, you can do it differently

                JSONObject jObject = new JSONObject(line);
                if (jObject.length() == 0) {
                    returnedUser = null;
                }
                else
                {
                    String name = jObject.getString("name");
                    int age = jObject.getInt("age");
                    returnedUser = new User(name, age, user.username, user.password);
                }
                Log.i("custom_check", "The values received in the store part are as follows:");
                Log.i("custom_check", line);

            } catch (Exception e) {
                e.printStackTrace();
            } finally
            {
                if (reader != null)
                {
                    try
                    {
                        reader.close();     //Closing the
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            return returnedUser;
        }

        @Override
        protected void onPostExecute(User returnedUser){
            progressDialog.dismiss();
            userCallback.done(returnedUser);
            super.onPostExecute(returnedUser);
        }
    }
        private String getEncodedData(Map<String, String> data) {
            StringBuilder sb = new StringBuilder();
            for (String key : data.keySet()) {
                String value = null;
                try {
                    value = URLEncoder.encode(data.get(key), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                if (sb.length() > 0)
                    sb.append("&");

                sb.append(key + "=" + value);
            }
            return sb.toString();
        }
    }
