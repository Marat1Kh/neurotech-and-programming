package com.epam.rd.tasks.sqlqueries;

/**
 * Implement sql queries like described
 */
public class SqlQueries {
    //Select all employees sorted by last name in ascending order
    //language=HSQLDB
    String select01 = "SELECT * FROM EMPLOYEE ORDER BY LASTNAME";

    //Select employees having no more than 5 characters in last name sorted by last name in ascending order
    //language=HSQLDB
    String select02 = "SELECT * FROM EMPLOYEE WHERE LENGTH(LASTNAME) <=5 ORDER BY LASTNAME";

    //Select employees having salary no less than 2000 and no more than 3000
    //language=HSQLDB
    String select03 = "SELECT * FROM EMPLOYEE WHERE SALARY>=2000 AND SALARY<=3000";

    //Select employees having salary no more than 2000 or no less than 3000
    //language=HSQLDB
    String select04 = "SELECT * FROM EMPLOYEE WHERE SALARY<=2000 OR SALARY>=3000";

    //Select all employees assigned to departments and corresponding department
    //language=HSQLDB
    String select05 = "SELECT * FROM EMPLOYEE AS X, DEPARTMENT AS Y WHERE X.DEPARTMENT = Y.ID";

    //Select all employees and corresponding department name if there is one.
    //Name column containing name of the department "depname".
    //language=HSQLDB
    String select06 = "SELECT X.*,Y.NAME AS depname FROM EMPLOYEE AS X LEFT JOIN DEPARTMENT AS Y ON X.DEPARTMENT = Y.ID";

    //Select total salary of all employees. Name it "total".
    //language=HSQLDB
    String select07 = "SELECT SUM(X.SALARY) AS total FROM EMPLOYEE AS X";

    //Select all departments and amount of employees assigned per department
    //Name column containing name of the department "depname".
    //Name column containing employee amount "staff_size".
    //language=HSQLDB
    String select08 = "SELECT Y.NAME AS depname, COUNT(*) AS staff_size FROM EMPLOYEE AS X JOIN DEPARTMENT AS Y ON X.DEPARTMENT = Y.ID GROUP BY Y.NAME";

    //Select all departments and values of total and average salary per department
    //Name column containing name of the department "depname".
    //language=HSQLDB
    String select09 = "SELECT Y.NAME AS depname, SUM(X.SALARY) AS TOTAL, AVG(X.SALARY) AS AVERAGE FROM EMPLOYEE AS X JOIN DEPARTMENT AS Y ON X.DEPARTMENT = Y.ID GROUP BY Y.NAME";

    //Select lastnames of all employees and lastnames of their managers if an employee has a manager.
    //Name column containing employee's lastname "employee".
    //Name column containing manager's lastname "manager".
    //language=HSQLDB
    String select10 = "SELECT X.LASTNAME AS EMPLOYEE, XX.LASTNAME AS MANAGER FROM EMPLOYEE AS X LEFT JOIN EMPLOYEE AS XX ON X.MANAGER = XX.ID";


}
