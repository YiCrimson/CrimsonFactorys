package co.crimsonf.yichu.sts.config;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
@ConfigSerializable
public class STSConfig {

    @Setting
    General general = new General();

    @ConfigSerializable
    public static class General {
        @Setting(comment = "The amount of money per level of the Pokemon.")
        public static int moneyPerLevel = 15;

        @Setting(comment = "The amount of money per % of the Pokemons iv. Example: $5 for a 1% IV Pokemon, $10 for a 2% IV Pokemon.")
        public static int moneyPerIV = 10;

        @Setting(comment = "The amount of money per % of the Pokemons ev. Example: $5 for a 1% IV Pokemon, $10 for a 2% IV Pokemon.")
        public static int moneyPerEV = 5;

        @Setting(comment = "The amount of money given if the Pokemon has a Hidden Ability.")
        public static int hiddenAbilityBooster = 1000;

        @Setting(comment = "The amount of money given if the Pokemon has perfect IVs.")
        public static int perfectIVBooster = 1000;

        @Setting(comment = "The amount of money given if the Pokemon has perfect EVs.")
        public static int perfectEVBooster = 1000;

        @Setting(comment = "The amount of money given if the Pokemon is shiny.")
        public static int shinyBooster = 2000;

        @Setting(comment = "The amount of money given if the Pokemon is a legendary.")
        public static int legendaryBooster = 4000;

        @Setting(comment = "The amount of money given if the Pokemon is a pokemon found in generation 1.")
        public static int Generation1Booster = 25;

        @Setting(comment = "The amount of money given if the Pokemon is a pokemon found in generation 1 and it rare!")
        public static int Generation1RBooster = 25;

        @Setting(comment = "The amount of money given to the player if the Pokemon has a custom texture.")
        public static int customTextureBooster = 1000;



    }
}
