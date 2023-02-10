package com.alpaca.pacabooks.service;

import com.alpaca.pacabooks.vo.AccountVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AccountService {

    public List listAcc() throws DataAccessException;

    public String getPwd() throws DataAccessException;

    public AccountVO findAccById() throws DataAccessException;

    public AccountVO loginCheck(String id, String pwd) throws DataAccessException;

    public void addAcc(String id, String pwd, String email, String tel) throws DataAccessException;
}
