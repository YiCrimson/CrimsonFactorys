package co.crimsonf.yichu.sts.boosters;

import co.crimsonf.yichu.sts.config.STSConfig;
import co.crimsonf.yichu.sts.interfaces.IBooster;
import com.pixelmonmod.pixelmon.storage.NbtKeys;
import net.minecraft.nbt.NBTTagCompound;

public class PerfectEVBooster implements IBooster {
    @Override
    public int getMoney(NBTTagCompound pokemon) {
        float evHP = pokemon.getByte(NbtKeys.EV_HP);
        float evAtk = pokemon.getByte(NbtKeys.EV_ATTACK);
        float evDef = pokemon.getByte(NbtKeys.EV_DEFENCE);
        float evSpeed = pokemon.getByte(NbtKeys.EV_SPEED);
        float evSAtk = pokemon.getByte(NbtKeys.EV_SPECIAL_ATTACK);
        float evSDef = pokemon.getByte(NbtKeys.EV_SPECIAL_DEFENCE);
        int per = Math.round(((evHP + evDef + evAtk + evSpeed + evSAtk + evSDef) / 510) * 100);
        return per >= 100 ? STSConfig.General.perfectEVBooster : 0;
    }

    @Override
    public String getItemLore() {
        return "Perfect EV Booster: $";
    }
}
