package com.ray3k.template.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import dev.lyze.gdxUnBox2d.Behaviour;
import dev.lyze.gdxUnBox2d.BodyDefType;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.GameObjectState;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import dev.lyze.gdxUnBox2d.behaviours.Box2dBehaviour;
import dev.lyze.gdxUnBox2d.behaviours.fixtures.CreateCircleFixtureBehaviour;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.screens.GameScreen.*;

public class BehaviorExplosionDamage extends BehaviourAdapter {
    private GameObject go;
    private static final Vector2 temp = new Vector2();
    private Array<GameObject> hitObjects = new Array<>();
    public GameObject owner;
    public float damage = 15f;
    
    public BehaviorExplosionDamage(GameObject gameObject) {
        super(gameObject);
        go = gameObject;
    }
    
    @Override
    public void start() {
        var def = new FixtureDef();
        def.isSensor = true;
        def.filter.categoryBits = CATEGORY_BULLET;
        def.filter.maskBits = CATEGORY_CHARACTER | CATEGORY_BOUNDS;
        new CreateCircleFixtureBehaviour(Vector2.Zero, p2m(30), def, go);
    }
    
    @Override
    public void onCollisionEnter(Behaviour other, Contact contact) {
        if (other.getGameObject() != owner && !hitObjects.contains(other.getGameObject(), true)) {
            hitObjects.add(other.getGameObject());
    
            var wall = other.getGameObject().getBehaviour(BehaviorWalls.class);
            var otherEd = other.getGameObject().getBehaviour(EntityData.class);
            if (wall == null && otherEd != null && otherEd.health > 0) {
                var fastMovement = other.getGameObject().getBehaviour(BehaviorZombieFastMovement.class);
                if (fastMovement != null) {
                    fastMovement.speed = fastMovement.speed / 2;
                }
        
                var goreSmall = new GameObject(unBox);
                new Box2dBehaviour(BodyDefType.DynamicBody, goreSmall);
                var goreSmallBehavior = new BehaviorGoreSmall(goreSmall);
                goreSmallBehavior.startX = m2p(other.getGameObject().getBehaviour(Box2dBehaviour.class).getBody().getPosition().x);
                goreSmallBehavior.startY = m2p(other.getGameObject().getBehaviour(Box2dBehaviour.class).getBody().getPosition().y);
        
                otherEd.health -= damage;
                if (otherEd.health <= 0) {
                    boolean otherDestroyed = other.getGameObject().getState() == GameObjectState.DESTROYED || other.getGameObject().getState() == GameObjectState.DESTROYING;
                    if (!otherDestroyed) other.getGameObject().destroy();
                    var gore = new GameObject(unBox);
                    new Box2dBehaviour(BodyDefType.DynamicBody, gore);
                    var goreBehavior = new BehaviorGore(gore);
                    goreBehavior.startX = m2p(other.getGameObject().getBehaviour(Box2dBehaviour.class).getBody().getPosition().x);
                    goreBehavior.startY = m2p(other.getGameObject().getBehaviour(Box2dBehaviour.class).getBody().getPosition().y);
                }
            }
        }
    }
}
