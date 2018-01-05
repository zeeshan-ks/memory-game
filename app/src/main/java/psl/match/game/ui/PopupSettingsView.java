package psl.match.game.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import psl.match.game.R;
import psl.match.game.common.Music;
import psl.match.game.common.Shared;
import psl.match.game.utils.FontLoader;
import psl.match.game.utils.FontLoader.Font;

public class PopupSettingsView extends LinearLayout {

    private ImageView mSoundImage;
    private TextView mSoundText;

    public PopupSettingsView(Context context) {
        this(context, null);
    }

    public PopupSettingsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        setBackgroundResource(R.drawable.settings_popup);
        LayoutInflater.from(getContext()).inflate(R.layout.popup_settings_view, this, true);
        mSoundText = (TextView) findViewById(R.id.sound_off_text);
        TextView rateView = (TextView) findViewById(R.id.rate_text);
        FontLoader.setTypeface(context, new TextView[]{mSoundText, rateView}, Font.GROBOLD);
        mSoundImage = (ImageView) findViewById(R.id.sound_image);
        View soundOff = findViewById(R.id.sound_off);
        soundOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Music.OFF = !Music.OFF;
                if (Music.OFF){
                    Log.d("setting","stopBackground Music");
                    Music.stopBackgroud();}
                else{
                    Log.d("setting","startBackground Music");
                    Music.playBackgroud();}
                setMusicButton();
            }
        });
        View rate = findViewById(R.id.rate);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String appPackageName = Shared.context.getPackageName();
                try {
                    Shared.activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    Shared.activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });
        setMusicButton();
    }

    private void setMusicButton() {
        if (Music.OFF) {
            mSoundText.setText("Sound OFF");
            mSoundImage.setImageResource(R.drawable.button_music_off);
        } else {
            mSoundText.setText("Sound ON");
            mSoundImage.setImageResource(R.drawable.button_music_on);
        }
    }
}
