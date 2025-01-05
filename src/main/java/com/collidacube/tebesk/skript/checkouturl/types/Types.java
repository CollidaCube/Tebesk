package com.collidacube.tebesk.skript.checkouturl.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.registrations.Classes;
import com.collidacube.tebesk.skript.TypesHelper;
import io.tebex.sdk.obj.CheckoutUrl;

public class Types {

    static {
        Classes.registerClass(new ClassInfo<>(CheckoutUrl.class, "checkouturl")
                .user("checkout ?url")
                .name("Checkout Url")
                .description("A temporary url to checkout a tebex basket")
                .since("1.0.0")
                .parser(TypesHelper.getBasicParser(null, CheckoutUrl::getUrl)));
    }

}
