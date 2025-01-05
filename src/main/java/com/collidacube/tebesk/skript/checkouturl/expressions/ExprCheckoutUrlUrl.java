package com.collidacube.tebesk.skript.checkouturl.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.tebex.sdk.obj.CheckoutUrl;
import org.bukkit.event.Event;

public class ExprCheckoutUrlUrl extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprCheckoutUrlUrl.class, String.class, ExpressionType.PROPERTY,
                "[the] url of %checkouturls%");
    }

    private Expression<CheckoutUrl> checkoutUrl;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        this.checkoutUrl = (Expression<CheckoutUrl>) exprs[0];
        return true;
    }

    @Override
    protected String[] get(Event e) {
        CheckoutUrl[] checkoutUrls = this.checkoutUrl.getArray(e);
        String[] urls = new String[checkoutUrls.length];
        for (int i = 0; i < checkoutUrls.length; i++)
            urls[i] = checkoutUrls[i].getUrl();
        return urls;
    }

    @Override
    public boolean isSingle() {
        return this.checkoutUrl.isSingle();
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "url of " + this.checkoutUrl.toString(e, debug);
    }

}
