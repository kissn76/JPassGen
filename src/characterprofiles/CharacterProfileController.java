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

    public void save(CharacterProfile characterProfile, String characterProfilesFolder) throws IOException {
        Properties prop = new Properties();

        if (characterProfile.getFilename() == null) {
            characterProfile.setFilename();
        }
        prop.setProperty("filename", characterProfile.getFilename());
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

        FileOutputStream output = new FileOutputStream(characterProfilesFolder + characterProfile.getFilename());
        prop.store(output, null);
        if (output != null) {
            output.close();
        }
    }

    private CharacterProfile load(String filePath) throws IOException {
        Properties prop = new Properties();
        FileInputStream input = new FileInputStream(filePath);
        prop.load(input);
        String filename = prop.getProperty("filename");
        String name = prop.getProperty("name");
        String lower = prop.getProperty("lower");
        String upper = prop.getProperty("upper");
        String digits = prop.getProperty("digits");
        String punctuation = prop.getProperty("punctuation");
        CharacterProfile characterProfile = new CharacterProfile();
        characterProfile.setFilename(filename);
        characterProfile.setName(name);
        characterProfile.setLowers(lower);
        characterProfile.setUppers(upper);
        characterProfile.setDigits(digits);
        characterProfile.setPunctuations(punctuation);
        return characterProfile;
    }

    public void delete(CharacterProfile characterProfile, String characterProfilesFolder) {
        File file = new File(characterProfilesFolder + characterProfile.getFilename());
        file.delete();
    }

}
