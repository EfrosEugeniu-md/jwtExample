package com.example.efros.demo.jwt;

import com.example.efros.demo.jwt.domens.Account;
import com.example.efros.demo.jwt.domens.Industry;
import com.example.efros.demo.jwt.service.AccountService;
import com.example.efros.demo.jwt.web.AccountController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountController.class)
public class TestAccountRESTController {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountService accountService;

    Account accountThis;

    Account accountThisModify;

    Long ACCOUNT_ID = 300L;

    @BeforeEach
    void init() {
        accountThis = new Account(300L, "A", "B", Industry.A,"C","D");
        accountThisModify = new Account(300L, "A", "B", Industry.A,"Cc","Dd");
    }

    @Test
    void findAll_thenReturnsList() throws Exception {
        when(accountService.findAll()).thenReturn(Collections.singletonList(
                accountThis
        ));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/accounts")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNotEmpty());
    }

    @Test
    void findById_thenReturnsDepartment() throws Exception {
        when(accountService.findById(anyInt())).thenReturn(accountThis);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/accounts/" + accountThis.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(accountThis.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("firstName").value(accountThis.getFirstName()))

        ;
    }

    @Test
    void save_thenReturnsDepartment() throws Exception {
        when(accountService.save(any(Account.class))).thenReturn(accountThis);

        String json = objectMapper.writeValueAsString(accountThis);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/accounts")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(accountThis.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("firstName").value(accountThis.getFirstName()))

        ;
    }

//    @Test
//    void update_thenReturnsDepartment() throws Exception {
//
//        when(accountService.update(anyInt(), any(Department.class))).thenReturn(accountThisModify);
//
//        String json = objectMapper.writeValueAsString(accountThisModify);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .put("/departments/" + accountThis.getId())
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json)
//                )
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("id").value(accountThisModify.getId()))
//                .andExpect(MockMvcResultMatchers.jsonPath("departmentName").value(accountThisModify.getDepartmentName()))
//                .andExpect(MockMvcResultMatchers.jsonPath("location").value(accountThisModify.getLocation()))
//        ;
//    }

    @Test
    void delete_thenReturns_204() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/accounts/" + ACCOUNT_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(204));

        verify(accountService).delete(Math.toIntExact(ACCOUNT_ID));
    }
}
