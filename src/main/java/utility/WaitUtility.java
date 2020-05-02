package utility;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import java.util.List;

/**
 * This class contains methods that deal with
 * custom wait functions based on a specific condition
 *
 * @author Shringar
 * @version 1.0
 * @since 1.0
 * @see PageObject
 */
public class WaitUtility extends PageObject {

    /**
     * This method waits for the presence of all
     * WebElements within a List of WebElements
     *
     * @param elementList   - List of Web Elements
     */
    public void waitUntilElementsArePresent(List<WebElementFacade> elementList) {
        for (WebElementFacade we : elementList){
            try {
                we.waitUntilPresent();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
