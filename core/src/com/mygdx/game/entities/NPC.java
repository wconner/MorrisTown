package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.JsonTest;

import java.util.ArrayList;


/**
 * Created by Ian on 2/26/2015.
 * NPC's ID should be: 0 < ID < 100
 */
public class NPC extends AbstractDynamicObject {

    private static final String TAG = NPC.class.getName();

    /**
    * To hold a texture
    */
    private TextureRegion npcTexture;
    private int levelID, dialogID;
    private String name;
    private static JsonTest jsonTest = new JsonTest();

    /**
     * create NPC with specific location
     */
    public NPC(int id, float x, float y, int levelID, String name) {
        super(id, "NPC");
        super.getBody().setUserData(this);
        this.position.set(x, y);
        this.levelID = levelID;
        this.name = name;
        dialogID = 0;
    }

    public void setDialog(){
         jsonTest.setDialog(name, levelID, dialogID);
    }
    public void setDialogID(int id) { dialogID = id; jsonTest.setDialog(name, levelID, dialogID);}

    public JsonTest getJsonTest(){ return jsonTest;}

    public void setRegion (TextureRegion region) {
        npcTexture = region;
    }

    public float getWidth(){
        return npcTexture.getRegionWidth() * Constants.UNIT_SCALE/2;
    }
    public float getHeight(){
        return npcTexture.getRegionHeight() * Constants.UNIT_SCALE/2;
    }


    /**
    * note, the render has its own texture which is grabbed all the time
    * it comes from the spritebatch but it just checks the current texture all the time
    */
    @Override
    public void render (SpriteBatch batch) {
        TextureRegion reg = null;

        reg = npcTexture;
        batch.draw(reg.getTexture(),
                super.getBody().getPosition().x - dimension.x / 2,
                super.getBody().getPosition().y - dimension.y / 2,
                origin.x, origin.y,
                dimension.x, dimension.y,
                scale.x, scale.y,
                rotation,
                reg.getRegionX(), reg.getRegionY(),
                reg.getRegionWidth(), reg.getRegionHeight(),
                false, false);

        // Reset color to white
        batch.setColor(1, 1, 1, 1);

    }
}