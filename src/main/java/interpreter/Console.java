package interpreter;

public class Console implements Output {

    @Override
    public void output(String string) {
        System.out.println(string);
    }
}
