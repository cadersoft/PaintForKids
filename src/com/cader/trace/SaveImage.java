package com.cader.trace;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SaveImage {
	private Context TheThis;
	private String NameOfFolder = "/PaintForKids";
	private String NameOfFile = "Image_";

	public SaveImage(Context context, View view) {
		TheThis = context;
		Bitmap ImageToSave = getBitmap(view);
		String file_path = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + NameOfFolder;
		String CurrentDateAndTime = getCurrentDateAndTime();
		File dir = new File(file_path);

		if (!dir.exists()) {
			dir.mkdirs();
		}

		File file = new File(dir, NameOfFile + CurrentDateAndTime + ".jpg");

		try {
			FileOutputStream fOut = new FileOutputStream(file);
			ImageToSave.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
			fOut.flush();
			fOut.close();
			MakeSureFileWasCreatedThenMakeAvabile(file);
			AbleToSave();

		} catch (FileNotFoundException e) {
			UnableToSave();
		} catch (IOException e) {
			UnableToSave();
		}

	}

	private void MakeSureFileWasCreatedThenMakeAvabile(File file) {
		MediaScannerConnection.scanFile(TheThis,
				new String[] { file.toString() }, null,
				new MediaScannerConnection.OnScanCompletedListener() {
					public void onScanCompleted(String path, Uri uri) {
						Log.e("ExternalStorage", "Scanned " + path + ":");
						Log.e("ExternalStorage", "-> uri=" + uri);

					}
				});

	}

	public Bitmap getBitmap(View view) {
		Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(),
				view.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(returnedBitmap);
		Drawable bgDrawable = view.getBackground();
		if (bgDrawable != null)
			bgDrawable.draw(canvas);
		else
			canvas.drawColor(Color.WHITE);
		view.draw(canvas);
		return returnedBitmap;
	}
	
	@SuppressLint("SimpleDateFormat")
	private String getCurrentDateAndTime() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String formattedDate = df.format(c.getTime());
		return formattedDate;
	}

	private void UnableToSave() {
		Toast.makeText(TheThis, "Picture cannot save to gallery.", Toast.LENGTH_SHORT)
				.show();

	}

	private void AbleToSave() {
		Toast.makeText(TheThis, "Picture saved in your gallery.", Toast.LENGTH_SHORT)
				.show();

	}
}