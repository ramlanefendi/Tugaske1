package com.example.tugas1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CheckBox PulsaCheckBox, TokenCheckBox, VoucherCheckBox;
    private RadioGroup membershipRadioGroup;
    private RadioButton memberRadioButton, nonMemberRadioButton;
    private TextView totalTextView, receiptTextView;
    private Button checkoutButton;
    private double totalHarga = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PulsaCheckBox = findViewById(R.id.pulsaCheckBox);
        TokenCheckBox = findViewById(R.id.voucherCheckBox);
        VoucherCheckBox = findViewById(R.id.tokenCheckBox);
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

        PulsaCheckBox.setOnCheckedChangeListener(checkBoxListener);
        TokenCheckBox.setOnCheckedChangeListener(checkBoxListener);
        VoucherCheckBox.setOnCheckedChangeListener(checkBoxListener);

        membershipRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            calculateTotal();
        });
    }

    private void showReceipt() {
        StringBuilder receiptText = new StringBuilder("Detail Pembelian:\n");

        if (PulsaCheckBox.isChecked()) {
            receiptText.append("Pulsa(Rp. 5000)\n");
        }
        if (PulsaCheckBox.isChecked()) {
            receiptText.append("Token(Rp. 10000)\n");
        }
        if (VoucherCheckBox.isChecked()) {
            receiptText.append("Voucher(Rp. 15000)\n");
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

        if (PulsaCheckBox.isChecked()) {
            totalHarga += 5000;
        }
        if (TokenCheckBox.isChecked()) {
            totalHarga += 10000;
        }
        if (VoucherCheckBox.isChecked()) {
            totalHarga += 15000;
        }

        // Menerapkan diskon jika status membership adalah member
        if (memberRadioButton.isChecked()) {
            totalHarga *= 0.95; // Diskon 5%
        }

        totalTextView.setText("Total Harga: Rp. " + totalHarga);
    }
}
