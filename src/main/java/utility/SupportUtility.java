package utility;

import net.serenitybdd.core.pages.WebElementFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class contains methods that deal with
 * WebElements from the Support and FAQ features
 *
 * @author Shringar
 * @version 1.0
 * @since 1.0
 * @see StrUtility
 */
public class SupportUtility extends StrUtility {

    /**
     * Checks for presence of String in text within each WebElement
     * from a List of WebElements
     *
     * @param elementFacadeList - List of WebElements
     * @param comparisonText    - The comparison string
     * @return                  - true or false based on presence
     *                            or absence of string
     */
    public boolean validateBreadcrumbs(List<WebElementFacade> elementFacadeList, String comparisonText){

        //Fetches text from List of Web Elements and adds them to a List of Strings
        List<String> breadcrumbComponents = new ArrayList<>();
        for (WebElementFacade we : elementFacadeList){
            breadcrumbComponents.add(we.getText());
        }

        //Java stream operation to check for presence of a specific string in a list of strings
        List<String> result = breadcrumbComponents.stream()
                .map(String::toLowerCase)
                .filter(comparisonText.toLowerCase()::contains)
                .collect(Collectors.toList());

        return result.size() > 0;
    }
    /**
     * Checks for Intersection of text from a List of WebElements
     * and a list of Strings
     *
     * @param elementFacadeList - List of WebElements
     * @param comparisonList    - The comparison list
     * @return                  - true or false based on presence
     *                            or absence of common strings
     */
    public boolean validateEmboldened(List<WebElementFacade> elementFacadeList, List<String> comparisonList){

        //Fetches text from List of Web Elements and removed leading "s" and adds them to a List of Strings
        List<String> emboldenedKeywords = new ArrayList<>();
        for (WebElementFacade we : elementFacadeList){
            emboldenedKeywords.add(removeLeadingS(we.getText()));
        }

        //Remove leading s and convert strings within a list to lowercase
        List<String> enteredKeywords = new ArrayList<>();
        for (String s : comparisonList){
            enteredKeywords.add(removeLeadingS(s.toLowerCase()));
        }

        //Java stream operation for Intersection of two lists
        List<String> result = emboldenedKeywords.stream()
                .map(String::toLowerCase)
                .filter(enteredKeywords::contains)
                .collect(Collectors.toList());

        return result.size() > 0;
    }

    /**
     * This method clicks on first web element from a list of
     * web elements that contains a specific string
     *
     * @param elementFacadeList     -   List of WebElements
     * @param comparisonText        -   The comparison text
     */
    public void clickIfElementsContainString(List<WebElementFacade> elementFacadeList, String comparisonText) {

        for (WebElementFacade we : elementFacadeList){
            if (we.getText().toLowerCase().contains(comparisonText.toLowerCase())){
                we.waitUntilClickable().click();
                break;
            }
        }
    }
}
