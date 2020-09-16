package com.disker.tablayoutsample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		// 코드를 통한 추가 방식
		TabLayout tabLayout = findViewById(R.id.tabLayout1);

		TabLayout.Tab tab1 = tabLayout.newTab();
		tab1.setText("tab1");

		TabLayout.Tab tab2 = tabLayout.newTab();
		tab2.setText("tab1");

		tabLayout.addTab(tab1);
		tabLayout.addTab(tab2);
	}
}