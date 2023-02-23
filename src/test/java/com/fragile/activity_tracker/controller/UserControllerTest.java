//package com.fragile.activity_tracker.controller;
//
//import com.fragile.activity_tracker.entity.User;
//import com.fragile.activity_tracker.service.UserService;
//import com.fragile.activity_tracker.serviceIpm.UserServiceIpm;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
////import org.junit.jupiter.api.Test;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//
//@RunWith(MockitoJUnitRunner.class)
//public class UserControllerTest {
//
//    @Mock
//    UserServiceIpm userService;
//
//    @InjectMocks
//    UserController userController;
//
//    @Test
//    public void testLoginUser_Success() throws Exception {
//        User user = new User();
//        user.setEmail("test@email.com");
//        user.setPassword("password");
//
//        User loggedInUser = new User();
//        loggedInUser.setId(1L);
//        loggedInUser.setUsername("testuser");
//
//        Mockito.when(userService.findUserByEmailAndPassword(user))
//                .thenReturn(loggedInUser);
//
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpSession session = mock(HttpSession.class);
//        Mockito.when(request.getSession()).thenReturn(session);
//
//        HttpServletResponse response = mock(HttpServletResponse.class);
//
//        String result = userController.LoginUser(user, request,response);
//        assertEquals("redirect:/dashboard", result);
//        verify(request.getSession(true)).setMaxInactiveInterval(300);
//        verify(request.getSession(true)).setAttribute("id", 1L);
//        verify(request.getSession(true)).setAttribute("username", "testuser");
//    }
//
//    @Test
//    public void testLoginUser_Failure() throws Exception {
//        User user = new User();
//        user.setEmail("test@email.com");
//        user.setPassword("password");
//
//        Mockito.when(userService.findUserByEmailAndPassword(user))
//                .thenReturn(null);
//
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpSession session = mock(HttpSession.class);
//        Mockito.when(request.getSession()).thenReturn(session);
//
//        HttpServletResponse response = mock(HttpServletResponse.class);
//
//        String result = userController.LoginUser(user, request, response);
//
//        assertEquals("redirect:/login", result);
//        verify(request.getSession()).invalidate();
//    }
//
//    @Test
//    public void testLogout() throws Exception {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpSession session = mock(HttpSession.class);
//        Mockito.when(request.getSession()).thenReturn(session);
//
//        String result = userController.logout(request);
//
//        assertEquals("redirect:/", result);
//        verify(request.getSession()).invalidate();
//    }
//
//}
