package pt.dw.room_livedata_viewmodel.viewitem;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.text.DateFormat;

import pt.dw.room_livedata_viewmodel.R;

public class ViewItemActivity extends AppCompatActivity {

    private TextView itemTextView, nameTextView, dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");

        itemTextView = findViewById(R.id.itemTextView);
        nameTextView = findViewById(R.id.nameTextView);
        dateTextView = findViewById(R.id.dateTextView);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> finish());


        ViewItemViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(this.getApplication(), id)).get(ViewItemViewModel.class);

        viewModel.getItem().observe(ViewItemActivity.this, borrowModel -> {
            DateFormat format = DateFormat.getDateInstance();
            itemTextView.setText(borrowModel != null ? borrowModel.getItemName() : "");
            nameTextView.setText(borrowModel != null ? borrowModel.getPersonName() : "");
            dateTextView.setText(format.format(borrowModel != null ? borrowModel.getBorrowDate() : ""));
        });



    }

}
