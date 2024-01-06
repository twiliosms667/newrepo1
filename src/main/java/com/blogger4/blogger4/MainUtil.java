package com.blogger4.blogger4;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainUtil {
    public static void main(String[] args) {
        List<Employee> data = Arrays.asList(
                new Employee("Mike", 5000),
                new Employee("Stalin", 10000),
                new Employee("Adam", 5000)
        );



        Map<Double, List<Employee>> groups= data.stream().collect(Collectors.groupingBy(Employee::getSalary));
          System.out.println(groups);

        System.out.println(groups);

        for (Map.Entry<Double,List<Employee>> entry:groups.entrySet()){
            double salary=entry.getKey();
            List<Employee> employeeList=entry.getValue();
            System.out.println("Employee with salary"+salary+";");
            for (Employee employee:employeeList){
                System.out.println("\t"+employee.getName());

            }
        }


        }
    }


