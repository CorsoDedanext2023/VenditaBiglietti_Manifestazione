package it.dedagroup.manifestazione;

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

    @Test
    @Order(1)
    void addManifestazione_Success() throws Exception {
        String nome = "Serie A";
        mock.perform(MockMvcRequestBuilders.get("/manifestazione/new/{nome}", nome)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @Order(2)
    void addManifestazione_InvalidInput() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/manifestazione/new/")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound()  )
                .andReturn();
    }

    @Test
    @Order(3)
    void updateManifestazione_Success() throws Exception {
        long id = 1;
        mock.perform(MockMvcRequestBuilders.put("/manifestazione/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @Order(4)
    void updateManifestazione_InvalidInput() throws Exception {
        mock.perform(MockMvcRequestBuilders.put("/manifestazione/update/")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }

    @Test
    @Order(5)
    void updateManifestazione_InvalidId() throws Exception {
        String id = "id";
        mock.perform(MockMvcRequestBuilders.put("/manifestazione/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    @Order(6)
    void deleteManifestazione_Success() throws Exception {
        long id = 1;
        mock.perform(MockMvcRequestBuilders.delete("/manifestazione/delete/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @Order(7)
    void deleteManifestazione_InvalidInput() throws Exception {
        mock.perform(MockMvcRequestBuilders.delete("/manifestazione/delete/")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }

    @Test
    @Order(8)
    void deleteManifestazione_InvalidId() throws Exception {
        String id = "id";
        mock.perform(MockMvcRequestBuilders.delete("/manifestazione/delete/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    @Order(9)
    void findAllManifestazioni_Success() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/manifestazione/all")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andReturn();
    }

    @Test
    @Order(10)
    void findManifestazioniByName_Success() throws Exception {
        String nome = "Serie A";
        mock.perform(MockMvcRequestBuilders.get("/manifestazione/nome/{nome}", nome)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andReturn();
    }

    @Test
    @Order(11)
    void findManifestazioniByName_InvalidInput() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/manifestazione/nome/")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }

    @Test
    @Order(12)
    void findManifestazioniByName_InvalidName() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/manifestazione/nome/")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }

    @Test
    @Order(13)
    void findManifestazioniById_Success() throws Exception {
        long id = 1;
        mock.perform(MockMvcRequestBuilders.get("/manifestazione/id/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andReturn();
    }

    @Test
    @Order(14)
    void findManifestazioniById_InvalidInput() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/manifestazione/id/")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }

    @Test
    @Order(15)
    void findManifestazioniById_InvalidId() throws Exception {
        String id = "asd";
        mock.perform(MockMvcRequestBuilders.get("/manifestazione/id/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }
}
