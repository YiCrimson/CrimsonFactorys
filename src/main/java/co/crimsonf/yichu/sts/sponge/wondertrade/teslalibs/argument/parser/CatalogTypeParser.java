package co.crimsonf.yichu.sts.sponge.wondertrade.teslalibs.argument.parser;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.spongepowered.api.CatalogType;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.ArgumentParseException;
import org.spongepowered.api.command.args.CommandArgs;
import org.spongepowered.api.command.args.CommandContext;

public class CatalogTypeParser<T extends CatalogType> extends StandardParser<T> {

    private final Class<T> type;

    public CatalogTypeParser(Class<T> type, ImmutableMap<String, String> messages) {
        super(messages);
        this.type = type;
    }

    @Override
    public T parseValue(CommandSource src, CommandArgs args) throws ArgumentParseException {
        String arg = args.next();
        return Sponge.getRegistry().getType(type, arg).orElseThrow(() ->
                args.createError(getMessage("no-type", "Input <arg> is not a registered <type>.", "arg", arg, "type", type.getSimpleName())));
    }

    @Override
    public ImmutableList<String> complete(CommandSource src, CommandArgs args, CommandContext ctx) {
        return complete(args, Sponge.getRegistry().getAllOf(type).stream().map(CatalogType::getId));
    }

}