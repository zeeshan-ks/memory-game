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


public class EventObserverAdapter implements EventObserver {

	public void onEvent(FlipCardEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(DifficultySelectedEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(HidePairCardsEvent event) {
		throw new UnsupportedOperationException();
	}
	@Override
	public void onEvent(TimerEndedEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(FlipDownCardsEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(StartEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(ThemeSelectedEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(GameWonEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(BackGameEvent event) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public void onEvent(NextGameEvent event) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public void onEvent(ResetBackgroundEvent event) {
		throw new UnsupportedOperationException();		
	}

}
