package com.antoni.fusteria.service;

import com.antoni.fusteria.domain.model.Estat_Treball;
import com.antoni.fusteria.domain.model.Treball;
import com.antoni.fusteria.domain.repository.TreballRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TreballServiceTest {

    @Mock
    private TreballRepository treballRepository;

    @InjectMocks
    private TreballService treballService;

    @Test
    void filtrarPerEstatRetornaNomesTreballsCorrectes() {
        Treball t1 = new Treball("Porta entrada");
        t1.setEstat(Estat_Treball.PENDENT);

        Treball t2 = new Treball("Moble cuina");
        t2.setEstat(Estat_Treball.EN_CURS);

        Treball t3 = new Treball("Armari dormitori");
        t3.setEstat(Estat_Treball.FINALITZAT);

        when(treballRepository.findAll()).thenReturn(List.of(t1, t2, t3));

        List<Treball> resultat = treballService.searchTreballs(null, Estat_Treball.PENDENT);

        assertEquals(1, resultat.size());
        assertEquals(Estat_Treball.PENDENT, resultat.get(0).getEstat());
    }

    @Test
    void cercaPerTitolRetornaTreballsCorrectes() {
        Treball t1 = new Treball("Porta entrada");
        t1.setEstat(Estat_Treball.PENDENT);

        Treball t2 = new Treball("Moble cuina");
        t2.setEstat(Estat_Treball.EN_CURS);

        when(treballRepository.findAll()).thenReturn(List.of(t1, t2));

        List<Treball> resultat = treballService.searchTreballs("porta", null);

        assertEquals(1, resultat.size());
        assertEquals("Porta entrada", resultat.get(0).getTitol());
    }

    @Test
    void senseFiltresTornaTotsElsTreballs() {
        Treball t1 = new Treball("Porta");
        Treball t2 = new Treball("Moble");
        Treball t3 = new Treball("Armari");

        when(treballRepository.findAll()).thenReturn(List.of(t1, t2, t3));

        List<Treball> resultat = treballService.searchTreballs(null, null);

        assertEquals(3, resultat.size());
    }
}
