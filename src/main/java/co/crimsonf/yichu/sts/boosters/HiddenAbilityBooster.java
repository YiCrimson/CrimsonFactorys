package co.crimsonf.yichu.sts.boosters;

import co.crimsonf.yichu.sts.config.STSConfig;
import co.crimsonf.yichu.sts.interfaces.IBooster;
import com.pixelmonmod.pixelmon.storage.NbtKeys;
import net.minecraft.nbt.NBTTagCompound;

public class HiddenAbilityBooster implements IBooster {
    @Override
    public int getMoney(NBTTagCompound pokemon) {
        return pokemon.getInt(NbtKeys.ABILITY_SLOT) == 2 ? STSConfig.General.hiddenAbilityBooster : 0;
    }

    @Override
    public String getItemLore()
    {
        return "Hidden Ability Booster: $";
    }
}
