package com.alpaca.pacabooks.service;

import com.alpaca.pacabooks.dao.AccountDAO;
import com.alpaca.pacabooks.vo.AccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDAO accountDAO;


    @Override
    public List<AccountVO> listAcc() throws DataAccessException {
        return accountDAO.listAcc();
    }

    @Override
    public void addAcc(String id, String pwd, String email, String tel) throws DataAccessException{
        accountDAO.addAcc(id,pwd,email, tel);
    }

    @Override
    public String getPwd() throws DataAccessException {
        return null;
    }

    @Override
    public AccountVO findAccById() throws DataAccessException {
        return null;
    }


    // 로그인 계정확인
    @Override
    public AccountVO loginCheck(String id, String pwd) throws DataAccessException {
        AccountVO accVO;
        try{
            accVO = accountDAO.getAccById(id);
            if(accVO==null){
                return null;
            }

        }catch (Exception e){  // 에러시(계정이 존재하지 않을경우)
            e.printStackTrace();
            return null;
        }

        if(accVO.getPassword().equals(pwd)){  // id, pwd가 일치할 경우
            return accVO;
        }else{
            return null;
        }
    }
}
