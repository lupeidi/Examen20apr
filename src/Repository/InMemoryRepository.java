package Repository;

import Domain.Entity;
import Domain.IValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository<T extends Entity> implements IRepository<T> {
    private Map<String, T> storage = new HashMap<>();
    private IValidator<T> validator;

    public InMemoryRepository(IValidator<T> validator) {
        this.validator = validator;
    }

    /**
     *
     * @param id of entity
     * @return entity
     */
    public T getById(String id) {
        return storage.get(id);
    }

    /**
     * updates or adds an entity
     * @param entity
     */
    public void upsert(T entity) {
        validator.validate(entity);
        storage.put(entity.getId(), entity);
    }

    /**
     * removes an entity by given id
     * @param id
     */
    public void remove(String id) {
        if (!storage.containsKey(id)) {
            throw new RuntimeException("There is no entity with the given id to remove.");
        }

        storage.remove(id);
    }

    /**
     *
     * @return list of all entities
     */
    public List<T> getAll() {
        return new ArrayList<>(storage.values());
    }

}
