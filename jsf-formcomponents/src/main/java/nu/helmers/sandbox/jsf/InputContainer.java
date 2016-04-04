package nu.helmers.sandbox.jsf;

import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import java.util.HashMap;
import java.util.Map;

/**
 * This is an {@link HtmlPanelGroup} with the ability to determine it's descendant(s) of type
 * {@link javax.faces.component.UIInput}.
 * <p/>
 * Knowing its child(ren) of type {@link javax.faces.component.UIInput} is necessary, if a component wants to:
 * <ul>
 * <li>link a &lt;label&gt; to an input control (using the 'for' attribute)</li>
 * <li>apply alternative CSS styles in case of errors</li>
 * <li>etc.</li>
 * </ul>
 * <p/>
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
 * <p/>
 * Styling Invalid Input Fields with JSF:
 * http://blog.primefaces.org/?p=1170
 */
@FacesComponent("InputContainer")
public class InputContainer extends HtmlPanelGroup implements NamingContainer {

    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE = "nu.helmers.sandbox.jsf-formcomponents.InputContainer";

    private Map<String, UIInput> uiInputMap = new HashMap<>();

    public void cleanup() {
        this.uiInputMap = null;
    }

    public Boolean isGeldig() {
//        System.out.println("Calling isValid() in phase: " + getFacesContext().getCurrentPhaseId());
        this.uiInputMap = getUIInputs(); // TODO map should be available before this method is called!
        return uiInputMap.values().stream().filter(input -> !input.isValid()).count() > 0;
    }

    /**
     * @return the first error message found on the UIInput descendants of this container. Null if all UIInputs are
     * valid.
     */
    public String getFoutmelding() {

        this.uiInputMap = getUIInputs(); // TODO map should be available before this method is called!
        if (this.isGeldig()) {
            return null; // Label without input field. Should never happen.
        }
        final UIInput invalidUIInput = uiInputMap.values().stream().filter(input -> !input.isValid()).findFirst().get();
        return invalidUIInput.getValidatorMessage();
    }

    /**
     * {@link javax.faces.component.UIComponentBase#findComponent(String)} describes an algorithm that is used to
     * find a component based on its id, and (optionally) the id's of the NamingContainers containing it.
     * <p/>
     * That sequence of id's should be provided as a String (an expression) like this: "nc1:nc2:nc3:compid" where nc1
     * is the id of the outer NamingContainer, etc. This method returns such an expression (in such a way that it can
     * be used by the h:outputLabel 'for' attribute...)
     *
     * @return expression expr for which findComponent(expr) returns the first descendant of type UIInput.
     */
    public String getUiInputPath() {
        this.uiInputMap = getUIInputs(); // TODO map should be available before this method is called!
        if (this.uiInputMap.isEmpty()) {
            return ""; // Label without input field. Should never happen.
        }
        return this.uiInputMap.keySet().iterator().next();
    }

    /**
     * TODO Guido - use PhaseListener to ensure that this method is called just one?
     */
    private Map<String, UIInput> getUIInputs() {
        final Map<String, UIInput> uiInputMap = new HashMap<>();

        final VisitContext context = VisitContext.createVisitContext(getFacesContext());
        final VisitCallback callback = new VisitCallback() {
            public VisitResult visit(final VisitContext visitContext, final UIComponent uiComponent) {
                if (uiComponent instanceof UIInput) {
                    String componentPath = determineComponentPath(uiComponent, InputContainer.this);
                    uiInputMap.put(componentPath, (UIInput) uiComponent);
                    // Is it smart to return ACCEPT here? Can a UIInput have a child of type UIInput?
                }
                return VisitResult.ACCEPT;
            }
        };
        this.visitTree(context, callback);

        return uiInputMap;
    }

    /**
     * This method returns a component path (a String expression of the form "id1:id2:...:idN")
     * that can be used to identify the given uiComponent inside the given NamingContainer.
     * See ${UIComponentBase#findComponent(String)} for more information on the algorithm.
     *
     * @param uiComponent         the component whose component path should be determined
     * @param baseNamingContainer the NamingContainer that serves as the base of the path expression.
     */
    private String determineComponentPath(final UIComponent uiComponent, final NamingContainer baseNamingContainer) {
        String expr = uiComponent.getId();
        UIComponent parent = uiComponent.getParent();
        while (!parent.equals(baseNamingContainer)) {
            if (parent instanceof NamingContainer) {
                expr = parent.getId() + ":" + expr;
            }
            parent = parent.getParent();
        }
        // Note that the id of the base NamingContainer is NOT part of the expression.
        System.out.println("Component path: " + expr);
        return expr;
    }
}
