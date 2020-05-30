package utilities;

import java.util.List;
import java.util.Objects;

public class RequiredValues {
    public String code;
    public List<User> data;

    public RequiredValues(String code, List<User> data) {
        this.code = code;
        this.data = data;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, data);
    }
}