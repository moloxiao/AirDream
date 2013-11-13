package com.mololovecode.airdream.game;

import com.hifreshday.android.pge.engine.Engine;
import com.hifreshday.android.pge.engine.options.EngineOptions;
import com.hifreshday.android.pge.engine.options.ScaleModel;
import com.hifreshday.android.pge.entity.scene.Scene;
import com.hifreshday.android.pge.ui.activity.BaseGameActivity;
import com.mololovecode.airdream.R;

public class GameViewActivity extends BaseGameActivity{

	@Override
	public boolean coming() {
		return true;
	}

	@Override
	public int getGameViewResId() {
		return R.id.gameview;
	}

	@Override
	public int getLayoutResId() {
		return R.layout.gameview;
	}

	@Override
	public int getSceneBgResId() {
		return R.drawable.bg;
	}

	@Override
	public Engine onEngineLoaded() {
		return new Engine(
				new EngineOptions(
						getScreenWidth(), 
						getScreenHeight(), 
						ScaleModel.CONSTRAIN, 
						true, 
						EngineOptions.ORIENTATION_PORTPRAIT));
	}

	@Override
	public void onLoadComplete() {
	}

	@Override
	public Scene onLoadScene() {
		GameScene scene = new GameScene();
		scene.onLoadResources(getResources(), null);	// TODO : change null object 
		scene.setScreenSize(
        		EngineOptions.getScreenWidth(), EngineOptions.getScreenHeight());
		scene.setBgResId(getResources(), getSceneBgResId());
		return scene;
	}

	@Override
	public void onPauseGame() {
	}

	@Override
	public void onResumeGame() {
	}

}
