package dejv.commons.jfx.binding;

import static java.util.Objects.requireNonNull;

import java.util.function.Function;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.DoubleExpression;

/**
 * A variant of {@link javafx.beans.binding.DoubleBinding}, that applies provided function (represented by {@link java.util.function.Function}) on its dependencies.
 * <p>
 * If there are no dependencies, binding value is equal to Double.NaN
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class FunctionDoubleBinding
        extends DoubleBinding {

    private final DoubleExpression expression;
    private final Function<Double, Double> function;


    public FunctionDoubleBinding(DoubleExpression expression, Function<Double, Double> function) {
        requireNonNull(expression, "Parameter 'expression' is null");
        requireNonNull(function, "Parameter 'function' is null");

        this.expression = expression;
        this.function = function;
        bind(expression);
    }


    @Override
    protected double computeValue() {
        return function.apply(expression.get());
    }
}
