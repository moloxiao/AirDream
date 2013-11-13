package com.mololovecode.airdream.game.res;

import java.util.HashMap;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.hifreshday.android.pge.engine.Engine;
import com.hifreshday.android.pge.view.res.GameBitmapUtil;
import com.hifreshday.android.pge.view.res.IBitmapRes;
import com.mololovecode.airdream.R;

public class BitmapRes implements IBitmapRes {

	private Resources res;
	private HashMap<Integer, Integer> resMap = new HashMap<Integer, Integer>();
	private HashMap<Integer, Bitmap> resBitmapMap = new HashMap<Integer, Bitmap>();
	
	public static final int KEY_SHIP = 10001;
	public static final int KEY_ENEMY_POOL_PLANE = 10002;
	
	public BitmapRes(Resources res) {
        Engine.isRecycle = false;
        this.res = res;
        initRes();
	}

	private void initRes() {
		resMap.put(KEY_SHIP, R.drawable.sb_ship);
		resMap.put(KEY_ENEMY_POOL_PLANE, R.drawable.enemy_foo_plane);
	}
	
	@Override
	public Bitmap getBitmap(int key) {
		Bitmap bmp = resBitmapMap.get(key);
        if (bmp == null) {
            bmp = GameBitmapUtil.loadBitmap(res, resMap.get(key));
            if (bmp != null) {
                resBitmapMap.put(key, bmp);
            }
        }
        return bmp;
	}

	@Override
	public Bitmap getDefalutBitmap() {
		return null;
	}

	@Override
	public String getResName() {
		return null;
	}

	@Override
	public void recycle() {
		Engine.isRecycle = true;
        if (resBitmapMap != null && resBitmapMap.size() > 0) {
            for (Bitmap bmp : resBitmapMap.values()) {
                GameBitmapUtil.recycleBitmap(bmp);
            }
        }

	}

}
