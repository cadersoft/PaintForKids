package com.cader.trace;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cader.trace.Generic.PaintObject;

public class Animal extends Activity {

	private int exercise;
	private ArrayList<PaintObject> arrayList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animal);

		String title = "";
		exercise = getIntent().getExtras().getInt("exercise");

		if (exercise == 1) {
			arrayList = Generic.Animals;
			title = "Animal";

		} else if (exercise == 2) {
			arrayList = Generic.Vehicle;
			title = "Transport";

		} else if (exercise == 3) {
			arrayList = Generic.Vegitable;
			title = "Vegetable";

		}

		Display display = getWindowManager().getDefaultDisplay();
		Point size = Generic.overrideGetSize(display);
		
		
		for (int i = 0; i < arrayList.size(); i++) {
			final Generic.PaintObject po = arrayList.get(i);
			final int index = i;
			ImageButton btn = (ImageButton) findViewById(po.button);

			btn.setImageResource(po.icon);

			btn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent in = Generic.GotoPaintActivity(po.title,
							Animal.this, po.brushSize, po.penColor, po.bg,
							index, exercise, po.sound);
					startActivity(in);
				}
			});
			/*btn.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View view, MotionEvent event) {

					// ImageButton b = (ImageButton) view;
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						// b.setBackgroundColor(getResources().getColor(R.color.blue));
					} else {
						// b.setBackgroundColor(Color.WHITE);
						// b.setBackgroundColor(R.style.ib_tool);
					}
					return false;
				}
			});*/

			Generic.setSize(size, btn, 0.1664, 0.3423);

			
		}

		TextView tv = (TextView) findViewById(R.id.textView2);

		tv.setText(title);
		Generic.ApplyFont(tv, getAssets());
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		Generic.pauseMedia();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Generic.playMedia();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		Intent in = new Intent(this, Main.class);
		startActivity(in);
	}
}
