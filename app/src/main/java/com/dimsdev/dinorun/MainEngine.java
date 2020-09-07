package com.dimsdev.dinorun;
import com.badlogic.gdx.*;
import com.dimsdev.dinorun.screen.*;

public class MainEngine extends Game{

	public static final boolean DEBUG = true;
	
	@Override
	public void create(){
		setScreen(new PlayScreen(this));
	}	
	
}
