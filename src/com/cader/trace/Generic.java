package com.cader.trace;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

public class Generic {
	
	public static ArrayList<PaintObject> Vegitable = new ArrayList<PaintObject>()
	{{
		add(PaintObjectMethod("Tomato", 70,0xffD4110B, R.drawable.tomato_bg, R.id.monkeybtn, R.drawable.tomato_ic, R.raw.tomato));
		add(PaintObjectMethod("Brinjal", 70,0xff5d0b6b,R.drawable.brinjal_bg, R.id.cowbtn, R.drawable.brinjal_ic, R.raw.brinjal));
		add(PaintObjectMethod("Carrot", 70,0xffC74C0C, R.drawable.carrot_bg, R.id.girafeebtn, R.drawable.carrot_ic, R.raw.carrot));
		add(PaintObjectMethod("Onion", 70,0xffBC8D57, R.drawable.onion_bg, R.id.elephantbtn, R.drawable.onion_ic, R.raw.onion));
		add(PaintObjectMethod("Cucumber", 70,0xff548A08,R.drawable.cucumber_bg, R.id.lionbtn, R.drawable.cucumber_ic, R.raw.cucumber));
		add(PaintObjectMethod("Potato", 70,0xff936037,R.drawable.potato_bg, R.id.camelbtn, R.drawable.potato_ic, R.raw.potato));
		add(PaintObjectMethod("Capsicum", 70,0xffFFCC00,R.drawable.peper_bg, R.id.horsebtn, R.drawable.peper_ic, R.raw.capsicon));
		add(PaintObjectMethod("Beetroot", 70,0xff9A1A43,R.drawable.beetroot_bg, R.id.kangaroobtn, R.drawable.beetroot_ic, R.raw.beetroot));
	}};
	
	public static ArrayList<PaintObject> Vehicle = new ArrayList<PaintObject>()
	{{
		add(PaintObjectMethod("Car", 70,0xffCD2B27, R.drawable.car_bg, R.id.monkeybtn, R.drawable.car_ic, R.raw.car));
		add(PaintObjectMethod("Bus", 70,0xff61BCE6, R.drawable.bus_bg, R.id.girafeebtn, R.drawable.bus_ic, R.raw.bus));
		add(PaintObjectMethod("Excavator", 70,0xffFFCC00, R.drawable.rc_bg, R.id.elephantbtn, R.drawable.rc_ic, R.raw.excavator));
		add(PaintObjectMethod("School Bus", 70,0xffFFCC00,R.drawable.schoolbus_bg, R.id.lionbtn, R.drawable.scoolbus_ic, R.raw.schoolbus));
		add(PaintObjectMethod("Ambulance", 70,0xff99CCCC,R.drawable.ambulance_bg, R.id.camelbtn, R.drawable.ambulance_ic, R.raw.ambulance));
		add(PaintObjectMethod("Truck", 70,0xffE06600,R.drawable.lorry_bg, R.id.cowbtn, R.drawable.lorry_ic, R.raw.truck));
		add(PaintObjectMethod("Train", 70,0xff0099FF,R.drawable.train_bg, R.id.horsebtn, R.drawable.train_ic , R.raw.train));
		add(PaintObjectMethod("Police Car", 70,0xff003366,R.drawable.policecar_bg, R.id.kangaroobtn, R.drawable.policecar_ic, R.raw.policecar));
	}};
	
	public static ArrayList<PaintObject> Animals = new ArrayList<PaintObject>()
	{{
		add(PaintObjectMethod("Monkey", 70,0xff42210B,R.drawable.monkeybg, R.id.monkeybtn, R.drawable.monkey_ic, R.raw.monkey));
		add(PaintObjectMethod("Giraffe", 70,0xffFFB614,R.drawable.giraffebg, R.id.girafeebtn, R.drawable.giraffe_ic, R.raw.giraffe));
		add(PaintObjectMethod("Elephant", 70,0xffA1C5D2,R.drawable.elephantbg, R.id.elephantbtn, R.drawable.elephant_ic, R.raw.elephant));
		add(PaintObjectMethod("Lion", 70,0xfff18126,R.drawable.lionbg, R.id.lionbtn, R.drawable.lion_ic, R.raw.lion));
		add(PaintObjectMethod("Camel", 70,0xffffcc33,R.drawable.camelbg, R.id.camelbtn, R.drawable.camel_ic, R.raw.camel));
		add(PaintObjectMethod("Cow", 70,0xff793e14,R.drawable.cowbg, R.id.cowbtn, R.drawable.cow_ic, R.raw.cow));
		add(PaintObjectMethod("Horse", 70,0xff996600,R.drawable.horsebg, R.id.horsebtn, R.drawable.horse_ic, R.raw.horse));
		add(PaintObjectMethod("Kangaroo", 70,0xffffcc00,R.drawable.kangaroobg, R.id.kangaroobtn, R.drawable.kangaroo_ic, R.raw.kangaroo));
		//add(PaintObjectMethod("Dog", 70,0xfff18126,R.drawable.dogbg, R.id.dogbtn));
	}};
	
