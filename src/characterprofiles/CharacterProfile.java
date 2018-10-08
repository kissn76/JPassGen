package characterprofiles;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class CharacterProfile {

    private File filePath;
    private String name;
    private String lowers;
    private String uppers;
    private String digits;
    private String punctuations;

    public File getFilePath() {
        return this.filePath;
    }

    public void generateFilePath(File folder) {
        try {
            String tmp = new Random().nextInt(Integer.MAX_VALUE) + "" + System.currentTimeMillis();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(tmp.getBytes("utf8"));
            this.filePath = new File(folder, String.format("%040x", new BigInteger(1, digest.digest())));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void setFilePath(File filePath) {
        this.filePath = filePath;
    }

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
