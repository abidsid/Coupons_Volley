package com.demo.meijertest;

import android.app.Activity;
import android.content.Context;

import com.demo.meijertest.utils.HFAUtils;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private static final String BASE_URL = "https://meijerkraig.azurewebsites.net/api/Products?code=34lgBae%2FxIEnqksQpkn3w9F0JTKCafuiCr0ejLNLvBzlOlOZBa1CMA%3D%3D";

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    //Is Reecyclerview Available or NOT
    @Test
    public void APIUrl_isCorrect() throws Exception {
// Context of the app under test.
        Assert.assertEquals(BASE_URL, HFAUtils.BASE_URL);

    }

    public void isInternetAvailable() throws Exception {
        Context activity = getTestContext();
        boolean flag = HFAUtils.isOnlineAvailable(activity);
       // Assert.assertTrue(flag);
        assertTrue(flag);
       // assertThat();
    }

    /**
     * @return The {@link Context} of the test project.
     */
    private Context getTestContext() {
        try {
            Method getTestContext = ExampleUnitTest.class.getMethod("getTestContext");
            return (Context) getTestContext.invoke(this);
        } catch (final Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

}