package page_elements.support_and_faq;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utility.SupportUtility;
import utility.WaitUtility;
import java.util.Arrays;
import java.util.List;

/**
 * This class locates Page Objects in the Search results Page
 * and also locates common elements in {@link ArticlesPageElements}
 *
 * @author Shringar
 * @version 1.0
 * @since  1.0
 */
public class SearchResultsPageElements extends ArticlesPageElements{

    WaitUtility waitUtility = new WaitUtility();
    SupportUtility supportUtility = new SupportUtility();

    @FindBy(tagName = "h2")
    protected WebElementFacade searchHeading;

    @FindBy(className = "page-header-description")
    protected WebElementFacade resultsHeader;

    @FindBy(className = "search-results")
    protected WebElementFacade searchResults;

    @FindBy(className = "search-result")
    protected List<WebElementFacade> searchResultItems;

    @FindBy(className = "search-result-link")
    protected List<WebElementFacade> searchResultLinks;

    @FindBy(tagName = "em")
    protected List<WebElementFacade> highlightedKeywords;

    @FindBy(className = "pagination")
    protected WebElementFacade paginationBar;

    public SearchResultsPageElements(WebDriver driver) {
        super(driver);
    }

    public WebElementFacade getSearchHeading() {
        return searchHeading;
    }

    public WebElementFacade getResultsHeader() {
        return resultsHeader;
    }

    public WebElementFacade getSearchResults() {
        return searchResults;
    }

    public List<WebElementFacade> getSearchResultItems() {
        return searchResultItems;
    }

    public List<WebElementFacade> getSearchResultLinks() {
        return searchResultLinks;
    }

    public List<WebElementFacade> getHighlightedKeywords() {
        return highlightedKeywords;
    }

    public WebElementFacade getPaginationBar() {
        return paginationBar;
    }

    /**
     * This method waits for all main elements in {@link SearchResultsPageElements}
     * and common elements in {@link ArticlesPageElements}
     */
    public void validateSearchResultsPageElements(){
        getLogo().waitUntilVisible();
        getNavigationLinks().waitUntilVisible();
        getWelcomeMessage().waitUntilVisible();
        getBreadcrumbs().waitUntilVisible();
        getSearchForm().waitUntilVisible();
        getSearchHeading().waitUntilVisible();
        getResultsHeader().waitUntilVisible();
        getSearchResults().waitUntilVisible();
        getFooter().waitUntilVisible();
    }

    /**
     * This method is used to validate all remaining fields in
     * {@link SearchResultsPageElements} if search results appear
     */
    public void validateRemainingPageElements(){
        waitUtility.waitUntilElementsArePresent(getSearchResultItems());
        waitUtility.waitUntilElementsArePresent(getSearchResultLinks());
        waitUtility.waitUntilElementsArePresent(getHighlightedKeywords());
        getPaginationBar().waitUntilVisible();
    }

    /**
     * This method looks for the presence of search results.
     * It also validates the number in {@link #resultsHeader} field
     * It also validates the presence of highlighted keywords in Search
     * and validates the page in the breadcrumbs field
     *
     * @param searchSuggestionValidity - validity mentioned in the feature file
     * @param searchKeywords           - keywords mentioned in the feature file
     */
    public void validateSearchResultsPresence(String searchSuggestionValidity, String searchKeywords) {
        if (searchSuggestionValidity.equalsIgnoreCase("valid")){

            //Validates all remaining elements if Search results appear
            validateRemainingPageElements();

            //Obtaining the number of results from the results header
            int numberOfResults = Integer
                    .parseInt(getResultsHeader()
                            .getText()
                            .replaceAll("\\D+",""));
            Assert.assertTrue(numberOfResults > 0);
            Assert.assertTrue(getSearchResultItems().size() > 0);

            //Validating highlighted keywords in results
            List<String> keywords = Arrays.asList(searchKeywords.split(" "));
            boolean highlightedKeywordPresence = supportUtility.validateEmboldened(getBoldKeywordSuggestions(), keywords);
            Assert.assertTrue(highlightedKeywordPresence);

            //Validating page in breadcrumbs
            boolean breadcrumbPresence = supportUtility.validateBreadcrumbs(getBreadcrumbElements(), getSearchHeading().getText());
            Assert.assertTrue(breadcrumbPresence);
        }
        else {
            Assert.assertTrue(getResultsHeader().getText().toLowerCase().contains("no results"));
            Assert.assertFalse(getSearchResultItems().size() > 0);
        }
    }
}
