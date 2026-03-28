files = $(shell find ./Java/src -name "*.java")
out = ./Java/bin
main = main.Main
testjar = jacoco/lib/jacocoagent.jar
testreport = jacoco/lib/jacococli.jar
reportdir = rapport/
reportcsv = rapport/report.csv
reportmd = rapport/report.md
testreportargs = jacoco.exec --classfiles $(out) --sourcefiles Java/src --html $(reportdir)
testcsvargs = jacoco.exec --classfiles $(out) --sourcefiles Java/src --csv $(reportcsv)
testout = "sources/valeurs_test_OUT*.txt"

exec: compile
	java -cp $(out) $(main)

compile:
	javac -d $(out) $(files)

test: compile
	java -javaagent:$(testjar) -cp $(out) $(main)
	java -jar $(testreport) report jacoco.exec $(testreportargs)
	rm -f jacoco.exec

test-txt: compile
	java -javaagent:$(testjar) -cp $(out) $(main)
	java -jar $(testreport) report jacoco.exec $(testcsvargs)
	awk -F',' 'NR==1{printf "|"; for(i=1;i<=NF;i++) printf " %s |", $$i; print "\n|"; for(i=1;i<=NF;i++) printf "---|"; print ""} NR>1{printf "|"; for(i=1;i<=NF;i++) printf " %s |", $$i; print ""}' $(reportcsv) > $(reportmd)
	rm -f jacoco.exec
	rm -f $(reportcsv)

clean:
	rm -rf $(out)
	rm -f jacoco.exec
	rm -rf rapport
	rm -f $(testout)
	rm -f $(reportcsv)
