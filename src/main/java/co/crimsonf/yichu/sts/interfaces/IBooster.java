package co.crimsonf.yichu.sts.interfaces;

import net.minecraft.nbt.NBTTagCompound;


public interface IBooster {

    /**
     * Returns the amount of money the booster is supposed to give per the values in the Pokemon parameter.
     * @param pokemon The {@link NBTTagCompound} of the Pokemon the player is selling.
     * @return
     */
    public int getMoney(NBTTagCompound pokemon);

    /**
     * The text that is supposed to appear under the item lore.
     * Recomended Format: "Short Booster Descroption: $"
     * @return A string that will be applied to the booster lore.
     */
    public String getItemLore();
}
