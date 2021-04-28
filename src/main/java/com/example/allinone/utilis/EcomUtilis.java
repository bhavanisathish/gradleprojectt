package com.example.allinone.utilis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class EcomUtilis {
    public String getWhereClass(int userId,int customerId,String filter){
        StringBuilder sb = new StringBuilder();
        sb.append(" where ");
        sb.append(getBase(userId,customerId));
        if(!filter.equalsIgnoreCase("")){
            if(!sb.toString().endsWith("where")){
                sb.append(" and ");
            }
            sb.append(filterseparation(filter));
        }
        return sb.toString();
    }
    public String getBase(int userId,int customerId){
        StringBuilder sgb = new StringBuilder();
        Boolean pre = false;
        if(userId !=0){
            pre = true;
            sgb.append(String.format(" user_id = %s ",userId));
        }
        if(customerId!=0){
            sgb.append( pre ? String.format(" and customer_id = %s ",customerId): String.format("customer_id = %s ",customerId));
        }
        return sgb.toString();
    }
    public String filterseparation(String filter){
        StringBuilder sb = new StringBuilder();
        boolean isfirst = true;
        sb.append(" ( ");
        List<String> filters = Arrays.asList(filter.split(","));
        for (String s: filters) {
            if (isfirst) {
                isfirst = false;
            } else {
                sb.append(" and ");
            }
            sb.append(camelcase(s));
        }
        sb.append(" ) ");
        return sb.toString();
    }
    public String camelcase(String text){
        StringBuilder sb = new StringBuilder();
        boolean specialchar = false;
        boolean like = false;
        Pattern pat = Pattern.compile("[a-zA-Z0-9]*");
        for(char ch:text.toCharArray()){
            if(!pat.matcher(Character.toString(ch)).matches() && ch != '!' && !specialchar){
                specialchar = true;
                if(":".equalsIgnoreCase(Character.toString(ch))){
                    like = true;
                    sb.append(ch).append(":text ilike '%");
                }else if("-".equalsIgnoreCase(Character.toString(ch))){
                    sb.append(" != '");
                }
                else sb.append(ch).append(" '");
                continue;
            }
            if(Character.isUpperCase(ch) && !specialchar){
                    sb.append("_").append(ch);
            }
            else{
                if(Character.toString(ch).equalsIgnoreCase("'")){
                    sb.append("''");
                }else{
                    sb.append(ch);
                }
            }
        }
        if(like){
            sb.append("%'");
        }
        else{
            sb.append("'");
        }
        return sb.toString();
    }

    public String writeAsString(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        }catch (JsonProcessingException e){
            return e.getMessage();
        }

    }

}
