package co.crimsonf.yichu.sts.boosters;

import co.crimsonf.yichu.sts.Utilities;
import co.crimsonf.yichu.sts.config.STSConfig;
import co.crimsonf.yichu.sts.interfaces.IBooster;
import com.pixelmonmod.pixelmon.storage.NbtKeys;
import net.minecraft.nbt.NBTTagCompound;

public class Generation1RBooster implements IBooster {
    @Override
    public int getMoney(final NBTTagCompound pokemon) {
        return Utilities.gen1R.contains(pokemon.getString(NbtKeys.NAME).toLowerCase()) ? STSConfig.General.Generation1RBooster : 0;
    }

    @Override
    public String getItemLore() {
        return "Generation 1 rare Pokemon Booster: $";
    }
}
