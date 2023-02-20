package com.ray3k.template.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import dev.lyze.gdxUnBox2d.Box2DGameObject;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.GameObjectState;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import dev.lyze.gdxUnBox2d.behaviours.fixtures.CreateCircleFixtureBehaviour;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.screens.GameScreen.*;
import static dev.lyze.gdxUnBox2d.box2D.BodyDefType.DynamicBody;

public class BehaviorExplosionDamage extends BehaviourAdapter<Box2DGameObject> {
    private Box2DGameObject go;
    private static final Vector2 temp = new Vector2();
    private Array<GameObject> hitObjects = new Array<>();
    public GameObject owner;
    public float damage = 15f;
    
    public BehaviorExplosionDamage(Box2DGameObject gameObject) {
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
    public void onCollisionEnter(Box2DGameObject other, Contact contact) {
        if (other != owner && !hitObjects.contains(other, true)) {
            hitObjects.add(other);
    
            var wall = other.getBehaviour(BehaviorWalls.class);
            var otherEd = other.getBehaviour(EntityData.class);
            if (wall == null && otherEd != null && otherEd.health > 0) {
                var fastMovement = other.getBehaviour(BehaviorZombieFastMovement.class);
                if (fastMovement != null) {
                    fastMovement.speed = fastMovement.speed / 2;
                }
        
                var goreSmall = new Box2DGameObject(DynamicBody, unBox);
                var goreSmallBehavior = new BehaviorGoreSmall(goreSmall);
                goreSmallBehavior.startX = m2p(other.getBody().getPosition().x);
                goreSmallBehavior.startY = m2p(other.getBody().getPosition().y);
        
                otherEd.health -= damage;
                if (otherEd.health <= 0) {
                    boolean otherDestroyed = other.getState() == GameObjectState.DESTROYED || other.getState() == GameObjectState.DESTROYING;
                    if (!otherDestroyed) other.destroy();
                    var gore = new Box2DGameObject(DynamicBody, unBox);
                    var goreBehavior = new BehaviorGore(gore);
                    goreBehavior.startX = m2p(other.getBody().getPosition().x);
                    goreBehavior.startY = m2p(other.getBody().getPosition().y);
                }
            }
        }
    }
}
