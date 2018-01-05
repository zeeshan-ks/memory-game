package psl.match.game;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;

import psl.match.game.common.Music;
import psl.match.game.common.Shared;
import psl.match.game.engine.Engine;
import psl.match.game.engine.ScreenController;
import psl.match.game.engine.ScreenController.Screen;
import psl.match.game.events.EventBus;
import psl.match.game.events.ui.BackGameEvent;
import psl.match.game.ui.PopupManager;
import psl.match.game.utils.Utils;

public class MainActivity extends FragmentActivity {

	private ImageView mBackgroundImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Shared.context = getApplicationContext();
		Shared.engine = Engine.getInstance();
		Shared.eventBus = EventBus.getInstance();

		setContentView(R.layout.activity_main);
		mBackgroundImage = (ImageView) findViewById(R.id.background_image);

		Shared.activity = this;
		Shared.engine.start();
		Shared.engine.setBackgroundImageView(mBackgroundImage);

		// set background
		setBackgroundImage();

		// set menu
		ScreenController.getInstance().openScreen(Screen.MENU);
			Music.backgroundMP = null;
			Music.playBackgroud();

	}

	@Override
	protected void onDestroy() {
		Shared.engine.stop();
		super.onDestroy();
		try {
			Music.stopBackgroud();
		} catch (Exception ex) {
			Log.e("Music","cant play background music:",ex);
		}
	}

	@Override
	public void onBackPressed() {
		if (PopupManager.isShown()) {
			PopupManager.closePopup();
			if (ScreenController.getLastScreen() == Screen.GAME) {
				Shared.eventBus.notify(new BackGameEvent());
			}
		} else if (ScreenController.getInstance().onBack()) {
			super.onBackPressed();
		}
	}

	private void setBackgroundImage() {
//		Bitmap bitmap = Utils.scaleDown(R.drawable.background, Utils.screenWidth(), Utils.screenHeight());
		Bitmap bitmap = Utils.scaleDown(R.drawable.ground, Utils.screenWidth(), Utils.screenHeight());
		bitmap = Utils.crop(bitmap, Utils.screenHeight(), Utils.screenWidth());
		bitmap = Utils.downscaleBitmap(bitmap, 2);
		mBackgroundImage.setImageBitmap(bitmap);
	}

}
