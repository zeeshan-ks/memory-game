package psl.match.game;

import android.app.Application;

import psl.match.game.utils.FontLoader;

public class GameApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FontLoader.loadFonts(this);

    }


}
