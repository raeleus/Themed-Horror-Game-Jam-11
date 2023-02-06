package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.esotericsoftware.spine.*;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import dev.lyze.gdxUnBox2d.behaviours.fixtures.CreateCircleFixtureBehaviour;

import static com.ray3k.template.Core.*;

public class BehaviorEntity extends BehaviourAdapter {
    public Skeleton skeleton;
    public SkeletonBounds skeletonBounds;
    public AnimationState animationState;
    public GameObject gameObject;
    private final static Vector2 temp = new Vector2();
    private float startX;
    private float startY;
    
    public BehaviorEntity(GameObject gameObject, SkeletonData skeletonData, AnimationStateData animationStateData, float startX, float startY) {
        super(gameObject);
        this.gameObject = gameObject;
        skeleton = new Skeleton(skeletonData);
        skeletonBounds = new SkeletonBounds();
        animationState = new AnimationState(animationStateData);
        this.startX = startX;
        this.startY = startY;
    }
    
    @Override
    public void start() {
        var body = gameObject.getBody();
        body.setTransform(p2m(startX), p2m(startY), (0));
        body.setFixedRotation(true);
        body.setType(BodyType.DynamicBody);
    }
    
    public Fixture setCollisionBox(float offsetX, float offsetY, float width, float height) {
        PolygonShape box = new PolygonShape();
        temp.set(p2m(offsetX + width / 2), p2m(offsetY + height / 2));
        box.setAsBox(p2m(width / 2), p2m(height / 2), temp, 0);
        
        var fixture = gameObject.getBody().createFixture(box, .5f);
        box.dispose();
        
        return fixture;
    }
    
    public Fixture setCollisionCircle(float centerX, float centerY, float radius) {
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(p2m(radius));
        temp.set(p2m(centerX), p2m(centerY));
        circleShape.setPosition(temp);
        
        var fixture = gameObject.getBody().createFixture(circleShape, .5f);
        circleShape.dispose();
        
        return fixture;
    }
    
    @Override
    public void update(float delta) {
        var body = gameObject.getBody();
        temp.set(body.getPosition());
        skeleton.setPosition(m2p(temp.x), m2p(temp.y));
        animationState.update(delta);
        skeleton.updateWorldTransform();
        animationState.apply(skeleton);
        skeletonBounds.update(skeleton, true);
    }
    
    @Override
    public void render(Batch batch) {
        skeletonRenderer.draw(batch, skeleton);
    }
}
