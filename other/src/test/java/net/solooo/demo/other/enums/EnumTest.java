package net.solooo.demo.other.enums;

import org.junit.Test;

/**
 * Description:
 * Author:Eric
 * Date:2017/8/2
 */
public class EnumTest {

    @Test
    public void operatorEnumTest() {
        System.out.println(OperatorEnum.EqualTo.name());
        System.out.println(OperatorEnum.values());
        System.out.println(OperatorEnum.getOp("$eq"));
        System.out.println(OperatorEnum.EqualTo.getName());
        System.out.println(OperatorEnum.EqualTo.getOp());
    }

}
