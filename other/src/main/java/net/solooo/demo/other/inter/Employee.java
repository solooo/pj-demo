package net.solooo.demo.other.inter;

/**
 * Created by Eric on 2017/7/1.
 */
public class Employee implements Person, Identified {
    @Override
    public int getId() {
        return 0;
    }

    public static void main(String[] args) {
        Employee employee = new Employee();
        System.out.println(employee.getId());
    }
}
