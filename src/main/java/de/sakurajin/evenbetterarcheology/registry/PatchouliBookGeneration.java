package de.sakurajin.evenbetterarcheology.registry;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.sakuralib.arrp.v2.patchouli.JPatchouliBook;
import de.sakurajin.sakuralib.arrp.v2.patchouli.JPatchouliCategory;
import de.sakurajin.sakuralib.arrp.v2.patchouli.JPatchouliEntry;
import de.sakurajin.sakuralib.arrp.v2.patchouli.pages.JCraftingPage;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

public class PatchouliBookGeneration {
    static public class NameIDPair extends Pair<String, Identifier>{
        public NameIDPair(String name) {
            super(name, EvenBetterArcheology.DATA.getSimpleID(name));
        }

        public String name(){
            return getLeft();
        }

        public Identifier ID(){
            return getRight();
        }

        public String IDString(){
            return ID().toString();
        }
    }

    static public final NameIDPair BOOK = new NameIDPair("better_archeology_guide");
    static public final NameIDPair BRUSHES_CATEGORY = new NameIDPair("brushes");
    static public final NameIDPair ARCHEOLOGY_CATEGORY = new NameIDPair("archeology");

    public static void generateBook() {
        //create the book and register it
        //or at least that would be the case if patchouli would allow loading books from datapacks
        //for whatever reason pachouli only loads books from the jar file so this is commented out
        //since it does not work atm anyway
        /*
        JPatchouliBook book = JPatchouliBook
            .create(
                "patchouli_book.even_better_archeology.better_archeology_guide.name",
                "patchouli_book.even_better_archeology.better_archeology_guide.landing"
            ).setVersion("2")
            .setCreativeTab("evenbetterarcheology:evenbetterarcheology");
        EvenBetterArcheology.DATA.registerPatchouliBook(BOOK.name(), book);
        */

        //create the brushes category and register it
        JPatchouliCategory brushes = JPatchouliCategory
            .create(
                "patchouli_book.even_better_archeology.better_archeology_guide.brushes.name",
                "patchouli_book.even_better_archeology.better_archeology_guide.brushes.description",
                "minecraft:brush"
            );
        EvenBetterArcheology.DATA.registerPatchouliCategory(BOOK.name(), BRUSHES_CATEGORY.name(), brushes);

        //create the archeology category and register it
        JPatchouliCategory archeology = JPatchouliCategory
            .create(
                "patchouli_book.even_better_archeology.better_archeology_guide.archeology.name",
                "patchouli_book.even_better_archeology.better_archeology_guide.archeology.description",
                "evenbetterarcheology:artifact_shards"
            ).setSecret(true);
        EvenBetterArcheology.DATA.registerPatchouliCategory(BOOK.name(), ARCHEOLOGY_CATEGORY.name(), archeology);

        //create and register the entry for the vanilla brush other entries will be added by the BetterBrushItem
        EvenBetterArcheology.DATA.registerPatchouliEntry(
            BOOK.name(),
            JPatchouliEntry
                .create(
                    "item.minecraft.brush",
                    BRUSHES_CATEGORY.IDString(),
                    "minecraft:brush"
                ).addTextPage("patchouli_book.even_better_archeology.better_archeology_guide.brush.vanilla_brush")
                .addPage(JCraftingPage.create("minecraft:brush"))
        );

    }
}
