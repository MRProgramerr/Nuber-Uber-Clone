package nuber.students;

public abstract class Person
{
	
	public final static String[] SAMPLE_NAMES = {"Bryan","Olivia","Vincent","Kenneth","Debra","Jack","Harold","Isabella","Jerry","Stephen","Larry","Ruth","Diane","Gerald","Brandon","Virginia","Helen","Gary","Noah","Michell","Alexis","Zachary","Gregory","Arthur","Dennis","Terry","Rose","Jeffrey","Jean","Jane","Brenda","Louis","Mary","Julia","Sandra","Catherine","Adam","Samantha","Amber","Ralp","Jacob","Raymond","Rachel","Kelly","Danielle","John","Melissa","Albert","Brian","Eugne","Jeremy","Nathan","Beverly","Margaret","Natalie","Charlotte","Ann","Betty","Randy","Tyler","Emma","Willie","Charles","Lisa","Anthony","Sara","Sean","James","Johnny","Jud","Evelyn","Theresa","Gloria","Emily","Denise","Frank","Steven","Jacqueline","Diana","Ronald","Kayla","Joe","Nicole","Scott","Henry","Lawrence","Ethan","Stephanie","Kevin","Kathleen","Angela","Joyce","Sarah","Benjamin","Carl","Cynthia","Nicholas","Andrea","Robert","Martha","Susan","Ryan","Alexander","Donna","Thomas","Brittany","Timothy","Hannah","Heather","Linda","Joan","Pamela","Maria","Kyle","Logan","Paul","Andrew","Dylan","Christina","Kimberly","Patricia","Victoria","Philip","Shirley","Billy","Jonathan","Roy","Christopher","Roger","Anna","Richard","Doris","Bruce","Peter","Dorothy","Amanda","Marilyn","Christine","Marie","Karen","Jordan","Wayne","Edward","Justin","Walter","Rebecca","Sharon","Jesse","Joshua","Sophia","Grace","Deborah","Ashley","Joseph","Matthew","Alan","Julie","Abigail","Mark","Megan","Juan","Michael","Frances","George","Eric","William","Cheryl","Daniel","Katherine","Amy","Laura","Donald","Jennifer","Judith","Carolyn","Christian","Janice","Barbara","Elijah","Nancy","Aaron","Teresa","Bobby","Douglas","Russell","Jose","Keith","Kathryn","Samuel","Austin","Jason","Jessica","David","Lauren","Patrick","Gabriel","Alice","Elizabeth","Madison","Carol"};
	private static int nextNameIndex = 0;
	
	public String name;
	protected int maxSleep;
	
	public Person(String name,int maxSleep) {
		this.name = name;
		this.maxSleep = maxSleep;
	}
	
	public static String getRandomName()
	{
		nextNameIndex = ++nextNameIndex % SAMPLE_NAMES.length;
		
		return SAMPLE_NAMES[nextNameIndex];
	}

}
