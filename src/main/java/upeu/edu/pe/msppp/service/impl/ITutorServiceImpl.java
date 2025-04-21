package upeu.edu.pe.msppp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upeu.edu.pe.msppp.domain.Tutor;
import upeu.edu.pe.msppp.repository.TutorRepository;
import upeu.edu.pe.msppp.service.ITutorService;

import java.util.List;
import java.util.Optional;

@Service
public class ITutorServiceImpl implements ITutorService {

    @Autowired
    private TutorRepository tutorRepository;

    @Override
    public Tutor create(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    @Override
    public Tutor update(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    @Override
    public void delete(Long id) {
        tutorRepository.deleteById(id);
    }

    @Override
    public Optional<Tutor> read(Long id) {
        return tutorRepository.findById(id);
    }

    @Override
    public List<Tutor> readAll() {
        return tutorRepository.findAll();
    }
}
