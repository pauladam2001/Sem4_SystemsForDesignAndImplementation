package core.domain.validators;

import core.domain.Route;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator implements Validator<Route>{
    @Override
    public void validate(Route route) throws ValidatorException {
        /// id not null
        Predicate<Long> isIdNull = (id) -> (id == null || id < 0);
        boolean idNotValid = isIdNull.test(route.getId());
        String idNotValidMessage = "Id must be non-null and greater than 0!";

        Predicate<String> isSourceNull = (s) -> (s == null || s.equals("")  || s.matches("[0-9]+"));
        Boolean sourceNotValid = isSourceNull.test(route.getSource());
        String sourceNotValidMessage = "Source not valid!";

        Predicate<String> isDestNull = (s) -> (s == null || s.equals("")  || s.matches("[0-9]+"));
        Boolean destNotValid = isDestNull.test(route.getSource());
        String destNotValidMessage = "Destination not valid!";

        List<Pair<Boolean, String>> checkList = List.of(
                new Pair<>(idNotValid, idNotValidMessage),
                new Pair<>(sourceNotValid, sourceNotValidMessage),
                new Pair<>(destNotValid, destNotValidMessage));
        checkList.stream()
                .filter(Pair::getFirst)
                .map(Pair::getSecond)
                .reduce((a,b) -> (a + b))
                .ifPresent(exceptionMessage -> {throw new ValidatorException(exceptionMessage);});
    }
}
