package com.example.tugas1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CheckBox rotiCheckBox, susuCheckBox, telurCheckBox;
    private RadioGroup membershipRadioGroup;
    private RadioButton memberRadioButton, nonMemberRadioButton;
    private TextView totalTextView, receiptTextView;
    private Button checkoutButton;
    private double totalHarga = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rotiCheckBox = findViewById(R.id.rotiCheckBox);
        susuCheckBox = findViewById(R.id.susuCheckBox);
        telurCheckBox = findViewById(R.id.telurCheckBox);
        membershipRadioGroup = findViewById(R.id.membershipRadioGroup);
        memberRadioButton = findViewById(R.id.memberRadioButton);
        nonMemberRadioButton = findViewById(R.id.nonMemberRadioButton);
        totalTextView = findViewById(R.id.totalTextView);
        checkoutButton = findViewById(R.id.checkoutButton);
        receiptTextView = findViewById(R.id.receiptTextView);

        checkoutButton.setOnClickListener(v -> showReceipt());

        CompoundButton.OnCheckedChangeListener checkBoxListener = (buttonView, isChecked) -> {
            calculateTotal();
        };

        rotiCheckBox.setOnCheckedChangeListener(checkBoxListener);
        susuCheckBox.setOnCheckedChangeListener(checkBoxListener);
        telurCheckBox.setOnCheckedChangeListener(checkBoxListener);

        membershipRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            calculateTotal();
        });
    }

    private void showReceipt() {
        StringBuilder receiptText = new StringBuilder("Detail Pembelian:\n");

        if (rotiCheckBox.isChecked()) {
            receiptText.append("Roti (Rp. 5000)\n");
        }
        if (susuCheckBox.isChecked()) {
            receiptText.append("Susu (Rp. 10000)\n");
        }
        if (telurCheckBox.isChecked()) {
            receiptText.append("Telur (Rp. 15000)\n");
        }

        receiptText.append("\n");

        // Menambahkan status membership dan diskon jika member
        String membershipStatus = memberRadioButton.isChecked() ? "Member (Diskon 5%)" : "Non Member";
        receiptText.append("Membership: ").append(membershipStatus).append("\n");

        // Menambahkan total harga
        receiptText.append("\nTotal Harga: Rp. ").append(totalHarga);

        receiptTextView.setText(receiptText.toString());
    }

    private void calculateTotal() {
        totalHarga = 0;

        if (rotiCheckBox.isChecked()) {
            totalHarga += 5000;
        }
        if (susuCheckBox.isChecked()) {
            totalHarga += 10000;
        }
        if (telurCheckBox.isChecked()) {
            totalHarga += 15000;
        }

        // Menerapkan diskon jika status membership adalah member
        if (memberRadioButton.isChecked()) {
            totalHarga *= 0.95; // Diskon 5%
        }

        totalTextView.setText("Total Harga: Rp. " + totalHarga);
    }
}
