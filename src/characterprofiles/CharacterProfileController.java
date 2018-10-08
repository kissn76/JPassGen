package characterprofiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CharacterProfileController {

    public CharacterProfile getCharacterProfile(File filePath) throws IOException {
        return load(filePath);
    }

    public Map<String, CharacterProfile> getCharacterProfiles(File folderPath) throws IOException {
        HashMap<String, CharacterProfile> characterProfiles = new HashMap<>();
        File[] listOfFiles = folderPath.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                characterProfiles.put(file.getName(), getCharacterProfile(file));
            }
        }
        return characterProfiles;
    }

    public void saveAsNew(CharacterProfile characterProfile, File folderPath) throws IOException {
        characterProfile.generateFilePath(folderPath);
        save(characterProfile);
    }

    public void save(CharacterProfile characterProfile) throws IOException {
        Properties prop = new Properties();

        if (characterProfile.getName() != null) {
            prop.setProperty("name", characterProfile.getName());
        }
        if (characterProfile.getLowers() != null) {
            prop.setProperty("lower", characterProfile.getLowers());
        }
        if (characterProfile.getUppers() != null) {
            prop.setProperty("upper", characterProfile.getUppers());
        }
        if (characterProfile.getDigits() != null) {
            prop.setProperty("digits", characterProfile.getDigits());
        }
        if (characterProfile.getPunctuations() != null) {
            prop.setProperty("punctuation", characterProfile.getPunctuations());
        }

        FileOutputStream output = new FileOutputStream(characterProfile.getFilePath());
        prop.store(output, null);
        if (output != null) {
            output.close();
        }
    }

    private CharacterProfile load(File filePath) throws IOException {
        Properties prop = new Properties();
        FileInputStream input = new FileInputStream(filePath);
        prop.load(input);
        String name = prop.getProperty("name");
        String lower = prop.getProperty("lower");
        String upper = prop.getProperty("upper");
        String digits = prop.getProperty("digits");
        String punctuation = prop.getProperty("punctuation");
        CharacterProfile characterProfile = new CharacterProfile();
        characterProfile.setFilePath(filePath);
        characterProfile.setName(name);
        characterProfile.setLowers(lower);
        characterProfile.setUppers(upper);
        characterProfile.setDigits(digits);
        characterProfile.setPunctuations(punctuation);
        return characterProfile;
    }

    public void delete(CharacterProfile characterProfile) {
        characterProfile.getFilePath().delete();
    }

}
