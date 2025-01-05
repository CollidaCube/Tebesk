package com.collidacube.tebesk.skript.checkouturl.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Date;
import ch.njol.util.Kleenean;
import io.tebex.sdk.obj.CheckoutUrl;
import org.bukkit.event.Event;

public class ExprCheckoutUrlExpiry extends SimpleExpression<Date> {

    static {
        Skript.registerExpression(ExprCheckoutUrlExpiry.class, Date.class, ExpressionType.PROPERTY,
                "[the] expiry [date] of %checkouturls%");
    }

    private Expression<CheckoutUrl> checkoutUrl;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        this.checkoutUrl = (Expression<CheckoutUrl>) exprs[0];
        return true;
    }

    @Override
    protected Date[] get(Event e) {
        CheckoutUrl[] checkoutUrls = this.checkoutUrl.getArray(e);
        Date[] urls = new Date[checkoutUrls.length];
        for (int i = 0; i < checkoutUrls.length; i++)
            urls[i] = new Date(checkoutUrls[i].getExpiry().getTime());
        return urls;
    }

    @Override
    public boolean isSingle() {
        return checkoutUrl.isSingle();
    }

    @Override
    public Class<? extends Date> getReturnType() {
        return Date.class;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "expiry of " + checkoutUrl.toString(e, debug);
    }

}
