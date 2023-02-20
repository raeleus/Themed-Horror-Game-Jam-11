package com.ray3k.template.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import dev.lyze.gdxUnBox2d.Box2DGameObject;
import dev.lyze.gdxUnBox2d.behaviours.fixtures.CreateBoxFixtureBehaviour;

public class CreateBoxFixtureBehaviour2 extends CreateBoxFixtureBehaviour {
    public CreateBoxFixtureBehaviour2(float width, float height, Box2DGameObject gameObject) {
        super(width / 2, height / 2, gameObject);
    }
    
    public CreateBoxFixtureBehaviour2(float width, float height, Vector2 position,
                                      Box2DGameObject gameObject) {
        super(width / 2, height / 2, position.add(width / 2, height / 2), gameObject);
    }
    
    public CreateBoxFixtureBehaviour2(float width, float height, Vector2 position,
                                      FixtureDef fixtureDef,
                                      Box2DGameObject gameObject) {
        super(width / 2, height / 2, position.add(width / 2, height / 2), fixtureDef, gameObject);
    }
}
