package com.ray3k.template.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.esotericsoftware.spine.Skin;
import com.ray3k.template.*;
import com.ray3k.template.Resources.*;
import com.ray3k.template.screens.*;
import dev.lyze.gdxUnBox2d.BehaviourState;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import dev.lyze.gdxUnBox2d.behaviours.fixtures.CreateCircleFixtureBehaviour;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.screens.GameScreen.*;

public class BehaviorBullet extends BehaviourAdapter {
    private GameObject go;
    private EntityData ed;
    private Skin skin;
    private float startX;
    private float startY;
    private float direction;
    private float speed;
    public float addDeltaX;
    public float addDeltaY;
    public Class owner;
    private static final Vector2 temp = new Vector2();
    public float damage = 10f;
    
    public BehaviorBullet(GameObject gameObject, Skin skin, float startX, float startY, float direction, float speed) {
        super(gameObject);
        go = gameObject;
        this.skin = skin;
        this.startX = startX;
        this.startY = startY;
        this.direction = direction;
        this.speed = speed;
    }
    
    @Override
    public void start() {
        ed = new EntityData(go, SpineBullet.skeletonData, SpineBullet.animationData, startX, startY);
        ed.skeleton.setSkin(skin);
        new BehaviorEntity(go);
        var def = new FixtureDef();
        def.filter.categoryBits = CATEGORY_BULLET;
        def.filter.maskBits = CATEGORY_CHARACTER | CATEGORY_BOUNDS;
        new CreateCircleFixtureBehaviour(Vector2.Zero, p2m(5), def, go);
        
        temp.set(p2m(speed), 0);
        temp.rotateDeg(direction);
        go.getBody().setLinearVelocity(temp.x + p2m(addDeltaX), temp.y + p2m(addDeltaY));
    }
    
    @Override
    public void update(float delta) {
        ed.skeleton.getRootBone().setRotation(go.getBody().getLinearVelocity().angleDeg());
    }
    
    @Override
    public boolean onCollisionPreSolve(GameObject other, Contact contact, Manifold oldManifold) {
        if (other.getBehaviour(owner) != null) {
            contact.setEnabled(false);
        }
        return false;
    }
    
    @Override
    public void onCollisionEnter(GameObject other, Contact contact) {
        boolean destroyed = getState() == BehaviourState.DESTROYED || getState() == BehaviourState.DESTROYING;
        if (other.getBehaviour(BehaviorWalls.class) != null) {
            if (!destroyed) go.destroy();
        } else {
            var otherEd = other.getBehaviour(EntityData.class);
            if (otherEd != null && other.getBehaviour(owner) == null && otherEd.health > 0) {
                if (!destroyed) go.destroy();
                
                var fastMovement = other.getBehaviour(BehaviorZombieFastMovement.class);
                if (fastMovement != null) {
                    fastMovement.speed = fastMovement.speed / 2;
                }
    
                var goreSmall = new GameObject(unBox);
                var goreSmallBehavior = new BehaviorGoreSmall(goreSmall);
                goreSmallBehavior.startX = m2p(go.getBody().getPosition().x);
                goreSmallBehavior.startY = m2p(go.getBody().getPosition().y);
                
                otherEd.health -= damage;
                if (otherEd.health <= 0) {
                    other.destroy();
                    var gore = new GameObject(unBox);
                    var goreBehavior = new BehaviorGore(gore);
                    goreBehavior.startX = m2p(go.getBody().getPosition().x);
                    goreBehavior.startY = m2p(go.getBody().getPosition().y);
                    
//                    var blood = new GameObject(unBox);
//                    var bloodBehavior = new BehaviorBlood(blood);
//                    bloodBehavior.startX = goreBehavior.startX;
//                    bloodBehavior.startY = goreBehavior.startY;
                }
            }
        }
    }
}
