package com.cader.trace;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class Rectangle extends View {
	Drawable shape;

	public Rectangle(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		// shape = context.getResources().getDrawable(R.drawable.rect);
		this.setBackgroundColor(Color.argb(30, 255, 255, 255));
		}

	public Rectangle(Context context, AttributeSet attrs) {

		super(context, attrs);
	}

	public Rectangle(Context context, AttributeSet attrs, int defStyle) {

		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// shape.setBounds(0, 0, 10, 10);
		// shape.draw(canvas);
	}

}
