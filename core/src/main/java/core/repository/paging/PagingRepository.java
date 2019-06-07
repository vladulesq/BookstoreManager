package core.repository.paging;




import core.model.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

/**
 * author: radu
 */
@NoRepositoryBean
public interface PagingRepository<ID extends Serializable,
        T extends BaseEntity<ID>>
        extends Repository<ID, T> {

    Page<T> findAll(Pageable pageable);

}
