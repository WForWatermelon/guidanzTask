package com.guidanz.crud;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@ApiResponses(value ={ @ApiResponse(code= 204,message="Record not found!"),@ApiResponse(code =205,message ="Duplicate entry found." )})
@Api(value = "Employee Controller",description = "Rest controller for employee.")
public class Controllers  {


    @CrossOrigin
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @ApiOperation(value = "Creates a employee entity.")
    public ResponseEntity create(@RequestBody Employee employee) {
        try {
            new RDBMS().create(employee);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new JsonResponse("Employee data added successfully."));
    }
    @CrossOrigin
    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    @ApiOperation(value = "Updates a employee entity.")
    public ResponseEntity update(@RequestBody Employee employee) {
        try {
            new RDBMS().update(employee);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new JsonResponse("Employee data updated successfully."));
    }
    @CrossOrigin
    @RequestMapping(path = "/read/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Reads a employee entity.")
    public ResponseEntity<Employee> read(@PathVariable(value = "id") int id) {
        Employee emp=new Employee();
        try {
            emp=new RDBMS().read(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return  ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(emp);

    }
    @CrossOrigin
    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Deletes a employee entity.")
    public ResponseEntity delete(@PathVariable(value = "id") int id) {
        try {
            new RDBMS().delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new JsonResponse("Employee data deleted successfully."));
    }
    @CrossOrigin
    @RequestMapping(path = "/read_all", method = RequestMethod.GET)
    @ApiOperation(value = "Reads a employee entity.")
    public ResponseEntity<List<Employee>> readAll() {
        List<Employee> emp=new ArrayList<Employee>();
        try {
            emp=new RDBMS().readAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(emp);
    }
}
