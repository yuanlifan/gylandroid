package com.example.admin.gyl.personcenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ylfcf.gyl.R;

public class AttestActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEt_company_id;
    private EditText mEt_company_name;
    private EditText mEt_company_number1;
    private EditText mEt_company_number2;
    private EditText mEt_bank_name;
    private EditText mEt_company_number3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attest);
        initView();

    }

    private void initView() {
        TextView topTitleTV = (TextView) findViewById(R.id.common_topbar_title);
        LinearLayout topLeftLayout = (LinearLayout) findViewById(R.id.common_topbar_left_layout);
        topLeftLayout.setOnClickListener(this);
        ImageView topLeftBtn = (ImageView) findViewById(R.id.common_topbar_left_btn);

        mEt_company_id = findViewById(R.id.et_company_id);
        mEt_company_name = findViewById(R.id.et_company_name);
        mEt_company_number1 = findViewById(R.id.et_company_number1);
        mEt_company_number2 = findViewById(R.id.et_company_number2);
        mEt_bank_name = findViewById(R.id.et_bank_name);
        mEt_company_number3 = findViewById(R.id.et_company_number3);
        TextView btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

        topLeftBtn.setImageResource(R.drawable.back_img);
        topTitleTV.setText("企业概况");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.common_topbar_left_layout:
                finish();
                break;

            case R.id.btn_next:
                //下一步
                Intent intent = new Intent(AttestActivity.this, CompanyPhotoActivity.class);
                startActivity(intent);
                break;
        }

    }

}
