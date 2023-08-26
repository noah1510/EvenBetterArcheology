package de.sakurajin.evenbetterarcheology.api.item;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.ItemModelGeneratateable;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.RecepieGeneratable;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.devtech.arrp.json.recipe.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BrushableBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BrushItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

public class BetterBrushItem extends BrushItem implements ItemModelGeneratateable, RecepieGeneratable {
    private final float brushingSpeed;
    private final Item material;
    private final String materialName;


    public BetterBrushItem(Settings settings, float pBrushingSpeed, Item material) {
        super(settings);
        brushingSpeed = pBrushingSpeed;
        this.material = material;

        String materialTranslationKey = material.getTranslationKey();
        var materialArray = materialTranslationKey.split("\\.");
        this.materialName = materialArray[materialArray.length-1];
    }

    @Override
    public void generateItemModel(DatagenModContainer container, String identifier) {
        container.DATA_GEN_HELPER.generateItemModel(identifier, "minecraft:item/brush", identifier);
    }

    @Override
    public void generateRecepie(DatagenModContainer container, String identifier){
        if(material == null) throw new RuntimeException("Material is null cannot generate Resource Data");

        container.RESOURCE_PACK.addRecipe(
                new Identifier(identifier, "crafting_"+identifier+"_"+materialName),
                JRecipe.shaped(
                        JPattern.pattern("x", "y", "z"),
                        JKeys.keys()
                                .key("x", JIngredient.ingredient().item(material))
                                .key("y", JIngredient.ingredient().item(Items.STICK))
                                .key("z", JIngredient.ingredient().item(Items.FEATHER)),
                        JResult.item(this)
                )
        );
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

    public static Builder Builder(DatagenModContainer container){
        return new Builder(container);
    }

    public static class Builder{
        private float brushingSpeed = 1.0f;
        private final OwoItemSettings settings;
        private Item material = null;
        private final DatagenModContainer container;

        public Builder(DatagenModContainer container) {
            this.container = container;
            this.settings = this.container.settings();
        }

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

        public Builder setMaterial(Item material){
            this.material = material;
            return this;
        }

        public BetterBrushItem build(){
            return new BetterBrushItem(settings, brushingSpeed, material);
        }

    }
}