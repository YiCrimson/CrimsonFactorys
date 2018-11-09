package co.crimsonf.yichu.sts.boosters;

import co.crimsonf.yichu.sts.config.STSConfig;
import co.crimsonf.yichu.sts.interfaces.IBooster;
import com.pixelmonmod.pixelmon.storage.NbtKeys;
import net.minecraft.nbt.NBTTagCompound;

public class BoosterShiny implements IBooster {
    @Override
    public int getMoney(NBTTagCompound pokemon) {
        boolean isShiny = pokemon.getBoolean(NbtKeys.IS_SHINY);
        return isShiny ? STSConfig.General.shinyBooster : 0;
    }

    @Override
    public String getItemLore()
    {
        return "Shiny Pokemon Booster: $";
    }
}