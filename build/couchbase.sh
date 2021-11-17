echo 'Couchbase preperation started..'
res=`docker ps | grep couchbase`
read -ra arr <<<"$res"
echo $arr
# prepare couchbase
container=$arr

docker exec -t $container couchbase-cli cluster-init --cluster-name qbank-cluster \
   --cluster-username Administrator --cluster-password password --services data,index,query,fts,eventing,analytics \
   --cluster-ramsize 2048

docker exec -t $container couchbase-cli bucket-create -c localhost -u Administrator -p password \
  --bucket question --bucket-type couchbase --bucket-ramsize 1024

docker exec -t $container couchbase-cli user-manage -c localhost -u Administrator -p password \
  --set --rbac-username codigician --rbac-name codigician --rbac-password password --roles admin \
  --auth-domain local

docker exec -t $container cbq -e couchbase://couchbase -u Administrator -p password \
  --script="create primary index question on question;"