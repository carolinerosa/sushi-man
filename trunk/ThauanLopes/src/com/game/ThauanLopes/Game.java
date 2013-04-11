package com.game.ThauanLopes;

import java.util.HashSet;
import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class Game extends View implements Runnable{

	
	private boolean running = true;
	
	// Thread Interval
	public int interval = 10;
	
	// To catch all the scene game objects and run their Update and Draw methods.
	public HashSet<Enemy> inimigos = new HashSet<Enemy>();
	public static HashSet<GameObject> cemetery = new HashSet<GameObject>();
	public HashSet<GameObject> gameObjects = new HashSet<GameObject>();
	
	Thread mainThread;
	
	EnemyManager enemyManager;
	
	private Player player;
	public static Resources resources;
	
	public static int canvasHeight;
	public static int canvasWidth;

	private Bitmap background;
	Background bg;
	
	public static int floor;
	
	public static long deltaTime;
	public static long lastTimeCount;
	
	private static Game instance = null;
	
	public static Game getInstance(Context context)
	{
		if(instance == null)
		{
			instance = new Game(context);
		}
		return instance;
	}
	public Game(Context context) 
	{
		super(context);
		
		running = true;
		
		this.setFocusable(true);
		
		resources = context.getResources();
		
		background = BitmapFactory.decodeResource(resources,R.drawable.punch_background);
		
		Log.i("Páscoa", "Contruiu");
		
		mainThread = new Thread(this);
		//thread.setPriority(Thread.NORM_PRIORITY);
		mainThread.start();
		
		enemyManager = new EnemyManager(500, 2000, this.inimigos);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		canvasWidth = w;
		canvasHeight = h;
		
		floor = h * 7/10;
		
		bg = new Background(background ,new Rect(0,0,w,h), gameObjects);
		player = new Player(w,h, gameObjects);
	}
	
	public boolean onTouchEvent(MotionEvent event) 
	{
		switch (event.getAction()) 
		{
			case MotionEvent.ACTION_DOWN:
			player.Turn();
			break;
		}
		return super.onTouchEvent(event);
	}
	
	// Start
	public void Start()
	{
		lastTimeCount = System.currentTimeMillis();	
	}
	
	// Update
	public void Update()
	{
		deltaTime = (System.currentTimeMillis() - lastTimeCount);
		lastTimeCount = System.currentTimeMillis();
		
		// gameObjects update
		if(!gameObjects.isEmpty())
		{
			for(GameObject gb : gameObjects)
			{
				gb.Update();
			}
		}
		// enemies update
		if(!inimigos.isEmpty())
		{
			for(Enemy enemy : inimigos)
			{
				enemy.Update();
			}
		}
		
		// player collision verification
		if(!inimigos.isEmpty() && gameObjects.contains(this.player) && player.isAlive())
		{
			for(Enemy enemy : inimigos)
			{
				if(enemy.isAlive()){
					switch( this.player.Collision(enemy.getRect()))
					{
					case 1:
							player.Attack();
							enemy.Die();
							break;
					case 2:
							enemy.Attack();
							player.Die();
							break;
						
					default:
						
						break;
					}
				}
			}
		}
	}
	
	//Draw
	@Override
	public void draw(Canvas canvas) 
	{
		super.draw(canvas);
		
			this.enemyManager.Update();
			
			if(!cemetery.isEmpty())
			{
				for(GameObject gb : cemetery)
				{
					gb.Destroy();
					gb = null;
				}
			}
			cemetery.clear();		
				
				
		if(!gameObjects.isEmpty())
		{
			for(GameObject go : gameObjects)
			{
				if(go == null)
				{
					//gameObjects.remove(go);
				}else{
					go.Draw(canvas);
				}
			}
		}
		
		// draw enemies
		if(!inimigos.isEmpty())
		{
			for(GameObject enemy : inimigos)
			{
				if(enemy == null)
				{
					
				}else{
					enemy.Draw(canvas);
				}
			}
		}
	}
	
	public void SetAlive(boolean bool)
	{
		this.running = bool;
	}
		
	@Override
	public void run() {
		this.Start();
		
		while(running)
		{
			try
			{
				Thread.sleep(interval);
			}
			catch(InterruptedException e)
			{
				running = false;
				Log.i("Thread exception!", e.toString());
			}
			
			Update();
			postInvalidate();			
		}

	}
}
