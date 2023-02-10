package com.alpaca.pacabooks.dao;


import com.alpaca.pacabooks.vo.AccountVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountDAO {
    public List<AccountVO> listAcc();

    public AccountVO getAccById(String id);

    public String getPwd(String id);

    public void addAcc(String id, String password, String email, String tel);
}




