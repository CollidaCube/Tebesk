package com.collidacube.tebesk.skript;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.registrations.Classes;
import io.tebex.sdk.obj.Package;
import io.tebex.sdk.obj.*;

public class Types {

    static {
        Classes.registerClass(new ClassInfo<>(BasketType.class, "baskettype")
                .user("basket ?type")
                .name("Basket Type")
                .description("The type of packages in the tebex basket: SINGLE, SUBSCRIPTION, or BOTH")
                .since("1.0.0")
                .parser(TypesHelper.getEnumParser(BasketType.class)));

        Classes.registerClass(new ClassInfo<>(Coupon.class, "coupon")
                .user("coupons?")
                .name("Coupon")
                .description("A tebex coupon")
                .since("1.0.0"));

        Classes.registerClass(new ClassInfo<>(Coupon.Discount.class, "coupondiscount")
                .user("(coupon )?discounts?")
                .name("Coupon Discount")
                .description("The discount of a tebex coupon")
                .since("1.0.0")
                .parser(TypesHelper.getBasicParser(null, d -> {
                    if (d.getType() == DiscountType.PERCENTAGE) return d.getPercentage() + "%";
                    else return String.valueOf(d.getValue());
                })));

        Classes.registerClass(new ClassInfo<>(DiscountType.class, "coupondiscounttype")
                .user("(coupon ?)?discount ?type")
                .name("Coupon Discount Type")
                .description("The type of tebex coupon discount: PERCENTAGE or AMOUNT")
                .since("1.0.0")
                .parser(TypesHelper.getEnumParser(DiscountType.class)));

        Classes.registerClass(new ClassInfo<>(Coupon.Effective.class, "couponscope")
                .user("coupon ?scope")
                .name("(Effective) Coupon Scope")
                .description("What packages/categories are within the scope of the tebex coupon. In other words, the packages/categories the coupon is effective on.")
                .since("1.0.0"));

        Classes.registerClass(new ClassInfo<>(Coupon.Effective.EffectiveType.class, "couponscopetype")
                .user("coupon ?scope ?type")
                .name("(Effective) Coupon Scope Type")
                .description("The tebex coupon will be effective on either of these types: PACKAGE or CATEGORY")
                .since("1.0.0")
                .parser(TypesHelper.getEnumParser(Coupon.Effective.EffectiveType.class)));

        Classes.registerClass(new ClassInfo<>(Coupon.Expiry.class, "couponexpiry")
                .user("coupon ?expiry( ?date)")
                .name("Coupon Expiry")
                .description("The expiry date of the tebex coupon")
                .since("1.0.0"));

        Classes.registerClass(new ClassInfo<>(Package.Category.class, "storeguicategory")
                .user("store ?categor(y|ies)")
                .name("Store Category")
                .description("A tebex category that contains packages and subcategories")
                .since("1.0.0")
                .parser(TypesHelper.getDefaultParser()));

        Classes.registerClass(new ClassInfo<>(Package.class, "categorypackage")
                .user("(store|category) ?packages?")
                .name("Category Package")
                .description("A purchasable tebex package")
                .since("1.0.0")
                .parser(TypesHelper.getBasicParser(null, Package::getName)));

        Classes.registerClass(new ClassInfo<>(PlayerLookupInfo.class, "playerlookupinfo")
                .user("player ?look-?up ?info")
                .name("Player Lookup Info")
                .description("Information about a player on your tebex webstore")
                .since("1.0.0")
                .parser(TypesHelper.getDefaultParser()));

        Classes.registerClass(new ClassInfo<>(PlayerLookupInfo.Payment.class, "payment")
                .user("((transactions?)|(payments?))")
                .name("Payment")
                .description("Information on a tebex payment")
                .since("1.0.0")
                .parser(TypesHelper.getBasicParser(null, PlayerLookupInfo.Payment::getTxnId)));

        Classes.registerClass(new ClassInfo<>(QueuedCommand.class, "queuedcommand")
                .user("queued ?commands?")
                .name("Queued Command")
                .description("A command in queue from a purchase")
                .since("1.0.0")
                .parser(TypesHelper.getBasicParser(null, QueuedCommand::getCommand)));
    }

}
