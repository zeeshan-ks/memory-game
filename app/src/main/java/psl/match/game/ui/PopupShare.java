package psl.match.game.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.appinvite.AppInviteInvitation;

import psl.match.game.R;
import psl.match.game.common.Shared;

public class PopupShare extends RelativeLayout {

    private TextView mTime;
    private ImageView mSMSButton;
    private ImageView mChooserButton;
    private Handler mHandler;

    public PopupShare(Context context) {
        this(context, null);
    }

    public PopupShare(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.popup_share, this, true);
        mTime = findViewById(R.id.time_bar_text);
        mSMSButton = findViewById(R.id.button_sms);
        mChooserButton = findViewById(R.id.button_chooser);
        setBackgroundResource(R.drawable.tile);
        mHandler = new Handler();

        mSMSButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new AppInviteInvitation.IntentBuilder(Shared.context.getString(R.string.app_name))
                            .setMessage(Shared.context.getString(R.string.invitation_message))
                            .setDeepLink(Uri.parse(Shared.context.getString(R.string.app_link)))
                            .setCallToActionText(Shared.context.getString(R.string.app_name))
                            .build();
                    Shared.activity.startActivityForResult(intent, 001);
                } catch (Exception ex) {

                }
            }
        });

        mChooserButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT,Shared.context.getString(R.string.invitation_message)+ Uri.parse(Shared.context.getString(R.string.app_link)));
                    sendIntent.setType("text/plain");
                    Shared.activity.startActivity(sendIntent);
                } catch (Exception ex) {
                }
            }
        });
    }
}
