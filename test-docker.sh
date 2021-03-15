docker stop postgres 2>/dev/null
docker rm postgres 2>/dev/null
docker run -d -p 5432:5432 -e POSTGRES_HOST_AUTH_METHOD=trust --name postgres --tmpfs /var/lib/postgresql/data postgres:11-alpine

docker stop mongo 2>/dev/null
docker rm mongo 2>/dev/null
docker run -d -p 27017:27017 --name mongo --tmpfs /data/db mongo:4-bionic

docker stop redis 2>/dev/null
docker rm redis 2>/dev/null
docker run -d -p 6379:6379 --name redis redis:6-alpine

sleep 4
docker exec postgres psql -U postgres -d postgres -c "create database test;"
