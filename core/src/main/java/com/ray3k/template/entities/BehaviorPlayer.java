package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.ray3k.template.screens.*;
import dev.lyze.gdxUnBox2d.BehaviourState;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.UnBox;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import dev.lyze.gdxUnBox2d.behaviours.fixtures.CreateCircleFixtureBehaviour;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;
import static com.ray3k.template.Resources.SpinePlayer.*;
import static com.ray3k.template.screens.GameScreen.*;

public class BehaviorPlayer extends BehaviourAdapter {
    private GameObject go;
    private EntityData ed;
    public BehaviorPlayer(GameObject gameObject) {
        super(gameObject);
    }
    
    @Override
    public void start() {
        go = getGameObject();
        ed = new EntityData(go, skeletonData, animationData, 512, 288);
    
        var def = new FixtureDef();
        def.filter.categoryBits = CATEGORY_CHARACTER;
        new CreateCircleFixtureBehaviour(Vector2.Zero, p2m(10), def, go);
        
        new BehaviorEntity(go);
        new BehaviorKeyboardMovement(go, 300);
        new BehaviorKeyboardShooting(go);
    }
    
    @Override
    public void update(float delta) {
        var body = go.getBody();
        ed.skeleton.getRootBone().setRotation(body.getAngle() * MathUtils.radDeg);
    }
    
    @Override
    public void onCollisionEnter(GameObject other, Contact contact) {
        if (other.getBehaviour(BehaviorEnemy.class) != null) {
            boolean destroyed = getState() == BehaviourState.DESTROYED || getState() == BehaviourState.DESTROYING;
            if (!destroyed) {
                go.destroy();
            }
        }
    }
    
    @Override
    public void lateUpdate(float delta) {
    
    }
    
    @Override
    public void onDestroy() {
        player = null;
        var restarter = new GameObject(unBox);
        new BehaviorRestarter(restarter);
        
        switch (MathUtils.random(2)) {
            case 0:
                sfx_playerHurt01.play(sfx);
                break;
            case 1:
                sfx_playerHurt02.play(sfx);
                break;
            case 2:
                sfx_playerHurt03.play(sfx);
                break;
        }
    }
}
