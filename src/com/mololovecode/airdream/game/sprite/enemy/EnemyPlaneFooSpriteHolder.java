package com.mololovecode.airdream.game.sprite.enemy;

import android.graphics.Canvas;
import android.util.Log;

import com.hifreshday.android.pge.engine.options.EngineOptions;
import com.hifreshday.android.pge.entity.IEntity;
import com.hifreshday.android.pge.entity.shape.Shape;
import com.hifreshday.android.pge.entity.shape.sprite.Sprite;
import com.hifreshday.android.pge.view.res.IBitmapRes;

public class EnemyPlaneFooSpriteHolder extends Sprite {


	public EnemyPlaneFooSpriteHolder(IBitmapRes bitmapRes, int pX, int pY,
			int width, int height) {
		super(bitmapRes, pX, pY, width, height);
	}
	private int babyElapsed;
	private static final int BABY_SPLIT_TIME = 1000;
	@Override
	protected void onUpdateSelf(float secondsElapsed) {
		babyElapsed += (int)(secondsElapsed*1000);
		if(babyElapsed>BABY_SPLIT_TIME) {
			babyElapsed = babyElapsed%BABY_SPLIT_TIME;
			attachBaby();
		}
	}
	private void attachBaby() {
		EnemyPlaneFooSprite enemyPlaneFooSprite = new EnemyPlaneFooSprite(bitmapRes, 
				getRandomInitX(), -30,
				EnemyPlaneFooSprite.WIDTH, EnemyPlaneFooSprite.HEIGHT);
		attachChild(enemyPlaneFooSprite);
	}
	
	private int getRandomInitX() {
		return 50 + (int)(Math.random()*(EngineOptions.PORTPRAIT_SCREEN_WIDTH-100));
	}
	
	@Override
	public void onDrawSelf(Canvas canvas) {
	}
	
	public boolean isCollision(Shape shape) {
		if(shape != null && children != null) {
			for(IEntity entity : children) {
				Sprite enemyPlane = (Sprite)entity;
				if(isCollsionWithRect(
						shape.getRect().left, shape.getRect().top, 
						shape.getRect().width(), shape.getRect().height(), 
						enemyPlane.getRect().left, enemyPlane.getRect().top, 
						enemyPlane.getRect().width(), enemyPlane.getRect().height())) {
					enemyPlane.getParent().detachChild(enemyPlane);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isCollsionWithRect(int x1,int y1,int w1,int h1,int x2,int y2,int w2,int h2){  
        //当矩形1位于矩形2的左侧  
        if (x1 >= x2 && x1>= x2 + w2){  
            return false;  
        //当矩形1位于矩形2的右侧  
        } else if (x1<= x2 && x1 + w1 <= x2){  
            return false;  
        //当矩形1位于矩形2的上方  
        } else if (y1 >= y2 && y1>= y2 + h2){  
            return false;  
        } else if (y1 <= y2 && y1 + h1 <= y2){  
            return false;  
        }  
        //所有不会发生碰撞都不满足时，肯定就是碰撞了  
        return true;  
    }  
}
