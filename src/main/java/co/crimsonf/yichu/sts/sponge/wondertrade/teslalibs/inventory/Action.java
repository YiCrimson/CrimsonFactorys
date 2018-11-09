package co.crimsonf.yichu.sts.sponge.wondertrade.teslalibs.inventory;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.event.item.inventory.InteractInventoryEvent;
import org.spongepowered.api.item.inventory.Slot;

public class Action<T extends InteractInventoryEvent> {

    private final T event;
    private final Player player;

    Action(T event, Player player) {
        this.event = event;
        this.player = player;
    }

    /**
     * @return the event of the inventory action
     */
    public T getEvent() {
        return event;
    }

    /**
     * @return the player involved in this action
     */
    public Player getPlayer() {
        return player;
    }

    public static class Click<T extends ClickInventoryEvent> extends Action<T> {

        private final Element element;
        private final Slot slot;

        Click(T event, Player player, Element element, Slot slot) {
            super(event, player);
            this.element = element;
            this.slot = slot;
        }

        /**
         * @return the element processing this click
         */
        public Element getElement() {
            return element;
        }

        /**
         * @return the slot clicked
         */
        public Slot getSlot() {
            return slot;
        }

    }

}