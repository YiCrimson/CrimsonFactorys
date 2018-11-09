package co.crimsonf.yichu.sts.sponge.wondertrade.teslalibs.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aliases {

    /**
     * The aliases for the command.
     *
     * @see Command.Settings#aliases(String...)
     */
    String[] value();

}