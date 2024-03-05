package com.example.tugas1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CheckBox rotiCheckBox, susuCheckBox, telurCheckBox;
    private EditText jumlahUangEditText;
    private TextView totalTextView, kembalianTextView;
    private Button checkoutButton;
    private double totalHarga = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rotiCheckBox = findViewById(R.id.rotiCheckBox);
        susuCheckBox = findViewById(R.id.susuCheckBox);
        telurCheckBox = findViewById(R.id.telurCheckBox);
        jumlahUangEditText = findViewById(R.id.jumlahUangEditText);
        totalTextView = findViewById(R.id.totalTextView);
        kembalianTextView = findViewById(R.id.kembalianTextView);
        checkoutButton = findViewById(R.id.checkoutButton);

        checkoutButton.setOnClickListener(v -> checkout());

        CompoundButton.OnCheckedChangeListener checkBoxListener = (buttonView, isChecked) -> {
            if (buttonView.getId() == R.id.rotiCheckBox && isChecked) {
                totalHarga += 5000;
            } else if (buttonView.getId() == R.id.rotiCheckBox && !isChecked) {
                totalHarga -= 5000;
            } else if (buttonView.getId() == R.id.susuCheckBox && isChecked) {
                totalHarga += 10000;
            } else if (buttonView.getId() == R.id.susuCheckBox && !isChecked) {
                totalHarga -= 10000;
            } else if (buttonView.getId() == R.id.telurCheckBox && isChecked) {
                totalHarga += 15000;
            } else if (buttonView.getId() == R.id.telurCheckBox && !isChecked) {
                totalHarga -= 15000;
            }
            updateTotal();
        };

        rotiCheckBox.setOnCheckedChangeListener(checkBoxListener);
        susuCheckBox.setOnCheckedChangeListener(checkBoxListener);
        telurCheckBox.setOnCheckedChangeListener(checkBoxListener);
    }

    private void checkout() {
        try {
            double jumlahUang = Double.parseDouble(jumlahUangEditText.getText().toString());
            double kembalian = jumlahUang - totalHarga;
            if (kembalian >= 0) {
                kembalianTextView.setText("Kembalian: Rp. " + kembalian);
            } else {
                Toast.makeText(this, "Jumlah uang tidak mencukupi", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Masukkan jumlah uang dengan benar", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateTotal() {
        totalTextView.setText("Total Harga: Rp. " + totalHarga);
    }
}
