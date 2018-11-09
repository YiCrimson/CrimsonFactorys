package co.crimsonf.yichu.sts.boosters;

import co.crimsonf.yichu.sts.config.STSConfig;
import co.crimsonf.yichu.sts.interfaces.IBooster;
import com.pixelmonmod.pixelmon.storage.NbtKeys;
import net.minecraft.nbt.NBTTagCompound;


public class BoosterMoneyPerLevel implements IBooster {

    @Override
    public int getMoney(NBTTagCompound pokemon) {
        int level = pokemon.getInt(NbtKeys.LEVEL);
        return level * STSConfig.General.moneyPerLevel;
    }

    @Override
    public String getItemLore() {
        return "Money from Level : $";
    }
}