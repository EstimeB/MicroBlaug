package com.revature.unit;

import com.revature.dao.PostDao;
import com.revature.model.Post;
import com.revature.service.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceUnitTests {
    @Mock
    PostDao mockPd;

    @InjectMocks
    PostService ps;

    @Test
    public void updatePostPositiveTest() throws SQLException, IOException {
        when(mockPd.updatePost(new Post(12, "Friday", "Friday is the best day of the week.", 103)))
                .thenReturn(new Post(12, "Friday", "Friday is the best day of the week.", 103));

        Post actual = ps.updatePost(new Post(12, "Friday", "Friday is the best day of the week.", 103));
        Post expected = new Post(12, "Friday", "Friday is the best day of the week.", 103);

        Assertions.assertEquals(expected, actual);
    }
}
