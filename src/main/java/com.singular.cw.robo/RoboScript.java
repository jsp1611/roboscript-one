package com.singular.cw.robo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * Implement syntax highlighting for Roboscript.
 * <p>
 * https://www.codewars.com/kata/roboscript-number-1-implement-syntax-highlighting
 *
 * @author jon
 */
public class RoboScript {

    private static final String SPAN_START = "<span style=\"color: %s\">";

    private static final String SPAN_END = "</span>";

    private enum ElemType {
        F_INSTR("pink"),
        L_INSTR("red"),
        R_INSTR("green"),
        NUM("orange");

        private String color;

        ElemType(String color) {
            this.color = color;
        }

        String getColor() {
            return color;
        }
    }

    private static final Supplier<Map<Character, ElemType>> HL_TYPE_SUPPLIER = () -> {
        final Map<Character, ElemType> map = new HashMap<>();
        map.put('F', ElemType.F_INSTR);
        map.put('L', ElemType.L_INSTR);
        map.put('R', ElemType.R_INSTR);
        IntStream.range(48, 58).forEach(i -> map.put((char) i, ElemType.NUM));
        return map;
    };

    private static Map<Character, ElemType> HL_TYPE = HL_TYPE_SUPPLIER.get();

    private static final Supplier<Set<Character>> HL_CHARSET_SUPPLIER = () -> {
        final Set<Character> set = new HashSet<>();
        set.add('F');
        set.add('L');
        set.add('R');
        IntStream.range(48, 58).forEach(i -> set.add((char) i));
        return set;
    };

    private static final Set<Character> HL_CHARSET = HL_CHARSET_SUPPLIER.get();

    public static String highlight(final String code) {
        final StringBuilder builder = new StringBuilder();
        boolean highlighting = false;
        ElemType lastTypeElemType = null;
        for (int index = 0; ; ) {
            if (index >= code.length()) {
                if (highlighting) {
                    builder.append(SPAN_END);
                }
                return builder.toString();
            }
            char current = code.charAt(index);
            ElemType currentElemType = HL_TYPE.get(current);
            if (highlighting) {
                if (currentElemType == lastTypeElemType) {
                    builder.append(current);
                    index++;
                } else {
                    builder.append(SPAN_END);
                    highlighting = false;
                }
            } else {
                if (HL_CHARSET.contains(current)) {
                    final String color = HL_TYPE.get(current).getColor();
                    builder.append(String.format(SPAN_START, color));
                    highlighting = true;
                } else {
                    builder.append(current);
                    index++;
                }
            }
            lastTypeElemType = currentElemType;
        }
    }
}
