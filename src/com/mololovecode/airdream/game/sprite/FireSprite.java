package com.mololovecode.airdream.game.sprite;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import com.hifreshday.android.pge.engine.options.EngineOptions;
import com.hifreshday.android.pge.entity.shape.sprite.Sprite;
import com.hifreshday.android.pge.view.res.IBitmapRes;
import com.mololovecode.airdream.game.domain.Fire;

public class FireSprite extends Sprite {
	
	public static final int WIDTH = 4;
	public static final int HEIGHT = 10;
	
	public static final int TO_POSITION_UP = 0;
	public static final int TO_POSITION_DOWN = 1;
	
	private static Paint firePaint;
	
	private int toPosition;
	private Fire fire;
	
	public FireSprite(IBitmapRes bitmapRes, int pX, int pY, int width,
			int height) {
		super(bitmapRes, pX, pY, width, height);
		toPosition = TO_POSITION_UP;
		fire = Fire.getDefaultShipFire();
	}
	
	@Override
	protected void onUpdateSelf(float secondsElapsed) {
		updatePosition(secondsElapsed);
	}

	private void updatePosition(float secondsElapsed) {
		int moved = (int)(secondsElapsed*fire.getSpeed()*EngineOptions.getScreenScaleY());
		if(toPosition == FireSprite.TO_POSITION_UP) {
			rect = new Rect(
					rect.left,
					rect.top - moved,
					rect.right,
					rect.bottom - moved);
		}else if(toPosition == FireSprite.TO_POSITION_DOWN) {
			rect = new Rect(
					rect.left,
					rect.top + moved,
					rect.right,
					rect.bottom + moved);
		}
		
		if(getRect().bottom < 0) {
			getParent().detachChild(this);
		}else if(getRect().top > EngineOptions.getRealScreenHeight()) {
			getParent().detachChild(this);
		}
	}
	
	@Override
	public void onDrawSelf(Canvas canvas) {
		canvas.drawRect(getRect(), FireSprite.getFirePaint());
	}
	
	public int getToPosition() {
		return toPosition;
	}

	public void setToPosition(int toPosition) {
		this.toPosition = toPosition;
	}

	public Fire getFire() {
		return fire;
	}

	public void setFire(Fire fire) {
		this.fire = fire;
	}

	public static Paint getFirePaint() {
		if(firePaint == null) {
			firePaint = new Paint();
			firePaint.setColor(Color.YELLOW);
		}
		return firePaint;
	}

}
