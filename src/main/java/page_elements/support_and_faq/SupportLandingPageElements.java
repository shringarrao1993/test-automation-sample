package page_elements.support_and_faq;

import net.serenitybdd.core.pages.*;
import java.util.Arrays;
import java.util.List;
import constants.URL;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.DefaultUrl;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utility.SupportUtility;

/**
 * This class locates Page Objects in the Support Landing Page
 * using the Page Object Model provided by Serenity
 *
 * @author Shringar
 * @version 1.0
 * @since  1.0
 * @see PageObject
 */
@DefaultUrl(URL.SupportURL.landingPage)
public class SupportLandingPageElements extends PageObject {

    public SupportLandingPageElements(WebDriver driver){
        super(driver);
    }

    SupportUtility supportUtility = new SupportUtility();

    //Header Elements

    @FindBy(className = "logo")
    protected WebElementFacade logo;

    @FindBy(id = "user-nav")
    protected WebElementFacade navigationLinks;

    @FindBy(className = "submit-a-request")
    protected WebElementFacade submitARequestNav;

    //Welcome Message Element

    @FindBy(className = "welcome-message")
    protected WebElementFacade welcomeMessage;

    //Search Element

    @FindBy(className = "search-full")
    protected WebElementFacade searchForm;

    @FindBy(id = "query")
    protected WebElementFacade searchInput;

    @FindBy(className = "blocks-list")
    protected WebElementFacade blockItems;

    @FindBy(className = "blocks-item-title")
    protected List<WebElementFacade> blockItemTitles;

    //Search Suggestion Elements

    @FindBy(tagName = "zd-autocomplete")
    protected WebElementFacade suggestionComponent;

    @FindBy(tagName = "zd-autocomplete-header")
    protected WebElementFacade suggestionHeader;

    @FindBy(tagName = "zd-autocomplete-option")
    protected List<WebElementFacade> suggestionOptions;

    @FindBy(tagName = "em")
    protected List<WebElementFacade> boldKeywordSuggestions;

    @FindBy(tagName = "zd-autocomplete-breadcrumbs")
    protected List<WebElementFacade> breadcrumbSuggestions;

    //Footer element

    @FindBy(className = "footer")
    protected WebElementFacade footer;

    public WebElementFacade getLogo() {
        return logo;
    }

    public WebElementFacade getNavigationLinks() {
        return navigationLinks;
    }

    public WebElementFacade getSubmitARequestNav() {
        return submitARequestNav;
    }

    public WebElementFacade getWelcomeMessage() {
        return welcomeMessage;
    }

    public WebElementFacade getSearchForm() {
        return searchForm;
    }

    public WebElementFacade getSearchInput() {
        return searchInput;
    }

    public WebElementFacade getBlockItems() {
        return blockItems;
    }

    public List<WebElementFacade> getBlockItemTitles() {
        return blockItemTitles;
    }

    public WebElementFacade getSuggestionComponent() {
        return suggestionComponent;
    }

    public WebElementFacade getSuggestionHeader() {
        return suggestionHeader;
    }

    public List<WebElementFacade> getSuggestionOptions() {
        return suggestionOptions;
    }

    public List<WebElementFacade> getBoldKeywordSuggestions() {
        return boldKeywordSuggestions;
    }

    public List<WebElementFacade> getBreadcrumbSuggestions() {
        return breadcrumbSuggestions;
    }

    public WebElementFacade getFooter() {
        return footer;
    }

    /**
     * This method waits for all main elements on {@link SupportLandingPageElements}
     */
    public void validateSupportLandingPageElements() {
        getLogo().waitUntilVisible();
        getNavigationLinks().waitUntilVisible();
        getWelcomeMessage().waitUntilVisible();
        getSearchForm().waitUntilVisible();
        getSearchInput().waitUntilVisible();
        getBlockItems().waitUntilVisible();
        getFooter().waitUntilVisible();
    }

