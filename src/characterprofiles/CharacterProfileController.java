package characterprofiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CharacterProfileController {
    private String folderPath = "./";

    public CharacterProfile getCharacterProfile(String filePath) throws IOException {
        return load(filePath);
    }

    public Map<String, CharacterProfile> getCharacterProfiles() throws IOException {
        HashMap<String, CharacterProfile> characterProfiles = new HashMap<>();
        File folder = new File(this.folderPath);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                characterProfiles.put(file.getName(), load(file.getAbsolutePath()));
            }
        }
        return characterProfiles;
    }

    public void save(CharacterProfile characterProfile, String filePath) throws IOException {
        Properties prop = new Properties();
        FileOutputStream output = new FileOutputStream(filePath);
        prop.setProperty("lower", characterProfile.getLOWER());
        prop.setProperty("upper", characterProfile.getUPPER());
        prop.setProperty("digits", characterProfile.getDIGITS());
        prop.setProperty("punctuation", characterProfile.getPUNCTUATION());
        prop.store(output, null);
        if (output != null) {
            output.close();
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
