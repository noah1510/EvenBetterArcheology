package de.sakurajin.evenbetterarcheology.item;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Presets.Item.CraftableItem;
import de.sakurajin.evenbetterarcheology.api.item.BetterBrushItem;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.registration.annotations.AssignedName;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Containers.ParsedItemRegistryContainer;
import net.devtech.arrp.json.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;


public class ModItems extends ParsedItemRegistryContainer {
    public ModItems() {super(EvenBetterArcheology.DATA);}

    //ITEM ENTRIES-------------------------------------------------------------------------//
    //BRUSHES
    public static final Item IRON_BRUSH = BetterBrushItem.Builder(EvenBetterArcheology.DATA).setBrushingSpeed(8).setMaxDamage(128).setMaterial(Items.IRON_INGOT).build();

    public static final Item DIAMOND_BRUSH = BetterBrushItem.Builder(EvenBetterArcheology.DATA).setBrushingSpeed(6).setMaxDamage(256).setMaterial(Items.DIAMOND).build();

    public static final Item ARTIFACT_SHARDS = new Item(EvenBetterArcheology.DATA.settings().rarity(Rarity.UNCOMMON));

    public static final Item UNIDENTIFIED_ARTIFACT = new CraftableItem(EvenBetterArcheology.DATA.settings().rarity(Rarity.UNCOMMON)) {
        @Override
        public void generateRecepie(DatagenModContainer container, String identifier) {
            container.RESOURCE_PACK.addRecipe(
                new Identifier(container.MOD_ID, identifier),
                JRecipe.shaped(
                    JPattern.pattern("###", "###", "###"),
                    JKeys.keys().key("#", JIngredient.ingredient().item(ModItems.ARTIFACT_SHARDS)),
                    JResult.item(this)
                )
            );
        }
    };

    //LOOT ITEMS
    @AssignedName("bomb")
    public static final Item BOMB_ITEM = new BombItem(EvenBetterArcheology.DATA.settings().rarity(Rarity.COMMON).maxCount(16));
    public static final Item TORRENT_TOTEM = new TorrentTotemItem(EvenBetterArcheology.DATA.settings().rarity(Rarity.UNCOMMON).maxCount(1).maxDamage(32));
    public static final Item SOUL_TOTEM = new SoulTotemItem(EvenBetterArcheology.DATA.settings().rarity(Rarity.UNCOMMON).maxDamage(32));

}
