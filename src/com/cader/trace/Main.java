package com.cader.trace;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Main extends Activity {

	private ImageView EXE1;
	private ImageView EXE2;
	private ImageView EXE3;
	private ImageView EXE4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		EXE1 = (ImageView) findViewById(R.id.exe1);
		EXE2 = (ImageView) findViewById(R.id.exe2);
		EXE3 = (ImageView) findViewById(R.id.exe3);
		EXE4 = (ImageView) findViewById(R.id.exe4);

		EXE1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent in = new Intent(Main.this, Paint_Activity.class);
				in.putExtra("brushSize", 10);
				in.putExtra("pencolor", 0xff000000);
				in.putExtra("bg", 0);
				startActivity(in);

			}
		});

		// Clicking Animal
		EXE2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent in = new Intent(Main.this, Animal.class);
				in.putExtra("exercise", 1);
				startActivity(in);

			}
		});

		// Clicking Vehicle
		EXE3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent in = new Intent(Main.this, Animal.class);
				in.putExtra("exercise", 2);
				startActivity(in);

			}
		});

		// Clicking Vegetable
		EXE4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent in = new Intent(Main.this, Animal.class);
				in.putExtra("exercise", 3);
				startActivity(in);

			}
		});

		Generic.initMedia(this);
		Generic.playMedia();

		View rec = findViewById(R.id.rectangle1);

		rec.setBackgroundColor(Color.argb(60, 255, 255, 255));
		
		rec.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				EXE1.setImageResource(R.drawable.ic);
				return false;
			}
		});

		Animation an = AnimationUtils.loadAnimation(this, R.anim.btnanim);

		EXE1.startAnimation(an);

		EXE3.startAnimation(an);

		EXE2.startAnimation(an);

		EXE4.startAnimation(an);

		TextView tv = (TextView) findViewById(R.id.textView2);
		tv.setText("Paint for Kids");

		Generic.ApplyFont(tv, getAssets());
		
		Display display = getWindowManager().getDefaultDisplay();
		Point size = Generic.overrideGetSize(display);
		
		Generic.setSize(size, EXE1, 0.1644, 0.3423);
		Generic.setSize(size, EXE2, 0.1644, 0.3423);
		Generic.setSize(size, EXE3, 0.1644, 0.3423);
		Generic.setSize(size, EXE4, 0.1644, 0.3423);
		
	}

	@Override
	protected void onPause() {

		super.onPause();

		Generic.pauseMedia();

	}

	@Override
	protected void onResume() {

		super.onResume();
		Generic.playMedia();
	}

	@Override
	public void onBackPressed() {

		super.onBackPressed();

	}
}
