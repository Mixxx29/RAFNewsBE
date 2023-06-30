package rs.raf.rafnews.respositories.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Pageable {
    private int pageIndex;
    private int pageSize;

    public static Pageable of(final int pageIndex, final int pageSize) {
        return new Pageable(pageIndex, pageSize);
    }
}
