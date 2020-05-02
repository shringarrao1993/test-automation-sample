package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import page_elements.support_and_faq.SupportLandingPageElements;

public class SupportAndFaqSteps {

    public static class BlockItemNavigationSteps extends ScenarioSteps{

        SupportLandingPageElements supportLandingPageElements;

        @Step
        public void userOpensTheSupportPage() {
            supportLandingPageElements.open();
            getDriver().manage().window().maximize();
            getDriver().manage().deleteAllCookies();
            supportLandingPageElements.validateSupportLandingPageElements();
        }



    }
}
