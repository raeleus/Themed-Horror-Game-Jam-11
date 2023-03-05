package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.ray3k.template.Resources.*;
import com.ray3k.template.*;
import dev.lyze.gdxUnBox2d.BodyDefType;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import dev.lyze.gdxUnBox2d.behaviours.Box2dBehaviour;
import dev.lyze.gdxUnBox2d.behaviours.fixtures.CreateCircleFixtureBehaviour;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineTanker.*;
import static com.ray3k.template.screens.GameScreen.*;

public class BehaviorTank extends BehaviourAdapter {
    private GameObject go;
    private EntityData ed;
    public float startX;
    public float startY;
    public BehaviorTank(GameObject gameObject) {
        super(gameObject);
    }
    public float delay = 1f;
    public float timer = delay;
    private final static Vector2 temp = new Vector2();
    
    @Override
    public void start() {
        go = getGameObject();
        ed = new EntityData(go, skeletonData, animationData, startX, startY, DEPTH_ENEMIES);
        ed.animationState.setAnimation(0, animationSpawn, false);
        ed.health = 500;
        ed.score = 300;
        
        var def = new FixtureDef();
        def.filter.categoryBits = CATEGORY_CHARACTER;
        new CreateCircleFixtureBehaviour(Vector2.Zero, p2m(15), def, go);
        
        new BehaviorEntity(go);
        new BehaviorZombieMovement(go, 50);
        new BehaviorEnemy(go);
    }
    
    @Override
    public void update(float delta) {
        var body = go.getBehaviour(Box2dBehaviour.class).getBody();
        ed.skeleton.findBone("rotater").setRotation(body.getAngle() * MathUtils.radDeg);
        
        if (timer >= 0) {
            timer -= delta;
            if (timer < 0 && player != null) {
                timer = delay;
                temp.set(player.getBehaviour(Box2dBehaviour.class).getBody().getLinearVelocity());
                var angle = Utils.pointDirection(m2p(go.getBehaviour(Box2dBehaviour.class).getBody().getPosition().x), m2p(go.getBehaviour(Box2dBehaviour.class).getBody().getPosition().y), m2p(player.getBehaviour(Box2dBehaviour.class).getBody().getPosition().x), m2p(player.getBehaviour(Box2dBehaviour.class).getBody().getPosition().y));
                var bullet = new GameObject(unBox);
                new Box2dBehaviour(BodyDefType.DynamicBody, bullet);
                var bulletBehavior = new BehaviorBullet(bullet, SpineBullet.skinShell, m2p(go.getBehaviour(Box2dBehaviour.class).getBody().getPosition().x), m2p(go.getBehaviour(Box2dBehaviour.class).getBody().getPosition().y), angle, 600);
                bulletBehavior.damage = 100;
                bulletBehavior.owner = go;
            }
        }
    }
    
    @Override
    public void onDestroy() {
    }
}
