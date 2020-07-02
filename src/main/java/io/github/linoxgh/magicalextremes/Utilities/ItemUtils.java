package io.github.linoxgh.magicalextremes.Utilities;

import io.github.linoxgh.magicalextremes.API.Crafting.RecipeType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

import io.github.linoxgh.magicalextremes.API.Items.ItemList;
import io.github.linoxgh.magicalextremes.API.Items.MagicItem;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ItemUtils {

    /**
     * Gets the {@link MagicItem} associated with the given ItemStack from
     * its NBT Tags.
     *
     * @param item
     *              The ItemStack to check
     * @return {@link MagicItem} if found. <code>null</code> if not found.
     */
    public static MagicItem<? extends Event> getMagicItem(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;

        PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
        for (NamespacedKey key : dataContainer.getKeys()) {
            if (key.getNamespace().equalsIgnoreCase("MagicalExtremesPlugin")) {
                for (MagicItem<? extends Event> magicItem : ItemList.getItems()) {
                    if (magicItem.getKey().equals(key)) return magicItem;
                }
            } else return null;
        }

        return null;
    }


    public static boolean isItemSimilar(ItemStack item1, ItemStack item2, boolean loreCheck, boolean enchCheck, boolean amountCheck) {
        if (item1 == null) return item2 == null;
        if (amountCheck && item1.getAmount() != item2.getAmount()) return false;

        if (item1.getType() != item2.getType()) return false;
        if (enchCheck && !item1.getEnchantments().equals(item2.getEnchantments())) return false;
        if (!item1.hasItemMeta()) return !item2.hasItemMeta();

        ItemMeta meta1 = item1.getItemMeta();
        ItemMeta meta2 = item2.getItemMeta();

        if (!meta1.getDisplayName().equals(meta2.getDisplayName())) return false;
        if (loreCheck) {
            if (!meta1.hasLore()) return !meta2.hasLore();
            return meta1.getLore().equals(meta2.getLore());
        }
        return false;
    }

    /**
     * Creates an ItemStack from the given values.
     *
     * @param type
     *                  The type of the ItemStack
     * @param itemMeta
     *                  The ItemMeta of the ItemStack
     * @param amount
     *                  The amount of items in the ItemStack
     * @return The created ItemStack
     */
    public static ItemStack createItemStack(Material type, ItemMeta itemMeta, int amount) {
        ItemStack item = new ItemStack(type, amount);
        item.setItemMeta(itemMeta);
        return item;
    }

    /**
     * Creates an ItemStack with 1 item from the given values.
     *
     * @param type
     *                  The type of the ItemStack
     * @param itemMeta
     *                  The ItemMeta of the ItemStack
     * @return The created ItemStack
     */
    public static ItemStack createItemStack(Material type, ItemMeta itemMeta) {
        return createItemStack(type, itemMeta, 1);
    }

    public static ItemStack createItemStack(Material type, String name, String... lore) {
        return createItemStack(type, 1, name, lore);
    }

    public static ItemStack createItemStack(Material type, int amount, String name, String... lore) {
        ItemStack item = new ItemStack(type, amount);
        if (!item.hasItemMeta()) return null;
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(name);
        itemMeta.setLore(Arrays.asList(lore));

        item.setItemMeta(itemMeta);
        return item;
    }
}
