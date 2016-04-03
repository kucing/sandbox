import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.html.HtmlPanelGroup;

/**
 * This is an {@link HtmlPanelGroup} with the ability to determine it's descendant(s) of type
 * {@link javax.faces.component.UIInput}.
 *
 * Knowing its child(ren) of type {@link javax.faces.component.UIInput} is necessary, if a component wants to:
 * <ul>
 *     <li>link a &lt;label&gt; to an input control (using the 'for' attribute)</li>
 *     <li>apply alternative CSS styles in case of errors</li>
 *     <li>etc.</li>
 * </ul>
 *
 * Note: This component is not a {@link NamingContainer}. If you want to create a NamingContainer at the scope of this
 * component, you can wrap it inside a f:subview. Or, if it's the direct (and only) child of a composite component
 * you're also safe (all composite components are NamingContainers).
 *
 * <p/>
 *
 * A simplified implementation of such a component (linking a label to a control, checking for field errors, ..)
 * could use the method {@link javax.faces.component.UIComponentBase#findComponent(String)}.
 * But that method has some restrictions: First, if you want to be able to look up a
 * {@link javax.faces.component.UIInput}, you should know its id or path. This would mean that it should always have a
 * fixed value (like 'input'), or you would have to pass it in as a tag attribute. Both of which require the using page
 * to have knowledge of the internals of this component.
 * On top of that, {@link javax.faces.component.UIComponentBase#findComponent(String)} assumes that the id provided in
 * the search expression exists in the NamingContainer that functions as the base component for the search
 * (see javadoc of {@link javax.faces.component.UIComponentBase#findComponent(String)}). Meaning that the UIComponent
 * can not be located if it is wrapped inside another NamingContainer (for instance, a composite component).
 * In other words, the using page would not be allowed to put a {@link javax.faces.component.UIInput} wrapped in a
 * <i>composite component</i> in the tag body.
 */
@FacesComponent("InputContainer")
public class InputContainer extends HtmlPanelGroup {

    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE = "nu.helmers.sandbox.jsf-formcomponents.InputContainer";

    public Boolean isGeldig() {
        // TODO Guido - traverse all descendants of type UIInput, return true only if all of them are valid.
        return false;
    }

    /**
     * @return the first error message found on the UIInput descendants of this container. Null if all UIInputs are valid.
     */
    public String getFoutmelding() {
        // TODO Guido - traverse all descendants of type UIInput, return first error message.
        return "Validation error.";
    }

    /**
     * {@link javax.faces.component.UIComponentBase#findComponent(String)} describes an algorithm that is used to
     * find a component based on its id, and (optionally) the id's of the NamingContainers containing it.
     *
     * That sequence of id's should be provided as a String (an expression) like this: "nc1:nc2:nc3:compid" where nc1
     * is the id of the outer NamingContainer, etc. This method returns such an expression (in such a way that it can
     * be used by the h:outputLabel 'for' attribute...)
     *
     * @return expression expr for which findComponent(expr) returns the first descendant of type UIInput.
     */
    public String getUiInputPath() {
        // TODO Guido - find first descendant of type UIInput, return its path.
        return "input";
    }
}
