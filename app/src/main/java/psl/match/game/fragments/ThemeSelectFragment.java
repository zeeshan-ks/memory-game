package psl.match.game.fragments;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Locale;

import psl.match.game.R;
import psl.match.game.common.Memory;
import psl.match.game.common.Shared;
import psl.match.game.events.ui.ThemeSelectedEvent;
import psl.match.game.themes.Theme;
import psl.match.game.themes.Themes;

public class ThemeSelectFragment extends Fragment {
    private AdView themeSelectionAdView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(Shared.context).inflate(R.layout.theme_select_fragment, container, false);
        themeSelectionAdView = view.findViewById(R.id.themeSelectionAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
//        AdRequest adRequest = new AdRequest.Builder().addTestDevice("4A85472C9E633E48C9318E07FE5DF791").build();
        themeSelectionAdView.loadAd(adRequest);

        View karachi = view.findViewById(R.id.theme_karachi_container);
        View quetta = view.findViewById(R.id.theme_quetta_container);
        View lahore = view.findViewById(R.id.theme_lahore_container);

        View islamabad = view.findViewById(R.id.theme_islamabad_container);
        View multan = view.findViewById(R.id.theme_multan_container);
        View peshawar = view.findViewById(R.id.theme_peshawar_container);

        final Theme themeKarachi = Themes.createKarachiTheme();
        setStars((ImageView) karachi.findViewById(R.id.theme_karachi), themeKarachi, "karachi");
        final Theme themeQuetta = Themes.createQuettaTheme();
        setStars((ImageView) quetta.findViewById(R.id.theme_quetta), themeQuetta, "quetta");
        final Theme themeLahore = Themes.createLahoreTheme();
        setStars((ImageView) lahore.findViewById(R.id.theme_lahore), themeLahore, "lahore");

        final Theme themeIslamabad = Themes.createIslamabadTheme();
        setStars((ImageView) islamabad.findViewById(R.id.theme_islamabad), themeIslamabad, "islamabad");
        islamabad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.eventBus.notify(new ThemeSelectedEvent(themeIslamabad));
            }
        });

        final Theme themeMultan = Themes.createMultanTheme();
        setStars((ImageView) multan.findViewById(R.id.theme_multan), themeMultan, "multan");
        multan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.eventBus.notify(new ThemeSelectedEvent(themeMultan));
            }
        });

        final Theme themePeshawar = Themes.createPeshawarTheme();
        setStars((ImageView) peshawar.findViewById(R.id.theme_peshawar), themePeshawar, "peshawar");
        peshawar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.eventBus.notify(new ThemeSelectedEvent(themePeshawar));
            }
        });

        karachi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.eventBus.notify(new ThemeSelectedEvent(themeKarachi));
            }
        });

        quetta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.eventBus.notify(new ThemeSelectedEvent(themeQuetta));
            }
        });

        lahore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.eventBus.notify(new ThemeSelectedEvent(themeLahore));
            }
        });


        /**
         * Imporove performance first!!!
         */
        animateShow(karachi);
        animateShow(quetta);
        animateShow(lahore);
        animateShow(islamabad);
        animateShow(peshawar);
        animateShow(multan);

        return view;
    }

    private void
    animateShow(View view) {
        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1f);
        ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.5f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300);
        animatorSet.playTogether(animatorScaleX, animatorScaleY);
        animatorSet.setInterpolator(new DecelerateInterpolator(2));
        view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        animatorSet.start();
    }

    private void setStars(ImageView imageView, Theme theme, String type) {
        try {
            int sum = 0;
            for (int difficulty = 1; difficulty <= 6; difficulty++) {
                sum += Memory.getHighStars(theme.id, difficulty);
            }
            int num = sum / 6;
            if (num != 0) {
                String drawableResourceName = String.format(Locale.US, type + "_theme_star_%d", num);
                int drawableResourceId = Shared.context.getResources().getIdentifier(drawableResourceName, "drawable", Shared.context.getPackageName());
                imageView.setImageResource(drawableResourceId);
            }
        } catch (Exception ex) {
            Log.e(this.getClass().getSimpleName(), "Exception", ex);
        }
    }
}
