package co.crimsonf.yichu.sts.sponge.wondertrade.teslalibs.argument.parser;

import co.crimsonf.yichu.sts.sponge.wondertrade.teslalibs.argument.Arguments;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.ArgumentParseException;
import org.spongepowered.api.command.args.CommandArgs;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.text.selector.Selector;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SelectorParser<T> extends DelegateParser<T, Set<T>> {

    private Function<Stream<Entity>, Stream<T>> function;

    public SelectorParser(Function<Stream<Entity>, Stream<T>> function, ValueParser<T> delegate, ImmutableMap<String, String> messages) {
        super(delegate, messages);
        this.function = function;
    }

    @Override
    public Set<T> parseValue(CommandSource src, CommandArgs args) throws ArgumentParseException {
        if (args.hasNext() && args.peek().startsWith("@")) {
            String arg = args.next();
            try {
                return function.apply(Selector.parse(arg).resolve(src).stream()).collect(Collectors.toSet());
            } catch (IllegalArgumentException e) {
                throw args.createError(getMessage("invalid-selector", "The selector <arg> is not in the correct format: <exception>.", "arg", arg, "exception", e.getMessage()));
            } catch (Exception e) {
                throw args.createError(getMessage("exception", "<exception>", "exception", e.getMessage()));
            }
        }
        return Sets.newHashSet(delegate.parseValue(src, args));
    }

    @Override
    public ImmutableList<String> complete(CommandSource src, CommandArgs args, CommandContext ctx) {
        Object state = args.getState();
        List<String> completions = delegate.complete(src, args, ctx);
        args.setState(state);
        return ImmutableList.<String>builder()
                .addAll(completions)
                .addAll(Selector.complete(args.nextIfPresent().orElse("")))
                .build();
    }

    /**
     * Creates a new {@link OrSourceParser} that returns a {@link Set} of
     * element(s) retrieved from the {@link CommandSource} if neither this
     * selector or delegate parser were successful.
     */
    public OrSourceParser<Set<T>> orSource(Function<CommandSource, Set<T>> function) {
        return Arguments.orSource(function, this, ImmutableMap.of("exception", "Unable to parse selector or delegate and source is not of the proper type: <exception>"));
    }

}