package com.disker.promotionnews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if(getWindow() != null)
			Util.setOnSystemUiVisibilityChangeListener(getWindow().getDecorView());

		Button button = findViewById(R.id.button);
		button.setOnClickListener(this);

		Button button2 = findViewById(R.id.button2);
		button2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.button:
				Intent intent = new Intent(MainActivity.this, PromotionNewsV2Activity.class);
				intent.putExtra("forced", "false");
				startActivity(intent);
				break;
			case R.id.button2:
				Intent intent2 = new Intent(MainActivity.this, PromotionNewsV2Activity.class);
				intent2.putExtra("forced", "true");
				startActivity(intent2);
				break;
			default:
				break;
		}
	}
}