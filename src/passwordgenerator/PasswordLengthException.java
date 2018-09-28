package passwordgenerator;
/**
 * Password minimum length, specified minimum number of each character type is longer then parameter
 * represented length.
 */
public class PasswordLengthException extends Exception {

    public PasswordLengthException(int minLength, int length) {
        super("Sum of minimum number of character type is higher (" + minLength + ") then password length (" + length + ")!");
    }

}
