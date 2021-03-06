package jsonrpc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public abstract class JsonRpcObj { //public solo per test
    JSONObject obj;
    //private boolean valid;
    String jsonRpcString;

    public String getJsonString() {
        return jsonRpcString;
    } //public solo per tesy

    /*public boolean isValid() {
        return valid;
    }*/

    abstract JSONObject toJsonObj() throws JSONRPCException; //crea oggetto json rpc utilizzando attributi. implementata in maniera differente in richiesta, risposta e errore

    static boolean checkMembersSubset(Enum<?> members[], JSONObject obj) {
        //verifica l'oggetto abbia solo i parametri contenuti nell'array dei membri
        ArrayList<String> memNames = new ArrayList<>();
        for (Enum<?> mem : members) {
            memNames.add(mem.toString());
        }
        for (String m : JSONObject.getNames(obj)) {
            if (!memNames.contains(m)) {
                return false;
            }
        }
        return true;
    }

    public static void putMember(JSONObject obj, String key, Member value) throws JSONException { //public solo per test
        switch (value.getType()) {
            case ARRAY:
            case OBJ:
                putStructuredMember(obj, key, value.getStructuredMember()); break;
            case BOOL: obj.put(key, value.getBool()); break;
            case NUMBER: obj.put(key, value.getNumber()); break;
            case STRING: obj.put(key, value.getString()); break;
            case NULL: obj.put(key, JSONObject.NULL); break;
            default: throw new InvalidParameterException("Invalid member type");
        }
    }

    public static void putMember(JSONArray array, Member value) {
        switch (value.getType()) {
            case ARRAY:
            case OBJ:
                putStructuredMember(array, value.getStructuredMember()); break;
            case BOOL: array.put(value.getBool()); break;
            case NUMBER: array.put(value.getNumber()); break;
            case STRING: array.put(value.getString()); break;
            case NULL: array.put(JSONObject.NULL); break;
            default: throw new InvalidParameterException("Invalid member type");
        }
    }

    static void putStructuredMember(JSONArray array, StructuredMember value) {
        if (value.isArray()) {
            array.put(value.getJSONArray());
        } else {
            array.put(value.getJSONObject());
        }
    }
    static void putStructuredMember(JSONObject obj, String key, StructuredMember member) throws JSONException {
        if (member.isArray()) {
            obj.put(key, member.getJSONArray());
        } else {
            obj.put(key, member.getJSONObject());
        }
    }

    public JSONObject getObj() {
        return this.obj;
    } //public solo per test
}
