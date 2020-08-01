package ru.job4j.forum.control;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.job4j.forum.Main;

import org.junit.jupiter.api.Test;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.service.ForumService;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class TopicControlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ForumService service;

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

    @Test
    @WithMockUser
    public void shouldReturnStatusRedirection() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("name", "Продажа автомобилей");
        map.add("status", "CLOSED");
        this.mockMvc.perform(post("/topic/save").params(map))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        ArgumentCaptor<Topic> argument = ArgumentCaptor.forClass(Topic.class);
        verify(service).addTopic(argument.capture());
        Topic value = argument.getValue();
        assertThat(value.getName(), is("Продажа автомобилей"));
        assertThat(value.getStatus().getText(), is("закрыта"));
    }
}