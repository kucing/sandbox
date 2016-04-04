package nu.helmers.sandbox.jsf;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.faces.component.html.HtmlInputText;

/**
 * This test is mainly concerned with verifying that the algorithm to lookup children of UIInput works properly.
 * Even if there are multiple children of type UIInput. And even if they are nested in ore or more NamingContainers
 * (which influences the default lookup mechanism, see UIComponentBase#findComponent(String)).
 */
public class InputContainerTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testIsGeldig() throws Exception {
        HtmlInputText input = new HtmlInputText();
        input.setId("input");


        InputContainer inputContainer = new InputContainer();
//        inputContainer.getChildren().add()
//        input.setParent();
    }

    @Test
    public void testGetFoutmelding() throws Exception {

    }

    @Test
    public void testGetUiInputPath() throws Exception {

    }
}