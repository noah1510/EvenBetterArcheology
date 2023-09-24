package de.sakurajin.evenbetterarcheology.api.item;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.sakurajin.evenbetterarcheology.registry.PatchouliBookGeneration;
import de.sakurajin.sakuralib.arrp.v2.patchouli.JPatchouliEntry;
import de.sakurajin.sakuralib.arrp.v2.patchouli.pages.JRecipePage;
import de.sakurajin.sakuralib.arrp.v2.patchouli.pages.JTextPage;
import de.sakurajin.sakuralib.datagen.v1.DatagenModContainer;
import de.sakurajin.sakuralib.datagen.v1.DataGenerateable;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.text.TranslationContext;
import net.devtech.arrp.json.recipe.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BrushableBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
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
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/**
 * This is an extension of the BrushItem for Brushes that are made from better materials.
 * It is recommended to use the Builder to create a new instance of this class.
 * <p>
 * To generate the data using the data generator, a material has to be provided.
 * This way the data generation system can generate the crafting recepie.
 * The material has to be an Item that is registered in the game.
 * The item model can always be generated even if no material is provided.
 *
 * @see BrushItem
 * @see Builder
 */
public class BetterBrushItem extends BrushItem implements DataGenerateable {
    private final float  brushingSpeed;
    private final Item   material;
    private final String materialName;


    public BetterBrushItem(Settings settings, float pBrushingSpeed, Item material) {
        super(settings);
        brushingSpeed = pBrushingSpeed;
        this.material = material;

        String materialTranslationKey = material.getTranslationKey();
        var    materialArray          = materialTranslationKey.split("\\.");
        this.materialName = materialArray[materialArray.length - 1];
    }

    @Override
    public ItemConvertible generateData(DatagenModContainer container, String identifier) {
        container.addTag("c:items/brushitems", identifier);
        container.generateItemModel(identifier, "minecraft:item/brush", identifier);

        JTextPage mainPage = JTextPage.create("patchouli_book.even_better_archeology.better_archeology_guide.brush." + identifier);

        JPatchouliEntry brushEntry = JPatchouliEntry
            .create("item.evenbetterarcheology."+identifier, PatchouliBookGeneration.BRUSHES_CATEGORY_ID.toString(), container.getStringID(identifier))
            .addPage(mainPage);

        if (material != null) {
            Identifier recipeID = container.getSimpleID("crafting_" + identifier + "_" + materialName);
            container.RESOURCE_PACK.addRecipe(
                recipeID,
                JRecipe.shaped(
                    JPattern.pattern("x", "y", "z"),
                    JKeys.keys()
                         .key("x", JIngredient.ingredient().item(material))
                         .key("y", JIngredient.ingredient().item(Items.STICK))
                         .key("z", JIngredient.ingredient().item(Items.FEATHER)),
                    JResult.item(this)
                )
            );

            brushEntry = brushEntry.addPage(JRecipePage.create(recipeID.toString()));
        } else {
            container.LOGGER.warn("No material provided for the BetterBrushItem {}. The crafting recipe will not be generated.", identifier);
        }

        container.registerPatchouliEntry(PatchouliBookGeneration.BOOK_NAME, brushEntry);

        return this;
    }

    public float getBrushingSpeed() {
        return brushingSpeed;
    }

    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (remainingUseTicks >= 0 && user instanceof PlayerEntity playerEntity) {
            HitResult hitResult = this.getHitResult(user);
            if (hitResult instanceof BlockHitResult blockHitResult && hitResult.getType() == HitResult.Type.BLOCK) {
                //actually brush the block
                if ((this.getMaxUseTime(stack) - remainingUseTicks + 1) % brushingSpeed == brushingSpeed / 2) {
                    BlockPos   blockPos   = blockHitResult.getBlockPos();
                    BlockState blockState = world.getBlockState(blockPos);

                    // spawn the particles
                    Arm arm = user.getActiveHand() == Hand.MAIN_HAND ? playerEntity.getMainArm() : playerEntity.getMainArm().getOpposite();
                    this.addDustParticles(world, blockHitResult, blockState, user.getRotationVec(0.0F), arm);
                    Block targetedBlock = blockState.getBlock();

                    // play the brushing sound
                    SoundEvent soundEvent;
                    if (targetedBlock instanceof BrushableBlock brushableBlock) {
                        soundEvent = brushableBlock.getBrushingSound();
                    } else {
                        soundEvent = SoundEvents.ITEM_BRUSH_BRUSHING_GENERIC;
                    }
                    world.playSound(playerEntity, blockPos, soundEvent, SoundCategory.BLOCKS);

                    // if on server, brush the block entity and damage the brush
                    if (!world.isClient()) {
                        BlockEntity targetedBlockEntity = world.getBlockEntity(blockPos);
                        if (targetedBlockEntity instanceof BrushableBlockEntity brushableBlockEntity) {
                            if (brushableBlockEntity.brush(world.getTime(), playerEntity, blockHitResult.getSide())) {
                                EquipmentSlot equipmentSlot = stack.equals(playerEntity.getEquippedStack(EquipmentSlot.OFFHAND)) ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
                                stack.damage(1, user, (userx) -> {
                                    userx.sendEquipmentBreakStatus(equipmentSlot);
                                });
                            }
                        }
                    }
                }

                //return early to prevent the item use from being stopped
                return;
            }
        }

        user.stopUsingItem();
    }

    public static Builder Builder(DatagenModContainer container) {
        return new Builder(container);
    }

    /**
     * A Builder for the BetterBrushItem.
     * It is recommended to use this Builder to create a new instance of the BetterBrushItem.
     * The default settings are created using the DatagenModContainer.
     * <p>
     * The following properties can be set using the Builder:
     * <p>
     * * brushingSpeed (default: 1.0f) - lower values mean faster brushing
     * * maxDamage (default: container.settings()) - the maximum durability of the brush
     * * maxCount (default: container.settings()) - the maximum stack size of the brush
     * * rarity (default: container.settings()) - the rarity of the brush
     * * material (default: null) - the material used to craft the brush
     *
     * @see BetterBrushItem
     * @see DatagenModContainer
     */
    public static class Builder {
        private       float           brushingSpeed = 1.0f;
        private final OwoItemSettings settings;
        private       Item            material      = null;

        public Builder(DatagenModContainer container) {
            this.settings = container.settings();
        }

        public Builder setBrushingSpeed(float pBrushingSpeed) {
            brushingSpeed = pBrushingSpeed;
            return this;
        }

        public Builder setMaxDamage(int maxDamage) {
            settings.maxDamage(maxDamage);
            return this;
        }

        public Builder setMaxCount(int maxCount) {
            settings.maxCount(maxCount);
            return this;
        }

        public Builder setRarity(Rarity rarity) {
            settings.rarity(rarity);
            return this;
        }

        public Builder setMaterial(Item material) {
            this.material = material;
            return this;
        }

        public BetterBrushItem build() {
            return new BetterBrushItem(settings, brushingSpeed, material);
        }

    }
}