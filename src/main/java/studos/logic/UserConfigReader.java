package studos.logic;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

/** @author Sebastian Woźny
 * @version 1.0
 * This class is responsible for reading and saving user data.
 * User data store username, password and language
 * from last sucessful login action.
 * Checkbox remember me runs saving data action,
 * load data action starts automatically
 * when application starts.
 */
public final class UserConfigReader {

/**
 * Private constructor for utility class.
*/
private UserConfigReader() {
}

/**
 * Variable that stores last logged user username.
*/
  private static String usernameString = "";

/**
 * Variable that stores last logged user password.
*/
  private static String passwordString = "";

/**
 * Variable that stores last logged user language.
*/
  private static String languageString = "";

/**
 * Variable that stores information about
 * checkbox "remember".
 * When checkbox is selected then
 * variable is true otherwise is false.
*/
  private static boolean rememberMe;

/**
 * Variable that stores information about
 * actuall application directory.
 * This variable is used to save/delete
 * userData config file.
*/
  private static String directory = UserConfigReader.class.getProtectionDomain()
  .getCodeSource().getLocation().getPath();

/**
 * Variable that stores hardcoded name
 * of the userData config file.
*/
  private static String fileName = "userData.config.xml";

/**
 * Variable that stores path that
 * depends on application directory and
 * harcoded file name.
 * On this path this class knows where to save
 * and where to shearch for config file.
*/
  private static String path = directory + fileName;

/**
 * This method is checking if there is actually existed
 * config file that we can read and have more than 0 chars.
 * @param pathString argument that is taking path to the file.
 * @return true if there is non damaged file.
 * false if there is no non damaged file.
*/
  public static boolean ifConfigIsReady(final String pathString) {
    try {
      final File fXmlFile = new File(pathString);
      return (fXmlFile.exists()
          && fXmlFile.canRead()
          && fXmlFile.length() != 0);
    } catch (final Exception e) {
      return false;
    }
  }

/**
 * This method is checking if userData config has
 * complete login data to be used by application.
 * Boolean is simplified.
 * @return true if there is data to be loaded.
 * false if there is no data to be loaded.
*/
  public static boolean ifDataIsReady() {
    if (rememberMe) {
      return !usernameString.isEmpty()
          && usernameString != null
          && !passwordString.isEmpty()
          && passwordString != null
          && !languageString.isEmpty()
          && languageString != null;
    } else {
      return false;
    }
  }
/**
 * This method is checking if there is actually existed
 * config file that we can read and have more than 0 chars.
 * If there is then file is deleted.
 * Then method is trying to create new userData.config file
 * with variables that user input inside login window before
 * he sucessfuly logged into app.
 * This method is called after sucesfully login.
 * @param usernameInput argument that is taking user username from input.
 * @param passwordInput argument that is taking user password from input.
 * @param languageInput argument that is taking user language from input.
*/
  public static void saveUserData(final String usernameInput,
  final String passwordInput, final String languageInput) {
    if (ifConfigIsReady(path)) {
        final File fXmlFile = new File(path);
        fXmlFile.delete();
    }

    try {
      final DocumentBuilderFactory documentFactory
      = DocumentBuilderFactory.newInstance();
      final DocumentBuilder documentBuilder
      = documentFactory.newDocumentBuilder();
      final Document document = documentBuilder.newDocument();

      // root element (main class)
      final Element root = document.createElement("userData");
      document.appendChild(root);

      // firstname
      final Element remember = document.createElement("remember");
      remember.appendChild(document.createTextNode("True"));
      root.appendChild(remember);

      // lastname
      final Element username = document.createElement("username");
      username.appendChild(document.createTextNode(usernameInput));
      root.appendChild(username);

      // password
      final Element password = document.createElement("password");
      password.appendChild(document.createTextNode(passwordInput));
      root.appendChild(password);

      // language
      final Element language = document.createElement("language");
      language.appendChild(document.createTextNode("pl"));
      root.appendChild(language);

      // create the xml file
      // transform the DOM Object to an XML File
      final TransformerFactory transformerFactory
      = TransformerFactory.newInstance();
      final Transformer transformer = transformerFactory.newTransformer();
      final DOMSource domSource = new DOMSource(document);
      final StreamResult streamResult = new StreamResult(new File(path));

      transformer.transform(domSource, streamResult);

    } catch (final ParserConfigurationException pce) {
    } catch (final TransformerException tfe) {
        }
  }

  /**
  * This function delete userData.config.xml.
  */
  public static void deleteUserData() {
    final File fXmlFile = new File(path);
    fXmlFile.delete();
  }

  /**
  * This method is checking if config file exists.
  * If file exists then application try to load informations
  * from that file. If succeed then app load login data into
  * variables.
  */
  public static void readUserData() {
    if (ifConfigIsReady(path)) {
      try {
        final File fXmlFile = new File(path);
        final DocumentBuilderFactory dbFactory
        = DocumentBuilderFactory.newInstance();
        final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        final Document doc = dBuilder.parse(fXmlFile);

        doc.getDocumentElement().normalize();
        final NodeList nList = doc.getElementsByTagName("userData");

        for (int temp = 0; temp < nList.getLength(); temp++) {

          final Node nNode = nList.item(temp);

          if (nNode.getNodeType() == Node.ELEMENT_NODE) {

            final Element eElement = (Element) nNode;
            if (eElement.getElementsByTagName("remember")
            .item(0).getTextContent().toString().contains("True")) {
              rememberMe = true;
              usernameString = eElement.getElementsByTagName("username")
              .item(0).getTextContent().toString();
              passwordString = eElement.getElementsByTagName("password")
              .item(0).getTextContent().toString();
              languageString = eElement.getElementsByTagName("language")
              .item(0).getTextContent().toString();
            } else {
              rememberMe = false;
            }
          }
        }
      } catch (final Exception e) {
      }
    }
  }

  /**
  * @return variable that
  * store data from userData.config about
  * checkbox "remember me" inside login window.
  */
  public static Boolean getRememberMeStatus() {
    return rememberMe;
  }

  /**
  * @return variable that
  * store data from userData.config about
  * user username from last sucessful login.
  */
  public static String getUsername() {
    return usernameString;
  }

  /**
  * @return variable that
  * store data from userData.config about
  * user password from last sucessful login.
  */
  public static String getPassword() {
    return passwordString;
  }

  /**
  * @return variable that
  * store data from userData.config about
  * user preffered language from last sucessful login.
  */
  public static String getLanguage() {
    return languageString;
  }

}

