package com.dimsdev.dinorun.object;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.*;

public class Dino{
	
	private Animation animation;
	private Vector2 pos, velocity, fpos;
	public Array<TextureRegion> texture;
	private Array<Texture> oTexture;
	private int size;
	private Rectangle collision;
	
	private static final int GRAVITY = -15;
	
	public Dino(float x, float y){
		pos = new Vector2(x,y);
		fpos = new Vector2(x, y);
		velocity = new Vector2(0,0);
		size = 60;
		
		oTexture = new Array<Texture>();
		oTexture.add(new Texture("Dino/Dino-stand.png"));
		oTexture.add(new Texture("Dino/Dino-big-eyes.png"));
		texture = new Array<TextureRegion>();	
		texture.add(new TextureRegion(new Texture("Dino/Dino-left-up.png")));
		texture.add(new TextureRegion(new Texture("Dino/Dino-right-up.png")));
			
		animation = new Animation(0.15f, texture);	
		animation.setPlayMode(Animation.PlayMode.LOOP);
		
		collision = new Rectangle(pos.x, pos.y, size, size);
		}
		
	public void reset(){
		pos.set(fpos);
		velocity.set(0, 0);
	}

	public Rectangle getCollision(){
		return collision;
	}

	public Array<Texture> getOtexture(){
		return oTexture;
	}
	
	public int getSize(){
		return size;
 	}
	
	public boolean isJump(){
		return Gdx.input.isTouched() && pos.y > 10 || pos.y > 10;
	}
	
	public void update(float dt){
		velocity.x += dt;
		velocity.y += GRAVITY * dt;
		
		if(Gdx.input.justTouched() && pos.y <= 10)
			velocity.y = 400 * dt;
		
		pos.add(velocity);
		if(pos.y <= 10)
			pos.y = 10;
			
		collision.setPosition(pos);
	}
		
	public Vector2 getPos(){
		return pos;
	}

	public Animation getAnimation(){
			return animation;
	}
	
	public void dispose(){
		for(Texture textures : texture){
			textures.dispose();
		}
		for(Texture texture : oTexture){
			texture.dispose();
		}
	}
	
}
