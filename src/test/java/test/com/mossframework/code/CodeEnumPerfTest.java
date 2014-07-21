package test.com.mossframework.code;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        Code201(201),
        Code202(202),
        Code203(203),
        Code204(204),
        Code205(205),
        Code206(206),
        Code207(207),
        Code208(208),
        Code209(209),
        Code210(210),
        Code211(211),
        Code212(212),
        Code213(213),
        Code214(214),
        Code215(215),
        Code216(216),
        Code217(217),
        Code218(218),
        Code219(219),
        Code220(220),
        Code221(221),
        Code222(222),
        Code223(223),
        Code224(224),
        Code225(225),
        Code226(226),
        Code227(227),
        Code228(228),
        Code229(229),
        Code230(230),
        Code231(231),
        Code232(232),
        Code233(233),
        Code234(234),
        Code235(235),
        Code236(236),
        Code237(237),
        Code238(238),
        Code239(239),
        Code240(240),
        Code241(241),
        Code242(242),
        Code243(243),
        Code244(244),
        Code245(245),
        Code246(246),
        Code247(247),
        Code248(248),
        Code249(249),
        Code250(250),
        Code251(251),
        Code252(252),
        Code253(253),
        Code254(254),
        Code255(255),
        Code256(256),
        Code257(257),
        Code258(258),
        Code259(259),
        Code260(260),
        Code261(261),
        Code262(262),
        Code263(263),
        Code264(264),
        Code265(265),
        Code266(266),
        Code267(267),
        Code268(268),
        Code269(269),
        Code270(270),
        Code271(271),
        Code272(272),
        Code273(273),
        Code274(274),
        Code275(275),
        Code276(276),
        Code277(277),
        Code278(278),
        Code279(279),
        Code280(280),
        Code281(281),
        Code282(282),
        Code283(283),
        Code284(284),
        Code285(285),
        Code286(286),
        Code287(287),
        Code288(288),
        Code289(289),
        Code290(290),
        Code291(291),
        Code292(292),
        Code293(293),
        Code294(294),
        Code295(295),
        Code296(296),
        Code297(297),
        Code298(298),
        Code299(299),
        Code300(300),
        Code301(301),
        Code302(302),
        Code303(303),
        Code304(304),
        Code305(305),
        Code306(306),
        Code307(307),
        Code308(308),
        Code309(309),
        Code310(310),
        Code311(311),
        Code312(312),
        Code313(313),
        Code314(314),
        Code315(315),
        Code316(316),
        Code317(317),
        Code318(318),
        Code319(319),
        Code320(320),
        Code321(321),
        Code322(322),
        Code323(323),
        Code324(324),
        Code325(325),
        Code326(326),
        Code327(327),
        Code328(328),
        Code329(329),
        Code330(330),
        Code331(331),
        Code332(332),
        Code333(333),
        Code334(334),
        Code335(335),
        Code336(336),
        Code337(337),
        Code338(338),
        Code339(339),
        Code340(340),
        Code341(341),
        Code342(342),
        Code343(343),
        Code344(344),
        Code345(345),
        Code346(346),
        Code347(347),
        Code348(348),
        Code349(349),
        Code350(350),
        Code351(351),
        Code352(352),
        Code353(353),
        Code354(354),
        Code355(355),
        Code356(356),
        Code357(357),
        Code358(358),
        Code359(359),
        Code360(360),
        Code361(361),
        Code362(362),
        Code363(363),
        Code364(364),
        Code365(365),
        Code366(366),
        Code367(367),
        Code368(368),
        Code369(369),
        Code370(370),
        Code371(371),
        Code372(372),
        Code373(373),
        Code374(374),
        Code375(375),
        Code376(376),
        Code377(377),
        Code378(378),
        Code379(379),
        Code380(380),
        Code381(381),
        Code382(382),
        Code383(383),
        Code384(384),
        Code385(385),
        Code386(386),
        Code387(387),
        Code388(388),
        Code389(389),
        Code390(390),
        Code391(391),
        Code392(392),
        Code393(393),
        Code394(394),
        Code395(395),
        Code396(396),
        Code397(397),
        Code398(398),
        Code399(399),
        Code400(400),
        Code401(401),
        Code402(402),
        Code403(403),
        Code404(404),
        Code405(405),
        Code406(406),
        Code407(407),
        Code408(408),
        Code409(409),
        Code410(410),
        Code411(411),
        Code412(412),
        Code413(413),
        Code414(414),
        Code415(415),
        Code416(416),
        Code417(417),
        Code418(418),
        Code419(419),
        Code420(420),
        Code421(421),
        Code422(422),
        Code423(423),
        Code424(424),
        Code425(425),
        Code426(426),
        Code427(427),
        Code428(428),
        Code429(429),
        Code430(430),
        Code431(431),
        Code432(432),
        Code433(433),
        Code434(434),
        Code435(435),
        Code436(436),
        Code437(437),
        Code438(438),
        Code439(439),
        Code440(440),
        Code441(441),
        Code442(442),
        Code443(443),
        Code444(444),
        Code445(445),
        Code446(446),
        Code447(447),
        Code448(448),
        Code449(449),
        Code450(450),
        Code451(451),
        Code452(452),
        Code453(453),
        Code454(454),
        Code455(455),
        Code456(456),
        Code457(457),
        Code458(458),
        Code459(459),
        Code460(460),
        Code461(461),
        Code462(462),
        Code463(463),
        Code464(464),
        Code465(465),
        Code466(466),
        Code467(467),
        Code468(468),
        Code469(469),
        Code470(470),
        Code471(471),
        Code472(472),
        Code473(473),
        Code474(474),
        Code475(475),
        Code476(476),
        Code477(477),
        Code478(478),
        Code479(479),
        Code480(480),
        Code481(481),
        Code482(482),
        Code483(483),
        Code484(484),
        Code485(485),
        Code486(486),
        Code487(487),
        Code488(488),
        Code489(489),
        Code490(490),
        Code491(491),
        Code492(492),
        Code493(493),
        Code494(494),
        Code495(495),
        Code496(496),
        Code497(497),
        Code498(498),
        Code499(499),
        Code500(500),
        ;
        
        private static Map<Integer, TestEnum> codeMap = 
                CodeEnumUtils.getCodeMap(TestEnum.class);
        
        TestEnum(int code) {
            this.code = code;
        }
        
        private int code;
        
        public int getCode() {
            return code;
        }
        
        public static TestEnum getEnum(int code) {
            //return codeMap.get(code);
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
    
    int code = 1;
    
    @Test
    public void getEnumWithMethod() {
        System.out.println("getEnumWithMethod");
        long start = System.currentTimeMillis();
        for (int i=0; i<100000; i++)
        {
            TestEnum a = CodeEnumUtils.getEnumWithMethod(TestEnum.class, code);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
    
    @Test
    public void getEnumWithMap() {
        System.out.println("getEnumWithMap");
        Method method = CodeEnumUtils.findGetEnumMethod(MapEnum.class, CodeType.Code);
        long start = System.currentTimeMillis();
        for (int i=0; i<100000; i++)
        {
            MapEnum a = CodeEnumUtils.getEnumWithGetEnumMethod(MapEnum.class, method, code);
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
            TestEnum a = CodeEnumUtils.getEnumWithGetCodeMethod(TestEnum.class, getCodeMethod, code);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
    
    @Test
    public void getEnumWithReflection() {
        System.out.println("getEnumWithReflection");
        long start = System.currentTimeMillis();
        for (int i=0; i<100000; i++)
        {
            TestEnum a = CodeEnumUtils.getEnum(TestEnum.class, code);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
    
    
    @Test
    public void getEnum() {
        System.out.println("getEnum");
        long start = System.currentTimeMillis();
        for (int i=0; i<1000000; i++)
        {
            TestEnum a = TestEnum.getEnum(code);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
    
    @Test
    public void getEnumMap() {
        System.out.println("getEnumMap");
        long start = System.currentTimeMillis();
        for (int i=0; i<1000000; i++)
        {
            MapEnum a = MapEnum.getEnum(code);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
    
}
