package core.domain.validators;

import core.domain.DriverTrain;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class DriverTrainValidator implements Validator<DriverTrain> {
    @Override
    public void validate(DriverTrain entity) throws ValidatorException {
        Predicate<Long> isDriverIdNull = (id) -> (id == null || id < 0);
        Boolean idDriverNotValid = isDriverIdNull.test(entity.getDriverID());
        String idDriverNotValidMessage = "Id must be greater than 0 and not null!";

        Predicate<Long> isTrainIdNull = (id) -> (id == null || id < 0);
        Boolean idTrainNotValid = isTrainIdNull.test(entity.getTrainID());
        String idTrainNotValidMessage = "Id must be greater than 0 and not null!";

        List<Pair<Boolean, String>> checkList = Arrays.asList(
                new Pair<>(idDriverNotValid, idDriverNotValidMessage),
                new Pair<>(idTrainNotValid, idTrainNotValidMessage));

        checkList.stream()
                .filter(Pair::getFirst)
                .map(Pair::getSecond)
                .reduce(String::concat)
                .ifPresent(s -> {throw new ValidatorException(s);});
    }
}
