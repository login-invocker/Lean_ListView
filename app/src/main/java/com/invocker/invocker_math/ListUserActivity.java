package com.invocker.invocker_math;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.invocker.invocker_math.Adapter.AdapterGsonStorage;
import com.invocker.invocker_math.Adapter.ListUserAdapter;
import com.invocker.invocker_math.Model.ListUser;
import com.invocker.invocker_math.Model.UserScope;

import java.util.List;

public class ListUserActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<UserScope> listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        recyclerView = findViewById(R.id.recycler_list_user);
        recyclerView.setHasFixedSize(true);
        addData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListUserAdapter adapter = new ListUserAdapter(getApplicationContext(), listUser);

        recyclerView.setAdapter(adapter);

    }

    private void addData() {

        AdapterGsonStorage newStorage = new AdapterGsonStorage(this);
        ListUser list = newStorage.stringtoOjbect(newStorage.readData());
        listUser=list.getUserScopes();
    }
}
