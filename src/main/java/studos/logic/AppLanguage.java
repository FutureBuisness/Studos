package studos.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import studos.logic.UserConfigReader;

/**
 * Back-end language controlling class. Ok... this class will be responsible for
 * language control in this app. To define language we will be using
 * 1.Userdata.config, 2. Desktop language preferences 3. Hardcode main language
 * - ENG
 */
public final class AppLanguage {

    /**
     * Private constructor for utility class.
    */
    private AppLanguage() {
    }

    /**
     * Variable that stores choosed language - TODO.
    */
    private static String choosedLanguage = "en";

    /**
    * This method is checking if there is definied language variable
    * that we can read and have more than 0 chars.
    * @return true if there is there is definied language.
    * false if there is no definied language.
    */
    
    private static boolean languageReadFromConfig() {
        if (UserConfigReader.ifDataIsReady()) {
            String tempChoosedLanguage = UserConfigReader.getLanguage();
            return !tempChoosedLanguage.isEmpty()
                && tempChoosedLanguage != null
                && tempChoosedLanguage.equals("pl")
                || tempChoosedLanguage.equals("en");
        } else {
            return false;
        }
    }

    /**
    * This method returns app choosed language.
    * @return variable that stores app language.
    */
    public static String getLanguage() {
        return choosedLanguage;
    }

    /**
    * This method sets a app language.
    * @param language overwritte choosed app language.
    */
    public static void setLanguage(final String language) {
        choosedLanguage = language;
    }

    /**
    * This method is defining choosed language.
    * 1. Method is checking if in config file is language variable.
    * 2. Method is checking if we can read variable.
    * Otherwise we take language from system
    * or use default.
    */
    private static String languageInitialization() {
        if (languageReadFromConfig()) {
            //If we can read variable from config file
            return choosedLanguage = UserConfigReader.getLanguage();
        } else {
            // here download default system language
            Locale currentLocale = Locale.getDefault();
            if (currentLocale.getLanguage().equals("pl")) {
                //If language is polish
                return choosedLanguage = "pl";
            } else {
                if (currentLocale.getLanguage().equals("en")) {
                    //If language is english
                    return choosedLanguage = "en";
                } else {
                    //If other language then use hardcoded
                    return choosedLanguage = "en";
                }
            }
        }
    }
    /**
     * this is something.
     */

    public static ResourceBundle loadLang() {
        Locale locale = new Locale(choosedLanguage);
        ResourceBundle bundle = ResourceBundle
            .getBundle("studos.languge.messages", locale);
        return bundle;
    }

    public static String getStBundle(String name) {
        return loadLang().getString(name);
    }

}

