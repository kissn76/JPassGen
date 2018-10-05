package characterprofiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class CharacterProfileController {

    private ArrayList<CharacterProfile> characterProfiles = new ArrayList<>();

    public CharacterProfileController() throws IOException {
        loadAll("characterprofiles");
    }

    public ArrayList<CharacterProfile> getCharacterProfiles() {
        return characterProfiles;
    }

    public void save() {
    }

    private void loadAll(String folderPath) throws IOException {
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                characterProfiles.add(load(file.getAbsolutePath()));
            }
        }
    }

    private CharacterProfile load(String filePath) throws IOException {
        Properties prop = new Properties();
        FileInputStream input = new FileInputStream(filePath);
        prop.load(input);
        String lower = prop.getProperty("lower");
        String upper = prop.getProperty("upper");
        String digits = prop.getProperty("digits");
        String punctuation = prop.getProperty("punctuation");
        CharacterProfile characterProfile = new CharacterProfile(lower, upper, digits, punctuation);
        return characterProfile;
    }

}
