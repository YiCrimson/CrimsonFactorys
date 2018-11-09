package co.crimsonf.yichu.sts.sponge.wondertrade.teslalibs.argument.parser;

import com.google.common.collect.ImmutableMap;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.ArgumentParseException;
import org.spongepowered.api.command.args.CommandArgs;

import java.util.function.Predicate;

public class PredicateParser<T> extends DelegateParser<T, T> {

    private final Predicate<T> predicate;

    public PredicateParser(Predicate<T> predicate, ValueParser<T> delegate, ImmutableMap<String, String> messages) {
        super(delegate, messages);
        this.predicate = predicate;
    }

    @Override
    public T parseValue(CommandSource src, CommandArgs args) throws ArgumentParseException {
        T value = delegate.parseValue(src, args);
        try {
            if (predicate.test(value)) {
                return value;
            }
        } catch (Exception e) {
            throw args.createError(getMessage("exception", "<exception>", "exception", e.getMessage()));
        }
        throw args.createError(getMessage("invalid-value", "The value <value> does not meet the requirements for this argument.", "value", value));
    }

}