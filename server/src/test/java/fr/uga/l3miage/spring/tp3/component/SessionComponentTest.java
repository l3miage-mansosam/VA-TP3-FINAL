package fr.uga.l3miage.spring.tp3.component;

import fr.uga.l3miage.spring.tp3.components.SessionComponent;
import fr.uga.l3miage.spring.tp3.models.EcosSessionEntity;
import fr.uga.l3miage.spring.tp3.models.EcosSessionProgrammationEntity;
import fr.uga.l3miage.spring.tp3.models.EcosSessionProgrammationStepEntity;
import fr.uga.l3miage.spring.tp3.repositories.EcosSessionProgrammationRepository;
import fr.uga.l3miage.spring.tp3.repositories.EcosSessionProgrammationStepRepository;
import fr.uga.l3miage.spring.tp3.repositories.EcosSessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SessionComponentTest {

    @Autowired
    private SessionComponent sessionComponent;

    @MockBean
    private EcosSessionRepository ecosSessionRepository;

    @MockBean
    private EcosSessionProgrammationRepository ecosSessionProgrammationRepository;

    @MockBean
    private EcosSessionProgrammationStepRepository ecosSessionProgrammationStepRepository;

    @Test
    public void testCreateSession() {
        EcosSessionEntity sessionEntity = new EcosSessionEntity();
        EcosSessionProgrammationEntity programmationEntity = new EcosSessionProgrammationEntity();
        List<EcosSessionProgrammationStepEntity> stepEntities = new ArrayList<>();

        programmationEntity.setEcosSessionProgrammationStepEntities(Set.of());
        sessionEntity.setEcosSessionProgrammationEntity(programmationEntity);

        when(ecosSessionProgrammationStepRepository.saveAll(stepEntities)).thenReturn(stepEntities);
        when(ecosSessionProgrammationRepository.save(programmationEntity)).thenReturn(programmationEntity);
        when(ecosSessionRepository.save(sessionEntity)).thenReturn(sessionEntity);

        EcosSessionEntity result = sessionComponent.createSession(sessionEntity);

        assertEquals(sessionEntity, result);
        verify(ecosSessionProgrammationStepRepository, times(1)).saveAll(stepEntities);
        verify(ecosSessionProgrammationRepository, times(1)).save(programmationEntity);
        verify(ecosSessionRepository, times(1)).save(sessionEntity);
    }
}