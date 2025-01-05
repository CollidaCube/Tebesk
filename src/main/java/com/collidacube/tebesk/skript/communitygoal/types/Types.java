package com.collidacube.tebesk.skript.communitygoal.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.registrations.Classes;
import com.collidacube.tebesk.Tebesk;
import com.collidacube.tebesk.skript.TypesHelper;
import io.tebex.sdk.obj.CommunityGoal;

public class Types {

    static {
        Classes.registerClass(new ClassInfo<>(CommunityGoal.Status.class, "communitygoalstatus")
                .user("community ?goal ?status")
                .name("Community Goal Status")
                .description("The current status of a community goal: ACTIVE, COMPLETED, or DISABLED")
                .since("1.0.0")
                .parser(TypesHelper.getEnumParser(CommunityGoal.Status.class)));

        Classes.registerClass(new ClassInfo<>(CommunityGoal.class, "communitygoal")
                .user("community ?goals?")
                .name("Community Goal")
                .description("A tebex community goal")
                .since("1.0.0"));
    }

}
