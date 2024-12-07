package nxu.it.movieapi;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class MovieApiV1Tests extends BaseWebTestCase{
    @Autowired
    MockMvc mvc;

    @Test
    void test_index() throws Exception{
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));

    }

  /*  @Test
    void test_authors() throws Exception{
        mvc.perform(get("/movie/api/v1/authors"))
                .andExpect(status().isOk())
                .andExpect());

    }*/
}
