package com.ray3k.template;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.BoneData;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SlotData;
import java.lang.String;

public class Resources {
    public static TextureAtlas textures_textures;

    public static Skin skin_skin;

    public static Sound sfx_beam;

    public static Sound sfx_bigMoney;

    public static Sound sfx_buyThatForADollar;

    public static Sound sfx_enemyGun;

    public static Sound sfx_enemyHurt01;

    public static Sound sfx_enemyHurt02;

    public static Sound sfx_enemyHurt03;

    public static Sound sfx_explosion01;

    public static Sound sfx_explosion02;

    public static Sound sfx_explosion03;

    public static Sound sfx_gun;

    public static Sound sfx_money;

    public static Sound sfx_ping01;

    public static Sound sfx_ping02;

    public static Sound sfx_ping03;

    public static Sound sfx_playerHurt01;

    public static Sound sfx_playerHurt02;

    public static Sound sfx_playerHurt03;

    public static Sound sfx_rocket;

    public static Music bgm_audioSample;

    public static Music bgm_enemyGun;

    public static Music bgm_menu;

    public static void loadResources(AssetManager assetManager) {
        textures_textures = assetManager.get("textures/textures.atlas");
        SpineBeam.skeletonData = assetManager.get("spine/beam.json");
        SpineBeam.animationData = assetManager.get("spine/beam.json-animation");
        SpineBeam.animationAnimation = SpineBeam.skeletonData.findAnimation("animation");
        SpineBeam.boneRoot = SpineBeam.skeletonData.findBone("root");
        SpineBeam.slotGameBeam = SpineBeam.skeletonData.findSlot("game/beam");
        SpineBeam.skinDefault = SpineBeam.skeletonData.findSkin("default");
        SpineBlood.skeletonData = assetManager.get("spine/blood.json");
        SpineBlood.animationData = assetManager.get("spine/blood.json-animation");
        SpineBlood.animationAnimation = SpineBlood.skeletonData.findAnimation("animation");
        SpineBlood.boneRoot = SpineBlood.skeletonData.findBone("root");
        SpineBlood.boneBone = SpineBlood.skeletonData.findBone("bone");
        SpineBlood.slotGameBlood01 = SpineBlood.skeletonData.findSlot("game/blood-01");
        SpineBlood.skinBlood01 = SpineBlood.skeletonData.findSkin("blood01");
        SpineBlood.skinBlood02 = SpineBlood.skeletonData.findSkin("blood02");
        SpineBlood.skinBlood03 = SpineBlood.skeletonData.findSkin("blood03");
        SpineBlood.skinBlood04 = SpineBlood.skeletonData.findSkin("blood04");
        SpineBomber.skeletonData = assetManager.get("spine/bomber.json");
        SpineBomber.animationData = assetManager.get("spine/bomber.json-animation");
        SpineBomber.animationShake = SpineBomber.skeletonData.findAnimation("shake");
        SpineBomber.animationSpark = SpineBomber.skeletonData.findAnimation("spark");
        SpineBomber.animationSpawn = SpineBomber.skeletonData.findAnimation("spawn");
        SpineBomber.boneRoot = SpineBomber.skeletonData.findBone("root");
        SpineBomber.boneGameBomber = SpineBomber.skeletonData.findBone("game/bomber");
        SpineBomber.slotGameBomber = SpineBomber.skeletonData.findSlot("game/bomber");
        SpineBomber.slotGameSparkLayer2 = SpineBomber.skeletonData.findSlot("game/spark-Layer-2");
        SpineBomber.skinDefault = SpineBomber.skeletonData.findSkin("default");
        SpineBoss.skeletonData = assetManager.get("spine/boss.json");
        SpineBoss.animationData = assetManager.get("spine/boss.json-animation");
        SpineBoss.animationStage0 = SpineBoss.skeletonData.findAnimation("stage-0");
        SpineBoss.animationStage1 = SpineBoss.skeletonData.findAnimation("stage-1");
        SpineBoss.animationStage2 = SpineBoss.skeletonData.findAnimation("stage-2");
        SpineBoss.animationStage3 = SpineBoss.skeletonData.findAnimation("stage-3");
        SpineBoss.animationStage4 = SpineBoss.skeletonData.findAnimation("stage-4");
        SpineBoss.boneRoot = SpineBoss.skeletonData.findBone("root");
        SpineBoss.boneGameBossHead = SpineBoss.skeletonData.findBone("game/boss-head");
        SpineBoss.boneMuzzle = SpineBoss.skeletonData.findBone("muzzle");
        SpineBoss.boneRotater = SpineBoss.skeletonData.findBone("rotater");
        SpineBoss.slotRotater = SpineBoss.skeletonData.findSlot("rotater");
        SpineBoss.slotGameBossHand = SpineBoss.skeletonData.findSlot("game/boss-hand");
        SpineBoss.slotGameBossHand2 = SpineBoss.skeletonData.findSlot("game/boss-hand2");
        SpineBoss.slotGameBossBody = SpineBoss.skeletonData.findSlot("game/boss-body");
        SpineBoss.slotGameBossHead = SpineBoss.skeletonData.findSlot("game/boss-head");
        SpineBoss.skinDefault = SpineBoss.skeletonData.findSkin("default");
        SpineBullet.skeletonData = assetManager.get("spine/bullet.json");
        SpineBullet.animationData = assetManager.get("spine/bullet.json-animation");
        SpineBullet.animationAnimation = SpineBullet.skeletonData.findAnimation("animation");
        SpineBullet.boneRoot = SpineBullet.skeletonData.findBone("root");
        SpineBullet.slotGameRocketTrailLayer2 = SpineBullet.skeletonData.findSlot("game/rocket-trail-Layer-2");
        SpineBullet.slotGameBulletRed = SpineBullet.skeletonData.findSlot("game/bullet-red");
        SpineBullet.skinGrenade = SpineBullet.skeletonData.findSkin("grenade");
        SpineBullet.skinRed = SpineBullet.skeletonData.findSkin("red");
        SpineBullet.skinRocket = SpineBullet.skeletonData.findSkin("rocket");
        SpineBullet.skinShell = SpineBullet.skeletonData.findSkin("shell");
        SpineBullet.skinWhite = SpineBullet.skeletonData.findSkin("white");
        SpineBullet.skinYellow = SpineBullet.skeletonData.findSkin("yellow");
        SpineDollar.skeletonData = assetManager.get("spine/dollar.json");
        SpineDollar.animationData = assetManager.get("spine/dollar.json-animation");
        SpineDollar.animationAnimation = SpineDollar.skeletonData.findAnimation("animation");
        SpineDollar.boneRoot = SpineDollar.skeletonData.findBone("root");
        SpineDollar.slotDollar = SpineDollar.skeletonData.findSlot("dollar");
        SpineDollar.skinDollar = SpineDollar.skeletonData.findSkin("dollar");
        SpineDollar.skinGem = SpineDollar.skeletonData.findSkin("gem");
        SpineDollar.skinRuby = SpineDollar.skeletonData.findSkin("ruby");
        SpineExplosion.skeletonData = assetManager.get("spine/explosion.json");
        SpineExplosion.animationData = assetManager.get("spine/explosion.json-animation");
        SpineExplosion.animationAnimation = SpineExplosion.skeletonData.findAnimation("animation");
        SpineExplosion.boneRoot = SpineExplosion.skeletonData.findBone("root");
        SpineExplosion.slotGameExplosionLayer1 = SpineExplosion.skeletonData.findSlot("game/explosion-Layer-1");
        SpineExplosion.skinDefault = SpineExplosion.skeletonData.findSkin("default");
        SpineGoreSmall.skeletonData = assetManager.get("spine/gore-small.json");
        SpineGoreSmall.animationData = assetManager.get("spine/gore-small.json-animation");
        SpineGoreSmall.animationAnimation = SpineGoreSmall.skeletonData.findAnimation("animation");
        SpineGoreSmall.boneRoot = SpineGoreSmall.skeletonData.findBone("root");
        SpineGoreSmall.slotGameGoreLayer1 = SpineGoreSmall.skeletonData.findSlot("game/gore-Layer-1");
        SpineGoreSmall.skinDefault = SpineGoreSmall.skeletonData.findSkin("default");
        SpineGore.skeletonData = assetManager.get("spine/gore.json");
        SpineGore.animationData = assetManager.get("spine/gore.json-animation");
        SpineGore.animationAnimation = SpineGore.skeletonData.findAnimation("animation");
        SpineGore.boneRoot = SpineGore.skeletonData.findBone("root");
        SpineGore.slotGameGoreLayer1 = SpineGore.skeletonData.findSlot("game/gore-Layer-1");
        SpineGore.skinDefault = SpineGore.skeletonData.findSkin("default");
        SpineGunFlare.skeletonData = assetManager.get("spine/gun-flare.json");
        SpineGunFlare.animationData = assetManager.get("spine/gun-flare.json-animation");
        SpineGunFlare.animationAnimation = SpineGunFlare.skeletonData.findAnimation("animation");
        SpineGunFlare.boneRoot = SpineGunFlare.skeletonData.findBone("root");
        SpineGunFlare.boneGameGunFlare = SpineGunFlare.skeletonData.findBone("game/gun-flare");
        SpineGunFlare.slotGameGunFlare = SpineGunFlare.skeletonData.findSlot("game/gun-flare");
        SpineGunFlare.skinDefault = SpineGunFlare.skeletonData.findSkin("default");
        SpineGunner.skeletonData = assetManager.get("spine/gunner.json");
        SpineGunner.animationData = assetManager.get("spine/gunner.json-animation");
        SpineGunner.animationAnimation = SpineGunner.skeletonData.findAnimation("animation");
        SpineGunner.boneRoot = SpineGunner.skeletonData.findBone("root");
        SpineGunner.boneRotater = SpineGunner.skeletonData.findBone("rotater");
        SpineGunner.slotGameGunnerBody = SpineGunner.skeletonData.findSlot("game/gunner-body");
        SpineGunner.slotGameGunnerGun = SpineGunner.skeletonData.findSlot("game/gunner-gun");
        SpineGunner.skinDefault = SpineGunner.skeletonData.findSkin("default");
        SpineGunner.skinGun = SpineGunner.skeletonData.findSkin("gun");
        SpineGunner.skinGunFlipped = SpineGunner.skeletonData.findSkin("gun-flipped");
        SpineMine.skeletonData = assetManager.get("spine/mine.json");
        SpineMine.animationData = assetManager.get("spine/mine.json-animation");
        SpineMine.animationAnimation = SpineMine.skeletonData.findAnimation("animation");
        SpineMine.boneRoot = SpineMine.skeletonData.findBone("root");
        SpineMine.slotGameMine = SpineMine.skeletonData.findSlot("game/mine");
        SpineMine.skinDefault = SpineMine.skeletonData.findSkin("default");
        SpineMoney.skeletonData = assetManager.get("spine/money.json");
        SpineMoney.animationData = assetManager.get("spine/money.json-animation");
        SpineMoney.animationAnimation = SpineMoney.skeletonData.findAnimation("animation");
        SpineMoney.boneRoot = SpineMoney.skeletonData.findBone("root");
        SpineMoney.boneBone = SpineMoney.skeletonData.findBone("bone");
        SpineMoney.boneGameMoneySign = SpineMoney.skeletonData.findBone("game/money-sign");
        SpineMoney.slotGameMoneySign = SpineMoney.skeletonData.findSlot("game/money-sign");
        SpineMoney.skinDefault = SpineMoney.skeletonData.findSkin("default");
        SpinePlayer.skeletonData = assetManager.get("spine/player.json");
        SpinePlayer.animationData = assetManager.get("spine/player.json-animation");
        SpinePlayer.animationAnimation = SpinePlayer.skeletonData.findAnimation("animation");
        SpinePlayer.boneRoot = SpinePlayer.skeletonData.findBone("root");
        SpinePlayer.slotGamePlayer = SpinePlayer.skeletonData.findSlot("game/player");
        SpinePlayer.skinDefault = SpinePlayer.skeletonData.findSkin("default");
        SpineRed.skeletonData = assetManager.get("spine/red.json");
        SpineRed.animationData = assetManager.get("spine/red.json-animation");
        SpineRed.animationAnimation = SpineRed.skeletonData.findAnimation("animation");
        SpineRed.boneRoot = SpineRed.skeletonData.findBone("root");
        SpineRed.boneGameRedBody = SpineRed.skeletonData.findBone("game/red-body");
        SpineRed.boneGameRedBody2 = SpineRed.skeletonData.findBone("game/red-body2");
        SpineRed.boneGameRedBody3 = SpineRed.skeletonData.findBone("game/red-body3");
        SpineRed.boneGameRedHand = SpineRed.skeletonData.findBone("game/red-hand");
        SpineRed.boneGameRedHand2 = SpineRed.skeletonData.findBone("game/red-hand2");
        SpineRed.boneGameRedFish = SpineRed.skeletonData.findBone("game/red-fish");
        SpineRed.boneGameRedFish2 = SpineRed.skeletonData.findBone("game/red-fish2");
        SpineRed.boneGameRedFish3 = SpineRed.skeletonData.findBone("game/red-fish3");
        SpineRed.boneGameRedHead = SpineRed.skeletonData.findBone("game/red-head");
        SpineRed.slotGameRedBody = SpineRed.skeletonData.findSlot("game/red-body");
        SpineRed.slotGameRedHand = SpineRed.skeletonData.findSlot("game/red-hand");
        SpineRed.slotGameRedHand2 = SpineRed.skeletonData.findSlot("game/red-hand2");
        SpineRed.slotGameRedHead = SpineRed.skeletonData.findSlot("game/red-head");
        SpineRed.slotGameRedFish = SpineRed.skeletonData.findSlot("game/red-fish");
        SpineRed.slotGameRedFish2 = SpineRed.skeletonData.findSlot("game/red-fish2");
        SpineRed.skinDefault = SpineRed.skeletonData.findSkin("default");
        SpineRobot.skeletonData = assetManager.get("spine/robot.json");
        SpineRobot.animationData = assetManager.get("spine/robot.json-animation");
        SpineRobot.animationAnimation = SpineRobot.skeletonData.findAnimation("animation");
        SpineRobot.boneRoot = SpineRobot.skeletonData.findBone("root");
        SpineRobot.slotGameRobot = SpineRobot.skeletonData.findSlot("game/robot");
        SpineRobot.skinDefault = SpineRobot.skeletonData.findSkin("default");
        SpineRocket.skeletonData = assetManager.get("spine/rocket.json");
        SpineRocket.animationData = assetManager.get("spine/rocket.json-animation");
        SpineRocket.animationAnimation = SpineRocket.skeletonData.findAnimation("animation");
        SpineRocket.boneRoot = SpineRocket.skeletonData.findBone("root");
        SpineRocket.boneScaler = SpineRocket.skeletonData.findBone("scaler");
        SpineRocket.slotGameRocketTrailLayer2 = SpineRocket.skeletonData.findSlot("game/rocket-trail-Layer-2");
        SpineRocket.slotGameRocket = SpineRocket.skeletonData.findSlot("game/rocket");
        SpineRocket.skinDefault = SpineRocket.skeletonData.findSkin("default");
        SpineStageCompass.skeletonData = assetManager.get("spine/stage-compass.json");
        SpineStageCompass.animationData = assetManager.get("spine/stage-compass.json-animation");
        SpineStageCompass.animationAnimation = SpineStageCompass.skeletonData.findAnimation("animation");
        SpineStageCompass.boneRoot = SpineStageCompass.skeletonData.findBone("root");
        SpineStageCompass.slotGameStageCompass = SpineStageCompass.skeletonData.findSlot("game/stage-compass");
        SpineStageCompass.skinDefault = SpineStageCompass.skeletonData.findSkin("default");
        SpineStageFloor.skeletonData = assetManager.get("spine/stage-floor.json");
        SpineStageFloor.animationData = assetManager.get("spine/stage-floor.json-animation");
        SpineStageFloor.animationAnimation = SpineStageFloor.skeletonData.findAnimation("animation");
        SpineStageFloor.boneRoot = SpineStageFloor.skeletonData.findBone("root");
        SpineStageFloor.slotGameStageFloor = SpineStageFloor.skeletonData.findSlot("game/stage-floor");
        SpineStageFloor.skinDefault = SpineStageFloor.skeletonData.findSkin("default");
        SpineStageWalls.skeletonData = assetManager.get("spine/stage-walls.json");
        SpineStageWalls.animationData = assetManager.get("spine/stage-walls.json-animation");
        SpineStageWalls.animationAnimation = SpineStageWalls.skeletonData.findAnimation("animation");
        SpineStageWalls.boneRoot = SpineStageWalls.skeletonData.findBone("root");
        SpineStageWalls.slotGameStageWalls = SpineStageWalls.skeletonData.findSlot("game/stage-walls");
        SpineStageWalls.skinDefault = SpineStageWalls.skeletonData.findSkin("default");
        SpineTanker.skeletonData = assetManager.get("spine/tanker.json");
        SpineTanker.animationData = assetManager.get("spine/tanker.json-animation");
        SpineTanker.animationSpawn = SpineTanker.skeletonData.findAnimation("spawn");
        SpineTanker.boneRoot = SpineTanker.skeletonData.findBone("root");
        SpineTanker.boneRotater = SpineTanker.skeletonData.findBone("rotater");
        SpineTanker.slotRotater = SpineTanker.skeletonData.findSlot("rotater");
        SpineTanker.slotGameTankerBody = SpineTanker.skeletonData.findSlot("game/tanker-body");
        SpineTanker.skinDefault = SpineTanker.skeletonData.findSkin("default");
        SpineZombie.skeletonData = assetManager.get("spine/zombie.json");
        SpineZombie.animationData = assetManager.get("spine/zombie.json-animation");
        SpineZombie.animationSpawn = SpineZombie.skeletonData.findAnimation("spawn");
        SpineZombie.boneRoot = SpineZombie.skeletonData.findBone("root");
        SpineZombie.slotGameZombie = SpineZombie.skeletonData.findSlot("game/zombie");
        SpineZombie.skinZombie = SpineZombie.skeletonData.findSkin("zombie");
        SpineZombie.skinZombieFast = SpineZombie.skeletonData.findSkin("zombie-fast");
        skin_skin = assetManager.get("skin/skin.json");
        SkinSkinStyles.bOptions = skin_skin.get("options", Button.ButtonStyle.class);
        SkinSkinStyles.bDefault = skin_skin.get("default", Button.ButtonStyle.class);
        SkinSkinStyles.bCredits = skin_skin.get("credits", Button.ButtonStyle.class);
        SkinSkinStyles.bPlay = skin_skin.get("play", Button.ButtonStyle.class);
        SkinSkinStyles.lDefault = skin_skin.get("default", Label.LabelStyle.class);
        SkinSkinStyles.spDefault = skin_skin.get("default", ScrollPane.ScrollPaneStyle.class);
        SkinSkinStyles.sDefaultHorizontal = skin_skin.get("default-horizontal", Slider.SliderStyle.class);
        SkinSkinStyles.tbToggle = skin_skin.get("toggle", TextButton.TextButtonStyle.class);
        SkinSkinStyles.tbDefault = skin_skin.get("default", TextButton.TextButtonStyle.class);
        SkinSkinStyles.tfDefault = skin_skin.get("default", TextField.TextFieldStyle.class);
        SkinSkinStyles.ttDefault = skin_skin.get("default", TextTooltip.TextTooltipStyle.class);
        SkinSkinStyles.wDefault = skin_skin.get("default", Window.WindowStyle.class);
        sfx_beam = assetManager.get("sfx/beam.mp3");
        sfx_bigMoney = assetManager.get("sfx/big-money.mp3");
        sfx_buyThatForADollar = assetManager.get("sfx/buy that for a dollar.mp3");
        sfx_enemyGun = assetManager.get("sfx/enemy-gun.mp3");
        sfx_enemyHurt01 = assetManager.get("sfx/enemy-hurt01.mp3");
        sfx_enemyHurt02 = assetManager.get("sfx/enemy-hurt02.mp3");
        sfx_enemyHurt03 = assetManager.get("sfx/enemy-hurt03.mp3");
        sfx_explosion01 = assetManager.get("sfx/explosion-01.mp3");
        sfx_explosion02 = assetManager.get("sfx/explosion-02.mp3");
        sfx_explosion03 = assetManager.get("sfx/explosion-03.mp3");
        sfx_gun = assetManager.get("sfx/gun.mp3");
        sfx_money = assetManager.get("sfx/money.mp3");
        sfx_ping01 = assetManager.get("sfx/ping-01.mp3");
        sfx_ping02 = assetManager.get("sfx/ping-02.mp3");
        sfx_ping03 = assetManager.get("sfx/ping-03.mp3");
        sfx_playerHurt01 = assetManager.get("sfx/player-hurt01.mp3");
        sfx_playerHurt02 = assetManager.get("sfx/player-hurt02.mp3");
        sfx_playerHurt03 = assetManager.get("sfx/player-hurt03.mp3");
        sfx_rocket = assetManager.get("sfx/rocket.mp3");
        bgm_audioSample = assetManager.get("bgm/audio-sample.mp3");
        bgm_enemyGun = assetManager.get("bgm/enemy-gun.mp3");
        bgm_menu = assetManager.get("bgm/menu.mp3");
    }

