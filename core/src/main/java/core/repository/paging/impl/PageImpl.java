package core.repository.paging.impl;

import core.repository.paging.Pageable;
import core.repository.paging.Page;

import java.util.stream.Stream;

public class PageImpl<T> implements Page<T> {

    private Pageable pageable;
    private Stream<T> content;

    public PageImpl(Pageable pageable, Stream<T> content) {
        this.pageable = pageable;
        this.content = content;
    }

    @Override
    public Pageable getPageable() {
        return pageable;
    }

    @Override
    public Pageable nextPageable() {
        return PageRequest.of(pageable.getPageNumber() + 1, pageable.getPageSize());
    }

    @Override
    public Stream<T> getContent() {
        return content;
    }


}
