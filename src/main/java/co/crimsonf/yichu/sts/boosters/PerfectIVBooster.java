package co.crimsonf.yichu.sts.boosters;

import co.crimsonf.yichu.sts.config.STSConfig;
import co.crimsonf.yichu.sts.interfaces.IBooster;
import com.pixelmonmod.pixelmon.storage.NbtKeys;
import net.minecraft.nbt.NBTTagCompound;

public class PerfectIVBooster implements IBooster {
    @Override
    public int getMoney(NBTTagCompound pokemon) {
        float ivHP = pokemon.getByte(NbtKeys.IV_HP);
        float ivAtk = pokemon.getByte(NbtKeys.IV_ATTACK);
        float ivDef = pokemon.getByte(NbtKeys.IV_DEFENCE);
        float ivSpeed = pokemon.getByte(NbtKeys.IV_SPEED);
        float ivSAtk = pokemon.getByte(NbtKeys.IV_SP_ATT);
        float ivSDef = pokemon.getByte(NbtKeys.IV_SP_DEF);
        int per = Math.round(((ivHP + ivDef + ivAtk + ivSpeed + ivSAtk + ivSDef) / 186f) * 100);
        return per == 100 ? STSConfig.General.perfectIVBooster : 0;
    }

    @Override
    public String getItemLore() {
        return "Perfect IV Booster: $";
    }
}
