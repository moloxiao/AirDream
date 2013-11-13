package com.mololovecode.airdream.game.sprite.enemy;

import android.graphics.Canvas;
import android.graphics.Rect;
import com.hifreshday.android.pge.engine.options.EngineOptions;
import com.hifreshday.android.pge.entity.shape.sprite.Sprite;
import com.hifreshday.android.pge.view.res.IBitmapRes;
import com.mololovecode.airdream.game.domain.Plane;
import com.mololovecode.airdream.game.res.BitmapRes;

public class EnemyPlaneFooSprite extends Sprite {

	public static final int WIDTH = 20;
	public static final int HEIGHT = 40;
	
	private Plane plane;
	
	public EnemyPlaneFooSprite(IBitmapRes bitmapRes, int pX, int pY, int width,
			int height) {
		super(bitmapRes, pX, pY, width, height);
		plane = Plane.getDefaultEnemyPlane();
	}
	
	@Override
	protected void onUpdateSelf(float secondsElapsed) {
		updatePosition(secondsElapsed);
	}

	
	private void updatePosition(float secondsElapsed) {
		int moved = (int)(secondsElapsed*plane.getSpeed()*EngineOptions.getScreenScaleY());
		rect = new Rect(
				rect.left,
				rect.top + moved,
				rect.right,
				rect.bottom + moved);
		
		if(getRect().top > EngineOptions.getRealScreenHeight()) {
			getParent().detachChild(this);
		}
	}

	@Override
	public void onDrawSelf(Canvas canvas) {
		canvas.drawBitmap(
				bitmapRes.getBitmap(BitmapRes.KEY_ENEMY_POOL_PLANE), null, getRect(), null);
	}
}