    public static class SpineBeam {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static BoneData boneRoot;

        public static SlotData slotGameBeam;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineBlood {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static BoneData boneRoot;

        public static BoneData boneBone;

        public static SlotData slotGameBlood01;

        public static com.esotericsoftware.spine.Skin skinBlood01;

        public static com.esotericsoftware.spine.Skin skinBlood02;

        public static com.esotericsoftware.spine.Skin skinBlood03;

        public static com.esotericsoftware.spine.Skin skinBlood04;
    }

    public static class SpineBomber {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationShake;

        public static Animation animationSpark;

        public static Animation animationSpawn;

        public static BoneData boneRoot;

        public static BoneData boneGameBomber;

        public static SlotData slotGameBomber;

        public static SlotData slotGameSparkLayer2;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineBoss {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationStage0;

        public static Animation animationStage1;

        public static Animation animationStage2;

        public static Animation animationStage3;

        public static Animation animationStage4;

        public static BoneData boneRoot;

        public static BoneData boneGameBossHead;

        public static BoneData boneMuzzle;

        public static BoneData boneRotater;

        public static SlotData slotRotater;

        public static SlotData slotGameBossHand;

        public static SlotData slotGameBossHand2;

        public static SlotData slotGameBossBody;

        public static SlotData slotGameBossHead;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineBullet {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static BoneData boneRoot;

        public static SlotData slotGameRocketTrailLayer2;

        public static SlotData slotGameBulletRed;

        public static com.esotericsoftware.spine.Skin skinGrenade;

        public static com.esotericsoftware.spine.Skin skinRed;

        public static com.esotericsoftware.spine.Skin skinRocket;

        public static com.esotericsoftware.spine.Skin skinShell;

        public static com.esotericsoftware.spine.Skin skinWhite;

        public static com.esotericsoftware.spine.Skin skinYellow;
    }

    public static class SpineDollar {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static BoneData boneRoot;

        public static SlotData slotDollar;

        public static com.esotericsoftware.spine.Skin skinDollar;

        public static com.esotericsoftware.spine.Skin skinGem;

        public static com.esotericsoftware.spine.Skin skinRuby;
    }

    public static class SpineExplosion {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static BoneData boneRoot;

        public static SlotData slotGameExplosionLayer1;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineGoreSmall {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static BoneData boneRoot;

        public static SlotData slotGameGoreLayer1;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineGore {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static BoneData boneRoot;

        public static SlotData slotGameGoreLayer1;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineGunFlare {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static BoneData boneRoot;

        public static BoneData boneGameGunFlare;

        public static SlotData slotGameGunFlare;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineGunner {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static BoneData boneRoot;

        public static BoneData boneRotater;

        public static SlotData slotGameGunnerBody;

        public static SlotData slotGameGunnerGun;

        public static com.esotericsoftware.spine.Skin skinDefault;

        public static com.esotericsoftware.spine.Skin skinGun;

        public static com.esotericsoftware.spine.Skin skinGunFlipped;
    }

    public static class SpineMine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static BoneData boneRoot;

        public static SlotData slotGameMine;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineMoney {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static BoneData boneRoot;

        public static BoneData boneBone;

        public static BoneData boneGameMoneySign;

        public static SlotData slotGameMoneySign;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpinePlayer {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static BoneData boneRoot;

        public static SlotData slotGamePlayer;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineRed {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static BoneData boneRoot;

        public static BoneData boneGameRedBody;

        public static BoneData boneGameRedBody2;

        public static BoneData boneGameRedBody3;

        public static BoneData boneGameRedHand;

        public static BoneData boneGameRedHand2;

        public static BoneData boneGameRedFish;

        public static BoneData boneGameRedFish2;

        public static BoneData boneGameRedFish3;

        public static BoneData boneGameRedHead;

        public static SlotData slotGameRedBody;

        public static SlotData slotGameRedHand;

        public static SlotData slotGameRedHand2;

        public static SlotData slotGameRedHead;

        public static SlotData slotGameRedFish;

        public static SlotData slotGameRedFish2;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineRobot {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static BoneData boneRoot;

        public static SlotData slotGameRobot;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineRocket {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static BoneData boneRoot;

        public static BoneData boneScaler;

        public static SlotData slotGameRocketTrailLayer2;

        public static SlotData slotGameRocket;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineStageCompass {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static BoneData boneRoot;

        public static SlotData slotGameStageCompass;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineStageFloor {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static BoneData boneRoot;

        public static SlotData slotGameStageFloor;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineStageWalls {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static BoneData boneRoot;

        public static SlotData slotGameStageWalls;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineTanker {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationSpawn;

        public static BoneData boneRoot;

        public static BoneData boneRotater;

        public static SlotData slotRotater;

        public static SlotData slotGameTankerBody;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineZombie {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationSpawn;

        public static BoneData boneRoot;

        public static SlotData slotGameZombie;

        public static com.esotericsoftware.spine.Skin skinZombie;

        public static com.esotericsoftware.spine.Skin skinZombieFast;
    }

    public static class SkinSkinStyles {
        public static Button.ButtonStyle bOptions;

        public static Button.ButtonStyle bDefault;

        public static Button.ButtonStyle bCredits;

        public static Button.ButtonStyle bPlay;

        public static Label.LabelStyle lDefault;

        public static ScrollPane.ScrollPaneStyle spDefault;

        public static Slider.SliderStyle sDefaultHorizontal;

        public static TextButton.TextButtonStyle tbToggle;

        public static TextButton.TextButtonStyle tbDefault;

        public static TextField.TextFieldStyle tfDefault;

        public static TextTooltip.TextTooltipStyle ttDefault;

        public static Window.WindowStyle wDefault;
    }

    public static class Values {
        public static float playerTimeToRun = 1.0f;

        public static int playerStandDeceleration = 2500;

        public static int playerWalkAcceleration = 1500;

        public static int playerWalkDeceleration = 1500;

        public static int playerMaxWalkSpeed = 600;

        public static int playerRunAcceleration = 2000;

        public static int playerRunDeceleration = 2000;

        public static int playerMaxRunSpeed = 1200;

        public static int playerSkidThreshold = 650;

        public static float playerSmokeDelay = 0.1f;

        public static int playerStandSmokeMinSpeed = 500;

        public static int playerGravity = 3000;

        public static int playerMaxFallYspeed = 1500;

        public static int playerFallXacceleration = 1500;

        public static int playerMaxFallXspeed = 600;

        public static int playerJumpSpeed = 1500;

        public static int playerJumpXacceleration = 1500;

        public static int playerJumpXspeed = 600;

        public static int playerJumpYacceleration = 5000;

        public static float playerJumpExtraTime = 0.2f;

        public static int playerDoubleJumpYacceleration = 5000;

        public static float playerDoubleJumpTime = 0.5f;

        public static float playerDoubleJumpDelay = 1.0f;

        public static String name = "Raeleus";

        public static boolean godMode = true;

        public static int id = 10;
    }
}
