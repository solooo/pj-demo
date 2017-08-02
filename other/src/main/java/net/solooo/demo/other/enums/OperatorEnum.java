package net.solooo.demo.other.enums;

/**
 * Description:
 * Author:Eric
 * Date:2017/8/2
 */
public enum OperatorEnum {
    EqualTo("$eq", "=");

    private String name;

    private String op;

    OperatorEnum(String name, String op) {
        this.name = name;
        this.op = op;
    }

    public String getName() {
        return name;
    }

    public String getOp() {
        return op;
    }

    public static String getOp(String name) {
        for (OperatorEnum operatorEnum : OperatorEnum.values()) {
            if (operatorEnum.getName().equals(name)) {
                return operatorEnum.getOp();
            }
        }
        return null;
    }
}
