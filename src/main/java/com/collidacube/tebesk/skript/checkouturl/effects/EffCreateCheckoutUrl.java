package com.collidacube.tebesk.skript.checkouturl.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.skript.util.AsyncEffect;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.collidacube.tebesk.Tebesk;
import io.tebex.sdk.obj.CheckoutUrl;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import io.tebex.sdk.obj.Package;

public class EffCreateCheckoutUrl extends AsyncEffect {

    static {
        Skript.registerEffect(EffCreateCheckoutUrl.class,
                "create checkout[ ]url with package %package% for [player] %player% and save to %checkouturl%",
                "create checkout[ ]url with package id %number% for [player] %player% and save to %checkouturl%");
    }

    private Expression<Package> packageObj;
    private Expression<Number> packageId;
    private Expression<Player> player;

    private Expression<CheckoutUrl> checkoutUrlResult;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        getParser().setHasDelayBefore(Kleenean.TRUE);
        if (matchedPattern == 0) packageObj = (Expression<Package>) exprs[0];
        else if (matchedPattern == 1) packageId = (Expression<Number>) exprs[0];
        player = (Expression<Player>) exprs[1];
        checkoutUrlResult = (Expression<CheckoutUrl>) exprs[2];

        if (!Changer.ChangerUtils.acceptsChange(checkoutUrlResult, Changer.ChangeMode.SET, CheckoutUrl.class)) {
            Skript.error("'" + checkoutUrlResult + "' cannot be set", ErrorQuality.SEMANTIC_ERROR);
            return false;
        }
        return true;
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    protected void execute(Event e) {
        int _packageId;
        if (packageObj != null)
            _packageId = packageObj.getSingle(e).getId();
        else if (packageId != null)
            _packageId = packageId.getSingle(e).intValue();
        else throw new RuntimeException("packageId AND packageObj are null. This is a bug. Please report this to https://github.com/CollidaCube/Tebesk");

        String username = player.getSingle(e).getName();
        Tebesk.getTebexSdk()
                .createCheckoutUrl(_packageId, username)
                .thenAccept(checkoutUrl -> checkoutUrlResult.change(e, CollectionUtils.array(checkoutUrl), Changer.ChangeMode.SET));
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public String toString(Event e, boolean debug) {
        return packageObj == null
                ? "create checkout url with package id \"" + packageId.getSingle(e).intValue() + "\" for player \"" + player.getSingle(e).getName() + "\""
                : "create checkout url with package \"" + packageObj.getSingle(e).getName() + "\" for player \"" + player.getSingle(e).getName() + "\"";
    }

}
