package io.github.linoxgh.magicalextremes.API.Items;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.linoxgh.magicalextremes.MagicalExtremesPlugin;
import io.github.linoxgh.magicalextremes.API.Crafting.RecipeType;
import io.github.linoxgh.magicalextremes.Utilities.ItemUtils;

public abstract class MagicItem<T extends Event> {

    private final String id;
    private final NamespacedKey key;
    private final Material type;
    private final RecipeType recipeType;
    private final ItemStack[] recipe;
    private final ItemStack recipeOutput;
    private final ItemMeta itemMeta;
    private T eventType;

    public MagicItem(String id, RecipeType recipeType, ItemStack[] recipe, Material type, String name, String... lore) {
        this(id, recipeType, recipe, ItemUtils.createItemStack(type, name, lore), type, name, lore);
    }

    public MagicItem(String id, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput, Material type, String name, String... lore) {
        this.id = id;
        this.key = new NamespacedKey(MagicalExtremesPlugin.getInstance(), id);
        this.type = type;
        this.recipeType = recipeType;
        this.recipe = recipe;
        this.recipeOutput = recipeOutput;
        this.itemMeta = Bukkit.getItemFactory().getItemMeta(type);

        if (itemMeta == null) return;
        itemMeta.setDisplayName(name);
        itemMeta.setLore(Arrays.asList(lore));

        ItemList.addItem(this);
    }

    public abstract void onEventFire();

    public String getId() {
        return id;
    }
    public NamespacedKey getKey() {
        return key;
    }
    public Material getType() {
        return type;
    }
    public RecipeType getRecipeType() {
        return recipeType;
    }
    public ItemStack[] getRecipe() {
        return recipe;
    }
    public ItemStack getRecipeOutput() {
        return recipeOutput;
    }
    public ItemMeta getItemMeta() {
        return itemMeta;
    }
    public T getEventType() {
        return eventType;
    }
}
