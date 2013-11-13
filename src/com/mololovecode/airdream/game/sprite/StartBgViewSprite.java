package com.mololovecode.airdream.game.sprite;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.hifreshday.android.pge.engine.options.EngineOptions;
import com.hifreshday.android.pge.entity.shape.sprite.Sprite;
import com.hifreshday.android.pge.view.res.IBitmapRes;

public class StartBgViewSprite extends Sprite {

	public static final int X = 0;
	public static final int Y = 0;
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	
	private int starsX[];
	private int starsY[];
	private Paint starsC[];
	private int numStars = 25;
	private int speed = 60;
	
	public StartBgViewSprite(IBitmapRes bitmapRes, int pX, int pY, int width,
			int height) {
		super(bitmapRes, pX, pY, width, height);
		initStars();
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	private void initStars() {
		starsX = new int[numStars];
		starsY = new int[numStars];
		starsC = new Paint[numStars];
		for (int i = 0; i < numStars; i++) {
			starsX[i] = (int) (((Math.random() * WIDTH - 1) + 1)*EngineOptions.getScreenScaleX());
			starsY[i] = (int) (((Math.random() * HEIGHT - 1) + 1)*EngineOptions.getScreenScaleY());
			starsC[i] = newColor();
		}
		
	}
	
	private Paint newColor() {
		int[] rgb;
		int t;
		rgb = new int[3];
		for (int i = 0; i < 3; i++)
			rgb[i] = 0;
		t = (int) (Math.random() * 3);
		rgb[t] = (int) (Math.random() * 128 + 1) + 127;
		Paint p = new Paint();
		p.setARGB(255, rgb[0], rgb[1], rgb[2]);
		return p; 
	}

	@Override
	public void onUpdate(float secondsElapsed) {
		moveStars(secondsElapsed);
	}

	private void moveStars(float secondsElapsed) {
		int moved = (int)(secondsElapsed*speed*EngineOptions.getScreenScaleY());
		for (int i = 0; i < numStars; i++) {
			if (starsY[i] > EngineOptions.getRealScreenHeight() - moved) {
				starsY[i] = 0;
				starsX[i] = (int) (((Math.random() * WIDTH - 1) + 1)*EngineOptions.getScreenScaleX());
				starsC[i] = newColor();
			} else {
				starsY[i] += moved;
			}
		}
	}

	@Override
	public void onDrawSelf(Canvas canvas) {
		for (int a = 0; a < numStars; a++) {
			canvas.drawRect(
					starsX[a], 
					starsY[a], 
					starsX[a] + 2*EngineOptions.getScreenScaleX(), 
					starsY[a] + 2*EngineOptions.getScreenScaleY(),
					starsC[a]);
		}
	}
}
