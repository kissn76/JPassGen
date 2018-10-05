package characterprofiles;

public class CharacterProfile {

    private final String LOWER;
    private final String UPPER;
    private final String DIGITS;
    private final String PUNCTUATION;

    public CharacterProfile(String lower, String upper, String digits, String punctuation) {
        LOWER = lower;
        UPPER = upper;
        DIGITS = digits;
        PUNCTUATION = punctuation;
    }

    public String getLOWER() {
        return LOWER;
    }

    public String getUPPER() {
        return UPPER;
    }

    public String getDIGITS() {
        return DIGITS;
    }

    public String getPUNCTUATION() {
        return PUNCTUATION;
    }

}
