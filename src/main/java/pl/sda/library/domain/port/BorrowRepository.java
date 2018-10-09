package pl.sda.library.domain.port;

import pl.sda.library.domain.model.Borrow;

public interface BorrowRepository {

    void save(Borrow borrow);
}
