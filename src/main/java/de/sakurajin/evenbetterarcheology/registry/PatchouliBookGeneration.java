package de.sakurajin.evenbetterarcheology.registry;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.sakuralib.arrp.v2.patchouli.JPatchouliCategory;
import de.sakurajin.sakuralib.arrp.v2.patchouli.JPatchouliEntry;
import de.sakurajin.sakuralib.arrp.v2.patchouli.pages.JCraftingPage;
import de.sakurajin.sakuralib.datagen.v2.DynamicOwOLangManager;
import de.sakurajin.sakuralib.datagen.v2.patchouli.DynamicPatchouliCategoryContainer;
import de.sakurajin.sakuralib.datagen.v2.patchouli.PatchouliDataManager;
import de.sakurajin.sakuralib.util.v1.JsonObjectBuilder;
import de.sakurajin.sakuralib.util.v1.SakuraJsonHelper;

public class PatchouliBookGeneration {

    static public final DynamicPatchouliCategoryContainer MAIN_CATEGORY = PatchouliDataManager.getOrCreateDynamicCategory(
        EvenBetterArcheology.DATA.MOD_ID,
        JPatchouliCategory.create(
            "itemGroup.evenbetterarcheology.evenbetterarcheology",
            "sakuralib_dynamic_book.ebe.maintext",
            "evenbetterarcheology:artifact_shards"
        )
    );
    static public final DynamicPatchouliCategoryContainer ARCHEOLOGY_CATEGORY = PatchouliDataManager.MINECRAFT_CATEGORY.add(
        JPatchouliCategory
            .create(
                "sakuralib_dynamic_book.minecraft.archeology.name",
                "sakuralib_dynamic_book.minecraft.archeology.description",
                "evenbetterarcheology:artifact_shards"
            ),
        "archeology"
    );
    static public final DynamicPatchouliCategoryContainer BRUSHES_CATEGORY = ARCHEOLOGY_CATEGORY.add(
        JPatchouliCategory.create(
            "sakuralib_dynamic_book.minecraft.archeology.brushes.name",
            "sakuralib_dynamic_book.minecraft.archeology.brushes.description",
            "minecraft:brush"
        ),
        "brushes"
    );

    public static void generateBook() {
        //generate the book contents
        generateVanillaBrush();
        generateArcheologyContent();

        //write the data to the json files
        MAIN_CATEGORY.registerData();

        //add the lang de_de to the dynamic lang manager and actually update the rrp
        DynamicOwOLangManager.declareLang("de_de");
        DynamicOwOLangManager.updateRRP();
    }

    private static void generateVanillaBrush(){
        //create and register the entry for the vanilla brush other entries will be added by the BetterBrushItem
        String statsLang = "sakuralib_dynamic_book.minecraft.archeology.brush.vanilla_brush.stats";
        DynamicOwOLangManager.addGlobalEntry(
            statsLang,
            SakuraJsonHelper.createArray(
                "",
                JsonObjectBuilder
                    .create()
                    .add("translate", "sakuralib_dynamic_book.minecraft.archeology.brush.brush_stats")
                    .add("with", SakuraJsonHelper.createArray(64, 10))
                    .build()
            )
        );
        BRUSHES_CATEGORY.add(
            JPatchouliEntry
                .create(
                    "item.minecraft.brush",
                    null,
                    "minecraft:brush"
                ).addTextPage("sakuralib_dynamic_book.minecraft.archeology.brush.vanilla_brush.text")
                .addPage(JCraftingPage.create("minecraft:brush").setText(statsLang))
        );
    }

    private static void generateArcheologyContent(){


        ARCHEOLOGY_CATEGORY.registerData();
    }
}
