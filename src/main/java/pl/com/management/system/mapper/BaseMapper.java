package pl.com.management.system.mapper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseMapper<DTO, Model> implements Mapper<DTO, Model> {
    @Override
    public DTO map(Model item) {
        if (item == null)
            return null;
        return _map(item);
    }

    @Override
    public List<DTO> map(Collection<? extends Model> entities) {
        if (entities == null)
            return Collections.emptyList();
        return entities.stream()
                .map(e -> map(e))
                .collect(Collectors.toList());
    }

    protected abstract DTO _map(Model item);
}
