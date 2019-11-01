print("Starting runanother")
// hook.Run("select * from dual1")
var helper =com.lbt.SandyApplication
// var helper = Java.type("com.lbt.SandyApplication");
var i = helper.executeSQL("select * from dual")
print(i)
for(var k in users){
    print("***********************"+users[k].toString()+"***************************")
    u = users[k]
    print(helper.RPAD(u.firstName,7,'_'))
    print(u.firstName)
    print(helper.MID$(u.firstName, 2, 5))
}


print("Finished runanother")