printer.println("Starting runme")
var i = helper.executeSQL("select * from dual")
printer.println(i)
print(users)
// users= JSON.parse(users);

// for( i=0 ; i< users.size(); ++ i){
//     print(users[i])
//     print(users.get(i));
//     print(helper.MID$(users.get(i).getFirstName(),2,5));
// }
print(users[0])

executeSQL()

for(var user in users){
    print(user)
    for(p in user)
        print(p)
}

printer.println("Finished runme")
