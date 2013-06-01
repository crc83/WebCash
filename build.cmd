cd webcash-domain
call mvn -B clean install
cd ../webcash
call mvn -B clean install
cd ../webcash-spec
call mvn -B clean exec:java integration-test -Dspring.profiles.active=test
