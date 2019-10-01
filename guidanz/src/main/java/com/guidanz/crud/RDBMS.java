package com.guidanz.crud;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RDBMS {


    Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com/TKWIxnfURm", "TKWIxnfURm", "MtWddK9WsA");
    static PreparedStatement stmt;
    boolean flag=false;
    public RDBMS() throws SQLException {
    }


    public final void create(Employee employee) throws SQLException {
       String insertStatement= "INSERT INTO Employee(empID,name,salary) VALUES (?,?,?);";
       stmt= con.prepareStatement(insertStatement);
       stmt.setObject(1, employee.getEmpID());
       stmt.setObject(2,employee.getName());
       stmt.setObject(3,employee.getSalary());
       stmt.execute();
       con.close();
    }

    public final List<Employee> readAll() throws SQLException {

        List list = new ArrayList();
        String selectStatement= "SELECT * FROM Employee;";
        stmt=con.prepareStatement(selectStatement);
        ResultSet resultSet= stmt.executeQuery();
        while (resultSet.next()){
            flag=true;
            Employee employee = new Employee();
            employee.setEmpID((Integer) resultSet.getObject("empID"));
            employee.setName((String) resultSet.getObject("name"));
            employee.setSalary((Integer) resultSet.getObject("salary"));
            list.add(employee);
        }
        if(flag==false)
            throw new SQLException("No records created.");
        con.close();
        return list;
    }
    public final void update(Employee e) throws SQLException{

        if(e.getName()!=null)
            flag= true;
        String updateStatement= "UPDATE Employee SET empID=?,name=?,salary=? WHERE empID=?;";
        stmt=con.prepareStatement(updateStatement);
        stmt.setObject(1, e.getEmpID());
        stmt.setObject(2,e.getName());
        stmt.setObject(3,e.getSalary());
        stmt.setObject(4,e.getEmpID());
        if(flag==false)
            throw new SQLException("Employee not found!");
        stmt.execute();
        con.close();
    }
    public final void delete(int id) throws SQLException {
        if(id!=0)
            flag= true;
        String deleteStatement="DELETE FROM Employee Where empID=?;";
        stmt=con.prepareStatement(deleteStatement);
        stmt.setObject(1,id);
        if(flag==false)
            throw new SQLException("Employee not found!");
        stmt.execute();
        con.close();
    }
    public final Employee read(int empID) throws SQLException {
        Employee employee = new Employee();

        String selectStatement= "SELECT * FROM Employee WHERE empID=?;";
        stmt=con.prepareStatement(selectStatement);
        stmt.setObject(1,empID);
        ResultSet resultSet= stmt.executeQuery();
        while (resultSet.next()){
            flag=true;
            employee.setEmpID((Integer) resultSet.getObject("empID"));
            employee.setName((String) resultSet.getObject("name"));
            employee.setSalary((Integer) resultSet.getObject("salary"));

        }
        if(flag==false)
            throw new SQLException("Employee not found!");
        con.close();
        return employee;

    }

}
