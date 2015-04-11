package dejv.commons.jfx.binding;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ObservableDoubleValue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A variant of {@link javafx.beans.binding.DoubleBinding}, that applies provided reduction operation (represented by {@link java.util.function.BinaryOperator}) on its dependencies.
 * <p>
 * If there are no dependencies, binding value is equal to 0.0d (this default value can be modified using the {@link #setIdentity(double)} method).
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class ReductionDoubleBinding
        extends DoubleBinding {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReductionDoubleBinding.class);

    private final List<ObservableDoubleValue> dependencies = new ArrayList<>();
    private final BinaryOperator<Double> binaryOperator;
    private double identity = 0d;


    /**
     * Creates a new instance of ReductionDoubleBonding with a given {@link java.util.function.BinaryOperator} and an optional set of initial dependencies.
     * @param binaryOperator The associative accumulation function (see {@link java.util.function.BinaryOperator} for more details)
     * @param dependencies An optional set of dependencies to be initially bound
     */
    public ReductionDoubleBinding(BinaryOperator<Double> binaryOperator, ObservableDoubleValue... dependencies) {
        requireNonNull(binaryOperator, "Parameter 'binaryOperator' is null");

        this.binaryOperator = binaryOperator;

        for (ObservableDoubleValue dependency : dependencies) {
            requireNonNull(dependency, "At least one of the dependecies is null");
            add(dependency);
        }
    }


    /**
     * Modify the identity value of the current binding.
     * <p>
     * <u>Note:</u>
     * Please keep in mind, that "identity" value always affects the result.
     * For example, if the dependencies are "a" and "b", given BinaryOperator is sum(a, b), and the identity is set to "c",
     * then the result will be "a+b+c"! (Standard behavior of {@link java.util.stream.Stream#reduce(BinaryOperator)}).
     * @param identity New identity value
     * @return The current binding with updated identity value
     */
    public ReductionDoubleBinding setIdentity(double identity) {
        this.identity = identity;
        return this;
    }


    /**
     * Bind the new (not previously bound) dependency.
     * @param dependency The dependency to be bound
     */
    public final void add(ObservableDoubleValue dependency) {
        requireNonNull(dependency, "Parameter 'dependency' is null");

        if (!dependencies.contains(dependency)) {

            dependencies.add(dependency);
            bind(dependency);
            invalidate();

        } else {
            LOGGER.warn("Dependency already included: " + dependency + ", Skipping.");
        }
    }


    /**
     * Unbind the existing (previously bound) dependency.
     * @param dependency The dependency to be unbound
     */
    public final void remove(ObservableDoubleValue dependency) {
        requireNonNull(dependency, "Parameter 'dependency' is null");

        if (dependencies.contains(dependency)) {

            dependencies.remove(dependency);
            unbind(dependency);
            invalidate();

        } else {
            LOGGER.warn("Dependency not included: " + dependency + ", Skipping.");
        }
    }


    /**
     * Unbind all the dependencies.
     */
    public void clear() {
        dependencies.forEach(this::unbind);
        dependencies.clear();
        invalidate();
    }


    @Override
    protected double computeValue() {
        return dependencies.stream().map(ObservableDoubleValue::get).reduce(identity, binaryOperator);
    }
}
