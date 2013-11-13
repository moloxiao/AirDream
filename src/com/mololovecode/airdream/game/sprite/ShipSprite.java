package com.mololovecode.airdream.game.sprite;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import com.hifreshday.android.pge.engine.options.EngineOptions;
import com.hifreshday.android.pge.entity.IEntity;
import com.hifreshday.android.pge.entity.shape.Shape;
import com.hifreshday.android.pge.entity.shape.sprite.Sprite;
import com.hifreshday.android.pge.view.res.IBitmapRes;
import com.mololovecode.airdream.game.GameScene;
import com.mololovecode.airdream.game.res.BitmapRes;

public class ShipSprite extends Sprite {

	public static final int X = 195;
	public static final int Y = 740;
	public static final int WIDTH = 90;
	public static final int HEIGHT = 39;
	
	private int speed = 720;
	
	private boolean needUpdatePosition = false;
	
	private boolean acceptTouch = false;
	private int moveGoal;
	
	private int minMoveGoal;
	private int maxMoveGoal;
	
	public ShipSprite(IBitmapRes bitmapRes, int pX, int pY, int width,
			int height) {
		super(bitmapRes, pX, pY, width, height);
		minMoveGoal = getRect().width()/2;
		maxMoveGoal = (int)(800*EngineOptions.getScreenScaleX()) - getRect().width()/2;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			acceptTouch = true;
			updateMoveGoal((int)(event.getX()));
			return true;
		}else if(acceptTouch && event.getAction() == MotionEvent.ACTION_MOVE) {
			updateMoveGoal((int)(event.getX()));
			return true;
		}else if(acceptTouch && 
				(event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) ) {
			acceptTouch = false;
			needUpdatePosition = false;
			return true;
		}
		acceptTouch = false;
		return false;
	}
	
	private void updateMoveGoal(int touchX) {
		moveGoal = touchX;
		needUpdatePosition = true;
	}
	

	@Override
	protected void onUpdateSelf(float secondsElapsed) {
		if(needUpdatePosition) {
			updateMoveDirection(secondsElapsed);
		}
		fire((int)(secondsElapsed*1000));
		updateCollosion();
	}
	
	private void updateCollosion() {
		if( children != null && children.size()>0 ) {
			for(IEntity entity : children) {
				if(((GameScene)getParent()).isCollision((Shape)entity)){
					Log.i("MOLO_DEBUG", "ship fire collision enemy ... ");
					entity.setNeedRemove(true);
				}
			}
		}
		if(((GameScene)getParent()).isCollision(this)) {
			Log.i("MOLO_DEBUG", "ship collision enemy ... ");
		}
	}

	private int fireElapsed;
	private static final int FIRE_SPLIT_TIME = 100;
	
	private void fire(int millisecondsElapsed) {
		fireElapsed += millisecondsElapsed;
		if(fireElapsed>FIRE_SPLIT_TIME) {
			fireElapsed = fireElapsed%FIRE_SPLIT_TIME;
			attachFire();
		}
	}

	private void attachFire() {
		attachChild(new FireSprite(bitmapRes,
				(int)((getLeftFireInitPositionX() - EngineOptions.getOffsetX())/EngineOptions.getScreenScaleX()),
				(int)((getRect().top - EngineOptions.getOffsetY())/EngineOptions.getScreenScaleY()),
				FireSprite.WIDTH,
				FireSprite.HEIGHT));
		
		attachChild(new FireSprite(bitmapRes,
				(int)((getRightFireInitPositionX() - EngineOptions.getOffsetX())/EngineOptions.getScreenScaleX()),
				(int)((getRect().top - EngineOptions.getOffsetY())/EngineOptions.getScreenScaleY()),
				FireSprite.WIDTH,
				FireSprite.HEIGHT));
		
	}

	private void updateMoveDirection(float secondsElapsed) {
		if(moveGoal < minMoveGoal) {
			moveGoal = minMoveGoal;
		}else if(moveGoal > maxMoveGoal) {
			moveGoal = maxMoveGoal;
		}
		int centerX = getRect().left + getRect().width()/2;
		int moved = (int)(secondsElapsed*speed*EngineOptions.getScreenScaleX());
		
		if(centerX == moveGoal) {
			needUpdatePosition = false;
		}else {
			if(moved < Math.abs(moveGoal - centerX)) {
				if(moveGoal - centerX > 0) {
				}else {
					moved = -1*moved;
				}
			}else {
				moved = moveGoal - centerX;
			}
			rect = new Rect(
					rect.left + moved,
					rect.top,
					rect.right + moved,
					rect.bottom);
		}
	}

	@Override
	public void onDrawSelf(Canvas canvas) {
		canvas.drawBitmap(bitmapRes.getBitmap(BitmapRes.KEY_SHIP), null, getRect(), null);
	}
	
	public int getLeftFireInitPositionX() {
		return getRect().left + (int)(17*EngineOptions.getScreenScaleX());
	}
	
	public int getRightFireInitPositionX() {
		return getRect().left + (int)(65*EngineOptions.getScreenScaleX());
	}
}
