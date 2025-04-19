1. seed_inventory name count price 
2. view_inventory
3. register_user user credit_Limit
4. buy user List<Item,quantity> payment_Method
5. clear_due user List<OrderId> date_of_clearing
6. view_dues user 

Question Link: https://docs.google.com/document/d/1B2w4-H3v-9L-BH5vafUpYpVujT3baTEXmCbQdUssoS4/edit?tab=t.0#heading=h.snvjv23iqu6u

#Thoughts:
1. Item price can be changed later


E.g:

register abc 100.0
seed lays 10 5.0
seed kurkure 10 5.0
buy
abc
lays
1
-1
-1
BNPL
clear abc
<order-id>
-1
