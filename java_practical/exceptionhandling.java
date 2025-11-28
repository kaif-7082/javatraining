public class exceptionhandling{

    public static void main(String[] args) {

        System.out.println("---- Example 1: Try and Catch ----");
        try {
            int a = 10, b = 0;
            int result = a / b;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero");
        }

        System.out.println("\n---- Example 2: Multiple Catch ----");
        try {
            int arr[] = {1, 2, 3};
            System.out.println(arr[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Invalid array index");
        } catch (ArithmeticException e) {
            System.out.println("Math error");
        } catch (Exception e) {
            System.out.println("Some other error occurred");
        }

        System.out.println("\n---- Example 3: Finally Block ----");
        try {
            int x = 100 / 2;
            System.out.println("Result = " + x);
        } catch (Exception e) {
            System.out.println("Error occurred");
        } finally {
            System.out.println("This will always run (Finally Block)");
        }

        System.out.println("\n---- Example 4: throw Keyword ----");
        try {
            checkAge(15);
        } catch (ArithmeticException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        System.out.println("\nProgram ended normally!");
    }

    static void checkAge(int age) {
        if (age < 18) {
            throw new ArithmeticException("Age must be 18 or above");
        } else {
            System.out.println("Age is valid");
        }
    }
}
