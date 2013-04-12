package com.game.ThauanLopes;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.R;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.util.Log;

public class AudioManager {
	private static final String TAG = "GAME AUDIO";
	private static AudioManager Instance;
	private boolean ready;
	private int id;
	public static MediaPlayer player;
	Map<String,MediaPlayer> map = new HashMap<String, MediaPlayer>(){{
	//    put("cat", MediaPlayer.create(MainActivity.context, com.example.appaudio.R.raw.techhouse));
	 
	}};
	MediaPlayer audioMain;
	Collection cont=map.values();
	Iterator i = cont.iterator(); 
	private AudioManager()
	{
	
		
	}
	public static AudioManager GetInstance()
	{
	if(Instance==null)
		Instance=new AudioManager();
		return Instance;
		
	}
	public void PlayAudio(String NameAudio,MediaPlayer Audio)
	{
		Audio = (MediaPlayer)map.get(NameAudio);
		Audio.start();
		
		
	}
	/*public void StopAudio(MediaPlayer Audio)
	{
		Audio.stop();
		Audio=null;
		while (i.hasNext()) {  
		audioMain = (MediaPlayer) i.next();  	
		audioMain.stop();
	
		}
	}*/
}