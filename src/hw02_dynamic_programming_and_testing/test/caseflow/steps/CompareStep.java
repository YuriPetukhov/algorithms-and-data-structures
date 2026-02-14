package hw02_dynamic_programming_and_testing.test.caseflow.steps;

import hw02_dynamic_programming_and_testing.test.caseflow.CaseContext;
import hw02_dynamic_programming_and_testing.test.caseflow.CaseStep;
import hw02_dynamic_programming_and_testing.test.model.TestResult;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

public class CompareStep implements CaseStep {

    @Override
    public void execute(CaseContext ctx) {
        String exp = ctx.expectedNorm();
        String act = ctx.actualNorm();

        boolean ok;

        String expT = exp == null ? "" : exp.trim();
        String actT = act == null ? "" : act.trim();

        Double expNum = tryParseDoubleSafe(expT);
        Double actNum = tryParseDoubleSafe(actT);
        if (expNum != null && actNum != null) {
            ok = almostEquals(expNum, actNum);
        }
        else if (looksLikeInteger(expT) && looksLikeInteger(actT)
                && (expT.length() >= 50 || actT.length() >= 50)) {
            ok = new BigInteger(expT).equals(new BigInteger(actT));
        }
        else {
            ok = expT.equals(actT);
        }

        if (ok) {
            ctx.setResult(TestResult.passed(ctx.testCase().name(), ctx.timeNanos()));
        } else {
            ctx.setResult(TestResult.failed(ctx.testCase().name(), ctx.timeNanos(), exp, act));
        }
    }

    private static Double tryParseDoubleSafe(String t) {
        if (t == null) return null;
        if (t.length() > 200) return null;

        try {
            return Double.parseDouble(t.replace(',', '.'));
        } catch (Exception e) {
            return null;
        }
    }

    private static boolean looksLikeInteger(String s) {
        if (s == null) return false;
        int len = s.length();
        if (len == 0) return false;
        int i = (s.charAt(0) == '-') ? 1 : 0;
        if (i == len) return false;
        for (; i < len; i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') return false;
        }
        return true;
    }

    private static boolean almostEquals(double expected, double actual) {
        double absEps = 2e-8;
        double relEps = 2e-8;

        double diff = Math.abs(expected - actual);
        double tol  = Math.max(absEps, relEps * Math.max(1.0, Math.abs(expected)));
        return diff <= tol;
    }



}
