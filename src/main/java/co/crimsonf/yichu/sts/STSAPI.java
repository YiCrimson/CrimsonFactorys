package co.crimsonf.yichu.sts;

import co.crimsonf.yichu.sts.interfaces.IBooster;

import java.util.List;

public class STSAPI {

    /**
     * Adds a booster to the list of active boosters.
     * @param booster A class that implements {@link co.crimsonf.yichu.sts.interfaces.IBooster}.
     */
    public static void addBooster(co.crimsonf.yichu.sts.interfaces.IBooster booster)
    {
        co.crimsonf.yichu.sts.STS.boosters.add(booster);
    }

    /**
     * Removes a booster from the list of active boosters. If the booster is not in the list, this does nothing.
     * @param booster A class that implements {@link co.crimsonf.yichu.sts.interfaces.IBooster}.
     */
    public static void removeBooster(co.crimsonf.yichu.sts.interfaces.IBooster booster)
    {
        if(co.crimsonf.yichu.sts.STS.boosters.contains(booster))
            co.crimsonf.yichu.sts.STS.boosters.remove(booster);
    }

    /**
     * Gets a list of all registered boosters.
     * @return A List containing {@link co.crimsonf.yichu.sts.interfaces.IBooster} instances.
     */
    public static List<IBooster> getBoosters()
    {
        return STS.boosters;
    }
}
