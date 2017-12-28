package com.kambul.flute.Screens;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kambul.flute.Actors.ParallaxBackground;
import com.kambul.flute.Actors.Jet;


/**
 * Created by julienvillegas on 17/01/2017.
 */
public class GameScreen implements Screen {

    private Stage stage;
    private Game game;
    private OrthographicCamera camera;
    private Jet[] jets;



    public GameScreen(Game aGame) {
        game = aGame;
        stage = new Stage(new ScreenViewport());
        camera = (OrthographicCamera) stage.getViewport().getCamera();

        Array<Texture> textures = new Array<Texture>();
        for(int i = 1; i <=6;i++){
            textures.add(new Texture(Gdx.files.internal("parallax/img"+i+".png")));
            textures.get(textures.size-1).setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        }

        ParallaxBackground parallaxBackground = new ParallaxBackground(textures);
        parallaxBackground.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        parallaxBackground.setSpeed(1);
        stage.addActor(parallaxBackground);

       final TextureRegion jetTexture = new TextureRegion(new Texture("data/jet.png"));
        
          jets = new Jet[10];
        
        // Create/seed our random number for positioning jets randomly
        Random random = new Random();
        
        // Create 10 Jet objects at random on screen locations
        for(int i = 0; i < 10; i++){
            jets[i] = new Jet(jetTexture);
            
            //Assign the position of the jet to a random value within the screen boundaries
            jets[i].setPosition(random.nextInt(Gdx.graphics.getWidth() - (int)jets[i].getWidth())
                    , random.nextInt(Gdx.graphics.getHeight() - (int)jets[i].getHeight()));
            jets[i].setScale(.25f);
            
            // Set the name of the Jet to it's index within the loop
            jets[i].setName(Integer.toString(i));
            
            // Add them to the stage
            stage.addActor(jets[i]);
        }
        
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }


}
