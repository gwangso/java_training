package main;
import java.util.Scanner;
public class Test {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine();
            Test test = new Test();
            Long id = test.numberTest(input);
            if (id != null){
                System.out.println(id);
                break;
            }
        }
    }
    public Long numberTest(String input) {
        try {
            Long id = Long.parseLong(input);
            return id;
        }catch (Exception e) {
            System.out.println("숫자가 아닙니다." + e.toString());
        }
        return null;
    }
}
