package co.crimsonf.yichu.sts.sponge.wondertrade.teslalibs.argument;

import co.crimsonf.yichu.sts.sponge.wondertrade.teslalibs.argument.parser.ValueParser;
import com.google.common.collect.ImmutableList;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.ArgumentParseException;
import org.spongepowered.api.command.args.CommandArgs;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.text.Text;

public class CommandElement<T> extends org.spongepowered.api.command.args.CommandElement implements ValueParser<T> {

    private final ValueParser<T> parser;

    public CommandElement(Text key, ValueParser<T> parser) {
        super(key);
        this.parser = parser;
    }

    public ValueParser<T> getParser() {
        return parser;
    }

    @Override
    public void parse(CommandSource src, CommandArgs args, CommandContext ctx) throws ArgumentParseException {
        parser.parse(getKey(), src, args, ctx);
    }

    @Override
    public T parseValue(CommandSource src, CommandArgs args) throws ArgumentParseException {
        return parser.parseValue(src, args);
    }

    @Override
    public ImmutableList<String> complete(CommandSource src, CommandArgs args, CommandContext ctx) {
        return parser.complete(src, args, ctx);
    }

}