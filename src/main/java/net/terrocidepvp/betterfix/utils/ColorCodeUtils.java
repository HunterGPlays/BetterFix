package net.terrocidepvp.betterfix.utils;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class ColorCodeUtils
{
    private static String translateAlternateColorCodes(final char altColorChar, final String textToTranslate) {
        final char[] b = textToTranslate.toCharArray();
        for (int i = 0; i < b.length - 1; ++i) {
            if (b[i] == altColorChar && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
                b[i] = '§';
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }
        return new String(b);
    }
    
    public static String translate(final String string) {
        return translateAlternateColorCodes('&', string);
    }
    
    public static List<String> translate(final List<String> string) {
        return string.stream().map((Function<? super Object, ?>)ColorCodeUtils::translate).collect((Collector<? super Object, ?, List<String>>)Collectors.toCollection((Supplier<R>)ArrayList::new));
    }
}
