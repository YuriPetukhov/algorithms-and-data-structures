package hw01_loops_and_recursion;

import hw01_loops_and_recursion.pattern.*;
import hw01_loops_and_recursion.render.Renderer;

public class HarryPotterSquare {
    public static void main(String[] args) {

        System.out.println("\n=== Заклинание 01 ===");
        Renderer.print(
                25,
                new Pattern01('#', '.').build());

        System.out.println("\n=== Заклинание 02 ===");
        Renderer.print(
                25,
                new Pattern02('#', '.').build());

        System.out.println("\n=== Заклинание 03 ===");
        Renderer.print(
                25,
                new Pattern03('#', '.').build());

        System.out.println("\n=== Заклинание 04 ===");
        Renderer.print(
                25,
                new Pattern04(6, '#', '.').build());

        System.out.println("\n=== Заклинание 05 ===");
        Renderer.print(
                25,
                new Pattern05(0, 2, 2, '#', '.').build());

        System.out.println("\n=== Заклинание 06 ===");
        Renderer.print(
                25,
                new Pattern06_07(10, 10, 15, '.', '#').build());

        System.out.println("\n=== Заклинание 07 ===");
        Renderer.print(
                25,
                new Pattern06_07(16, 16, 9, '#', '.').build());

        System.out.println("\n=== Заклинание 08 ===");
        Renderer.print(
                25,
                new Pattern08('#', '.').build());

        System.out.println("\n=== Заклинание 09 ===");
        Renderer.print(
                25,
                new Pattern09(11, '#', '.').build());

        System.out.println("\n=== Заклинание 10 ===");
        Renderer.print(
                25,
                new Pattern10('#', '.').build());

        System.out.println("\n=== Заклинание 11 ===");
        Renderer.print(
                25,
                new Pattern11(1, '#', '.').build());

        System.out.println("\n=== Заклинание 12 ===");
        Renderer.print(
                25,
                new Pattern12('#', '.').build());

        System.out.println("\n=== Заклинание 13 ===");
        Renderer.print(
                25,
                new Pattern13(4, '#', '.').build());

        System.out.println("\n=== Заклинание 14 ===");
        Renderer.print(
                25,
                new Pattern14('#', '.').build());

        System.out.println("\n=== Заклинание 15 ===");
        Renderer.print(
                25,
                new Pattern15(15, 5, '#', '.').build());

        System.out.println("\n=== Заклинание 16 ===");
        Renderer.print(
                25,
                new Pattern16(9, '#', '.').build());

        System.out.println("\n=== Заклинание 17 ===");
        Renderer.print(
                25,
                new Pattern17('#', '.').build());

        System.out.println("\n=== Заклинание 18 ===");
        Renderer.print(
                25,
                new Pattern18(2, '#', '.').build());

        System.out.println("\n=== Заклинание 19 ===");
        Renderer.print(
                25,
                new Pattern19(1, '#', '.').build());

        System.out.println("\n=== Заклинание 20 ===");
        Renderer.print(
                25,
                new Pattern20(1, '#', '.').build());

        System.out.println("\n=== Заклинание 21 ===");
        Renderer.print(
                25,
                new Pattern21(0, '#', '.').build());

        System.out.println("\n=== Заклинание 22 ===");
        Renderer.print(
                25,
                new Pattern22(2, 2, '#', '.').build());

        System.out.println("\n=== Заклинание 23 ===");
        Renderer.print(
                25,
                new Pattern23(2, 1, '#', '.').build());

        System.out.println("\n=== Заклинание 24 ===");
        Renderer.print(
                25,
                new Pattern24('#', '.').build());

        System.out.println("\n=== Заклинание 25 ===");
        Renderer.print(
                25,
                new Pattern25(5, '#', '.').build());

    }
}