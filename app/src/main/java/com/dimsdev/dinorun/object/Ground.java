package com.dimsdev.dinorun.object;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;

public class Ground
{
	
	private Texture texture;
	private Vector2 pos, fpos;
	
	public Ground(float x, float y){
		pos = new Vector2(x, y);
		fpos = new Vector2(x, y);
		texture = new Texture("Ground.png");
		}
		
	public void reposition(OrthographicCamera camera){
		if(camera.position.x - camera.viewportWidth / 2 >
		pos.x + texture.getWidth()){
			pos.add(texture.getWidth(), 0);
		}
	}
		
	public void reset(){
		pos.set(fpos);
	}
		
	public Texture getTexture()
		{
			return texture;
		}

	public Vector2 getPos()
		{
			return pos;
		}
	
}
