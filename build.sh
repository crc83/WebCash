cd webcash-domain
mvn -B clean install
cd ../webcash
mvn -B clean install
# cd ../webcash-spec
# mvn -B clean exec:java integration-test -Dspring.profiles.active=test