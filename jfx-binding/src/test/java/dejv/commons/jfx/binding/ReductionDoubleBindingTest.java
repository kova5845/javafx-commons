package dejv.commons.jfx.binding;

import static junit.framework.TestCase.assertEquals;

import java.util.function.BinaryOperator;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import org.apache.commons.math3.util.Precision;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ReductionDoubleBinding
 * <br/>
 *
 * @author dejv78 (www.dejv.info)
 */
public class ReductionDoubleBindingTest {

    private static final BinaryOperator<Double> REDUCTION = ReductionDoubleBindingTest::sum;
    private static final DoubleProperty DE1 = new SimpleDoubleProperty();
    private static final DoubleProperty DE2 = new SimpleDoubleProperty();


    @Before
    public void init() {
        DE1.set(10);
        DE2.set(20);
    }


    @Test(expected = NullPointerException.class)
    public void createWithNullDependency() {
        new ReductionDoubleBinding(REDUCTION, null);
    }


    @Test(expected = NullPointerException.class)
    public void createWithNullReduction() {
        new ReductionDoubleBinding(null, DE1);
    }


    @Test
    public void createWithDefault() {
        final double value = 20d;

        final ReductionDoubleBinding cut = new ReductionDoubleBinding(REDUCTION).setIdentity(value);
        assertEquals("Binding value 1", value, cut.get(), Precision.EPSILON);

        cut.add(DE1);
        assertEquals("Binding value 2", REDUCTION.apply(DE1.get(), value), cut.get(), Precision.EPSILON);

        cut.add(DE2);
        assertEquals("Binding value 3", REDUCTION.apply(REDUCTION.apply(DE1.get(), DE2.get()), value), cut.get(), Precision.EPSILON);
    }


    @Test
    public void createWithValidParams() {
        final DoubleBinding cut = new ReductionDoubleBinding(REDUCTION, DE1, DE2);

        assertEquals("Binding value 1", REDUCTION.apply(DE1.get(), DE2.get()), cut.get(), Precision.EPSILON);

        DE1.set(30);
        assertEquals("Binding value 2", REDUCTION.apply(DE1.get(), DE2.get()), cut.get(), Precision.EPSILON);

        DE2.set(50);
        assertEquals("Binding value 3", REDUCTION.apply(DE1.get(), DE2.get()), cut.get(), Precision.EPSILON);
    }


    @Test(expected = NullPointerException.class)
    public void callAddWithNullDependency() {
        final ReductionDoubleBinding cut = new ReductionDoubleBinding(REDUCTION, DE1, DE2);

        cut.add(null);
    }


    @Test
    public void callAddWithValidDependency() {
        final ReductionDoubleBinding cut = new ReductionDoubleBinding(REDUCTION, DE1, DE2);

        final DoubleProperty de3 = new SimpleDoubleProperty(30);
        cut.add(de3);
        assertEquals("Binding value 1", REDUCTION.apply(REDUCTION.apply(DE1.get(), DE2.get()), de3.get()), cut.get(), Precision.EPSILON);

        cut.add(de3);
        assertEquals("Binding value 2", REDUCTION.apply(REDUCTION.apply(DE1.get(), DE2.get()), de3.get()), cut.get(), Precision.EPSILON);
    }


    @Test(expected = NullPointerException.class)
    public void callRemoveWithNullDependency() {
        final ReductionDoubleBinding cut = new ReductionDoubleBinding(REDUCTION, DE1, DE2);

        cut.remove(null);
    }


    @Test
    public void callRemoveWithValidDependency() {
        final ReductionDoubleBinding cut = new ReductionDoubleBinding(REDUCTION, DE1, DE2);

        final DoubleProperty de3 = new SimpleDoubleProperty(-10);
        cut.add(de3);

        cut.remove(DE1);
        assertEquals("Binding value 1", REDUCTION.apply(DE2.get(), de3.get()), cut.get(), Precision.EPSILON);

        cut.remove(DE1);
        assertEquals("Binding value 2", REDUCTION.apply(DE2.get(), de3.get()), cut.get(), Precision.EPSILON);

        cut.remove(DE2);
        assertEquals("Binding value 3", de3.get(), cut.get(), Precision.EPSILON);

        cut.remove(de3);
        assertEquals("Binding value 4", 0d, cut.get(), Precision.EPSILON);
    }


    @Test
    public void callClear() {
        final ReductionDoubleBinding cut = new ReductionDoubleBinding(REDUCTION, DE1, DE2);
        final DoubleProperty de3 = new SimpleDoubleProperty(-10);
        cut.add(de3);

        cut.clear();
        assertEquals("Binding value 1", 0d, cut.get(), Precision.EPSILON);
    }

    @Test
    public void call() {
        final ReductionDoubleBinding cut = new ReductionDoubleBinding(REDUCTION).setIdentity(150);

        System.out.println("R1 " + cut.get());
        cut.add(DE1);
        System.out.println("R2 " + cut.get());
    }

    private static double sum(double d1, double d2) {
        return d1 + d2;
    }



}
