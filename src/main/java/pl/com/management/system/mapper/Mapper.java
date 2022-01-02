package pl.com.management.system.mapper;

import java.util.Collection;
import java.util.List;

public interface Mapper<E, D> {
    E map(final D item);

    List<E> map(final Collection<? extends D> entities);
}
