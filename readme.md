1. seed_inventory name count price 
2. view_inventory
3. register_user user credit_Limit
4. buy user List<Item,quantity> payment_Method
5. clear_due user List<OrderId> date_of_clearing
6. view_dues user 


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
