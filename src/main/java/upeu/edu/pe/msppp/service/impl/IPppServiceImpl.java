package upeu.edu.pe.msppp.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upeu.edu.pe.msppp.domain.Ppp;
import upeu.edu.pe.msppp.repository.PppRepository;
import upeu.edu.pe.msppp.service.IPppService;


import java.util.List;
import java.util.Optional;

@Service
public class IPppServiceImpl implements IPppService {

    @Autowired
    private PppRepository pppRepository;

    @Override
    public Ppp create(Ppp p) {
        long dias = java.time.temporal.ChronoUnit.DAYS.between(p.getFechaIn(), p.getFechaFin());
        if (dias < 60 || dias > 180) {
            throw new RuntimeException("La duración de la práctica debe ser entre 60 y 180 días.");
        }

        if (pppRepository.existsByEstudianteIdAndEmpresaId(p.getIdEstudi().longValue(), p.getEmpresa().getIdempresa())) {
            throw new RuntimeException("El estudiante ya tiene una práctica registrada en esta empresa.");
        }

        return pppRepository.save(p);
    }

    @Override
    public Ppp update(Ppp p) {
        Optional<Ppp> actual = pppRepository.findById(p.getIdppp());
        if (actual.isPresent() && "APROBADA".equalsIgnoreCase(actual.get().getEstado())) {
            throw new RuntimeException("No se puede modificar una práctica que ya fue aprobada.");
        }

        long rechazos = pppRepository.countByEstudianteIdAndEstado(p.getIdEstudi().longValue(), "RECHAZADA");
        if (rechazos >= 2 && "RECHAZADA".equalsIgnoreCase(p.getEstado())) {
            throw new RuntimeException("No se permite modificar la práctica: excedió el límite de rechazos.");
        }

        return pppRepository.save(p);
    }

    @Override
    public void delete(Long id) {
        pppRepository.deleteById(id);
    }

    @Override
    public Optional<Ppp> read(Long id) {
        return pppRepository.findById(id);
    }

    @Override
    public List<Ppp> readAll() {
        return pppRepository.findAll();
    }
}
