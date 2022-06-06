package core.domain.validators;

import core.domain.Driver;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class DriverValidator implements Validator<Driver> {
    @Override
    public void validate(Driver entity) throws ValidatorException {
        Predicate<Long> isIdNull = (id) -> (id == null || id < 0);
        Boolean idNotValid = isIdNull.test(entity.getId());
        String idNotValidMessage = "Id must be greater than 0 and not null!";

        Predicate<String> isNameNull = (s) -> (s == null || s.equals("")  || s.matches("[0-9]+"));
        Boolean nameNotValid = isNameNull.test(entity.getName());
        String nameNotValidMessage = "Person must have a name!";

        Predicate<String> isCNPNull = (s) -> (s == null || s.equals("") || s.length() != 13 || (s.charAt(0) != '5' && s.charAt(0) != '6')  || !s.matches("[0-9]+"));
        Boolean CNPNotValid = isCNPNull.test(entity.getCNP());
        String CNPNotValidMessage = "Person must have a valid CNP!";

        List<Pair<Boolean, String>> checkList = Arrays.asList(
                new Pair<>(idNotValid, idNotValidMessage),
                new Pair<>(nameNotValid, nameNotValidMessage),
                new Pair<>(CNPNotValid, CNPNotValidMessage));

        checkList.stream()
                .filter(Pair::getFirst)
                .map(Pair::getSecond)
                .reduce(String::concat)
                .ifPresent(s -> {throw new ValidatorException(s);});
    }
}
