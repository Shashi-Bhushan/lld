package in.shabhushan.ticketbooking.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum City {
    PUNE,
    MUMBAI,
    BANGALORE;

    private static final Map<String, City> map =
            Arrays.stream(values())
                    .collect(Collectors.toMap(City::name, Function.identity()));

    public static City forName(String name) {
        return map.get(name);
    }
}
