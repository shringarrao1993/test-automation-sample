package utility;

import org.apache.commons.lang3.StringUtils;

/**
 * This class contains methods that deal
 * with custom String manipulation
 *
 * @author Shringar
 * @version 1.0
 * @since 1.0
 */
public class StrUtility{

    /**
     * Simple method to remove leading "s" character from a string
     * based on whether a leading "s" is found
     *
     * @param plural    - word that is supposed to be plural
     * @return          - word from which "s" has been removed
     */
    public String removeLeadingS(String plural) {
        if (plural.endsWith("s")) {
            return StringUtils.removeEnd(plural, "s");
        }else {
            return plural;
        }
    }
}

