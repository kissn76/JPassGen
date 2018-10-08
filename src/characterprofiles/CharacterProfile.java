package characterprofiles;

public class CharacterProfile {

    private String name;
    private String lowers;
    private String uppers;
    private String digits;
    private String punctuations;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLowers() {
        return this.lowers;
    }

    public void setLowers(String lowers) {
        this.lowers = lowers;
    }

    public String getUppers() {
        return this.uppers;
    }

    public void setUppers(String uppers) {
        this.uppers = uppers;
    }

    public String getDigits() {
        return this.digits;
    }

    public void setDigits(String digits) {
        this.digits = digits;
    }

    public String getPunctuations() {
        return this.punctuations;
    }

    public void setPunctuations(String punctuations) {
        this.punctuations = punctuations;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
