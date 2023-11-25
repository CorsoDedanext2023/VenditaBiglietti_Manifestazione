package it.dedagroup.manifestazione;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.dedagroup.manifestazione.DTO.Request.ManifestazioneRequest;
import it.dedagroup.manifestazione.DTO.Request.ManifestazioneRequestConId;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ContextConfiguration(classes = VenditaBigliettiManifestazioneApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class VenditaBigliettiManifestazioneApplicationTests {

    @Autowired
    private MockMvc mock;
    @Autowired
    ObjectMapper objectMapper;


    @Test
    @Order(1)
    void addManifestazione_Success() throws Exception {
        ManifestazioneRequest request = new ManifestazioneRequest("manifestazionePiùBellaDelMondo", 1L, 1L);
        String manifestazioneRequestJson = objectMapper.writeValueAsString(request);
        mock.perform(MockMvcRequestBuilders.post("/manifestazione/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(manifestazioneRequestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Manifestazione creata."));
    }

    @Test
    @Order(2)
    void updateManifestazione_Success() throws Exception {
        ManifestazioneRequestConId request = new ManifestazioneRequestConId(1L, "nuovoNomeDellaManifestazione", 1, 1 ,1L);
        String manifestazioneRequestJson = objectMapper.writeValueAsString(request);
        mock.perform(MockMvcRequestBuilders.post("/manifestazione/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(manifestazioneRequestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Manifestazione aggiornata."));
    }

    @Test
    @Order(3)
    void deleteManifestazione_Success() throws Exception {
        String id = "1";
        mock.perform(MockMvcRequestBuilders.delete("/manifestazione/delete/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @Order(4)
    void deleteManifestazione_InvalidInput() throws Exception {
        mock.perform(MockMvcRequestBuilders.delete("/manifestazione/delete/")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }

    @Test
    @Order(5)
    void deleteManifestazione_InvalidId() throws Exception {
        String id = "idAlfabeticoAnzichéNumerico";
        mock.perform(MockMvcRequestBuilders.delete("/manifestazione/delete/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    @Order(6)
    void findAllManifestazioni_Success() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/manifestazione/find-all")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andReturn();
    }

    @Test
    @Order(7)
    void findManifestazioneByName_Success() throws Exception {
        String nome = "Concerto acustico";
        mock.perform(MockMvcRequestBuilders.get("/manifestazione/find-name/{nome}", nome)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andReturn();
    }

    @Test
    @Order(8)
    void findManifestazioneByName_InvalidName() throws Exception {
        String name = "nomeCheNonDovrebbeEsistereSulDatabaseOForseNo";
        mock.perform(MockMvcRequestBuilders.get("/manifestazione/find-name/{name}",  name)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }

    @Test
    @Order(9)
    void findManifestazioneById_Success() throws Exception {
        long id = 1;
        mock.perform(MockMvcRequestBuilders.get("/manifestazione/find-id/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andReturn();
    }

    @Test
    @Order(10)
    void findManifestazioneById_InvalidInput() throws Exception {
        String id = "idAlfabeticoAnzichéNumerico";
        mock.perform(MockMvcRequestBuilders.get("/manifestazione/find-id/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }
}