    /**
     * This method enters keywords into {@link #searchInput}
     *
     * @param keywords  -   search keywords mentioned in the feature file
     */
    public void enterSearchKeywords(String keywords) {
        getSearchInput().waitUntilClickable().click();
        getSearchInput().waitUntilVisible().clear();
        getSearchInput().sendKeys(keywords);
    }

    /**
     * This method validates the presence of Search Suggestions
     * in {@link #suggestionOptions}
     *
     * @param searchSuggestionValidity - validity mentioned in the feature file
     */
    public void validateSuggestionPresence(String searchSuggestionValidity) {
        if (searchSuggestionValidity.equalsIgnoreCase("valid")){
            getSuggestionComponent().waitUntilVisible();
            getSuggestionHeader().shouldBeCurrentlyVisible();
            Assert.assertTrue(getSuggestionOptions().size() > 0);
        }else{
            getSuggestionComponent().shouldNotBeCurrentlyVisible();
            getSuggestionHeader().shouldNotBeCurrentlyVisible();
            Assert.assertFalse(getSuggestionOptions().size() > 0);
        }
    }

    /**
     * This method validates the emboldened keywords in suggestions
     * from {@link #boldKeywordSuggestions} and looks for the suggestion breadcrumbs
     * in {@link #breadcrumbSuggestions}
     *
     * @param validity          -   validity mentioned in the feature file
     * @param searchKeywords    -   search keywords mentioned in the feature file
     */
    public void validateBoldKeywordsInSuggestions(String validity, String searchKeywords) {
        if (validity.equalsIgnoreCase("valid")) {
            getSuggestionComponent().waitUntilVisible();
            List<String> keywords = Arrays.asList(searchKeywords.split(" "));

            //Using a utility function to check whether each of the keywords are emboldened
            boolean emboldenedKeywordPresence = supportUtility.validateEmboldened(getBoldKeywordSuggestions(), keywords);

            Assert.assertTrue(emboldenedKeywordPresence);
            Assert.assertTrue(getBreadcrumbSuggestions().size() > 0);

        } else {
            Assert.assertFalse(getBoldKeywordSuggestions().size() > 0);
            Assert.assertFalse(getBreadcrumbSuggestions().size() > 0);
        }
    }

    /**
     * This method clicks on the first suggestion in {@link #suggestionOptions}
     *
     * @param validity  -   validity mentioned in the feature file
     * @return          -   if valid, returns list with suggested article and
     *                      breadcrumbs
     *                  -   if invalid, returns null
     */
    public List<String> userClicksOnFirstArticleSuggestion(String validity) {
        if (validity.equalsIgnoreCase("valid")) {

            //Splitting the suggestion text and breadcrumb text
            List<String> suggestionText = Arrays.asList(getSuggestionOptions()
                    .get(0)
                    .getText()
                    .split("-"));

            getSuggestionOptions()
                    .get(0)
                    .waitUntilClickable()
                    .click();
            return suggestionText;
        } else{
            Assert.fail();
            return null;
        }
    }

    /**
     * This method clicks on specific block item title in
     * {@link #blockItemTitles} based on the parameter
     *
     * @param blockItemText     - block text mentioned in the feature file
     */
    public void userClicksOnTheBlockItem(String blockItemText) {
        for (WebElementFacade we : getBlockItemTitles()){
            if (we.getText().equalsIgnoreCase(blockItemText)){
                we.waitUntilClickable().click();
                break;
            }
        }
    }

    /**
     * This method presses enter in {@link #searchInput}
     */
    public void userPressesEnter() {
        getSearchInput().sendKeys(Keys.ENTER);
    }

    /**
     * This method is used to click the submit a request link
     *
     * @param linkName      - link name mentioned in the feature file
     */
    public void userClicksOnNavigationLink(String linkName) {
        if (linkName.equalsIgnoreCase("submit a request")){
            getSubmitARequestNav().waitUntilClickable().click();
        }
    }
}
