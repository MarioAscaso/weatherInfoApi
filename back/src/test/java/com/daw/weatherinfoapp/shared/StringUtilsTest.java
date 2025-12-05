package com.daw.weatherinfoapp.shared;

import com.daw.weatherinfoapp.shared.utils.StringUtils;
import org.junit.jupiter.api.Test;

public class StringUtilsTest {
    @Test
    public void testWhenHappyPath() {
        String aString = "<lat><long>";
        String[] toBeReplaced={"<lat>", "<long>"};
        double[] newValues = {1D, 2D};

        String newString = StringUtils.replaceAll(aString, toBeReplaced, newValues);

        System.out.println(newString);

    }
}
