package de.sakurajin.evenbetterarcheology.mixin;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class MixinPlugin implements IMixinConfigPlugin {

    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if(!mixinClassName.startsWith("de.sakurajin.evenbetterarcheology.mixin")){
            return false;
        }

        String modPackage = mixinClassName.replaceFirst("de.sakurajin.evenbetterarcheology.mixin.", "").split("\\.")[0];

        if (modPackage.equals("minecraft")){
            return true;
        }

        return FabricLoader.getInstance().isModLoaded(modPackage);
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

}
