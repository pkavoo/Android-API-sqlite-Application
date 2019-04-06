package com.sendy.assignment.database;

public class Worker {
    public static final String TABLE_NAME = "worker";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_SALARY = "salary";




    private String name, age, salary;




    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_AGE + " TEXT,"
                    + COLUMN_SALARY + " TEXT"
                    + ")";

    public Worker() {
    }

    public Worker(String name, String age, String salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String l_name) {
        this.age = age;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String location) {
        this.salary = salary;
    }

}
