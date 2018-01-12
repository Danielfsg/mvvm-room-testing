package pt.dw.room_livedata_viewmodel.additem;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import pt.dw.room_livedata_viewmodel.R;
import pt.dw.room_livedata_viewmodel.db.BorrowModel;

public class AddItemActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Date date;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    private EditText itemEditText, nameEditText;

    private AddBorrowViewModel addBorrowViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        itemEditText = findViewById(R.id.itemName);
        nameEditText = findViewById(R.id.personName);
        Button dateButton = findViewById(R.id.dateButton);

        calendar = Calendar.getInstance();
        addBorrowViewModel = ViewModelProviders.of(this).get(AddBorrowViewModel.class);

        datePickerDialog = new DatePickerDialog(this, AddItemActivity.this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(view -> {
            if (itemEditText.getText() == null || nameEditText.getText() == null || date == null)
                Toast.makeText(this, "Missing fields", Toast.LENGTH_SHORT).show();
            else {
                addBorrowViewModel.addItem(new BorrowModel(
                        itemEditText.getText().toString(),
                        nameEditText.getText().toString(),
                        date
                ));
                finish();
            }
        });

        dateButton.setOnClickListener(view -> datePickerDialog.show());

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date = calendar.getTime();

    }
}
