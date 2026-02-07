package hw02_dynamic_programming_and_testing.app.render;

public class ResultRenderer {

    public static void render(String result) {
        System.out.println(result);
    }

    public static void renderError(Exception e) {
        System.err.println("Ошибка: " + e.getMessage());
    }
}
