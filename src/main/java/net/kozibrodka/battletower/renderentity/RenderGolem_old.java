package net.kozibrodka.battletower.renderentity;

import net.kozibrodka.battletower.entity.EntityGolem_old;

import net.minecraft.client.render.entity.UndeadEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;


public class RenderGolem_old extends UndeadEntityRenderer
{
    public RenderGolem_old() {
        super(new BipedEntityModel(), 1.0F);
        setDecorationModel(new BipedEntityModel());
    }

//    public RenderGolem(EntityModelBase arg, EntityModelBase arg2, float f) {
//        super(arg, f);
//        this.setModel(arg2);
//
//    }

    protected void func_15310_scalegolem(EntityGolem_old entitygolem, float f)
    {
        GL11.glScalef(2.0F, 2.0F, 2.0F);
    }

    @Override
    protected void applyScale(LivingEntity entityliving, float f)
    {
        func_15310_scalegolem((EntityGolem_old)entityliving, f);
    }
}

