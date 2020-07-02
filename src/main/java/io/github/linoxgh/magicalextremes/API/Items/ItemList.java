package io.github.linoxgh.magicalextremes.API.Items;

import io.github.linoxgh.magicalextremes.Implementation.Items.Spellcraft.SpellBook;
import org.bukkit.event.Event;

import java.util.HashSet;
import java.util.Set;

public class ItemList {

    public static final MagicItem<? extends Event> SPELL_BOOK = new SpellBook(9);

    private static final Set<MagicItem<? extends Event>> items = new HashSet<>();
    public static Set<MagicItem<? extends Event>> getItems() {
        return items;
    }

    public static void addItem(MagicItem<? extends Event> item) {
        items.add(item);
    }
}
