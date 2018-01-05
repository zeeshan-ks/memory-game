package psl.match.game.events.engine;

import psl.match.game.events.AbstractEvent;
import psl.match.game.events.EventObserver;

/**
 * When the 'timer reaches 00:00'
 */
public class TimerEndedEvent extends AbstractEvent {

	public static final String TYPE = TimerEndedEvent.class.getName();

	@Override
	protected void fire(EventObserver eventObserver) {
		eventObserver.onEvent(this);
	}

	@Override
	public String getType() {
		return TYPE;
	}

}
