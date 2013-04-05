package com.game.ThauanLopes;

import java.util.HashSet;
import java.util.Random;


public class EnemyManager {

	private float maxInterval;
	private float minInterval;
	
	private float cronometro;
	private float tempEnemyInterval;
	
	private Random random = new Random();
	
	private HashSet<Enemy> inimigos;
	
	public EnemyManager(int minInterval, int maxInterval, HashSet<Enemy> inimigos)
	{
		this.maxInterval = maxInterval;
		this.minInterval = minInterval;
		
		this.inimigos = inimigos;
		
		tempEnemyInterval = random.nextFloat() * (this.maxInterval - this.minInterval) + minInterval;
	}

	public void Update()
	{
		
		this.cronometro += Game.deltaTime;
		//Log.i("ENEMYMANAGER", "" + this.cronometro);
		
		if(this.cronometro >= this.tempEnemyInterval)
		{
			this.cronometro = 0;
			Enemy enemy;
			
			int rndEnemy = random.nextInt(3);
			switch(rndEnemy)
			{
			case 0:
				enemy = new Slowest(inimigos);
				break;
				
			case 1: 
				enemy = new Medium(inimigos);
				break;
				
			case 2:
				enemy = new Faster(inimigos);
				break;
			}

			this.tempEnemyInterval = random.nextFloat() * (this.maxInterval - this.minInterval) + minInterval;
		}
		
	}
	
	public void SetInterval(float min, float max)
	{
		this.minInterval = min;
		this.maxInterval = max;
	}
}
