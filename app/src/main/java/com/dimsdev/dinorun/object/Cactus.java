package com.dimsdev.dinorun.object;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.utils.*;
import java.util.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.*;

public class Cactus{
	
	private Random random;
	private Array<Texture> texture;
	private Vector2 pos, fpos;
	private Rectangle collisionBox;
	private int randomTextureArray;
	
	public Cactus(int x, int y){	
		random = new Random();
		texture = new Array<Texture>();
		randomTextureArray = Math.min(Math.max(random.nextInt(), 0), 4);
		
		for(int i = 1; i <= 5; i++)
			texture.add(new 
			Texture("Obstacle/Cactus-" + i + ".png"));
		
		pos = new Vector2(x, y);
		fpos = new Vector2(x, y);
		for(Texture textures : texture){
			collisionBox = new Rectangle(pos.x, pos.y, 
			textures.getWidth(), textures.getHeight());
		}
	}
	
	public boolean collide(Rectangle object){
		return object.overlaps(collisionBox);
	}

	public Rectangle getCollisionBox(){
		return collisionBox;
	}
		
	public TextureRegion getRandomTexture(){
		return new TextureRegion(texture.get(randomTextureArray));
	}
	
	public void reposition(int x, int y){
		pos.set(x, y);
		collisionBox.setPosition(pos);
	}
	
	public void reset(){
		pos.set(fpos);
	}
	
	public Vector2 getPos()	{
		return pos;
	}
	
	public void dispose(){
		for(Texture textures : texture)
			textures.dispose();
	}
	
}
