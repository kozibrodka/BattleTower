package net.kozibrodka.battletower.renderentity;

import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import org.lwjgl.opengl.GL11;

public class RenderGolemFireball extends EntityRenderer {

    @Override
    public void render(Entity entity, double x, double y, double z, float yaw, float pitch) {
//        GL11.glPushMatrix();
//        GL11.glTranslatef((float)x, (float)y, (float)z);
//        GL11.glEnable(32826);
//        float var10 = 2.0F;
//        GL11.glScalef(var10 / 1.0F, var10 / 1.0F, var10 / 1.0F);
//        int var11 = Item.SNOWBALL.getTextureId(0);
//        this.bindTexture("/gui/items.png");
//        Tessellator var12 = Tessellator.INSTANCE;
//        float var13 = (float)(var11 % 16 * 16 + 0) / 256.0F;
//        float var14 = (float)(var11 % 16 * 16 + 16) / 256.0F;
//        float var15 = (float)(var11 / 16 * 16 + 0) / 256.0F;
//        float var16 = (float)(var11 / 16 * 16 + 16) / 256.0F;
//        float var17 = 1.0F;
//        float var18 = 0.5F;
//        float var19 = 0.25F;
//        GL11.glRotatef(180.0F - this.dispatcher.yaw, 0.0F, 1.0F, 0.0F);
//        GL11.glRotatef(-this.dispatcher.pitch, 1.0F, 0.0F, 0.0F);
//        var12.startQuads();
//        var12.normal(0.0F, 1.0F, 0.0F);
//        var12.vertex((double)(0.0F - var18), (double)(0.0F - var19), (double)0.0F, (double)var13, (double)var16);
//        var12.vertex((double)(var17 - var18), (double)(0.0F - var19), (double)0.0F, (double)var14, (double)var16);
//        var12.vertex((double)(var17 - var18), (double)(1.0F - var19), (double)0.0F, (double)var14, (double)var15);
//        var12.vertex((double)(0.0F - var18), (double)(1.0F - var19), (double)0.0F, (double)var13, (double)var15);
//        var12.draw();
//        GL11.glDisable(32826);
//        GL11.glPopMatrix();
    }
}
