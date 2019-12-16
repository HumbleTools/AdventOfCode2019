package fr.lma.advent.day6;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Advent_6 {

	public static void main(final String... args) {

		final Set<Orbiter> tempOrbiters = Arrays.stream(Const6.input_part1.split("[\\r\\n]+"))
				.map(Orbiter::new)
				.collect(Collectors.toSet());
		final Set<Orbiter> allOrbiters = new HashSet<>(tempOrbiters);
		final Orbiter origin = findOrigin(allOrbiters);
		tempOrbiters.remove(origin);
		buildMap(origin, tempOrbiters);
		System.out.println(calculateOrbitSum(allOrbiters));

		// TODO :
		// Add some Gherkins <3
		// create COM orbiter with null center
		// rework findOrigin and chainSizeFrom
		// code findSanta
		// code findYou(refactorFindSanta)
		// Get center of both
		// find path of both until first common ancestor
		// calculate number of jumps
		// is it required to perform the jumps ?
	}

	public static int calculateOrbitSum(final Set<Orbiter> orbiters) {
		return orbiters.stream()
				.map(Advent_6::chainSizeFrom)
				.mapToInt(Integer::intValue)
				.sum();
	}

	public static Integer chainSizeFrom(final Orbiter orbiter) {
		int size = 1;
		Orbiter currentOrbiter = orbiter;
		while (Objects.nonNull(currentOrbiter.getCenter())) {
			size++;
			currentOrbiter = currentOrbiter.getCenter();
		}
		return size;
	}

	private static void buildMap(final Orbiter center, final Set<Orbiter> orbiters) {
		center.getSatellites().addAll(orbiters.stream()
				.filter(o -> o.getCenterName().equals(center.getName()))
				.collect(Collectors.toSet()));
		orbiters.removeAll(center.getSatellites());
		for (final Orbiter orbiter : center.getSatellites()) {
			orbiter.setCenter(center);
			if (!orbiters.isEmpty()) {
				buildMap(orbiter, orbiters);
			}
		}
	}

	private static Orbiter findOrigin(final Set<Orbiter> orbiters) {
		return orbiters.stream().filter(o -> o.getCenterName().equals("COM")).findFirst().get();
	}

}

class Const6 {

	final static String input_test = "COM)B\r\n" +
			"B)C\r\n" +
			"C)D\r\n" +
			"D)E\r\n" +
			"E)F\r\n" +
			"B)G\r\n" +
			"G)H\r\n" +
			"D)I\r\n" +
			"E)J\r\n" +
			"J)K\r\n" +
			"K)L"; // Expecting 42.

