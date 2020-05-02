package step_definition.support_and_faq;

import io.cucumber.java.en.*;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import page_elements.support_and_faq.*;
import steps.SupportAndFaqSteps;
import utility.StrUtility;
import java.util.List;

/**
 * This class contains methods that deal with
 * WebElements from the Support and FAQ features
 *
 * @author Shringar
 * @version 1.0
 * @since 1.0
 * @see StrUtility
 */
public class StepDefinitions {

    protected SupportLandingPageElements supportLandingPageElements;
    protected CategoriesPageElements categoriesPageElements;
    protected ArticlesPageElements articlesPageElements;
    protected SearchResultsPageElements searchResultsPageElements;
    protected SubmitARequestPageElements submitARequestPageElements;

    @Steps
    protected SupportAndFaqSteps.BlockItemNavigationSteps blockItemNavigationSteps;

    protected String searchSuggestionValidity;
    protected String searchKeywords;
    protected List<String> suggestionText;
    protected String categoryText;

    @Given("user opens the Support page")
    public void userOpensTheSupportPage() {
        blockItemNavigationSteps.userOpensTheSupportPage();
    }

    @When("user enters the <validity> keywords <keywords>")
    @And("user enters the {string} keywords {string}")
    public void userEntersTheKeywords(String validity, String keywords) {
        supportLandingPageElements.enterSearchKeywords(keywords);
        searchSuggestionValidity = validity;
        searchKeywords = keywords;
    }

    @Then("user sees suggestions based on the validity of keywords")
    public void userSeesSuggestionsBasedOnTheValidityOfKeywords() {
        supportLandingPageElements.validateSuggestionPresence(searchSuggestionValidity);
    }

    @And("if one or more suggestions appear, the keywords should be emboldened")
    public void ifOneOrMoreSuggestionsAppearTheKeywordsShouldBeEmboldened() {
        supportLandingPageElements.validateBoldKeywordsInSuggestions(searchSuggestionValidity, searchKeywords);
    }

    @When("user clicks on first article suggestion")
    public void userClicksOnFirstArticleSuggestion() {
        suggestionText = supportLandingPageElements.userClicksOnFirstArticleSuggestion(searchSuggestionValidity);
    }

    @Then("user is taken to the correct article page")
    public void userIsTakenToTheCorrectArticlePage() {
        articlesPageElements.validateArticlePageElements();
        articlesPageElements.userIsTakenToTheCorrectArticlePage(suggestionText);
    }

    @When("user clicks on the block item {string}")
    public void userClicksOnTheBlockItem(String blockItemText) {
        supportLandingPageElements.userClicksOnTheBlockItem(blockItemText);
        categoryText = blockItemText;
    }

    @Then("user is taken to the correct categories page")
    public void userIsTakenToTheCorrectCategoriesPage() {
        categoriesPageElements.validateCategoriesPageElements();
        categoriesPageElements.userIsTakenToTheCorrectCategoriesPage(categoryText);
    }

    @And("user presses enter")
    public void userPressesEnter() {
        supportLandingPageElements.userPressesEnter();
    }

    @Then("user is taken to the search results page")
    public void userIsTakenToTheSearchResultsPage() {
        searchResultsPageElements.validateSearchResultsPageElements();
    }

    @And("if one or more results appear, highlighted keywords are displayed")
    public void ifOneOrMoreResultsAppearHighlightedKeywordsAreDisplayed() {
        searchResultsPageElements.validateSearchResultsPresence(searchSuggestionValidity, searchKeywords);
    }

    @And("user clicks on {string} navigation link")
    public void userClicksOnNavigationLink(String linkName) {
        supportLandingPageElements.userClicksOnNavigationLink(linkName);
    }

    @When("user is taken to the correct requests page")
    public void userIsTakenToTheCorrectRequestsPage() {
        submitARequestPageElements.validateSubmitARequestPageElements();
    }

    @And("user chooses {string} issue and clicks submit for empty form")
    public void userChoosesIssueAndClicksSubmit(String issueType) {
        submitARequestPageElements.userChoosesAnIssueAndClicksSubmit(issueType);
    }

    @Then("user should be able to see error messages for compulsory fields")
    public void userShouldBeAbleToSeeErrorMessagesForCompulsoryFields() {
        submitARequestPageElements.validateErrorMessages();
    }
}
