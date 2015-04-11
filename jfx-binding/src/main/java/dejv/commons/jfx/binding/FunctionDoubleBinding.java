package dejv.commons.jfx.binding;

import static java.util.Objects.requireNonNull;

import java.util.function.Function;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ObservableDoubleValue;

/**
 * A variant of {@link javafx.beans.binding.DoubleBinding}, that applies provided function (represented by {@link java.util.function.Function}) on a supplied dependency.
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class FunctionDoubleBinding
        extends DoubleBinding {

    private final ObservableDoubleValue dependency;
    private final Function<Double, Double> function;


    /**
     * Creates a new instance of FunctionDoubleBinding with a given {@link java.util.function.Function} on a supplied dependency.
     * @param function Function to modify the dependency value
     * @param dependency The dependency
     */
    public FunctionDoubleBinding(Function<Double, Double> function, ObservableDoubleValue dependency) {
        requireNonNull(dependency, "Parameter 'dependency' is null");
        requireNonNull(function, "Parameter 'function' is null");

        this.dependency = dependency;
        this.function = function;
        bind(dependency);
    }


    @Override
    protected double computeValue() {
        return function.apply(dependency.get());
    }
}
