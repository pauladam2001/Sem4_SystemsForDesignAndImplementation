package core.domain.validators;

import core.domain.Train;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class TrainValidator implements Validator<Train>{
    @Override
    public void validate(Train train) throws ValidatorException {
        /// id not null
        Predicate<Long> isIdNull = (id) -> (id == null || id < 0);
        Boolean idNotValid = isIdNull.test(train.getId());
        String idNotValidMessage = "Id must be non-null and greater than 0!";

//        Predicate<Train> areArrivalAndDepartureTimesInvalid = (train1) -> (train1.getArrivalTime() >= train.getDepartureTime());
//        Boolean timesNotValid = areArrivalAndDepartureTimesInvalid.test(train);
//        String timesNotValidMessage = "Departure time cannot be after the arrival!";

        List<Pair<Boolean, String>> checkList = List.of(
                new Pair<>(idNotValid, idNotValidMessage)
//                new Pair<>(timesNotValid, timesNotValidMessage)
        );
        checkList.stream()
                .filter(Pair::getFirst)
                .map(Pair::getSecond)
                .reduce((a,b) -> (a + b))
                .ifPresent(exceptionMessage -> {throw new ValidatorException(exceptionMessage);});
    }
}

