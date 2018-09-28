public class NewPassword {

    public static void main(String[] args) {
        PasswordGenerator passwordGenerator = new PasswordGenerator();

        if (!args[0].isEmpty()) {
            try {
                System.out.println(passwordGenerator.generate());
            } catch (PasswordLengthException e) {
                System.out.println(e.getMessage());
            }
        } else {
        }
    }

}
