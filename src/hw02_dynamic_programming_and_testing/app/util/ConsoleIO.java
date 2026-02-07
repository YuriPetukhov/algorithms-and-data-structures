package hw02_dynamic_programming_and_testing.app.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ConsoleIO {

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

    public void print(String s) {
        System.out.print(s);
    }

    public void println(String s) {
        System.out.println(s);
    }

    public String readLine() throws Exception {
        return br.readLine();
    }
}
