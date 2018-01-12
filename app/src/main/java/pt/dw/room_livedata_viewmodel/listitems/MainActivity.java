package pt.dw.room_livedata_viewmodel.listitems;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import pt.dw.room_livedata_viewmodel.R;
import pt.dw.room_livedata_viewmodel.additem.AddItemActivity;
import pt.dw.room_livedata_viewmodel.db.BorrowModel;
import pt.dw.room_livedata_viewmodel.viewitem.ViewItemActivity;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnClickListener {

    private BorrowedListViewModel viewModel;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> startActivity(
                new Intent(MainActivity.this, AddItemActivity.class)));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<>(), this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerViewAdapter);

        viewModel = ViewModelProviders.of(this).get(BorrowedListViewModel.class);

        viewModel.getItemAndPersonList().observe(MainActivity.this,
                itemAndPeople -> recyclerViewAdapter.addItems(itemAndPeople));
    }

    @Override
    public boolean onLongClick(View v) {
        BorrowModel borrowModel = (BorrowModel) v.getTag();
        viewModel.deleteItem(borrowModel);
        return true;
    }

    @Override
    public void onClick(View v) {
        BorrowModel borrowModel = (BorrowModel) v.getTag();
        Intent intent = new Intent(MainActivity.this, ViewItemActivity.class);
        intent.putExtra("ID",String.valueOf(borrowModel.id));
        startActivity(intent);
    }
}
