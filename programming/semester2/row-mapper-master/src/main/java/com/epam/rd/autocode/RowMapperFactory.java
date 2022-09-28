package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;

public class RowMapperFactory {

    public RowMapper<Employee> employeeRowMapper() {
        return resultSet -> {
            String firstname = null;
            String lastname = null;
            String middlename = null;
            BigInteger id = null;
            Position position = null;
            BigDecimal salary = null;
            LocalDate hiredate = null;
            FullName fullname;
            try {
            id = BigInteger.valueOf(resultSet.getInt("ID"));
            firstname = resultSet.getString("FIRSTNAME");
            lastname = resultSet.getString("LASTNAME");
            middlename = resultSet.getString("MIDDLENAME");
            position = Position.valueOf(resultSet.getString("POSITION"));
            hiredate = Date.valueOf(String.valueOf(resultSet.getDate("HIREDATE"))).toLocalDate();
            salary = resultSet.getBigDecimal("SALARY");
            } catch (Exception e){
                e.printStackTrace();
            }
        fullname =new FullName(firstname,lastname, middlename);
            return  new Employee(id, fullname, position, hiredate,salary);
        };
    }
}
