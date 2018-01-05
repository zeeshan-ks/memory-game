package psl.match.game.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import psl.match.game.R;
import psl.match.game.common.Shared;
import psl.match.game.events.engine.FlipDownCardsEvent;
import psl.match.game.events.engine.GameWonEvent;
import psl.match.game.events.engine.HidePairCardsEvent;
import psl.match.game.events.engine.TimerEndedEvent;
import psl.match.game.model.Game;
import psl.match.game.ui.BoardView;
import psl.match.game.ui.PopupManager;
import psl.match.game.utils.Clock;
import psl.match.game.utils.Clock.OnTimerCount;
import psl.match.game.utils.FontLoader;
import psl.match.game.utils.FontLoader.Font;

public class GameFragment extends BaseFragment {

    private BoardView mBoardView;
    private TextView mTime;
    private ImageView mTimeImage;
    private AdView themeSelectionAdView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.game_fragment, container, false);
        view.setClipChildren(false);
        ((ViewGroup) view.findViewById(R.id.game_board)).setClipChildren(false);
        mTime = (TextView) view.findViewById(R.id.time_bar_text);
        mTimeImage = (ImageView) view.findViewById(R.id.time_bar_image);
        FontLoader.setTypeface(Shared.context, new TextView[]{mTime}, Font.GROBOLD);
        mBoardView = BoardView.fromXml(getActivity().getApplicationContext(), view);
        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.game_container);
        frameLayout.addView(mBoardView);
        frameLayout.setClipChildren(false);
        themeSelectionAdView = view.findViewById(R.id.themeSelectionAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
//        AdRequest adRequest = new AdRequest.Builder().addTestDevice("4A85472C9E633E48C9318E07FE5DF791").build();
        themeSelectionAdView.loadAd(adRequest);
        // build board
        buildBoard();
        Shared.eventBus.listen(FlipDownCardsEvent.TYPE, this);
        Shared.eventBus.listen(HidePairCardsEvent.TYPE, this);
        Shared.eventBus.listen(GameWonEvent.TYPE, this);
        Shared.eventBus.listen(TimerEndedEvent.TYPE, this);

        return view;
    }

    @Override
    public void onDestroy() {
        Shared.eventBus.unlisten(FlipDownCardsEvent.TYPE, this);
        Shared.eventBus.unlisten(HidePairCardsEvent.TYPE, this);
        Shared.eventBus.unlisten(GameWonEvent.TYPE, this);
        Shared.eventBus.unlisten(TimerEndedEvent.TYPE, this);
        super.onDestroy();
    }

    private void buildBoard() {
        try {
            Game game = Shared.engine.getActiveGame();
            int time = game.boardConfiguration.time;
            setTime(time);
            mBoardView.setBoard(game);

            startClock(time);
        } catch (Exception ex) {
            Toast.makeText(Shared.context, "Unable to create board", Toast.LENGTH_SHORT).show();
        }
    }

    private void setTime(int time) {
        int min = time / 60;
        int sec = time - min * 60;
        mTime.setText(" " + String.format("%02d", min) + ":" + String.format("%02d", sec));
    }

    private void startClock(int sec) {
        Clock clock = Clock.getInstance();
        clock.startTimer(sec * 1000, 1000, new OnTimerCount() {

            @Override
            public void onTick(long millisUntilFinished) {
                setTime((int) (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                setTime(0);
                Shared.eventBus.notify(new TimerEndedEvent());
            }
        });
    }

    @Override
    public void onEvent(GameWonEvent event) {
        mTime.setVisibility(View.GONE);
        mTimeImage.setVisibility(View.GONE);
        PopupManager.showPopupWon(event.gameState);
    }

    @Override
    public void onEvent(TimerEndedEvent event) {
        mTime.setVisibility(View.GONE);
        mTimeImage.setVisibility(View.GONE);
        Log.d(getClass().getSimpleName(), "onEvent " + event.getType());
        PopupManager.showPopupTimeOver();
    }

    @Override
    public void onEvent(FlipDownCardsEvent event) {
        mBoardView.flipDownAll();
    }

    @Override
    public void onEvent(HidePairCardsEvent event) {
        mBoardView.hideCards(event.id1, event.id2);
    }

}
