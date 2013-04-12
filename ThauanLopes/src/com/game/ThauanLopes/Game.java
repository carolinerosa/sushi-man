package com.game.ThauanLopes;

import java.util.HashSet;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
	
	private int enemiesDead;
	
	private Paint textEnemiesDeads;
	
	public static int canvasHeight;
	public static int canvasWidth;

	private boolean isDead = false;
	
	private Bitmap background;
	Background bg;
	
	public static int floor;
	public GameObject reload;
	
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
		
		textEnemiesDeads = new Paint();
		textEnemiesDeads.setColor(Color.BLACK); 
		textEnemiesDeads.setTextSize(20);
		
		this.setBackgroundColor(Color.WHITE);
		

		
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
		
		Bitmap reloadImage = BitmapFactory.decodeResource(resources, R.drawable.reload);
		reload = new ImageButton(reloadImage,w/2-128,h/2-128,"reload");
	}
	
	public boolean onTouchEvent(MotionEvent event) 
	{
		switch (event.getAction()) 
		{
			case MotionEvent.ACTION_DOWN:
				
			if(isDead)
			{
				int x = (int) event.getX(0);
				int y = (int) event.getY(0);
					
				Rect fingersPos = new Rect(x-25,y - 25,x + 25, y + 25);
				
				if(reload.collision(fingersPos))
				{
					AudioManager audio = AudioManager.GetInstance();
					
					if(audio.ready == false)
					audio.PlayAudio("cat");
					
					else
						audio.resumeAudio();
					
					Game game = new Game(MainActivity.context);
					Activity act = (Activity) MainActivity.context;
					act.setContentView(game);
				}
			}
			else
			{
				player.Turn();
				break;
			}
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
							enemiesDead++;
							enemy.Die();
							break;
					case 2:
							enemy.Attack();
							player.Die();
							isDead = true;
							SetAlive(false);
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
			
			canvas.drawText(""+enemiesDead, 40, 40, textEnemiesDeads);
			
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
				go.Draw(canvas);
				
			}
		}
		
		// draw enemies
		if(!inimigos.isEmpty())
		{
			for(GameObject enemy : inimigos)
			{
				enemy.Draw(canvas);
				
			}
		}
		
		if(isDead)
		{
			reload.Draw(canvas);
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
