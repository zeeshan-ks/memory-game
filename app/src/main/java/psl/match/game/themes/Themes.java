package psl.match.game.themes;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;

import psl.match.game.common.Shared;
import psl.match.game.utils.Utils;

public class Themes {

    public static String URI_DRAWABLE = "drawable://";

    public static Theme createKarachiTheme() {
        Theme theme = new Theme();
        try {
            theme.id = 1;
            theme.name = "Karachi Kings";
            theme.backgroundImageUrl = URI_DRAWABLE + "background";
            theme.tileImageUrls = new ArrayList<String>();
            // 40 drawables
            for (int i = 1; i <= 25; i++) {
                theme.tileImageUrls.add(URI_DRAWABLE + String.format("kk%d", i));
            }
        } catch (Exception ex) {
            Log.e("Themes", "Exception", ex);
        }
        return theme;
    }

    public static Theme createQuettaTheme() {
        Theme theme = new Theme();
        try {
            theme.id = 2;
            theme.name = "Quetta Gladiator";
            theme.backgroundImageUrl = URI_DRAWABLE + "background";
            theme.tileImageUrls = new ArrayList<String>();
            // 40 drawables
            for (int i = 1; i <= 25; i++) {
                theme.tileImageUrls.add(URI_DRAWABLE + String.format("qg%d", i));
            }
        } catch (Exception ex) {
            Log.e("Themes", "Exception", ex);
        }
        return theme;
    }

    public static Theme createLahoreTheme() {
        Theme theme = new Theme();
        try {
            theme.id = 3;
            theme.name = "Lahore Qalandars";
            theme.backgroundImageUrl = URI_DRAWABLE + "background";
            theme.tileImageUrls = new ArrayList<String>();
            // 40 drawables
            for (int i = 1; i <= 25; i++) {
                theme.tileImageUrls.add(URI_DRAWABLE + String.format("lq%d", i));
            }
        } catch (Exception ex) {
            Log.e("Themes", "Exception", ex);
        }
        return theme;
    }

    public static Theme createIslamabadTheme() {
        Theme theme = new Theme();
        try {
            theme.id = 4;
            theme.name = "Islamabad United";
            theme.backgroundImageUrl = URI_DRAWABLE + "background";
            theme.tileImageUrls = new ArrayList<String>();
            // 40 drawables
            for (int i = 1; i <= 25; i++) {
                theme.tileImageUrls.add(URI_DRAWABLE + String.format("iu%d", i));
            }
        } catch (Exception ex) {
            Log.e("Themes", "Exception", ex);
        }
        return theme;
    }

    public static Theme createMultanTheme() {
        Theme theme = new Theme();
        try {
            theme.id = 5;
            theme.name = "Multan Sultan";
            theme.backgroundImageUrl = URI_DRAWABLE + "background";
            theme.tileImageUrls = new ArrayList<String>();
            // 40 drawables
            for (int i = 1; i <= 25; i++) {
                theme.tileImageUrls.add(URI_DRAWABLE + String.format("ms%d", i));
            }
        } catch (Exception ex) {
            Log.e("Themes", "Exception", ex);
        }

        return theme;
    }

    public static Theme createPeshawarTheme() {
        Theme theme = new Theme();
        try {
            theme.id = 6;
            theme.name = "";
            theme.backgroundImageUrl = URI_DRAWABLE + "background";
            theme.tileImageUrls = new ArrayList<String>();
            // 40 drawables
            for (int i = 1; i <= 25; i++) {
                theme.tileImageUrls.add(URI_DRAWABLE + String.format("pz%d", i));
            }
        } catch (Exception ex) {
            Log.e("Themes", "Exception", ex);
        }
        return theme;
    }

    public static Bitmap getBackgroundImage(Theme theme) {
        Bitmap bitmap = null;
        try {
            String drawableResourceName = theme.backgroundImageUrl.substring(Themes.URI_DRAWABLE.length());
            int drawableResourceId = Shared.context.getResources().getIdentifier(drawableResourceName, "drawable", Shared.context.getPackageName());
            bitmap = Utils.scaleDown(drawableResourceId, Utils.screenWidth(), Utils.screenHeight());
        } catch (Exception ex) {
            Log.e("Themes", "Exception", ex);
        }
        return bitmap;
    }

}
