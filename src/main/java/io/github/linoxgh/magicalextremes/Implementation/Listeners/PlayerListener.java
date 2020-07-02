package io.github.linoxgh.magicalextremes.Implementation.Listeners;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import io.github.linoxgh.magicalextremes.API.Items.MagicItem;
import io.github.linoxgh.magicalextremes.Utilities.ItemUtils;

public class PlayerListener implements Listener {

    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        if (event.getAction() != Action.PHYSICAL) {
            ItemStack item = event.getItem();
            if (item == null) return;

            MagicItem<? extends Event> magicItem = ItemUtils.getMagicItem(item);
            if (magicItem == null) return;

            if (magicItem.getEventType() instanceof PlayerInteractEvent) {
                magicItem.onEventFire();
            }
        }
    }
}
