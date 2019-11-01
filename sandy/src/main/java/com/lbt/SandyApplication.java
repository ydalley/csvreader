package com.lbt;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.json.JsonParser;
import org.mozilla.javascript.tools.shell.Global;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SandyApplication {

//    private static ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

    public static void main(String[] args) throws IOException {
       callPreproc();
//       callPostroc();
    }



    static void initContext(String varpath, Map<String,Object> bindings) throws IOException {
        Path path = Path.of(varpath);
        String evaluationScript = Files.readString(path);
        Context cx = Context.enter();
        Scriptable scope = cx.initStandardObjects();
        Global global = new Global(cx);
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Initialize the standard objects (Object, Function, etc.). This must be done before scripts can be
            // executed. The null parameter tells initStandardObjects
            // to create and return a scope object that we use
            // in later calls.


            // Pass the Stock Java object to the JavaScript context

            for (String key : bindings.keySet()){
//                ScriptableObject.putProperty(global, key, bindings.get(key));
               String jso = mapper.writeValueAsString(bindings.get(key));
//                Object jsO = Context.javaToJS(bindings.get(key), global);

                ScriptableObject.putProperty(global, key, jso);
            }
            ScriptableObject.putProperty(global, "helper", new SandyApplication());
            ScriptableObject.putProperty(global, "printer", System.out);

            // Execute the script
            cx.evaluateString(global, evaluationScript, "EvaluationScript", 1, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Exit the Context. This removes the association between the Context and the current thread and is an
            // essential cleanup action. There should be a call to exit for every call to enter.
            Context.exit();
        }
    }

    public static void callPreproc() throws IOException {
        String scPath = "/Users/dallex/git/sandy/src/main/js/runme.js";

        List<User> users = Arrays.asList(User.UserBuilder.anUser().withFirstName("Tosin")
                        .withLastName("Hilo").withDateOfBirth(LocalDate.of(1972, 3, 12)).build(),
                User.UserBuilder.anUser().withFirstName("Bayo")
                        .withLastName("Graham").withDateOfBirth(LocalDate.of(2001, 1, 23)).build());

        String s;
        Map bindings = new HashMap();
        bindings.put("users", users);
        initContext(scPath,bindings);
    }

    public static void callPostroc() throws IOException {
        String scPath = "/Users/dallex/git/sandy/src/main/js/runanother.js";

        List<User> users = Arrays.asList(User.UserBuilder.anUser().withFirstName("Wale")
                        .withLastName("Bolahan").withDateOfBirth(LocalDate.of(1980, 3, 12)).build(),
                User.UserBuilder.anUser().withFirstName("James")
                        .withLastName("Williams").withDateOfBirth(LocalDate.of(2007, 1, 23)).build());
        User.UserBuilder.anUser().withFirstName("Chinwe")
                .withLastName("Obi").withDateOfBirth(LocalDate.of(2008, 1, 23)).build();

        String s;
        Map bindings = new HashMap();
        bindings.put("users", users);
        initContext(scPath, bindings);
    }

    public static int executeSQL(String script) {

        System.out.println("Executing script in Java scontext");
        System.out.println(script);
        System.out.println("Done");
        return Double.valueOf(Math.random() * 10).intValue();
    }


    public static String LPAD(String val, int number, char p) {
        return StringUtils.leftPad(val, number, p);
    }

    public static String RPAD(String val, int number, char p) {
        return StringUtils.rightPad(val, number, p);
    }

    public static String LTRIM(String val, String p) {
        return StringUtils.stripStart(val, p);
    }

    public static String RTRIM(String val, String p) {
        return StringUtils.stripEnd(val, p);
    }

    public static String MID$(String val, int start, int stop) {
        return StringUtils.substring(val, start, stop);
    }

    public static String MID$(String val, int start) {
        return StringUtils.substring(val, start);
    }

    public static int GETPOSITION(String val, String find) {
        return StringUtils.indexOf(val, find);
    }
}
