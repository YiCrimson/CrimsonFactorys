package co.crimsonf.yichu.sts.ui;

import co.crimsonf.yichu.sts.STS;
import co.crimsonf.yichu.sts.Utilities;
import co.crimsonf.yichu.sts.interfaces.IBooster;
import co.crimsonf.yichu.sts.sponge.wondertrade.teslalibs.inventory.Action;
import co.crimsonf.yichu.sts.sponge.wondertrade.teslalibs.inventory.Element;
import co.crimsonf.yichu.sts.sponge.wondertrade.teslalibs.inventory.Layout;
import co.crimsonf.yichu.sts.sponge.wondertrade.teslalibs.inventory.View;
import com.pixelmonmod.pixelmon.enums.EnumPokemon;
import com.pixelmonmod.pixelmon.storage.NbtKeys;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.DyeColors;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.text.Text;

import java.util.ArrayList;
import java.util.function.Consumer;

public class UISTS {

    NBTTagCompound[] pokemon;
    Player player;

    int noPokemon = 0;

    public UISTS(Player player, final NBTTagCompound[] pokemon)
    {
        this.pokemon = pokemon;
        this.player = player;
    }

    public void displayGUI()
    {
        Element blueGlass = Element.of(ItemStack.builder().itemType(ItemTypes.STAINED_GLASS_PANE).add(Keys.DYE_COLOR, DyeColors.BLUE).build());
        Element redGlass = Element.of(ItemStack.builder().itemType(ItemTypes.STAINED_GLASS_PANE).add(Keys.DYE_COLOR, DyeColors.RED).build());
        Element blackGlass = Element.of(ItemStack.builder().itemType(ItemTypes.STAINED_GLASS_PANE).add(Keys.DYE_COLOR, DyeColors.BLACK).build());
        Element whiteGlass = Element.of(ItemStack.builder().itemType(ItemTypes.STAINED_GLASS_PANE).add(Keys.DYE_COLOR, DyeColors.WHITE).build());

        Layout.Builder layout = Layout.builder()
                .set(blueGlass, 0, 1, 3, 4, 5, 7, 8)
                .set(redGlass, 2, 6)
                .set(blackGlass, 9, 17)
                .set(whiteGlass, 13, 18, 19, 20, 21, 22, 23, 24, 25, 26);
        int slot = 10;
        for(final NBTTagCompound poke : pokemon)
        {
            if(poke == null)
            {
                layout.set(Element.of(ItemStack.of(ItemTypes.AIR, 1)));
            }
            else
            {
                noPokemon += 1;
                int price = Utilities.getPrice(poke);
                Consumer<Action.Click> action = a ->
                {
                    if(noPokemon == 1)
                    {
                        player.sendMessage(Text.of("[STS] You can't sell your last Pokemon!"));
                    }
                    else {
                        co.crimsonf.yichu.sts.ui.UIConfirm con = new UIConfirm(player, poke, price);
                        con.displayGUI();
                    }
                };
                ItemStack p = Utilities.getPokemonItem(EnumPokemon.getFromNameAnyCase(poke.getString(NbtKeys.NAME)), poke.getInt(NbtKeys.FORM), poke.getBoolean(NbtKeys.IS_EGG), poke.getBoolean(NbtKeys.IS_SHINY));
                ArrayList<Text> lore = new ArrayList<>();
                for(IBooster booster : STS.boosters)
                {
                    if(booster.getMoney(poke) != 0)
                        lore.add(Text.of(booster.getItemLore() + booster.getMoney(poke)));
                }
                p.offer(Keys.ITEM_LORE, lore);
                p.offer(Keys.DISPLAY_NAME, Text.of("\u2605 " + poke.getString(NbtKeys.NAME) + " ($" + price + ") \u2605"));
                layout.set(Element.of(p, action), slot);
            }
            if(slot == 12)
                slot += 2;
            else
                slot += 1;
        }
        Layout lay = layout.build();
        View view = View.builder().archetype(InventoryArchetypes.CHEST).property(InventoryTitle.of(Text.of("Sell to Server"))).build(STS.getInstance().container);
        view.define(lay);
        view.open(player);
    }
}
