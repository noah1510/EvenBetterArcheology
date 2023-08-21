package de.sakurajin.evenbetterarcheology.api.item;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BrushableBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class BetterBrushItem extends BrushItem {
    private float brushingSpeed;

    public BetterBrushItem(Settings settings, float pBrushingSpeed) {
        super(settings);
        brushingSpeed = pBrushingSpeed;
    }

    public float getBrushingSpeed(){
        return brushingSpeed;
    }

    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (remainingUseTicks >= 0 && user instanceof PlayerEntity playerEntity) {
            HitResult hitResult = this.getHitResult(user);
            if (hitResult instanceof BlockHitResult blockHitResult) {
                if (hitResult.getType() == HitResult.Type.BLOCK) {
                    int i = this.getMaxUseTime(stack) - remainingUseTicks + 1;
                    boolean bl = i % brushingSpeed == brushingSpeed/2;
                    if (bl) {
                        BlockPos blockPos = blockHitResult.getBlockPos();
                        BlockState blockState = world.getBlockState(blockPos);
                        Arm arm = user.getActiveHand() == Hand.MAIN_HAND ? playerEntity.getMainArm() : playerEntity.getMainArm().getOpposite();
                        this.addDustParticles(world, blockHitResult, blockState, user.getRotationVec(0.0F), arm);
                        Block var15 = blockState.getBlock();
                        SoundEvent soundEvent;
                        if (var15 instanceof BrushableBlock) {
                            BrushableBlock brushableBlock = (BrushableBlock)var15;
                            soundEvent = brushableBlock.getBrushingSound();
                        } else {
                            soundEvent = SoundEvents.ITEM_BRUSH_BRUSHING_GENERIC;
                        }

                        world.playSound(playerEntity, blockPos, soundEvent, SoundCategory.BLOCKS);
                        if (!world.isClient()) {
                            BlockEntity var18 = world.getBlockEntity(blockPos);
                            if (var18 instanceof BrushableBlockEntity) {
                                BrushableBlockEntity brushableBlockEntity = (BrushableBlockEntity)var18;
                                boolean bl2 = brushableBlockEntity.brush(world.getTime(), playerEntity, blockHitResult.getSide());
                                if (bl2) {
                                    EquipmentSlot equipmentSlot = stack.equals(playerEntity.getEquippedStack(EquipmentSlot.OFFHAND)) ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
                                    stack.damage(1, user, (userx) -> {
                                        userx.sendEquipmentBreakStatus(equipmentSlot);
                                    });
                                }
                            }
                        }
                    }

                    return;
                }
            }

            user.stopUsingItem();
        } else {
            user.stopUsingItem();
        }
    }

    public static Builder Builder(){
        return new Builder();
    }

    public static class Builder{
        private float brushingSpeed = 1.0f;
        private RegistryKey<ItemGroup> group = null;
        private String name = null;
        private String modid = null;
        private OwoItemSettings settings = new OwoItemSettings();

        public Builder setBrushingSpeed(float pBrushingSpeed){
            brushingSpeed = pBrushingSpeed;
            return this;
        }

        public Builder setMaxDamage(int maxDamage){
            settings.maxDamage(maxDamage);
            return this;
        }

        public Builder setMaxCount(int maxCount){
            settings.maxCount(maxCount);
            return this;
        }

        public Builder setRarity(Rarity rarity){
            settings.rarity(rarity);
            return this;
        }

        public Builder setGroup(RegistryKey<ItemGroup> group){
            this.group = group;
            return this;
        }

        public Builder setGroup(OwoItemGroup group){
            settings.group(group);
            return this;
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setModid(String modid){
            this.modid = modid;
            return this;
        }

        public BetterBrushItem build(){
            BetterBrushItem item = new BetterBrushItem(settings, brushingSpeed);
            return item;
        }

        public Item buildAndRegister(){
            if (name == null) throw new IllegalArgumentException("Name must not be null");
            if (modid == null) throw new IllegalArgumentException("Modid must not be null");

            BetterBrushItem item = build();
            Item registeredItem = Registry.register(Registries.ITEM, new Identifier(modid, name), item);
            if(group != null) ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(registeredItem));

            return registeredItem;
        }
    }
}