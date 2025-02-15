package resident.human;

import java.util.Optional;

/**
 * An interface representing an object that can be used by a human.
 * Implementing classes should define the behavior of being used by a human.
 */
public interface  Usable {

    Optional<Usable> use(Human human);
}
