package co.crimsonf.yichu.sts.sponge.wondertrade.teslalibs.argument.parser;

import co.crimsonf.yichu.sts.sponge.wondertrade.teslalibs.argument.Arguments;
import co.crimsonf.yichu.sts.sponge.wondertrade.teslalibs.argument.CommandElement;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.ArgumentParseException;
import org.spongepowered.api.command.args.CommandArgs;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.text.Text;

import java.util.function.Function;
import java.util.function.Predicate;

@FunctionalInterface
public interface ValueParser<T> {

    /**
     * Parses a value from this parser. This is a {@link FunctionalInterface}
     * and may be defined in lambda expressions.
     */
    T parseValue(CommandSource src, CommandArgs args) throws ArgumentParseException;

    /**
     * Parses a value from this parser and adds it into the context under the
     * given key.
     */
    default void parse(Text key, CommandSource src, CommandArgs args, CommandContext ctx) throws ArgumentParseException {
        T value = parseValue(src, args);
        if (value instanceof Iterable) {
            ((Iterable<?>) value).forEach(v -> ctx.putArg(key, v));
        } else {
            ctx.putArg(key, value);
        }
    }

    /**
     * Completes this parser and returns an immutable list of suggestions.
     */
    default ImmutableList<String> complete(CommandSource src, CommandArgs args, CommandContext ctx) {
        return ImmutableList.of();
    }

    /**
     * @return true if this parser can be parsed without consuming an argument.
     */
    default boolean isOptional() {
        return false;
    }

    /**
     * Creates a new {@link FunctionParser} with the given mapper.
     *
     * @see Arguments#function(Function, ValueParser, ImmutableMap)
     */
    default <R> FunctionParser<T, R> map(Function<T, R> mapper) {
        return Arguments.function(mapper, this, ImmutableMap.of());
    }

    /**
     * Creates a new {@link PredicateParser} with the given predicate.
     *
     * @see Arguments#predicate(Predicate, ValueParser, ImmutableMap)
     */
    default PredicateParser<T> filter(Predicate<T> predicate) {
        return Arguments.predicate(predicate, this, ImmutableMap.of());
    }

    /**
     * Creates a new {@link OptionalParser} with this parser.
     *
     * @see Arguments#optional(ValueParser, ImmutableMap)
     */
    default OptionalParser<T> optional() {
        return Arguments.optional(this, ImmutableMap.of());
    }

    /**
     * Creates a new {@link CommandElement} with the given key.
     */
    default CommandElement<T> toElement(String key) {
        return new CommandElement<>(Text.of(key), this);
    }

}