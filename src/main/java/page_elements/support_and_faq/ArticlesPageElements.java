package page_elements.support_and_faq;

import net.serenitybdd.core.pages.*;
import net.serenitybdd.core.annotations.findby.FindBy;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utility.SupportUtility;

import java.util.List;

/**
 * This class locates Page Objects in the Articles page
 * and also locates common elements in {@link SupportLandingPageElements}
 *
 * @author Shringar
 * @version 1.0
 * @since  1.0
 */
public class ArticlesPageElements extends SupportLandingPageElements {

    SupportUtility supportUtility = new SupportUtility();

    @FindBy(className = "article-title")
    protected WebElementFacade articleFromPage;

    @FindBy(className = "breadcrumbs")
    protected WebElementFacade breadcrumbs;

    @FindBy(css = "ol.breadcrumbs>li")
    protected List<WebElementFacade> breadcrumbElements;

    @FindBy(className = "search")
    protected WebElementFacade searchForm;

    @FindBy(className = "current-article")
    protected WebElementFacade currentArticle;

    @FindBy(className = "article-info")
    protected WebElementFacade articleInformation;

    @FindBy(className = "article-votes")
    protected WebElementFacade articleVotes;

    @FindBy(className = "article-votes-controls")
    protected WebElementFacade articleVoteControls;

    @FindBy(className = "article-more-questions")
    protected WebElementFacade articleMoreQuestions;

    @FindBy(className = "article-relatives")
    protected WebElementFacade articleRelated;

    @FindBy(className = "article-sidebar")
    protected WebElementFacade articleSidebar;

    public ArticlesPageElements(WebDriver driver) {
        super(driver);
    }

    public WebElementFacade getArticleFromPage() {
        return articleFromPage;
    }

    public WebElementFacade getBreadcrumbs() {
        return breadcrumbs;
    }

    public List<WebElementFacade> getBreadcrumbElements() {
        return breadcrumbElements;
    }

    @Override
    public WebElementFacade getSearchForm() {
        return searchForm;
    }

    public WebElementFacade getCurrentArticle() {
        return currentArticle;
    }

    public WebElementFacade getArticleInformation() {
        return articleInformation;
    }

    public WebElementFacade getArticleVotes() {
        return articleVotes;
    }

    public WebElementFacade getArticleVoteControls() {
        return articleVoteControls;
    }

    public WebElementFacade getArticleMoreQuestions() {
        return articleMoreQuestions;
    }

    public WebElementFacade getArticleRelated() {
        return articleRelated;
    }

    public WebElementFacade getArticleSidebar() {
        return articleSidebar;
    }

    /**
     * This method waits for all main elements on {@link ArticlesPageElements}
     * and common elements in {@link SupportLandingPageElements}
     */
    public void validateArticlePageElements() {
        getLogo().waitUntilVisible();
        getNavigationLinks().waitUntilVisible();
        getWelcomeMessage().waitUntilVisible();
        getBreadcrumbs().waitUntilVisible();
        getArticleFromPage().waitUntilVisible();
        getSearchForm().waitUntilVisible();
        getCurrentArticle().waitUntilVisible();
        getArticleInformation().waitUntilVisible();
        getArticleVotes().waitUntilVisible();
        getArticleVoteControls().waitUntilVisible();
        getArticleMoreQuestions().waitUntilVisible();
        getArticleRelated().waitUntilVisible();
        getArticleSidebar().waitUntilVisible();
        getFooter().waitUntilVisible();
    }

    /**
     * This method validates navigation to the correct Article page
     * It validates the name of the article in the URL
     * and also validates the name of the article based on the suggestion clicked on
     * and the breadcrumbs of the page with that in the suggestion
     *
     * @param suggestionText    -   The suggestion text from the
     *                               StepDefinitions method
     */
    public void userIsTakenToTheCorrectArticlePage(List<String> suggestionText) {
        String currentURL = getDriver().getCurrentUrl();
        String articleInURL = currentURL.split("[0-9]+")[1]
                .replace("-", " ")
                .trim();

        String articleSuggestion = suggestionText.get(0).trim();
        String breadcrumbSuggestion = suggestionText.get(1).toLowerCase();

        Assert.assertTrue(articleSuggestion.equalsIgnoreCase(articleInURL));
        Assert.assertTrue(articleSuggestion.equalsIgnoreCase(getArticleFromPage().getText()));

        //Current article highlighting is inconsistent as seen with "profile color" keywords
        //Assert.assertTrue(articleSuggestion.equalsIgnoreCase(currentArticle.getText()));

        boolean breadcrumbPresence = supportUtility.validateBreadcrumbs(getBreadcrumbElements(), breadcrumbSuggestion);
        Assert.assertTrue(breadcrumbPresence);
    }
}
