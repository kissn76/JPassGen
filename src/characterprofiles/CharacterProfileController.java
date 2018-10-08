package characterprofiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CharacterProfileController {

    public CharacterProfile getCharacterProfile(String filePath) throws IOException {
        return load(filePath);
    }

    public Map<String, CharacterProfile> getCharacterProfiles(String folderPath) throws IOException {
        HashMap<String, CharacterProfile> characterProfiles = new HashMap<>();
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                characterProfiles.put(file.getName(), getCharacterProfile(file.getAbsolutePath()));
            }
        }
        return characterProfiles;
    }

    public void save(CharacterProfile characterProfile, String filePath) throws IOException {
        Properties prop = new Properties();
        FileOutputStream output = new FileOutputStream(filePath);
        prop.setProperty("name", characterProfile.getName());
        prop.setProperty("lower", characterProfile.getLowers());
        prop.setProperty("upper", characterProfile.getUppers());
        prop.setProperty("digits", characterProfile.getDigits());
        prop.setProperty("punctuation", characterProfile.getPunctuations());
        prop.store(output, null);
        if (output != null) {
            output.close();
        }
    }

    private CharacterProfile load(String filePath) throws IOException {
        Properties prop = new Properties();
        FileInputStream input = new FileInputStream(filePath);
        prop.load(input);
        String name = prop.getProperty("name");
        String lower = prop.getProperty("lower");
        String upper = prop.getProperty("upper");
        String digits = prop.getProperty("digits");
        String punctuation = prop.getProperty("punctuation");
        CharacterProfile characterProfile = new CharacterProfile();
        characterProfile.setName(name);
        characterProfile.setLowers(lower);
        characterProfile.setUppers(upper);
        characterProfile.setDigits(digits);
        characterProfile.setPunctuations(punctuation);
        return characterProfile;
    }

}
