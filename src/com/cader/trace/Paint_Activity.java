package com.cader.trace;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.cader.trace.ColorPickerView.OnColorChangedListener;
import com.cader.trace.Generic.PaintObject;


public class Paint_Activity extends Activity implements OnTouchListener, SensorEventListener {

	private int topBg;
	private int index;
	private int exercise;
	private ImageView iv;
	private String title;
	private TextView titleText;
	private int cnt = 0;
	private RelativeLayout rl;
	private RelativeLayout starRl;
	private MyCountDownTimer countDownTimer;
	private long startTime = 2 * 1000;
	private long interval = 1 * 1000;
	private int sound;
	private Paint_Activity main_context;
	private Button prevbtn;
	private Button nextbtn;
	private PaintView paintView;
	private long lastUpdate;
	private SensorManager sensorManager;
	private Point size;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_paint);
		
		Display display = getWindowManager().getDefaultDisplay();
		size = Generic.overrideGetSize(display);
		
		rl = (RelativeLayout) findViewById(R.id.paint_rl);
		
		paintView = new PaintView(this);
		paintView.setOnTouchListener(this);
		rl.addView(paintView);


		Button colorbtn =getImageButton(R.id.colorbtn);
		Button penbtn = getImageButton(R.id.penbtn);
		getImageButton(R.id.deletebtn);
		getImageButton(R.id.savebtn);
		getImageButton(R.id.menubtn);
		Button subbtn = getImageButton(R.id.submenubtn);
		Button undobtn = getImageButton(R.id.undobtn);
		Button redobtn = getImageButton(R.id.redobtn);
		

		nextbtn = getImageButton(R.id.nextbtn);
		prevbtn = getImageButton(R.id.prevbtn);

		paintView.penColor = getIntent().getExtras().getInt("pencolor");
		paintView.brushSize = getIntent().getExtras().getInt("brushSize");
		topBg = getIntent().getExtras().getInt("bg");
		exercise = getIntent().getExtras().getInt("exercise");
		index = getIntent().getExtras().getInt("index");
		title = getIntent().getExtras().getString("title");
		sound = getIntent().getExtras().getInt("sound");

		
		countDownTimer = new MyCountDownTimer(startTime, interval);

		starRl = new RelativeLayout(this);
		rl.addView(starRl);
		
		//
		
		if (topBg != 0) {
			iv = new ImageView(this);
			iv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
			iv.setBackgroundResource(topBg);

			((LinearLayout) penbtn.getParent()).removeView(penbtn);
			((LinearLayout) colorbtn.getParent()).removeView(colorbtn);
			((LinearLayout) undobtn.getParent()).removeView(undobtn);
			((LinearLayout) redobtn.getParent()).removeView(redobtn);

			rl.addView(iv);
			
			CheckNavigation(index);
		} else {

			((LinearLayout) nextbtn.getParent()).removeView(subbtn);
			((LinearLayout) nextbtn.getParent()).removeView(nextbtn);
			((LinearLayout) prevbtn.getParent()).removeView(prevbtn);
		}

		titleText = (TextView) findViewById(R.id.titleText);
		Generic.ApplyFont(titleText, getAssets());

		titleText.setText(title);
		
		main_context = this;
		Generic.playSound(main_context, sound);
		
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		lastUpdate = System.currentTimeMillis();
		
		
		
	}

	public ArrayList<Generic.PaintObject> getExercise(int id) {
		switch (id) {
		case 1:
			return Generic.Animals;
		case 2:
			return Generic.Vehicle;
		case 3:
			return Generic.Vegitable;
		}
		return null;
	}

	private Button getImageButton(final int id) {

		Button ib = (Button) findViewById(id);
		
		Drawable dr = ib.getCompoundDrawables()[0];
		dr.setBounds(0, 0, 60, 60);
		ib.setCompoundDrawables(dr, null, null, null);
		
		Generic.setSize(size, ib, 0.1390, 0.0842);
		
		
		ib.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				switch (id) {
				case R.id.penbtn:
					Dialog pendialog = new Dialog(Paint_Activity.this);
					pendialog.setCanceledOnTouchOutside(true);
					pendialog.setContentView(R.layout.penlayout);
					pendialog.setTitle("Pen thickness");

					final SeekBar sk = (SeekBar) pendialog
							.findViewById(R.id.seekBar1);
					sk.setProgress(paintView.brushSize);
					sk.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

						@Override
						public void onStopTrackingTouch(SeekBar seekBar) {
		
						}

						@Override
						public void onStartTrackingTouch(SeekBar seekBar) {
		
						}

						@Override
						public void onProgressChanged(SeekBar seekBar,
								int progress, boolean fromUser) {
							paintView.brushSize = progress;
						}
					});

					pendialog.show();

					break;

				case R.id.colorbtn:

					final Dialog dialog = new Dialog(Paint_Activity.this);
					dialog.setCanceledOnTouchOutside(true);
					dialog.setContentView(R.layout.colorpickerlayout);
					dialog.setTitle("Pick the Color");

					final ColorPickerView cpv = (ColorPickerView) dialog
							.findViewById(R.id.color_picker_view);
					cpv.setColor(paintView.penColor);

					final View b1 = (View) dialog.findViewById(R.id.view1);
					b1.setBackgroundColor(paintView.penColor);

					cpv.setOnColorChangedListener(new OnColorChangedListener() {

						@Override
						public void onColorChanged(int color) {
							paintView.penColor = cpv.getColor();
							b1.setBackgroundColor(paintView.penColor);

						}
					});

					Button dialogButton = (Button) dialog
							.findViewById(R.id.dialogButtonOK);
					dialogButton.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {

							paintView.penColor = cpv.getColor();

							dialog.dismiss();
						}
					});

					View b2 = (View) dialog.findViewById(R.id.view2);
					b2.setBackgroundColor(cpv.getColor());

					dialog.show();

					break;
				case R.id.deletebtn:
					paintView.ClearAll();
					break;

				case R.id.undobtn:
					paintView.Undo();
					break;
				case R.id.redobtn:
					paintView.Redo();
					break;
				case R.id.nextbtn:
					ArrayList<PaintObject> pos = getExercise(exercise);
					if (pos.size() != index + 1) {
						index = index + 1;
						redrawPaint(pos.get(index));
						CheckNavigation(index);
					}
					break;
				case R.id.prevbtn:
					pos = getExercise(exercise);
					if (index != 0) {
						index = index - 1;
						redrawPaint(pos.get(index));
						CheckNavigation(index);
						
					}
					break;
				case R.id.menubtn:
					Intent in = new Intent(Paint_Activity.this, Main.class);
					startActivity(in);
					break;
				case R.id.submenubtn:
					Intent in1 = new Intent(Paint_Activity.this, Animal.class);
					in1.putExtra("exercise", exercise);
					startActivity(in1);
					break;
					
				case R.id.savebtn:
					new SaveImage(main_context, rl);
					break;
				}
				
			
			}

		});
		return ib;
	}

	private void redrawPaint(PaintObject po)
	{
		paintView.penColor = po.penColor;
		paintView.brushSize = po.brushSize;
		titleText.setText(po.title);
		iv.setBackgroundResource(po.bg);
		Generic.playSound(main_context, po.sound);
		paintView.ClearAll();
	}
	
	private void CheckNavigation(int i) {
		ArrayList<PaintObject> pos = getExercise(exercise);
		if (pos.size()-1 == i) {
			nextbtn.setAlpha(0.5f);

		} else {
			nextbtn.setAlpha(1f);
		}

		if (i == 0) {
			
			prevbtn.setAlpha(0.5f);
		} else {
			
			prevbtn.setAlpha(1f);
		}
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		int x = (int) event.getX();
		int y = (int) event.getY();
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {

			countDownTimer.cancel();
			((PaintView)v).startPath(x,y);
			

		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {

			ImageView rl1 = (ImageView) getStars();

			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			params.setMargins(x, y, 0, 0);
			rl1.setLayoutParams(params);
			starRl.addView(rl1);

			((PaintView)v).addPath(x,y);
			
			
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			countDownTimer.start();
			((PaintView)v).endPath(x,y);
		}
		return true;
	}

	private View getStars() {
		final ImageView iv = new ImageView(this);
		cnt = (cnt + 1) % 3;
		switch (cnt) {
		case 0:
			iv.setBackgroundResource(R.drawable.star_blue);
			break;
		case 1:
			iv.setBackgroundResource(R.drawable.star_pink);
			break;
		case 2:
			iv.setBackgroundResource(R.drawable.star_yellow);
			break;
		}

		Animation an = AnimationUtils.loadAnimation(this, R.anim.disappear);
		an.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub

				iv.setVisibility(View.INVISIBLE);
			}
		});
		iv.startAnimation(an);

		return iv;

	}

	public void saveImage(Bitmap b, int count) throws Exception {

		String path = Environment.getExternalStorageDirectory().toString();
		OutputStream fOut = null;

		String s = "File";
		String alphaAndDigits = s.replaceAll("[^a-zA-Z0-9]+", "_");

		String fileName = alphaAndDigits + "_Paint_" + count;
		File file = new File(path, fileName + ".jpg");

		fOut = new FileOutputStream(file);
		b.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
		fOut.flush();
		fOut.close();

		MediaStore.Images.Media.insertImage(getContentResolver(), b,
				file.getName(), file.getName());
	}

	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			//getAccelerometer(event);
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		
		Generic.playMedia();
	}


	@Override
	protected void onPause() {

		super.onPause();
		sensorManager.unregisterListener(this);
		
		Generic.pauseMedia();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}
	
	private void getAccelerometer(SensorEvent event) {
		float[] values = event.values;
		
		float x = values[0];
		float y = values[1];
		float z = values[2];

		float accelationSquareRoot = (x * x + y * y + z * z)
				/ (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
		long actualTime = System.currentTimeMillis();
		if (accelationSquareRoot >= 2) //
		{
			if (actualTime - lastUpdate < 500) {
				return;
			}
			lastUpdate = actualTime;
			Toast.makeText(this, "Device was shuffed to delete paint.", Toast.LENGTH_SHORT)
					.show();
			
			paintView.ClearAll();
		}
	}

	
	public class MyCountDownTimer extends CountDownTimer {
		public MyCountDownTimer(long startTime, long interval) {
			super(startTime, interval);
		}

		@Override
		public void onFinish() {
			if (starRl.getChildCount() != 0)
				starRl.removeAllViews();
		}

		@Override
		public void onTick(long millisUntilFinished) {
			Log.d("Cader",
					"Timer - " + String.valueOf(millisUntilFinished / 1000));
		}

	}


	
}
