package hw02_dynamic_programming_and_testing.run;

public class AlgorithmRunApp {

    public static void main(String[] args) {

        SelectedTask selected = provider.get(args);
        String result = selected.task().run(selected.input());
        System.out.println(result);
    }
}
