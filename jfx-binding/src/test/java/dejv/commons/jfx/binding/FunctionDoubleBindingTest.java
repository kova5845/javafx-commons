package dejv.commons.jfx.binding;

import static junit.framework.TestCase.assertEquals;

import java.util.function.Function;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import org.apache.commons.math3.util.Precision;
import org.junit.Before;
import org.junit.Test;

import dejv.commons.binding.FunctionDoubleBinding;

/**
 * Test class for FunctionDoubleBinding.
 * <br/>
 *
 * @author dejv78 (www.dejv.info)
 */
public class FunctionDoubleBindingTest {


    private static final Function<Double, Double> FUNCTION = Math::asin;
    private static final DoubleProperty EXPRESSION = new SimpleDoubleProperty();


    @Before
    public void init() {
        EXPRESSION.set(0.1);
    }


    @Test(expected = NullPointerException.class)
    public void createWithNullExpression() {

        new FunctionDoubleBinding(null, FUNCTION);
    }


    @Test(expected = NullPointerException.class)
    public void createWithNullFunction() {

        new FunctionDoubleBinding(EXPRESSION, null);
    }


    @Test
    public void createWithValidParam() {

        final DoubleBinding cut = new FunctionDoubleBinding(EXPRESSION, FUNCTION);

        assertEquals("Binding value", FUNCTION.apply(EXPRESSION.get()), cut.get(), Precision.EPSILON);

        EXPRESSION.set(-0.5);
        assertEquals("Binding value", FUNCTION.apply(EXPRESSION.get()), cut.get(), Precision.EPSILON);
    }
}
