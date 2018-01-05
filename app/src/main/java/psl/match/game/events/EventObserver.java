package psl.match.game.events;

import psl.match.game.events.engine.FlipDownCardsEvent;
import psl.match.game.events.engine.GameWonEvent;
import psl.match.game.events.engine.HidePairCardsEvent;
import psl.match.game.events.engine.TimerEndedEvent;
import psl.match.game.events.ui.BackGameEvent;
import psl.match.game.events.ui.DifficultySelectedEvent;
import psl.match.game.events.ui.FlipCardEvent;
import psl.match.game.events.ui.NextGameEvent;
import psl.match.game.events.ui.ResetBackgroundEvent;
import psl.match.game.events.ui.StartEvent;
import psl.match.game.events.ui.ThemeSelectedEvent;


public interface EventObserver {

	void onEvent(FlipCardEvent event);
	void onEvent(TimerEndedEvent event);

	void onEvent(DifficultySelectedEvent event);

	void onEvent(HidePairCardsEvent event);

	void onEvent(FlipDownCardsEvent event);

	void onEvent(StartEvent event);

	void onEvent(ThemeSelectedEvent event);

	void onEvent(GameWonEvent event);

	void onEvent(BackGameEvent event);

	void onEvent(NextGameEvent event);

	void onEvent(ResetBackgroundEvent event);

}
