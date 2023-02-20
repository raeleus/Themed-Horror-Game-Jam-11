package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ray3k.template.*;
import com.ray3k.template.entities.*;
import com.ray3k.template.screens.DialogDebug.*;
import com.ray3k.template.screens.DialogPause.*;
import dev.lyze.gdxUnBox2d.Box2DGameObject;
import dev.lyze.gdxUnBox2d.Box2DUnBox;
import dev.lyze.gdxUnBox2d.UnBox;
import space.earlygrey.shapedrawer.ShapeDrawer;

import static com.ray3k.template.Core.*;
import static dev.lyze.gdxUnBox2d.box2D.BodyDefType.DynamicBody;
import static dev.lyze.gdxUnBox2d.box2D.BodyDefType.StaticBody;

public class GameScreen extends JamScreen {
    public static GameScreen gameScreen;
    public static final Color BG_COLOR = new Color();
    public Stage stage;
    public boolean paused;
    private Label fpsLabel;
    public static Label statsLabel;
    public static Box2DUnBox unBox;
    public static Box2DGameObject player;
    public static int totalScore;
    public static int barScore;
    
    @Override
    public void show() {
        super.show();
        totalScore = 0;
        barScore = 1000;
    
        gameScreen = this;
        BG_COLOR.set(Color.BLACK);
    
        paused = false;
    
        stage = new Stage(new ScreenViewport(), batch);
        
        var root = new Table();
        root.setFillParent(true);
        root.align(Align.bottomLeft);
        root.pad(10);
        stage.addActor(root);
        
        statsLabel = new Label("", skin);
        root.add(statsLabel).left();
        
        root.row();
        fpsLabel = new Label("test", skin);
        root.add(fpsLabel).left();
        
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (!paused && keycode == Keys.ESCAPE) {
                    paused = true;
                
                    DialogPause dialogPause = new DialogPause(GameScreen.this);
                    dialogPause.show(stage);
                    dialogPause.addListener(new PauseListener() {
                        @Override
                        public void resume() {
                            paused = false;
                        }
                    
                        @Override
                        public void quit() {
                            core.transition(new MenuScreen());
                        }
                    });
                }
                return super.keyDown(event, keycode);
            }
        });
    
        stage.addListener(new DebugListener());
    
        shapeDrawer = new ShapeDrawer(batch, skin.getRegion("white"));
        shapeDrawer.setPixelSize(.5f);
    
        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(inputMultiplexer);
    
        camera = new OrthographicCamera();
        camera.zoom = 1;
        camera.position.set(512, 288, 0);
        viewport = new FitViewport(1024, 576, camera);
    
        debugShapeDrawer = new Box2DDebugShapeDrawer(shapeDrawer);
        unBox = new Box2DUnBox(new Vector2(0, 0), true);
        
        var floor = new Box2DGameObject(DynamicBody, unBox);
        new BehaviorFloor(floor);
        
        var walls = new Box2DGameObject(StaticBody, unBox);
        new BehaviorWalls(walls);
        
        player = new Box2DGameObject(DynamicBody, unBox);
        new BehaviorPlayer(player);
        
        var spawner = new Box2DGameObject(DynamicBody, unBox);
        new BehaviorSpawnerSpawner(spawner);
        
        var bigMoney = new Box2DGameObject(DynamicBody, unBox);
        new BehaviorRed(bigMoney);
    }
    
    @Override
    public void act(float delta) {
        if (!paused) {
            vfxManager.update(delta);
        }
        stage.act(delta);
        
//        statsLabel.setText(mouseX + " " + mouseY);
        statsLabel.setText("Score: " + totalScore + "\nHighScore: " + preferences.getInteger("high", totalScore));
        fpsLabel.setText(Gdx.graphics.getFramesPerSecond());
    }
    
    @Override
    public void draw(float delta) {
        unBox.preRender(Gdx.graphics.getDeltaTime());
        batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
        batch.setColor(Color.WHITE);
        vfxManager.cleanUpBuffers();
        vfxManager.beginInputCapture();
        Gdx.gl.glClearColor(BG_COLOR.r, BG_COLOR.g, BG_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
        unBox.render(batch);
        shapeDrawer.setDefaultLineWidth(2f);
//        debugShapeDrawer.render(unBox.getWorld());
        batch.end();
        vfxManager.endInputCapture();
        vfxManager.applyEffects();
        vfxManager.renderToScreen();
        unBox.postRender();
    
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        stage.draw();
    }
    
    @Override
    public void resize(int width, int height) {
        if (width + height != 0) {
            vfxManager.resize(width, height);
            viewport.update(width, height);
        
            stage.getViewport().update(width, height, true);
        }
    }
    
    @Override
    public void dispose() {
    }
    
    @Override
    public void hide() {
        super.hide();
        vfxManager.removeAllEffects();
    }
    
    @Override
    public void pause() {
    
    }
    
    @Override
    public void resume() {
    
    }
}
