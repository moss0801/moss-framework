package test.com.mossframework.code;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.mossframework.code.CodeType;
import com.mossframework.code.util.CodeEnumUtils;

public class CodeEnumPerfTest {
    
    private int count = 200*30*(3+8);  // 66000 (200쓰레드, 30개)
    public enum TestEnum {
        Code1(1),
        Code2(2),
        Code3(3),
        Code4(4),
        Code5(5),
        Code6(6),
        Code7(7),
        Code8(8),
        Code9(9),
        Code10(10),
        Code11(11),
        Code12(12),
        Code13(13),
        Code14(14),
        Code15(15),
        Code16(16),
        Code17(17),
        Code18(18),
        Code19(19),
        Code20(20),
        Code21(21),
        Code22(22),
        Code23(23),
        Code24(24),
        Code25(25),
        Code26(26),
        Code27(27),
        Code28(28),
        Code29(29),
        Code30(30),
        Code31(31),
        Code32(32),
        Code33(33),
        Code34(34),
        Code35(35),
        Code36(36),
        Code37(37),
        Code38(38),
        Code39(39),
        Code40(40),
        Code41(41),
        Code42(42),
        Code43(43),
        Code44(44),
        Code45(45),
        Code46(46),
        Code47(47),
        Code48(48),
        Code49(49),
        Code50(50),
        Code51(51),
        Code52(52),
        Code53(53),
        Code54(54),
        Code55(55),
        Code56(56),
        Code57(57),
        Code58(58),
        Code59(59),
        Code60(60),
        Code61(61),
        Code62(62),
        Code63(63),
        Code64(64),
        Code65(65),
        Code66(66),
        Code67(67),
        Code68(68),
        Code69(69),
        Code70(70),
        Code71(71),
        Code72(72),
        Code73(73),
        Code74(74),
        Code75(75),
        Code76(76),
        Code77(77),
        Code78(78),
        Code79(79),
        Code80(80),
        Code81(81),
        Code82(82),
        Code83(83),
        Code84(84),
        Code85(85),
        Code86(86),
        Code87(87),
        Code88(88),
        Code89(89),
        Code90(90),
        Code91(91),
        Code92(92),
        Code93(93),
        Code94(94),
        Code95(95),
        Code96(96),
        Code97(97),
        Code98(98),
        Code99(99),
        Code100(100),
        Code101(101),
        Code102(102),
        Code103(103),
        Code104(104),
        Code105(105),
        Code106(106),
        Code107(107),
        Code108(108),
        Code109(109),
        Code110(110),
        Code111(111),
        Code112(112),
        Code113(113),
        Code114(114),
        Code115(115),
        Code116(116),
        Code117(117),
        Code118(118),
        Code119(119),
        Code120(120),
        Code121(121),
        Code122(122),
        Code123(123),
        Code124(124),
        Code125(125),
        Code126(126),
        Code127(127),
        Code128(128),
        Code129(129),
        Code130(130),
        Code131(131),
        Code132(132),
        Code133(133),
        Code134(134),
        Code135(135),
        Code136(136),
        Code137(137),
        Code138(138),
        Code139(139),
        Code140(140),
        Code141(141),
        Code142(142),
        Code143(143),
        Code144(144),
        Code145(145),
        Code146(146),
        Code147(147),
        Code148(148),
        Code149(149),
        Code150(150),
        Code151(151),
        Code152(152),
        Code153(153),
        Code154(154),
        Code155(155),
        Code156(156),
        Code157(157),
        Code158(158),
        Code159(159),
        Code160(160),
        Code161(161),
        Code162(162),
        Code163(163),
        Code164(164),
        Code165(165),
        Code166(166),
        Code167(167),
        Code168(168),
        Code169(169),
        Code170(170),
        Code171(171),
        Code172(172),
        Code173(173),
        Code174(174),
        Code175(175),
        Code176(176),
        Code177(177),
        Code178(178),
        Code179(179),
        Code180(180),
        Code181(181),
        Code182(182),
        Code183(183),
        Code184(184),
        Code185(185),
        Code186(186),
        Code187(187),
        Code188(188),
        Code189(189),
        Code190(190),
        Code191(191),
        Code192(192),
        Code193(193),
        Code194(194),
        Code195(195),
        Code196(196),
        Code197(197),
        Code198(198),
        Code199(199),
        Code200(200),
        ;
        
        TestEnum(int code) {
            this.code = code;
        }
        
        private int code;
        
        public int getCode() {
            return code;
        }
        
        public static TestEnum getEnum(int code) {
            for (TestEnum value : TestEnum.values()) {
                if (value.getCode() == code)
                    return value;
            }
            return null;
        }
    }
    
    @Ignore
    @Test
    public void aa()
    {
        System.out.println("doReflectionWithMethod");
        TestEnum value = TestEnum.Code1;
        int a = 0;
        Method method = CodeEnumUtils.findCodeMethod(TestEnum.class, CodeType.Code);
        long start = System.currentTimeMillis();
        for (int i=0; i<10000000; i++)
        {
            try {
                a = (int) method.invoke(value);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(a);
    }
    
    @Ignore
    @Test
    public void doRegular()
    {
        System.out.println("doRegular");
        TestEnum value = TestEnum.Code1;
        int a = 0;
        long start = System.currentTimeMillis();
        for (long i=0; i<10000000; i++)
        {
            a = value.getCode();
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(a);
    }
    
    @Ignore
    @Test
    public void doReflectionWithPropertyUtils()
    {
        System.out.println("doReflectionWithPropertyUtils");
        TestEnum value = TestEnum.Code1;
        int a = 0;
        long start = System.currentTimeMillis();
        for (int i=0; i<10000000; i++)
        {
            a = CodeEnumUtils.getCode(value);
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(a);
    }
    
    @Test
    public void getEnumWithMethod() {
        System.out.println("getEnumWithMethod");
        long start = System.currentTimeMillis();
        for (int i=0; i<100000; i++)
        {
            CodeEnumUtils.getEnumWithMethod(TestEnum.class, 200);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
    
    @Test
    public void getEnumWithMap() {
        System.out.println("getEnumWithMap");
        long start = System.currentTimeMillis();
        for (int i=0; i<100000; i++)
        {
            CodeEnumUtils.getEnumWithMethod(MapEnum.class, 200);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
    
    @Test
    public void getEnumWithGetCodeMethod() {
        System.out.println("getEnumWithGetCodeMethod");
        long start = System.currentTimeMillis();
        Method getCodeMethod = CodeEnumUtils.findCodeMethod(TestEnum.class, CodeType.Code);
        for (int i=0; i<100000; i++)
        {
            CodeEnumUtils.getEnumWithGetCodeMethod(TestEnum.class, getCodeMethod, 200);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
    
    @Test
    public void getEnumWithReflection() {
        System.out.println("getEnumWithReflection");
        long start = System.currentTimeMillis();
        for (int i=0; i<100000; i++)
        {
            CodeEnumUtils.getEnum(TestEnum.class, 200);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
    
    
    @Test
    public void getEnum() {
        System.out.println("getEnum");
        long start = System.currentTimeMillis();
        for (int i=0; i<100000; i++)
        {
            TestEnum.getEnum(200);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
    
}
