package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.ray3k.template.screens.*;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import dev.lyze.gdxUnBox2d.behaviours.Box2dBehaviour;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.screens.GameScreen.*;

public class BehaviorEntity extends BehaviourAdapter {
    public GameObject go;
    public EntityData ed;
    private final static Vector2 temp = new Vector2();
    
    public BehaviorEntity(GameObject gameObject) {
        super(gameObject);
    }
    
    @Override
    public void start() {
        go = getGameObject();
        ed = go.getBehaviour(EntityData.class);
        var body = go.getBehaviour(Box2dBehaviour.class).getBody();
        body.setTransform(p2m(ed.startX), p2m(ed.startY), (0));
        body.setFixedRotation(true);
        setRenderOrder(ed.depth);
    }
    
    @Override
    public void update(float delta) {
        var body = go.getBehaviour(Box2dBehaviour.class).getBody();
        temp.set(body.getPosition());
        ed.skeleton.setPosition(m2p(temp.x), m2p(temp.y));
        ed.animationState.update(delta);
        ed.skeleton.updateWorldTransform();
        ed.animationState.apply(ed.skeleton);
        ed.skeletonBounds.update(ed.skeleton, true);
    }
    
    @Override
    public void render(Batch batch) {
        skeletonRenderer.draw(batch, ed.skeleton);
    }
    
    @Override
    public void onDestroy() {
        if (player != null) {
            totalScore += ed.score;
            if (totalScore > barScore) {
                barScore += 1000;
                var mo = player.getBehaviour(BehaviorKeyboardShooting.class);
                mo.changeWeapon();
            }
            if (totalScore > preferences.getInteger("high", 0)) {
                preferences.putInteger("high", totalScore);
                preferences.flush();
            }
        }
    }
}
