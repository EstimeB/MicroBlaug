//package com.revature.unit;
//
//import com.revature.dao.CommentDao;
//import com.revature.model.Comment;
//import com.revature.service.CommentService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.sql.SQLException;
//import java.sql.Date;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class) // provides jupiter with additional functionalities
//public class CommentsServiceTests {
//    @Mock
//    CommentDao mockcs;
//
//    @InjectMocks
//    CommentService cs;
//
//    Date newDate = 2022-11-23;
//    @Test
//    public void findCommentPositive() throws SQLException {
//        when(mockcs.findCommentByPostId(eq(19)))
//                .thenReturn((new Comment(10, "this is a comment", (Date) 2022-11-23
//                        ,19, 14)));
//        int actual = cs.findAndDeleteCommentById(19);
//        Comment expected = new Comment(10, "this is a comment", 2022-11-23,19, 14);
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//}
