package core.repository.paging;

import java.util.stream.Stream;

/**
 * author: radu
 */
public interface Page<T> {
    Pageable getPageable();

    Pageable nextPageable();

    Stream<T> getContent();


}
