/**
 *Info about this package doing something for package-info.java file.
 */

package studos.logic;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * I18N utility class..
 */
public final class I18N {

    /** the current selected Locale. */
    private static final ObjectProperty<Locale> LOCALE;

    /**
     * class.
     */
    private I18N() {
    }

    static {
        LOCALE = new SimpleObjectProperty<>(getDefaultLocale());
        LOCALE.addListener((observable, oldValue, newValue)
            -> Locale.setDefault(newValue));
    }

    /**
    * This method is checking if there is definied language variable.
    * that we can read and have more than 0 chars.
    * @return true if there is there is definied language.
    *        false if there is no definied language.
    */

    private static boolean languageReadFromConfig() {
        if (UserConfigReader.ifDataIsReady()) {
            final String tempChoosedLanguage = UserConfigReader.getLanguage();
            return !tempChoosedLanguage.isEmpty()
                && tempChoosedLanguage != null
                && tempChoosedLanguage.equals("pl")
                || tempChoosedLanguage.equals("en");
        } else {
            return false;
        }
    }

    /**
     * get the supported Locales.
     *
     * @return List of Locale objects.
     */
    public static List<Locale> getSupportedLocales() {
        return new ArrayList<>(Arrays.asList(Locale.forLanguageTag("pl"),
        Locale.forLanguageTag("en")));
    }

    /**
     * Default.
     * @return the defaultLocale but firstly checking if the locale
     *     was rememberd if not it takes the systemDefaultLocale
     *     if contained in the supported locales, english otherwise.
     */
    public static Locale getDefaultLocale() {
        /*
         * return userData if true.
         */
        Locale sysDefault = languageReadFromConfig()
            ? Locale.forLanguageTag(UserConfigReader.getLanguage())
            : Locale.getDefault();
        /*
         * Change Locale from full format to language.
         */
        sysDefault =
            sysDefault.getLanguage().equals(new Locale("pl-PL").getLanguage())
            || sysDefault.getLanguage().equals(new Locale("pl").getLanguage())
            ? Locale.forLanguageTag("pl") : Locale.forLanguageTag("en");
        return getSupportedLocales()
            .contains(sysDefault) ? sysDefault : Locale.forLanguageTag("en");
    }

    /**
     *  Locale.
     * @return Locale.
     */

    public static Locale getLocale() {
        return LOCALE.get();
    }
    /**
     * sdfsd.
     * @return sdfsdf
     */

    public static String getLanguage() {
        return getLocale().getLanguage();
    }

    /**
     * setLocale manually.
     * @param locale set.
     */

    public static void setLocale(final Locale locale) {
        localeProperty().set(locale);
        Locale.setDefault(locale);
    }

    /**
     * propertyForLocale.
     * @return locale.
     */

    public static ObjectProperty<Locale> localeProperty() {
        return LOCALE;
    }

    /**
     * gets the string with the given key from the resource bundle for
     * the current locale and uses it as first argument
     * to MessageFormat.format,
     * passing in the optional args and returning the result.
     *
     * @param key
     *         message key
     * @param args
     *         optional arguments for the message
     * @return localized formatted string
     */
    public static String get(final String key, final Object... args) {
        final ResourceBundle bundle = ResourceBundle
            .getBundle("studos.languages.messages", getLocale());
        return MessageFormat.format(bundle.getString(key), args);
    }

    /**
     * creates a String binding to a localized String
     * for the given message bundle key.
     *
     * @param key
     *         key
     * @param args
     *         args.
     * @return String binding
     */
    public static StringBinding
        createStringBinding(final String key, final Object... args) {
        return Bindings.createStringBinding(() -> get(key, args), LOCALE);
    }

    /**
     * creates a String Binding to a localized String
     * that is computed by calling the given func.
     *
     * @param func
     *         function called on every change
     * @return StringBinding
     */
    public static StringBinding
        createStringBinding(final Callable<String> func) {
        return Bindings.createStringBinding(func, LOCALE);
    }

}
