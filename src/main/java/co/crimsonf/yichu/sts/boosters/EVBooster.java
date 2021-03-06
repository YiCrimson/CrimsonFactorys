package co.crimsonf.yichu.sts.boosters;

import co.crimsonf.yichu.sts.config.STSConfig;
import co.crimsonf.yichu.sts.interfaces.IBooster;
import com.pixelmonmod.pixelmon.storage.NbtKeys;
import net.minecraft.nbt.NBTTagCompound;

public class EVBooster implements IBooster {
    @Override
    public int getMoney(NBTTagCompound pokemon) {
        float evHP = pokemon.getByte(NbtKeys.EV_HP);
        float evAtk = pokemon.getByte(NbtKeys.EV_ATTACK);
        float evDef = pokemon.getByte(NbtKeys.EV_DEFENCE);
        float evSpeed = pokemon.getByte(NbtKeys.EV_SPEED);
        float evSAtk = pokemon.getByte(NbtKeys.EV_SPECIAL_ATTACK);
        float evSDef = pokemon.getByte(NbtKeys.EV_SPECIAL_DEFENCE);
        return Math.round(((evHP + evDef + evAtk + evSpeed + evSAtk + evSDef) / 510f) * 100) * STSConfig.General.moneyPerEV;
    }

    @Override
    public String getItemLore() {
        return "Money from EV Percentage: $";
    }
}
