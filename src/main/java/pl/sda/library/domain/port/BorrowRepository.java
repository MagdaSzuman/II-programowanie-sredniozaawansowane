package pl.sda.library.domain.port;

import pl.sda.library.domain.model.Borrow;

import java.util.List;

public interface BorrowRepository {

    void save(Borrow borrow);

    List<Borrow> findAll();
}
