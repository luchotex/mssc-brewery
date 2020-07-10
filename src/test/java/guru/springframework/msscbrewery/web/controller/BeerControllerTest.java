package guru.springframework.msscbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbrewery.web.model.BeerDto;
import guru.springframework.msscbrewery.web.services.BeerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BeerController.class)
public class BeerControllerTest {

  public static final String BEER_URL = "/api/v1/beer/";
  @MockBean private BeerService beerService;

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  private BeerDto validBeer;

  @Before
  public void setUp() throws Exception {
    validBeer =
        BeerDto.builder()
            .id(UUID.randomUUID())
            .beerName("Beer1")
            .beerStyle("PALE_ALE")
            .upc(123456789012L)
            .build();
  }

  @Test
  public void getBeerById_successfulTest() throws Exception {
    // given
    given(beerService.getBeerById(any(UUID.class))).willReturn(validBeer);

    mockMvc
        .perform(get(BEER_URL + validBeer.getId().toString()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(validBeer.getId().toString())))
        .andExpect(jsonPath("$.beerName", is("Beer1")));
  }

  @Test
  public void create_successfulTest() throws Exception {
    // given
    BeerDto beerDto = validBeer;
    beerDto.setId(null);
    String beerToJson = objectMapper.writeValueAsString(validBeer);
    BeerDto createdDto = BeerDto.builder().id(UUID.randomUUID()).beerName("New Beer").build();

    // given
    given(beerService.create(any())).willReturn(createdDto);

    // when
    mockMvc
        .perform(post(BEER_URL).contentType(MediaType.APPLICATION_JSON).content(beerToJson))
        .andExpect(status().isCreated());
  }

  @Test
  public void update_successfulTest() throws Exception {

    // given
    BeerDto beerDto = validBeer;
    beerDto.setId(null);
    String beerToJson = objectMapper.writeValueAsString(validBeer);

    // when
    mockMvc
        .perform(
            put(BEER_URL + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerToJson))
        .andExpect(status().isNoContent());
    // then
    then(beerService).should().update(any(), any());
  }
}
