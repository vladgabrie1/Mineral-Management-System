package be.kdg.prog6.Landside.testcontainers;

import be.kdg.prog6.Landside.TestIds;
import be.kdg.prog6.Landside.adapters.out.db.JpaEntities.AppointmentJpaEntity;
import be.kdg.prog6.Landside.adapters.out.db.JpaEntities.WeighingBridgeJpaEntity;
import be.kdg.prog6.Landside.adapters.out.db.JpaRepositories.AppointmentJpaRepository;
import be.kdg.prog6.Landside.adapters.out.db.JpaRepositories.WeighingBridgeJpaRepository;
import be.kdg.prog6.Landside.domain.WeighBridge.WeighingBridge;
import be.kdg.prog6.Landside.ports.out.LoadSchedulePort;
import be.kdg.prog6.Landside.ports.out.LoadWeighingBridgePort;
import be.kdg.prog6.Landside.ports.out.UpdateTruckPort;
import be.kdg.prog6.Landside.ports.out.UpdateWeighingBridgePort;
import be.kdg.prog6.common.domain.MaterialType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class TruckEntryControllerIntegrationTest extends AbstractDatabaseTest {
    private static final String ENDPOINT = "/truck/entry/ABCD12/2024-12-01T10:15:00";
    @Autowired
    private LoadSchedulePort loadSchedulePort;
    @Autowired
    private LoadWeighingBridgePort loadWeighingBridgePort;
    @Autowired
    private UpdateWeighingBridgePort updateWeighingBridgePort;
    @Autowired
    private WeighingBridgeJpaRepository weighingBridgeJpaRepository;
    @Autowired
    private AppointmentJpaRepository appointmentJpaRepository;
    @Autowired
    private UpdateTruckPort updateTruckPort;
    @Autowired
    private MockMvc mvc;

    @Test
    void truckEntry() throws Exception {
        weighingBridgeJpaRepository.save(new WeighingBridgeJpaEntity(1, true));
        appointmentJpaRepository.save(new AppointmentJpaEntity(UUID.randomUUID(), TestIds.SELLER_ID.id(), "ABCD12", MaterialType.CEMENT, LocalDateTime.of(2024, 12, 1, 10, 0), LocalDateTime.of(2024, 12, 1, 11, 0), UUID.randomUUID()));
        mvc.perform(get(ENDPOINT).with(jwt()
                                .authorities(new SimpleGrantedAuthority("admin")))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        Optional<WeighingBridgeJpaEntity> weighingBridgeJpaEntity = weighingBridgeJpaRepository.findById(1);
        assertFalse(weighingBridgeJpaEntity.get().isAvailable());
    }
}
