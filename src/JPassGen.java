import gui.JPassGenGui;
import javafx.application.Application;
import passwordgenerator.PasswordGenerator;
import passwordgenerator.PasswordLengthException;

public class JPassGen {

    public static void main(String[] args) {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String output = "";

        int passLength = passwordGenerator.getDefaultLength();
        try {
            if (args.length > 0) {
                if (args[0].equals("gui")) {
                    Application.launch(JPassGenGui.class, args);
                } else if (args[0].equals("-h") || args[0].equals("--help")) {
                    output += "\njava JPassGen gui" + "\n";
                    output += "              Start graphical frontend." + "\n\n";
                    output += "java JPassGen [length] [lower] [upper] [digit] [punctuation]" + "\n\n";
                    output += "length       - Length of the password." + "\n";
                    output += "                0 - default length" + "\n";
                    output += "lower        - Minimum number of lower case characters in password." + "\n";
                    output += "upper        - Minimum number of upper case characters in password." + "\n";
                    output += "digit        - Minimum number of digit characters in password." + "\n";
                    output += "punctuation  - Minimum number of punctuation characters in password." + "\n";
                    output += "               -1 - disable" + "\n";
                    output += "                0 - random number" + "\n";
                    output += "               >0 - digit minimum number" + "\n\n";
                    output += "Examples:" + "\n";
                    output += "---------" + "\n";
                    output += "java JPassGen                      // Default length (" + passwordGenerator.getDefaultLength() + "characters) random password with default settings" + "\n";
                    output += "java JPassGen 12                   // 12 characters length random password with default settings" + "\n";
                    output += "java JPassGen 12 -1                // 12 characters length random password, lowercase characters disabled " + "\n";
                    output += "java JPassGen 12 0 3               // 12 characters length random password, random number lowercase characters, minimum 3 uppercase characters" + "\n";
                    output += "java JPassGen 0 0 -1 3 2           // Default length random password, random number lowercase characters, disable uppercase, minimum 3 digit and minimum 2 punctuation characters" + "\n";
                } else if (Integer.parseInt(args[0]) > 0) {
                    passLength = Integer.parseInt(args[0]);
                }

                if (args.length > 1) {
                    if (Integer.parseInt(args[1]) < 0) {
                        passwordGenerator.disableLower();
                    } else {
                        passwordGenerator.enableLower();
                        passwordGenerator.setMinNumberLower(Integer.parseInt(args[1]));
                    }

                    if (args.length > 2) {
                        if (Integer.parseInt(args[2]) < 0) {
                            passwordGenerator.disableUpper();
                        } else {
                            passwordGenerator.enableUpper();
                            passwordGenerator.setMinNumberUpper(Integer.parseInt(args[2]));
                        }

                        if (args.length > 3) {
                            if (Integer.parseInt(args[3]) < 0) {
                                passwordGenerator.disableDigit();
                            } else {
                                passwordGenerator.enableDigit();
                                passwordGenerator.setMinNumberDigit(Integer.parseInt(args[3]));
                            }

                            if (args.length > 4) {
                                if (Integer.parseInt(args[4]) < 0) {
                                    passwordGenerator.disablePunctuation();
                                } else {
                                    passwordGenerator.enablePunctuation();
                                    passwordGenerator.setMinNumberPunctuation(Integer.parseInt(args[4]));
                                }
                            }
                        }
                    }
                }
            }
            if (output.length() == 0) {
                output = passwordGenerator.generate(passLength);
            }
            System.out.println(output);
        } catch (NumberFormatException e) {
            System.out.println("Type:\n");
            System.out.println("java JPassGen --help");
            System.out.println("or");
            System.out.println("java JPassGen -h\n");
            System.out.println("for help!");
        } catch (PasswordLengthException e) {
            System.out.println(e.getMessage());
        }
    }

}
