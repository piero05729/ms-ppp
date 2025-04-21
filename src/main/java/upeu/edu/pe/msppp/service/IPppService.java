package upeu.edu.pe.msppp.service;
import upeu.edu.pe.msppp.domain.Ppp;

import java.util.List;
import java.util.Optional;

public interface IPppService {
    Ppp create(Ppp p);
    Ppp update(Ppp p);
    void delete(Long id);
    Optional<Ppp> read(Long id);
    List<Ppp> readAll();
}
