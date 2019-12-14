package com.invocker.invocker_math.Adapter;

import android.content.Context;

import com.google.gson.Gson;
import com.invocker.invocker_math.Model.ListUser;
import com.invocker.invocker_math.Model.UserScope;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class AdapterGsonStorage {
    private Context context;
    private int scope;
    private String name;
    private String json;
    private String object;

    public AdapterGsonStorage(Context context) {
        this.context = context;
    }

    public AdapterGsonStorage(Context context, int scope, String name) {
        this.context = context;
        this.scope = scope;
        this.name = name;
    }

    public String FILENAME = "listScope";

    public void objectToString() {
        List<UserScope> lisUser = new ArrayList<>();
        lisUser.add(new UserScope(this.name, scope));
        ListUser list = new ListUser("listUser", lisUser);
        Gson gson = new Gson();
        json = gson.toJson(list);
        saveData(json);
    }

    //save data
    private void saveData(String name) {

        try {

            // Mở một luồng ghi file.
            FileOutputStream out = context.openFileOutput(FILENAME, MODE_PRIVATE);
            // Ghi dữ liệu.
            out.write(name.getBytes());
            out.close();
           // Toast.makeText(context, "File saved!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
           // Toast.makeText(context, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //doc data
    public String readData() {
        try {
            // Mở một luồng đọc file.
            FileInputStream in = context.openFileInput(FILENAME);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String s = null;
            while ((s = br.readLine()) != null) {
                sb.append(s).append("\n");
            }
            this.object = sb.toString();
        } catch (Exception e) {
          //  Toast.makeText(context, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return this.object;
    }

    public ListUser stringtoOjbect(String str) {
        Gson gson = new Gson();
        ListUser founderList = gson.fromJson(str, ListUser.class);
        return founderList;
    }


}
