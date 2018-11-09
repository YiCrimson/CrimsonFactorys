package co.crimsonf.yichu.sts.boosters;

import co.crimsonf.yichu.sts.Utilities;
import co.crimsonf.yichu.sts.config.STSConfig;
import co.crimsonf.yichu.sts.interfaces.IBooster;
import com.pixelmonmod.pixelmon.storage.NbtKeys;
import net.minecraft.nbt.NBTTagCompound;

public class Generation1Booster implements IBooster {
    @Override
    public int getMoney(NBTTagCompound pokemon) {
        return Utilities.gen1.contains(pokemon.getString(NbtKeys.NAME).toLowerCase()) ? STSConfig.General.Generation1Booster : 0;
    }

    @Override
    public String getItemLore() {
        return "Generation 1 Pokemon Booster: $";
    }
}
