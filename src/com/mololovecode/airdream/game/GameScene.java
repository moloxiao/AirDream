package com.mololovecode.airdream.game;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import com.hifreshday.android.pge.entity.scene.BitmapBgScreen;
import com.hifreshday.android.pge.entity.shape.Shape;
import com.mololovecode.airdream.game.res.BitmapRes;
import com.mololovecode.airdream.game.sprite.ShipSprite;
import com.mololovecode.airdream.game.sprite.StartBgViewSprite;
import com.mololovecode.airdream.game.sprite.enemy.EnemyPlaneFooSpriteHolder;

public class GameScene extends BitmapBgScreen {

	private BitmapRes bitmapRes;
	
	private StartBgViewSprite startBgViewSprite;
	private ShipSprite shipSprite;
	private EnemyPlaneFooSpriteHolder enemyPlaneFooSpriteHolder;
	
	@Override
	public void onLoadResources(Resources res, AssetManager assertManager) {
		bitmapRes = new BitmapRes(res);
		initSprite();

	}

	private void initSprite() {
		createSprite();
		managerAllChild();
		managerAllTouch();
	}
	
	private void createSprite() {
		startBgViewSprite = new StartBgViewSprite(bitmapRes, 
				StartBgViewSprite.X, StartBgViewSprite.Y,
				StartBgViewSprite.WIDTH, StartBgViewSprite.HEIGHT);
		shipSprite = new ShipSprite(bitmapRes, 
				ShipSprite.X, ShipSprite.Y,
				ShipSprite.WIDTH, ShipSprite.HEIGHT);
		enemyPlaneFooSpriteHolder = new EnemyPlaneFooSpriteHolder(bitmapRes, 0,0,0,0);
	}
	
	private void managerAllChild() {
		attachChild(startBgViewSprite);
		attachChild(shipSprite);
		attachChild(enemyPlaneFooSpriteHolder);
	}
	
	private void managerAllTouch() {
		registerTouch(shipSprite);
	}

	@Override
	public void onUnloadResources() {
	}
	
	public boolean isCollision(Shape shape) {
		return enemyPlaneFooSpriteHolder.isCollision(shape);
	}

	public void destoryShip() {
		Log.i("MOLO_DEBUG", "ship collision enemy ... ");
		
	}

}
