package hw02_dynamic_programming_and_testing.app.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DoubleResultFormatter {

    public static String formatResult(double x) {
        BigDecimal bd = BigDecimal.valueOf(x)
                .setScale(11, RoundingMode.HALF_UP)
                .stripTrailingZeros();

        String s = bd.toPlainString();
        if (!s.contains(".")) s += ".0";
        return s;
    }
}
