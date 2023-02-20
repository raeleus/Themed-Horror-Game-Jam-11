package com.ray3k.template.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import dev.lyze.gdxUnBox2d.Box2DGameObject;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineStageWalls.*;

public class BehaviorWalls extends BehaviourAdapter<Box2DGameObject>{
    private Box2DGameObject go;
    private static final Vector2 temp = new Vector2();
    
    public BehaviorWalls(Box2DGameObject gameObject) {
        super(gameObject);
        this.go = gameObject;
    }
    
    @Override
    public void start() {
        var ed = new EntityData(go, skeletonData, animationData, 0, 0, DEPTH_WALLS);
//        ed.bodyType = BodyType.StaticBody;
        new BehaviorEntity(go);
        ed.skeleton.getRootBone().setScale(4);
        
        var def = new FixtureDef();
        def.filter.categoryBits = CATEGORY_BOUNDS;
        
        //left
        temp.set(p2m(0), p2m(0));
        new CreateBoxFixtureBehaviour2(p2m(35f), p2m(220f), temp, def, go);
        
        temp.set(p2m(-5f), p2m(220));
        new CreateBoxFixtureBehaviour2(p2m(10f), p2m(120f), temp, def, go);
        
        temp.set(p2m(0f), p2m(340f));
        new CreateBoxFixtureBehaviour2(p2m(35f), p2m(236f), temp, def, go);
    
        //right
        temp.set(p2m(989f), p2m(0));
        new CreateBoxFixtureBehaviour2(p2m(35f), p2m(235f), temp, def, go);
    
        temp.set(p2m(1019f), p2m(235f));
        new CreateBoxFixtureBehaviour2(p2m(10f), p2m(120f), temp, def, go);
    
        temp.set(p2m(989f), p2m(355f));
        new CreateBoxFixtureBehaviour2(p2m(35f), p2m(221f), temp, def, go);
    
        //bottom
        temp.set(p2m(0), p2m(0f));
        new CreateBoxFixtureBehaviour2(p2m(460f), p2m(18f), temp, def, go);
    
        temp.set(p2m(460f), p2m(0f));
        new CreateBoxFixtureBehaviour2(p2m(160f), p2m(5f), temp, def, go);
    
        temp.set(p2m(620f), p2m(0f));
        new CreateBoxFixtureBehaviour2(p2m(404f), p2m(18f), temp, def, go);
    
        //top
        temp.set(p2m(0), p2m(494f));
        new CreateBoxFixtureBehaviour2(p2m(403f), p2m(82f), temp, def, go);
        
        temp.set(p2m(403f), p2m(566f));
        new CreateBoxFixtureBehaviour2(p2m(278f), p2m(82f), temp, def, go);
    
        temp.set(p2m(681), p2m(494f));
        new CreateBoxFixtureBehaviour2(p2m(343f), p2m(82f), temp, def, go);
    }
}