	final static String input_part1 = "6CF)4J7\r\n" +
			"RC4)H87\r\n" +
			"LMS)RL4\r\n" +
			"KGS)1QW\r\n" +
			"H8N)KW5\r\n" +
			"JJM)YPS\r\n" +
			"P1W)ZKT\r\n" +
			"BSY)FMR\r\n" +
			"9T5)JK4\r\n" +
			"2X2)NQ5\r\n" +
			"H6R)FLY\r\n" +
			"8N9)H7L\r\n" +
			"5LR)TYT\r\n" +
			"VM7)V5V\r\n" +
			"GVF)Q8P\r\n" +
			"PQV)9NB\r\n" +
			"4W3)V84\r\n" +
			"H59)B3G\r\n" +
			"RGW)82C\r\n" +
			"BTQ)VRF\r\n" +
			"KQW)TNC\r\n" +
			"RT3)JHN\r\n" +
			"ZLY)15X\r\n" +
			"YJJ)55L\r\n" +
			"8Q2)6S7\r\n" +
			"963)PNL\r\n" +
			"8F3)WPH\r\n" +
			"H2Z)LMN\r\n" +
			"MFK)DMJ\r\n" +
			"JGN)H4X\r\n" +
			"P1C)Y9K\r\n" +
			"92D)8YS\r\n" +
			"2M2)PQ1\r\n" +
			"YY6)PRG\r\n" +
			"3J8)LBX\r\n" +
			"2WD)TK3\r\n" +
			"CNS)BJL\r\n" +
			"B53)JJ5\r\n" +
			"NND)CBM\r\n" +
			"FHS)RP5\r\n" +
			"RMS)6VH\r\n" +
			"FBB)QVC\r\n" +
			"SD2)WPN\r\n" +
			"BXL)76Q\r\n" +
			"V8H)LCW\r\n" +
			"MXC)2W2\r\n" +
			"CCX)KC9\r\n" +
			"JSQ)2T8\r\n" +
			"7HM)D24\r\n" +
			"XN9)N36\r\n" +
			"J3X)PRZ\r\n" +
			"6VQ)8ZV\r\n" +
			"QR4)MTL\r\n" +
			"KVT)V47\r\n" +
			"PXR)VFJ\r\n" +
			"VZN)P7W\r\n" +
			"WK6)72B\r\n" +
			"R46)J6N\r\n" +
			"5M4)LZB\r\n" +
			"4PL)V6R\r\n" +
			"WWR)BJ1\r\n" +
			"TPX)NRM\r\n" +
			"BS7)X7M\r\n" +
			"JJ5)8N2\r\n" +
			"V9N)D6W\r\n" +
			"7K2)2LZ\r\n" +
			"3JZ)FV1\r\n" +
			"2DJ)6N1\r\n" +
			"RC5)L5D\r\n" +
			"2RG)FK8\r\n" +
			"V8M)CL6\r\n" +
			"KLY)MBD\r\n" +
			"DMJ)KVD\r\n" +
			"6M6)6WX\r\n" +
			"HB1)1HD\r\n" +
			"KJW)G61\r\n" +
			"HZF)B1Q\r\n" +
			"2N4)73R\r\n" +
			"H8G)8XL\r\n" +
			"ZGK)RSY\r\n" +
			"35Y)FSR\r\n" +
			"H34)GLB\r\n" +
			"9MV)WR9\r\n" +
			"NBS)RV6\r\n" +
			"6QM)KLM\r\n" +
			"HN3)K2T\r\n" +
			"LCB)GNZ\r\n" +
			"LDJ)D3T\r\n" +
			"5PC)512\r\n" +
			"BXH)6MZ\r\n" +
			"XS7)W5B\r\n" +
			"LF3)6LJ\r\n" +
			"3Z8)ZTJ\r\n" +
			"3NT)9D9\r\n" +
			"65F)JVH\r\n" +
			"V6V)C9B\r\n" +
			"KTQ)KG8\r\n" +
			"6WP)92D\r\n" +
			"X4M)WGG\r\n" +
			"66R)B53\r\n" +
			"B5C)2MP\r\n" +
			"FC4)6T3\r\n" +
			"WWR)VRD\r\n" +
			"9DG)P94\r\n" +
			"XFV)TQQ\r\n" +
			"4LK)V21\r\n" +
			"2H8)HVS\r\n" +
			"JHN)QHZ\r\n" +
			"2GV)VSL\r\n" +
			"581)XRN\r\n" +
			"THC)ZGK\r\n" +
			"CB8)R2T\r\n" +
			"FN9)4Y9\r\n" +
			"1KH)RHQ\r\n" +
			"YF2)FNW\r\n" +
			"ZBW)JLW\r\n" +
			"YCK)R9D\r\n" +
			"2SH)X2M\r\n" +
			"512)W7P\r\n" +
			"TDK)FN9\r\n" +
			"TH8)HQZ\r\n" +
			"JK4)P62\r\n" +
			"BLG)CWW\r\n" +
			"V84)VYC\r\n" +
			"BTY)NXZ\r\n" +
			"8GC)DX1\r\n" +
			"CN5)YPZ\r\n" +
			"Y6J)CQP\r\n" +
			"L1X)NXF\r\n" +
			"SQ7)3QL\r\n" +
			"8KD)PS4\r\n" +
			"1SW)RHW\r\n" +
			"7X4)S86\r\n" +
			"RGS)581\r\n" +
			"2BC)DVS\r\n" +
			"6J8)XXQ\r\n" +
			"Q3S)TKF\r\n" +
			"T2K)TRL\r\n" +
			"NY4)16L\r\n" +
			"96Z)NMX\r\n" +
			"J1W)YRJ\r\n" +
			"8C3)29M\r\n" +
			"VRF)VX2\r\n" +
			"LD6)Y6M\r\n" +
			"RHX)FN3\r\n" +
			"J6N)LMY\r\n" +
			"BSB)JXV\r\n" +
			"PN4)L1X\r\n" +
			"M92)JPT\r\n" +
			"TBX)324\r\n" +
			"8RN)QKN\r\n" +
			"GNZ)J2D\r\n" +
			"GZ1)WJ4\r\n" +
			"783)96Z\r\n" +
			"SLD)L3C\r\n" +
			"9FK)V9L\r\n" +
			"D3T)PM2\r\n" +
			"1JL)MKH\r\n" +
			"CKL)PYR\r\n" +
			"SZD)YF2\r\n" +
			"8LD)4GQ\r\n" +
			"MQ2)JCP\r\n" +
			"CTW)GZ1\r\n" +
			"M7W)8NR\r\n" +
			"FWH)GBQ\r\n" +
			"1CX)BTC\r\n" +
			"YJL)RGX\r\n" +
			"FLY)ZDD\r\n" +
			"NCJ)8GC\r\n" +
			"PM2)2YG\r\n" +
			"T82)KM8\r\n" +
			"4M3)Q71\r\n" +
			"LGQ)JYC\r\n" +
			"K4Y)287\r\n" +
			"J48)THC\r\n" +
			"CNV)NCJ\r\n" +
			"9P7)SZY\r\n" +
			"QXP)XM5\r\n" +
			"S5Z)BSY\r\n" +
			"59Q)SLL\r\n" +
			"S79)16C\r\n" +
			"W66)JL4\r\n" +
			"L6Q)CZ2\r\n" +
			"7CB)7HP\r\n" +
			"DQ4)CLP\r\n" +
			"PQ1)FCR\r\n" +
			"5QP)163\r\n" +
			"RJB)H3J\r\n" +
			"CH5)25F\r\n" +
			"TQG)VLN\r\n" +
			"SDT)J67\r\n" +
			"1VL)X2B\r\n" +
			"5SM)G71\r\n" +
			"RSY)1J4\r\n" +
			"CZP)XNT\r\n" +
			"57T)37Z\r\n" +
			"1J4)3B2\r\n" +
			"7X4)WNS\r\n" +
			"BNT)NND\r\n" +
			"QMS)DVH\r\n" +
			"VW6)6QM\r\n" +
			"VFJ)4XH\r\n" +
			"17T)NLM\r\n" +
			"N8T)7HM\r\n" +
			"QPS)7LM\r\n" +
			"XXQ)2KV\r\n" +
			"WBS)8RF\r\n" +
			"95F)3Z8\r\n" +
			"NR1)WQM\r\n" +
			"S75)WNT\r\n" +
			"ZJ5)7WS\r\n" +
			"YNF)RDQ\r\n" +
			"2DY)XBV\r\n" +
			"7LM)V48\r\n" +
			"C78)64H\r\n" +
			"VHR)L2P\r\n" +
			"V47)9QN\r\n" +
			"9C5)Y5S\r\n" +
			"9D9)QDT\r\n" +
			"7DC)2DJ\r\n" +
			"H8P)H6P\r\n" +
			"QH4)X4N\r\n" +
			"K19)CYL\r\n" +
			"CXY)CMV\r\n" +
			"G8D)FDN\r\n" +
			"4Z3)LVF\r\n" +
			"V1Z)YHN\r\n" +
			"GM3)B2Q\r\n" +
			"LVF)VZH\r\n" +
			"2LZ)YOU\r\n" +
			"FNW)1R5\r\n" +
			"36J)2QT\r\n" +
			"2Y4)69H\r\n" +
			"LTZ)JGN\r\n" +
			"GTN)MZ3\r\n" +
			"FQX)WZP\r\n" +
			"3M8)P7L\r\n" +
			"16L)H78\r\n" +
			"9B1)NTB\r\n" +
			"PB7)86H\r\n" +
			"Z3P)T9T\r\n" +
			"M1X)KK5\r\n" +
			"6PS)5FQ\r\n" +
			"55L)2WZ\r\n" +
			"174)6GC\r\n" +
			"86H)2W7\r\n" +
			"WXH)R15\r\n" +
			"3B2)3Q6\r\n" +
			"BGQ)SPZ\r\n" +
			"1QW)NJT\r\n" +
			"WCK)5R8\r\n" +
			"ZKT)MTH\r\n" +
			"Z7S)67J\r\n" +
			"FK8)5J4\r\n" +
			"WJM)H83\r\n" +
			"4Y9)Z9B\r\n" +
			"LKC)3MW\r\n" +
			"6FM)MC3\r\n" +
			"163)68L\r\n" +
			"XJS)2PD\r\n" +
			"NVB)C8Q\r\n" +
			"WTV)S32\r\n" +
			"V9L)SVG\r\n" +
			"5HW)7LG\r\n" +
			"BTC)DX6\r\n" +
			"TKF)2DX\r\n" +
			"JJK)NPF\r\n" +
			"LQN)DSV\r\n" +
			"21B)JTT\r\n" +
			"9H8)ZY4\r\n" +
			"72B)W1Y\r\n" +
			"MF8)M3Q\r\n" +
			"6WF)SQ3\r\n" +
			"N34)4HL\r\n" +
			"1KB)32T\r\n" +
			"VYP)H6F\r\n" +
			"TTC)XHP\r\n" +
			"QM4)H2Z\r\n" +
			"NRM)7FK\r\n" +
			"ZGK)V13\r\n" +
			"XRN)361\r\n" +
			"324)ZMX\r\n" +
			"1PY)PH7\r\n" +
			"PDS)VLX\r\n" +
			"M88)XD4\r\n" +
			"1DZ)VF8\r\n" +
			"YL2)J9J\r\n" +
			"6GB)NR1\r\n" +
			"VX2)JJM\r\n" +
			"X4N)FN1\r\n" +
			"KK5)MZX\r\n" +
			"7ZT)GLZ\r\n" +
			"JDG)GGM\r\n" +
			"Q4N)8RN\r\n" +
			"JYC)N5Z\r\n" +
			"MTL)SPD\r\n" +
			"1KQ)135\r\n" +
			"9JX)FHS\r\n" +
			"NSF)PYT\r\n" +
			"QWF)QX9\r\n" +
			"FY1)48P\r\n" +
			"V4W)GXX\r\n" +
			"L5D)8KQ\r\n" +
			"P1C)F46\r\n" +
			"5R8)M1X\r\n" +
			"NSM)SV5\r\n" +
			"366)CXY\r\n" +
			"D24)Q83\r\n" +
			"ZKG)SD2\r\n" +
			"QKN)MC4\r\n" +
			"P4P)RK6\r\n" +
			"9QN)K4Y\r\n" +
			"38D)TJH\r\n" +
			"RG6)SZD\r\n" +
			"JL4)GQQ\r\n" +
			"Y6M)3NT\r\n" +
			"FR7)C3Q\r\n" +
			"DNH)7YW\r\n" +
			"SDL)M5X\r\n" +
			"JFT)YY6\r\n" +
			"5D9)KVT\r\n" +
			"JBL)GM3\r\n" +
			"C4V)FYC\r\n" +
			"KW5)Y7D\r\n" +
			"V76)VSH\r\n" +
			"CMV)ZKY\r\n" +
			"TK3)5KN\r\n" +
			"KDS)X34\r\n" +
			"NMY)QYZ\r\n" +
			"MDP)GVL\r\n" +
			"QSS)CY8\r\n" +
			"QFX)ZN1\r\n" +
			"Q54)VJP\r\n" +
			"VSH)3DK\r\n" +
			"ZY4)B19\r\n" +
			"X6Y)15G\r\n" +
			"L3C)78D\r\n" +
			"LDV)SJT\r\n" +
			"8M7)6VQ\r\n" +
			"22Z)BTQ\r\n" +
			"RHQ)NFK\r\n" +
			"9NT)DQ4\r\n" +
			"C9B)WB9\r\n" +
			"HGD)QXP\r\n" +
			"YDX)YMZ\r\n" +
			"VMQ)8C3\r\n" +
			"9RM)QTY\r\n" +
			"GQQ)9C1\r\n" +
			"LSG)KJW\r\n" +
			"6MS)GVF\r\n" +
			"ZTL)LLK\r\n" +
			"S86)W19\r\n" +
			"1FV)LGQ\r\n" +
			"C8Q)P4P\r\n" +
			"DMP)Y1X\r\n" +
			"48P)LGX\r\n" +
			"MZ3)9B1\r\n" +
			"LKH)2WL\r\n" +
			"NZJ)TNJ\r\n" +
			"F67)L34\r\n" +
			"9Y3)783\r\n" +
			"ZNJ)6WF\r\n" +
			"WX6)ZSC\r\n" +
			"15G)RTW\r\n" +
			"QHP)9YN\r\n" +
			"DQH)KY6\r\n" +
			"4LF)LY8\r\n" +
			"XHR)GK1\r\n" +
			"3MW)SRY\r\n" +
			"Q8N)RPX\r\n" +
			"R9X)SZL\r\n" +
			"V85)FR7\r\n" +
			"MVW)QLB\r\n" +
			"1DY)H29\r\n" +
			"43D)NY4\r\n" +
			"MKH)SBX\r\n" +
			"5FQ)ZT8\r\n" +
			"P97)2JM\r\n" +
			"DSV)RGW\r\n" +
			"37Z)5SJ\r\n" +
			"3WG)KTQ\r\n" +
			"Z9G)2N4\r\n" +
			"K7Q)HQV\r\n" +
			"BX1)2H8\r\n" +
			"7WS)G27\r\n" +
			"WHN)2DL\r\n" +
			"YHN)9YB\r\n" +
			"1JN)BGQ\r\n" +
			"PYP)VQ8\r\n" +
			"VXC)917\r\n" +
			"13Z)XTZ\r\n" +
			"9WT)N8Q\r\n" +
			"VVH)MP7\r\n" +
			"WDM)RC5\r\n" +
			"G8T)WHN\r\n" +
			"VYC)MR6\r\n" +
			"XM5)FWH\r\n" +
			"N8Q)V77\r\n" +
			"2YM)HH9\r\n" +
			"L4J)8G9\r\n" +
			"1TG)S6H\r\n" +
			"XCC)TD8\r\n" +
			"KRF)4KZ\r\n" +
			"19N)9FK\r\n" +
			"SMS)V1K\r\n" +
			"8N2)MZS\r\n" +
			"869)8F8\r\n" +
			"JGD)PYW\r\n" +
			"76V)CLW\r\n" +
			"NDZ)VM7\r\n" +
			"VLX)6WD\r\n" +
			"895)38D\r\n" +
			"1XZ)Q34\r\n" +
			"VF8)1F6\r\n" +
			"TNC)7DC\r\n" +
			"16C)MWQ\r\n" +
			"LDJ)C4L\r\n" +
			"ZV6)7XJ\r\n" +
			"NXZ)H34\r\n" +
			"BLS)4MM\r\n" +
			"GG6)NKG\r\n" +
			"BCD)KT9\r\n" +
			"PYW)LSG\r\n" +
			"LNP)7XQ\r\n" +
			"MZX)BY9\r\n" +
			"RGX)J8P\r\n" +
			"S32)DSS\r\n" +
			"J9J)LTZ\r\n" +
			"39P)S75\r\n" +
			"CNS)PDZ\r\n" +
			"PYW)CHG\r\n" +
			"Z19)V8Q\r\n" +
			"68L)9WR\r\n" +
			"V6R)MVW\r\n" +
			"LLK)MYP\r\n" +
			"9MV)MQT\r\n" +
			"1FH)4CK\r\n" +
			"P4N)MMM\r\n" +
			"4TD)96J\r\n" +
			"V77)CH4\r\n" +
			"MMW)CTW\r\n" +
			"MLN)3CS\r\n" +
			"W8G)2JS\r\n" +
			"4KZ)HBT\r\n" +
			"5PM)6PP\r\n" +
			"C81)JSQ\r\n" +
			"V11)WK6\r\n" +
			"FCR)MVH\r\n" +
			"YR2)KLY\r\n" +
			"W1H)XTX\r\n" +
			"SKL)NQG\r\n" +
			"2YM)VL7\r\n" +
			"M8K)6HN\r\n" +
			"1K6)JX8\r\n" +
			"ZDD)H1W\r\n" +
			"4JW)NV8\r\n" +
			"GS2)CMP\r\n" +
			"RDM)7XB\r\n" +
			"3QB)GTN\r\n" +
			"D6W)MJT\r\n" +
			"NRK)6PS\r\n" +
			"7WS)B1L\r\n" +
			"PKH)L4J\r\n" +
			"G61)WTV\r\n" +
			"2T8)8Q2\r\n" +
			"TL6)W7Z\r\n" +
			"1TC)KXM\r\n" +
			"1VL)DZD\r\n" +
			"LVZ)1DZ\r\n" +
			"1TQ)S3R\r\n" +
			"7BY)ZB6\r\n" +
			"JPT)1JN\r\n" +
			"LF3)K91\r\n" +
			"37D)LKM\r\n" +
			"9LN)VZN\r\n" +
			"MQT)Q1F\r\n" +
			"M6X)GTS\r\n" +
			"CNH)1WS\r\n" +
			"6GC)BSM\r\n" +
			"1F6)88V\r\n" +
			"FKP)XQZ\r\n" +
			"QX9)5SM\r\n" +
			"1HD)VP7\r\n" +
			"MMQ)RT3\r\n" +
			"QXX)9P7\r\n" +
			"GLT)SQ7\r\n" +
			"HQP)TLR\r\n" +
			"K2T)P6X\r\n" +
			"TTZ)Z7S\r\n" +
			"D49)3NV\r\n" +
			"DFD)V29\r\n" +
			"LPW)877\r\n" +
			"ZKT)BXL\r\n" +
			"YHN)TKD\r\n" +
			"FV1)9Y3\r\n" +
			"3ZB)57Z\r\n" +
			"WN1)35Y\r\n" +
			"SQ3)ZCF\r\n" +
			"DJF)ZNJ\r\n" +
			"R61)LCB\r\n" +
			"BMX)YFT\r\n" +
			"W5B)3VM\r\n" +
			"46C)174\r\n" +
			"BJ1)2XP\r\n" +
			"XZJ)KN9\r\n" +
			"2D7)N8T\r\n" +
			"TTF)KDS\r\n" +
			"1T5)8M7\r\n" +
			"Y5Q)SKL\r\n" +
			"6C8)WHW\r\n" +
			"QBH)6PZ\r\n" +
			"6RD)7BY\r\n" +
			"Z2Y)1KB\r\n" +
			"J3Q)X6Y\r\n" +
			"PBR)2D7\r\n" +
			"JLW)2CY\r\n" +
			"RP5)54P\r\n" +
			"PYT)9HC\r\n" +
			"N5M)FY1\r\n" +
			"MWG)N2M\r\n" +
			"V1K)M7W\r\n" +
			"55L)869\r\n" +
			"M3Q)Q8N\r\n" +
			"FSR)ZV6\r\n" +
			"MX8)RQF\r\n" +
			"LKM)SAN\r\n" +
			"KKP)WDM\r\n" +
			"FN1)1DW\r\n" +
			"WB9)VDR\r\n" +
			"G98)RMS\r\n" +
			"H7L)CCN\r\n" +
			"TL3)LJF\r\n" +
			"YNZ)K8V\r\n" +
			"KL2)66B\r\n" +
			"M28)P9Z\r\n" +
			"Q34)Y28\r\n" +
			"TNJ)NSM\r\n" +
			"W25)R9X\r\n" +
			"8XL)GS2\r\n" +
			"ZB6)26Z\r\n" +
			"BYR)5QP\r\n" +
			"MVH)BNW\r\n" +
			"TKD)P1C\r\n" +
			"9JW)WX6\r\n" +
			"8LC)J3Q\r\n" +
			"H8F)Y14\r\n" +
			"347)7SJ\r\n" +
			"8VL)RC4\r\n" +
			"38T)1FV\r\n" +
			"9FM)M39\r\n" +
			"CH4)LVQ\r\n" +
			"LNV)QPS\r\n" +
			"H87)FPL\r\n" +
			"9YN)LKH\r\n" +
			"9HB)PBR\r\n" +
			"9WR)NWL\r\n" +
			"X7M)BYR\r\n" +
			"3GM)N7X\r\n" +
			"67J)RJB\r\n" +
			"2XV)LNV\r\n" +
			"3VX)WWR\r\n" +
			"1R5)VMD\r\n" +
			"9HC)BTY\r\n" +
			"GVL)CVB\r\n" +
			"D8J)5LR\r\n" +
			"M39)VW6\r\n" +
			"NPF)MFK\r\n" +
			"7NQ)6M9\r\n" +
			"3DK)P76\r\n" +
			"ZQG)J1W\r\n" +
			"JCP)TBX\r\n" +
			"DX1)9DG\r\n" +
			"917)LDV\r\n" +
			"CBT)M9J\r\n" +
			"G71)H8P\r\n" +
			"4J7)FZJ\r\n" +
			"RGX)CB8\r\n" +
			"63W)P1W\r\n" +
			"V8Q)2DY\r\n" +
			"H42)YGG\r\n" +
			"RY7)1V6\r\n" +
			"W7Z)CYN\r\n" +
			"BGL)JJ8\r\n" +
			"TLR)X2S\r\n" +
			"X4H)2SB\r\n" +
			"MC4)QR4\r\n" +
			"Q8P)CCX\r\n" +
			"P8R)43D\r\n" +
			"J3R)VS6\r\n" +
			"TPK)9H8\r\n" +
			"D17)NMY\r\n" +
			"G27)9S9\r\n" +
			"1SV)RWF\r\n" +
			"MQ2)B47\r\n" +
			"L2P)VMF\r\n" +
			"V13)Y5Q\r\n" +
			"L5B)YJJ\r\n" +
			"WNT)MZF\r\n" +
			"B47)WN1\r\n" +
			"KN9)9VX\r\n" +
			"LFJ)GPB\r\n" +
			"F82)855\r\n" +
			"BD5)MLN\r\n" +
			"SBX)CBT\r\n" +
			"7XQ)H65\r\n" +
			"VJP)6WB\r\n" +
			"PH7)WKG\r\n" +
			"LBF)1SW\r\n" +
			"2YG)FSB\r\n" +
			"7LM)8DN\r\n" +
			"913)5D9\r\n" +
			"VLN)BLG\r\n" +
			"FM6)5PM\r\n" +
			"5W2)7W8\r\n" +
			"BY9)6M6\r\n" +
			"ZN1)FP5\r\n" +
			"2WZ)BSB\r\n" +
			"D24)4LK\r\n" +
			"8NR)R8W\r\n" +
			"414)QSS\r\n" +
			"ZMX)4TD\r\n" +
			"3VM)ZLY\r\n" +
			"HT3)LDJ\r\n" +
			"XDR)CJ6\r\n" +
			"R2T)22Z\r\n" +
			"L34)RY7\r\n" +
			"7FK)17T\r\n" +
			"X2S)TTZ\r\n" +
			"714)9C5\r\n" +
			"BNR)GYJ\r\n" +
			"RQF)KQW\r\n" +
			"3F9)GWN\r\n" +
			"FL6)DNH\r\n" +
			"6WB)YL4\r\n" +
			"7MT)V1Z\r\n" +
			"Q1F)LBY\r\n" +
			"PS4)Q54\r\n" +
			"82C)MDP\r\n" +
			"77H)MGJ\r\n" +
			"CVB)DXB\r\n" +
			"HGD)FBB\r\n" +
			"ZTJ)XH9\r\n" +
			"FXS)JP8\r\n" +
			"KB9)WYZ\r\n" +
			"C4L)XG8\r\n" +
			"C3Q)YR2\r\n" +
			"96J)F8M\r\n" +
			"W53)6C8\r\n" +
			"LZB)L6Q\r\n" +
			"Z78)62Y\r\n" +
			"361)G8D\r\n" +
			"FY1)RDM\r\n" +
			"S5Z)V9N\r\n" +
			"23W)PN4\r\n" +
			"SRX)Z3P\r\n" +
			"PRZ)PXR\r\n" +
			"7NF)R58\r\n" +
			"QTY)WY4\r\n" +
			"897)V3S\r\n" +
			"GVF)ZV3\r\n" +
			"PTP)21B\r\n" +
			"37G)H59\r\n" +
			"Y3R)LVZ\r\n" +
			"CWH)C81\r\n" +
			"W9M)GXC\r\n" +
			"V29)CKL\r\n" +
			"NXR)91Q\r\n" +
			"Y84)6RD\r\n" +
			"G2K)2JD\r\n" +
			"MR6)1CX\r\n" +
			"QDT)F67\r\n" +
			"ZM3)1BY\r\n" +
			"XHP)H8F\r\n" +
			"WVL)9JX\r\n" +
			"NRM)Y4R\r\n" +
			"CY8)CHJ\r\n" +
			"H6P)XJS\r\n" +
			"96Z)MT8\r\n" +
			"P6X)4M3\r\n" +
			"XQZ)SC7\r\n" +
			"J67)3J8\r\n" +
			"GCR)V85\r\n" +
			"H65)ZM3\r\n" +
			"K4Y)W74\r\n" +
			"WPN)6NJ\r\n" +
			"F46)LG7\r\n" +
			"H78)NXR\r\n" +
			"LBY)G98\r\n" +
			"FZJ)63T\r\n" +
			"21B)CT7\r\n" +
			"7G9)414\r\n" +
			"2D8)G7Z\r\n" +
			"QLB)VTR\r\n" +
			"1V6)VGH\r\n" +
			"R15)LQP\r\n" +
			"2QT)Y3R\r\n" +
			"Q83)7YN\r\n" +
			"BXL)NV4\r\n" +
			"J8P)JDG\r\n" +
			"76Q)C4V\r\n" +
			"SZY)PTP\r\n" +
			"7SJ)7G9\r\n" +
			"C8R)H42\r\n" +
			"VW6)8LC\r\n" +
			"135)6MB\r\n" +
			"4XH)WJM\r\n" +
			"8KT)93S\r\n" +
			"G7Z)7NQ\r\n" +
			"9C1)GSW\r\n" +
			"TC8)J26\r\n" +
			"3F4)W8G\r\n" +
			"CZ2)RPJ\r\n" +
			"9WT)BCD\r\n" +
			"LY8)533\r\n" +
			"548)PKH\r\n" +
			"K2M)6MQ\r\n" +
			"2X1)HN1\r\n" +
			"LSY)R9G\r\n" +
			"PVN)X3T\r\n" +
			"H3J)L65\r\n" +
			"7XB)DT6\r\n" +
			"DVS)XN9\r\n" +
			"SGN)DS4\r\n" +
			"NYD)WHP\r\n" +
			"DWD)C3V\r\n" +
			"LM8)MGP\r\n" +
			"ZCF)VSC\r\n" +
			"PXN)ZPT\r\n" +
			"7ZT)CWH\r\n" +
			"DGH)7K2\r\n" +
			"VSL)4CC\r\n" +
			"FP5)Z9G\r\n" +
			"6F2)MWG\r\n" +
			"RV6)MNB\r\n" +
			"8TQ)GTB\r\n" +
			"YPZ)QL2\r\n" +
			"14G)SMS\r\n" +
			"VML)XHR\r\n" +
			"Y4R)D46\r\n" +
			"MNB)1K6\r\n" +
			"FNP)9H6\r\n" +
			"X1T)5JP\r\n" +
			"1DW)28V\r\n" +
			"76P)4MK\r\n" +
			"WW4)53M\r\n" +
			"WZP)B5C\r\n" +
			"DS4)1PZ\r\n" +
			"B1L)DQW\r\n" +
			"RHX)RGS\r\n" +
			"42C)CBV\r\n" +
			"HQV)9T5\r\n" +
			"YDV)DFD\r\n" +
			"KG8)Q7R\r\n" +
			"72W)BSZ\r\n" +
			"369)ZBW\r\n" +
			"9YB)77H\r\n" +
			"MD3)YT8\r\n" +
			"YGG)VYP\r\n" +
			"3QL)289\r\n" +
			"X2B)548\r\n" +
			"Y5S)R64\r\n" +
			"9MM)P8R\r\n" +
			"W19)382\r\n" +
			"RPJ)QQG\r\n" +
			"P9Z)BNT\r\n" +
			"DNB)CCP\r\n" +
			"6HN)DMP\r\n" +
			"MW5)SLD\r\n" +
			"WRS)HWD\r\n" +
			"ZKR)186\r\n" +
			"WQM)LQN\r\n" +
			"NL4)JBS\r\n" +
			"WHW)TTC\r\n" +
			"WJ4)1T5\r\n" +
			"R9G)FQX\r\n" +
			"2W2)8VY\r\n" +
			"P3H)V11\r\n" +
			"243)XCC\r\n" +
			"SJT)7NF\r\n" +
			"3Y4)9SS\r\n" +
			"MP7)4ZD\r\n" +
			"186)BD5\r\n" +
			"NZ3)913\r\n" +
			"KLM)MMQ\r\n" +
			"8G9)5GZ\r\n" +
			"G38)YDV\r\n" +
			"85T)MX8\r\n" +
			"V4B)9FM\r\n" +
			"8RQ)NQ7\r\n" +
			"LX6)BMX\r\n" +
			"2SB)9MV\r\n" +
			"8KD)5VJ\r\n" +
			"J67)PGY\r\n" +
			"SD2)FC4\r\n" +
			"GWN)R2R\r\n" +
			"VL7)3W9\r\n" +
			"CB5)1TQ\r\n" +
			"5Q9)BV2\r\n" +
			"M5X)PXN\r\n" +
			"HHT)3ZB\r\n" +
			"NV4)JYQ\r\n" +
			"5TY)XQ5\r\n" +
			"RG5)NL4\r\n" +
			"CLW)Q3S\r\n" +
			"NMX)K2M\r\n" +
			"VQY)T82\r\n" +
			"FN3)CCF\r\n" +
			"TQQ)KGT\r\n" +
			"6MZ)KRF\r\n" +
			"N36)49V\r\n" +
			"Z8B)LSY\r\n" +
			"69H)M88\r\n" +
			"DX6)Y84\r\n" +
			"HQ7)1KQ\r\n" +
			"F1V)D17\r\n" +
			"JQM)8M4\r\n" +
			"QNV)Z5Z\r\n" +
			"NQ5)YJL\r\n" +
			"GSW)DDZ\r\n" +
			"6WX)3S2\r\n" +
			"CNV)T5Z\r\n" +
			"2JS)366\r\n" +
			"5JP)VPV\r\n" +
			"6LJ)6GB\r\n" +
			"YFT)347\r\n" +
			"GTS)J1D\r\n" +
			"KC9)S38\r\n" +
			"LBX)H8G\r\n" +
			"4NY)2KP\r\n" +
			"WHW)2D8\r\n" +
			"W25)9M2\r\n" +
			"R58)D8J\r\n" +
			"D17)88Z\r\n" +
			"8T7)8ZZ\r\n" +
			"7YN)HY6\r\n" +
			"ZBW)WB3\r\n" +
			"ZSC)DZK\r\n" +
			"X2B)R9B\r\n" +
			"VP7)GLT\r\n" +
			"2Q1)ZJ5\r\n" +
			"ZV3)T68\r\n" +
			"MTH)39P\r\n" +
			"SNY)4LF\r\n" +
			"2WL)JBN\r\n" +
			"WPL)D49\r\n" +
			"CCF)VVH\r\n" +
			"HPS)9M9\r\n" +
			"FYV)PQV\r\n" +
			"9N8)VMQ\r\n" +
			"92F)1DY\r\n" +
			"RWF)ZGM\r\n" +
			"CYL)V6K\r\n" +
			"3Z5)2Q1\r\n" +
			"VCN)HGD\r\n" +
			"NV8)LD6\r\n" +
			"KKV)V8H\r\n" +
			"6PZ)KVK\r\n" +
			"5VJ)KKP\r\n" +
			"GDT)NX1\r\n" +
			"CWW)4JW\r\n" +
			"Y9K)7ZT\r\n" +
			"DB4)CH5\r\n" +
			"DXB)VXC\r\n" +
			"SPD)4NV\r\n" +
			"YNV)4W3\r\n" +
			"KGT)2M2\r\n" +
			"YRJ)KV6\r\n" +
			"X9Y)TDK\r\n" +
			"S3R)L5B\r\n" +
			"8F3)7MT\r\n" +
			"XWR)TM1\r\n" +
			"Y8W)PB7\r\n" +
			"PGC)SWT\r\n" +
			"Q71)2XH\r\n" +
			"Y1X)32Q\r\n" +
			"2PD)81V\r\n" +
			"FY4)X1T\r\n" +
			"J8M)W25\r\n" +
			"WPH)2C1\r\n" +
			"N5Z)DQH\r\n" +
			"ZKY)LM8\r\n" +
			"HWD)3W8\r\n" +
			"395)429\r\n" +
			"3Q4)6NL\r\n" +
			"H4X)W8Z\r\n" +
			"WB3)MQ2\r\n" +
			"5G2)JGD\r\n" +
			"F5F)W9M\r\n" +
			"88V)HT3\r\n" +
			"GGM)J2X\r\n" +
			"FKN)P8V\r\n" +
			"VMD)3F4\r\n" +
			"RHW)9RR\r\n" +
			"RMS)243\r\n" +
			"MT8)Z8B\r\n" +
			"8YP)WW4\r\n" +
			"VLX)F82\r\n" +
			"3S2)LKC\r\n" +
			"8YS)9N8\r\n" +
			"XTX)Y2V\r\n" +
			"VGH)4W2\r\n" +
			"Z5Z)5BT\r\n" +
			"GLZ)FL6\r\n" +
			"93S)CVW\r\n" +
			"J26)ZQG\r\n" +
			"JSX)CZP\r\n" +
			"9SS)WPL\r\n" +
			"L4J)YNZ\r\n" +
			"M9J)395\r\n" +
			"5HW)8RQ\r\n" +
			"7K2)JBL\r\n" +
			"SWT)7T3\r\n" +
			"57W)Y8W\r\n" +
			"V21)WCK\r\n" +
			"9M9)NDZ\r\n" +
			"KVD)FY4\r\n" +
			"8WJ)1KH\r\n" +
			"W7K)HN3\r\n" +
			"72N)M6X\r\n" +
			"C66)1VL\r\n" +
			"JJ8)4ZS\r\n" +
			"6M9)QT9\r\n" +
			"RK6)BKR\r\n" +
			"7HP)S3V\r\n" +
			"H6F)XDR\r\n" +
			"4ZS)XZJ\r\n" +
			"R8W)4G3\r\n" +
			"6NL)H72\r\n" +
			"BGL)JP1\r\n" +
			"V5V)5G2\r\n" +
			"9M2)13Z\r\n" +
			"W7P)M28\r\n" +
			"BNW)V6V\r\n" +
			"P94)RG5\r\n" +
			"5G2)78H\r\n" +
			"2C1)HB1\r\n" +
			"ZGM)7VK\r\n" +
			"CBM)6FM\r\n" +
			"5Y3)NR2\r\n" +
			"FV1)85T\r\n" +
			"JTT)H8N\r\n" +
			"6C8)SGN\r\n" +
			"Y9M)BXH\r\n" +
			"877)DTS\r\n" +
			"JP1)TL3\r\n" +
			"D46)C5Z\r\n" +
			"2XP)XY7\r\n" +
			"H72)3VX\r\n" +
			"26Z)95S\r\n" +
			"RMV)QBH\r\n" +
			"69H)ZRT\r\n" +
			"2KV)897\r\n" +
			"4MK)JF2\r\n" +
			"P7W)DGH\r\n" +
			"RDQ)LX6\r\n" +
			"2DL)MP8\r\n" +
			"1PZ)GDT\r\n" +
			"ZTV)NVQ\r\n" +
			"KVV)NZC\r\n" +
			"22Z)9NT\r\n" +
			"B4W)S5Z\r\n" +
			"6MB)52R\r\n" +
			"B19)V8M\r\n" +
			"VRD)SDT\r\n" +
			"8ZZ)8Z4\r\n" +
			"WKG)9HB\r\n" +
			"R2R)MD3\r\n" +
			"VTP)CB5\r\n" +
			"T5Z)J3Y\r\n" +
			"KVK)QCV\r\n" +
			"DTS)23W\r\n" +
			"PCP)2X2\r\n" +
			"HN1)8KT\r\n" +
			"LGX)KKV\r\n" +
			"XG8)JFT\r\n" +
			"H1W)KZT\r\n" +
			"B1N)3Q4\r\n" +
			"NQ7)9JW\r\n" +
			"FWH)TCR\r\n" +
			"JBR)SSB\r\n" +
			"SX6)R3L\r\n" +
			"QCV)CNV\r\n" +
			"H29)QFX\r\n" +
			"TM1)2YM\r\n" +
			"ZZS)HZF\r\n" +
			"9VX)PYP\r\n" +
			"Y7D)V4B\r\n" +
			"TD8)J3R\r\n" +
			"82P)WXH\r\n" +
			"855)P2R\r\n" +
			"3W8)92F\r\n" +
			"BQZ)8WJ\r\n" +
			"VTR)JQM\r\n" +
			"NR2)5TY\r\n" +
			"L65)N34\r\n" +
			"64H)YL2\r\n" +
			"1BY)TPK\r\n" +
			"Y14)XS7\r\n" +
			"4KM)2BC\r\n" +
			"5H1)MCJ\r\n" +
			"T68)LBF\r\n" +
			"H83)SX6\r\n" +
			"HT6)C78\r\n" +
			"7W8)TFL\r\n" +
			"32T)QXX\r\n" +
			"TFL)FKP\r\n" +
			"CT7)5HW\r\n" +
			"QXP)NZ3\r\n" +
			"8C3)6J8\r\n" +
			"5GZ)FBY\r\n" +
			"V6K)9DJ\r\n" +
			"NWL)5W2\r\n" +
			"W74)ZTV\r\n" +
			"429)369\r\n" +
			"WYZ)Q7P\r\n" +
			"LJF)F5F\r\n" +
			"NVQ)S79\r\n" +
			"X7P)1TG\r\n" +
			"4CK)7X4\r\n" +
			"N9H)4NY\r\n" +
			"15X)H8Y\r\n" +
			"BV2)95F\r\n" +
			"WK6)9RM\r\n" +
			"JF2)46C\r\n" +
			"DNH)RZQ\r\n" +
			"LMN)GCR\r\n" +
			"CGB)YDQ\r\n" +
			"NXF)5H1\r\n" +
			"8RF)TVM\r\n" +
			"3VT)LFJ\r\n" +
			"LMY)2RG\r\n" +
			"7HM)C8R\r\n" +
			"GLB)MF8\r\n" +
			"5NR)QMS\r\n" +
			"CLP)V76\r\n" +
			"2W7)NYD\r\n" +
			"4W2)4PL\r\n" +
			"JBN)GPF\r\n" +
			"J3Y)XWR\r\n" +
			"C5Z)MW5\r\n" +
			"SPZ)65F\r\n" +
			"PWT)LNP\r\n" +
			"X2M)5Q9\r\n" +
			"CH5)RMV\r\n" +
			"FBY)ZZK\r\n" +
			"8Z4)R46\r\n" +
			"XH9)HT6\r\n" +
			"FDN)76V\r\n" +
			"NLM)HCM\r\n" +
			"MP8)YDX\r\n" +
			"R9B)SBW\r\n" +
			"1TQ)963\r\n" +
			"H8Y)CGB\r\n" +
			"5J4)FM6\r\n" +
			"9NB)3VT\r\n" +
			"XTZ)3Y4\r\n" +
			"CCN)714\r\n" +
			"TCR)SRX\r\n" +
			"3W9)WVL\r\n" +
			"2DL)JBR\r\n" +
			"XBV)PBL\r\n" +
			"S38)B8B\r\n" +
			"SZT)Z78\r\n" +
			"GK1)CZC\r\n" +
			"B3D)QHP\r\n" +
			"ZT8)DNB\r\n" +
			"TDK)KVV\r\n" +
			"HBT)MXC\r\n" +
			"VPV)ZTL\r\n" +
			"Q7P)NPD\r\n" +
			"QYZ)6WP\r\n" +
			"2KP)KGS\r\n" +
			"LQP)XFQ\r\n" +
			"LSG)8TQ\r\n" +
			"P62)72W\r\n" +
			"CPM)57W\r\n" +
			"2XH)XLN\r\n" +
			"R3L)DNY\r\n" +
			"TPX)X4M\r\n" +
			"KM8)64L\r\n" +
			"ZV6)82P\r\n" +
			"4NV)8N9\r\n" +
			"JP8)FYV\r\n" +
			"F8M)38T\r\n" +
			"64L)NZJ\r\n" +
			"QCV)VML\r\n" +
			"SBW)QNV\r\n" +
			"YDQ)2WD\r\n" +
			"T9F)GZ3\r\n" +
			"Z9B)8YP\r\n" +
			"KY6)2X1\r\n" +
			"TJH)LMS\r\n" +
			"YL4)W53\r\n" +
			"3CS)1JL\r\n" +
			"CVW)9LN\r\n" +
			"7DX)VTP\r\n" +
			"53M)W1H\r\n" +
			"NTB)V4W\r\n" +
			"PCP)TH8\r\n" +
			"SLL)ZZS\r\n" +
			"MJT)42C\r\n" +
			"62Y)DWD\r\n" +
			"WGC)7CB\r\n" +
			"XQ5)LMM\r\n" +
			"NKG)P97\r\n" +
			"NRK)F1V\r\n" +
			"BJL)4Z3\r\n" +
			"7YW)6MS\r\n" +
			"WHP)6F2\r\n" +
			"N7X)ZKG\r\n" +
			"WY4)JJK\r\n" +
			"NZC)G38\r\n" +
			"GBQ)BGL\r\n" +
			"8DN)3JZ\r\n" +
			"641)72N\r\n" +
			"XD4)B1N\r\n" +
			"WNT)6CF\r\n" +
			"92F)HPS\r\n" +
			"GYJ)HQP\r\n" +
			"P1W)Q17\r\n" +
			"R9D)5PC\r\n" +
			"PDZ)RHX\r\n" +
			"2CY)Z19\r\n" +
			"W1Y)5M4\r\n" +
			"32Q)2SH\r\n" +
			"BSM)NRK\r\n" +
			"QHZ)9G3\r\n" +
			"MBD)36J\r\n" +
			"TT4)NSF\r\n" +
			"PRG)VQY\r\n" +
			"4GQ)QH4\r\n" +
			"4MM)3WG\r\n" +
			"382)HC7\r\n" +
			"V48)8T7\r\n" +
			"CHG)J8M\r\n" +
			"N2M)NBS\r\n" +
			"2JD)PDS\r\n" +
			"SVG)5Y3\r\n" +
			"T9T)QFT\r\n" +
			"52R)T9F\r\n" +
			"9T5)2XV\r\n" +
			"8ZY)1SV\r\n" +
			"RL4)VHR\r\n" +
			"S6H)1TC\r\n" +
			"MGJ)TPX\r\n" +
			"WR9)SQG\r\n" +
			"6N1)XFV\r\n" +
			"DNY)X7P\r\n" +
			"TYT)HHT\r\n" +
			"XFQ)2Y4\r\n" +
			"KZT)X9Y\r\n" +
			"J1D)4KM\r\n" +
			"Y2V)W66\r\n" +
			"NC5)N9H\r\n" +
			"MGP)2GV\r\n" +
			"88Z)37G\r\n" +
			"LCW)S7P\r\n" +
			"XJL)Q4G\r\n" +
			"9G3)KL2\r\n" +
			"DZK)CNS\r\n" +
			"NJT)BS7\r\n" +
			"SC7)K19\r\n" +
			"HVS)SZT\r\n" +
			"P7L)14G\r\n" +
			"NTB)YNV\r\n" +
			"1NH)8LD\r\n" +
			"WNS)8VL\r\n" +
			"Q17)N5M\r\n" +
			"MQT)LPW\r\n" +
			"PGY)1FH\r\n" +
			"78H)M92\r\n" +
			"8ZV)BLS\r\n" +
			"FSB)BX1\r\n" +
			"9FM)9MM\r\n" +
			"FPL)GG6\r\n" +
			"29M)9NY\r\n" +
			"P76)8SN\r\n" +
			"COM)PWT\r\n" +
			"9DJ)8F3\r\n" +
			"LVQ)Y9M\r\n" +
			"C2R)SDL\r\n" +
			"6NJ)WGC\r\n" +
			"4VX)RYG\r\n" +
			"8M7)63W\r\n" +
			"MZF)59Q\r\n" +
			"61G)895\r\n" +
			"25F)W7K\r\n" +
			"28V)9WT\r\n" +
			"ZZK)T2K\r\n" +
			"L6Q)19N\r\n" +
			"NFK)P3H\r\n" +
			"B3G)CN5\r\n" +
			"P2R)1XZ\r\n" +
			"NQG)95L\r\n" +
			"HY6)1NH\r\n" +
			"BSZ)CNH\r\n" +
			"4CC)H6R\r\n" +
			"JX8)8KD\r\n" +
			"91Q)YCK\r\n" +
			"5SJ)B3D\r\n" +
			"KV6)57T\r\n" +
			"JXV)4VX\r\n" +
			"VS6)KB9\r\n" +
			"NQG)QWF\r\n" +
			"FMR)RG6\r\n" +
			"DDZ)MMW\r\n" +
			"B2Q)X4H\r\n" +
			"YMZ)PCP\r\n" +
			"MMM)BNR\r\n" +
			"SZL)36D\r\n" +
			"289)G2K\r\n" +
			"YT8)TT4\r\n" +
			"9HC)TTF\r\n" +
			"QVC)TC8\r\n" +
			"VMF)FNP\r\n" +
			"CL6)Q4N\r\n" +
			"95L)SNY\r\n" +
			"Q4G)R61\r\n" +
			"8M4)B57\r\n" +
			"JYQ)C2R\r\n" +
			"VDR)3F9\r\n" +
			"4G3)C66\r\n" +
			"R64)BQZ\r\n" +
			"X34)QF1\r\n" +
			"DQW)XJL\r\n" +
			"GPB)QM4\r\n" +
			"C3V)CPM\r\n" +
			"4J7)66R\r\n" +
			"95S)37D\r\n" +
			"B57)ZKR\r\n" +
			"78D)61G\r\n" +
			"CYN)NC5\r\n" +
			"CMP)FKN\r\n" +
			"HQ3)VCN\r\n" +
			"CZC)JSX\r\n" +
			"K8V)G8T\r\n" +
			"KT9)WBS\r\n" +
			"VZH)Y6J\r\n" +
			"9H6)PVN\r\n" +
			"CCP)3M8\r\n" +
			"VP7)DB4\r\n" +
			"KXM)TQG\r\n" +
			"5KN)Z2Y\r\n" +
			"QL2)LF3\r\n" +
			"9NY)FXS\r\n" +
			"8VY)M8K\r\n" +
			"JBS)B4W\r\n" +
			"DZD)P4N\r\n" +
			"RQF)3Z5\r\n" +
			"3Q6)WRS\r\n" +
			"GXX)5NR\r\n" +
			"63W)3QB\r\n" +
			"W8Z)J3X\r\n" +
			"6VH)VJ6\r\n" +
			"4BW)YNF\r\n" +
			"MC3)TL6\r\n" +
			"SLD)J48\r\n" +
			"MYP)HQ7\r\n" +
			"DT6)G8J\r\n" +
			"GPF)1PY\r\n" +
			"6PP)KFB\r\n" +
			"GZ3)76P\r\n" +
			"CHJ)3GM\r\n" +
			"DX1)NVB\r\n" +
			"7LG)8ZY\r\n" +
			"QF1)HQ3\r\n" +
			"85T)7DX\r\n" +
			"6T3)K7Q\r\n" +
			"49V)4BW\r\n" +
			"Y3R)DJF\r\n" +
			"4HL)641\r\n" +
			"FYC)PGC\r\n"; // Expecting 245089
}