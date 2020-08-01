package ru.job4j.forum.control;

import org.junit.jupiter.api.Test;
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
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.ForumService;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class PostControlTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ForumService service;

    @Test
    @WithMockUser
    public void shouldReturnPostCreateView() throws Exception {
        this.mockMvc.perform(get("/create?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/create"));
    }

    @Test
    @WithMockUser
    public void shouldReturnPostEditView() throws Exception {
        this.mockMvc.perform(get("/update?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/edit"));
    }

    @Test
    @WithMockUser
    public void shouldReturnPostView() throws Exception {
        this.mockMvc.perform(get("/post?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }

    @Test
    @WithMockUser
    public void shouldReturnStatusRedirection() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id", "1");
        map.add("name", "Toyota");
        map.add("description", "Продам автомобиль Toyota");
        map.add("topic_id", "1");
        this.mockMvc.perform(post("/save")
                .params(map))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);

        verify(service).addPost(anyInt(), argument.capture());

        Post post = argument.getValue();

        assertThat(post.getDescription(), is("Продам автомобиль Toyota"));
    }
}