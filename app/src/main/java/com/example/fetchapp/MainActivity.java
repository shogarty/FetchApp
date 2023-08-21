package com.example.fetchapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView itemListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemListView = findViewById((R.id.itemListView));
        getJItems();
    }

    private void getJItems()
    {
        Call<List<JItems>> call = RetrofitClient.getInstance().getMyApi().getJItems();
        call.enqueue(new Callback<List<JItems>>()
        {
            @Override
            public void onResponse(Call<List<JItems>> call, Response<List<JItems>> response)
            {
                List<JItems> myList = response.body();
                for (int i = 0; i < myList.size(); i++)
                {
                    if (myList.get(i).getName().isEmpty() || myList.get(i).getName() == "")
                    {
                        myList.remove(i);
                        i--;
                    }
                }

                myList.sort(Comparator.comparing(JItems::getListId).thenComparing(JItems::getName));

                String [] items = new String [myList.size()];
                for (int i = 0; i < myList.size(); i++)
                {
                    int id = myList.get(i).getId();
                    int listId = myList.get(i).getListId();
                    String name = myList.get(i).getName();
                    String entry = new String ("Name: " + name + " | List Id: " + listId + " | Item Id: " +id);
                    items[i] = entry;

                }

                itemListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, items));
            }
            @Override
            public void onFailure(Call<List<JItems>> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }

}