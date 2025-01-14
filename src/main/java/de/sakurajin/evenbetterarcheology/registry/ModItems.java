package de.sakurajin.evenbetterarcheology.registry;

import de.sakurajin.sakuralib.datagen.v1.DatagenModContainer;
import de.sakurajin.sakuralib.datagen.v1.Presets.Item.CraftableItem;
import de.sakurajin.sakuralib.datagen.v1.Containers.ParsedItemRegistryContainer;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.evenbetterarcheology.api.item.BetterBrushItem;
import de.sakurajin.evenbetterarcheology.item.BombItem;
import de.sakurajin.evenbetterarcheology.item.SoulTotemItem;
import de.sakurajin.evenbetterarcheology.item.TorrentTotemItem;

import io.wispforest.owo.registration.annotations.AssignedName;
import net.devtech.arrp.json.recipe.*;

import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;


public class ModItems extends ParsedItemRegistryContainer {
    public ModItems() {
        super(EvenBetterArcheology.DATA);
        EvenBetterArcheology.DATA.addTag("c:items/brushitems", "minecraft:brush");
    }

    //ITEM ENTRIES-------------------------------------------------------------------------//
    //BRUSHES
    public static final Item IRON_BRUSH = BetterBrushItem.Builder(EvenBetterArcheology.DATA).setBrushingSpeed(8).setMaxDamage(128).setMaterial(Items.IRON_INGOT).build();

    public static final Item DIAMOND_BRUSH = BetterBrushItem.Builder(EvenBetterArcheology.DATA).setBrushingSpeed(6).setMaxDamage(256).setMaterial(Items.DIAMOND).build();

    public static final Item ARTIFACT_SHARDS = new CraftableItem(EvenBetterArcheology.DATA.settings().rarity(Rarity.UNCOMMON)){};

    public static final Item UNIDENTIFIED_ARTIFACT = new CraftableItem(EvenBetterArcheology.DATA.settings().rarity(Rarity.UNCOMMON)) {
        @Override
        public ItemConvertible generateData(DatagenModContainer container, String identifier) {
            container.RESOURCE_PACK.addRecipe(
                new Identifier(container.MOD_ID, identifier),
                JRecipe.shaped(
                    JPattern.pattern("###", "###", "###"),
                    JKeys.keys().key("#", JIngredient.ingredient().item(ModItems.ARTIFACT_SHARDS)),
                    JResult.item(this)
                )
            );
            super.generateData(container, identifier);
            return this;
        }
    };

    //LOOT ITEMS
    @AssignedName("bomb")
    public static final Item BOMB_ITEM = new BombItem(EvenBetterArcheology.DATA.settings().rarity(Rarity.COMMON).maxCount(16));
    public static final Item TORRENT_TOTEM = new TorrentTotemItem(EvenBetterArcheology.DATA.settings().rarity(Rarity.UNCOMMON).maxCount(1).maxDamage(32));
    public static final Item SOUL_TOTEM = new SoulTotemItem(EvenBetterArcheology.DATA.settings().rarity(Rarity.UNCOMMON).maxDamage(32));

}
