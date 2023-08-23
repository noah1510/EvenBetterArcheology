package de.sakurajin.evenbetterarcheology.item;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.evenbetterarcheology.api.item.BetterBrushItem;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.registration.annotations.AssignedName;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Rarity;

public class ModItems implements ItemRegistryContainer {
    //ITEM ENTRIES-------------------------------------------------------------------------//
    //BRUSHES
    public static final Item IRON_BRUSH = BetterBrushItem.Builder().setBrushingSpeed(8).setMaxDamage(128).setMaterial(Items.IRON_INGOT).build();

    public static final Item DIAMOND_BRUSH = BetterBrushItem.Builder().setBrushingSpeed(6).setMaxDamage(256).setMaterial(Items.DIAMOND).build();

    public static final Item ARTIFACT_SHARDS = new Item(new OwoItemSettings().group(EvenBetterArcheology.GROUP).rarity(Rarity.UNCOMMON));

    public static final Item UNIDENTIFIED_ARTIFACT = new Item(new OwoItemSettings().group(EvenBetterArcheology.GROUP).rarity(Rarity.UNCOMMON));

    //LOOT ITEMS
    @AssignedName("bomb")
    public static final Item BOMB_ITEM = new BombItem(new OwoItemSettings().group(EvenBetterArcheology.GROUP).rarity(Rarity.COMMON).maxCount(16));
    public static final Item TORRENT_TOTEM = new TorrentTotemItem(new OwoItemSettings().group(EvenBetterArcheology.GROUP).rarity(Rarity.UNCOMMON).maxCount(1).maxDamage(32));
    public static final Item SOUL_TOTEM = new SoulTotemItem(new OwoItemSettings().group(EvenBetterArcheology.GROUP).rarity(Rarity.UNCOMMON).maxDamage(32));

}
