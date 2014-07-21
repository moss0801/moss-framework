package test.com.mossframework.code;


public enum RawEnum {
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
    ;
    
    RawEnum(int code) {
        this.code = code;
    }
    
    private int code;
    
    public int getCode() {
        return code;
    }
    
    public static RawEnum getEnum(int code) {
        for (RawEnum value : RawEnum.values()) {
            if (value.getCode() == code)
                return value;
        }
        return null;
    }
}