	/* For Full Version
	public static ArrayList<PaintObject> Animals = new ArrayList<PaintObject>()
	{{
		add(PaintObjectMethod("Monkey", 70,0xff42210B,R.drawable.monkeybg, R.id.monkeybtn, R.drawable.monkey_ic));
		add(PaintObjectMethod("Giraffe", 70,0xffFFB614,R.drawable.giraffebg, R.id.girafeebtn, R.drawable.giraffe_ic));
		add(PaintObjectMethod("Elephant", 70,0xffA1C5D2,R.drawable.elephantbg, R.id.elephantbtn, R.drawable.elephant_ic));
		add(PaintObjectMethod("Lion", 70,0xfff18126,R.drawable.lionbg, R.id.lionbtn, R.drawable.lion_ic));
		add(PaintObjectMethod("Camel", 70,0xffffcc33,R.drawable.camelbg, R.id.camelbtn, R.drawable.camel_ic));
		add(PaintObjectMethod("Cow", 70,0xff793e14,R.drawable.cowbg, R.id.cowbtn, R.drawable.cow_ic));
		add(PaintObjectMethod("Horse", 70,0xff996600,R.drawable.horsebg, R.id.horsebtn, R.drawable.horse_ic));
		add(PaintObjectMethod("Kangaroo", 70,0xffffcc00,R.drawable.kangaroobg, R.id.kangaroobtn, R.drawable.kangaroo_ic));
		//add(PaintObjectMethod("Dog", 70,0xfff18126,R.drawable.dogbg, R.id.dogbtn));
	}};
	*/
	public static PaintObject PaintObjectMethod(String title, int brushSize, int penColor, int bg, int button, int icon, int sound)
	{
		PaintObject  po = new PaintObject();
		po.brushSize = brushSize;
		po.penColor = penColor;
		po.bg = bg;
		po.button = button;
		po.title = title;
		po.icon = icon;
		po.sound = sound;
		return po;
	}
	
	public static Intent GotoPaintActivity(String title, Context context, int brushSize, int penColor, int bg, int i, int exercise, int sound) {
		Intent in = new Intent(context, Paint_Activity.class);
		in.putExtra("brushSize", brushSize);
		in.putExtra("pencolor", penColor);
		in.putExtra("bg", bg);
		in.putExtra("index", i);
		in.putExtra("exercise", exercise);
		in.putExtra("title", title);
		in.putExtra("sound", sound);
		return in;
	}
	
	public static class PaintObject
	{
		int sound;
		int icon;
		String title;
		int brushSize;
		int penColor;
		int bg;
		int button;
		
	}
	
	public static MediaPlayer mp;
	
	public static void initMedia(Context context)
	{
		mp = MediaPlayer.create(context, R.raw.music);
		mp.setLooping(true);
		mp.setVolume(100,100);
	}
	
	public static void playMedia()
	{
		if(mp != null)
			mp.start();
	}
	
	public static void pauseMedia()
	{
		if(mp != null)
			mp.pause();
	}

	public static void playSound(Context context, int resid)
	{
		MediaPlayer p = MediaPlayer.create(context, R.raw.paint);
		if(p != null)
			p.start();
		
		if(resid != 0)
		{
			final Context c = context;
			final int s = resid;
			
			p.setOnCompletionListener(new OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer mp) {
					// TODO Auto-generated method stub
					MediaPlayer p1 = MediaPlayer.create(c, s);	
					p1.start();
				}
			});
		}
	}
	
	public static void ApplyFont(TextView titleText, AssetManager assetManager) {
		// TODO Auto-generated method stub
		String fontPath = "fonts/Edson_Comics_Bold.ttf";

		

		// Loading Font Face
		Typeface tf = Typeface.createFromAsset(assetManager, fontPath);

		// Applying font
		titleText.setTypeface(tf);
	}

	public static void trace(String s) {
		
		Log.d("Cader", s);
	}

	public static Point overrideGetSize(Display display) {
		
		Point outSize = new Point();
		 
		
		try {

			Class pointClass = Class.forName("android.graphics.Point");
			Method newGetSize = Display.class.getMethod("getSize",
					new Class[] { pointClass });

			newGetSize.invoke(display, outSize);
		} catch (NoSuchMethodException ex) {

			outSize.x = display.getWidth();
			outSize.y = display.getHeight();
		} catch (ClassNotFoundException e) {

			outSize.x = display.getWidth();
			outSize.y = display.getHeight();
		} catch (IllegalArgumentException e) {

			outSize.x = display.getWidth();
			outSize.y = display.getHeight();
		} catch (IllegalAccessException e) {

			outSize.x = display.getWidth();
			outSize.y = display.getHeight();
		} catch (InvocationTargetException e) {

			outSize.x = display.getWidth();
			outSize.y = display.getHeight();
		}
		
		return outSize;
	}

	public static void setSize(Point size, View v, double w, double h) {

		v.getLayoutParams().width = (int) (size.x * w);
		v.getLayoutParams().height = (int) (size.y * h);
	}
}
