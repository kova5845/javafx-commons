package dejv.jfx.commons.binding;

import static java.util.Objects.requireNonNull;

import java.util.function.Function;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.DoubleExpression;

/**
 * Binding, that applies function on its dependency.
 * <br/>
 *
 * @author dejv78 (www.github.com/dejv78)
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
