package com.ray3k.template.entities;

import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.UnBox;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

import static com.ray3k.template.screens.GameScreen.*;

public class BehaviorSpawnerSpawner extends BehaviourAdapter {
    private GameObject go;
    private float timeline;
    private int event;
    
    public BehaviorSpawnerSpawner(GameObject gameObject) {
        super(gameObject);
        this.go = gameObject;
    }
    
    @Override
    public void start() {
    
    }
    
    @Override
    public void update(float delta) {
        timeline += delta;
        
        var eventCounter = 0;
        if (timeline > 3 && event == eventCounter++) {
            var spawner = new GameObject(unBox);
            new BehaviorZombieSpawner(spawner);
            event++;
        } else if (timeline > 10 && event == eventCounter++) {
            var spawner = new GameObject(unBox);
            new BehaviorZombieSpawner(spawner);
            event++;
        } else if (timeline > 15 && event == eventCounter++) {
            var spawner = new GameObject(unBox);
            new BehaviorZombieSpawner(spawner);
            event++;
        } else if (timeline > 25 && event == eventCounter++) {
            var spawner = new GameObject(unBox);
            new BehaviorZombieFastSpawner(spawner);
            event++;
        } else if (timeline > 35 && event == eventCounter++) {
            var spawner = new GameObject(unBox);
            new BehaviorBomberSpawner(spawner);
            event++;
        } else if (timeline > 45 && event == eventCounter++) {
            var spawner = new GameObject(unBox);
            new BehaviorZombieSpawner(spawner);
            event++;
        } else if (timeline > 50 && event == eventCounter++) {
            var spawner = new GameObject(unBox);
            new BehaviorZombieSpawner(spawner);
            event++;
        } else if (timeline > 55 && event == eventCounter++) {
            var spawner = new GameObject(unBox);
            new BehaviorZombieFastSpawner(spawner);
            event++;
        } else if (timeline > 60 && event == eventCounter++) {
            var spawner = new GameObject(unBox);
            new BehaviorZombieFastSpawner(spawner);
            event++;
        }
    }
}
