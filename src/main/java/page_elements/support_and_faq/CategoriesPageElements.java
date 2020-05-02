package page_elements.support_and_faq;

import net.serenitybdd.core.pages.*;
import net.serenitybdd.core.annotations.findby.FindBy;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utility.SupportUtility;
import java.util.List;

/**
 * This class locates Page Objects in the Categories Page
 * and also locates common elements in {@link ArticlesPageElements}
 *
 * @author Shringar
 * @version 1.0
 * @since  1.0
 */
public class CategoriesPageElements extends ArticlesPageElements {

    SupportUtility supportUtility = new SupportUtility();

    @FindBy(className = "category-list")
    protected WebElementFacade categoryList;

    @FindBy(className = "active")
    protected WebElementFacade activeCategory;

    @FindBy(className = "section-tree")
    protected WebElementFacade sectionTree;

    public CategoriesPageElements(WebDriver driver) {
        super(driver);
    }

    public WebElementFacade getCategoryList() {
        return categoryList;
    }

    public WebElementFacade getActiveCategory() {
        return activeCategory;
    }

    public WebElementFacade getSectionTree() {
        return sectionTree;
    }

    /**
     * This method waits for all main elements on {@link CategoriesPageElements}
     * and common elements in {@link ArticlesPageElements}
     */
    public void validateCategoriesPageElements() {
        getLogo().waitUntilVisible();
        getNavigationLinks().waitUntilVisible();
        getWelcomeMessage().waitUntilVisible();
        getBreadcrumbs().waitUntilVisible();
        getSearchForm().waitUntilVisible();
        getCategoryList().waitUntilVisible();
        getActiveCategory().waitUntilVisible();
        getSectionTree().waitUntilVisible();
        getFooter().waitUntilVisible();
    }

    /**
     * This method validates navigation to the correct Categories page
     * It validate the presence of categories keyword in the URL
     * and also validates the breadcrumbs based on the block item clicked on
     * and also validates the category name on the welcome message
     * and the active category
     *
     * @param categoryText      -   category text mentioned in the feature file
     */
    public void userIsTakenToTheCorrectCategoriesPage(String categoryText) {
        String currentURL = getDriver().getCurrentUrl();
        Assert.assertTrue(currentURL.contains("categories"));

        boolean breadcrumbPresence = supportUtility.validateBreadcrumbs(getBreadcrumbElements(), categoryText);
        Assert.assertTrue(breadcrumbPresence);

        Assert.assertTrue(categoryText.equalsIgnoreCase(getWelcomeMessage().getText()));
        Assert.assertTrue(categoryText.equalsIgnoreCase(getActiveCategory().getText()));
    }
}
