package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.esotericsoftware.spine.Skin;
import com.ray3k.template.*;
import com.ray3k.template.Resources.*;
import dev.lyze.gdxUnBox2d.*;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import dev.lyze.gdxUnBox2d.behaviours.Box2dBehaviour;
import dev.lyze.gdxUnBox2d.behaviours.fixtures.CreateCircleFixtureBehaviour;

import javax.swing.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;
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
    public GameObject owner;
    private static final Vector2 temp = new Vector2();
    public float damage = 10f;
    public float lifeSpan = -1f;
    public float lifeTimer;
    public boolean createExplosion;
    public float explosionDamage = 15f;
    public boolean homing;
    
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
        lifeTimer = lifeSpan;
        ed = new EntityData(go, SpineBullet.skeletonData, SpineBullet.animationData, startX, startY, DEPTH_BULLETS);
        ed.skeleton.setSkin(skin);
        new BehaviorEntity(go);
        var def = new FixtureDef();
        def.filter.categoryBits = CATEGORY_BULLET;
        def.filter.maskBits = CATEGORY_CHARACTER | CATEGORY_BOUNDS;
        new CreateCircleFixtureBehaviour(Vector2.Zero, p2m(5), def, go);
        
        temp.set(p2m(speed), 0);
        temp.rotateDeg(direction);
        go.getBehaviour(Box2dBehaviour.class).getBody().setLinearVelocity(temp.x + p2m(addDeltaX), temp.y + p2m(addDeltaY));
    }
    
    @Override
    public void update(float delta) {
        ed.skeleton.getRootBone().setRotation(go.getBehaviour(Box2dBehaviour.class).getBody().getLinearVelocity().angleDeg());
        
        if (homing) {
            BehaviorEnemy closest = null;
            var closestDistance = Float.MAX_VALUE;
            float x = m2p(getGameObject().getBehaviour(Box2dBehaviour.class).getBody().getPosition().x);
            float y = m2p(getGameObject().getBehaviour(Box2dBehaviour.class).getBody().getPosition().y);
            float direction = go.getBehaviour(Box2dBehaviour.class).getBody().getLinearVelocity().angleDeg();
            for (var enemy : unBox.findBehaviours(BehaviorEnemy.class)) {
                float enemyX = m2p(enemy.getGameObject().getBehaviour(Box2dBehaviour.class).getBody().getPosition().x);
                float enemyY = m2p(enemy.getGameObject().getBehaviour(Box2dBehaviour.class).getBody().getPosition().y);
                var distance = Utils.pointDistance(enemyX, enemyY, x, y);
                var directionToEnemy = Utils.pointDirection(x, y, enemyX, enemyY);
    
                if (Utils.degInRange(directionToEnemy, direction - 45, direction + 45) && distance < closestDistance) {
                    closest = enemy;
                    closestDistance = distance;
                }
            }
            
            if (closest != null) {
                temp.set(p2m(speed), 0);
                float targetX = m2p(closest.getGameObject().getBehaviour(Box2dBehaviour.class).getBody().getPosition().x);
                float targetY = m2p(closest.getGameObject().getBehaviour(Box2dBehaviour.class).getBody().getPosition().y);
                float enemyDirection = Utils.pointDirection(x, y, targetX, targetY);
                direction = Utils.approach360(direction, enemyDirection, 500f * delta);
                temp.rotateDeg(direction);
                go.getBehaviour(Box2dBehaviour.class).getBody().setLinearVelocity(temp);
            }
        }
        
        if (lifeTimer > 0) {
            lifeTimer -= delta;
            if (lifeTimer <= 0) {
                boolean destroyed = getState() == BehaviourState.DESTROYED || getState() == BehaviourState.DESTROYING;
                if (!destroyed) go.destroy();
            }
        }
    }
    
    @Override
    public boolean onCollisionPreSolve(Behaviour other, Contact contact, Manifold oldManifold) {
        if (other.getGameObject() == owner) {
            contact.setEnabled(false);
        }
        return false;
    }
    
    @Override
    public void onCollisionEnter(Behaviour other, Contact contact) {
        boolean destroyed = getState() == BehaviourState.DESTROYED || getState() == BehaviourState.DESTROYING;
        if (other.getGameObject().getBehaviour(BehaviorWalls.class) != null) {
            if (!destroyed) go.destroy();
        } else {
            var otherEd = other.getGameObject().getBehaviour(EntityData.class);
            if (otherEd != null && other.getGameObject() != owner && otherEd.health > 0) {
                if (!destroyed) go.destroy();
                
                var fastMovement = other.getGameObject().getBehaviour(BehaviorZombieFastMovement.class);
                if (fastMovement != null) {
                    fastMovement.speed = fastMovement.speed / 2;
                }
    
                var goreSmall = new GameObject(unBox);
                new Box2dBehaviour(BodyDefType.DynamicBody, goreSmall);
                var goreSmallBehavior = new BehaviorGoreSmall(goreSmall);
                goreSmallBehavior.startX = m2p(go.getBehaviour(Box2dBehaviour.class).getBody().getPosition().x);
                goreSmallBehavior.startY = m2p(go.getBehaviour(Box2dBehaviour.class).getBody().getPosition().y);
                
                otherEd.health -= damage;
                if (otherEd.health <= 0) {
                    boolean otherDestroyed = other.getGameObject().getState() == GameObjectState.DESTROYED || other.getGameObject().getState() == GameObjectState.DESTROYING;
                    if (!otherDestroyed) other.getGameObject().destroy();
                    var gore = new GameObject(unBox);
                    new Box2dBehaviour(BodyDefType.DynamicBody, gore);
                    var goreBehavior = new BehaviorGore(gore);
                    goreBehavior.startX = m2p(go.getBehaviour(Box2dBehaviour.class).getBody().getPosition().x);
                    goreBehavior.startY = m2p(go.getBehaviour(Box2dBehaviour.class).getBody().getPosition().y);
                }
            }
        }
    }
    
    @Override
    public void onDestroy() {
        if (createExplosion) {
            var explosion = new GameObject(unBox);
            new Box2dBehaviour(BodyDefType.DynamicBody, explosion);
            var explosionBehavior = new BehaviorExplosion(explosion);
            explosionBehavior.startX = m2p(go.getBehaviour(Box2dBehaviour.class).getBody().getPosition().x);
            explosionBehavior.startY = m2p(go.getBehaviour(Box2dBehaviour.class).getBody().getPosition().y);
            var explosionDamageBehavior = new BehaviorExplosionDamage(explosion);
            explosionDamageBehavior.owner = owner;
            explosionDamageBehavior.damage = explosionDamage;
    
            switch (MathUtils.random(2)) {
                case 0:
                    sfx_explosion01.play(sfx * .1f);
                    break;
                case 1:
                    sfx_explosion02.play(sfx * .1f);
                    break;
                case 2:
                    sfx_explosion03.play(sfx * .1f);
                    break;
            }
        }
    }
}
