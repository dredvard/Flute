package com.kambul.flute.Actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class Jet extends Actor {
    private TextureRegion _texture;
    
    public Jet(TextureRegion texture){
        _texture = texture;
        setBounds(getX(),getY(),_texture.getRegionWidth(), _texture.getRegionHeight());
        
        this.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons){
                System.out.println("Touched" + getName());

                
                SequenceAction sequenceAction = new SequenceAction();

                MoveToAction moveAction = new MoveToAction();
                RotateToAction rotateAction = new RotateToAction();
                ScaleToAction scaleAction = new ScaleToAction(); 

                moveAction.setPosition(300f, 0f);
                moveAction.setDuration(5f);
                rotateAction.setRotation(90f);
                rotateAction.setDuration(5f);
                scaleAction.setScale(0.15f);
                scaleAction.setDuration(5f);

                sequenceAction.addAction(scaleAction);
                sequenceAction.addAction(rotateAction);
                sequenceAction.addAction(moveAction);


                addAction(sequenceAction);
                
                return true;
            }
        });
    }

    // Implement the full form of draw() so we can handle rotation and scaling.
    public void draw(Batch batch, float alpha){
        batch.draw(_texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                getScaleX(), getScaleY(), getRotation());
    }
    
    // This hit() instead of checking against a bounding box, checks a bounding circle.
    public Actor hit(float x, float y, boolean touchable){
        // If this Actor is hidden or untouchable, it cant be hit
        if(!this.isVisible() || this.getTouchable() == Touchable.disabled)
            return null;
        
        // Get centerpoint of bounding circle, also known as the center of the rect
        float centerX = getWidth()/2;
        float centerY = getHeight()/2;
        
        // Square roots are bad m'kay. In "real" code, simply square both sides for much speedy fastness
        // This however is the proper, unoptimized and easiest to grok equation for a hit within a circle
        // You could of course use LibGDX's Circle class instead.
        
        // Calculate radius of circle
        float radius = (float) Math.sqrt(centerX * centerX +
                centerY * centerY);

        // And distance of point from the center of the circle
        float distance = (float) Math.sqrt(((centerX - x) * (centerX - x)) 
                + ((centerY - y) * (centerY - y)));
        
        // If the distance is less than the circle radius, it's a hit
        if(distance <= radius) return this;
        
        // Otherwise, it isnt
        return null;
    }
    
    
}