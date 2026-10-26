#!/usr/bin/env bash
# Seeds the Step 0 data of the exam paper through the application's own
# /save endpoints. Run this after the app is up on http://localhost:8080.
set -euo pipefail

BASE="http://localhost:8080/api"

post() {
  curl -sS -f -X POST "$1" -H "Content-Type: application/json" -d "$2" > /dev/null
}

id_by() {
  # id_by <path> <jq filter selecting the one row>
  curl -s "$BASE/$1/all" | jq -r "$2"
}

echo "== AnimalType =="
post "$BASE/animal-types/save" '{"name":"Cattle"}'
post "$BASE/animal-types/save" '{"name":"Goats"}'
post "$BASE/animal-types/save" '{"name":"Pigs"}'
post "$BASE/animal-types/save" '{"name":"Poultry"}'

CATTLE_ID=$(id_by animal-types '.[] | select(.name=="Cattle") | .id')
GOATS_ID=$(id_by animal-types '.[] | select(.name=="Goats") | .id')
PIGS_ID=$(id_by animal-types '.[] | select(.name=="Pigs") | .id')
# Poultry is held by nobody on purpose (C3).

echo "== Stall =="
post "$BASE/stalls/save" '{"stallNumber":11,"name":"Stall 11"}'
post "$BASE/stalls/save" '{"stallNumber":12,"name":"Stall 12"}'
post "$BASE/stalls/save" '{"stallNumber":13,"name":"Stall 13"}'
post "$BASE/stalls/save" '{"stallNumber":14,"name":"Stall 14"}'

STALL11=$(id_by stalls '.[] | select(.stallNumber==11) | .id')
STALL12=$(id_by stalls '.[] | select(.stallNumber==12) | .id')
STALL13=$(id_by stalls '.[] | select(.stallNumber==13) | .id')
# Stall 14 is left free on purpose (no seller).

echo "== Seller =="
post "$BASE/sellers/save" "{\"firstName\":\"Pasika Julien\",\"lastName\":\"Murera\",\"phone\":\"0788000001\",\"farmLocation\":\"Nyagatare\",\"stall\":{\"id\":\"$STALL11\"},\"animalTypes\":[{\"id\":\"$CATTLE_ID\"},{\"id\":\"$GOATS_ID\"}]}"
post "$BASE/sellers/save" "{\"firstName\":\"Sandra\",\"lastName\":\"Murera\",\"phone\":\"0788000002\",\"farmLocation\":\"Kayonza\",\"stall\":{\"id\":\"$STALL12\"},\"animalTypes\":[{\"id\":\"$CATTLE_ID\"}]}"
post "$BASE/sellers/save" "{\"firstName\":\"Leondrie\",\"lastName\":\"Gikundiro\",\"phone\":\"0788000003\",\"farmLocation\":\"Kirehe\",\"stall\":{\"id\":\"$STALL13\"},\"animalTypes\":[{\"id\":\"$PIGS_ID\"}]}"

SELLER1=$(id_by sellers '.[] | select(.firstName=="Pasika Julien") | .id')
SELLER2=$(id_by sellers '.[] | select(.firstName=="Sandra") | .id')
SELLER3=$(id_by sellers '.[] | select(.firstName=="Leondrie") | .id')

echo "== Buyer =="
# Kim and Moreen share the lastName "Gakuba" (B1).
post "$BASE/buyers/save" '{"firstName":"Kim","lastName":"Gakuba","nationalId":"1198880023456789","phone":"0788100001","district":"Gasabo"}'
post "$BASE/buyers/save" '{"firstName":"Moreen","lastName":"Gakuba","nationalId":"1198880023456790","phone":"0788100002","district":"Kicukiro"}'
post "$BASE/buyers/save" '{"firstName":"Tessy Mick","lastName":"Irakoze","nationalId":"1198880023456791","phone":"0788100003","district":"Nyarugenge"}'
post "$BASE/buyers/save" '{"firstName":"Fred","lastName":"Nkotanyi","nationalId":"1198880023456792","phone":"0788100004","district":"Musanze"}'
post "$BASE/buyers/save" '{"firstName":"Diane","lastName":"Mbabazi","nationalId":"1198880023456793","phone":"0788100005","district":"Huye"}'
post "$BASE/buyers/save" '{"firstName":"Jessicah","lastName":"Gatera","nationalId":"1198880023456794","phone":"0788100006","district":"Rubavu"}'
post "$BASE/buyers/save" '{"firstName":"Cissy","lastName":"Mwiza","nationalId":"1198880023456795","phone":"0788100007","district":"Rwamagana"}'

BUYER_KIM=$(id_by buyers '.[] | select(.nationalId=="1198880023456789") | .id')
BUYER_MOREEN=$(id_by buyers '.[] | select(.nationalId=="1198880023456790") | .id')
BUYER_TESSY=$(id_by buyers '.[] | select(.nationalId=="1198880023456791") | .id')
BUYER_FRED=$(id_by buyers '.[] | select(.nationalId=="1198880023456792") | .id')
BUYER_DIANE=$(id_by buyers '.[] | select(.nationalId=="1198880023456793") | .id')
BUYER_JESSICAH=$(id_by buyers '.[] | select(.nationalId=="1198880023456794") | .id')
BUYER_CISSY=$(id_by buyers '.[] | select(.nationalId=="1198880023456795") | .id')

echo "== AnimalSale =="
sale() {
  post "$BASE/animal-sales/save" "{\"saleDate\":\"$1\",\"status\":\"$2\",\"breed\":\"$3\",\"askingPrice\":$4,\"buyer\":{\"id\":\"$5\"},\"seller\":{\"id\":\"$6\"}}"
}
# Kim Gakuba has 3 sales (B1/E2 reuse); seller1 has two sales on 2026-10-20, one WITHDRAWN (D1);
# 3 sales are SOLD (payments in Part E); all four statuses are used.
sale "2026-10-05" "SOLD"       "Cyozi"   500000 "$BUYER_KIM"      "$SELLER1"
sale "2026-10-12" "NEGOTIATED" "Gaju"    450000 "$BUYER_KIM"      "$SELLER2"
sale "2026-11-02" "INQUIRED"   "Mayeji"  600000 "$BUYER_KIM"      "$SELLER3"
sale "2026-10-20" "INQUIRED"   "Hinda"   300000 "$BUYER_MOREEN"   "$SELLER1"
sale "2026-10-20" "WITHDRAWN"  "Kadogo"  320000 "$BUYER_MOREEN"   "$SELLER1"
sale "2026-10-25" "SOLD"       "Muunga"  700000 "$BUYER_TESSY"    "$SELLER2"
sale "2026-11-10" "NEGOTIATED" "Bamba"   480000 "$BUYER_FRED"     "$SELLER3"
sale "2026-11-15" "INQUIRED"   "Cyozi"   520000 "$BUYER_DIANE"    "$SELLER1"
sale "2026-10-08" "WITHDRAWN"  "Gaju"    350000 "$BUYER_JESSICAH" "$SELLER3"
sale "2026-11-20" "SOLD"       "Mayeji"  610000 "$BUYER_CISSY"    "$SELLER2"

echo "Seed data loaded."
