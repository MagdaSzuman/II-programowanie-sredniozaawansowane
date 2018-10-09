package pl.sda.library.infrastructure.memory;

import pl.sda.library.domain.model.Borrow;
import pl.sda.library.domain.port.BorrowRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryBorrowRepository implements BorrowRepository {

    private List<Borrow> borrowList;

    public InMemoryBorrowRepository() {
        this.borrowList = new ArrayList<>();
    }

    @Override
    public void save(Borrow borrow) {
        borrowList.add(borrow);
    }
}
