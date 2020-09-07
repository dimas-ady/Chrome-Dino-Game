package com.dimsdev.dinorun.screen;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.dimsdev.dinorun.*;
import com.dimsdev.dinorun.object.*;
import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.*;
import java.util.*;
import com.badlogic.gdx.graphics.glutils.*;
public class PlayScreen implements Screen{
	
		private MainEngine mainEngine;
		
		private SpriteBatch batch;
		private AssetManager manager;
		private OrthographicCamera camera;
		private BitmapFont text;
		//private Viewport viewport;
		private float time;
		private float score;
		private TextButton buttonRotate;
		private ShapeRenderer shapeDebug;
		
		private Dino dino;
		private Ground ground;
		private Array<Cactus> cactus;
		
		public PlayScreen(MainEngine mainEngine){
			this.mainEngine =  mainEngine;
			
			manager = new AssetManager();
			//manager.load("Font/vcr1.ttf", BitmapFont.class);
			manager.finishLoading();
			batch = new SpriteBatch();
			camera = new OrthographicCamera();
			camera.setToOrtho(false, 
			400 * Gdx.graphics.getWidth() / 
			Gdx.graphics.getHeight(), 400);
			//camera.rotate(2 * 90);
			text = new BitmapFont();
			dino = new Dino(70, 10);
			ground = new Ground(10, 10);
			
			
			cactus = new Array<Cactus>();
			for(int i = 0; i <= 4; i++){
				cactus.add(new Cactus(
				Math.max(new Random().nextInt(1600), 500) * i + 500, 10));
			}
			shapeDebug = new ShapeRenderer();
		}

		@Override
		public void show(){
			/*buttonRotate.setText("Rotate");
			buttonRotate.setColor(Color.RED);
				buttonRotate.addListener(new EventListener(){
					@Override
					public boolean handle(Event p1){
					return false;
				}
			});*/
		}

		@Override
		public void render(float p1){
			
			int fps = Gdx.graphics.getFramesPerSecond();
			score += .03 * Math.ceil(Gdx.graphics.getDeltaTime());
			
			// Update Here
			camera.update();
			camera.position.x = dino.getPos().x + 300;
			time += Gdx.graphics.getDeltaTime();
			dino.update(Gdx.graphics.getDeltaTime());
			//ground.reposition(camera);
			
			for(Cactus cactuses : cactus){
				if(camera.position.x - camera.viewportWidth / 2 >
				   cactuses.getPos().x + cactuses.getRandomTexture().getTexture().getWidth()){
				   cactuses.reposition((int) cactuses.getPos().x + (1500 + 52) * cactus.size, (int) cactuses.getPos().y);
				}		
			}
			
			for(Cactus cactuses : cactus){
				if(cactuses.collide(dino.getCollision())){
					dino.reset();
					ground.reset();
					ground.reset();
					score = 0;
				}
			}
			
			Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
			Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT | 
			Gdx.gl20.GL_DEPTH_BUFFER_BIT);
			
			batch.enableBlending();
			
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			
			
			for(int i = 1; i <= 20000; i += 110){
				batch.draw(ground.getTexture(), ground.getPos().x * i,
				ground.getPos().y, ground.getTexture().getWidth(),
				ground.getTexture().getHeight() * 1.4f);
			}
			
			
			for(Cactus cactuses : cactus){
				batch.draw(cactuses.getRandomTexture(), 
				cactuses.getPos().x, cactuses.getPos().y);
			}
			
			if(!dino.isJump()){
				batch.draw((TextureRegion) dino.getAnimation().getKeyFrame(time),
				dino.getPos().x, dino.getPos().y, dino.getSize(), dino.getSize());
			} else if(dino.isJump()){
				batch.draw(dino.getOtexture().get(0),
			    dino.getPos().x, dino.getPos().y, dino.getSize(), dino.getSize());
			} else {
				for(Cactus cactuses : cactus){
					if(cactuses.collide(dino.getCollision())){
						batch.draw(dino.getOtexture().get(1), dino.getPos().x,
						dino.getPos().y, dino.getSize(), dino.getSize());
					}
				}
			}
			
			
			// FPS LOGGER
			if(MainEngine.DEBUG){
				if(fps <= 80 && fps >= 30)
					text.setColor(Color.GREEN);
				else if(fps < 30 && fps >= 15)
					text.setColor(Color.YELLOW);
				else
					text.setColor(Color.RED);
				text.draw(batch, "FPS : " + fps, 
				camera.position.x - camera.viewportWidth / -2.5f, 
				camera.viewportHeight - 10);
			}
			
			text.setColor(Color.GRAY);
			text.draw(batch, "Chrome Dino Game clone by Dimas Adiyaksa",
			camera.position.x - camera.viewportWidth / 2, camera.viewportHeight - 10);
			
			// Draw Score
			text.setColor(Color.GOLD);
			text.draw(batch, "Score : " + score, camera.position.x,
			camera.viewportHeight - 20);
			
			batch.end();
			
			shapeDebug.setProjectionMatrix(camera.combined);
			if(mainEngine.DEBUG){
				shapeDebug.begin(ShapeRenderer.ShapeType.Filled);
				/*shapeDebug.setColor(Color.RED);
				shapeDebug.rect(dino.getCollision().getX(), 
				dino.getCollision().getY(), 
				dino.getCollision().getWidth(), 
				dino.getCollision().getHeight());
				
				for(Cactus cactuses : cactus){
					
					//if(cactuses.collide(
					
					shapeDebug.rect( cactuses.getCollisionBox().getX(),
					cactuses.getCollisionBox().getY(),
					 cactuses.getCollisionBox().getWidth(), 
					 cactuses.getCollisionBox().getHeight());
					 
				}*/
				
				shapeDebug.end();
			}
			
		}

		@Override
		public void resize(int p1, int p2){
			
		}

		@Override
		public void pause(){
				
		}

		@Override
		public void resume(){
				
		}

		@Override
		public void hide(){
			
		}

		@Override
		public void dispose(){
			batch.dispose();
			manager.dispose();
			text.dispose();
			dino.dispose();
			ground.getTexture().dispose();	
			for(Cactus getCactus: cactus){
				getCactus.dispose();
			}
			shapeDebug.dispose();
		}
		
}
