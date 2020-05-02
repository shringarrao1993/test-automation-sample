package page_elements.support_and_faq;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utility.SupportUtility;
import utility.WaitUtility;
import java.util.List;

/**
 * This class locates Page Objects in the Submit a request page
 * and also locates common elements in {@link ArticlesPageElements}
 *
 * @author Shringar
 * @version 1.0
 * @since  1.0
 */
public class SubmitARequestPageElements extends ArticlesPageElements{

    WaitUtility waitUtility = new WaitUtility();
    SupportUtility supportUtility = new SupportUtility();

    @FindBy(className = "form")
    protected WebElementFacade formContainer;

    @FindBy(tagName = "h2")
    protected WebElementFacade heading;

    @FindBy(className = "nesty-input")
    protected List<WebElementFacade> dropDowns;

    @FindBy(css = "ul[role='listbox']>li")
    protected List<WebElementFacade> issueListItems;

    @FindBy(className = "required")
    protected List<WebElementFacade> requiredFields;

    @FindBy(css = "input[value='Submit']")
    protected WebElementFacade submitButton;

    //Specifically looks for error messages only under required fields
    @FindBy(xpath = "//div[contains(@class,'required')]/div[contains(text(),'cannot be blank')]")
    protected List<WebElementFacade> blankErrorElements;

    public SubmitARequestPageElements(WebDriver driver) {
        super(driver);
    }

    public WebElementFacade getFormContainer() {
        return formContainer;
    }

    public WebElementFacade getHeading() {
        return heading;
    }

    public List<WebElementFacade> getDropDowns() {
        return dropDowns;
    }

    public List<WebElementFacade> getIssueListItems() {
        return issueListItems;
    }

    public List<WebElementFacade> getRequiredFields() {
        return requiredFields;
    }

    public WebElementFacade getSubmitButton() {
        return submitButton;
    }

    public List<WebElementFacade> getBlankErrorElements() {
        return blankErrorElements;
    }

    /**
     * This method waits for all main elements on {@link SubmitARequestPageElements}
     * and common elements in {@link ArticlesPageElements}
     */
    public void validateSubmitARequestPageElements(){
        getLogo().waitUntilVisible();
        getNavigationLinks().waitUntilVisible();
        getWelcomeMessage().waitUntilVisible();
        getBreadcrumbs().waitUntilVisible();
        getHeading().waitUntilVisible();
        getSearchForm().waitUntilVisible();
        getFormContainer().waitUntilVisible();
        getDropDowns().get(0).waitUntilVisible();
        waitUtility.waitUntilElementsArePresent(requiredFields);
        getFooter().waitUntilVisible();
    }

    /**
     * This method clicks on the issues dropdown and selects
     * the issue from the panel based on parameter sent in
     *
     * @param issueType     -   issue type mentioned in the feature file
     */
    public void userChoosesAnIssueAndClicksSubmit(String issueType) {
        //Issue selection
        getDropDowns().get(0).waitUntilClickable().click();
        supportUtility.clickIfElementsContainString(getIssueListItems(), issueType);

        //Second dropdown for account type
        getDropDowns().get(1).waitUntilClickable().click();
        supportUtility.clickIfElementsContainString(getIssueListItems(), "-");

        getSubmitButton().waitUntilClickable().click();
    }

    /**
     * This method validates the page reload and compares
     * the number of required fields and whether the error
     * messages only appeared for those fields
     */
    public void validateErrorMessages() {

        validateSubmitARequestPageElements();

        //The following lines will work only if reCaptcha is disabled
        int numberOfRequiredFields = getRequiredFields().size();
        int numberOfErrorMessagesUnderRequiredFields = getBlankErrorElements().size();

        Assert.assertEquals("reCaptcha needs to be disabled -- ", numberOfRequiredFields, numberOfErrorMessagesUnderRequiredFields);
    }
}
