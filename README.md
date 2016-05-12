# JSONSerializer
This is a tool that helps to serialize your data objects to JSON strings and to load them back later.

A simple demo:

1. Let's assume that you have an object to output as a JSON string like below:

	public class Person {

		public String name;
		
		public String gender;
		
		public int age;
		
		public Person (String name, String gender, int age) {
			this.name = name;
			this.gender = gender;
			this.age = age;
		}
	}

2. Traditionally, you need to follow the below steps:

	Person person = new Person("Sam", "Male", 33);
	JSONObject jo = new JSONObject();
	jo.put("name", person.name);
	jo.put("gender", person.gender);
	jo.put("age", person.age);
	String jsonString = jo.toString();
	
3. Likewise, if you want to load a JSON string back to a Person object, you need to:
	String jsonString = "{\"gender\":\"Male\",\"name\":\"Sam\",\"age\":33}";
	Person person = new Person(null, null, -1);
	JSONObject jo = new JSONObject(jsonString);
	person.name = jo.optString("name");
	person.gender = jo.optString("gender");
    person.age = jo.optInt("age");

Then this tool can help you to do it easier and more quickly:

1. Add only 2 changes to your object class:
	// CAUTION: Make the Person an extended subclass of JSONBean
	public class Person extends JSONBean {

		public String name;
		
		public String gender;
		
		public int age;
		
		public Person (String name, String gender, int age) {
			this.name = name;
			this.gender = gender;
			this.age = age;
		}
		
		// CAUTION: Give a public and parameterless constructor
		public Person() {
			// Leave it blank here and that's all
		}
	}
	
2. Translate the person object to a JSON string
	Person person = new Person("Sam", "Male", 33);
	String jsonString = person.toJSONString();
	
3. Load a JSON string to instantiate a Person object
	String jsonString = "{\"gender\":\"Male\",\"name\":\"Sam\",\"age\":33}";
	Person person = new Person();
	person.loadJSONString(jsonString);
	
The more complex the class is, the more efforts it spares you.

For more wonderful usages, please refer to the sample files under "/src/samples/main/java/org/samwit/tools/jsonserializer/samples/jsonbean/".
