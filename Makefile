files = $(shell find ./Java/src -name "*.java")
out = ./Java/bin
main = main.Main
testjar = jacoco/lib/jacocoagent.jar
testreport = jacoco/lib/jacococli.jar
testreportargs = jacoco.exec --classfiles $(out) --sourcefiles Java/src --html rapport/

testin = "sources/valeurs_test_IN.txt"
testout = "sources/valeurs_test_OUT.txt"

exec: compile
	java -cp $(out) $(main) $(testin) $(testout)

compile:
	javac -d $(out) $(files)

test: compile
	java -javaagent:$(testjar) -cp $(out) $(main) $(testin) $(testout)
	java -jar $(testreport) report jacoco.exec $(testreportargs)
	rm -f jacoco.exec

clean:
	rm -rf $(out)
	rm -f jacoco.exec
	rm -rf rapport
	rm -f $(testout)