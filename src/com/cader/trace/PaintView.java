package com.cader.trace;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.View;

public class PaintView extends View {

	public int penColor;
	public int brushSize;
	private Path path;
	private ArrayList<Node> nodes;
	private ArrayList<Node> redos;

	public PaintView(Context context) {
		super(context);
		penColor = 0xFFFFB614;
		brushSize = 80;
		nodes = new ArrayList<Node>();
		redos = new ArrayList<Node>();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			canvas.drawPath(node.Path, node.Paint);
		}
	}

	public void startPath(int x, int y) {

		redos = new ArrayList<Node>();
		
		path = new Path();
		path.moveTo(x, y);

		Node node = new Node();
		node.Paint = getPaint();
		node.Path = path;
		node.Point = new Point(x, y);

		nodes.add(node);

		invalidate();
	}

	public Paint getPaint() {

		Paint mPaint = new Paint();
		mPaint.setDither(true);
		mPaint.setColor(penColor);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(brushSize);

		return mPaint;

	}

	public void addPath(int x, int y) {

		path.lineTo(x, y);
		invalidate();
	}

	public void endPath(int x, int y) {

	}

	public void ClearAll() {
		nodes = new ArrayList<Node>();
		invalidate();
	}

	public void Undo() {

		int len = nodes.size();
		if (len > 0) {
			Node node = nodes.get(len - 1);
			redos.add(node);
			nodes.remove(node);
			invalidate();
		}
	}

	public void Redo() {
		int len = redos.size();
		if (len > 0) {
			Node node = redos.get(len - 1);
			redos.remove(node);
			nodes.add(node);
			invalidate();
		}
	}

	public class Node {

		public Point Point;
		public Path Path;
		public Paint Paint;
	}

}
