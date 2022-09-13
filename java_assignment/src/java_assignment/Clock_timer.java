package java_assignment;

import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Clock_timer extends Timer{

	public Clock_timer(int delay, ActionListener listener) {
		super(delay, listener);
	}
	
	@Override
	public void start() {
		super.setInitialDelay(0);
		super.start();
	}
	

}