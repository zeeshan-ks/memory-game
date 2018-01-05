package psl.match.game.common;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import psl.match.game.R;

public class Music {
    public static MediaPlayer backgroundMP;
    public static boolean OFF = false;

    public static void playBackgroud() {
        if (!OFF) {
            try {
                if (backgroundMP == null || !backgroundMP.isPlaying()) {
                    backgroundMP = MediaPlayer.create(Shared.context, R.raw.background_music);

                    backgroundMP.setOnCompletionListener(new OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.reset();
                            mp.release();
                            mp = null;
                            Music.playBackgroud();
                        }

                    });
                    backgroundMP.start();
                } else {
                    backgroundMP.start();
                    ;
                }
            } catch (Exception ex) {

            }
        }
    }

    public static void stopBackgroud() {
        try {
            backgroundMP.pause();
            backgroundMP = null;
        } catch (Exception ex) {
        }
    }

    public static void playCorrent() {
        if (!OFF) {
//			MediaPlayer mp = MediaPlayer.create(Shared.context, R.raw.correct_answer);
            MediaPlayer mp = MediaPlayer.create(Shared.context, R.raw.whistling);
            mp.setOnCompletionListener(new OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.reset();
                    mp.release();
                    mp = null;
                }

            });
            mp.start();
        }
    }

    public static void showStar() {
        if (!OFF) {
            MediaPlayer mp = MediaPlayer.create(Shared.context, R.raw.swash);
            mp.setOnCompletionListener(new OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.reset();
                    mp.release();
                    mp = null;
                }

            });
            mp.start();
        }
    }
}
