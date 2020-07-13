package Repository;

import java.util.function.Consumer;

public interface HttpDataRepo {
    void getData (Consumer<String> data);
}
