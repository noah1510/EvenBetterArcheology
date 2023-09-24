package de.sakurajin.evenbetterarcheology.registry;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.sakuralib.arrp.v2.patchouli.JPatchouliBook;
import de.sakurajin.sakuralib.arrp.v2.patchouli.JPatchouliCategory;
import de.sakurajin.sakuralib.arrp.v2.patchouli.JPatchouliEntry;
import de.sakurajin.sakuralib.arrp.v2.patchouli.pages.JRecipePage;
import net.minecraft.util.Identifier;

public class PatchouliBookGeneration {
    static public final String BOOK_NAME = "better_archeology_guide";
    static public final Identifier BOOK_ID = EvenBetterArcheology.DATA.getSimpleID(BOOK_NAME);
    static public final String BRUSHES_CATEGORY_NAME = "brushes";
    static public final Identifier BRUSHES_CATEGORY_ID = EvenBetterArcheology.DATA.getSimpleID(BRUSHES_CATEGORY_NAME);

    public static void generateBook() {
        //create the book and register it
        JPatchouliBook book = JPatchouliBook
            .create(
                "patchouli_book.even_better_archeology.better_archeology_guide.name",
                "patchouli_book.even_better_archeology.better_archeology_guide.landing"
            ).setVersion("2")
            .setCreativeTab("evenbetterarcheology:evenbetterarcheology");

        EvenBetterArcheology.DATA.registerPatchouliBook(BOOK_NAME, book);

        //create the brushes category and register it
        JPatchouliCategory brushes = JPatchouliCategory
            .create(
                "patchouli_book.even_better_archeology.better_archeology_guide.brushes.name",
                "patchouli_book.even_better_archeology.better_archeology_guide.brushes.description",
                "minecraft:brush"
            );

        EvenBetterArcheology.DATA.registerPatchouliCategory(BOOK_NAME, BRUSHES_CATEGORY_NAME, brushes);

        //create and register the entry for the vanilla brush other entries will be added by the BetterBrushItem
        EvenBetterArcheology.DATA.registerPatchouliEntry(
            BOOK_NAME,
            JPatchouliEntry
                .create(
                    "item.minecraft.brush",
                    BRUSHES_CATEGORY_ID.toString(),
                    "minecraft:brush"
                ).addTextPage("patchouli_book.even_better_archeology.better_archeology_guide.brush.vanilla_brush")
                .addPage(JRecipePage.create("minecraft:brush"))
        );

    }
}
