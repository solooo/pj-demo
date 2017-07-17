package net.solooo.demo.other.literal;

/**
 * Description:
 * Author:Eric
 * Date:2017/5/4
 */
public class Hello {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        System.out.println("hello.toString....");

        return "Hello{" +
                "name='" + name + '\'' +
                '}';
    }
}
