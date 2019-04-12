package com.kobe.binderstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kobe.binderstudy.section1.Section1Activity;
import com.kobe.binderstudy.section2.Section2Activity;
import com.kobe.binderstudy.section3.Section3Activity;
import com.kobe.binderstudy.section4.Section4Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnSection1;
    private Button mBtnSection2;
    private Button mBtnSection3;
    private Button mBtnSection4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnSection1 = (Button) findViewById(R.id.btn_section1);
        mBtnSection2 = (Button) findViewById(R.id.btn_section2);
        mBtnSection3 = (Button) findViewById(R.id.btn_section3);
        mBtnSection4 = (Button) findViewById(R.id.btn_section4);
        mBtnSection1.setOnClickListener(this);
        mBtnSection2.setOnClickListener(this);
        mBtnSection3.setOnClickListener(this);
        mBtnSection4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_section1: {
                Intent intent = new Intent(this, Section1Activity.class);
                this.startActivity(intent);
            }
            break;
            case R.id.btn_section2: {
                Intent intent = new Intent(this, Section2Activity.class);
                this.startActivity(intent);
            }
            break;
            case R.id.btn_section3: {
                Intent intent = new Intent(this, Section3Activity.class);
                this.startActivity(intent);
            }
            break;
            case R.id.btn_section4: {
                Intent intent = new Intent(this, Section4Activity.class);
                this.startActivity(intent);
            }
            break;
        }
    }
}
