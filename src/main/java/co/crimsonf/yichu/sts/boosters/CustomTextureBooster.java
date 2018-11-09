package co.crimsonf.yichu.sts.boosters;

import co.crimsonf.yichu.sts.config.STSConfig;
import co.crimsonf.yichu.sts.interfaces.IBooster;
import com.pixelmonmod.pixelmon.storage.NbtKeys;
import net.minecraft.nbt.NBTTagCompound;

public class CustomTextureBooster implements IBooster {
    @Override
    public int getMoney(NBTTagCompound pokemon) {
        return pokemon.hasUniqueId(NbtKeys.CUSTOM_TEXTURE) ? STSConfig.General.customTextureBooster : 0;
    }

    @Override
    public String getItemLore() {
        return "Custom Texture Booster: $";
    }
}