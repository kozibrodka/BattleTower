package net.kozibrodka.battletower.renderentity;

import net.kozibrodka.battletower.entity.EntityGolem;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.entity.Living;
import org.lwjgl.opengl.GL11;

// Referenced classes of package net.minecraft.src:
//            RenderBiped, ModelBiped, EntityGolem, EntityLiving

public class RenderGolem extends BipedEntityRenderer
{
    public RenderGolem() {
        super(new Biped(), 1.0F);
        setModel(new Biped());
    }

//    public RenderGolem(EntityModelBase arg, EntityModelBase arg2, float f) {
//        super(arg, f);
//        this.setModel(arg2);
//
//    }

    protected void func_15310_scalegolem(EntityGolem entitygolem, float f)
    {
        GL11.glScalef(2.0F, 2.0F, 2.0F);
    }

    protected void method_823(Living entityliving, float f)
    {
        func_15310_scalegolem((EntityGolem)entityliving, f);
    }
}

