make: MinimizeTime.class Models.class Process.class Time.class
	java Models
	
MinimizeTime.class Models.class Process.class Time.class: MinimizeTime.java Process.java Models.java Time.java
	javac MinimizeTime.java Process.java Models.java Time.java
	
		
clean:
	rm *.class
