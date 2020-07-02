package io.github.linoxgh.magicalextremes.Implementation.Items.Spellcraft;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import io.github.linoxgh.magicalextremes.API.Crafting.RecipeType;
import io.github.linoxgh.magicalextremes.API.Items.MagicItem;

public class SpellBook extends MagicItem<PlayerInteractEvent> {
    private final int maxSpells;

    public SpellBook(int maxSpells) {
        super("spell-book", RecipeType.SHAPELESS_RECIPE,
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.BOOK), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.DIAMOND), new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.BOOK), new ItemStack(Material.GOLD_NUGGET)},
                Material.ENCHANTED_BOOK, "&5Spell Book", "", "&6Right Click &3to access your spells");

        this.maxSpells = maxSpells;
    }

    public int getMaxSpells() {
        return maxSpells;
    }
    public String getInventoryName() {
        return ChatColor.DARK_PURPLE + "Spell Book - " + ChatColor.DARK_AQUA + getMaxSpells();
    }

    @Override
    public void onEventFire() {

    }
}
