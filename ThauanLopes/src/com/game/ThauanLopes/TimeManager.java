package com.game.ThauanLopes;

public class TimeManager implements Runnable {

	private Thread timeThread;
	private double deltaTime;
	private double lastTimeCount;
	public TimeManager()
	{
		timeThread = new Thread(this);
		timeThread.start();
		
	}
	
	public void run()
	{
		deltaTime = (System.currentTimeMillis() - this.lastTimeCount);
		lastTimeCount = System.currentTimeMillis();
	}
	
	public double getDeltaTime()
	{
		return this.deltaTime;
	}
}
