package co.crimsonf.yichu.sts.boosters;

import co.crimsonf.yichu.sts.Utilities;
import co.crimsonf.yichu.sts.config.STSConfig;
import co.crimsonf.yichu.sts.interfaces.IBooster;
import com.pixelmonmod.pixelmon.storage.NbtKeys;
import net.minecraft.nbt.NBTTagCompound;

public class LegendaryBooster implements IBooster {
    @Override
    public int getMoney(NBTTagCompound pokemon) {
        return Utilities.legends.contains(pokemon.getString(NbtKeys.NAME).toLowerCase()) ? STSConfig.General.legendaryBooster : 0;
    }

    @Override
    public String getItemLore() {
        return "Legendary Pokemon Booster: $";
    }
}
