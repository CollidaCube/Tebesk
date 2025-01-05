package com.collidacube.tebesk.skript;

import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class TypesHelper {

    public static <T extends Enum<T>> Parser<T> getEnumParser(Class<T> clazz) {
        return getBasicParser(s -> T.valueOf(clazz, s), T::name);
    }

    public static <T> Parser<T> getBasicParser(Function<String, T> parser, Function<T, String> toString) {
        return new Parser<T>() {
            @Override
            public boolean canParse(@NotNull ParseContext context) {
                return parser != null;
            }

            @Override
            public T parse(@NotNull String s, @NotNull ParseContext context) {
                return parser == null ? null : parser.apply(s);
            }

            @Override
            public @NotNull String toString(T o, int flags) {
                return toString.apply(o);
            }

            @Override
            public @NotNull String toVariableNameString(T o) {
                return toString.apply(o);
            }
        };
    }

    @SuppressWarnings("NullableProblems")
    public static <T> Parser<T> getDefaultParser() {
        return new Parser<T>() {
            @Override
            public boolean canParse(ParseContext context) {
                return false;
            }

            @Override
            public String toString(T o, int flags) {
                return o.toString();
            }

            @Override
            public String toVariableNameString(T o) {
                return o.toString();
            }
        };
    }

}
