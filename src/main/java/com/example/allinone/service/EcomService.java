package com.example.allinone.service;

import com.example.allinone.model.EcomModel;
import com.example.allinone.model.EcomReq;
import com.example.allinone.repo.EcomRepo;
import com.example.allinone.utilis.EcomUtilis;
import com.example.allinone.utilis.ResponseUtilis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class EcomService {
    @Autowired
    private ResponseUtilis responseUtilis;

    @Autowired
    private EcomRepo ecomRepo;

    @Autowired
    private EcomUtilis ecomUtilis;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ResponseEntity createUpdate(EcomModel ecomModel){
        int count = ecomRepo.update(ecomModel.getUserId(),ecomModel.getCustomerId(),ecomModel.getName(),ecomModel.getEmail(),ecomModel.getMobile());
        if(count==0)
            ecomRepo.save(ecomModel.getUserId(),ecomModel.getCustomerId(),ecomModel.getName(),ecomModel.getEmail(),ecomModel.getMobile());
        return responseUtilis.getTemplate(200,"Inserted or Updated Successfully");
    }

    public ResponseEntity get(EcomReq ecomReq){
        String query = "select *from ecom";
        String where = ecomUtilis.getWhereClass(ecomReq.getUserId(),ecomReq.getCustomerId(),ecomReq.getFilter());
        return responseUtilis.getTemplate(200,ecomUtilis.writeAsString(jdbcTemplate.queryForObject(query+where,this::thismap)));
    }
    public EcomModel thismap(ResultSet rs,int rownum) throws SQLException{
        EcomModel ecomModel = new EcomModel();
        ecomModel.setUserId(rs.getInt("user_id"));
        ecomModel.setCustomerId(rs.getInt("customer_id"));
        ecomModel.setName(rs.getString("name"));
        ecomModel.setMobile(rs.getString("mobile"));
        ecomModel.setEmail(rs.getString("email"));
        return ecomModel;
    }
}
