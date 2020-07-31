package ru.job4j.forum.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class TopicControlTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnTopicCreateView() throws Exception {
        this.mockMvc.perform(get("/topic/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("topic/create"));
    }

    @Test
    @WithMockUser
    public void shouldReturnTopicEditView() throws Exception {
        this.mockMvc.perform(get("/topic/update?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("topic/edit"));
    }
}