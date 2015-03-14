package dejv.commons.jfx.binding;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.value.ObservableDoubleValue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A variant of {@link javafx.beans.binding.DoubleBinding}, that applies provided reduction operation (represented by {@link java.util.function.BinaryOperator}) on its dependencies.
 * <p>
 * If there are no dependencies, binding value is equal to Double.NaN
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class ReductionDoubleBinding
        extends DoubleBinding {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReductionDoubleBinding.class);

    private final List<DoubleExpression> dependencies = new ArrayList<>();
    private final BinaryOperator<Double> binaryOperator;


    public ReductionDoubleBinding(DoubleExpression dependency1, BinaryOperator<Double> binaryOperator) {
        requireNonNull(binaryOperator, "Parameter 'binaryOperator' is null");

        this.binaryOperator = binaryOperator;

        add(dependency1);
    }


    public ReductionDoubleBinding(DoubleExpression dependency1, DoubleExpression dependency2, BinaryOperator<Double> binaryOperator) {
        this(dependency1, binaryOperator);

        add(dependency2);
    }


    public final ReductionDoubleBinding add(DoubleExpression dependency) {
        requireNonNull(dependency, "Parameter 'dependency' is null");

        if (!dependencies.contains(dependency)) {

            dependencies.add(dependency);
            bind(dependency);

        } else {
            LOGGER.warn("Dependency already included: " + dependency + ", Skipping.");
        }
        return this;
    }


    public final ReductionDoubleBinding remove(DoubleExpression dependency) {
        requireNonNull(dependency, "Parameter 'dependency' is null");

        if (dependencies.contains(dependency)) {

            dependencies.remove(dependency);
            unbind(dependency);
            invalidate();

        } else {
            LOGGER.warn("Dependency not included: " + dependency + ", Skipping.");
        }
        return this;
    }


    public void clear() {
        dependencies.forEach(this::unbind);
        dependencies.clear();
        invalidate();
    }


    @Override
    protected double computeValue() {
        final Optional<Double> result = dependencies.stream().map(ObservableDoubleValue::get).reduce(binaryOperator);
        return (result.isPresent()) ? result.get() : Double.NaN;
    }

}
